DROP DATABASE if exists dyingmessage;
CREATE DATABASE dyingmessage; 
USE dyingmessage;

DROP TABLE if exists typecard ;
create table typecard (						# Table 카드 종류
	tno int auto_increment,					# 카드 타입 번호(PK) int
    tname varchar(10) not null unique,		# 카드 이름
    primary key(tno)
);

drop table if exists card;
create table card(							# Table 카드
	tno int,								# 카드타입번호 (FK)
    cno int auto_increment,					# 카드번호 (PK)
    cname varchar(10) not null unique,		# 카드명
    primary key(cno),
    foreign key(tno) references typecard(tno)	
);

drop table if exists hint;
create table hint (							# Table 힌트카드	
	hno int auto_increment,					# 힌트카드번호
    hname varchar(10) not null,				# 힌트 카드의 타입 a: 형용사 / n: 명사
    htype char(1) not null,
    cno int,								# 카드번호 (FK)
    primary key(hno),
    foreign key(cno) references card(cno)
);

/*
		샘플 데이터
*/

# 카드타입
insert into typecard (tname) values('용의자');   	# 1
insert into typecard (tname) values('범행도구');	# 2
insert into typecard (tname) values('범행동기');	# 3

# 카드
select * from card;
insert into card (tno, cname) values (1,'가수');
insert into card (tno, cname) values (1,'의사');
insert into card (tno, cname) values (1,'강사');
insert into card (tno, cname) values (1,'살인마');
insert into card (tno, cname) values (1,'바텐더');
insert into card (tno, cname) values (1,'아이돌');
insert into card (tno, cname) values (1,'경찰');


insert into card (tno, cname) values (2,'줄넘기');
insert into card (tno, cname) values (2,'가방');
insert into card (tno, cname) values (2,'넥타이');
insert into card (tno, cname) values (2,'손도끼');
insert into card (tno, cname) values (2,'하이힐');
insert into card (tno, cname) values (2,'벽돌');
insert into card (tno, cname) values (2,'베개');

insert into card (tno, cname) values (3,'배신');
insert into card (tno, cname) values (3,'재미');
insert into card (tno, cname) values (3,'유산');
insert into card (tno, cname) values (3,'시기');
insert into card (tno, cname) values (3,'불륜');
insert into card (tno, cname) values (3,'협박');
insert into card (tno, cname) values (3,'층간소음');


# 힌트a
select * from hint;
insert into hint(hname, htype ,cno) values ('입', 'n' ,1);
insert into hint(hname, htype ,cno) values ('마이크', 'n' ,1);
insert into hint(hname, htype ,cno) values ('인기있는', 'a' ,1);
insert into hint(hname, htype ,cno) values ('똑똑한', 'a' ,2);
insert into hint(hname, htype ,cno) values ('차가운', 'a' ,2);
insert into hint(hname, htype ,cno) values ('냉철한', 'a' ,2);
insert into hint(hname, htype ,cno) values ('하얀', 'a' ,2);
insert into hint(hname, htype ,cno) values ('가운', 'n' ,2);
insert into hint(hname, htype ,cno) values ('기면수', 'n' ,3);
insert into hint(hname, htype ,cno) values ('저희', 'a' ,3);
insert into hint(hname, htype ,cno) values ('목소리', 'n' ,3);
insert into hint(hname, htype ,cno) values ('마스크', 'n' ,3);
insert into hint(hname, htype ,cno) values ('졸린', 'a' ,3);
insert into hint(hname, htype ,cno) values ('침착한', 'a' ,3);
insert into hint(hname, htype ,cno) values ('칼', 'n' ,4);
insert into hint(hname, htype ,cno) values ('무서운', 'a' ,4);
insert into hint(hname, htype ,cno) values ('잔인한', 'a' ,4);


#바텐더
insert into hint(hname, htype ,cno) values ('얼음', 'n' , 5);
insert into hint(hname, htype ,cno) values ('하이볼', 'n' , 5);
insert into hint(hname, htype ,cno) values ('밤', 'n' , 5);
insert into hint(hname, htype ,cno) values ('불', 'n' , 5);
insert into hint(hname, htype ,cno) values ('칵테일', 'n' , 5);
insert into hint(hname, htype ,cno) values ('언변이좋은', 'a' , 5);

#아이돌
insert into hint(hname, htype ,cno) values ('인기있는', 'a' , 6);
insert into hint(hname, htype ,cno) values ('우상', 'n' , 6);
insert into hint(hname, htype ,cno) values ('아름다운', 'a' , 6);
insert into hint(hname, htype ,cno) values ('현란한', 'a' , 6);
insert into hint(hname, htype ,cno) values ('무대', 'n' , 6);

#경찰
insert into hint(hname, htype ,cno) values ('딱딱한', 'a' ,7);
insert into hint(hname, htype ,cno) values ('무전기', 'n' ,7);


#줄넘기
insert into hint(hname, htype ,cno) values ('기다란', 'a' , 8);
insert into hint(hname, htype ,cno) values ('점프', 'n' , 8);
insert into hint(hname, htype ,cno) values ('알록달록', 'n' , 8);
insert into hint(hname, htype ,cno) values ('가느다란', 'a' , 8);

#가방
insert into hint(hname, htype ,cno) values ('큰', 'a' , 9);
insert into hint(hname, htype ,cno) values ('용이한', 'a' , 9);
insert into hint(hname, htype ,cno) values ('가벼운', 'a' ,9);
insert into hint(hname, htype ,cno) values ('키링', 'n' , 9);
insert into hint(hname, htype ,cno) values ('지퍼', 'n' , 9);
# 넥타이
insert into hint(hname, htype ,cno) values ('목', 'n' , 10 );
insert into hint(hname, htype ,cno) values ('질식', 'n' , 10 );
insert into hint(hname, htype ,cno) values ('회식', 'n' , 10 );
insert into hint(hname, htype ,cno) values ('부장님', 'n' , 10 );
insert into hint(hname, htype ,cno) values ('회사원', 'n' , 10 );
insert into hint(hname, htype ,cno) values ('기다란', 'a' , 10 );
insert into hint(hname, htype ,cno) values ('부드러운', 'a' , 10 );

