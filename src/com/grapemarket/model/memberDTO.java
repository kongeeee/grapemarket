package com.grapemarket.model;

public class memberDTO {
	
	int member_index;	//회원 고유번호
	String nickname;	//회원 닉네임
	String pswd;		//회원 비밀번호
	String phone;		//회원 휴대폰번호
	String userID;		//회원 아이디
	int deactive;		//회원 탈퇴여부
	int isadmin;		//관리자 여부
	
	public int getMember_index() {
		return member_index;
	}
	public void setMember_index(int member_index) {
		this.member_index = member_index;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPswd() {
		return pswd;
	}
	public void setPswd(String pswd) {
		this.pswd = pswd;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public int getDeactive() {
		return deactive;
	}
	public void setDeactive(int deactive) {
		this.deactive = deactive;
	}
	public int getIsadmin() {
		return isadmin;
	}
	public void setIsadmin(int isadmin) {
		this.isadmin = isadmin;
	}
}