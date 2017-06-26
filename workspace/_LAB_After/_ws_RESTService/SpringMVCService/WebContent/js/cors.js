/**
 * This is for Cross-site Origin Resource Sharing (CORS) requests.
 *
 * Additionally the script will fail-over to a proxy if you have one set up.
 *
 * @param string   url      the url to retrieve
 * @param mixed    data     data to send along with the get request [optional]
 * @param function callback function to call on successful result [optional]
 * @param string   type     the type of data to be returned [optional]
 */
function getCORS(url, data, callback, type) {
	try {
		if (jQuery.browser.msie) {
			if (window.XDomainRequest) {
		        // Use Microsoft XDR
		        var xdr = new XDomainRequest();
		        xdr.open("get", url);
		        xdr.onload = function() {
		            callback(this.responseText, 'success');
		        };
		        xdr.send();	
			} else {
				alert('브라우저가 Cross Origin Resource Sharing을 지원하지 않습니다.')
			}
		} else {
	        // Try using jQuery to get data
	        jQuery.get(url, data, callback, type);
		}
	} catch (e) {
		alert(e.name + " : " + e.message);
	}
}

/**
 * This method is for Cross-site Origin Resource Sharing (CORS) POSTs
 *
 * @param string   url      the url to post to
 * @param mixed    data     additional data to send [optional]
 * @param function callback a function to call on success [optional]
 * @param string   type     the type of data to be returned [optional]
 */
function postCORS(url, data, callback, type)
{
    try {
    	if (jQuery.browser.msie) {
    		if (window.XDomainRequest) {
                
                var xdr = new XDomainRequest();
                xdr.open("post", url);
                xdr.send(data);
                xdr.onload = function() {
                    callback(xdr.responseText, 'success');
                };
    		} else {
    			alert('브라우저가 Cross Origin Resource Sharing을 지원하지 않습니다.')
    		}
    	} else {
    		jQuery.post(url, data, callback, type);
    	}
    } catch(e) {
    	alert(e.name + " : " + e.message);
    }
}