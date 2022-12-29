
CREATE TABLE user (
    `id` VARCHAR(40) NOT NULL,
	`name` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
	`user` VARCHAR(20),
	`password` VARCHAR(100),
	PRIMARY KEY (`id`)
)
