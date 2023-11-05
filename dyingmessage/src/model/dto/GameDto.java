package model.dto;

import java.util.ArrayList;
import java.util.Arrays;

public class GameDto {

	// 1.필드



	// + 보기카드 7장씩 받기위한 필드
	private int tno;
	private int cno;
	private String cname;

	// + 힌트카드 받기위한 필드
	private int hno;
	private String hname;
	private String htype;


	// 2. 생성자
	public GameDto() {
	}

	// + 보기카드 7장씩 받기위한 생성자
	public GameDto(int tno, int cno, String cname) {
		super();
		this.tno = tno;
		this.cno = cno;
		this.cname = cname;
	}

	// + 힌트 불러오기 위한 생성자
	public GameDto(int tno, int cno, String cname, int hno, String hname, String htype) {
		super();
		this.tno = tno;
		this.cno = cno;
		this.cname = cname;
		this.hno = hno;
		this.hname = hname;
		this.htype = htype;
	}
	
	

	// 3.메소드

	public int getTno() {
		return tno;
	}

	public void setTno(int tno) {
		this.tno = tno;
	}

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}


	public int getHno() {
		return hno;
	}

	public void setHno(int hno) {
		this.hno = hno;
	}

	public String getHname() {
		return hname;
	}

	public void setHname(String hname) {
		this.hname = hname;
	}

	public String getHtype() {
		return htype;
	}

	public void setHtype(String htype) {
		this.htype = htype;
	}



	@Override
	public String toString() {
		return "GameDto [tno=" + tno + ", cno=" + cno + ", cname=" + cname + ", hno=" + hno + ", hname=" + hname
				+ ", htype=" + htype + "]";
	}


}
