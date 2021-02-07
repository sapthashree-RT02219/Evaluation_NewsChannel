CREATE DATABASE `news`;

CREATE TABLE `news_channel` (
  `id` int NOT NULL,
  `channel_names` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `news_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `channel_id` int DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `active_status` int DEFAULT NULL,
  `type` int DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `popular_news` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `type` int DEFAULT NULL,
  `description` varchar(10000) DEFAULT NULL,
  `thumbnail` longblob,
  PRIMARY KEY (`id`)
);

CREATE TABLE `top_news` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `type` int DEFAULT NULL,
  `description` varchar(10000) DEFAULT NULL,
  `thumbnail` longblob,
  PRIMARY KEY (`id`)
) ;

CREATE TABLE `bookmark_news` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `type` int DEFAULT NULL,
  `description` varchar(10000) DEFAULT NULL,
  `thumbnail` longblob,
  PRIMARY KEY (`id`)
);


