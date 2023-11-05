package model.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.AdminInfoDto;



public class AdminInfoDao extends Dao {
	// 싱글톤 생성 
	private static AdminInfoDao adminInfoDao = new AdminInfoDao();
	public static AdminInfoDao getInstance() { return adminInfoDao; }
	private AdminInfoDao() {}
	
	/*
	 * 작성자 :  김준희
	 * 기능 	:	insert 쿼리 실행 Controller.addCard()로 등록결과를 int형으로 return
	 * */
	public int addCard(int tno, String cname) {
		System.out.println("Iam Dao ('ㅁ') / tno : " + tno +" | cname : "+cname);
		
		try {
			String sql = "insert into card(tno, cname) values (?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, tno);
			ps.setString(2, cname);
			int row = ps.executeUpdate();
			if(row ==1) {return 1;}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 2;
	}
	/*
	 * 작성자 :  김준희
	 * 기능 	:  카드 혹은  힌트가 존재여부를 반환하는 함수 
	 * 		   매개변수의 값이 0 과 name(카드명)인경우 -   카드 테이블을 검색하고 카드번호를 반환
	 * 		   매개변수의 값이 cno와 name(카드명)인 경우 - 힌트테이블을 검색하고 힌드의번호를 반환
	 * 		   카드번호 or 힌트번호가 있으면 중복카드 / 0 이면 존재하지 않는 카드 
	 * */
	public int isExist(int no , String name) {
		String sql = "";
		try {
			if(no == 0) { 
				sql = "select cno  from card where cname =?";
				ps= conn.prepareStatement(sql);
				ps.setString(1,name);	
			} else {
				sql = "select hno from hint where hname = ? and cno =?";
				ps= conn.prepareStatement(sql);
				ps.setString(1,name);
				ps.setInt(2,no);
			}
			rs= ps.executeQuery();
			if(rs.next()) return rs.getInt(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	/*
	 * 작성자	: 김준희
	 * 기능 	: 카드 등록이 완료되면 등록된 카드의 번호를 리턴해주는 함수 
	 * 
	 * */
	public int getCardNo(String cname) {
		try {
			String sql = "select cno from card where cname = ? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, cname);
			rs =  ps.executeQuery();
			if(rs.next()) { return rs.getInt(1);}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} return 0;
	}		

	/*
	 * 작성자	: 김준희
	 * 기능 	: 힌트등록함수
	 * 
	 * */
	public int addHint(String htype, String hname, int cno) {
		
		try {
			String sql = "insert into hint(cno,htype, hname) values (?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, cno);
			ps.setString(2, htype);
			ps.setString(3, hname);
			int row = ps.executeUpdate();
			if(row ==1) {return 1;}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 2;

	}
	/*
	 * 작성자	: 김준희
	 * 기능 	: 힌트등록함수
	 *index : 0 = card 수정 / index = 1 힌트수정
	 * */
	public boolean updateCard(int hno, int cno, String name) {
		String sql = "";
		try {
			if(hno == 0 ) { //카드수정
				//update 테이블명 set 수정할필드명 = 수정할값 where 조건 
				sql = "update card set cname = ? where cno = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, name);
				ps.setInt(2,cno);
			} else  { //힌트수정
				sql = "update hint set hname = ? where cno = ? and hno = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, name);
				ps.setInt(2,cno);
				ps.setInt(3,hno);
			}
			
			
			
			int row = ps.executeUpdate();
			if(row == 1 ) {return true;}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} return false;
		
		
	}
	
	// -------------------------------------------------------------------
	// 카드 내역 출력하기 위해 데이터 받아오기 ( 신예지 ) -------------------------------------------------------------------
	public ArrayList<AdminInfoDto> viewHint() {
		
		ArrayList<AdminInfoDto> list = new ArrayList<>();
		try {
			// 1. SQL 작성하기
			String sql = "select tno, cno, hname, htype from typecard natural join card natural join hint order by cno asc;"; 
			// 2. 작성한 SQL을 조작할 인터페이스 PS객체 반환한다.
			ps = conn.prepareStatement(sql);
			// 3. 검색결과의 레코드를 여러개 조작하기 위해 resultSet 객체 반환 
			rs = ps.executeQuery();
			// 4. 여러개 레코드 조회
			while( rs.next() ){ // 마지막 레코드까지 하나씩 이동
				int tno = rs.getInt("tno");
				int cno = rs.getInt("cno");
	            String hname = rs.getString("hname");
	            String htype = rs.getString("htype");
				AdminInfoDto dto = new AdminInfoDto(tno, cno, hname, htype);
				list.add(dto);
			} // while e
		} catch ( Exception e ) {}
		return list;
	}// viewCardAll e
	
	
	public ArrayList<AdminInfoDto> viewCard() {
		
		ArrayList<AdminInfoDto> list = new ArrayList<>();
		try {
			// 1. SQL 작성하기
			String sql = "select * from card order by tno asc;"; 
			// 2. 작성한 SQL을 조작할 인터페이스 PS객체 반환한다.
			ps = conn.prepareStatement(sql);
			// 3. 검색결과의 레코드를 여러개 조작하기 위해 resultSet 객체 반환 
			rs = ps.executeQuery();
			// 4. 여러개 레코드 조회
			while( rs.next() ){ // 마지막 레코드까지 하나씩 이동
				int tno = rs.getInt("tno");
				int cno = rs.getInt("cno");
	            String cname = rs.getString("cname");
				AdminInfoDto dto = new AdminInfoDto(tno, cno, cname);
				list.add(dto);
			} // while e
		} catch ( Exception e ) {}
		return list;
	}// viewCard e
	
	// -------------------------------------------------------------------
	// 카드 삭제하기 ( 신예지 ) -------------------------------------------------------------------
	public boolean deleteCard(String c) {
		try {
			// 1. SQL 작성하기
			String sql = "delete from card where cname = ?;"; 
			// 2. 작성한 SQL을 조작할 인터페이스 PS객체 반환한다.
			ps = conn.prepareStatement(sql);
			ps.setString(1, c);
			// 3. 삭제
			int row = ps.executeUpdate();
			if(row > 0) {return true;}		
		} catch ( Exception e ) {}
		return false;
	}// deleteCard e
	// -------------------------------------------------------------------
	// 힌트 삭제하기 ( 신예지 ) -------------------------------------------------------------------
	public boolean deleteHint(String h) {
		try {
			// 1. SQL 작성하기
			String sql = "delete from hint where hname = ?;"; 
			// 2. 작성한 SQL을 조작할 인터페이스 PS객체 반환한다.
			ps = conn.prepareStatement(sql);
			ps.setString(1, h);
			// 3. 삭제
			int row = ps.executeUpdate();
			if(row > 0) {return true;}		
		} catch ( Exception e ) {}
		return false;
	} // deleteHint e
}
