CREATE TABLE categories (
  id    INTEGER AUTO_INCREMENT,
  title VARCHAR2(50) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE menuitems (
  id       INTEGER AUTO_INCREMENT,
  title    VARCHAR2(50) NOT NULL,
  category INTEGER,
  price    INTEGER,
  PRIMARY KEY (id),
  FOREIGN KEY (category) REFERENCES categories (id)
);