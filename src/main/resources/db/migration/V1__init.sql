-- Users Table creation
CREATE TABLE users
(
  id UUID PRIMARY KEY NOT NULL,
  username VARCHAR(128) NOT NULL,
  password VARCHAR(60) NOT NULL,
  email VARCHAR(256) NOT NULL
);
CREATE UNIQUE INDEX unique_email ON users (email);
CREATE UNIQUE INDEX unique_username ON users (username);

-- Roles table creation

CREATE TABLE roles
(
  id UUID PRIMARY KEY NOT NULL,
  authority VARCHAR(64)
);

-- User_role table creation, to associate user with roles

CREATE TABLE user_role
(
    id UUID PRIMARY KEY NOT NULL,
    id_user UUID NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    id_role UUID NOT NULL REFERENCES roles (id)
);

INSERT INTO roles (id, authority) VALUES  ('30b93a19-da04-4524-aac0-a56870892c7f', 'ROLE_ADMIN');
INSERT INTO roles (id, authority) VALUES  ('66069f18-1c8f-4d3b-96c7-b1b6df3e2c9c', 'ROLE_USER');