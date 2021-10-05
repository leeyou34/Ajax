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
 * Servlet implementation class ContactUpdateServlet
 */
@WebServlet("/delete.do")
public class ContactDeleteServlet extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		
		request.setCharacterEncoding("UTF-8");		
		String status = "ok";
		String message = "";
		
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
		
		//JAVA 객체 -> json 객체로 변환
		Result result = new Result(status, message);
		String json = Converter.convertToJson(result);
		
		//화면에 표출
		PrintWriter writer  = response.getWriter();
		writer.println(json);
	}

}
