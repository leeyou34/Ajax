package com.ajaxstudy.contact.domain;

import java.util.List;

public class ContactList {
	private int pageNo;
	private int pageSize;
	private int totalCount;
	private List<Contact> contacts;
	
	public ContactList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ContactList(int pageNo, int pageSize, int totalCount, List<Contact> contacts) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.contacts = contacts;
	}
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<Contact> getContacts() {
		return contacts;
	}
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	@Override
	public String toString() {
		return "ContactList [pageNo=" + pageNo + ", pageSize=" + pageSize + ", totalCount=" + totalCount + ", contacts="
				+ contacts + "]";
	}	
}
