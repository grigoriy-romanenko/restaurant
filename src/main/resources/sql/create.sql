CREATE TABLE categories (
  id    INTEGER AUTO_INCREMENT,
  title VARCHAR2(50) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE menuitems (
  id       INTEGER AUTO_INCREMENT,
  title    VARCHAR2(50) NOT NULL,
  category INTEGER      NOT NULL,
  price    INTEGER      NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (category) REFERENCES categories (id)
);

CREATE TABLE roles (
  id    INTEGER AUTO_INCREMENT,
  title VARCHAR2(20) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE users (
  id       INTEGER AUTO_INCREMENT,
  username VARCHAR2(50)  UNIQUE NOT NULL,
  password VARCHAR2(100) NOT NULL,
  role     INTEGER       NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (role) REFERENCES roles (id)
);