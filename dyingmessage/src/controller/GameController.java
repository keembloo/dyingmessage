package controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import model.dao.GameDao;
import model.dto.GameDto;
import view.GameView;
import view.MainView;

public class GameController {
	// 싱글톤 생성
	private static GameController gameController = new GameController();

	public static GameController getInstance() {
		return gameController;
	}

	private GameController() {
	}

	// 사용자에게 입력받은 카드 저장하는 곳
	public String[] cardNames = new String[4];
	// 정답 입력받아 저장하는곳
	public String[] answers = new String[3];
	// 목숨 변수
	public int heart = 3;
	// 라운드 변수
	public int round = 1;

	// 범인 , 도구 , 동기 보기 카드 7개
	public ArrayList<String> humanCard = new ArrayList<>();
	public ArrayList<String> itemCard = new ArrayList<>();
	public ArrayList<String> motiveCard = new ArrayList<>();

	// 정답 카드 배열
	public ArrayList<String> answerCard = new ArrayList<>();

	// 정답 카드의 용의자 힌트 배열
	public ArrayList<String> humanhintA = new ArrayList<>();
	public ArrayList<String> humanhintN = new ArrayList<>();

	// 정답 카드의 도구 힌트 배열
	public ArrayList<String> itemhintA = new ArrayList<>();
	public ArrayList<String> itemhintN = new ArrayList<>();

	// 정답 카드의 동기 힌트 배열
	public ArrayList<String> motivehintA = new ArrayList<>();
	public ArrayList<String> motivehintN = new ArrayList<>();

	// 정답 카드의 1라운드 출력용 힌트 배열
	public ArrayList<String> hintRound1 = new ArrayList<>();

	// 정답 카드의 2,3라운드 추가 힌트 배열
	public ArrayList<String> hintRound2 = new ArrayList<>();
	
	// 승패 유무 저자하는 변수
	boolean victory = false;
	
	// 정답 결과 저장하는 변수 파일처리보낼거
	String gameresult = "";
	
	
	// 0.보기 카드 7장씩 뽑기
	public ArrayList<GameDto> sampleCard() {
		//System.out.println("컨트롤러연동");
		ArrayList<GameDto> result = GameDao.getInstance().sampleCard();
		//System.out.println("컨트롤러 result : "+result);
		for (int i = 0; i < result.size(); i++) {
			// System.out.println("리절트 겟 3 : "+result.get(i).getCname());
			if (result.get(i).getTno() == 1) { // tno 1이면 용의자 카드배열에 넣기
				humanCard.add(result.get(i).getCname());
			} else if (result.get(i).getTno() == 2) { // tno 2이면 도구 카드배열에 넣기
				itemCard.add(result.get(i).getCname());
			} else if (result.get(i).getTno() == 3) { // tno 3이면 동기 카드배열에 넣기
				motiveCard.add(result.get(i).getCname());
			}
		} // for end
		answerRandom(); // 정답 랜덤 뽑기 함수 실행
		hintSplit(); // 힌트 분류 함수 실행
		hintRandom(); // 1라운드 힌트 뽑기 함수 실행
		//System.out.println("1범인카드"+ GameDto.getHumanCard());
		//System.out.println("2도구카드"+ GameDto.getItemCard());
		//System.out.println("3동기카드"+ GameDto.getMotiveCard());
		return result;
	}

	// 1.정답 카드 난수뽑기
	public ArrayList<String> answerRandom() {
		for (int i = 1; i <= 3; i++) {
			int random = (int) ((Math.random() * 10000) % 7);
			if (i == 1) { // 범인카드 랜덤뽑아서 정답 배열에 넣기
				answerCard.add(humanCard.get(random));
				//System.out.println("컨트롤러 랜덤 : " +random);
			} else if (i == 2) { // 도구카드 랜덤뽑아서 정답 배열에 넣기
				answerCard.add(itemCard.get(random));
			} else if (i == 3) { // 동기카드 랜덤뽑아서 정답 배열에 넣기
				answerCard.add(motiveCard.get(random));
			}
		} // for end
			// System.out.println(answerCard);
		return answerCard;
	}
	
