package org.thinker.openapi;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

public class ApiKeyProcessor {
	
	private static final String API_KEY_PROPERTIES = "apiKey.properties";
	private static final String SECRET_KEY = "secret";
	private static final String MAX_KEY = "max";
	
    private static final Base64 BASE64 = new Base64();

	private static Logger logger = Logger.getLogger(ApiKeyProcessor.class);
	
	private static String secretKey;
	private static int maxCount;
	
	
	private Properties prop;
	
	private ApiKeyRepository repository;
	

	
	public ApiKeyProcessor()throws ApiKeyException{
		
		this(ApiKeyProcessor.class.getResource(API_KEY_PROPERTIES));
		
	}
	
	public ApiKeyProcessor(URL url)throws ApiKeyException{
	
		prop = new Properties();
		
		try {
			prop.load(url.openStream());
			secretKey = prop.getProperty(SECRET_KEY);
			maxCount = Integer.parseInt(prop.getProperty(MAX_KEY));
			
			logger.info("repository: "+ repository);
			
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new ApiKeyException("Could not find API KEY FILE");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApiKeyException(e);
		} 		
	}
	

	public void setRepository(ApiKeyRepository repository) {
		this.repository = repository;
	}
	

	public String requestNewAPIKey(ApiKeyVO apiKeyVO)throws Exception{
		
		logger.info("secret key: " + secretKey);
		
		HmacSHA1 sha = new HmacSHA1();
		sha.setConsumerSecret(secretKey);
		
		String keyValue = sha.getSignature(apiKeyVO.getHostName()) ;
		
		logger.info("hostName : " + apiKeyVO);
		logger.info("keyValue: "+ keyValue);
		apiKeyVO.setApiKey(keyValue);
		
		try {
			repository.create(apiKeyVO);
		} catch (Exception e) {
			throw new ApiKeyException("SAME KEY IS ALREADY EXIST.");
		}
		
		return keyValue;
		
	}
	
	public void checkApiKey(String hostname, String apiKey)throws ApiKeyException{
		
		ApiKeyVO vo = repository.read(hostname);
		
		if(vo == null){
			throw new ApiKeyException("OPEN API KEY IS UNREGISTED ");
		}
		
		String encodedKey= null;
		
		try {
			encodedKey= URLEncoder.encode( vo.getApiKey(),"UTF-8");
			
			logger.info("requested api key: "+ apiKey);
			logger.info("encoded api key: "+encodedKey);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(apiKey == null || apiKey.equals( encodedKey) == false ){
			throw new ApiKeyException("OPEN API KEY IS NOT VALID PLZ.. CHECK YOUR API KEY");
		}
		
		if(vo.getCount() > maxCount){
			throw new ApiKeyException("EXCESSIVE NUMBER OF REQUEST");
		}
		
		logger.info("######## : " + vo);
		
		repository.update(hostname);
	}
	
	
	
	 class HmacSHA1  {
			
		    private static final String BASE64_ENCODING = "ISO-8859-1";

		    
		    private static final String ENCODING = "UTF-8";
		    private static final String MAC_NAME = "HmacSHA1";
		    
		    private String consumerSecret;

		    private String tokenSecret;

		    public String getConsumerSecret() {
		        return consumerSecret;
		    }

		    public void setConsumerSecret(String consumerSecret) {
		        this.consumerSecret = consumerSecret;
		    }

		    public String getTokenSecret() {
		        return tokenSecret;
		    }

		    public void setTokenSecret(String tokenSecret) {
		        this.tokenSecret = tokenSecret;
		    }


		    public  byte[] decodeBase64(String s) {
		    	byte[] b;
		    	try {
		    	    b = s.getBytes(BASE64_ENCODING);
		    	} catch (UnsupportedEncodingException e) {
		    	    System.err.println(e + "");
		    	    b = s.getBytes();
		    	}
		    	return BASE64.decode(b);
		    }

		    public String base64Encode(byte[] b) {
				byte[] b2 = BASE64.encode(b);
				try {
				    return new String(b2, BASE64_ENCODING);
				} catch (UnsupportedEncodingException e) {
				    System.err.println(e + "");
				}
				return new String(b2);
		    }
		    
			
		    
		    public String getSignature(String baseString) throws Exception {
		    	
		    	logger.info("HMAC_SHA1 getSignature baseString " + baseString);
		    	
		        try {
		            String signature = base64Encode(computeSignature(baseString));
		            
		            logger.info("HMAC_SHA1 getSignature signature " + signature);
		            return signature;
		        } catch (Exception e) {
		            throw new Exception(e);
		        } 
		    }
		    



		    public  boolean isValid(String signature, String baseString)throws Exception {
		        try {
		            byte[] expected = computeSignature(baseString);
		            byte[] actual = decodeBase64(signature);
		            return equals(expected, actual);
		        } catch (Exception e) {
		            throw new Exception(e);
		        } 
		    }
		    

		    
		    public boolean equals(byte[] a, byte[] b) {
		        if (a == null)
		            return b == null;
		        else if (b == null)
		            return false;
		        else if (b.length <= 0)
		            return a.length <= 0;
		        byte diff = (byte) ((a.length == b.length) ? 0 : 1);
		        int j = 0;
		        for (int i = 0; i < a.length; ++i) {
		            diff |= a[i] ^ b[j];
		            j = (j + 1) % b.length;
		        }
		        return diff == 0;
		    }

		    private byte[] computeSignature(String baseString)
		            throws GeneralSecurityException, UnsupportedEncodingException {
		        SecretKey key = null;
		        synchronized (this) {
		            if (this.key == null) {
		            	
		            	logger.info("compute Signature consumer secret " + getConsumerSecret());
		            	logger.info("compute Signature token secret " + getTokenSecret());
		            	
		                String keyString =percentEncode(getConsumerSecret())
		                        + '&' + percentEncode(getTokenSecret());
		                
		                logger.info("compute Signature keyString " + keyString);
		                
		                byte[] keyBytes = keyString.getBytes(ENCODING);
		                this.key = new SecretKeySpec(keyBytes, MAC_NAME);
		            }
		            key = this.key;
		            
		            
		        }
		        Mac mac = Mac.getInstance(MAC_NAME);
		        mac.init(key);
		        byte[] text = baseString.getBytes(ENCODING);
		        
		        logger.info("compute Signature text " + new String(text));
		        
		        return mac.doFinal(text);
		    }



		    private SecretKey key = null;

		    public String percentEncode(String s) {
		        if (s == null) {
		            return "";
		        }
		        try {
		            return URLEncoder.encode(s, ENCODING)
		                    // OAuth encodes some characters differently:
		                    .replace("+", "%20").replace("*", "%2A")
		                    .replace("%7E", "~");
		            // This could be done faster with more hand-crafted code.
		        } catch (UnsupportedEncodingException wow) {
		            throw new RuntimeException(wow.getMessage(), wow);
		        }
		    }


		}
	 
	 public static void main(String[] args) throws Exception{
		
		 ApiKeyProcessor gen = new ApiKeyProcessor();
		 logger.info(gen);
		 logger.info(gen.prop.getProperty("secret"));
	}
}
