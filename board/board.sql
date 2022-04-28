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



