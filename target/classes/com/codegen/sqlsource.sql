CREATE TABLE `xx_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `phone` varchar(11) NOT NULL,
  `birthday` timestamp NULL DEFAULT NULL,
  `memberShipLevel` int(11) NOT NULL,
  `growthValue` int(11) NOT NULL,
  `integral` int(11) NOT NULL,
  `areaCode` varchar(64) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `mail` varchar(64) DEFAULT NULL,
  `freezeIntegral` int(11) NOT NULL,
  `unfreezeIntegral` int(11) NOT NULL,
  `gender` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
)  COMMENT='首页积分商城表';