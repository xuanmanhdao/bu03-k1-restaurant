-- Insert data for table ROLE
Insert into "MANHBAUTROI"."ROLE" (ID,NAME) values (1,'CUSTOMER');
Insert into "MANHBAUTROI"."ROLE" (ID,NAME) values (2,'OWNER');
Insert into "MANHBAUTROI"."ROLE" (ID,NAME) values (3,'STAFF');

-- Insert account default: owner
-- Username login: owner@gmail.com
-- Password login: 123456
Insert into "MANHBAUTROI"."USERS" (ID,PASSWORD,NAME,PHONE,CREATED_AT,UPDATED_AT,EMAIL) values (1,'$2a$10$dyhoh7RyAt2Xs0rT7uKwmuGsEyZ5Ln20zfVYuQ0mmPF.YLugzWLxW','Chủ cửa hàng',null,to_date('11-MAY-23','DD-MON-RR'),null,'owner@gmail.com')
Insert into "MANHBAUTROI"."USER_ROLE" (ID,ROLE_ID,USER_ID) values (1,2,1);
