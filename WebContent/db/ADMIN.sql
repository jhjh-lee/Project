DROP TABLE ADMIN;

CREATE TABLE ADMIN(
    AID VARCHAR2(10) PRIMARY KEY,
    APW VARCHAR2(10) NOT NULL,
    ANAME VARCHAR2(30) NOT NULL
);
INSERT INTO ADMIN VALUES ('admin','111','만수르');

-- 로그인 
SELECT * FROM ADMIN WHERE AID='admin' AND APW='111';

SELECT * FROM ADMIN WHERE AID='admin';
COMMIT;