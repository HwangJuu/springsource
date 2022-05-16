--회원 정보 테이블
create table sp_user(
	userid varchar2(50),
	email varchar2(100) not null,
	enabled char(1) default '1',
	password varchar2(100) not null
);

alter table sp_user add constraint sp_user_pk primary key(userid);

--회원이 가지고 있어야할 권한 테이블 :authority
create table sp_user_authority(
	userid varchar2(50) not null,
	authority varchar2(50) not null
);

--외래키 제약 조건으로 걸어둠. 회원정보 테이블이 부모, 권한 테이블이 자식 테이블

alter table sp_user_authority add constraint sp_user_authority_fk foreign key(userid)
references sp_user(userid);

insert into sp_user(userid, email, password) values('1','user@test.com','1111');
insert into sp_user_authority(userid, authority) values('1', 'ROLE_USER');
insert into sp_user_authority(userid, authority) values('1', 'ROLE_ADMIN');

--권한 가져오기
select * from sp_user_authority where userid='1';

--join(내부조인, 외부조인)
--내부조인(일치하는 컬럼을 기준으로 값을 가져오기)
--외부조인(left outer join, right outer join)


select s1.userid, email, password, enabled, authority
from sp_user s1 join sp_user_authority s2 on s1.userid = s2.userid
where s1.userid = '1';

--회원가입 후 비밀번호 확인해보기(암호화걸려있음)
select * from sp_user;
select * from sp_user_authority;


--로그인 기억하기 테이블
create table persistent_logins(
	username varchar(64) not null,
	series varchar(64) primary key,
	token varchar(64) not null,
	last_used timestamp not null
);

select * from persistent_logins;