	// 2. 1라운드 힌트분류
	public void hintSplit() {
		for (int i = 0; i < answerCard.size(); i++) { // 정답카드의 힌트들만 불러오기
			String hintSample = answerCard.get(i); // 정답카드 객체의 0,1,2번째 인덱스
			//System.out.println("게임컨트롤러 hintSplit함수 hintSample" + hintSample);
			ArrayList<GameDto> result = GameDao.getInstance().hintSplit(hintSample);
			// System.out.println("리절트"+result.get(0)); // [cardNames=[null, null, null,
			// null], tno=1, cno=1, cname=가수, hno=1, hname=입, htype=n]
			// System.out.println(result.get(i).getHtype().equals("n"));
			ArrayList<String> humanA = humanhintA;
			ArrayList<String> humanN = humanhintN;
			ArrayList<String> itemA = itemhintA;
			ArrayList<String> itemN = itemhintN;
			ArrayList<String> motiveA = motivehintA;
			ArrayList<String> motiveN = motivehintN;

			for (int j = 0; j < result.size(); j++){
				// System.out.println("리절트 겟 제이 : " +result.get(j));
				if (result.get(i).getCno() == 1 && result.get(j).getHtype().equals("a")) { // 카테고리1번이면서 힌트타입 a면 용의자형용사																	// 배열에 추가
					humanA.add(result.get(j).getHname());
				} else if (result.get(i).getCno() == 1 && result.get(j).getHtype().equals("n")) {
					humanN.add(result.get(j).getHname());
				}
				if (result.get(i).getCno() == 2 && result.get(j).getHtype().equals("a")) {
					itemA.add(result.get(j).getHname());
				} else if (result.get(i).getCno() == 2 && result.get(j).getHtype().equals("n")) {
					itemN.add(result.get(j).getHname());
				}
				if (result.get(i).getCno() == 3 && result.get(j).getHtype().equals("a")) {
					motiveA.add(result.get(j).getHname());
				} else if (result.get(i).getCno() == 3 && result.get(j).getHtype().equals("n")) {
					motiveN.add(result.get(j).getHname());
				}
			} // for2 end

			// 라운드2,3에 쓰일 힌트 배열 미리 넣어두기
			for ( int k=0; k<result.size();k++) {
				hintRound2.add(result.get(k).getHname());
			} // for 3 end
		} // for end

		//System.out.println("humanhintA : " + GameDto.getHumanhintA());
		//System.out.println("humanhintN : " + GameDto.getHumanhintN());
		//System.out.println("itemhintA : " + GameDto.getItemhintA());
		//System.out.println("itemhintN : " + GameDto.getItemhintN());
		//System.out.println("motivehintA : " + GameDto.getMotivehintA());
		//System.out.println("motivehintN : " + GameDto.getMotivehintN());
	}
	
	// 1라운드 힌트 난수뽑기
	public void hintRandom() {
		ArrayList<String> hintR1 = hintRound1;
		// 힌트 형용사, 명사 1개씩 뽑기
		int random = (int) ((Math.random() * 10000) % (humanhintA.size()));
		//System.out.println("컨트롤러 랜덤 : "+random);
		hintR1.add(humanhintA.get(random));
		random = (int) ((Math.random() * 10000) % (humanhintN.size()));
		hintR1.add(humanhintN.get(random));
		random = (int) ((Math.random() * 10000) % (itemhintA.size()));
		hintR1.add(itemhintA.get(random));
		random = (int) ((Math.random() * 10000) % (itemhintN.size()));
		hintR1.add(itemhintN.get(random));
		random = (int) ((Math.random() * 10000) % (motivehintA.size()));
		hintR1.add(motivehintA.get(random));
		random = (int) ((Math.random() * 10000) % (motivehintN.size()));
		hintR1.add(motivehintN.get(random));
	}
	
	static String hintBonus = "";
	// 2,3라운드 추가 힌트 뽑는 함수
	public String hintRandomAdd() {
		
		int random = (int) ((Math.random() * 10000) % 3);
		//System.out.println("hintRandomAdd : "+random);
	
		if(random ==0) {
			int random2 = (int) ((Math.random() * 10000) % (humanhintN.size()));
			//System.out.println("인간힌트배열 : "+random2);
			//System.out.println("인간힌트배열 그 값 : "+GameDto.getHumanhintN().get(random2));
			hintBonus = (humanhintN.get(random2));
		} else if (random ==1) {
			int random2 = (int) ((Math.random() * 10000) % (itemhintN.size()));
			//System.out.println("도구힌트배열 : "+random2);
			//System.out.println("도구힌트배열 그 값 : "+GameDto.getItemhintN().get(random2));
			hintBonus = (itemhintN.get(random2));
		}else if (random ==2) {
			int random2 = (int) ((Math.random() * 10000) % (motivehintN.size()));
			//System.out.println("동기힌트배열 : "+random2);
			//System.out.println("동기힌트배열 그 값 : "+GameDto.getMotivehintN().get(random2));
			hintBonus = motivehintN.get(random2);
		}
		return hintBonus;
	}
	
