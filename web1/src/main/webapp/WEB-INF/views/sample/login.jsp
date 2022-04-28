<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Login</h1>
<form action="" method="post"> <%--action에 값이 없으면 현재 경로 --%>
	<div>
		<label for="userid">userid</label>
		<input type="text" name="userid" id="userid"/>
	</div>
	<div>
		<label for="password">password</label>
		<input type="password"" name="password" id="password""/>
	</div>
	<!-- <div>
		<label for="addr">addr</label>
		<input type="text"" name="addr" id="addr""/>
	</div>
	<div>
		<label for="age">age</label>
		<input type="text"" name="age" id="age""/>
	</div> -->
	<div>
		<button type="submit">전송</button>
	</div>
</form>
</body>
</html>