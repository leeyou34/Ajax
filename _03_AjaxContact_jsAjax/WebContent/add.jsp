<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.ajaxstudy.contact.domain.*" %>
<%@ page import="com.ajaxstudy.contact.util.*" %>
<%
	request.setCharacterEncoding("UTF-8");
	String status = "ok";
	String message = "";
	
	//파라미터 전송 방식이 POST 일때만 처리하겠다.
	if(request.getMethod().equals("POST")) {
		//POST로 전송된 파라미터 request 객체를 이용하여 받음
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String address = request.getParameter("address");
		
		//이름이나 전화번호가 빈 값으로 전달되면
		if(name == null || name.equals("") || tel == null || tel.equals("")) {
			//실패 메시지 표출
			status = "fail";
			message = "이름과 전화번호는 필수 입력 사항입니다.";
		} else {
			Contact c = new Contact(0, name, tel, address);
			SampleDAO.addContact(c);
			message = "일련번호 " + c.getNo() + "번 데이터가 추가되었습니다.";
		}
	//파라미터 전송 방식이 POST가 아닐 때 
	} else {
		//실패 메시지 표출
		status = "fail";
		message = "POST 메서드만 지원합니다.";
	}
%>
<!-- status, message 표출 -->
{
	"status" : "<%=status %>",
	"message" : "<%=message %>"
}