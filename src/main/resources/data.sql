CREATE SCHEMA IF NOT EXISTS "PUBLIC";

DROP TABLE IF EXISTS user;

CREATE TABLE user (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    firstname VARCHAR(250),
    lastname VARCHAR(250),
   age INT
);

INSERT INTO user (firstname, lastname, age) VALUES
('jim', 'carry', 20),
('leo', 'messi', 15),
('cris', 'ronaldo', 65),
('mok', 'snow', 12),
('leo', 'dicaprio', 34),
('brad', 'pit', 44),
('anjel', 'juli', 32),
('scarllet', 'youh', 25),
('kevin', 'trump', 16),
('shab', 'dolazim', 19),
('asghar', 'farhadi', 33),
('jimmi', 'jarmush', 35),
('farang', 'is', 67),
('xavi', 'hernandez', 47),
('rafael', 'nadal', 23),
('big', 'boy', 11),
('madar', 'tereza', 45),
('jac', 'obama', 45),
('rahim', 'strling', 66),
('david', 'beckham', 44),
('zein', 'zeinodin', 33),
('toni', 'montana', 19),
('toni', 'kross', 34),
('alen', 'delon', 10),
('shadi', 'ghasem', 70),
('leon', 'dejageh', 69),
('fan', 'basten', 25);