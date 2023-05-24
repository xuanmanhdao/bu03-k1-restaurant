--------------------------------------------------------
--  File created - Saturday-May-20-2023   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Sequence AUTO_INCREMENT
--------------------------------------------------------

   CREATE SEQUENCE  "MANHBAUTROI"."AUTO_INCREMENT"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 121 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence AUTO_INCREMENT_SEQUENCE
--------------------------------------------------------

   CREATE SEQUENCE  "MANHBAUTROI"."AUTO_INCREMENT_SEQUENCE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 41 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence NEXT_ID_CART
--------------------------------------------------------

   CREATE SEQUENCE  "MANHBAUTROI"."NEXT_ID_CART"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 41 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence NEXT_ID_CART_DETAIL
--------------------------------------------------------

   CREATE SEQUENCE  "MANHBAUTROI"."NEXT_ID_CART_DETAIL"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 41 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence NEXT_ID_CATEGORY
--------------------------------------------------------

   CREATE SEQUENCE  "MANHBAUTROI"."NEXT_ID_CATEGORY"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 41 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence NEXT_ID_ORDERS
--------------------------------------------------------

   CREATE SEQUENCE  "MANHBAUTROI"."NEXT_ID_ORDERS"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 81 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence NEXT_ID_ORDER_DETAIL
--------------------------------------------------------

   CREATE SEQUENCE  "MANHBAUTROI"."NEXT_ID_ORDER_DETAIL"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 81 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence NEXT_ID_PRODUCT
--------------------------------------------------------

   CREATE SEQUENCE  "MANHBAUTROI"."NEXT_ID_PRODUCT"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence NEXT_ID_ROLE
--------------------------------------------------------

   CREATE SEQUENCE  "MANHBAUTROI"."NEXT_ID_ROLE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence NEXT_ID_USERS
--------------------------------------------------------

   CREATE SEQUENCE  "MANHBAUTROI"."NEXT_ID_USERS"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 121 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence NEXT_ID_USER_PRODUCT
--------------------------------------------------------

   CREATE SEQUENCE  "MANHBAUTROI"."NEXT_ID_USER_PRODUCT"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence NEXT_ID_USER_ROLE
--------------------------------------------------------

   CREATE SEQUENCE  "MANHBAUTROI"."NEXT_ID_USER_ROLE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 121 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Table CART
