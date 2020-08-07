package com.grapemarket.model;

public class messengerDTO {
	
	int messenger_index;	//메신저 고유번호
	int member_index;		//송신자
	int member_index2;		//수신자
	String content;			//메신저 내용
	String date_time;		//메신저 작성일
	String sender;			//보낸 사람 닉네임
	String receiver;		//받은 사람 닉네임
	
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public int getMessenger_index() {
		return messenger_index;
	}
	public void setMessenger_index(int messenger_index) {
		this.messenger_index = messenger_index;
	}
	public int getMember_index() {
		return member_index;
	}
	public void setMember_index(int member_index) {
		this.member_index = member_index;
	}
	public int getMember_index2() {
		return member_index2;
	}
	public void setMember_index2(int member_index2) {
		this.member_index2 = member_index2;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate_time() {
		return date_time;
	}
	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}
}