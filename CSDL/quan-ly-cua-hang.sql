-- DDL Table Users
CREATE TABLE "USERS" 
   (	"ID" NUMBER(20,0), 
	"PASSWORD" VARCHAR2(255 BYTE), 
	"NAME" VARCHAR2(20 BYTE), 
	"PHONE" VARCHAR2(10 BYTE), 
	"CREATED_AT" DATE, 
	"UPDATED_AT" DATE, 
	"EMAIL" VARCHAR2(100 BYTE),
     CONSTRAINT "USER_PK" PRIMARY KEY (ID)
   );
CREATE SEQUENCE "NEXT_ID_USERS"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 101 CACHE 20 NOORDER  NOCYCLE ;
create or replace TRIGGER USERS_TRG 
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

-- DDL Table Role
 CREATE TABLE "ROLE" 
   (	"ID" NUMBER, 
	"NAME" VARCHAR2(20 BYTE),
    CONSTRAINT "ROLE_PK" PRIMARY KEY ("ID")
   );
CREATE SEQUENCE "NEXT_ID_ROLE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
create or replace TRIGGER ROLE_TRG 
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

-- DDL Table USER_ROLE
CREATE TABLE "USER_ROLE" 
   (	"ID" NUMBER, 
	"ROLE_ID" NUMBER(19,0), 
	"USER_ID" NUMBER(19,0),
    CONSTRAINT "USER_ROLE_PK" PRIMARY KEY ("ID")
   );
CREATE SEQUENCE "NEXT_ID_USER_ROLE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 101 CACHE 20 NOORDER  NOCYCLE ;
create or replace TRIGGER USER_ROLE_TRG 
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

-- DDL Table CART
CREATE TABLE "CART" 
   (	"ID" NUMBER(22,0), 
	"TOTAL_PRICE" NUMBER(20,0), 
	"CREATED_BY" NUMBER(22,0),
    CONSTRAINT "CART_PK" PRIMARY KEY ("ID")
   );
CREATE SEQUENCE "NEXT_ID_CART"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
create or replace TRIGGER CART_TRG 
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

-- DDL CART_DETAIL
CREATE TABLE "CART_DETAIL" 
   (	"ID" NUMBER(22,0), 
	"CART_ID" NUMBER(22,0), 
	"PRODUCT_ID" NUMBER(22,0), 
	"PRICE" NUMBER(20,0), 
	"QUANTITY" NUMBER(*,0),
    CONSTRAINT "CART_DETAIL_PK" PRIMARY KEY ("ID")
   );
CREATE SEQUENCE "NEXT_ID_CART_DETAIL"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 41 CACHE 20 NOORDER  NOCYCLE ;
create or replace TRIGGER CART_DETAIL_TRG 
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

-- DDL Table Category
CREATE TABLE "CATEGORY" 
   (	"ID" NUMBER(22,0), 
	"TYPE" VARCHAR2(100 BYTE), 
	"DESCRIPTION" VARCHAR2(300 BYTE), 
	"CREATED_AT" DATE, 
	"UPDATED_AT" DATE, 
	"PARENT_ID" NUMBER(22,0),
    CONSTRAINT "CATEGORY_PK" PRIMARY KEY ("ID")
   );
CREATE SEQUENCE  "NEXT_ID_CATEGORY"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 41 CACHE 20 NOORDER  NOCYCLE ;
create or replace TRIGGER CATEGORY_TRG 
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

-- DDL Table 
  CREATE TABLE "PRODUCT" 
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
	"CREATED_BY" NUMBER(20,0),
    CONSTRAINT "PRODUCT_PK" PRIMARY KEY ("ID")
   );
CREATE SEQUENCE "NEXT_ID_PRODUCT"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
create or replace TRIGGER PRODUCT_TRG 
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

-- DDL Table USER_PRODUCT
CREATE TABLE "USER_PRODUCT" 
   (	"ID" NUMBER(20,0), 
	"USER_ID" NUMBER(20,0), 
	"PRODUCT_ID" NUMBER(20,0),
    CONSTRAINT "USER_PRODUCT_PK" PRIMARY KEY ("ID")
   );
CREATE SEQUENCE "NEXT_ID_USER_PRODUCT"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
create or replace TRIGGER USER_PRODUCT_TRG 
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

-- DDL Table Orders
CREATE TABLE "ORDERS" 
   (	"ID" NUMBER(20,0), 
	"TOTAL" NUMBER(20,0), 
	"STATUS" NUMBER(10,0), 
	"CREATED_AT" DATE, 
	"CREATED_BY" NUMBER(20,0),
    CONSTRAINT "ORDER_PK" PRIMARY KEY ("ID")
   );
CREATE SEQUENCE "NEXT_ID_ORDERS"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 81 CACHE 20 NOORDER  NOCYCLE ;
create or replace TRIGGER ORDERS_TRG 
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

-- DDL Table ORDER_DETAIL
CREATE TABLE "ORDER_DETAIL" 
   (	"ID" NUMBER(20,0), 
	"ORDER_ID" NUMBER(20,0), 
	"PRODUCT_ID" NUMBER(20,0), 
	"QUANTITY" NUMBER(20,0), 
	"PRICE" NUMBER(20,0),
    CONSTRAINT "ORDER_DETAIL_PK" PRIMARY KEY ("ID")
   );
CREATE SEQUENCE "NEXT_ID_ORDER_DETAIL"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 81 CACHE 20 NOORDER  NOCYCLE ;
create or replace TRIGGER ORDER_DETAIL_TRG 
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