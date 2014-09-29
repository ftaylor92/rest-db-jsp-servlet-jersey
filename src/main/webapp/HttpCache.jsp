<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>File Upload</title>
</head>
<body>
	<center><h3>HTTP Cache/Mock/Virtualization</h3></center>
	
	<ol>
	<li>Make multiple calls to limited-access URLs without worrying about being cut-off or about availability</li>
	<li>Mocking external REST-GET URLs/web-services (for testing)</li>
	<li>Web-Service Virualization through custom URLs to simulate HTTP Response (for testing)</li>
	</ol>
	
	All calls to this web-service, after the first time, will returned cached content and won't call the original passed-in URL again.
	<br/>
	<br/>
	<U>There are two ways to use this service:</U>
	<ol>
	<li>Just call it with a valid, <a href="http://www.w3schools.com/jsref/jsref_encodeURI.asp">encoded</a> URL with "&"s replaced with "ANDK"s <small>(see included createFullHttpCacherUrl() in this page source)</small>.  ex: <em><a id="mockUrlStr" href="http://rest-db-jsp-servlet-jersey.fmtmac2.cloudbees.net/rest/httpcache?url=http%3A%2F%2Fwww.google.com">http://rest-db-jsp-servlet-jersey.fmtmac2.cloudbees.net/rest/httpcache?<B>url=http://www.google.com</B></a></em></li>
	<br/><li> (1st) Upload a HTTP Response as a file using this form below, and then (2nd) call this REST-URL: <a id="mockUrl" href="http://rest-db-jsp-servlet-jersey.fmtmac2.cloudbees.net/rest/httpcache?url=YourUniqueId">http://rest-db-jsp-servlet-jersey.fmtmac2.cloudbees.net/rest/httpcache?<B>url=YourUniqueId</B></a></em> as many times as you like to get back the same cached file content</li>
	</ol>
	<br/>
    <form method="POST" action="upload" enctype="multipart/form-data">
    	URI(unique identifier): <input type="text" name="URL" required=true value="YourUniqueId" /><br/>
        Select file to always return as mock-HTTP-Response: <input type="file" name="uploadFile" required=true />
        <br/><br/>
        <input type="submit" value="Upload" />
    </form>
    
    <script>
    	/**
    		encode URL to use with HTTPCacher web service.
    		param: originalUrl URL you want called and HTTP-Result returned.
    	**/
    	function encodeForHttpCacher(originalUrl) {
    		return encodeURI(originalUrl).replace(/&/g,'ANDK');
    	}
    
    	/**
		creates full URL to use to call HTTPCacher web service with the URL you want called/mocked.
			param: originalUrl URL you want called and HTTP-Result returned.
		**/
    	function createFullHttpCacherUrl(originalUrl) {
				var cacherlUrl= "http://rest-db-jsp-servlet-jersey.fmtmac2.cloudbees.net/rest/httpcache?url=";
    		return cacherlUrl+ encodeForHttpCacher(originalUrl);
    	}
    </script>

</body>
</html>
