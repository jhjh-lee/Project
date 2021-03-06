DROP TABLE CART;
CREATE TABLE CART (
    CNO NUMBER(9) PRIMARY KEY,
    MID VARCHAR2(30) REFERENCES MEMBER(MID),
    PCODE VARCHAR2(30) REFERENCES PRODUCT(PCODE),
    CNT NUMBER(5)
);
DROP SEQUENCE CART_SEQ;
CREATE SEQUENCE CART_SEQ MAXVALUE 999 NOCACHE NOCYCLE;
SELECT * FROM CART;

-- 카트 담기
INSERT INTO CART (CNO,MID,PCODE,CNT) VALUES (CART_SEQ.NEXTVAL, 'aaa','001','1');

-- 카트 목록
SELECT * FROM CART ORDER BY CNO;

SELECT C.CNO CNO, C.MID MID, P.PFILENAME PFILENAME, P.PNAME PNAME, P.PCODE PCODE,
        C.CNT CNT, P.PPRICE PPRICE, (C.CNT*P.PPRICE) COST 
    FROM CART C, PRODUCT P 
        WHERE P.PCODE = C.PCODE AND C.MID='ses2155' ORDER BY C.CNO;
-- 선택카트

SELECT C.CNO CNO, C.MID MID, P.PFILENAME PFILENAME, P.PNAME PNAME, P.PCODE PCODE,
        C.CNT CNT, P.PPRICE PPRICE, (C.CNT*P.PPRICE) COST 
    FROM CART C, PRODUCT P 
        WHERE P.PCODE = C.PCODE AND C.CNO='48' ORDER BY C.CNO;

SELECT * FROM (SELECT ROWNUM RN, A.* FROM
    (SELECT C.CNO, P.PCODE, C.CNT, P.PPRICE, (C.CNT*P.PPRICE) COST FROM CART C, PRODUCT P WHERE P.PCODE = C.PCODE AND MID='ses2155' ORDER BY C.CNO)A)
        WHERE RN BETWEEN 1 AND 5;

-- 카트 선택 목록 가져오기 위한 dto 
SELECT C.CNO, C.MID, P.PCODE, C.CNT,P.PNAME, P.PPRICE, (C.CNT*P.PPRICE) COST, P.PFILENAME 
    FROM CART C, PRODUCT P
    WHERE P.PCODE = C.PCODE AND CNO='';       
-- 카트 목록 삭제
DELETE FROM CART WHERE CNO = '3';

-- 카트 업데이트
UPDATE CART SET CNT='5' WHERE CNO='42';

-- 주문후 카트 삭제

DELETE FROM CART WHERE MID='aaa123';
SELECT * FROM CART WHERe MID='bbb123'; 
COMMIT;