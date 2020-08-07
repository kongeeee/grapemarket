package com.grapemarket.model;

import java.util.ArrayList;

public interface reference {
	
	/*DB 관련*/

	//DB 연결
	public void DBconnect();
	
	//DB 끊기
	public void DBclose();
	
	/*SELECT*/
	
	//메인 미리보기
	public ArrayList<postDTO> post_main();
	
	//조회수 순
	public ArrayList<postDTO> post_views();
	
	//게시물 전체 갯수
	public int post_count(String category);
	
	//글목록(카테고리 별)
	public ArrayList<postDTO> post_list(String category);
	
	//게시글 자르기(카테고리별)
	public ArrayList<postDTO> post_list(String category, int limit, int offset);
	
	//글보기
	public postDTO getpost(int post_index);
	
	//댓글 조회
	public ArrayList<commentDTO> comment(int post_index);
	
	//위시리스트 조회
	public ArrayList<wishlistDTO> wishlist(int sender, int limit, int offset);
	
	//위시리스트 등록 유무
	public int wishlist(int member_index, int post_index);
	
	//아이디만 조회
	public memberDTO id_search(String userID);
	
	//닉네임만 조회
	public memberDTO nickname_search(String nickname);
	
	//마이페이지 조회
	public ArrayList<memberDTO> member(int sender);
	
	//내가 쓴 글
	public ArrayList<postDTO> mem_post(int sender);
	
	//내가 쓴 댓글
	public ArrayList<commentDTO> mem_comment(int sender);
	
	//제목으로 검색
	public ArrayList<postDTO> title_search(String search_title, String category);
	
	//제목으로 검색(자른거)
	public ArrayList<postDTO> title_search(String search_title, String category,int limit, int offset);
	
	//로그인(아이디, 비밀번호)
	public memberDTO login(String id, String pswd);
	
	//채팅 왼쪽(사용자의 모든 채팅 보기)
	public ArrayList<messengerDTO> messenger(int my_index);
	
	//채팅 오른쪽(자른거)
	public ArrayList<messengerDTO> messeger_cut(int sender, int receiver, int limit, int offset);

	/*INSERT*/
	
	//회원가입
	public int mem_insert(memberDTO mem);
	
	//글쓰기
	public int post_insert(memberDTO mem, postDTO post);
	
	//글쓰기(휴대폰 번호 공개여부 추가)
	public int postinsert(memberDTO mem, postDTO post);
	
	//채팅 입력
	public int mes_insert(messengerDTO mes);
	
	//댓글 작성
	public int comment_insert(int member_index, int post_index, String content);
	
	//위시리스트
	public int wish_insert(memberDTO mem, postDTO post);
	
	/*UPDATE*/
	
	//게시물 수정
	public int post_update(postDTO post);
	
	//조회수 증가
	public int views_up(int post_index);
	
	//댓글 수정
	public int comment_update(commentDTO com);
	
	//마이페이지 (둘다 변경)
	public int mem_update(memberDTO mem);
	
	//마이페이지(닉네임 변경)
	public int memnick_update(memberDTO mem);
	
	//마이페이지(비밀변호 변경)
	public int mempswd_update(memberDTO mem);
	
	/*DELETE*/
	
	//게시물 삭제
	public int post_delete(postDTO post);
	
	//댓글 삭제
	public int comment_delete(int comment_index);

}
