
<<<<<<< HEAD
CREATE TABLE user(//创建user表
    `id` VARCHAR(40) NOT NULL,//UUID最长32位
	`name` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
	`user` VARCHAR(20),
	`password` VARCHAR(100),//因为MD5加密，所以更长
	PRIMARY KEY (`id`)
)
=======
CREATE TABLE user (
    `id` VARCHAR(40) NOT NULL,
	`name` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
	`user` VARCHAR(20),
	`password` VARCHAR(100),
	PRIMARY KEY (`id`)
)
>>>>>>> ff52b43 (期末修改2)
