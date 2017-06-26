package com.tfactory.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	
	private static String savePath;
	
	static{
		
		try{
			
			URL url = FileUtil.class.getClassLoader().getResource("");
			
			savePath = new File(url.toURI()).getParentFile().getParentFile().getPath();
			savePath = savePath+File.separator+"upload" + File.separator;

		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	public static String saveFile(MultipartFile origin)throws IOException{
		
		String uploadedName = null;
		
		if(origin == null || origin.getBytes().length == 0){
			return null;
		}
		
		
		UUID uid = UUID.randomUUID();
		
		String fileName = origin.getOriginalFilename();
		uploadedName = uid + "_"+fileName;
		
		System.out.println(savePath);
		System.out.println(uploadedName);
		
		FileCopyUtils.copy(origin.getInputStream(), new FileOutputStream(savePath+uploadedName));
		
		
		return uploadedName;
	}
	
	public static void deleteFile(String fileName){
		
		File targetFile = new File(savePath+fileName);
		
		targetFile.delete();
		
		
	}

}
