create table phonebook_tb (
    id number (10) primary key,
    name varchar2(10),
    phoneNum varchar2(20),
    birth varchar2(20)
);

create sequence seq_phonebook
    increment by 1 
    start with 1 
    nomaxvalue
    minvalue 1 
    nocycle 
    nocache; 

commit;