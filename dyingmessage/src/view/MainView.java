package view;

import java.util.Scanner;


public class MainView {
	// 싱글톤 생성 
	private static MainView mainView = new MainView();
	public static MainView getInstance() { return mainView; }
	private MainView() {}
	
	Scanner sc = new Scanner(System.in);
	
	//게임로고, 메뉴출력
	// 귤     
	/*
	public static final String RESET = "\u001B[0m";    
	public static final String FONT_BLACK = "\u001B[30m";    
	public static final String FONT_RED = "\u001B[31m";     
	public static final String FONT_GREEN = "\u001B[32m";    
	public static final String FONT_BLUE = "\u001B[34m";   
	public static final String FONT_PURPLE = "\u001B[35m";     
	public static final String FONT_CYAN = "\u001B[36m";    
	public static final String BACKGROUND_BLACK = "\u001B[40m";     
	public static final String BACKGROUND_RED = "\u001B[41m";     
	public static final String BACKGROUND_GREEN = "\u001B[42m";    
	public static final String BACKGROUND_YELLOW = "\u001B[43m";     
	public static final String BACKGROUND_BLUE = "\u001B[44m";    
	public static final String BACKGROUND_PURPLE = "\u001B[45m";     
	public static final String BACKGROUND_CYAN = "\u001B[46m"; 
	public static final String BACKGROUND_WHITE = "\u001B[47m";
	*/    
	public static final String FONT_RED = "\u001B[31m"; 
	public static final String FONT_WHITE = "\u001B[37m";     
	public static final String FONT_YELLOW = "\u001B[33m";
	// 출처 // https://kkh0977.tistory.com/958
	
	//게임로고, 메뉴출력 ---------------------------------------------------------
	public void mainPage() {
		//함수명
		System.out.println(FONT_WHITE+"┌────────────────────────────────────────────────────────────────────────────────────────┐");
		System.out.println("                                       *~ GAME ~*\n"
				+ FONT_YELLOW+"   ______ __   __ _____  _   _  _____  ___  ___ _____  _____  _____   ___   _____  _____ \r\n"
				+ "   |  _  \\\\ \\ / /|_   _|| \\ | ||  __ \\ |  \\/  ||  ___|/  ___|/  ___| / _ \\ |  __ \\|  ___|\r\n"
				+ "   | | | | \\ V /   | |  |  \\| || |  \\/ | .  . || |__  \\ `--. \\ `--. / /_\\ \\| |  \\/| |__  \r\n"
				+ "   | | | |  \\ /    | |  | . ` || | __  | |\\/| ||  __|  `--. \\ `--. \\|  _  || | __ |  __| \r\n"
				+ "   | |/ /   | |   _| |_ | |\\  || |_\\ \\ | |  | || |___ /\\__/ //\\__/ /| | | || |_\\ \\| |___ \r\n"
				+ "   |___/    \\_/   \\___/ \\_| \\_/ \\____/ \\_|  |_/\\____/ \\____/ \\____/ \\_| |_/ \\____/\\____/ \r\n"
				+ "                                                                                      \r\n"
				+ "");
		System.out.println(FONT_WHITE+"└────────────────────────────────────────────────────────────────────────────────────────┘");
		
		while(true) {
			System.out.println("--------------------------------");
			System.out.println("0.관리자모드 | 1.게임시작 | 2.게임설명\n");
			System.out.print("선택 >>> ");
			int ch = sc.nextInt();
			
			// 관리자로그인 (신예지) ----------------------------------------------------
			if(ch==0) {	
				System.out.print("아이디 :"); String id = sc.next();
				System.out.print("비밀번호 :"); int pw = sc.nextInt();
				if(id.equals("admin") && pw==1234) {adminPage();}
				else { System.out.println(FONT_RED+"경고] 관리자 정보가 일치하지 않습니다.\n"+FONT_WHITE);}
			}
			else if(ch==1) {	gamePage();}
			else if(ch==2) {	rulePage();}
			else { System.err.println(FONT_RED+"경고] 올바른 메뉴를 입력 바랍니다.\n"+FONT_WHITE);}
		}
	} // mainPage end
	
