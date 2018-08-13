--2.1 SELECT
SELECT *
FROM EMPLOYEE;
/
SELECT *
FROM EMPLOYEE
WHERE LASTNAME = 'King';
/
SELECT *
FROM EMPLOYEE
WHERE FIRSTNAME = 'Andrew'
  AND REPORTSTO IS NULL;
/
--2.2 ORDER BY
SELECT *
FROM ALBUM
ORDER BY TITLE DESC;
/
SELECT FIRSTNAME
FROM CUSTOMER
ORDER BY CITY;
/
--2.3 INSERT INTO
INSERT INTO GENRE
VALUES (26, 'Goblin Metal');
INSERT INTO GENRE
VALUES (27, 'Christmas Satire');
/
INSERT INTO EMPLOYEE
VALUES (9,
        'Rohrbach',
        'Zachary',
        'Pen Tester Overlord',
        1,
        TO_DATE('1762-2-28 00:00:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2020-8-14 00:00:00', 'yyyy-mm-dd hh24:mi:ss'),
        '5048 Rockefeller Road',
        'Port-Cartier',
        'AB',
        'Québec',
        'T5K 2N1',
        '+1 (315) 808-3606',
        '+1 (407) 508-3543	',
        'overlord@chinookcorp.com');
INSERT INTO EMPLOYEE
VALUES (10,
        'Ficarra',
        'Matthew',
        'Sound Technician Overlord',
        1,
        TO_DATE('1562-2-28 00:00:00', 'yyyy-mm-dd hh24:mi:ss'),
        TO_DATE('2050-8-14 00:00:00', 'yyyy-mm-dd hh24:mi:ss'),
        '75249 Crescent Oaks Trail',
        'Strömsund',
        'Jämtland',
        'Sweden',
        'T5K 2N1',
        '+242 (697) 390-7508',
        '+7 (584) 540-0407',
        'soundboi@chinookcorp.com');
/
INSERT INTO Customer (CustomerId, FirstName, LastName, Address, City, Country, PostalCode, Phone, Email, SupportRepId)
VALUES (60,
        'James',
        'Smith',
        'Theodor-Heuss-Straße 69',
        'Stuttgart',
        'Germany',
        '70174',
        '+49 0711 2843333',
        'jamessmith@surfeu.de',
        5);
INSERT INTO Customer (CustomerId, FirstName, LastName, Address, City, Country, PostalCode, Phone, Email, SupportRepId)
VALUES (61,
        'Jonathan',
        'Fulcher',
        'Rue De Femme 35',
        'Paris',
        'France',
        '70174',
        '+49 1327 2842222',
        'JonathanF@gmail.fr',
        5);
/
--2.4 UPDATE
UPDATE CUSTOMER
SET FIRSTNAME = 'Robert',
    LASTNAME  = 'Walter'
WHERE FIRSTNAME = 'Aaron'
  AND LASTNAME = 'Mitchell';
UPDATE ARTIST
SET NAME = 'CCR'
WHERE NAME = 'Credence Clearwater Revival';
/
--2.5 LIFE
SELECT *
FROM INVOICE
WHERE BILLINGADDRESS LIKE 'T%';
/
--2.6 BETWEEN
SELECT *
FROM INVOICE
WHERE TOTAL BETWEEN 15 AND 50;
SELECT *
FROM EMPLOYEE
WHERE HIREDATE BETWEEN TO_DATE('2003-06-01', 'yyyy-mm-dd') AND TO_DATE('2004-03-01', 'yyyy-mm-dd');
/
--2.7 DELETE
UPDATE CUSTOMER
SET SUPPORTREPID = NULL
WHERE FIRSTNAME = 'Robert'
  AND LASTNAME = 'Walter';
DELETE
FROM INVOICELINE
WHERE INVOICEID IN (SELECT INVOICEID
                    FROM INVOICE
                    WHERE CUSTOMERID IN (SELECT CUSTOMERID
                                         FROM CUSTOMER
                                         WHERE FIRSTNAME = 'Robert'
                                           AND LASTNAME = 'Walter'));
DELETE
FROM INVOICE
WHERE CUSTOMERID = (SELECT CUSTOMERID
                    FROM CUSTOMER
                    WHERE FIRSTNAME = 'Robert'
                      AND LASTNAME = 'Walter');
DELETE
FROM CUSTOMER
WHERE FIRSTNAME = 'Robert'
  AND LASTNAME = 'Walter';
/
--3.1
CREATE OR REPLACE FUNCTION GET_CURRENT_TIME
  RETURN TIMESTAMP
IS
  T TIMESTAMP;
  BEGIN
    T := CURRENT_TIMESTAMP;
  end;
/
CREATE OR REPLACE FUNCTION GET_NAME_LENGTH_MEDIATYPE
  RETURN NUMBER
IS
  N NUMBER;
  BEGIN

  end;

--6.1
CREATE OR REPLACE TRIGGER TR_EMPLOYEE_AFTER
  AFTER INSERT
  ON EMPLOYEE
  FOR EACH ROW
  BEGIN
    SELECT 1 INTO :NEW.REPORTSTO FROM DUAL;
  end;
/
CREATE OR REPLACE TRIGGER TR_ALBUM_UPDATE
  AFTER UPDATE
  ON ALBUM
  FOR EACH ROW
  BEGIN
    SELECT 1 INTO :NEW.ARTISTID FROM DUAL;
  end;
/
CREATE OR REPLACE TRIGGER TR_CUSTOMER_DELETE
  AFTER DELETE
  ON CUSTOMER
  FOR EACH ROW
  BEGIN
      DELETE FROM INVOICELINE WHERE INVOICEID IN (
          SELECT INVOICEID FROM INVOICE WHERE CUSTOMERID IN (
              SELECT CUSTOMERID FROM CUSTOMER WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter'
          )
      );
  end;
/
--7.1
SELECT FIRSTNAME, LASTNAME, INVOICEID
FROM CUSTOMER INNER JOIN INVOICE
    ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID
GROUP BY FIRSTNAME, LASTNAME, INVOICEID;
/
--7.2
SELECT CUSTOMER.CUSTOMERID, FIRSTNAME, LASTNAME, INVOICEID, TOTAL
FROM CUSTOMER FULL OUTER JOIN INVOICE
    ON INVOICE.CUSTOMERID = CUSTOMER.CUSTOMERID;
/
--7.3
SELECT ARTIST.NAME, ALBUM.TITLE
FROM ARTIST RIGHT JOIN ALBUM
    ON ARTIST.ARTISTID = ALBUM.ARTISTID;
/
--7.4
SELECT * FROM ARTIST CROSS JOIN ALBUM ORDER BY ARTIST.NAME;
--7.5
SELECT E1.FIRSTNAME, E1.LASTNAME, 'WORKS FOR', E2.FIRSTNAME, E2.LASTNAME
FROM EMPLOYEE E1, EMPLOYEE E2
WHERE E1.REPORTSTO = E2.EMPLOYEEID;
