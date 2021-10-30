CREATE DATABASE `db_m4u_grupo1`;
USE `db_m4u_grupo1`;
CREATE TABLE `student` (
  `id_student` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `age` int NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `id_course` int DEFAULT NULL,
  PRIMARY KEY (`id_student`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `address` (
  `id_address` int NOT NULL AUTO_INCREMENT,
  `street` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `zipcode` int NOT NULL,
  `country` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `id_student` int NOT NULL,
  PRIMARY KEY (`id_address`),
  KEY `fk_address_id_student` (`id_student`),
  CONSTRAINT `fk_address_id_student` FOREIGN KEY (`id_student`) REFERENCES `student` (`id_student`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `user` (
  `id_user` int NOT NULL AUTO_INCREMENT,
  `user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `id_student` int NOT NULL,
  PRIMARY KEY (`id_user`),
  KEY `fk_user_id_student` (`id_student`),
  CONSTRAINT `fk_user_id_student` FOREIGN KEY (`id_student`) REFERENCES `student` (`id_student`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `course` (
  `id_course` int NOT NULL AUTO_INCREMENT,
  `discipline` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `id_student` int NOT NULL,
  PRIMARY KEY (`id_course`),
  KEY `fk_course_id_student` (`id_student`),
  CONSTRAINT `fk_course_id_student` FOREIGN KEY (`id_student`) REFERENCES `student` (`id_student`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

