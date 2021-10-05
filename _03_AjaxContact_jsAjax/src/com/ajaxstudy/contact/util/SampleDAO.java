package com.ajaxstudy.contact.util;

import java.util.ArrayList;
import java.util.List;

import com.ajaxstudy.contact.domain.Contact;
import com.ajaxstudy.contact.domain.ContactList;

/*
연락처 리스트를 저장하고 있는 클래스
DB에 연결하지 않고 컬렉션 객체에 주소데이터 보관
연락처 추가, 수정, 삭제, 조회 기능 구현
*/
public class SampleDAO {
	//연락처 목록에서 사용할 이름 목록 변수 선언
	private static String nameList = "고수,공유,권상우,권율,김범,김수현," +
									 "김우빈,현빈,남주혁,마동석,박보검,박서준," +
									 "박해일,박해진,박형식,서강준,서인국,소지섭," +
									 "송강호,신구,안재욱,양택조,옥택연,원기준," +
									 "원빈,유오성,유지태,윤계상,윤상현,이계인," +
									 "이광기,이덕화,이동욱,이병헌,이원종,이준기," +
									 "인교진,임창정,임채무,전무송,전국환,전노민," +
									 "정우성,정보석,정웅인,조인성,조정석,조진웅," +
									 "주지훈,지성,지창욱,지진희,지현우,주원," +
									 "차인표,차승원,최다니엘,최민수,최민식,최불암," +
									 "하석진,현빈,홍광호,황정민";
	//연락처 목록을 담을 변수 선언
	private static List<Contact> contacts;
	//다음 일련번호를 담을 변수 선언
	private static long nextNo = 0;
	
	//데이터 초기화(연락처 정보 목록 생성)
	static {
		//이름 목록 배열 생성
		String[] names = nameList.split(",");
		//연락처 목록 담을 변수 초기화
		contacts = new ArrayList<Contact>();
		for(int i = 0; i < names.length; i++) {
			nextNo++; //일련번호 1부터 담음
			String tel = "010-1111-22" + (i+10);
			String address = "서울특별시";
			//연락처 정보 생성
			Contact c = new Contact(nextNo, names[i], tel, address);
			//위에서 생성한 연락처 정보를 연락처 목록에 하나씩 담음
			//contacts.get(i)로 하나씩 꺼낼 수 있고
			//contacts.get(i)의 내용은 {1, 고수, 010-1111-2210, 서울특별시}
			contacts.add(i, c);
		}
	}
	
	//전체 연락처 목록 조회
	public static ContactList getContacts() {
		//static 블록에서 생성한 연락처 목록으로 된 ContactList 객체 생성
		//(현재 페이지 0, 페이지 사이즈 0, 목록 사이즈 contacts.size(), List<Contact> contacts)
		ContactList cList = new ContactList(0, 0, contacts.size(), contacts);
		return cList;
	}
	
	//특정 페이지와 페이지에 포함될 연락처 개수 요청(일부 연락처 정보만 전송)
	public static ContactList getContacts(int pageno, int pagesize) {
		//전송할 연락처 목록의 시작위치 지정
		int startIndex = (pageno-1) * pagesize;
		//전송할 연락처 목록의 끝위치 지정
		int endIndex = startIndex + pagesize;
		
		List<Contact> temps = null;
		
		//연락처 목록의 범위를 벗어난 요청이 오면
		if(startIndex > contacts.size() -1 || startIndex < 0 || pagesize <1) {
			//빈 객체를 리턴
			temps = new ArrayList<Contact>();
		}
		//유효한 범위의 요청이 오면
		else {
			//전송 종료 위치가 연락처 목록의 크기를 넘어서면
			//끝위치를 연락처 목록의 마지막 인덱스로 지정한다.
			if(endIndex > contacts.size()) {
				endIndex = contacts.size();
			}
			//시작위치와 끝위치에 해당하는 연락처 목록 부분을 추출
			temps = contacts.subList(startIndex, endIndex);
		}
		//위에서 추출한 연락처 목록의 내용으로 된 연락처 목록 객체 생성
		//(현재 페이지 pageno, 페이지 사이즈 pagesize, 목록 사이즈 contacts.size(), List<Contact> temps)
		ContactList cList = new ContactList(pageno, pagesize, contacts.size(), temps);
		
		return cList;
	}
	
	//연락처 목록에 연락처 추가
	public static void addContact(Contact c) {
		nextNo++;
		c.setNo(nextNo);
		contacts.add(0, c);
	}
	
	//해당 일련번호의 연락처 삭제
	public static void deleteContact(long no) {
		//지워진 연락처의 개수
		int count = 0;
		for(int i = 0; i < contacts.size(); i++) {
			Contact c = contacts.get(i);
			//연락처의 일련번호가 전달받은 일련번호와 같을 때 목록에서 제거
			if(c.getNo() == no) {
				contacts.remove(i);
				count++;
				break;
			}
		}
	}
	
	//연락처 수정
	//수정될 연락처 정보 매개변수로 받음
	public static int updateContact(Contact c) {
		long no = c.getNo();
		//수정된 연락처의 개수
		int count = 0;
		for(int i = 0; i < contacts.size(); i++) {
			//기존에 연락처 목록에서 연락처 하나씩 꺼내서 비교
			Contact con = contacts.get(i);
			//매개변수로 받은 연락처의 일련번호와 기존 연락처 목록에서 꺼내온 연락처의 일련번호가 같을때
			if(con.getNo() == no) {
				//기존 연락처 정보를 매개변수로 받은 연락처 정보로 수정한다.
				contacts.set(i, c);
				count++;
				break;
			}
		}
		//하나씩 수정되기 때문에 1로 리턴
		return count;
	}
	
	//여러개의 연락처 한 번에 수정
	//수정할 연락처 목록 매개변수로 받음
	public static int updateBatch(ContactList contactList) {
		//수정될 연락처의 개수
		int count = 0;
		//매개변수에서 pageNo, pageSize, totalCount는 제외한 연락처 목록만 뽑음
		List<Contact> list = contactList.getContacts();
		if(list.size() > 0) {
			//연락처 목록에서 연락처 정보 하나씩 꺼냄
			for(Contact c : list) {
				//수정 메소드 호출
				SampleDAO.updateContact(c);
				count++;
			}
		}
		return count;
	}
}
