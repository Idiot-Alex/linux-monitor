/*Table structure for table `dashboard_user` */
CREATE TABLE IF NOT EXISTS `admin_user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'primary key id',
  `user_name` varchar(64) NOT NULL COMMENT 'user name',
  `password` varchar(128) DEFAULT NULL COMMENT 'user password',
  `enabled` tinyint(4) NOT NULL COMMENT 'delete or not',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update time',
  PRIMARY KEY (`id`)
);

/* Table structure for table `server`*/
CREATE TABLE IF NOT EXISTS `server` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'primary key id',
  `host` varchar(128) NOT NULL COMMENT 'server host',
  `user` varchar(128) DEFAULT NULL COMMENT 'server user',
  `password` varchar(128) DEFAULT NULL COMMENT 'server user',
  `port` int NOT NULL COMMENT 'server port',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update time',
  PRIMARY KEY (`id`)
);