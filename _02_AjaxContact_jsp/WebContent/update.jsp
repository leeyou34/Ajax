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
		long no = 0;
		
		try{
			//POST 방식으로 전송된 파라미터를 long타입으로 형변환
			no = Long.parseLong(request.getParameter("no"));
		} catch(Exception e) {
			System.out.println("번호를 숫자로 변경할 수 없습니다.");
			return;
		}
		
		//request 객체로부터 파라미터 값 받음
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String address = request.getParameter("address");
		
		//이름과 전화번호가 빈값일 때
		if(name == null || name.equals("") || tel == null || tel.equals("")) {
			//실패 메시지 표출
			status = "fail";
			message ="이름과 전화번호는 필수 입력 값입니다.";
		//이름과 전화번호가 정상적으로 들어왔을때
		} else {
			//연락처 정보 객체 생성
			Contact c = new Contact(no, name, tel, address);
			//SampleDAO에 수정 메소드 호출
			int count = SampleDAO.updateContact(c);
			if(count == 1) {
				status = "ok";
				message = "일련번호 " + c.getNo() + "번 데이터가 수정되었습니다.";
			} else {
				status = "fail";
				message = "수정하려는 데이터가 존재하지 않습니다.";
			}
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