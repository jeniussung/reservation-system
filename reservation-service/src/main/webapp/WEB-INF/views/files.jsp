<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
    <script type="text/javascript" src="/resources/lib/jquery.min.js"></script>
</head>
<body>

<form method="post" action="/files" enctype="multipart/form-data" id = "fff" data-id = "${a}">
    title : <input type="text" name="title"><br>
    <input id='fileid' type="file" name="file" multiple><br>

    <input type="submit" value="등록">
</form>

<script type="text/javascript">
    console.log("id :%s", $('#fff').data('id'));
</script>

<img alt="asdf" class="img_thumb" src="/files/1">
</body>
</html>
