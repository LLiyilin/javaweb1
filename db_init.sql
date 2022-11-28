
CREATE TABLE user(//创建user表
    `id` VARCHAR(30) NOT NULL,
	`name` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
	`user` VARCHAR(20),
	`password` VARCHAR(12),
	PRIMARY KEY (`id`)
)
