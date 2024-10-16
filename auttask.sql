##データベースが既に存在している場合、削除する
DROP DATABASE IF EXISTS task_db;

/* データベース作成 */
CREATE DATABASE task_db;


/* ユーザマスタテーブル作成 */
CREATE TABLE task_db.m_user(
	user_id 		VARCHAR(24) PRIMARY KEY 
		NOT NULL,
	password 		VARCHAR(32) 
		NOT NULL,
	user_name 		VARCHAR(20) UNIQUE 
		NOT NULL,
	update_datetime TIMESTAMP 	DEFAULT current_timestamp 
		on update current_timestamp 
		NOT NULL
); 


/* カテゴリマスタテーブル作成 */
CREATE TABLE task_db.m_category(
	category_id 	INT 		PRIMARY KEY 
		NOT NULL 
		AUTO_INCREMENT,
	category_name 	VARCHAR(20) UNIQUE 
		NOT NULL,
	update_datetime TIMESTAMP 	DEFAULT current_timestamp 
		on update current_timestamp 
		NOT NULL
);


/* ステータスマスタテーブル作成 */
CREATE TABLE task_db.m_status(
	status_code 	CHAR(2) 	PRIMARY KEY 
		NOT NULL,
	status_name 	VARCHAR(20) UNIQUE 
		NOT NULL,
	update_datetime TIMESTAMP 	DEFAULT current_timestamp 
		on update current_timestamp 
		NOT NULL
);


/* タスクテーブル作成 */
CREATE TABLE task_db.t_task(
	task_id 		INT 		PRIMARY KEY 
		NOT NULL 
		AUTO_INCREMENT,
	task_name 		VARCHAR(50) 
		NOT NULL,
	category_id 	INT 
		NOT NULL,
	limit_date 		DATE,
	user_id 		VARCHAR(24) 
		NOT NULL,
	status_code 	CHAR(2) 
		NOT NULL,
	memo 			VARCHAR(100),
	create_datetime TIMESTAMP 	DEFAULT current_timestamp,
	update_datetime TIMESTAMP 	DEFAULT current_timestamp 
		on update current_timestamp NOT NULL,
	##以下外部キー
	FOREIGN KEY(category_id) 	REFERENCES task_db.m_category(category_id),
	FOREIGN KEY(user_id) 		REFERENCES task_db.m_user(user_id),
	FOREIGN KEY(status_code) 	REFERENCES task_db.m_status(status_code)
);


/* テスト用ユーザマスタデータ挿入 */
INSERT INTO task_db.m_user (
	user_id, 
	password, 
	user_name
	) 
VALUES (
	'admin', 
	'root', 
	'テスト用１'
);

INSERT INTO task_db.m_user (
	user_id, 
	password, 
	user_name
	) 
VALUES (
	'test2', 
	'test2pass', 
	'テスト用２'
);


/* テスト用カテゴリマスタデータ挿入 */
INSERT INTO task_db.m_category 
		(category_name) 
VALUES 	('新商品A：開発プロジェクト');

INSERT INTO task_db.m_category 
		(category_name) 
VALUES 	('既存商品B：改良プロジェクト');

INSERT INTO task_db.m_category 
		(category_name) 
VALUES 	('既存商品C：改良プロジェクト');


/* テスト用ステータスマスタデータ挿入 */
INSERT INTO task_db.m_status (
		status_code, status_name) 
VALUES (00, 		 '未着手'	);

INSERT INTO task_db.m_status (
		status_code, status_name) 
VALUES (50, 		 '着手'	  	);

INSERT INTO task_db.m_status (
		status_code, status_name) 
VALUES (99, 		 '完了'	  	);


/* テスト用タスクダミーデータ挿入 */
INSERT INTO task_db.t_task(
		task_name, 	category_id, limit_date, 	user_id, 	status_code, memo	 )
VALUES ('タスク1',	1,			 '2030-01-01', 	'admin',	00,			 'テスト');

INSERT INTO task_db.t_task(
		task_name, 	category_id, limit_date, 	user_id, 	status_code, memo	 )
VALUES ('タスク2',	1,			 null,		 	'admin',	00,			 'テスト');

INSERT INTO task_db.t_task(
		task_name, 	category_id, limit_date, 	user_id, 	status_code, memo	 )
VALUES ('タスク3',	2,			 '2030-01-01', 	'test2',	50,			 ''	 	 );

INSERT INTO task_db.t_task(
		task_name, 	category_id, limit_date, 	user_id, 	status_code, memo	 )
VALUES ('タスク4',	3,			 null,			'test2',	99,			 ''	 	 );