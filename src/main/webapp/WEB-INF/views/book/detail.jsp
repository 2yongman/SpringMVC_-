<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!-- 컨트롤러에서 보여준 데이터를 뷰에 표현하려면 JSTL을 사용해야 된다. -->
<!-- fmt는 데이터 포멧터 태그 라이브러리, 원본 데이터의 형식을 바꿔주는 역할  -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<title>책 상세</title>
</head>

<body>
	<h1>책 상세</h1>
	<p>제목 : ${data.title} }</p> <!-- 컨트롤러에서 mav 객체에 data라는 이름으로 db의 정보를 넣었기 때문에 ${data.title} 형식으로 사용-->
	<p>카테고리 : ${data.category}</p>
	
	<!--  -->
	<p>
		가격 :
		<fmt:formatNumber type="number" maxFractionDigits="3"
			value="${data.price}" />
	</p>
	
	
	<p>
		입력일 :
		<fmt:formatDate value="${data.insert_date}"
			pattern="yyyy.MM.dd HH:mm:ss" />
	</p>
	<!-- bookId의 경우 따로 키와 값을 설정했으므로 ${bookId} 형식으로 가지고 온다. -->
	<p>
		<a href="/update?bookId=${bookId}">수정</a>
	</p>
	
	<!-- 삭제는 데이터를 변경시키기 때문에 POST 메소드가 좋다. -->
	<!-- /delete URL에 bookId 파라미터를 함께 보냄으로 데이터를 삭제하는 HTML 태그다.  -->
	<!-- form 태그의 action속성은 URI를 지칭한다. 현재 주소창의 주소와 다른 URI로 서버에 전달을 해야 할 경우 action 속성을 명시적으로 설정한다. -->
	<form method="POST" action="/delete">
	<!-- type="hidden"은 숨은 태그다. 사용자에게 보이진 않지만 서버로 전달하거나 숨겨놓고 값을 사용해야 할 때 쓰인다. -->
		<input type="hidden" name="bookId" value="${bookId}" /> <input
			type="submit" value="삭제" />
	</form>
	<p>
		<a href="/list">목록으로</a>
	</p>
</body>
</html>