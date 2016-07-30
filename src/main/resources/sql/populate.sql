INSERT INTO categories (id, title) VALUES (1, 'Drinks');

INSERT INTO menuitems (title, category, price) VALUES ('Juice', 1, 10);
INSERT INTO menuitems (title, category, price) VALUES ('Milk', 1, 15);

INSERT INTO roles (id, title) VALUES (1, 'admin');
INSERT INTO roles (id, title) VALUES (2, 'user');

INSERT INTO users (name, role) VALUES ('admin', 1);
INSERT INTO users (name, role) VALUES ('user', 2);