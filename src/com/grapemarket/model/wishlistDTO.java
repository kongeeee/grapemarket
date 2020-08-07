package com.grapemarket.model;

public class wishlistDTO {
	
	int post_index;		//게시글 고유번호
	int member_index;	//회원 고유번호
	String title;
	String img;
	String nickname;
	String date_created;
	String category;
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	
	public int getPost_index() {
		return post_index;
	}
	public void setPost_index(int post_index) {
		this.post_index = post_index;
	}
	public int getMember_index() {
		return member_index;
	}
	public void setMember_index(int member_index) {
		this.member_index = member_index;
	}

}