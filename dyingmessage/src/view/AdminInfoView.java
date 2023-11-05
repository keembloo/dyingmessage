package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.AdminInfoController;
import model.dto.AdminInfoDto;



public class AdminInfoView {
	// 싱글톤 생성 
	private static AdminInfoView adminInfoView = new AdminInfoView();
	public static AdminInfoView getInstance() { return adminInfoView; }
	private AdminInfoView() {}
	
	//입력객체
	Scanner sc = new Scanner(System.in);
	
	/*
	 * 작성자 : 김준희
	 * 기능 	: Controller.addCard()로 매개변수 tno(카드타입)과  cname(카드명)전달 
	 * 리턴 	: int형으로 return 받음
	 * */
	public void addCard() {
	    System.err.println(" ------------- >> 카드 등록 << -------------");
	    System.out.println("1. 카드추가 | 2.힌트추가 ");
	    System.out.print(" 선택 >>>");
	    int ch = sc.nextInt();
	    
	    if(ch ==1 ) {
	    
			System.out.println("1. 용의자 | 2 범행도구 | 3.범행동기 | 4.돌아가기");
			System.out.print(" 선택 >>>");
			int tno = 0;// 카드 타입
			try {
				tno = sc.nextInt();
				if(tno==0 || tno == 4) {return;}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.err.println(" 경고]잘못입력하셨습니다." + e); //뮨자입력시
			} 				
			System.out.println("카드명을 입력하세요");
			String cname = sc.next();				// 카드명
			//중복체크
			int isExist = AdminInfoController.getInstance().isExist(0, cname); 
			if (isExist != 0) { 
				System.err.println(MainView.FONT_RED+"경고]이미 존재하는 카드입니다. "+MainView.FONT_WHITE);
				return;
			}
			//Controller로  매개변수 tno(카드타입)과  cname(카드명)전달
			int result = AdminInfoController.getInstance().addCard(tno, cname);
			if (result == 1 ) {System.out.println("안내) 등록 성공했어요");
			
				while(true) {
					// 앞서 등록한 카드의 카드명으로 카드번호 가져오기
					int cno = AdminInfoController.getInstance().getCardNo(cname);
					
					
					System.out.println("힌트카드를 입력해주세요");
					System.out.print("1. 형용사  2.명사  3. 카드등록종료  >>선택");
					
					String htype = "";
					try {
						int hintT  = sc.nextInt();
										
						if (hintT == 1) { htype = "a"; }
						if (hintT == 2) { htype = "n"; }
						if (hintT == 3) { MainView.getInstance().adminPage(); }
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.err.println(MainView.FONT_RED+"경고] 잘못입력하셨습니다." + e+MainView.FONT_WHITE);
					}
					System.out.println("힌트의 이름을 입력하세요");
					String hname = sc.next(); 
					isExist = AdminInfoController.getInstance().isExist(cno, hname); 
					if (isExist !=0) { 
						System.err.println(MainView.FONT_RED+"경고] 이미 존재하는 카드입니다. "+MainView.FONT_WHITE);
						return;
					}
					int hResult = AdminInfoController.getInstance().addHint(htype,hname,cno);
					if( hResult == 1) {System.out.println("안내)힌트 등록 완료");}
					if( hResult == 2) {System.err.println(MainView.FONT_RED+"경고]힌트 등록 실패"+MainView.FONT_WHITE);}
				}				
			} //if end
			if (result == 2 ) {System.err.println(MainView.FONT_RED+"등록실패 : 관리자에게 문의"+MainView.FONT_WHITE);}
			if (result == 3 ) {System.err.println(MainView.FONT_RED+"등록실패 : 이미 등록된 카드입니다."+MainView.FONT_WHITE);}
	    } else if( ch == 2) {
	    	addHint();
	    } else { System.out.println(MainView.FONT_RED+"경고] 잘못된 입력입니다."+MainView.FONT_WHITE);}
	}	
	