# 손도끼
insert into hint(hname, htype ,cno) values ('차가운', 'a' , 11 );
insert into hint(hname, htype ,cno) values ('무거운', 'a' , 11);
insert into hint(hname, htype ,cno) values ('날카로운', 'a' , 11);
insert into hint(hname, htype ,cno) values ('신선', 'n' , 11);
insert into hint(hname, htype ,cno) values ('장작', 'n' , 11);
insert into hint(hname, htype ,cno) values ('농부', 'n' , 11);

# 하이힐
insert into hint(hname, htype ,cno) values ('여자', 'n' , 12);
insert into hint(hname, htype ,cno) values ('귀신', 'n' , 12);
insert into hint(hname, htype ,cno) values ('저주', 'n' , 12);
insert into hint(hname, htype ,cno) values ('빨간', 'a' , 12);
insert into hint(hname, htype ,cno) values ('높은', 'a' , 12);

# 벽돌
insert into hint(hname, htype ,cno) values ('무거운', 'a' , 13);
insert into hint(hname, htype ,cno) values ('딱딱한', 'a' , 13);
insert into hint(hname, htype ,cno) values ('까슬까슬한', 'a' , 13);
insert into hint(hname, htype ,cno) values ('붉은', 'a' , 13);
insert into hint(hname, htype ,cno) values ('아기돼지', 'n' , 13);
insert into hint(hname, htype ,cno) values ('시멘트', 'n' , 13);
insert into hint(hname, htype ,cno) values ('공사장', 'n' , 13);
insert into hint(hname, htype ,cno) values ('보도블럭', 'n' , 13);

#베개
insert into hint(hname, htype ,cno) values ('부드러운', 'a' , 14);
insert into hint(hname, htype ,cno) values ('깨끗한', 'a' , 14);
insert into hint(hname, htype ,cno) values ('흰색', 'n' , 14);
insert into hint(hname, htype ,cno) values ('사각형', 'n' , 14);

# 13 배신
insert into hint(hname, htype ,cno) values ('충격', 'n' ,15);
insert into hint(hname, htype ,cno) values ('신뢰', 'n' ,15);
insert into hint(hname, htype ,cno) values ('얍쌉한', 'a' ,15);
insert into hint(hname, htype ,cno) values ('런닝맨', 'n' ,15);
insert into hint(hname, htype ,cno) values ('이광수', 'n' ,15);
#재미
insert into hint(hname, htype ,cno) values ('냉장고', 'n' ,16);
insert into hint(hname, htype ,cno) values ('흥미로운', 'a' ,16);
insert into hint(hname, htype ,cno) values ('엔돌핀', 'n' ,16);
insert into hint(hname, htype ,cno) values ('지루하지않은', 'a' ,16);
insert into hint(hname, htype ,cno) values ('개그맨', 'n' ,16);
insert into hint(hname, htype ,cno) values ('친구', 'n' ,16);
#유산
insert into hint(hname, htype ,cno) values ('돈', 'n' ,17);
insert into hint(hname, htype ,cno) values ('변호사', 'n' ,17);
insert into hint(hname, htype ,cno) values ('가족', 'n' ,17);
insert into hint(hname, htype ,cno) values ('마지막', 'n' ,17);
insert into hint(hname, htype ,cno) values ('슬픈', 'a' ,17);

#시기
insert into hint(hname, htype ,cno) values ('열등감', 'n' ,18);
insert into hint(hname, htype ,cno) values ('치졸한', 'a' ,18);
insert into hint(hname, htype ,cno) values ('소외감', 'n' ,18);
insert into hint(hname, htype ,cno) values ('비난', 'n' ,18);
insert into hint(hname, htype ,cno) values ('억까', 'n' ,18);
insert into hint(hname, htype ,cno) values ('힐난', 'n' ,18);
insert into hint(hname, htype ,cno) values ('악플', 'n' ,18);
insert into hint(hname, htype ,cno) values ('옹졸한', 'a' ,18);
#불륜
insert into hint(hname, htype ,cno) values ('모텔', 'n' ,19);
insert into hint(hname, htype ,cno) values ('몸', 'n' ,19);
insert into hint(hname, htype ,cno) values ('은밀한', 'a' ,19);
insert into hint(hname, htype ,cno) values ('관계', 'n' ,19);
insert into hint(hname, htype ,cno) values ('부부의세계', 'n' ,19);
# 협박
insert into hint(hname, htype ,cno) values ('전화', 'n' ,20);
insert into hint(hname, htype ,cno) values ('납치', 'n' ,20);
insert into hint(hname, htype ,cno) values ('공포', 'a' ,20);
insert into hint(hname, htype ,cno) values ('두려운', 'a' ,20);
insert into hint(hname, htype ,cno) values ('불안', 'a' ,20);
insert into hint(hname, htype ,cno) values ('핸드폰', 'n' ,20);
insert into hint(hname, htype ,cno) values ('인질', 'n' ,20);
#층간소음
insert into hint(hname, htype ,cno) values ('짜증나는', 'a' ,21);
insert into hint(hname, htype ,cno) values ('아이들', 'n' ,21);
insert into hint(hname, htype ,cno) values ('신경질적인', 'a' ,21);
insert into hint(hname, htype ,cno) values ('날카로운', 'a' ,21);
insert into hint(hname, htype ,cno) values ('창문', 'n' ,21);