	//0.관리자모드출력  ---------------------------------------------------------
	public void adminPage() {

		while(true) {
			System.out.println("------------- >> 관리자 페이지 << -------------");
			System.out.println(" 1.카드추가| 2.카드수정 | 3.카드내역 | 4.카드삭제 | 5.돌아가기 " );
			System.out.print(" 메뉴선택 >>"); int ch = sc.nextInt();
			if(ch==1) {	AdminInfoView.getInstance().addCard();}
			if(ch==2) {	AdminInfoView.getInstance().updateCard();}
			if(ch==3) {	AdminInfoView.getInstance().viewCard();}
			if(ch==4) {	AdminInfoView.getInstance().delete();}
			if(ch==5) {	mainPage();}
		}
		
	}
	
	//1.게임시작출력 ---------------------------------------------------------
	public void gamePage() {
		System.out.println("\n\n------------- 다잉메시지 게임 시작  ------------");
		System.out.println("------------------- 시나리오 ----------------------");
		System.out.println("||           2023년 08월 21일                  	||");
		System.out.println("|| 유능한 학원강사이자 작가인 기면수(37세 추정)는      	||");
		System.out.println("|| 학원 504호 강의실에서 숨진 채 발견되었다.          	||");
		System.out.println("|| 사건 현장에서 피해자가 남겨놓은 단서들이 남아있었다.  	||");
		System.out.println("|| 누가? 어떤 도구로? 무슨 이유로 살해했을까 ..?      	||");
		System.out.println("|| 단서를 잘 조합하여 범인을 찾아보자.               	||");
		System.out.println("--------------------------------------------------");
		GameView.getInstance().gameStart();
		//return GameView.getInstance().gameStart();
		
	}
	//2.게임설명출력 ---------------------------------------------------------
	public void rulePage() {
		System.out.println("\n\n------------- 다잉메시지 게임 룰 설명 ------------");
		System.out.println("추리가가 되어서 피해자가 남긴 단서를 찾아 용의자, 살해도구 , 살인동기를 맞추면 승리하는게임입니다. ");
		System.out.println(" - 라운드는 3라운드까지 진행되며, 주어진 목숨 4개안에 정답을 맞추면 승리합니다. ");
		System.out.println(" - 목숨이 0이되면 패배합니다.");
		System.out.println("");
		System.out.println("1.전체 카드 더미에서 게임에 사용 될 용의자 7장 , 살해도구 7장 , 살인동기 7장 카드를 컴퓨터가 랜덤으로 뽑습니다.");
		System.out.println("2.뽑아낸 용의자,살해도구,살인동기 카드중에서 각각 1장씩 컴퓨터가 뽑아 임의로 정답을 생성합니다.");
		System.out.println("3.정답으로 설정된 카드에 관한 명사,형용사로 이루어진 힌트가 랜덤으로 각각 2개씩 제공됩니다.");
		System.out.println("4.힌트를 조합하여 보기 카드중에서 정답이 아닌것 같은 카드 4개를 입력받아 제외시킵니다.");
		System.out.println("5.제외한 카드중에 정답이 있을시, 목숨이 한개 차감되며, 정답이 아닌 카드 4개를 제외할때까지 제외를 반복합니다.");
		System.out.println("6.성공적으로 정답이 아닌카드 4개를 제외할 경우 , 2라운드로 넘어가며, 제외한 카드는 목록에서 사라지고, 용의자,살해도구,살인동기 중 랜덤으로 1개의 힌트가 추가로 주어집니다.");
		System.out.println("7.힌트를 조합하여 정답이 아닌것 같은 카드 4개를 제외시킵니다.");
		System.out.println("8.제외한 카드중에 정답이 있을시, 목숨이 한개 차감되며, 정답이 아닌 카드 4개를 제외할때까지 제외를 반복합니다.");
		System.out.println("9.성공적으로 정답이 아닌카드 4개를 제외할 경우 , 3라운드로 넘어가며, 제외한 카드는 목록에서 사라지고, 용의자,살해도구,살인동기 중 랜덤으로 1개의 힌트가 추가로 주어집니다.");
		System.out.println("10.힌트를 조합하여 최종적으로 정답을 맞추면 승리하며 , 오답시 목숨 한개가 차감됩니다. ");
		System.out.println("");
		System.out.print(FONT_YELLOW+"게임시작을 시작하시겠습니까?  1.예 | 2.아니오\n"+FONT_WHITE); 
		System.out.print("선택 >>> "); int ch = sc.nextInt();
		if(ch==1) {
			gamePage(); // 바로 게임 시작하기
		}
		if(ch==2) {
			mainPage(); // 메인페이지로 이동
		}
	}
		
}
