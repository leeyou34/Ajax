package com.studyajax.contact.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ajaxstudy.contact.domain.Contact;
import com.ajaxstudy.contact.util.Converter;
import com.ajaxstudy.contact.util.SampleDAO;
import com.ajaxstudy.contact.domain.Result;

/**
 * Servlet implementation class ContactAddServlet
 */
/*WebServlet : 브라우저에서 호출할 주소*/
@WebServlet("/add.do")
public class ContactAddServlet extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	//doGet : Get방식의 통신 이뤄졌을 때 동작
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Get 방식으로 들어왔을때 status와 message설정
		Result result = new Result("fail", "POST메소드만 지원합니다.");
		//result 객체를 json 문자열로 변환
		String json = Converter.convertToJson(result);
		//변환된 json 문자열 화면에 표출
		PrintWriter writer = response.getWriter();
		writer.println(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//doPost : Post방식의 통신 이뤄졌을 때 동작
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Content-Type을 html로 지정
		response.setContentType("text/html;charset=UTF-8");
		
		request.setCharacterEncoding("UTF-8");		
		String status = "ok";
		String message = "";
		
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String address = request.getParameter("address");
		
		if(name == null || name.equals("") || tel == null || tel.equals("")) {
			status = "fail";
			message = "이름과 전화번호는 필수 입력 사항입니다.";
		} else {
			Contact c = new Contact(0, name, tel, address);
			SampleDAO.addContact(c);
			message = "일련번호 " + c.getNo() +"번 데이터가 추가되었습니다.";
		}
		
		//JAVA 객체 -> json 객체로 변환
		Result result = new Result(status, message);
		String json = Converter.convertToJson(result);
		
		//화면에 표출
		PrintWriter writer  = response.getWriter();
		writer.println(json);
	}

}
