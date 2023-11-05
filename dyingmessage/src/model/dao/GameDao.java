package model.dao;

import java.util.ArrayList;

import model.dto.GameDto;

public class GameDao extends Dao {
		// 싱글톤 생성 
		private static GameDao gameDao = new GameDao();
		public static GameDao getInstance() { return gameDao; }
		private GameDao() {}
		
		// 0.보기 카드 7장씩 뽑기
		public ArrayList<GameDto> sampleCard( ) { 
			ArrayList<GameDto> list = new ArrayList<>(); // 여러개 dto를 저장할 리스트 객체 선언
			try {
				for ( int i = 1; i<=3; i++) {
					String sql = " select * from card where tno = "+i+" order by rand() limit 7;"; // sql문에서 난수로 뽑아올꺼?
					ps = conn.prepareStatement(sql);
					rs = ps.executeQuery(); // true 값으로 받아옴
					while (rs.next()) { // 하나씩 넘겨가면서 반복문
						// System.out.println(rs.getString(3));
						GameDto dto = new GameDto(
								rs.getInt(1), rs.getInt(2) , rs.getString(3));
						// System.out.println("ddd : " +dto);
						list.add(dto);
					}
				} // for end
			}catch (Exception e) {
				System.out.println(e);
			}
			//System.out.println("게임다오 보기카드7장 : "+list);
			return list;
		}
		// 1.정답 난수뽑기
		// public void answerRandom() {} 
		// 2. 힌트 난수뽑기
		public ArrayList<GameDto> hintSplit(String hint1) {
			ArrayList<GameDto> list = new ArrayList<>();
			try {
				String sql = "select * from card natural join hint where cname ='"+hint1+"'";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
					GameDto dto = new GameDto(
							rs.getInt(1), rs.getInt(2) , rs.getString(3), 
							rs.getInt(4), rs.getString(5), rs.getString(6));
					list.add(dto);
					//System.out.println("힌트 난수뽑기 : "+list);
				}
			}catch (Exception e) {
				System.out.println(e);
			}
			//System.out.println(list);
			return list;
		}
		// 3. 목숨 차감
		public void countHeart() {}
		// 4. 제외하는 함수
		public void exceptCard() {}
		// 5. 정답 판단 함수
		public void judge() {}
}
