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
    <center>
        <form method="POST" action="upload" enctype="multipart/form-data">
        	URI(unique identifier): <input type="text" name="URL" required=true />
            Select file to upload: <input type="file" name="uploadFile" required=true />
            <br/><br/>
            <input type="submit" value="Upload" />
        </form>
    </center>
</body>
</html>