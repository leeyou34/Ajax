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
		//Json형태의 데이터 리스트를 InputStream으로 읽어서 JAVA객체로 번환
		ContactList contactList = 
				Converter.convertFromJsonStream(request.getInputStream(), ContactList.class);
		if(contactList == null) {
			status = "fail";
			message = "요청 정보 json 데이터 객체 변환 실패";
		} else {
			int count = SampleDAO.updateBatch(contactList);
			if(count > 0) {
				status = "ok";
				message = "총 " + count + "건의 데이터가 수정되었습니다.";
			} else {
				status = "fail";
				message = "수정할 데이터가 존재하지 않습니다.";
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