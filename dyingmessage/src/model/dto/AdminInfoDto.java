package model.dto;


public class AdminInfoDto {

	// 1. 필드 ------------------------------------------------------------
		// typecard 테이블
		private int tno;			// 카드 타입 번호(PK)
		private String tname;		// 카드 이름
		
		// card 테이블
									// tno 카드 타입 번호(FK)
		private int cno;			// 카드번호 (PK)
		private String cname;		// 카드이름
		
		// hint 테이블	
									// 카드번호 (FK)
		private int hno;			// 힌트카드번호
		private String hname;		// 힌트 이름
		private String htype;		// 힌트의 타입 a: 형용사 / n: 명사

		// 관리자 정보 DB 사용 안하기로 했지만 혹시 어디 사용될까 싶어서 !
		private String id;
		private String pw;
		
		
	// 2. 생성자 ------------------------------------------------------------
		public AdminInfoDto() {
			super();
		}
		
		public AdminInfoDto(int cno, String cname) {
			super();
			this.cno = cno;
			this.cname = cname;
		}
		
		
		public AdminInfoDto(int cno, String hname, String htype) {
			super();
			this.cno = cno;
			this.hname = hname;
			this.htype = htype;
		}

		public AdminInfoDto(int tno, int cno, String cname) {
			super();
			this.tno = tno;
			this.cno = cno;
			this.cname = cname;
		}
		
		

		public AdminInfoDto(int tno, int cno, String hname, String htype) {
			super();
			this.tno = tno;
			this.cno = cno;
			this.hname = hname;
			this.htype = htype;
		}

		public AdminInfoDto(int tno, int cno, String cname, String hname, String htype) {
			super();
			this.tno = tno;
			this.cno = cno;
			this.cname = cname;
			this.hname = hname;
			this.htype = htype;
		}

		public AdminInfoDto(int tno, String tname, int cno, String cname, int hno, String hname, String htype,
				String id, String pw) {
			super();
			this.tno = tno;
			this.tname = tname;
			this.cno = cno;
			this.cname = cname;
			this.hno = hno;
			this.hname = hname;
			this.htype = htype;
			this.id = id;
			this.pw = pw;
		}
		
		
		
		
	// 3. 메소드 ------------------------------------------------------------
		public int getTno() {
			return tno;
		}
		public void setTno(int tno) {
			this.tno = tno;
		}
		public String getTname() {
			return tname;
		}
		public void setTname(String tname) {
			this.tname = tname;
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
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getPw() {
			return pw;
		}
		public void setPw(String pw) {
			this.pw = pw;
		}

		@Override
		public String toString() {
			return "AdminInfoDto [tno=" + tno + ", tname=" + tname + ", cno=" + cno + ", cname=" + cname + ", hno="
					+ hno + ", hname=" + hname + ", htype=" + htype + ", id=" + id + ", pw=" + pw + "]";
		}

	

}
