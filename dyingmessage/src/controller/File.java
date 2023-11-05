package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import view.MainView;

public class File {
	public static void saveResult(String gameresult) {
		try {
			//System.out.println("파일에 저장할 내용 :"+gameresult);
			
			// 1. 파일처리 [ 저장 ]
				// 1. 파일출력스트림 객체 선언 [파일경로]
			FileOutputStream fieFileOutputStream = new FileOutputStream("./src/gameFile.txt" , true); // 이어쓰기 기능추가
				// 2. 파일출력스트림 객체를 이용한 내보내기 메소드 사용 [바이트 단위]
					// 1. 문자열을 바이트배열로 변환후
			byte[] outstrArray  = gameresult.getBytes();
					// 2. 바이트배열을 내보내기
			fieFileOutputStream.write(outstrArray);  // 문자열 --> 바이트배열 변환후 
			fieFileOutputStream.flush(); // 버퍼를 비워서 데이터를 파일에 기록
			fieFileOutputStream.close(); // 파일 닫기
			// 2.파일처리 [불러오기]
				// 1. 파일입력스트림 객체 선언 [ 파일경로 ]
			FileInputStream fileInputStream = new FileInputStream("./src/gameFile.txt");
				// 2. 파일입력스트림 객체를 이용한 파일내용 읽어오기 [ 바이트 단위 ]
					// 1. 읽어온 바이트를 저장할 배열을 우선 선언
			byte[] instrArray = new byte[1000]; // 파일의 바이트만큼 배열 선언 [ *정확한 파일의 용량 알수 없어서 임의로 ]
					// 2. 바이트 읽어서 해당 바이트배열에 저장
			fileInputStream.read( instrArray );
			fileInputStream.close(); // 파일 닫기
				// 3. 저장된 바이트배열을 문자열 변환
			String str = new String( instrArray ); // 만약에 빈 바이트는 공백 채움 
			//System.out.println(str);
			MainView.getInstance().mainPage();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
