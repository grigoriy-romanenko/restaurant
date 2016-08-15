INSERT INTO categories (id, title) VALUES (1, 'First meals');
INSERT INTO categories (id, title) VALUES (2, 'Desserts');
INSERT INTO categories (id, title) VALUES (3, 'Drinks');

INSERT INTO menuitems (title, category, price) VALUES ('Bouillon', 1, 20);
INSERT INTO menuitems (title, category, price) VALUES ('Borsch', 1, 20);
INSERT INTO menuitems (title, category, price) VALUES ('Cake', 2, 10);
INSERT INTO menuitems (title, category, price) VALUES ('Cookie', 2, 5);
INSERT INTO menuitems (title, category, price) VALUES ('Juice', 3, 10);
INSERT INTO menuitems (title, category, price) VALUES ('Milk', 3, 10);

INSERT INTO roles (id, title) VALUES (1, 'admin');
INSERT INTO roles (id, title) VALUES (2, 'user');

INSERT INTO users (username, password, role)
VALUES ('admin', '$2a$10$XQ/eRONtUv6h9hOWcPNwReWp3YJ6nEySZJJjdlCwtLRsxXsH8o5Yq', 1);
INSERT INTO users (username, password, role)
VALUES ('user', '$2a$10$/vA5621WwQ1ARxWIRyuH8uTqFjFJh10r/5E0fzWVyU40mxr7CEkG2', 2);