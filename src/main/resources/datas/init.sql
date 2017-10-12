CREATE USER rubrum WITH PASSWORD 'rubrum';
CREATE DATABASE rubrum;
GRANT ALL PRIVILEGES ON DATABASE rubrum TO rubrum;

INSERT INTO users(id, username, password, email) VALUES ('71f7a21a-4bd7-4c88-96a1-8f521393dce7', 'jack', 'password', 'jack@email.com');

INSERT INTO roles (id, authority) VALUES  ('30b93a19-da04-4524-aac0-a56870892c7f', 'ROLE_ADMIN');
INSERT INTO roles (id, authority) VALUES  ('20b43399-c1be-403c-8040-9bf48b2c5d50', 'ROLE_EDITOR');
INSERT INTO roles (id, authority) VALUES  ('66069f18-1c8f-4d3b-96c7-b1b6df3e2c9c', 'ROLE_USER');

INSERT INTO user_role (id, id_user, id_role) VALUES ('9ee3cdf6-8239-488f-b0f9-5fd3895af321', '71f7a21a-4bd7-4c88-96a1-8f521393dce7', '30b93a19-da04-4524-aac0-a56870892c7f');
