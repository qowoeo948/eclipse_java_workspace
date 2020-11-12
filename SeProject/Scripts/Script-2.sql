create table board_member(
	member_id NUMBER PRIMARY KEY,
	m_id varchar(20),
	m_pass varchar(20),
	m_name varchar(20),
	regdate DATE DEFAULT sysdate
);

CREATE SEQUENCE seq_board_member
INCREMENT BY 1
START WITH 1;
