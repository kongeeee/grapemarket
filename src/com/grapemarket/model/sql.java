package com.grapemarket.model;

public class sql {
	
	/*select문*/
	//글목록
	public String p_list() {
		String post_list = "SELECT "
			    +	"post.post_index,post.category,post.title,member.nickname,post.date_created,post.views "
				+ "FROM "
				+ 	"post INNER JOIN member ON post.member_index = member.member_index "
				+ "WHERE "
				+ 	"post.category = ? "
				+ "ORDER BY "
				+ 	"post_index DESC";
		return post_list;
	}
	
	//제목으로 검색
	public String title_search() {
		String search = "SELECT "
				+ 		"post.post_index,post.title,post.date_created,post.views,member.nickname,post.category "
				+ "FROM "
				+ 		"post INNER JOIN member ON post.member_index= member.member_index "
				+ "WHERE "
				+ 		"(title like ? and category = ?) "
				+ "ORDER BY "
				+ 		"post_index DESC";
		return search;
	}
	
	//제목으로 검색(자른거)
	public static String title_search = "SELECT "
			+ 		"post.post_index,post.title,post.date_created,post.views,member.nickname,post.category "
			+ "FROM "
			+ 		"post INNER JOIN member ON post.member_index= member.member_index "
			+ "WHERE "
			+ 		"(title like ? and category = ?) "
			+ "ORDER BY "
			+ 		"post_index DESC "
			+ "limit ? offset ?";
	
	//게시글 전체 갯수
	public static String post_count = "SELECT "
					+ "count(*) AS count "
			+ "FROM "
					+ "post "
			+ "WHERE "
					+ "category = ?";
	
	// 게시물 검색결과 갯수
	public static String search_countX = "SELECT COUNT(*) as count FROM post WHERE category = ? and title like ?";
	
	//게시글 자르기
	public static String post_cut = "SELECT "
					+ "title, category "
			+ "FROM "
					+ "post "
			+ "WHERE "
					+ "category = ? "
			+ "LIMIT ? OFFSET ?";
	
	//id 검색
	public static String id_search = "SELECT "
					+ "* "
			+ "FROM "
					+ "member "
			+ "WHERE "
					+ "userid = ?";
	
	//닉네임 검색
	public static String nickname_search = "SELECT "
					+ "nickname "
			+ "FROM "
					+ "member "
			+ "WHERE "
					+ "nickname = ?";
	
	//글보기
	public String getpost() {
		String post = "SELECT "
				   + 	"member.member_index,post.post_index,post.title,post.content,post.date_created,post.category,post.img,post.price,member.nickname,member.phone,post.isphone "
				   + "FROM "
				   + 	"post INNER JOIN member ON post.member_index = member.member_index "
				   + "WHERE "
				   + 	"post_index = ? ";
		return post;
	}
	
	//메인 미리보기
	public String p_main() {
		String post_main = "SELECT "
			    + 		"title, img, post_index, category "
				+ "FROM "
				+ 		"post "
				+ "ORDER BY "
				+ 		"post_index DESC "
				+ "limit 3";
		return post_main;
	}
	
	//조회수 순
	public String p_views() {
		String post_views = "SELECT "
				+ 		"title, img, post_index, category "
				+ "FROM "
				+ 		"post "
				+ "WHERE "
				+ 		"img is not null and img != '' and category in('deal_board','free_board') "
				+ "ORDER BY "
				+ 		"views DESC "
				+ "limit 3";
		return post_views;
	}
	
	//채팅 왼쪽
	public String me_select() {
		String messenger_select = "select * from " 
						+ "(select "
						+ "(SELECT nickname FROM member WHERE member_index = messenger.sender) as sender_nick, "
						+ "content, "
						+ "to_char(date_time,'YY-MM-DD HH24:MI') as date_time, "
						+ "sender, "
						+ "receiver "
						+ "from messenger inner join member on messenger.sender = member.member_index " 
						+ "where (messenger.sender,date_time) " 
						+ "in (select messenger.sender, max(date_time) as date_time from messenger group by messenger.sender, receiver) " 
						+ "order by date_time desc)t "
						+ "WHERE receiver = ?";
		return messenger_select;
	}
	
