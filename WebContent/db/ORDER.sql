DROP TABLE ORDERS CASCADE CONSTRAINTS;
DROP SEQUENCE ORDERS_SEQ ;
CREATE TABLE ORDERS(
    ONO VARCHAR2(30) PRIMARY KEY,
    MID VARCHAR2(30) REFERENCES MEMBER(MID),
    ONAME VARCHAR2(30) NOT NULL,
    OPOSTALCODE VARCHAR2(100),
    OADDRESS VARCHAR2(255) NOT NULL,
    OTEL VARCHAR2(30) NOT NULL,
    OEMAIL VARCHAR2(100),
    ORDATE DATE DEFAULT SYSDATE
);
CREATE SEQUENCE ORDERS_SEQ MAXVALUE 999 NOCACHE NOCYCLE;

DROP TABLE ORDERDETAIL;
DROP SEQUENCE ORDERDETAIL_SEQ;
CREATE TABLE ORDERDETAIL (
    ODNO VARCHAR2(30) PRIMARY KEY,
    ONO VARCHAR2(30) REFERENCES ORDERS(ONO) NOT NULL,
    PCODE VARCHAR2(30) REFERENCES PRODUCT(PCODE) NOT NULL,
    CNT NUMBER(5),
    COST NUMBER(9)
);
CREATE SEQUENCE ORDERDETAIL_SEQ MAXVALUE 999 NOCACHE NOCYCLE;

-- 주문 테이블
INSERT INTO ORDERS (ONO,MID,ONAME,OADDRESS,OTEL) 
    VALUES (ORDERS_SEQ.NEXTVAL, 'aaa','지단', '프랑스', '01000000000');

SELECT * FROM ORDERS WHERE MID='aaa';
select 'ORDER'||TRIM(TO_CHAR(PRODUCT_SEQ.nextval,'000')) from dual;
select 'ORDER'||TRIM(TO_CHAR(PRODUCT_SEQ.CURRVAL,'000')) from dual;
-- 주문 상세 테이블
SELECT * FROM ORDERDETAIL;
INSERT INTO ORDERDETAIL (ODNO, ONO, PCODE, CNT, COST) 
    VALUES 
    (ORDERDETAIL_SEQ.NEXTVAL,'ORDER'||TRIM(TO_CHAR(ORDERS_SEQ.CURRVAL,'000')), 'GOGI003', '3', '450000');

    
-- 주문 확인 보기 (mid 로 )
SELECT O.ONO ONO, O.MID MID, O.ONAME ONAME, O.OADDRESS OADDRESS, O.OTEL OTEL, O.ORDATE ORDATE, 
    D.ODNO ODNO, D.CNT CNT, D.COST COST, P.PCODE PCODE, P.PNAME PNAME
    FROM ORDERS O, ORDERDETAIL D,  PRODUCT P WHERE D.ONO= O.ONO AND P.PCODE = D.PCODE AND O.MID='aaa123';
    


SELECT O.ONO ONO, O.MID MID, O.ONAME ONAME, O.OADDRESS OADDRESS, O.OTEL OTEL, O.ORDATE ORDATE, 
    D.ODNO ODNO, D.CNT CNT, D.COST COST, P.PCODE PCODE,(D.CNT*P.PPRICE) PRICE
    FROM ORDERS O, ORDERDETAIL D,  PRODUCT P WHERE D.ONO= O.ONO AND P.PCODE = D.PCODE;
    
-- 상품 정보(파일이미지, 파일이름) ,주문일자, 주문디테일번호, 주문금액,수량, 

SELECT P.PFILENAME, P.PNAME, O.ONO, O.MID, D.ODNO, D.CNT, D.COST, P.PPRICE 
    FROM ORDERS O, ORDERDETAIL D,  PRODUCT P WHERE D.ONO= O.ONO AND P.PCODE = D.PCODE AND O.MID='aaa123' ORDER BY O.ORDATE;
 
 
-- 내 주문 리스트
SELECT * FROM (SELECT ROWNUM RN , A.* FROM
    (SELECT P.PFILENAME, P.PNAME, O.ONO, O.MID, D.ODNO, D.CNT, D.COST, O.ORDATE, P.PCODE  
        FROM ORDERS O, ORDERDETAIL D,  PRODUCT P 
        WHERE D.ONO= O.ONO AND P.PCODE = D.PCODE AND O.MID='bbb123' 
        ORDER BY O.ORDATE)A)
    WHERE RN BETWEEN 1 AND 10;





-- 주문리스트 페이징처리를 위한 개수
SELECT COUNT(*) FROM ORDERS O, ORDERDETAIL D WHERE D.ONO= O.ONO AND O.MID='aaa123';

-- 주문상세 페이지 출력
SELECT P.PFILENAME, P.PNAME, O.ONO, O.MID, D.ODNO, D.CNT, D.COST, O.ORDATE, P.PCODE  
        FROM ORDERS O, ORDERDETAIL D,  PRODUCT P 
        WHERE D.ONO= O.ONO AND P.PCODE = D.PCODE AND O.ONO='ORDER001' 
        ORDER BY O.ORDATE;
-- 주문상세 페이지 배송자 정보 출력
SELECT * FROM ORDERS WHERE ONO='ORDER001';
COMMIT;