<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<html>
	<head>
		<title>책 생성하기</title>
	</head>
	
	<body>
	<h1>책 생성하기</h1>
	<!-- 서버로 전달되는 항목들은 from 태그 안에 존재 해야 한다. -->
	<!-- 서버는 name 속성을 키로, value 속성을 값으로 판단한다. -->
	<!-- 서버로 전달되는 키:값 쌍을 HTTP 파라미터 라고 부른다. -->
	<form method="POST">
		<p>제목 :<input type="text" name="title" /></p>
		<p>카테고리 : <input type="text" name="category" /></p>
		<p>가격 : <input type="text" name ="price" /></p>
		<!-- submit : form의 값을 전송하는 버튼  -->
		<p><input type="submit" value ="저장"/>
	</form>
	</body>
</html>