	// 2,3라운드 뽑은 추가 힌트 분류해서 리턴해주는 함수
	public int hint2split() {
		String result = hintBonus; // 랜덤으로 뽑은 힌트 변수 불러와서 분류
		//System.out.println("힌트두번째랜덤" +result);
		for ( int i =0; i<humanhintA.size(); i++) {
			if(result.equals(humanhintA.get(i))){ // 만약에 힌트2와 힌트A에 있으면...
				return 1; // 추가힌트는 범인 형용사 힌트임
			}
		}
		for ( int i =0; i<humanhintN.size(); i++) {
			if(result.equals(humanhintN.get(i))){ // 만약에 힌트2와 힌트N에 있으면...
				return 2; // 추가힌트는 범인 명사 힌트임
			}
		}
		for ( int i =0; i<itemhintA.size(); i++) {
			if(result.equals(itemhintA.get(i))){ // 만약에 힌트2와 힌트A에 있으면...
				return 3; // 추가힌트는 도구 형용사 힌트임
			}
		}
		for ( int i =0; i<itemhintN.size(); i++) {
			if(result.equals(itemhintN.get(i))){ // 만약에 힌트2와 힌트N에 있으면...
				return 4; // 추가힌트는 도구 명사 힌트임
			}
		}
		for ( int i =0; i<motivehintA.size(); i++) {
			if(result.equals(motivehintA.get(i))){ // 만약에 힌트2와 힌트A에 있으면...
				return 5; // 추가힌트는 동기 형용사 힌트임
			}
		}
		for ( int i =0; i<motivehintN.size(); i++) {
			if(result.equals(motivehintN.get(i))){ // 만약에 힌트2와 힌트N에 있으면...
				return 6; // 추가힌트는 동기 명사 힌트임
			}
		}
		return 0; // 아무것도 없으면 걍 0반환
	}
	
		
	// 3. 목숨 차감 함수
	public int countHeart() {
		int 남은목숨 = heart; // 현재의 heart 값을 가져옴
		남은목숨 -= 1; // 1을 감소시킴
		heart = 남은목숨;
		System.out.println("남은 목숨 :" + 남은목숨);
		if (남은목숨 == 0) {
			System.out.println("남은 목숨이 없습니다. - GAME OVER - ");
			victory = false;
			gameEnd();
		}
		return 남은목숨;
	}
	
	
	// 4. 제외 판단 함수
	public void exceptCard() {
		String[] cardNames = getInstance().cardNames;
		boolean result = judge(); //

		if (result) {
			removeCardsByNames();
			int 라운드 = round;
			라운드 += 1; // 라운드 1 증가
			round = 라운드;
			System.out.println("라운드횟수 : "+라운드);
			if( 라운드 == 2 ) { GameView.getInstance().printCardNames2();}
			if( 라운드 == 3 ) { GameView.getInstance().printCardNames3();}
			String printHintBonus = hintRandomAdd(); // 랜덤으로 뽑은 보너스 힌트를 저장하고있음
			System.out.println(hint2split() + printHintBonus); // 보너스힌트 출력
			
		} else {
			System.out.println("사용자가 입력한 카드와 정답카드에 일치하는 카드가 있습니다.");
			countHeart();
			// 사용자에게 입력받는 cardNames 배열 초기화
	         String[] emptyCardNames = new String[cardNames.length];
	         cardNames = emptyCardNames;
	         GameView.getInstance().inputCard();
		}
	}


	// 카드 제외 함수 
	public void removeCardsByNames() {
	    String[] cardNames = getInstance().cardNames;

	    for (String cardName : cardNames) {
	        if (humanCard.contains(cardName)) {
	        	humanCard.remove(cardName);
	        }
	        if (itemCard.contains(cardName)) {
	        	itemCard.remove(cardName);
	        }
	        if (motiveCard.contains(cardName)) {
	        	motiveCard.remove(cardName);
	        }
	    }

	    // 사용자에게 입력받는 cardNames 배열 초기화
	    String[] emptyCardNames = new String[cardNames.length];
	    cardNames=emptyCardNames;
	}

	// 제외 가능한지 판단 함수 
	public boolean judge() {
		for (String userCard : cardNames) {
			for (String answerCard : answerCard) {
				if (userCard.equals(answerCard)) {

					return false; // 사용자가 입력한 카드와 정답카드에 일치하는 카드가 있으면 False 반환
				}
			}
		}
		return true; // 일치하는 카드가 없으면 True 반환
	}
	
	
	
	
	// 정답 판단 함수
	public boolean judgeAnswer() {
		//String[] answer = getInstance().answers;
		boolean allMatch = true; 
	    for ( int i = 0 ; i< answerCard.size() ; i++ ) {
	       // String answer = getInstance().answers[i];
	        	//if ( !answer.equals( getInstance().answerCard.get(i) ) ) {
	            if ( !answers[i].equals(answerCard.get(i)) ) {
	            	//System.out.println(정답.get(i));
	                allMatch = false;
	                break; // 하나라도 일치하지 않으면 루프 중단
	            }
	    }
        if (allMatch) {
            return true; // 사용자가 입력한 카드와 정답카드 모두 일치하는 경우
        }

	    return false; // 모든 가능한 정답 카드와 비교 후에도 일치하지 않는 경우
	}

