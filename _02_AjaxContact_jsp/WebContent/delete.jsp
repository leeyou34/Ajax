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
		boolean isDel = false;
		long no = 0;
		
		try{
			//POST 방식으로 전송된 파라미터를 long타입으로 형변환
			no = Long.parseLong(request.getParameter("no"));
			isDel = true;
		} catch(Exception e) {
			isDel = false;
			status = "fail";
			message = "번호를 숫자로 변경할 수 없습니다.";
		}
		
		if(isDel) {
			SampleDAO.deleteContact(no);
			status = "ok";
			message = "일련번호 " + no + "번 데이터가 삭제되었습니다.";
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