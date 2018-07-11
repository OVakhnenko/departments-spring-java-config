-- Table: users
CREATE TABLE users (
  id       INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL
)
  ENGINE = InnoDB
  CHARACTER SET utf8
  COLLATE utf8_general_ci
  DEFAULT CHARSET = utf8;
;

-- Table: roles
CREATE TABLE roles (
  id   INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL
)
  ENGINE = InnoDB
  CHARACTER SET utf8
  COLLATE utf8_general_ci
  DEFAULT CHARSET = utf8;
;

-- Table for mapping user and roles: user_roles
CREATE TABLE user_roles (
  user_id INT NOT NULL,
  role_id INT NOT NULL,

  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (role_id) REFERENCES roles (id),

  UNIQUE (user_id, role_id)
)
  ENGINE = InnoDB
  CHARACTER SET utf8
  COLLATE utf8_general_ci
  DEFAULT CHARSET = utf8;
;

-- Insert data

-- https://www.dailycred.com/article/bcrypt-calculator
INSERT INTO users
VALUES (1, 'admin', '$2a$10$SRikmt23vmocG0fn3lDLY.vxUdred2NMl5OpU1A89k6.2q2Oec8w.'); -- bcrypt admin 10 rounds
INSERT INTO users
VALUES (2, 'user', '$2a$10$KiT/FGjwueFIhSTNnUvGOePa.JucZA5vvT8zRCVtLqZ0sL7sae/DK'); -- bcrypt user 10 rounds

INSERT INTO roles VALUES (1, 'ROLE_USER'); -- ROLE_USER in class Constants
INSERT INTO roles VALUES (2, 'ROLE_ADMIN'); -- ROLE_ADMIN in class Constants

INSERT INTO user_roles VALUES (1, 2); -- user ADMIN has role ADMIN :)
INSERT INTO user_roles VALUES (2, 1); -- user USER has role USER :)