	// 정답 확인 함수
	public void checkAnswer() {
		boolean result = judgeAnswer();

		if (result) {
			System.out.println("정답 입니다! 게임에서 승리하셨습니다.");
			victory = true;
			gameEnd();

		} else {
			System.out.println("오답 입니다. 정답을 다시 입력하세요.");
			victory = false;
			countHeart();
			
			// 정답 입력하는 배열 비워주기 
			String[] answers = getInstance().answers;
			String[] emptyAnswers = new String[answers.length];
			answers = emptyAnswers;
		    getInstance().answers= answers; 
			GameView.getInstance().inputAnswer();
		}
	}
	 
	// 유효성 검사 함수
	public boolean isValidCard(String cardName) {
		//System.out.println("-----------게임컨트롤러 cardName : "+cardName);
		if (cardName == null) {
			return false;
		}
		// GameDto에 속하는지 검사
		boolean isValidDtoCard = humanCard.contains(cardName) || itemCard.contains(cardName)
				|| motiveCard.contains(cardName);
		
		// 이미 정답을 모두 입력한상태면 초기화 해줘야함
		boolean nullState = false;
		
		for (String findNull : cardNames) {
		    if (findNull == null) {
		    	nullState = true;
		        break; // null 값이 하나라도 발견되면 포문나가기
		    }
		}

		if (!nullState) {
		    // null 값이 없을 때 배열 모든 값을 null로 초기화
		    for (int i = 0; i < cardNames.length; i++) {
		        cardNames[i] = null;
		    }
		}
		// cardNames 배열에 포함되어있는지 검사
		boolean isCardNameInArray = Arrays.asList(cardNames).contains(cardName);
		// 두 조건 모두 만족해야 유효한 카드로 판단
		//System.out.println("-----------게임컨트롤러 isValidDtoCard : "+isValidDtoCard);
		//System.out.println("-----------게임컨트롤러 isCardNameInArray : "+isCardNameInArray);
		//System.out.println("-----------게임컨트롤러 cardNames 배열[0] : "+cardNames[0]);
		//System.out.println("-----------게임컨트롤러 cardNames 배열[1] : "+cardNames[1]);
		//System.out.println("-----------게임컨트롤러 cardNames 배열[2] : "+cardNames[2]);
		//System.out.println("-----------게임컨트롤러 cardNames 배열[3] : "+cardNames[3]);
		return isValidDtoCard && !isCardNameInArray;
	}
	 

	 
	// 정답 유효성 검사 함수
	public boolean isValidCard2(String cardName) {
		if (cardName == null) {
			return false;
		}
		// GameDto에 속하는지 검사
		boolean isValidDtoCard = humanCard.contains(cardName) || itemCard.contains(cardName)
				|| motiveCard.contains(cardName);
		// cardNames 배열에 포함되어있는지 검사
		boolean isCardNameInArray2 = Arrays.asList(answers).contains(cardName);
		// 두 조건 모두 만족해야 유효한 카드로 판단
		return isValidDtoCard && !isCardNameInArray2;
	}
	

	
	// 게임실행 날짜 , 남은목숨 , 승패유무 
	public String gameRank() {
		LocalDateTime nowTime = LocalDateTime.now();	// 현재시간구하기
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");	// 포멧지정
		String formattedTime = nowTime.format(formatter);	// 형식적용
		
		if(victory) {
			gameresult = "게임 시간 : "+formattedTime+" | 남은 목숨 : " + heart + " | 결과 : 승리\n";
		} else {
			gameresult = "게임 시간 : "+formattedTime+" | 남은 목숨 : " + heart + " | 결과 : 패배\n";
		}
		return gameresult;
		
	}
	
	// 게임 종료 함수
	public void gameEnd() {
		gameRank();
		System.out.println("초기화면으로 돌아갑니다.");
		heart =3;
		Arrays.fill(getInstance().cardNames, null);
		Arrays.fill(getInstance().answers, null);
		humanCard.clear();
		itemCard.clear();
		motiveCard.clear();
		answerCard.clear();
		humanhintA.clear();
		humanhintN.clear();
		itemhintA.clear();
		itemhintN.clear();
		motivehintA.clear();
		motivehintN.clear();
		hintRound1.clear();
		round = 1;
		
		// 파일 처리 클래스를 사용하여 결과를 파일에 저장
		File.saveResult(gameresult);
	}


}