INSERT INTO categories (id, title) VALUES (1, 'Drinks');

INSERT INTO menuitems (title, category, price) VALUES ('Juice', 1, 10);
INSERT INTO menuitems (title, category, price) VALUES ('Milk', 1, 15);

INSERT INTO roles (id, title) VALUES (1, 'admin');
INSERT INTO roles (id, title) VALUES (2, 'user');

INSERT INTO users (username, password, role)
VALUES ('admin', '$2a$10$XQ/eRONtUv6h9hOWcPNwReWp3YJ6nEySZJJjdlCwtLRsxXsH8o5Yq', 1);
INSERT INTO users (username, password, role)
VALUES ('user', '$2a$10$/vA5621WwQ1ARxWIRyuH8uTqFjFJh10r/5E0fzWVyU40mxr7CEkG2', 2);