	public void addHint() {
		System.out.println("힌트가 추가될 카드명을 입력하세요");
		System.out.println(" 입력 >>>");
		String cname = sc.next();
		
		//카드존재 여부체크 , return값은 cno
		int cno = AdminInfoController.getInstance().isExist(0, cname); 
		
		// 존재하지 않는 카드이면 리턴
		if (cno == 0) { 
			System.err.println(MainView.FONT_RED+"경고] 등록된 카드가 아닙니다.. "+MainView.FONT_WHITE);
			return;
		}
		
		while(true) {
			//존재하는 카드이면 힌드 등록을 진행함
			System.out.println("힌트카드를 입력해주세요");
			System.out.print("1. 형용사  2.명사  3. 카드등록종료  >>선택");
			
			String htype = "";
			try {
				int hintT  = sc.nextInt(); //힌트가 형용사인지 명사인지. 
								
				if (hintT == 1) { htype = "a"; }
				if (hintT == 2) { htype = "n"; }
				if (hintT == 3) { MainView.getInstance().adminPage(); }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.err.println(MainView.FONT_RED+"경고] 잘못입력하셨습니다." + e +MainView.FONT_WHITE);
			}
			System.out.println("힌트의 이름을 입력하세요");
			String hname = sc.next(); 
			
			//힌트의 중복체크
			int isExist = AdminInfoController.getInstance().isExist(cno, hname);
			if (isExist !=0) { 
				System.err.println(MainView.FONT_RED+"경고] 이미 존재하는 카드입니다. "+MainView.FONT_WHITE);
			}
			int hResult = AdminInfoController.getInstance().addHint(htype,hname,cno);
			if( hResult == 1) {System.out.println("안내)힌트 등록 완료");}
			if( hResult == 2) {System.err.println(MainView.FONT_RED+"경고]힌트 등록 실패"+MainView.FONT_WHITE);}
		}
		
	}
	public void updateCard() {
		
		
	    System.out.println(" ------------- >> 카드 수정  << -------------");
		System.out.println("수정할 카드명을 입력하세요  예시: 가수 ");
	    System.out.println(" 입력>>>");
	    String cname = sc.next();
	    
	    // 카드 존재여부 반환 : 0 = 존재하지 않음 / 0보다 큰경우 : 존재하는 카드
	    int cno = AdminInfoController.getInstance().isExist(0, cname); 
	    if(cno == 0) {
	        	System.err.println(MainView.FONT_RED+" 경고] 등록된 카드명이 아닙니다."+MainView.FONT_WHITE);
	        	return;
	        	
	    } else {
	    	System.out.println("1. 카드명수정  2. 힌트명수정");
	    	System.out.println(" 선택>>>");
	    	int ch = sc.nextInt();
	    	
	    	if (ch == 1) {// 카드명 수정
	    		System.out.println("기존의 카드명 : "  + cname );
	    		System.out.print("변경할 카드명 : >> ");
	    		String changeName = sc.next();
	    		
	    		//첫번째 매개변수 hno = 0 카드명수정 / hno > 0 힌트명수정 
	    		boolean result = AdminInfoController.getInstance().updateCard(0, cno, changeName);
	    		
	    		if(result) {
	    			System.out.println("안내) 카드명이 수정되었습니다.");
	    		} else {
	    			System.err.println(MainView.FONT_RED+"경고] 카드명이 수정실패 "+MainView.FONT_WHITE);
	    		}    		
	    	} else if(ch ==2) { //힌트명수정
	    		
	    		System.out.println("수정할 힌트명을 입력하세요  예시: 부드러운 ");
	    	    System.out.println(" 입력>>>");
	    	    String hname = sc.next();
	    	   // 힌트 존재여부 반환 : 0 = 존재하지 않음 / 0보다 큰경우 : 존재하는 카드
	    	   int hno = AdminInfoController.getInstance().isExist(cno, hname); 
	    	   
	    	   if(hno == 0) {
	    		   System.err.println(MainView.FONT_RED+"경고] 존재하지 않는 힌트입니다."+MainView.FONT_WHITE);
	    		   return;
	    	   } else {
	    		   	System.err.println(MainView.FONT_RED+"타입은 변경되지 않습니다. 주의바랍니다.!!"+MainView.FONT_WHITE);
	    		    System.out.println("기존의 힌트명 : "  + hname );
		    		System.out.print("변경할 카드명 : >> ");
		    		String changeName = sc.next();
		    		
		    		boolean result = AdminInfoController.getInstance().updateCard(hno, cno, changeName);
		    		if(result) {
		    			System.out.println("안내) 힌트명이 수정되었습니다.");
		    		} else {
		    			System.err.println(MainView.FONT_RED+"경고] 힌트명이 수정실패 "+MainView.FONT_WHITE);
		    		} 
	    	   }
	    		
	    	}
	    	
	    }
	    
		
	}
	
	
	// -------------------------------------------------------------------
	// 카드 내역 출력하기 ( 신예지 ) -------------------------------------------------------------------
	public void viewCard() {
		
		AdminInfoController.getInstance().viewHint();
		AdminInfoController.getInstance().viewCard();
		
		
	    System.out.println(" ------------- >> 카드 내역 확인 << -------------");
	    System.out.println(MainView.FONT_YELLOW+" \n■■■■■■■■■■■■■■■■■■■■■■■ 범인카드 ■■■■■■■■■■■■■■■■■■■■■■■ "+MainView.FONT_WHITE);
		AdminInfoController.getInstance().printCard(1); // 카드 출력 메소드 호출
		System.out.println(MainView.FONT_YELLOW+" \n■■■■■■■■■■■■■■■■■■■■■■■ 도구카드 ■■■■■■■■■■■■■■■■■■■■■■■ "+MainView.FONT_WHITE);
		AdminInfoController.getInstance().printCard(2); // 카드 출력 메소드 호출
		System.out.println(MainView.FONT_YELLOW+" \n■■■■■■■■■■■■■■■■■■■■■■■ 동기카드 ■■■■■■■■■■■■■■■■■■■■■■■ "+MainView.FONT_WHITE);
		AdminInfoController.getInstance().printCard(3); // 카드 출력 메소드 호출
		System.out.print("\n\n" ); // 줄 바꿈용
	}

