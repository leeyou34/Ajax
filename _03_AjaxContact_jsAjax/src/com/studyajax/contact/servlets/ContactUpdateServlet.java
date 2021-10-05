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
@WebServlet("/update.do")
public class ContactUpdateServlet extends HttpServlet {
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
		//JAVA 객체 -> json 객체로 변환
		Result result = new Result(status, message);
		String json = Converter.convertToJson(result);
		
		//화면에 표출
		PrintWriter writer  = response.getWriter();
		writer.println(json);
	}

}
