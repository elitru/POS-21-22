DROP TABLE IF EXISTS emp;
DROP TABLE IF EXISTS dept;
DROP TABLE IF EXISTS salgrade;


CREATE TABLE emp (
                     empno      INTEGER NOT NULL,
                     ename      VARCHAR(50),
                     job        VARCHAR(30),
                     mgr        INTEGER,
                     hiredate   DATE,
                     sal        INTEGER,
                     comm       INTEGER,
                     deptno     INTEGER,
                     PRIMARY KEY (empno)
);

CREATE TABLE dept(
                     deptno     INTEGER,
                     dname      VARCHAR(50),
                     loc        VARCHAR(50),
                     PRIMARY KEY (deptno)
);

CREATE TABLE salgrade(
                         grade      INTEGER,
                         losal      INTEGER,
                         hisal      INTEGER
);

-- emp to dept
ALTER TABLE emp
    ADD CONSTRAINT fk_emp_dept FOREIGN KEY (deptno) REFERENCES dept (deptno);

-- emp to emp
ALTER TABLE emp
    ADD CONSTRAINT fk_emp_emp FOREIGN KEY (mgr) REFERENCES emp (empno);

INSERT INTO dept VALUES (10, 'ACCOUNTING', 'NEW YORK');
INSERT INTO dept VALUES (20, 'RESEARCH',   'DALLAS');
INSERT INTO dept VALUES (30, 'SALES',      'CHICAGO');
INSERT INTO dept VALUES (40, 'OPERATIONS', 'BOSTON');

INSERT INTO emp VALUES (7839, 'KING',   'PRESIDENT', NULL, TO_DATE('17-11-1981', 'DD-MM-YYYY'), 5000, NULL, 10);
INSERT INTO emp VALUES (7566, 'JONES',  'MANAGER',   7839, TO_DATE('02-04-1981', 'DD-MM-YYYY'), 2975, NULL, 20);
INSERT INTO emp VALUES (7788, 'SCOTT',  'ANALYST',   7566, TO_DATE('19-04-1987', 'DD-MM-YYYY'), 3000, NULL, 20);
INSERT INTO emp VALUES (7876, 'ADAMS',  'CLERK',     7788, TO_DATE('23-05-1987', 'DD-MM-YYYY'), 1100, NULL, 20);
INSERT INTO emp VALUES (7902, 'FORD',   'ANALYST',   7566, TO_DATE('03-12-1981', 'DD-MM-YYYY'), 3000, NULL, 20);
INSERT INTO emp VALUES (7369, 'SMITH',  'CLERK',     7902, TO_DATE('17-12-1980', 'DD-MM-YYYY'),  800, NULL, 20);
INSERT INTO emp VALUES (7698, 'BLAKE',  'MANAGER',   7839, TO_DATE('01-05-1981', 'DD-MM-YYYY'), 2850, NULL, 30);
INSERT INTO emp VALUES (7499, 'ALLEN',  'SALESMAN',  7698, TO_DATE('20-02-1981', 'DD-MM-YYYY'), 1600,  300, 30);
INSERT INTO emp VALUES (7521, 'WARD',   'SALESMAN',  7698, TO_DATE('22-02-1981', 'DD-MM-YYYY'), 1250,  500, 30);
INSERT INTO emp VALUES (7654, 'MARTIN', 'SALESMAN',  7698, TO_DATE('28-09-1981', 'DD-MM-YYYY'), 1250, 1400, 30);
INSERT INTO emp VALUES (7844, 'TURNER', 'SALESMAN',  7698, TO_DATE('08-09-1981', 'DD-MM-YYYY'), 1500,    0, 30);
INSERT INTO emp VALUES (7900, 'JAMES',  'CLERK',     7698, TO_DATE('03-12-1981', 'DD-MM-YYYY'),  950, NULL, 30);
INSERT INTO emp VALUES (7782, 'CLARK',  'MANAGER',   7839, TO_DATE('09-06-1981', 'DD-MM-YYYY'), 2450, NULL, 10);
INSERT INTO emp VALUES (7934, 'MILLER', 'CLERK',     7782, TO_DATE('23-01-1982', 'DD-MM-YYYY'), 1300, NULL, 10);

INSERT INTO salgrade VALUES (1,  700, 1200);
INSERT INTO salgrade VALUES (2, 1201, 1400);
INSERT INTO salgrade VALUES (3, 1401, 2000);
INSERT INTO salgrade VALUES (4, 2001, 3000);
INSERT INTO salgrade VALUES (5, 3001, 9999);