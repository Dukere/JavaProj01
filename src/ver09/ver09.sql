create table phonebook_tb (
    id number (10) primary key,
    name varchar2(10),
    phoneNum varchar2(20),
    birth varchar2(20)
);

create sequence seq_phonebook
    increment by 1 
    start with 0 
    nomaxvalue
    minvalue 0
    nocycle 
    nocache; 

drop sequence seq_phonebook;

drop table phonebook_tb;

commit;

delete from phonebook_tb where name like '%1%';

rollback;