	//채팅 왼쪽(자기꺼 포함)
	public static String messenger = "select * "
			+ "from "
			+ "(select messenger_index,(SELECT nickname FROM member WHERE member_index = messenger.sender) as sender_nick, content, to_char(date_time,'YY-MM-DD HH24:MI') as date_time, sender, receiver "
			+ "from messenger inner join member on messenger.sender = member.member_index "
			+ "where (messenger.sender,date_time) in (select messenger.sender, max(date_time) as date_time from messenger "
			+ "group by messenger.sender, receiver) "
			+ "order by date_time desc)t "
			+ "WHERE receiver = ? or sender = ? ";
	
	//채팅 오른쪽
	public String me_content(int sender, int receiver) {
		String messenger_content = "SELECT "
				+ 		"(SELECT nickname FROM member WHERE member_index = messenger.sender) as send_name, "
				+		"(SELECT nickname FROM member WHERE member_index = messenger.receiver) as recv_name, "
				+		"content, "
				+		"to_char(date_time,'YY-MM-DD HH24:MI') as date_time "
				+"FROM "
				+ 		"messenger "
				+"WHERE "
				+ 		"sender = "+ sender +" AND receiver = " + receiver;
		return messenger_content;
	}
	
	//채팅 오른쪽(자른거)
	public static String messeger_cut = 
					"SELECT "
			+ 		"(SELECT nickname FROM member WHERE member_index = sender) as send_name, "
			+ 		"content, "
			+ 		"to_char(date_time,'YY-MM-DD HH24:MI') as date_time, "
			+ 		"messenger.sender, "
			+ 		"receiver "
			+ "FROM "
			+ 		"messenger "
			+ "WHERE "
			+ 		"(sender = ? AND receiver = ?) " 
			+ 		"OR (receiver = ? AND sender = ?) " 
			+ "limit ? offset ?";
	
	//댓글
	public String c_select() {
		String comment_select = "SELECT "
				 + 		"* "
				 + "FROM "
				 + 		"comment INNER JOIN member ON comment.member_index = member.member_index "
				 + "WHERE "
				 + 		"post_index = ?";
		return comment_select;
	}
	
	//위시리스트
	public String w_select() {
		String wishlist_select = "SELECT "
				  + 	"post.category,post.post_index, post.title, post.img, member.nickname, post.date_created "
				  + "FROM "
				  + 	"wishlist "
				  + "JOIN post ON wishlist.post_index = post.post_index "
				  + "JOIN member ON wishlist.member_index = member.member_index "
				  + "WHERE "
				  + 	"wishlist.member_index = ? "
				  + "limit ? offset ?";
		return wishlist_select;
	}
	
	//위시리스트 조회
	public static String wishlist = "SELECT count(*) FROM wishlist WHERE member_index = ? AND post_index = ?";
	
	//마이페이지
	public String my_select() {
		String mypage_select = "SELECT "
				+ 		"userID,nickname,pswd "
				+ "FROM "
				+ 		"member "
				+ "WHERE "
				+ 		"member_index = ?";
		return mypage_select;
	}
	
	// 내가 쓴 글
	public String me_post(int member_index) {
		String member_post = "SELECT "
				  + 	"(select nickname from member where sender = "+ member_index + "), "
				  +		"post_index, title, date_created, views "
				  + "FROM "
				  + 	"post "
				  + "WHERE "
				  + 	"member_index = " + member_index;
		return member_post;
	}
	
	//내가 쓴 댓글
	public String me_comment(int member_index) {
		String member_comment = "SELECT "
				 + 		"(select nickname from member where sender ="+ member_index +"), "
				 + 		"content, date_created "
				 + "FROM "
				 + 		"comment "
				 + "WHERE "
				 + 		"member_index =" + member_index;
		return member_comment;
	}
	
