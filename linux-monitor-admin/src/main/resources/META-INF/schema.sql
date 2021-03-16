CREATE DATABASE  IF NOT EXISTS  `linux-monitor`  DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;

USE `linux-monitor`;

/*Table structure for table `admin_user` */
CREATE TABLE IF NOT EXISTS `admin_user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'primary key id',
  `user_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'user name',
  `password` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'user password',
  `enabled` tinyint(4) NOT NULL COMMENT 'delete or not',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/* Table structure for table `server`*/
CREATE TABLE IF NOT EXISTS `server` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'primary key id',
  `host` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'server host',
  `user` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'server user',
  `password` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'server user',
  `port` int NOT NULL COMMENT 'server port',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;