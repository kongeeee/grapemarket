package com.grapemarket.model;

public class commentDTO {
	
	int comment_index;		// 댓글 고유 번호
	int member_index;	    // 회원 고유 번호
	int post_index;			// 게시글 고유 번호
	String date_created;	// 댓글 작성일
	String content;			// 댓글 내용
	String nickname;		// 별명
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getComment_index() {
		return comment_index;
	}
	public void setComment_index(int comment_index) {
		this.comment_index = comment_index;
	}
	public int getMember_index() {
		return member_index;
	}
	public void setMember_index(int member_index) {
		this.member_index = member_index;
	}
	public int getPost_index() {
		return post_index;
	}
	public void setPost_index(int post_index) {
		this.post_index = post_index;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_careated) {
		this.date_created = date_careated;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
