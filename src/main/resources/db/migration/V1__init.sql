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
    id_user UUID NOT NULL REFERENCES users (id),
    id_role UUID NOT NULL REFERENCES roles (id)
);
