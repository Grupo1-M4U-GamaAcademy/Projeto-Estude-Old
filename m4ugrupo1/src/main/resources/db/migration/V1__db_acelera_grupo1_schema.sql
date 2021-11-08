CREATE DATABASE IF NOT EXISTS `v1__db_acelera_grupo1`;

CREATE TABLE `teachers` (
  `teachers_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `last_name` varchar(128) NOT NULL,
  `email` varchar(128) NOT NULL,
  `description` varchar(256) NOT NULL,
  `details` varchar(1024) DEFAULT NULL,
  `image_url` varchar(1024) NOT NULL,
  PRIMARY KEY (`teachers_id`)
) ENGINE=InnoDB;
CREATE TABLE `user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(128) NOT NULL,
  `password` varchar(256) NOT NULL,
  `name` varchar(128) NOT NULL,
  `lastname` varchar(128) NOT NULL,
  `email` varchar(128) NOT NULL,
  `date` date NOT NULL,
  `details` varchar(1024) DEFAULT NULL,
  `image_url` varchar(1024) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB;
CREATE TABLE `authentication_user_group` (
  `auth_user_group_id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(128) NOT NULL,
  `auth_group` varchar(128) NOT NULL,
  PRIMARY KEY (`auth_user_group_id`),
  UNIQUE KEY `username` (`username`,`auth_group`),
  CONSTRAINT `user_auth_user_group_fk` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB;
CREATE TABLE `course` (
  `course_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `description` varchar(256) NOT NULL,
  `details` varchar(1024) NOT NULL,
  `difficulty` varchar(128) NOT NULL,
  `teachers_id` bigint NOT NULL,
  `url` varchar(1024) NOT NULL,
  `image_url` varchar(1024) NOT NULL,
  PRIMARY KEY (`course_id`),
  UNIQUE KEY `name` (`name`),
  KEY `curso_fk` (`teachers_id`),
  CONSTRAINT `course_fk` FOREIGN KEY (`teachers_id`) REFERENCES `teachers` (`teachers_id`)
) ENGINE=InnoDB;
CREATE TABLE `registration` (
  `registration_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `course_id` bigint NOT NULL,
  `registration_date` date NOT NULL,
  PRIMARY KEY (`registration_id`),
  KEY `enrollment_user_fk` (`user_id`),
  KEY `enrollment_course_fk` (`course_id`),
  CONSTRAINT `enrollment_course_fk` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`),
  CONSTRAINT `enrollment_user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB;
