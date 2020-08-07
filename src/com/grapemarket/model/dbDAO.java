package com.grapemarket.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class dbDAO implements reference{
	
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement pstm = null;
	
	ArrayList<postDTO> plist;
	ArrayList<commentDTO> clist;
	ArrayList<memberDTO> memlist;
	ArrayList<wishlistDTO> wlist;
	ArrayList<messengerDTO> meslist;
	
	int result = 0; //result가 0이면 입력 실패, result가 1이면 입력 성공
	
	//postgresql 정보
	String url="jdbc:postgresql://192.168.0.208/grapemarket";
	String user = "grappy";
	String password = "grappy";
	
	//DB연결
	public void DBconnect() {
		try {
			Class.forName("org.postgresql.Driver"); // JDBC 드라이버 로딩
			//System.out.println("jdbc Driver connect success");
			
			con = DriverManager.getConnection(url,user,password); //데이터베이스 연결
			st = con.createStatement(); //statement 객체 생성
			rs = st.executeQuery("SELECT VERSION()");
			
			if(rs.next()) {
			//System.out.println(rs.getString(1));
		}}catch(ClassNotFoundException e) {
			System.out.println("where is JDBC Driver?");
			e.printStackTrace();
		}catch(SQLException ex) {
			System.out.println("Connection failed");
			ex.printStackTrace();
			return;
		}
	}
	
	/*select*/
	//글목록(카테고리 별)
	public ArrayList<postDTO> post_list(String category) {
				try {
					plist = new ArrayList<>();
					sql SQL = new sql();
					String sql = SQL.p_list();
					
					pstm = con.prepareStatement(sql);
					pstm.setString(1, category);
				    rs = pstm.executeQuery();
					
					while(rs.next()) {
						postDTO post = new postDTO();
						post.setPost_index(rs.getInt("post_index"));
						post.setTitle(rs.getString("title"));
						post.setDate_created(rs.getString("date_created"));
						post.setCategory(rs.getString("category"));
						post.setViews(rs.getInt("views"));
						post.setNickname(rs.getString("nickname"));
						
						plist.add(post);
					}
					
					for(int i = 0; i<plist.size(); i++) {
						postDTO postDTO = plist.get(i);
					}
					
					rs.close();
					pstm.close();
					
				}catch(SQLException e) {
					System.out.println("SQL Error : " + e.getMessage());
					System.out.println("post_list 오류");
				}catch(Exception e) {
					System.out.println("데이터 가져오기를 실패했습니다.");
					e.printStackTrace();
				}
				return plist;
	}
	
	//게시물 전체갯수
	public int post_count(String category) {
		postDTO post = null;
		try {
			String SQL = sql.post_count;
			
			pstm = con.prepareStatement(SQL);
			pstm.setString(1,category);
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				post = new postDTO();
				post.setPostcount(rs.getInt("count"));
			}

		}catch(SQLException e) {
			System.out.println("SQL 오류 :"+e.getMessage());
			System.out.println("post_count 오류");
		}catch(Exception e) {
			System.out.println("데이터 가져오기를 실패했습니다.");
		}
		return post.getPostcount();
	}
	
	public int post_countX(String category, String search) {
		int count = 0;
		try { 
			pstm = con.prepareStatement(sql.search_countX);
			pstm.setString(1, category);
			pstm.setString(2, "%"+search+"%");
			rs = pstm.executeQuery();
			if (rs.next()) {
				count = rs.getInt("count");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("post_countX Error");
		}
		return count;
	}
	
	//게시글 자르기
	public ArrayList<postDTO> post_list(String category, int limit, int offset) {
		try {
			plist = new ArrayList<>();
			String SQL = sql.post_cut;
			
			pstm = con.prepareStatement(SQL);
			pstm.setString(1, category);
			pstm.setInt(2, limit);
			pstm.setInt(3, offset);
		    rs = pstm.executeQuery();
			
			while(rs.next()) {
				postDTO post = new postDTO();
				post.setPost_index(rs.getInt("post_index"));
				post.setTitle(rs.getString("title"));
				post.setDate_created(rs.getString("date_created"));
				post.setCategory(rs.getString("category"));
				post.setViews(rs.getInt("views"));
				post.setNickname(rs.getString("nickname"));
				
				plist.add(post);
			}

			rs.close();
			pstm.close();
			
		}catch(SQLException e) {
			System.out.println("SQL Error : " + e.getMessage());
			System.out.println("post_list 오류");
		}catch(Exception e) {
			System.out.println("데이터 가져오기를 실패했습니다.");
			e.printStackTrace();
		}
		return plist;
	}
	
	//아이디만 조회
	public memberDTO id_search(String userID) {
		memberDTO mem = null;
		try {
			String SQL = sql.id_search;
			
			pstm = con.prepareStatement(SQL);
			pstm.setString(1, userID);
		    rs = pstm.executeQuery();
		    
			if(rs.next()) {
				mem = new memberDTO();
				mem.setUserID(rs.getString("UserID"));
				mem.setMember_index(rs.getInt("member_index"));
				mem.setNickname(rs.getString("nickname"));
				mem.setPhone(rs.getString("phone"));
				mem.setIsadmin(rs.getInt("isadmin"));
			}
			rs.close();
			pstm.close();
			
		}catch(SQLException e) {
			System.out.println("SQL Error : " + e.getMessage());
			System.out.println("id_search 오류");
		}catch(Exception e) {
			System.out.println("데이터 가져오기를 실패했습니다.");
			e.printStackTrace();
		}
		return mem;
	}
	
	//닉네임만 조회
	public memberDTO nickname_search(String nickname) {
		memberDTO mem = null;
		try {
			String SQL = sql.nickname_search;
			
			pstm = con.prepareStatement(SQL);
			pstm.setString(1, nickname);
		    rs = pstm.executeQuery();
		    
			if(rs.next()) {
				mem = new memberDTO();
				mem.setNickname(rs.getString("nickname"));
			}
			rs.close();
			pstm.close();
			
		}catch(SQLException e) {
			System.out.println("SQL Error : " + e.getMessage());
			System.out.println("nickname_search 오류");
		}catch(Exception e) {
			System.out.println("데이터 가져오기를 실패했습니다.");
			e.printStackTrace();
		}
		return mem;
	}
	
	
	//글보기
	public postDTO getpost(int post_index) {
		postDTO post = null;
		try {
			sql SQL = new sql();
			String sql = SQL.getpost();
			
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, post_index);
		    rs = pstm.executeQuery();
		    
			if(rs.next()) {
				post = new postDTO();
				post.setPost_index(rs.getInt("post_index"));
				post.setTitle(rs.getString("title"));
				post.setContent(rs.getString("content"));
				post.setDate_created(rs.getString("category"));
				post.setImg(rs.getString("img"));
				post.setPrice(rs.getString("price"));
				post.setNickname(rs.getString("nickname"));
				post.setPhone(rs.getString("phone"));
				post.setMember_index(rs.getInt("member_index"));
				post.setIsphone(rs.getInt("isphone"));
			}
			rs.close();
			pstm.close();
		}catch(SQLException e) {
			System.out.println("SQL Error : " + e.getMessage());
			System.out.println("getpost 오류");
		}catch(Exception e) {
			System.out.println("데이터 가져오기를 실패했습니다.");
			e.printStackTrace();
		}
		return post;
	}
	
	//조회수
	public int views_up(int post_index) {
		try {
			//postDTO post = new postDTO();
			sql SQL = new sql();
			String sql = SQL.views_up();
			
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, post_index);
			result = pstm.executeUpdate();
			pstm.close();
			
		}catch(SQLException e) {
			System.out.println("SQL Error");
			System.out.println("views_up 오류");
		}catch(Exception e) {
			System.out.println("조회수 올리기를 실패하였습니다.");
		}
		return result;
	}
	
	//메인 미리보기
	public ArrayList<postDTO> post_main() {
		try {
			plist = new ArrayList<>();
			sql SQL = new sql();
			String sql = SQL.p_main();
			
			st = con.createStatement(); // 데이터베이스의 sql 실행 준비
			rs = st.executeQuery(sql);  // sql 실행하고 결과 리턴
			
			while(rs.next()) {
				postDTO post = new postDTO();
				post.setTitle(rs.getString("title"));
				post.setImg(rs.getString("img"));
				post.setCategory(rs.getString("category"));
				post.setPost_index(rs.getInt("post_index"));
				
				plist.add(post);
				
				for(int i = 0; i<plist.size(); i++) {
					postDTO postDTO = plist.get(i);
				}	
			}
			rs.close();
		}catch(SQLException e) {
			System.out.println("SQL Error : " + e.getMessage());
			System.out.println("post_main 오류");
		}catch(Exception e) {
			System.out.println("데이터 가져오기를 실패했습니다.");
			e.printStackTrace();
		}
		return plist;
	}
	
	//조회수 순
	public ArrayList<postDTO> post_views() {
		try {
			plist = new ArrayList<>();
			sql SQL = new sql();
			String sql = SQL.p_views();
			
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				postDTO post = new postDTO();
				post.setTitle(rs.getString("title"));
				post.setImg(rs.getString("img"));
				post.setCategory(rs.getString("category"));
				post.setPost_index(rs.getInt("post_index"));
				
				plist.add(post);
				
				for(int i = 0; i<plist.size(); i++) {
					postDTO postDTO = plist.get(i);
				}
			}
			rs.close();
		}catch(SQLException e) {
			System.out.println("SQL Error : " + e.getMessage());
			System.out.println("post_views 오류");
		}catch(Exception e) {
			System.out.println("데이터 가져오기를 실패했습니다.");
			e.printStackTrace();
		}
		return plist;
	}
	
	//댓글
	public ArrayList<commentDTO> comment(int post_index) {
		try {
			clist = new ArrayList<>();
			sql SQL = new sql();
			String sql = SQL.c_select();
			
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, post_index);
		    rs = pstm.executeQuery();
			
			while(rs.next()) {
				commentDTO co = new commentDTO();
				co.setContent(rs.getString("content"));
				co.setDate_created(rs.getString("date_created"));
				co.setNickname(rs.getString("nickname"));
				co.setComment_index(rs.getInt("comment_index"));
				co.setMember_index(rs.getInt("member_index"));
				clist.add(co);
				
				for(int i = 0; i<clist.size(); i++) {
					commentDTO commentDTO = clist.get(i);
//					System.out.println(commentDTO.getNickname());
				}
			}
			rs.close();
			pstm.close();
		}catch(SQLException e) {
			System.out.println("SQL Error : " + e.getMessage());
			System.out.println("comment 오류");
		}catch(Exception e) {
			System.out.println("데이터 가져오기를 실패했습니다.");
			e.printStackTrace();
		}
		return clist;
	}
	
	//위시리스트
	public ArrayList<wishlistDTO> wishlist(int sender, int limit, int offset) {
		try {
			wlist = new ArrayList<>();
			sql SQL = new sql();
			String sql = SQL.w_select();
			pstm = con.prepareStatement(sql);
			
			pstm.setInt(1, sender);
			pstm.setInt(2, limit);
			pstm.setInt(3, offset);
		    rs = pstm.executeQuery();
			
			while(rs.next()) {
				wishlistDTO wish = new wishlistDTO();
				wish.setImg(rs.getString("img"));
				wish.setDate_created(rs.getString("date_created"));
				wish.setTitle(rs.getString("title"));
				wish.setPost_index(rs.getInt("post_index"));
				wish.setCategory(rs.getString("category"));
				wlist.add(wish);
			}
			rs.close();
			pstm.close();
		}catch(SQLException e) {
			System.out.println("SQL Error : " + e.getMessage());
			System.out.println("wishlist 오류");
		}catch(Exception e) {
			System.out.println("데이터 가져오기를 실패했습니다.");
			e.printStackTrace();
		}
		return wlist;
	}
	
	//위시리스트 등록 유무
	public int wishlist(int member_index, int post_index) {
		postDTO post = null;
		try {
			String SQL = sql.wishlist;
			
			pstm = con.prepareStatement(SQL);
			pstm.setInt(1,member_index);
			pstm.setInt(2,post_index);
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				post = new postDTO();
				post.setPostcount(rs.getInt("count"));
			}

		}catch(SQLException e) {
			System.out.println("SQL 오류 :"+e.getMessage());
			System.out.println("wishlist 오류");
		}catch(Exception e) {
			System.out.println("데이터 가져오기를 실패했습니다.");
		}
		return post.getPostcount();
	}
	
	//마이페이지
	public ArrayList<memberDTO> member(int sender) {
		try {
			memlist = new ArrayList<>();
			sql SQL = new sql();
			String sql = SQL.my_select();
			pstm = con.prepareStatement(sql);
			
			pstm.setInt(1, sender);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				memberDTO mem = new memberDTO();
				mem.setUserID(rs.getString("userID"));
				mem.setNickname(rs.getString("nickname"));
				mem.setPswd(rs.getString("pswd"));
				
				memlist.add(mem);
				
				for(int i = 0; i<memlist.size(); i++) {
					memberDTO memberDTO = memlist.get(i);
				}
			}
			rs.close();
			pstm.close();
		}catch(SQLException e) {
			System.out.println("SQL Error : " + e.getMessage());
			System.out.println("member 오류");
		}catch(Exception e) {
			System.out.println("데이터 가져오기를 실패했습니다.");
			e.printStackTrace();
		}
		return memlist;
	}
	
	//내가 쓴 글
	public ArrayList<postDTO> mem_post(int sender) {
		try {
			plist = new ArrayList<>();
			sql SQL = new sql();
			String sql = SQL.me_post(sender);
			
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				postDTO post = new postDTO();
				post.setPost_index(rs.getInt("post_index"));
				post.setTitle(rs.getString("title"));
				post.setDate_created(rs.getString("date_created"));
				post.setViews(rs.getInt("views"));
				post.setNickname(rs.getString("nickname"));
				
				plist.add(post);
				
				for(int i = 0; i<plist.size(); i++) {
					postDTO postDTO = plist.get(i);
				}
			}
			rs.close();
		}catch(SQLException e) {
			System.out.println("SQL Error : " + e.getMessage());
			System.out.println("mem_post 오류");
		}catch(Exception e) {
			System.out.println("데이터 가져오기를 실패했습니다.");
			e.printStackTrace();
		}
		return plist;
	}
	
	//내가 쓴 댓글
	public ArrayList<commentDTO> mem_comment(int sender) {
		try {
			clist = new ArrayList<>();
			sql SQL = new sql();
			String sql = SQL.me_comment(sender);
			
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				commentDTO co = new commentDTO();
				co.setContent(rs.getString("content"));
				co.setDate_created(rs.getString("date_created"));
				co.setNickname(rs.getString("nickname"));
				
				clist.add(co);
				
				for(int i = 0; i<clist.size(); i++) {
					commentDTO commentDTO = clist.get(i);
				}
			}
			rs.close();
		}catch(SQLException e) {
			System.out.println("SQL Error : " + e.getMessage());
			System.out.println("mem_comment 오류");
		}catch(Exception e) {
			System.out.println("데이터 가져오기를 실패했습니다.");
			e.printStackTrace();
		}
		return clist;
	}
	

	//채팅 왼쪽(보낸 사람만 보기)
