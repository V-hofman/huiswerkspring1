

DROP TABLE IF EXISTS AUTHOR;
CREATE TABLE AUTHOR
(
    authorID INTEGER AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    email VARCHAR(50),
    gender CHAR
);

DROP TABLE IF EXISTS BOOK;
CREATE TABLE BOOK
(
    ISBN INTEGER AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    qty_in_stock INTEGER NOT NULL,
    authorID INTEGER,
    CONSTRAINT authorID FOREIGN KEY(authorID) REFERENCES Author(authorID)

);




INSERT INTO Book ( name,price, qty_in_stock) VALUES ( 'Het diner',  5.00, 4);
INSERT INTO Book ( name, price, qty_in_stock) VALUES ( 'Het avondeten',   4.50, 8);
INSERT INTO Book ( name, price, qty_in_stock) VALUES ( 'Het lunch',  7.00, 3);