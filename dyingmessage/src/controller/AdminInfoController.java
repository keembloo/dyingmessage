package controller;



import java.util.ArrayList;

import model.dao.AdminInfoDao;
import model.dto.AdminInfoDto;


public class AdminInfoController {
	// 싱글톤 생성 
	private static AdminInfoController adminInfoController = new AdminInfoController();
	public static AdminInfoController getInstance() { return adminInfoController; }
	private AdminInfoController() {}
	
	/*
	 * 작성자 :  김준희
	 * 기능 	:	Dao.addCard  타입번호와 카드명 전달 / 등록여부(boolean) 반환
	 * */
	public int addCard(int tno, String cname) {
		System.out.println("tno : " + tno +" | cname : "+cname);
		
		return AdminInfoDao.getInstance().addCard(tno,cname);   
	}
	/*
	 * 작성자 :  	김준희
	 * 기능 	:	isExist: 카드중복체크 no값이 0인경우는 카드테이블 조회 / 0이 아닌경우 카드 번호와 힌트명
	 * 리턴 	: 	cno / hno
	 * */
	public int isExist(int no , String name) {
		return AdminInfoDao.getInstance().isExist(no, name);
	}
	/*
	 * 작성자	: 김준희
	 * 기능 	: 카드 등록이 완료되면 등록된 카드의 번호를 리턴해주는 함수 
	 * */
	public int getCardNo(String cname) {
		return AdminInfoDao.getInstance().getCardNo(cname);
	}

	public int addHint(String htype, String hname, int cno) {
		return AdminInfoDao.getInstance().addHint(htype, hname, cno);
	}
	
	public boolean updateCard(int hno, int no, String name) {
		return AdminInfoDao.getInstance().updateCard(hno, no, name);
		
	}
	
	// -------------------------------------------------------------------
	// 카드 내역 출력하기 ( 신예지 ) -------------------------------------------------------------------
	// 분류된 데이터를 저장할 리스트 전역에 만들어두기
	public ArrayList<AdminInfoDto> hint = new ArrayList<>(); 
	public ArrayList<AdminInfoDto> Card = new ArrayList<>();
	// 카드 분류할 메소드들 -----------------------
	public void viewHint() {
		// 1. 여러개의 게시물을 요청해서 반환된 결과
		ArrayList<AdminInfoDto> r = AdminInfoDao.getInstance().viewHint();
	    //전역 리스트에 넣어주기
		hint.addAll(r);
	} // viewHint e -----------
	public void viewCard() {
		// 1. 여러개의 게시물을 요청해서 반환된 결과
		ArrayList<AdminInfoDto> r = AdminInfoDao.getInstance().viewCard();
	    // 전역 리스트에 넣어주기
		Card.addAll(r);
	} // viewCard e -----------
	
	// 출력기능 메소드 ----------------------------
	public void printCard(int tno) {
		//viewHint(); viewCard(); // 데이터 분류 메소드 실행
		for(int i = 0; i<Card.size(); i++) {
			if(Card.get(i).getTno() == tno) {
				int cno = Card.get(i).getCno();
				System.out.println("\n [ "+Card.get(i).getCname()+" ]"); 
				System.out.println(" ● 명사힌트");
				System.out.print( " => " );
				for( int j = 0; j<hint.size(); j++) {
					if( tno == hint.get(j).getTno() && hint.get(j).getCno() == cno && hint.get(j).getHtype().equals("n")) {
						System.out.print(hint.get(j).getHname()+" ");
					}
				}
				System.out.println("\n ● 형용사힌트");
				System.out.print( " => " );
				for( int j = 0; j<hint.size(); j++) {
					if( tno == hint.get(j).getTno() && hint.get(j).getCno() == cno && hint.get(j).getHtype().equals("a")) {
						System.out.print(hint.get(j).getHname()+" ");
					}
				}	
				System.out.print( " \n " );
			}
			
		}

	}
	
//	// 명사 힌트 출력 메소드 ( 신예지 ) ------------------------------------------------------
//	public void PrintNCard( int intN ) { // PrintNCard s
//		ArrayList<AdminInfoDto> N = new ArrayList<>();
//		if( intN == 1 ) 	{N = tno_01;} 
//		else if( intN == 2 ){N = tno_02;}
//		else if( intN == 3 ){N = tno_03;}
//		for ( int i = 0; i<N.size(); i++) {
//	    	if (N.get(i).getHtype().equals("n")) {
//	    		System.out.print(N.get(i).getHname()+" ");
//	    	}
//	    } 
//	} // PrintNCard e
//	// 형용사 힌트 출력 메소드 ( 신예지 ) ------------------------------------------------------
//	public void PrintACard( int intA ) { // PrintACard s
//		ArrayList<AdminInfoDto> A = new ArrayList<>();
//		if( intA == 1 ) 	{A = tno_01;} 
//		else if( intA == 2 ){A = tno_02;}
//		else if( intA == 3 ){A = tno_03;}
//		for ( int i = 0; i<A.size(); i++) {
//	    	if (A.get(i).getHtype().equals("a")) {
//	    		System.out.print(A.get(i).getHname()+" ");
//	    	}
//	    } 
//	}// PrintACard e
	// -------------------------------------------------------------------
	// 삭제하기 ( 신예지 ) -------------------------------------------------------------------
	// 카드 삭제하기 -------------
	public boolean deleteCard(String c) {
		return AdminInfoDao.getInstance().deleteCard(c);
	} // deleteCard e
	// 힌트 삭제하기 -------------
	public boolean deleteHint(String h) {
		return AdminInfoDao.getInstance().deleteHint(h);
	} // deleteCard e
	
}
