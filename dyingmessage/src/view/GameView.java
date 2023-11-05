package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import controller.GameController;
import model.dao.GameDao;
import model.dto.GameDto;

public class GameView {
	// 싱글톤 생성
	private static GameView gameView = new GameView();

	public static GameView getInstance() {
		return gameView;
	}

	private GameView() {
	}
	public static final String FONT_WHITE = "\u001B[37m";  //흰색
	public static final String FONT_CYAN = "\u001B[36m"; // 폰트색
	public static final String FONT_YELLOW = "\u001B[33m"; //노란색
	// 게임 카드 출력
	public void gameStart() {
		System.out.println("게임스타트시작");
		ArrayList<GameDto> result = GameController.getInstance().sampleCard();

		System.out.println(FONT_CYAN+"용의자 카드 => " + GameController.getInstance().humanCard);
		System.out.println(FONT_WHITE+"용의자 힌트 : " + GameController.getInstance().hintRound1.get(0));
		System.out.println();
		System.out.println(FONT_CYAN+"도구 카드 => " + GameController.getInstance().itemCard);
		System.out.println(FONT_WHITE+"도구 힌트 : " + GameController.getInstance().hintRound1.get(2));
		System.out.println();
		System.out.println(FONT_CYAN+"동기 카드 => " + GameController.getInstance().motiveCard);
		System.out.println(FONT_WHITE+"동기 힌트 : " + GameController.getInstance().hintRound1.get(4));
		System.out.println();
		System.out.println("정답카드 : " + GameController.getInstance().answerCard);
		// System.out.println("힌트 : "+ GameController.getInstance().hintRandom());
		inputCard();
		
	}

	// 2라운드 출력
	public void printCardNames2() {
		System.out.println("-------- 라운드2 -----------");
		System.out.println(FONT_CYAN+"용의자 카드 => " + GameController.getInstance().humanCard);
		System.out.println(FONT_WHITE+"용의자 힌트 : " + GameController.getInstance().hintRound1.get(0));
		System.out.println("");
		System.out.println(FONT_CYAN+"도구 카드 => " + GameController.getInstance().itemCard);
		System.out.println(FONT_WHITE+"도구 힌트 : " + GameController.getInstance().hintRound1.get(2));
		System.out.println("");
		System.out.println(FONT_CYAN+"동기 카드 => " + GameController.getInstance().motiveCard);
		System.out.println(FONT_WHITE+"동기 힌트 : " + GameController.getInstance().hintRound1.get(4));
		System.out.println("");
		System.out.println("정답카드 : " + GameController.getInstance().answerCard);
		String printHintBonus2 = GameController.getInstance().hintRandomAdd(); // 랜덤으로 뽑은 보너스 힌트를 저장하고있음
		System.out.println("2라운드 보너스 힌트 - "+hint2split() + printHintBonus2); // 보너스힌트 출력
		inputCard();
	}

	// 3라운드 출력
	public void printCardNames3() {
		System.out.println("-------- 라운드3 ----------");
		System.out.println(FONT_CYAN+"용의자 카드 => " + GameController.getInstance().humanCard);
		System.out.println(FONT_WHITE+"용의자 힌트 : " + GameController.getInstance().hintRound1.get(0));
		System.out.println("");
		System.out.println(FONT_CYAN+"도구 카드 => " + GameController.getInstance().itemCard);
		System.out.println(FONT_WHITE+"도구 힌트 : " + GameController.getInstance().hintRound1.get(2));
		System.out.println("");
		System.out.println(FONT_CYAN+"동기 카드 => " + GameController.getInstance().motiveCard);
		System.out.println(FONT_WHITE+"동기 힌트 : " + GameController.getInstance().hintRound1.get(4));
		System.out.println("");
		System.out.println("정답카드 : " + GameController.getInstance().answerCard);
		String printHintBonus3 = GameController.getInstance().hintRandomAdd(); // 랜덤으로 뽑은 보너스 힌트를 저장하고있음
		System.out.println("3라운드 보너스 힌트 - "+hint2split() + printHintBonus3); // 보너스힌트 출력
		inputAnswer();
	}
		
	
	// 2,3라운드 뽑은 추가 힌트 분류해서 리턴해주는 함수
	public String hint2split() {
		int result = GameController.getInstance().hint2split();
		if( result == 1 || result == 2) {
			return "범인 추가 힌트 : ";
		} else if( result == 3 || result == 4) {
			return "도구 추가 힌트 : ";
		} else if( result == 5 || result == 6) {
			return "동기 추가 힌트 : ";
		}
		return null;
	}

	// 상태체크하는 배열 
	public boolean[] checkWord = {false, false, false, false};
	
	public void inputCard() {
		Scanner scanner = new Scanner(System.in);
	    for (int i = 0; i < 4; i++) {
	        System.out.print("제외할 카드 이름을 입력해주세요 " + (i + 1) + ": ");
	        String cardName = scanner.nextLine();
	        //System.out.println("게임뷰 cardName : "+cardName);
	        boolean isvalidCard = GameController.getInstance().isValidCard(cardName);
	        //System.out.println("게임뷰 isvalidCard : "+isvalidCard);
	        if (isvalidCard) {
	            GameController.getInstance().cardNames[i]= cardName;
	            checkWord[i] = true;
	            //System.out.println("게임뷰 카드네임 :"+GameController.getInstance().cardNames[i]);
	        }else {
	            System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
	            i--; // 유효하지 않은 입력을 받았을 때는 다시 같은 인덱스로 입력받도록 하여 재입력을 유도
	        } 
	        
	    }
	    
	    System.out.println(Arrays.toString(GameController.getInstance().cardNames)); // 배열 안 값 확인
	    GameController.getInstance().judge();
	    System.out.println(GameController.getInstance().judge());
	    GameController.getInstance().exceptCard();
	}
	
	public void inputAnswer() {
		// 사용자로부터 정답 카드를 입력받아 인덱스로 DTO에 String[] answers 배열에 저장
		Scanner scanner = new Scanner(System.in);
		System.out.println("정답은 용의자 , 도구 , 동기 순으로 입력해주세요.");
		for (int i = 0; i < 3; i++) {
			System.out.print("정답 카드를 입력해주세요 " + (i + 1) + ": ");
			String cardName = scanner.nextLine();
			boolean isvalidCard = GameController.getInstance().isValidCard2(cardName);
			if (isvalidCard) {
				GameController.getInstance().answers[i] = cardName;
	        } else {
	            System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
	            i--; // 유효하지 않은 입력을 받았을 때는 다시 같은 인덱스로 입력받도록 하여 재입력을 유도
	        }
			System.out.println();
		}

		//System.out.println(Arrays.toString(getInstance().answers)); // 배열 안 값 확인
		GameController.getInstance().judgeAnswer();
		GameController.getInstance().checkAnswer();
	}

}