Drop Table tbl_model;
Drop Table tbl_apikey;
Drop Table tbl_oauth;

create table tbl_model 
(
 mcode varchar2(50), 
 mname varchar2(200) not null, 
 mdesc varchar2(2000),
 mimg varchar2(300),
 regdate date default sysdate
);

alter table tbl_model add constraint pk_model  primary key (mcode); 


create table tbl_apikey 
(
 hostName varchar2(200), 
 keyValue varchar2(200),
 cnt number default 0,
 regdate date default sysdate,
 updateDate date default sysdate
);

alter table tbl_apikey add constraint pk_apikey  primary key (hostName);


create table tbl_oauth
(consumerkey varchar2(300),
 consumerSecretKey varchar2(300), 
 requestToken varchar2(300), 
 requestTokenSecret varchar2(300), 
 accessToken varchar2(300), 
 accessTokenSecret varchar2(300),
 verifier varchar2(300),
 callback varchar2(300)
);

alter table tbl_oauth add constraint pk_oauth primary key (consumerkey);