//	public ArrayList<messengerDTO> messenger(int receiver) {
//		try {
//			meslist = new ArrayList<>();
//			sql SQL = new sql();
//			String sql = SQL.me_select();
//			
//			pstm = con.prepareStatement(sql);
//			pstm.setInt(1, receiver);
//		    rs = pstm.executeQuery();
//		    
//			while(rs.next()) {
//				messengerDTO mes = new messengerDTO();
//				mes.setSender(rs.getString("sender_nick"));
//				mes.setContent(rs.getString("content"));
//				mes.setDate_time(rs.getString("date_time"));
//				mes.setMember_index(rs.getInt("sender"));
//				
//				meslist.add(mes);
//				
//				for(int i = 0; i<meslist.size(); i++) {
//					messengerDTO messengerDTO = meslist.get(i);
//				}
//			}
//			rs.close();
//			pstm.close();
//			
//		}catch(SQLException e) {
//			System.out.println("SQL Error : " + e.getMessage());
//			System.out.println("messenger 오류");
//		}catch(Exception e) {
//			System.out.println("데이터 가져오기를 실패했습니다.");
//			e.printStackTrace();
//		}
//		return meslist;
//	}
	
	//채팅 왼쪽(사용자의 모든 채팅 보기)
	public ArrayList<messengerDTO> messenger(int my_index) {
		try {
			meslist = new ArrayList<>();
			String SQL = sql.messenger;
			
			pstm = con.prepareStatement(SQL);
			pstm.setInt(1, my_index);
			pstm.setInt(2, my_index);
		    rs = pstm.executeQuery();
		    
			while(rs.next()) {
				messengerDTO mes = new messengerDTO();
				mes.setSender(rs.getString("sender_nick"));
				mes.setContent(rs.getString("content"));
				mes.setDate_time(rs.getString("date_time"));
				if (rs.getInt("sender") == my_index) {
					mes.setMember_index(rs.getInt("receiver"));
				} else {
					mes.setMember_index(rs.getInt("sender"));
				}
				meslist.add(mes);
			}
			rs.close();
			pstm.close();
			
		}catch(SQLException e) {
			System.out.println("SQL Error : " + e.getMessage());
			System.out.println("messenger 오류");
		}catch(Exception e) {
			System.out.println("데이터 가져오기를 실패했습니다.");
			e.printStackTrace();
		}
		return meslist;
	}
	public ArrayList<messengerDTO> messenger2(int member_index) {
		String sql1 = "select * from (select messenger_index, content, to_char(date_time,'YY-MM-DD HH24:MI') as date_time, sender, receiver from messenger inner join member on messenger.sender = member.member_index where (messenger.sender,date_time) in (select messenger.sender, max(date_time) as date_time from messenger group by messenger.sender, receiver) order by date_time desc)t WHERE receiver = ? or sender = ?;";
		meslist = new ArrayList<messengerDTO>();
		ArrayList<Integer> person_list = new ArrayList<Integer>();
		ArrayList<Integer> messenger_list = new ArrayList<Integer>();
		try {
			pstm = con.prepareStatement(sql1);
			pstm.setInt(1, member_index);
			pstm.setInt(2, member_index);
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				messengerDTO mes = new messengerDTO();
				mes.setMessenger_index(rs.getInt("messenger_index"));
				if (rs.getInt("sender") == member_index) {
					mes.setMember_index(rs.getInt("sender"));
					mes.setMember_index2(rs.getInt("receiver"));
				} else {
					mes.setMember_index(rs.getInt("receiver"));
					mes.setMember_index2(rs.getInt("sender"));
				}
				
				meslist.add(mes);
			}
			
			for (messengerDTO item : meslist) {
				int person = item.getMember_index2();
				if (person_list.indexOf(person) == -1) {
					person_list.add(person);
					messenger_list.add(item.getMessenger_index());
				}
			}
			meslist = new ArrayList<messengerDTO>();
			for (int index = 0; index < person_list.size(); index++) {
				String sql2 = "SELECT * FROM messenger WHERE messenger_index = " + String.valueOf(messenger_list.get(index)) + ";";
				pstm = con.prepareStatement(sql2);
				rs = pstm.executeQuery();
				messengerDTO mes = new messengerDTO();
				if (rs.next()) {
					mes.setContent(rs.getString("content"));
					mes.setDate_time(rs.getString("date_time"));
				}
				mes.setMember_index(person_list.get(index));
				String sql3 = "SELECT * FROM member WHERE member_index = " + String.valueOf(person_list.get(index)) + ";";
				pstm = con.prepareStatement(sql3);
				rs = pstm.executeQuery();
				if (rs.next()) {
					mes.setSender(rs.getString("nickname"));
				}
				meslist.add(mes);
			}
			
			
		} catch (SQLException e) {
			System.out.println("SQL Error : " + e.getMessage());
			System.out.println("messenger2 오류");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return meslist;
		
	}
	
	//채팅 오른쪽(현재 대화중인 채팅 보기)
	public ArrayList<messengerDTO> mes_content(int sender, int receiver) {
		try {
			meslist = new ArrayList<>();
			sql SQL = new sql();
			String sql = SQL.me_content(sender, receiver);
			
			st = con.createStatement(); // 데이터베이스의 sql 실행 준비
			rs = st.executeQuery(sql);  // sql 실행하고 결과 리턴
			
			while(rs.next()) {
				messengerDTO mes = new messengerDTO();
				mes.setSender(rs.getString("send_name"));
				mes.setReceiver(rs.getString("recv_name"));
				mes.setContent(rs.getString("content"));
				mes.setDate_time(rs.getString("date_time"));
				
				meslist.add(mes);
			}
				for(int i = 0; i<meslist.size(); i++) {
				messengerDTO messengerDTO = meslist.get(i);
				System.out.println(messengerDTO.getDate_time());
				}
			rs.close();
		}catch(SQLException e) {
			System.out.println("SQL Error : " + e.getMessage());
			System.out.println("mes_content 오류");
		}catch(Exception e) {
			System.out.println("데이터 가져오기를 실패했습니다.");
			e.printStackTrace();
		}
		return meslist;
	}
	
	//채팅 오른쪽(자른거)
	public ArrayList<messengerDTO> messeger_cut(int sender, int receiver, int limit, int offset) {
		try {
			meslist = new ArrayList<>();
			String SQL = sql.messeger_cut; 
			
			pstm = con.prepareStatement(SQL);
			pstm.setInt(1, sender);
			pstm.setInt(2, receiver);
			pstm.setInt(3, sender);
			pstm.setInt(4, receiver);
			pstm.setInt(5, limit);
			pstm.setInt(6, offset);
		    rs = pstm.executeQuery();
			
			while(rs.next()) {
				messengerDTO mes = new messengerDTO();
				mes.setSender(rs.getString("send_name"));
				mes.setContent(rs.getString("content"));
				mes.setDate_time(rs.getString("date_time"));
				mes.setMember_index(rs.getInt("sender"));
				mes.setMember_index2(rs.getInt("receiver"));
				
				meslist.add(mes);
			}
				for(int i = 0; i<meslist.size(); i++) {
				messengerDTO messengerDTO = meslist.get(i);
				//System.out.println(messengerDTO.getDate_time());
				}
			rs.close();
			pstm.close();
			
		}catch(SQLException e) {
			System.out.println("SQL Error : " + e.getMessage());
			System.out.println("messeger_cut 오류");
		}catch(Exception e) {
			System.out.println("데이터 가져오기를 실패했습니다.");
			e.printStackTrace();
		}
		return meslist;
	}
	
	//제목으로 검색
	public ArrayList<postDTO> title_search(String search_title, String category) {
		try{
			plist = new ArrayList<>();
			sql SQL = new sql();
			String sql = SQL.title_search();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, "%"+search_title+"%");
			pstm.setString(2, category);
		    rs = pstm.executeQuery();
			
			while(rs.next()) {
				postDTO post = new postDTO();
				post.setPost_index(rs.getInt("post_index"));
				post.setTitle(rs.getString("title"));
				post.setDate_created(rs.getString("date_created"));
				post.setViews(rs.getInt("views"));
				post.setNickname(rs.getString("nickname"));
				
				plist.add(post);
			}
			
			rs.close();
			pstm.close();
			
			for(int i = 0; i<plist.size(); i++) {
				postDTO postDTO = plist.get(i);
				//System.out.println(postDTO.getTitle());
			}
			
		}catch(SQLException e) {
			System.out.println("SQL Error : " + e.getMessage());
			System.out.println("title_search 오류");
		}catch(Exception e) {
			System.out.println("데이터 가져오기를 실패했습니다.");
		}
		return plist;
	}
	
	//제목으로 검색(자른거)
	public ArrayList<postDTO> title_search(String search_title, String category,int limit, int offset) {
		try{
			plist = new ArrayList<>();
			String SQL = sql.title_search;
			
			pstm = con.prepareStatement(SQL);
			pstm.setString(1, "%"+search_title+"%");
			pstm.setString(2, category);
			pstm.setInt(3, limit);
			pstm.setInt(4, offset);
		    rs = pstm.executeQuery();
			
			while(rs.next()) {
				postDTO post = new postDTO();
				post.setPost_index(rs.getInt("post_index"));
				post.setTitle(rs.getString("title"));
				post.setDate_created(rs.getString("date_created"));
				post.setViews(rs.getInt("views"));
				post.setNickname(rs.getString("nickname"));
				
				plist.add(post);
			}
			
			rs.close();
			pstm.close();
			
		}catch(SQLException e) {
			System.out.println("SQL Error : " + e.getMessage());
			System.out.println("title_search(자른거) 오류");
		}catch(Exception e) {
			System.out.println("데이터 가져오기를 실패했습니다.");
		}
		return plist;
	}
	
	//로그인(아이디, 비밀번호)
	public memberDTO login(String id, String pswd) {
		memberDTO mem = null;
		try {
			sql SQL = new sql();
			String sql = SQL.login();
			
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, SHA256(pswd));
			rs = pstm.executeQuery(); // sql 실행하고 결과 리턴
		    
			if(rs.next()) {
				mem = new memberDTO();
				mem.setUserID(rs.getString("UserID"));
				mem.setMember_index(rs.getInt("member_index"));
				mem.setNickname(rs.getString("nickname"));
				mem.setPhone(rs.getString("phone"));
				mem.setIsadmin(rs.getInt("isadmin"));
			}
			rs.close();

		}catch(SQLException e) {
			System.out.println("SQL Error : " + e.getMessage());
			System.out.println("login 오류");
		}catch(Exception e) {
			System.out.println("데이터 가져오기를 실패했습니다.");
			e.printStackTrace();
		}
		return mem;
	}
	
	/*select*/
	
	/*insert*/
	//회원가입
	public int mem_insert(memberDTO mem) {
		try {
			//memberDTO mem = new memberDTO();
			sql SQL = new sql ();
			String sql = SQL.my_insert();
			pstm = con.prepareStatement(sql);
			
			
			/*mem.setNickname("닉네임");
			mem.setPswd("비밀번호");
			mem.setPhone("010");
			mem.setUserID("토끼");
			mem.setIsadmin(1);
			mem.setDeactive(1);*/ 
			
			pstm.setString(1, mem.getNickname());
			pstm.setString(2, SHA256(mem.getPswd()));
			pstm.setString(3, mem.getPhone());
			pstm.setString(4, mem.getUserID());
			result = pstm.executeUpdate();
			pstm.close();
			
		}catch(SQLException e) {
			System.out.println("SQL Error");
			System.out.println("mem_insert 오류");
		}catch(Exception e) {
			System.out.println("데이터 입력을 실패했습니다");
		}
		return result;
	}
	
	//글쓰기
	public int post_insert(memberDTO mem, postDTO post) {
		try {
			//memberDTO mem = new memberDTO();
			//postDTO post = new postDTO();
			sql SQL = new sql();
			String sql = SQL.p_insert();
			pstm = con.prepareStatement(sql);
			
			pstm.setInt(1, mem.getMember_index());
			pstm.setString(2, post.getTitle());
			pstm.setString(3, post.getContent());
			pstm.setString(4, post.getCategory());
			pstm.setString(5, post.getImg());
			pstm.setString(6, post.getPrice());
			pstm.setInt(7, post.getIsphone());
			result = pstm.executeUpdate();
			pstm.close();
			
		}catch(SQLException e) {
			System.out.println("SQL Error");
			System.out.println("post_insert 오류");
		}catch(Exception e) {
			System.out.println("데이터 입력을 실패했습니다");
		}
		return result;
	}
	
	//글쓰기(휴대폰 번호 공개여부 추가)
	public int postinsert(memberDTO mem, postDTO post) {
		try {
			//memberDTO mem = new memberDTO();
			//postDTO post = new postDTO();
			
			String SQL = sql.post_insert;
			pstm = con.prepareStatement(SQL);
			
			pstm.setInt(1, mem.getMember_index());
			pstm.setString(2, post.getTitle());
			pstm.setString(3, post.getContent());
			pstm.setString(4, post.getCategory());
			pstm.setString(5, post.getImg());
			pstm.setString(6, post.getPrice());
			pstm.setInt(7, post.getIsphone());
			result = pstm.executeUpdate();
			pstm.close();
			
		}catch(SQLException e) {
			System.out.println("SQL Error");
			System.out.println("postinsert 오류");
		}catch(Exception e) {
			System.out.println("데이터 입력을 실패했습니다");
		}
		return result;
	}
	
	//채팅
	public int mes_insert(messengerDTO mes) {
		try {
			//messengerDTO mes = new messengerDTO();
			//memberDTO mem = new memberDTO();
			sql SQL = new sql();
			String sql = SQL.me_insert();
			pstm = con.prepareStatement(sql);
			
			pstm.setInt(1, mes.getMember_index());
			pstm.setInt(2, mes.getMember_index2());
			pstm.setString(3, mes.getContent());
			result = pstm.executeUpdate();
			pstm.close();
			
		}catch(SQLException e) {
			System.out.println("SQL Error");
			System.out.println("mes_insert 오류");
		}catch(Exception e) {
			System.out.println("데이터 입력을 실패했습니다");
		}
		return result;
	}
	
	//댓글
	public int comment_insert(int member_index, int post_index, String content) {
		try {
			//memberDTO mem = new memberDTO();
			//postDTO post = new postDTO();
			//commentDTO com = new commentDTO();
			sql SQL = new sql();
			String sql = SQL.c_insert();
			pstm = con.prepareStatement(sql);
			
			pstm.setInt(1, member_index);
			pstm.setInt(2, post_index);
			pstm.setString(3, content);
			result = pstm.executeUpdate();
			pstm.close();
			
		}catch(SQLException e) {
			System.out.println("SQL Error");
			System.out.println("comment_insert 오류");
			System.out.println(e.getMessage());
		}catch(Exception e) {
			System.out.println("데이터 입력을 실패했습니다");
		}
		return result;
	}
	
	//위시리스트
	public int wish_insert(memberDTO mem, postDTO post) {
		try {
			sql SQL = new sql();
			String sql = SQL.w_insert();
			pstm = con.prepareStatement(sql);
			
			pstm.setInt(1, post.getPost_index());
			pstm.setInt(2, mem.getMember_index());
			result = pstm.executeUpdate();
			pstm.close();
			
		}catch(SQLException e) {
			System.out.println("SQL Error");
			System.out.println("wish_insert 오류");
		}catch(Exception e) {
			System.out.println("데이터 입력을 실패했습니다");
		}
		return result;
	}
	
	/*update*/
	//게시물
	public int post_update(postDTO post) {
		try {
			//postDTO post = new postDTO();
			sql SQL = new sql();
			String sql = SQL.p_update();
			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, post.getTitle());
			pstm.setString(2, post.getContent());
			pstm.setString(3, post.getCategory());
			pstm.setString(4, post.getImg());
			pstm.setString(5, post.getPrice());
			pstm.setInt(6, post.getIsphone());
			pstm.setInt(7, post.getPost_index());
			result = pstm.executeUpdate();
			pstm.close();
			
		}catch(SQLException e) {
			System.out.println("SQL Error");
			System.out.println("post_update 게시물 수정 오류");
		}catch(Exception e) {
			System.out.println("데이터 수정을 실패했습니다");
		}
		return result;
	}
	
	//댓글
	public int comment_update(commentDTO com) {
		try {
			//commentDTO com = new commentDTO();
			sql SQL = new sql();
			String sql = SQL.c_update();
			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, com.getContent());
			pstm.setInt(2, com.getComment_index());
			result = pstm.executeUpdate();
			pstm.close();
			
		}catch(SQLException e) {
			System.out.println("SQL Error");
			System.out.println("comment_update 댓글 수정 오류");
		}catch(Exception e) {
			System.out.println("데이터 수정을 실패했습니다");
		}
		return result;
	}
	
	//마이페이지 (둘다 변경)
	public int mem_update(memberDTO mem) {
		try {
			//memberDTO mem = new memberDTO();
			sql SQL = new sql();
			String sql = SQL.me_update();
			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, SHA256(mem.getPswd()));
			pstm.setString(2, mem.getNickname());
			pstm.setInt(3, mem.getMember_index());
			result = pstm.executeUpdate();
			pstm.close();
			
		}catch(SQLException e) {
			System.out.println("SQL Error");
			System.out.println("mem_update 마이페이지(둘다 변경) 오류");
		}catch(Exception e) {
			System.out.println("데이터 수정을 실패했습니다");
		}
		return result;
	}
	
	//마이페이지(닉네임 변경)
	public int memnick_update(memberDTO mem) {
		try {
			//memberDTO mem = new memberDTO();
			sql SQL = new sql();
			String sql = SQL.menick_update();
			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, mem.getNickname());
			pstm.setInt(2, mem.getMember_index());
			result = pstm.executeUpdate();
			pstm.close();
			
		}catch(SQLException e) {
			System.out.println("SQL Error");
			System.out.println("memnick_update 마이페이지 닉네임 변경 오류");
		}catch(Exception e) {
			System.out.println("데이터 수정을 실패했습니다");
		}
		return result;
	}
	
	//마이페이지(비밀변호 변경)
	public int mempswd_update(memberDTO mem) {
		try {
			//memberDTO mem = new memberDTO();
			sql SQL = new sql();
			String sql = SQL.mepswd_update();
			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, SHA256(mem.getPswd()));
			pstm.setInt(2, mem.getMember_index());
			result = pstm.executeUpdate();
			pstm.close();
			
		}catch(SQLException e) {
			System.out.println("SQL Error");
			System.out.println("mempswd_update 마이페이지 비밀번호 변경 오류");
		}catch(Exception e) {
			System.out.println("데이터 수정을 실패했습니다");
		}
		return result;
	}
	/*insert*/
	
	/*delete*/
	//게시물
	public int post_delete(postDTO post) {
		try {
			//postDTO post = new postDTO();
			sql SQL = new sql();
			String sql = SQL.p_delete();
			pstm = con.prepareStatement(sql);
			
			pstm.setInt(1, post.getPost_index());
			result = pstm.executeUpdate();
			pstm.close();
			
		}catch(SQLException e) {
			System.out.println("SQL Error");
			System.out.println("post_delete 게시물 삭제 오류");
		}catch(Exception e) {
			System.out.println("데이터 삭제를 실패했습니다");
		}
		return result;
	}
	
	//댓글
	public int comment_delete(int comment_index) {
		try {
			sql SQL = new sql();
			String sql = SQL.c_delete();
			pstm = con.prepareStatement(sql);
			
			pstm.setInt(1, comment_index);
			result = pstm.executeUpdate();
			pstm.close();
			
		}catch(SQLException e) {
			System.out.println("SQL Error");
			System.out.println("comment_delete 댓글 삭제 오류");
		}catch(Exception e) {
			System.out.println("데이터 삭제를 실패했습니다");
		}
		return result;
	}
	
	//댓글
	public int wish_delete(wishlistDTO wish) {
		try {
			String SQL = sql.w_delete;
			pstm = con.prepareStatement(SQL);
			
			pstm.setInt(1, wish.getPost_index());
			result = pstm.executeUpdate();
			pstm.close();
			
		}catch(SQLException e) {
			System.out.println("SQL Error");
			System.out.println("wish_delete 댓글 삭제 오류");
		}catch(Exception e) {
			System.out.println("데이터 삭제를 실패했습니다");
		}
		return result;
	}
	/*delete*/
	
	//DB닫기
	public void DBclose() {
		try {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException e) {}
			}
			if(st != null) {
				try{
					st.close();
				}catch(SQLException e) {}
			}
			if(con != null) {
				try {
				con.close();
				}catch(SQLException e) {}
			}
			if(pstm != null) {
				try {
					pstm.close();
				}catch(SQLException e) {}
			}
			//System.out.println("연결 끊기");
			
	}catch(Exception e) {
		System.out.println("CLOSE ERROR : " + e);
		}
	}
	private String SHA256(String password) {
		String SHA = ""; 
		try{
			MessageDigest sh = MessageDigest.getInstance("SHA-256"); 
			sh.update(password.getBytes()); 
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer(); 
			for(int i = 0 ; i < byteData.length ; i++){
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}
			SHA = sb.toString();
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace(); 
			SHA = null; 
		}
		return SHA;
	}
}