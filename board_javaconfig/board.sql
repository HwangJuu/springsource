create table spring_board(
	bno number(10,0),
	title varchar2(200) not null,
	content varchar2(2000) not null,
	writer varchar2(50) not null,
	regdate date default sysdate,
	updatedate date default sysdate
);

alter table spring_board add constraint pk_spring_board primary key(bno);

create sequence seq_board;
create sequence spring_board_seq;

insert into spring_board(bno, title,content,writer) values(spring_board_seq.nextval, '자바', '자바따라하기', '김자바');
insert into spring_board(bno, title,content,writer) values(spring_board_seq.nextval, '스프링프레임워크', '스프링따라하기', '김스프링');
insert into spring_board(bno, title,content,writer) values(spring_board_seq.nextval, '게시판', '게시판작성하기', '글쓴이');
insert into spring_board(bno, title,content,writer) values(spring_board_seq.nextval, '읽어보기', '게시글', '관리자');

-- 댓글 수 컬럼 추가
alter table spring_board add(replycnt number default 0);

-- 기존 댓글 업데이트
update spring_board 
set replycnt = (
				select count(rno) 
				from SPRING_REPLY
				where spring_board.bno = spring_reply.bno
				);


--oracle 페이지 나누기

--더미 데이터
insert into spring_board(bno, title, content,writer)
(select spring_board_seq.nextval, title, content, writer from spring_board);

select count(*) from spring_board;

--rownum 사용
select rownum,bno, title from spring_board;

-- rownum 은 order by 절과 함께 사용시 주의
-- order by 절에서 사용 하는 컬럼이 index가 아닐때
-- 임의로 행을 가지고 나온 후 번호를 붙임 =? 인라인 쿼리
-- select rownum,...
-- from(select * from board where bno >0 order by re_ref desc)

--1) rownum 사용방식. 인라인 쿼리
select rownum, bno, title, writer
from (select bno, title, writer,redgate, updatedate from spring_board order by bno desc)
where rownum<=10;

--2) index에 순서를 지정
-- order by 컬럼이 인덱스라면 오라클 힌트 이용 가능
select /*+INDEX_DESC(spring_board pk_spring_board)*/rownum, bno, title, writer
from spring_board
where rownum<=10;

--1page 최신글 가져오기
select bno, title, writer, regdate, updatedate
from(
	select /*+INDEX_DESC(spring_board pk_spring_board)*/rownum rn, bno, title, writer,regdate, updatedate
	from spring_board
	where rownum<=10
	)
where rn> 0;

--2page 최신글 가져오기
--힌트부분이 없으면 오류가 나진 않음.
select bno, title, writer, regdate, updatedate
from(
	select /*+INDEX_DESC(spring_board pk_spring_board)*/rownum rn, bno, title, writer,regdate, updatedate
	from spring_board
	where rownum<=20
	)
where rn> 10;

--
select bno, title, writer, regdate, updatedate
from(
	select /*+INDEX_DESC(spring_board pk_spring_board)*/rownum rn, bno, title, writer,regdate, updatedate
	from spring_board
	where rownum<=?
	)
where rn> ?;


--페이지 나누기 + 검색

--타이틀 == 모달 검색
--type : 제목
--/list?pageNum=1&amount=20&type=T&keyword=모달
select bno, title, writer, regdate, updatedate
from(
	select /*+INDEX_DESC(spring_board pk_spring_board)*/rownum rn, bno, title, writer,regdate, updatedate
	from spring_board
	where bno >=0 and (title like '%모달%') and rownum<=(1 * 30)
	)
where rn> (1-1)*30;

--type : 제목 or 내용
----/list?pageNum=1&amount=20&type=TC&keyword=모달
select bno, title, writer, regdate, updatedate
from(
	select /*+INDEX_DESC(spring_board pk_spring_board)*/rownum rn, bno, title, writer,regdate, updatedate
	from spring_board
	where (title like '%모달%' or content like '%모달%') and bno >=0  and rownum<=(1 * 30)
	)
where rn> (1-1)*30;



--2022.05.03
--댓글
create table spring_reply(
	rno number(10,0),		-- 댓글번호
	bno number(10,0) not null, --원번글 번호
	reply varchar2(1000) not null, -- 댓글 내용
	replyer varchar2(50) not null, -- 댓글 작성자
	replydate date default sysdate,  --댓글 작성 날짜
	updatedate date default sysdate  --댓글 수정 날짜

);

-- 댓글 시퀀스
create sequence seq_reply;

--댓글 테이블 pk 설정 후 이름 지정
alter table spring_reply add constraint pk_reply primary key(rno);

-- 외래키 제약
alter table spring_reply add constraint fk_reply_board foreign key(bno)
references spring_board(bno);


--2022.05.04
select * from spring_reply;

select * from spring_reply where rno=2;

select * from spring_reply where bno=1813;

--댓글 수 확인
select * from spring_board where bno=1813;
select * from spring_board where bno=1812;

--인덱스 생성
create index idx_reply on spring_reply(bno desc, rno asc);

--2022.05.06
--첨부파일 테이블 생성
create table spring_attach(
	uuid varchar2(100)not null,
	uploadpath varchar2(200) not null,
	filename varchar2(100) not null,
	filetype char(1) default '1',
	bno number(10,0)
);

alter table spring_attach add constraint pk_attach primary key(uuid);
alter table spring_attach add constraint fk_board_attach
foreign key(bno) references spring_board(bno);

select * from spring_attach;

