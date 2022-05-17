<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!-- ��Ʈ�ѷ����� ������ �����͸� �信 ǥ���Ϸ��� JSTL�� ����ؾ� �ȴ�. -->
<!-- fmt�� ������ ������ �±� ���̺귯��, ���� �������� ������ �ٲ��ִ� ����  -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<title>å ��</title>
</head>

<body>
	<h1>å ��</h1>
	<p>���� : ${data.title} }</p> <!-- ��Ʈ�ѷ����� mav ��ü�� data��� �̸����� db�� ������ �־��� ������ ${data.title} �������� ���-->
	<p>ī�װ� : ${data.category}</p>
	
	<!--  -->
	<p>
		���� :
		<fmt:formatNumber type="number" maxFractionDigits="3"
			value="${data.price}" />
	</p>
	
	
	<p>
		�Է��� :
		<fmt:formatDate value="${data.insert_date}"
			pattern="yyyy.MM.dd HH:mm:ss" />
	</p>
	<!-- bookId�� ��� ���� Ű�� ���� ���������Ƿ� ${bookId} �������� ������ �´�. -->
	<p>
		<a href="/update?bookId=${bookId}">����</a>
	</p>
	
	<!-- ������ �����͸� �����Ű�� ������ POST �޼ҵ尡 ����. -->
	<!-- /delete URL�� bookId �Ķ���͸� �Բ� �������� �����͸� �����ϴ� HTML �±״�.  -->
	<!-- form �±��� action�Ӽ��� URI�� ��Ī�Ѵ�. ���� �ּ�â�� �ּҿ� �ٸ� URI�� ������ ������ �ؾ� �� ��� action �Ӽ��� ��������� �����Ѵ�. -->
	<form method="POST" action="/delete">
	<!-- type="hidden"�� ���� �±״�. ����ڿ��� ������ ������ ������ �����ϰų� ���ܳ��� ���� ����ؾ� �� �� ���δ�. -->
		<input type="hidden" name="bookId" value="${bookId}" /> <input
			type="submit" value="����" />
	</form>
	<p>
		<a href="/list">�������</a>
	</p>
</body>
</html>