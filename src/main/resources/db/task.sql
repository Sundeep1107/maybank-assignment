CREATE DATABASE `testdb`;

CREATE TABLE `task` (
  `task_id` bigint NOT NULL AUTO_INCREMENT,
  `completed` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`task_id`),
  UNIQUE KEY `UK20c7byw48jcthxnvt67bbvijq` (`title`)
);