	//-------------------------------------------------------------------
	// 카드/힌트 삭제하기 ( 신예지 ) -------------------------------------------------------------------
	public void delete() {
		System.out.println(" ------------- >> 카드/힌트 삭제 하기 << -------------"); 	
		System.out.println(" 1. 카드삭제하기 | 2. 힌트삭제하기"); 
		System.out.print(" 선택 >> "); int ch = sc.nextInt(); 
		// 카드 삭제하기 ---------------------
		if( ch == 1 ) {
			System.out.print(" 삭제할 카드 이름 입력 >> "); String c = sc.next();
			System.err.println(MainView.FONT_RED+" 경고] 해당 카드를 삭제 하시면 카드에 속한 모든 힌트도 함께 삭제됩니다. ");
			System.out.println(MainView.FONT_WHITE+" 1. 삭제하기 | 2. 취소하기"); 
			System.out.print(" 선택 >> ");  int chD = sc.nextInt(); 
			if ( chD == 1 ) {
			// 컨트롤러에게 전달하기 ( 완료되면 true 반환 실패하면 false 반환 )
			boolean r = AdminInfoController.getInstance().deleteCard( c );
			// 안내 메세지 출력하기
			if(r) { System.out.println(" 안내) ["+c+"] 카드 삭제완료 ");} 
			else { System.err.println(MainView.FONT_RED+" 경고] 카드 삭제 실패 : 관리자 문의");}
			} else { MainView.getInstance().adminPage(); }
			
		}
		// 힌트 삭제하기 ---------------------
		else if( ch == 2) {
			// 안내 메세지 출력하기
			System.out.print(MainView.FONT_WHITE+"삭제할 힌트 이름 입력 >> "); String h = sc.next();
			// 컨트롤러에게 전달하기 ( 완료되면 true 반환 실패하면 false 반환 )
			boolean r = AdminInfoController.getInstance().deleteHint( h );
			// 안내 메세지 출력하기
			if(r) { System.out.println(" 안내) ["+h+"] 힌트 삭제완료 ");} 
			else { System.err.println(MainView.FONT_RED+" 경고] 카드 삭제 실패 : 관리자 문의");
				  System.err.println(MainView.FONT_WHITE+"");}
			        
			}

	} // deleteCard e
	
} // AdminInfoView end
