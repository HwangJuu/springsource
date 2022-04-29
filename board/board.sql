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

