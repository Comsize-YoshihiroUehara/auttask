DROP DATABASE task_db;

CREATE DATABASE task_db;

CREATE TABLE task_db.m_user(
	user_id VARCHAR(24) PRIMARY KEY NOT NULL,
	password VARCHAR(32) NOT NULL,
	user_name VARCHAR(20) UNIQUE NOT NULL,
	update_datetime TIMESTAMP DEFAULT current_timestamp on update current_timestamp NOT NULL
); 

CREATE TABLE task_db.m_category(
	category_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	category_name VARCHAR(20) UNIQUE NOT NULL,
	update_datetime TIMESTAMP DEFAULT current_timestamp on update current_timestamp NOT NULL
);

CREATE TABLE task_db.m_status(
	status_code CHAR(2) PRIMARY KEY NOT NULL,
	status_name VARCHAR(20) UNIQUE NOT NULL,
	update_datetime TIMESTAMP DEFAULT current_timestamp on update current_timestamp NOT NULL
);

CREATE TABLE task_db.t_task(
	task_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	task_name VARCHAR(50) NOT NULL,
	category_id INT NOT NULL,
	limit_date DATE,
	user_id VARCHAR(24) NOT NULL,
	status_code CHAR(2) NOT NULL,
	memo VARCHAR(100),
	create_datetime TIMESTAMP DEFAULT current_timestamp,
	update_datetime TIMESTAMP DEFAULT current_timestamp on update current_timestamp NOT NULL,
	FOREIGN KEY(category_id) REFERENCES task_db.m_category(category_id),
	FOREIGN KEY(user_id) REFERENCES task_db.m_user(user_id),
	FOREIGN KEY(status_code) REFERENCES task_db.m_status(status_code)
);

USE task_db;

INSERT INTO m_user (user_id, password, user_name) VALUES ('admin', 'root', 'テスト用');

INSERT into m_category (category_name) VALUES ('新商品A：開発プロジェクト');
INSERT into m_category (category_name) VALUES ('既存商品B：改良プロジェクト');

INSERT into m_status (status_code, status_name) VALUES (00, '未着手');
INSERT into m_status (status_code, status_name) VALUES (50, '着手');
INSERT into m_status (status_code, status_name) VALUES (99, '完了');

UPDATE t_task SET
