package com.grapemarket.model;

public class postDTO {
	
	String title;			//게시글 제목
	String content;			//게시글 내용
	String date_created;	//게시글 작성일
	String category;		//게시글 종류
	String img;				//이미지
	String price;			//가격
	String nickname;		//닉네임
	String phone;			//휴대폰 번호
	int views;				//조회수
	int post_index;			//게시글 고유번호
	int isphone;			//폰번호 공개여부
	int postcount;			//카테고리 별 게시물 갯수
	int member_index;		//멤버 인덱스
	
	public int getMember_index() {
		return member_index;
	}
	public void setMember_index(int member_index) {
		this.member_index = member_index;
	}
	public int getPostcount() {
		return postcount;
	}
	public void setPostcount(int postcount) {
		this.postcount = postcount;
	}
	public int getIsphone() {
		return isphone;
	}
	public void setIsphone(int isphone) {
		this.isphone = isphone;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getPost_index() {
		return post_index;
	}
	public void setPost_index(int post_index) {
		this.post_index = post_index;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	
}