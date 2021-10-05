<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.ajaxstudy.contact.domain.*" %>
<%@ page import="com.ajaxstudy.contact.util.*" %>
<%
// 이 jsp파일은 서블릿의 역할을 수행하는 것이다
// view의 역할은 아니다
	String strPageno = request.getParameter("pageno");
	String strPagesize = request.getParameter("pagesize");
	int pageno = 0;		// 0이면 전체 데이터 조회, 1이상이면 해당 페이지 조회
	int pagesize = 3;	// 한 페이지의 크기
	// 1) 브라우저가 보내온 페이지 번호와 페이지 크기를 int로 변환
	try{
		pageno = Integer.parseInt(strPageno);
	}catch(Exception e){
		pageno = 0;
		System.out.println("pageno 가 없음");
	}
	try{
		pagesize = Integer.parseInt(strPagesize);
	}catch(Exception e){
		pagesize = 3;
		System.out.println("pagesize 가 없음");
	}
	
	ContactList contactList = null;
	if(pageno==0)	// 전체 주소록 데이터
		contactList = SampleDAO.getContacts();
	else			// 특정 페이지 데이터
		contactList = SampleDAO.getContacts(pageno, pagesize);
	// java 객체 -> json문자열로 변환
	String json = Converter.convertToJson(contactList);
%>
<%=json%>
