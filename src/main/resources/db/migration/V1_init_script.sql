CREATE SCHEMA `bookshelf_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE bookshelf_db;

DROP TABLE IF EXISTS roles;

CREATE TABLE roles (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(50) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


INSERT INTO roles (name)
VALUES
('ROLE_USER'),('ROLE_ADMIN');


DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(50) NOT NULL,
  password char(80) NOT NULL,
  email varchar(50) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS users_roles;

CREATE TABLE users_roles (
  user_id int NOT NULL,
  role_id int NOT NULL,

  PRIMARY KEY (user_id, role_id),

  KEY FK_ROLE_idx (role_id),

  CONSTRAINT FK_USER FOREIGN KEY (user_id)
  REFERENCES users (id)
  ON DELETE NO ACTION ON UPDATE NO ACTION,

  CONSTRAINT FK_ROLE FOREIGN KEY (role_id)
  REFERENCES roles (id)
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS authors;

CREATE TABLE persistent_logins (
    username varchar(64) not null,
    series varchar(64) not null,
    token varchar(64) not null,
    last_used timestamp not null,
    PRIMARY KEY (series)
);


DROP TABLE IF EXISTS books;

CREATE TABLE books (
	id int NOT NULL AUTO_INCREMENT,
    title varchar(2000) not null,
    description varchar(10000) not null,
    maturity_rating varchar(30),
    language varchar(20),
    image_link varchar(150),
    web_reader_link varchar(150),
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS authors;

CREATE TABLE authors (
	id int NOT NULL AUTO_INCREMENT,
    book_id int NOT NULL,
    name varchar(150) NOT NULL UNIQUE,
    PRIMARY KEY (id),

	CONSTRAINT FK_AUTHOR_BOOK FOREIGN KEY (book_id)
    REFERENCES books (id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS categories;

CREATE TABLE categories (
	id int NOT NULL AUTO_INCREMENT,
    book_id int NOT NULL,
    name varchar(150) NOT NULL,
    PRIMARY KEY (id),

	CONSTRAINT FK_CATEGORY_BOOK FOREIGN KEY (book_id)
    REFERENCES books (id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS user_books;

CREATE TABLE user_books (
	id int NOT NULL AUTO_INCREMENT,
    user_id int NOT NULL,
    book_id int NOT NULL,
    book_group varchar(150),
    add_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    close_date timestamp,
    active boolean NOT NULL DEFAULT TRUE,
    PRIMARY KEY (id),

	CONSTRAINT FK_SHELF_BOOK FOREIGN KEY (book_id)
    REFERENCES books (id)
    ON DELETE NO ACTION ON UPDATE NO ACTION,

	CONSTRAINT FK_SHELF_USER FOREIGN KEY (user_id)
    REFERENCES users (id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
);