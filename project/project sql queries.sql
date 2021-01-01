
create extension chkpass;


create table bankApp_register(
       id serial primary key,
       username varchar(10) not null,
       password varchar(10) not null,
       email text not null,
       usertype varchar(1) not null);
      
      
alter table project_register alter column password type varchar(10);   

delete from project_register ;


drop table project_register ;

      
      
      
insert into project_schema.bankapp_register(username,password,email,usertype) values('hari','pass','hari@gmail.com',1);      



select * from project_register;


INSERT INTO project_schema.project_register(username,password, email)
VALUES('mukesh1','pass1','cmukesh8688@gmail.com');

delete from bank_customers ;

drop table bank_customers ;

create sequence bank_customer_id;

alter sequence bank_customer_id restart with 1001 increment by 1;


CREATE  TABLE bank_customers(
customerId int primary key default nextval('bank_customer_id'),
firstName varchar(45) not null,
lastName varchar(45) not null,
address varchar(45) not null,
city   varchar(45),
state  varchar(2),
phoneNumber varchar(15) not null,
ssn int4(9) unique not null,
joinDate date not null,
customer_registerid int not null,
zipcode varchar(5) not null,
constraint fk_customer 
   foreign key(customer_registerid)
   references project_schema.bankApp_register(id));
   
  
  
  
  select * from project_customers ;
  
 
 delete from bank_customers ;
 

delete from bank_transactions ;

delete from bank_account ;

drop table bank_account;
 


 
 create sequence bank_acc_num_id;

 alter sequence bank_acc_num_id restart with 1000001 increment by 1;
 
 create table bank_account(
      accountNumber int primary key default nextval('bank_acc_num_id'),
      balance numeric(8,2) not null,
      OpeningBalance numeric(8,2) not null,
      accountName varchar(45) not null,
      customers_customerId int not null,
      dateOpened date not null,
      interest numeric(8,2) ,
      account_status smallint not null,
      accounttype int not null,
      branchloc varchar(6) not null,
      constraint fk_account
         foreign key(customers_customerId)
         references project_schema.bank_customers(customerId));
 
 )
 
 
 
 
 

 
 alter table bank_account add column accounttype int not null;
 
 alter table bank_account 
 add column account_status smallint not null;
 
 



drop table bank_transactions;

 create sequence bank_trans_id;

alter sequence bank_trans_id restart with 101 increment by 1;

 create table bank_transactions(
   transaction_id int primary key default nextval('bank_trans_id'),
   trans_type int not null,
   amount numeric(8,2) not null,
   account_accountNumber int not null,
   total_balance numeric(8,2) not null,
   transactionDate date,
   constraint fk_transaction 
       foreign key(account_accountNumber)
       references project_schema.bank_account(accountNumber));
 )
 
 drop table project_transactions ;
 
 
 alter table project_transactions 
 add column transaction_id serial primary key,
 add column trans_type int not null,
 add column total_balance numeric(8,2) not null;



create table bankApp_registor as 
select * from project_register where  1 = 0 ;


select * from bankapp_registor ;


insert into bankapp_registor  select * from project_register ;


create table bank_customers as
select * from project_customers where 1 =0;


create table bank_account as 
select * from project_account ;


create table bank_transaction as
select * from project_transactions pt ;

create table bankapp_registor1 as
table bankapp_registor ;
 
 
 
 drop table bank_account,bank_customers ,bank_transaction ,bankapp_registor ,bankapp_registor1 ;

drop table project_account ,project_customers ,project_register ,project_transactions ;

commit;


CREATE TABLE LOGS
   (USER_ID serial     primary key,
    DATED   DATE           NOT NULL,
    LOGGER  VARCHAR(50)    NOT NULL,
    logLEVEL   VARCHAR(10)    NOT NULL,
    MESSAGE VARCHAR(1000)  NOT NULL
   );
 
 
  
select * from logs


select * from bankapp_register where username ='mukesh' and password ='11111';


alter table bankapp_register add column usertype varchar(1) ;

update bankapp_register 
set usertype = 1;

update bankapp_register 
set usertype = 3
where id =1;


select * from bankapp_register br where username = 'Preet' ;


delete from bankapp_register where id  = 15 ;

drop table bankapp_register ;


insert into bankapp_register(username,password,email,usertype) values('priya','pass','cpriya@gmail.com',2);


select * from bankapp_register br ;


alter table bank_account add column branchloc varchar(4) not null;


alter table bank_customers add column zipcode varchar(6) not null;


select * from bank_account ba ;


insert into bank_customers (firstname,lastname,address,city,state,phonenumber,ssn,joindate,customer_registerid,zipcode)
values('mukesh','Chaudhary','5620 N ', 'chicago','IL',4634634384,3434343,'2020-12-1',10,'60606');


select * from bank_customers bc ;

insert into bank_account (balance,openingbalance,accountname,customers_customerid,dateopened,interest,account_status,accounttype,branchloc)
values(1000,1000,'Checking Account',1002,'2020-11-12',0.1,1,1,'IL0001');


select * from bank_account ba ;


insert into bank_transactions (trans_type,amount,account_accountnumber,total_balance,transactiondate) 
values(2,200,1000002,1200,'2020-11-12');

select * from bank_transactions bt ;

select lastval() from bank_account;


ALTER table bank_account ALTER COLUMN accountnumber RESTART WITH 1000001;

alter sequence serial restart with 1000000 increment by 1;

CREATE SEQUENCE serial START 1000001;





select * from bankapp_register br ;

select * from bank_account ba ;

select * from bank_customers bc ;

select * from bank_transactions bt ;


select * from bank_customers bc  where customers_customerid  = 13

SELECT balance, openingbalance, accountname, customers_customerid, dateopened, interest, account_status, accounttype, branchloc
FROM project_schema.bank_account
WHERE accountnumber = 1000003 


delete from bankapp_register where id between 20 and 23;