	//로그인(아이디 비밀번호)
	public String login() {
		String login = "SELECT "
				+ 		"* "
				+ "FROM "
				+ 		"member "
				+ "WHERE "
				+		"userid = ? and pswd = ?";
		
		return login;
	}

	/*insert 문*/
	//회원가입
	public String my_insert() {
		String signup_insert = "INSERT INTO "
				+ 		"member (nickname, pswd, phone, userID) "
				+ "VALUES "
				+ 		"(?, ?, ?, ?)";
		return signup_insert;
	}
	
	//게시글
	public String p_insert() {
		String post_insert   = "INSERT INTO "
				+ 		"post (member_index, title, content, date_created, category, img, views, price, isphone) "
				+ "VALUES "
				+ 		"(?, ?, ?, CAST('now' as DATE), ?, ?, 0, ?, ?)";
		return post_insert;
	}
	
	public static String post_insert = "INSERT INTO "
			+ 		"post (member_index, title, content, date_created, category, img, views, price) "
			+ "VALUES "
			+ 		"(?, ?, ?, CAST('now' as DATE), ?, ?, 0, ?)";

	//채팅
	public String me_insert() {
		String messenger_insert = "INSERT INTO "
				   + 		"messenger (sender, receiver, content, date_time) "
				   + "VALUES "
				   + 		"(? , ?, ?,CAST('now' as timestamp))";
		return messenger_insert;
	}
	
	//댓글
	public String c_insert() {
		String comment_insert = "INSERT INTO "
				 + 		"comment (member_index, post_index, content, date_created) "
				 + "VALUES "
				 + 		"(?, ?, ?, CAST('now' as DATE))";
		return comment_insert;
	}
	
	//위시리스트
	public String w_insert() {
		String wishlist_insert = "INSERT INTO "
				+ 		"wishlist (post_index, member_index) "
                + "VALUES "
                + 		"(?, ?)";
		return wishlist_insert;
	}
	
	/*update문*/
	//게시물
	public String p_update() {
		String post_update = "UPDATE "
				  + 		"post "
				  + "SET "
				  + 		"title = ?,content = ?, category = ?, img = ?, price = ?, isphone = ? "
				  + "WHERE "
				  + 		"post_index = ?";
		return post_update;
	}
	
	//댓글
	public String c_update() {
		String comment_update = "UPDATE "
				 + 		"comment "
				 + "SET "
				 + 		"content= ?"
				 + "WHERE "
				 + 		"comment_index = ?";
		return comment_update;
	}
	
	//마이페이지 (둘다 변경)
	public String me_update() {
		String mypage_update = "UPDATE "
		         + 		"member "
		         + "SET "
		         + 		"pswd = ?, nickname = ?"
		         + "WHERE "
		         + 		"member_index = ?";
		return mypage_update;
	}
	
	//마이페이지(닉네임 변경)
	public String menick_update() {
		String mypage_update = "UPDATE "
		         + 		"member "
		         + "SET "
		         + 		" nickname = ?"
		         + "WHERE "
		         + 		"member_index = ?";
		return mypage_update;
	}
	
	//마이페이지(비밀번호 변경)
	public String mepswd_update() {
		String mypage_update = "UPDATE "
		         + 		"member "
		         + "SET "
		         + 		"pswd = ?"
		         + "WHERE "
		         + 		"member_index = ?";
		return mypage_update;
	}
	
	//조회수
	public String views_up() {
		String views_up = "UPDATE "
				   + 		"post "
				   + "SET "
				   + 		"views = views+1 "
				   + "WHERE "
				   + 		"post_index = ?";
		return views_up;
	}
	
	/*delete*/
	//게시물
	public String p_delete() {
		String post_delete = "DELETE FROM "
				  + 	"post "
				  + "WHERE "
				  + 	"post_index = ?" ; 
		return post_delete;
	}
	
	//댓글
	public String c_delete() {
		String comment_delete = "DELETE FROM "
				+ 		"comment "
				+ "WHERE "
				+ 		"comment_index = ?";
		return comment_delete;
	}
	
	//위시리스트
	public static String w_delete = "DELETE FROM wishlist WHERE post_index = ?";

}