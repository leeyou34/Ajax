package com.studyajax.contact.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ajaxstudy.contact.domain.ContactList;
import com.ajaxstudy.contact.util.Converter;
import com.ajaxstudy.contact.util.SampleDAO;
/**
 * Servlet implementation class ContactUpdateServlet
 */
//list.do : servlet 
@WebServlet("/list.do")
public class ContactListServlet extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
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
		
		PrintWriter writer = response.getWriter();
		writer.println(json);
	}

}
