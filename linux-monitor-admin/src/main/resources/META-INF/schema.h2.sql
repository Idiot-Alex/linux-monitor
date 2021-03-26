/*Table structure for table `dashboard_user` */
CREATE TABLE IF NOT EXISTS `admin_user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'primary key id',
  `user_name` varchar(64) NOT NULL COMMENT 'user name',
  `password` varchar(128) DEFAULT NULL COMMENT 'user password',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update time',
  PRIMARY KEY (`id`)
);

/* Table structure for table `server`*/
CREATE TABLE IF NOT EXISTS `server` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'primary key id',
  `host` varchar(128) NOT NULL COMMENT 'server host',
  `user` varchar(128) DEFAULT NULL COMMENT 'server user',
  `password` varchar(128) DEFAULT NULL COMMENT 'user password',
  `port` int NOT NULL COMMENT 'server port',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update time',
  PRIMARY KEY (`id`)
);

/* admin user */
INSERT INTO `admin_user` (`id`, `user_name`,`password`, `create_time`, `update_time`) VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', '2021-03-18 14:39:56','2021-03-18 15:43:43');

/* server */
INSERT INTO `server` (`id`, `host`, `user`, `password`, `port`, `create_time`, `update_time`) VALUES (1, '127.0.0.1', 'test', '123456', 22, '2021-03-18 14:39:56','2021-03-18 15:43:43');
INSERT INTO `server` (`id`, `host`, `user`, `password`, `port`, `create_time`, `update_time`) VALUES (2, '121.42.160.109', 'root', '272377@zx', 22, '2021-03-18 14:39:56','2021-03-18 15:43:43');
INSERT INTO `server` (`id`, `host`, `user`, `password`, `port`, `create_time`, `update_time`) VALUES (3, '47.96.30.54', 'root', 'Chimoo2019', 22, '2021-03-18 14:39:56','2021-03-18 15:43:43');