--------------------------------------------------------

  CREATE TABLE "MANHBAUTROI"."CART" 
   (	"ID" NUMBER(22,0), 
	"TOTAL_PRICE" NUMBER(20,0), 
	"CREATED_BY" NUMBER(22,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table CART_DETAIL
--------------------------------------------------------

  CREATE TABLE "MANHBAUTROI"."CART_DETAIL" 
   (	"ID" NUMBER(22,0), 
	"CART_ID" NUMBER(22,0), 
	"PRODUCT_ID" NUMBER(22,0), 
	"PRICE" NUMBER(20,0), 
	"QUANTITY" NUMBER(*,0)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table CATEGORY
--------------------------------------------------------

  CREATE TABLE "MANHBAUTROI"."CATEGORY" 
   (	"ID" NUMBER(22,0), 
	"TYPE" VARCHAR2(100 BYTE), 
	"DESCRIPTION" VARCHAR2(300 BYTE), 
	"CREATED_AT" DATE, 
	"UPDATED_AT" DATE, 
	"PARENT_ID" NUMBER(22,0)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table ORDERS
--------------------------------------------------------

  CREATE TABLE "MANHBAUTROI"."ORDERS" 
   (	"ID" NUMBER(20,0), 
	"TOTAL" NUMBER(20,0), 
	"STATUS" NUMBER(10,0), 
	"CREATED_AT" DATE, 
	"CREATED_BY" NUMBER(20,0)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table ORDER_DETAIL
--------------------------------------------------------

  CREATE TABLE "MANHBAUTROI"."ORDER_DETAIL" 
   (	"ID" NUMBER(20,0), 
	"ORDER_ID" NUMBER(20,0), 
	"PRODUCT_ID" NUMBER(20,0), 
	"QUANTITY" NUMBER(20,0), 
	"PRICE" NUMBER(20,0)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table PRODUCT
--------------------------------------------------------

  CREATE TABLE "MANHBAUTROI"."PRODUCT" 
   (	"ID" NUMBER(20,0), 
	"CATEGORY_ID" NUMBER(20,0), 
	"STATUS" NUMBER(20,0), 
	"IMAGE" VARCHAR2(500 CHAR), 
	"NAME" VARCHAR2(50 BYTE), 
	"PRICE" NUMBER(20,0), 
	"TOTAL_SOLD" NUMBER(20,0), 
	"DESCRIPTION" VARCHAR2(500 BYTE), 
	"QUANTITY" NUMBER(20,0), 
	"CREATED_AT" DATE, 
	"UPDATED_AT" DATE, 
	"UPDATED_BY" NUMBER(20,0), 
	"CREATED_BY" NUMBER(20,0)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table ROLE
--------------------------------------------------------

  CREATE TABLE "MANHBAUTROI"."ROLE" 
   (	"ID" NUMBER, 
	"NAME" VARCHAR2(20 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table USERS
--------------------------------------------------------

  CREATE TABLE "MANHBAUTROI"."USERS" 
   (	"ID" NUMBER(20,0), 
	"PASSWORD" VARCHAR2(255 BYTE), 
	"NAME" VARCHAR2(20 BYTE), 
	"PHONE" VARCHAR2(10 BYTE), 
	"CREATED_AT" DATE, 
	"UPDATED_AT" DATE, 
	"EMAIL" VARCHAR2(100 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table USER_PRODUCT
--------------------------------------------------------

  CREATE TABLE "MANHBAUTROI"."USER_PRODUCT" 
   (	"ID" NUMBER(20,0), 
	"USER_ID" NUMBER(20,0), 
	"PRODUCT_ID" NUMBER(20,0)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table USER_ROLE
--------------------------------------------------------

  CREATE TABLE "MANHBAUTROI"."USER_ROLE" 
   (	"ID" NUMBER, 
	"ROLE_ID" NUMBER(19,0), 
	"USER_ID" NUMBER(19,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
REM INSERTING into MANHBAUTROI.CART
SET DEFINE OFF;
REM INSERTING into MANHBAUTROI.CART_DETAIL
SET DEFINE OFF;
REM INSERTING into MANHBAUTROI.CATEGORY
SET DEFINE OFF;
REM INSERTING into MANHBAUTROI.ORDERS
SET DEFINE OFF;
REM INSERTING into MANHBAUTROI.ORDER_DETAIL
SET DEFINE OFF;
REM INSERTING into MANHBAUTROI.PRODUCT
SET DEFINE OFF;
REM INSERTING into MANHBAUTROI.ROLE
SET DEFINE OFF;
Insert into MANHBAUTROI.ROLE (ID,NAME) values (1,'CUSTOMER');
Insert into MANHBAUTROI.ROLE (ID,NAME) values (2,'OWNER');
Insert into MANHBAUTROI.ROLE (ID,NAME) values (3,'STAFF');
REM INSERTING into MANHBAUTROI.USERS
SET DEFINE OFF;
Insert into MANHBAUTROI.USERS (ID,PASSWORD,NAME,PHONE,CREATED_AT,UPDATED_AT,EMAIL) values (1,'$2a$10$dyhoh7RyAt2Xs0rT7uKwmuGsEyZ5Ln20zfVYuQ0mmPF.YLugzWLxW','Ch? c?a h�ng',null,to_date('11-MAY-23','DD-MON-RR'),null,'owner@gmail.com');
REM INSERTING into MANHBAUTROI.USER_PRODUCT
SET DEFINE OFF;
REM INSERTING into MANHBAUTROI.USER_ROLE
SET DEFINE OFF;
Insert into MANHBAUTROI.USER_ROLE (ID,ROLE_ID,USER_ID) values (1,2,1);
--------------------------------------------------------
--  DDL for Index CART_DETAIL_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "MANHBAUTROI"."CART_DETAIL_PK" ON "MANHBAUTROI"."CART_DETAIL" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index CART_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "MANHBAUTROI"."CART_PK" ON "MANHBAUTROI"."CART" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index CATEGORY_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "MANHBAUTROI"."CATEGORY_PK" ON "MANHBAUTROI"."CATEGORY" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index ORDER_DETAIL_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "MANHBAUTROI"."ORDER_DETAIL_PK" ON "MANHBAUTROI"."ORDER_DETAIL" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index ORDER_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "MANHBAUTROI"."ORDER_PK" ON "MANHBAUTROI"."ORDERS" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index PRODUCT_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "MANHBAUTROI"."PRODUCT_PK" ON "MANHBAUTROI"."PRODUCT" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index ROLE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "MANHBAUTROI"."ROLE_PK" ON "MANHBAUTROI"."ROLE" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index USER_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "MANHBAUTROI"."USER_PK" ON "MANHBAUTROI"."USERS" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index USER_PRODUCT_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "MANHBAUTROI"."USER_PRODUCT_PK" ON "MANHBAUTROI"."USER_PRODUCT" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index USER_ROLE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "MANHBAUTROI"."USER_ROLE_PK" ON "MANHBAUTROI"."USER_ROLE" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Trigger CART_DETAIL_TRG
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "MANHBAUTROI"."CART_DETAIL_TRG" 
BEFORE INSERT ON CART_DETAIL 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT NEXT_ID_CART_DETAIL.NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "MANHBAUTROI"."CART_DETAIL_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger CART_TRG
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "MANHBAUTROI"."CART_TRG" 
BEFORE INSERT ON CART 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT NEXT_ID_CART.NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "MANHBAUTROI"."CART_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger CATEGORY_TRG
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "MANHBAUTROI"."CATEGORY_TRG" 
BEFORE INSERT ON CATEGORY 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT NEXT_ID_CATEGORY.NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "MANHBAUTROI"."CATEGORY_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger ORDERS_TRG
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "MANHBAUTROI"."ORDERS_TRG" 
BEFORE INSERT ON ORDERS 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT NEXT_ID_ORDERS.NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "MANHBAUTROI"."ORDERS_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger ORDER_DETAIL_TRG
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "MANHBAUTROI"."ORDER_DETAIL_TRG" 
BEFORE INSERT ON ORDER_DETAIL 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT NEXT_ID_ORDER_DETAIL.NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "MANHBAUTROI"."ORDER_DETAIL_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger PRODUCT_TRG
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "MANHBAUTROI"."PRODUCT_TRG" 
BEFORE INSERT ON PRODUCT 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT NEXT_ID_PRODUCT.NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "MANHBAUTROI"."PRODUCT_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger ROLE_TRG
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "MANHBAUTROI"."ROLE_TRG" 
BEFORE INSERT ON ROLE 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT NEXT_ID_ROLE.NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "MANHBAUTROI"."ROLE_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger USERS_TRG
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "MANHBAUTROI"."USERS_TRG" 
BEFORE INSERT ON USERS 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT NEXT_ID_USERS.NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "MANHBAUTROI"."USERS_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger USER_PRODUCT_TRG
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "MANHBAUTROI"."USER_PRODUCT_TRG" 
BEFORE INSERT ON USER_PRODUCT 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT NEXT_ID_USER_PRODUCT.NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "MANHBAUTROI"."USER_PRODUCT_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger USER_ROLE_TRG
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "MANHBAUTROI"."USER_ROLE_TRG" 
BEFORE INSERT ON USER_ROLE 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT NEXT_ID_USER_ROLE.NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "MANHBAUTROI"."USER_ROLE_TRG" ENABLE;



/
--------------------------------------------------------
--  Constraints for Table PRODUCT
--------------------------------------------------------

  ALTER TABLE "MANHBAUTROI"."PRODUCT" ADD CONSTRAINT "PRODUCT_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table ORDER_DETAIL
--------------------------------------------------------

  ALTER TABLE "MANHBAUTROI"."ORDER_DETAIL" ADD CONSTRAINT "ORDER_DETAIL_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table USER_PRODUCT
--------------------------------------------------------

  ALTER TABLE "MANHBAUTROI"."USER_PRODUCT" ADD CONSTRAINT "USER_PRODUCT_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table USERS
--------------------------------------------------------

  ALTER TABLE "MANHBAUTROI"."USERS" ADD CONSTRAINT "USER_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table CART
--------------------------------------------------------

  ALTER TABLE "MANHBAUTROI"."CART" ADD CONSTRAINT "CART_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table CART_DETAIL
--------------------------------------------------------

  ALTER TABLE "MANHBAUTROI"."CART_DETAIL" ADD CONSTRAINT "CART_DETAIL_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table USER_ROLE
--------------------------------------------------------

  ALTER TABLE "MANHBAUTROI"."USER_ROLE" ADD CONSTRAINT "USER_ROLE_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table ROLE
--------------------------------------------------------

  ALTER TABLE "MANHBAUTROI"."ROLE" ADD CONSTRAINT "ROLE_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table ORDERS
--------------------------------------------------------

  ALTER TABLE "MANHBAUTROI"."ORDERS" ADD CONSTRAINT "ORDER_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table CATEGORY
--------------------------------------------------------

  ALTER TABLE "MANHBAUTROI"."CATEGORY" ADD CONSTRAINT "CATEGORY_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS"  ENABLE;