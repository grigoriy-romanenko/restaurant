CREATE TABLE categories (
  id    IDENTITY,
  title VARCHAR2(50) UNIQUE NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE menuitems (
  id       IDENTITY,
  title    VARCHAR2(50) UNIQUE NOT NULL,
  category BIGINT              NOT NULL,
  price    BIGINT              NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (category) REFERENCES categories (id)
);

CREATE TABLE roles (
  id    IDENTITY,
  title VARCHAR2(20) UNIQUE NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE users (
  id       IDENTITY,
  username VARCHAR2(50) UNIQUE NOT NULL,
  password VARCHAR2(100)       NOT NULL,
  role     BIGINT              NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (role) REFERENCES roles (id)
);