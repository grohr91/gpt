/*
SQLyog Community v12.2.4 (64 bit)
MySQL - 5.7.9-log : Database - gpt
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`gpt` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `gpt`;

/*Table structure for table `desafio` */

DROP TABLE IF EXISTS `desafio`;

CREATE TABLE `desafio` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_externo` int(11) NOT NULL,
  `id_configuracao` int(11) NOT NULL,
  `nm_desafio` varchar(255) NOT NULL,
  `fg_ativo` tinyint(1) NOT NULL DEFAULT '1',
  `dt_ultima_sincronizacao` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_desafio_sys_configuracao1_idx` (`id_configuracao`),
  CONSTRAINT `fk_desafio_sys_configuracao1` FOREIGN KEY (`id_configuracao`) REFERENCES `sys_configuracao` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `desafio` */

insert  into `desafio`(`id`,`id_externo`,`id_configuracao`,`nm_desafio`,`fg_ativo`,`dt_ultima_sincronizacao`) values 
(1,1,1,'Produtividade',1,'2016-06-14 17:58:39'),
(2,2,1,'Pontualidade',1,'2016-06-14 17:58:58'),
(3,3,1,'Vendas',1,'2016-06-14 18:23:46');

/*Table structure for table `emblema` */

DROP TABLE IF EXISTS `emblema`;

CREATE TABLE `emblema` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nm_emblema` varchar(255) NOT NULL,
  `vl_emblema` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `emblema` */

/*Table structure for table `grupo` */

DROP TABLE IF EXISTS `grupo`;

CREATE TABLE `grupo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_externo` int(11) DEFAULT NULL,
  `id_configuracao` int(11) NOT NULL,
  `nm_grupo` varchar(255) NOT NULL,
  `fg_ativo` tinyint(1) NOT NULL DEFAULT '1',
  `dt_ultima_sincronizacao` datetime NOT NULL,
  `xp_atual` int(11) DEFAULT NULL,
  `qt_atividades_concluidas` int(11) DEFAULT NULL,
  `qt_desafios_concluidos` int(11) DEFAULT NULL,
  `qt_metas_concluidas` int(11) DEFAULT NULL,
  `qt_emblemas` int(11) DEFAULT NULL,
  `qt_itens` int(11) DEFAULT NULL,
  `vl_dinehiro` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_grupo_sys_configuracao1_idx` (`id_configuracao`),
  CONSTRAINT `fk_grupo_sys_configuracao1` FOREIGN KEY (`id_configuracao`) REFERENCES `sys_configuracao` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=337 DEFAULT CHARSET=utf8;

/*Data for the table `grupo` */

insert  into `grupo`(`id`,`id_externo`,`id_configuracao`,`nm_grupo`,`fg_ativo`,`dt_ultima_sincronizacao`,`xp_atual`,`qt_atividades_concluidas`,`qt_desafios_concluidos`,`qt_metas_concluidas`,`qt_emblemas`,`qt_itens`,`vl_dinehiro`) values 
(332,NULL,1,'DESENVOLVIMENTO',0,'2016-06-16 00:39:07',NULL,0,0,0,0,0,1000),
(333,46,1,'Idealogic',1,'2016-06-16 00:39:07',NULL,0,0,0,0,0,1000),
(334,47,1,'Kopp',1,'2016-06-16 00:39:07',NULL,0,0,0,0,0,1000),
(335,49,1,'Simbius',1,'2016-06-16 00:39:06',NULL,0,0,0,0,0,1000),
(336,56,1,'SC Info',1,'2016-06-16 00:39:07',NULL,0,0,0,0,0,1000);

/*Table structure for table `grupo_atividade` */

DROP TABLE IF EXISTS `grupo_atividade`;

CREATE TABLE `grupo_atividade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_externo` int(11) NOT NULL,
  `id_grupo` int(11) NOT NULL,
  `id_desafio` int(11) NOT NULL,
  `id_configuracao` int(11) NOT NULL,
  `dt_ocorrencia` datetime DEFAULT NULL,
  `vl_atingido` float DEFAULT NULL,
  `dt_atingido` datetime DEFAULT NULL,
  `sg_atingido` varchar(45) DEFAULT NULL,
  `vl_planejado` float DEFAULT NULL,
  `dt_planejado` datetime DEFAULT NULL,
  `sg_planejado` varchar(45) DEFAULT NULL,
  `dt_ultima_sincronizacao` datetime NOT NULL,
  `fg_ativo` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_grupo_atividade_grupo1_idx` (`id_grupo`),
  KEY `fk_grupo_atividade_sys_configuracao1_idx` (`id_configuracao`),
  KEY `fk_grupo_atividade_desafio1_idx` (`id_desafio`),
  CONSTRAINT `fk_grupo_atividade_desafio1` FOREIGN KEY (`id_desafio`) REFERENCES `desafio` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_grupo_atividade_grupo1` FOREIGN KEY (`id_grupo`) REFERENCES `grupo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_grupo_atividade_sys_configuracao1` FOREIGN KEY (`id_configuracao`) REFERENCES `sys_configuracao` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `grupo_atividade` */

/*Table structure for table `grupo_individuo` */

DROP TABLE IF EXISTS `grupo_individuo`;

CREATE TABLE `grupo_individuo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_configuracao` int(11) NOT NULL,
  `id_grupo` int(11) NOT NULL,
  `id_individuo` int(11) NOT NULL,
  `fg_ativo` tinyint(1) NOT NULL DEFAULT '1',
  `dt_ultima_sincronizacao` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_grupo_individuo_grupo1_idx` (`id_grupo`),
  KEY `fk_grupo_individuo_sys_configuracao1_idx` (`id_configuracao`),
  KEY `fk_grupo_individuo_individuo1_idx` (`id_individuo`),
  CONSTRAINT `fk_grupo_individuo_grupo1` FOREIGN KEY (`id_grupo`) REFERENCES `grupo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_grupo_individuo_individuo1` FOREIGN KEY (`id_individuo`) REFERENCES `individuo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_grupo_individuo_sys_configuracao1` FOREIGN KEY (`id_configuracao`) REFERENCES `sys_configuracao` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=991 DEFAULT CHARSET=utf8;

/*Data for the table `grupo_individuo` */

insert  into `grupo_individuo`(`id`,`id_configuracao`,`id_grupo`,`id_individuo`,`fg_ativo`,`dt_ultima_sincronizacao`) values 
(859,1,332,1104,0,'2016-06-16 00:36:48'),
(860,1,332,1105,0,'2016-06-16 00:38:38'),
(861,1,332,1106,0,'2016-06-16 00:38:43'),
(862,1,332,1107,0,'2016-06-16 00:38:50'),
(863,1,332,1108,0,'2016-06-16 00:38:50'),
(864,1,333,1109,0,'2016-06-16 00:38:50'),
(865,1,332,1110,0,'2016-06-16 00:38:50'),
(866,1,332,1111,0,'2016-06-16 00:38:50'),
(867,1,332,1112,0,'2016-06-16 00:38:50'),
(868,1,332,1113,0,'2016-06-16 00:38:50'),
(869,1,333,1113,0,'2016-06-16 00:38:50'),
(870,1,332,1114,0,'2016-06-16 00:38:50'),
(871,1,333,1114,0,'2016-06-16 00:38:50'),
(872,1,334,1115,0,'2016-06-16 00:38:50'),
(873,1,332,1116,0,'2016-06-16 00:38:50'),
(874,1,335,1117,0,'2016-06-16 00:38:50'),
(875,1,335,1118,0,'2016-06-16 00:38:50'),
(876,1,332,1119,0,'2016-06-16 00:38:50'),
(877,1,332,1120,0,'2016-06-16 00:38:50'),
(878,1,334,1121,0,'2016-06-16 00:38:50'),
(879,1,334,1122,0,'2016-06-16 00:38:50'),
(880,1,332,1123,0,'2016-06-16 00:38:50'),
(881,1,332,1124,0,'2016-06-16 00:38:50'),
(882,1,333,1124,0,'2016-06-16 00:38:50'),
(883,1,333,1125,0,'2016-06-16 00:38:50'),
(884,1,332,1126,0,'2016-06-16 00:38:50'),
(885,1,332,1127,0,'2016-06-16 00:38:50'),
(886,1,333,1128,0,'2016-06-16 00:38:50'),
(887,1,334,1129,0,'2016-06-16 00:38:50'),
(888,1,334,1130,0,'2016-06-16 00:38:50'),
(889,1,336,1131,0,'2016-06-16 00:38:50'),
(890,1,333,1104,0,'2016-06-16 00:38:50'),
(891,1,334,1132,0,'2016-06-16 00:38:50'),
(892,1,334,1133,0,'2016-06-16 00:38:50'),
(893,1,334,1134,0,'2016-06-16 00:38:50'),
(894,1,333,1135,0,'2016-06-16 00:38:50'),
(895,1,334,1136,0,'2016-06-16 00:38:50'),
(896,1,334,1137,0,'2016-06-16 00:38:50'),
(897,1,334,1138,0,'2016-06-16 00:38:50'),
(898,1,332,1139,0,'2016-06-16 00:38:50'),
(899,1,334,1140,0,'2016-06-16 00:38:50'),
(900,1,334,1141,0,'2016-06-16 00:38:50'),
(901,1,334,1142,0,'2016-06-16 00:38:50'),
(902,1,332,1143,0,'2016-06-16 00:38:50'),
(903,1,336,1144,0,'2016-06-16 00:38:50'),
(904,1,336,1145,0,'2016-06-16 00:38:50'),
(905,1,332,1146,0,'2016-06-16 00:38:50'),
(906,1,333,1147,0,'2016-06-16 00:38:50'),
(907,1,332,1148,0,'2016-06-16 00:38:50'),
(908,1,332,1149,0,'2016-06-16 00:38:50'),
(909,1,332,1150,0,'2016-06-16 00:38:50'),
(910,1,332,1151,0,'2016-06-16 00:38:50'),
(911,1,334,1152,0,'2016-06-16 00:38:50'),
(912,1,332,1153,0,'2016-06-16 00:38:50'),
(913,1,332,1154,0,'2016-06-16 00:38:50'),
(914,1,332,1155,0,'2016-06-16 00:38:50'),
(915,1,332,1156,0,'2016-06-16 00:38:50'),
(916,1,332,1157,0,'2016-06-16 00:38:50'),
(917,1,332,1158,0,'2016-06-16 00:38:50'),
(918,1,332,1159,0,'2016-06-16 00:38:50'),
(919,1,332,1160,0,'2016-06-16 00:38:50'),
(920,1,332,1161,0,'2016-06-16 00:38:50'),
(921,1,332,1162,0,'2016-06-16 00:38:50'),
(922,1,332,1163,0,'2016-06-16 00:38:50'),
(923,1,332,1164,0,'2016-06-16 00:38:50'),
(924,1,332,1118,0,'2016-06-16 00:38:50'),
(925,1,332,1104,1,'2016-06-16 00:39:06'),
(926,1,332,1105,1,'2016-06-16 00:39:06'),
(927,1,332,1106,1,'2016-06-16 00:39:06'),
(928,1,332,1107,1,'2016-06-16 00:39:06'),
(929,1,332,1108,1,'2016-06-16 00:39:06'),
(930,1,333,1109,1,'2016-06-16 00:39:06'),
(931,1,332,1110,1,'2016-06-16 00:39:06'),
(932,1,332,1111,1,'2016-06-16 00:39:06'),
(933,1,332,1112,1,'2016-06-16 00:39:06'),
(934,1,332,1113,1,'2016-06-16 00:39:06'),
(935,1,333,1113,1,'2016-06-16 00:39:06'),
(936,1,332,1114,1,'2016-06-16 00:39:06'),
(937,1,333,1114,1,'2016-06-16 00:39:06'),
(938,1,334,1115,1,'2016-06-16 00:39:06'),
(939,1,332,1116,1,'2016-06-16 00:39:06'),
(940,1,335,1117,1,'2016-06-16 00:39:06'),
(941,1,335,1118,1,'2016-06-16 00:39:06'),
(942,1,332,1119,1,'2016-06-16 00:39:06'),
(943,1,332,1120,1,'2016-06-16 00:39:06'),
(944,1,334,1121,1,'2016-06-16 00:39:06'),
(945,1,334,1122,1,'2016-06-16 00:39:06'),
(946,1,332,1123,1,'2016-06-16 00:39:06'),
(947,1,332,1124,1,'2016-06-16 00:39:06'),
(948,1,333,1124,1,'2016-06-16 00:39:06'),
(949,1,333,1125,1,'2016-06-16 00:39:06'),
(950,1,332,1126,1,'2016-06-16 00:39:06'),
(951,1,332,1127,1,'2016-06-16 00:39:06'),
(952,1,333,1128,1,'2016-06-16 00:39:07'),
(953,1,334,1129,1,'2016-06-16 00:39:07'),
(954,1,334,1130,1,'2016-06-16 00:39:07'),
(955,1,336,1131,1,'2016-06-16 00:39:07'),
(956,1,333,1104,1,'2016-06-16 00:39:07'),
(957,1,334,1132,1,'2016-06-16 00:39:07'),
(958,1,334,1133,1,'2016-06-16 00:39:07'),
(959,1,334,1134,1,'2016-06-16 00:39:07'),
(960,1,333,1135,1,'2016-06-16 00:39:07'),
(961,1,334,1136,1,'2016-06-16 00:39:07'),
(962,1,334,1137,1,'2016-06-16 00:39:07'),
(963,1,334,1138,1,'2016-06-16 00:39:07'),
(964,1,332,1139,1,'2016-06-16 00:39:07'),
(965,1,334,1140,1,'2016-06-16 00:39:07'),
(966,1,334,1141,1,'2016-06-16 00:39:07'),
(967,1,334,1142,1,'2016-06-16 00:39:07'),
(968,1,332,1143,1,'2016-06-16 00:39:07'),
(969,1,336,1144,1,'2016-06-16 00:39:07'),
(970,1,336,1145,1,'2016-06-16 00:39:07'),
(971,1,332,1146,1,'2016-06-16 00:39:07'),
(972,1,333,1147,1,'2016-06-16 00:39:07'),
(973,1,332,1148,1,'2016-06-16 00:39:07'),
(974,1,332,1149,1,'2016-06-16 00:39:07'),
(975,1,332,1150,1,'2016-06-16 00:39:07'),
(976,1,332,1151,1,'2016-06-16 00:39:07'),
(977,1,334,1152,1,'2016-06-16 00:39:07'),
(978,1,332,1153,1,'2016-06-16 00:39:07'),
(979,1,332,1154,1,'2016-06-16 00:39:07'),
(980,1,332,1155,1,'2016-06-16 00:39:07'),
(981,1,332,1156,1,'2016-06-16 00:39:07'),
(982,1,332,1157,1,'2016-06-16 00:39:07'),
(983,1,332,1158,1,'2016-06-16 00:39:07'),
(984,1,332,1159,1,'2016-06-16 00:39:07'),
(985,1,332,1160,1,'2016-06-16 00:39:07'),
(986,1,332,1161,1,'2016-06-16 00:39:07'),
(987,1,332,1162,1,'2016-06-16 00:39:07'),
(988,1,332,1163,1,'2016-06-16 00:39:07'),
(989,1,332,1164,1,'2016-06-16 00:39:07'),
(990,1,332,1118,1,'2016-06-16 00:39:07');

/*Table structure for table `individuo` */

DROP TABLE IF EXISTS `individuo`;

CREATE TABLE `individuo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_externo` int(11) NOT NULL,
  `id_configuracao` int(11) NOT NULL,
  `nm_individuo` varchar(255) NOT NULL,
  `fg_ativo` tinyint(1) NOT NULL DEFAULT '1',
  `dt_ultima_sincronizacao` datetime NOT NULL,
  `xp_atual` int(11) DEFAULT NULL,
  `qt_atividades_concluidas` int(11) DEFAULT NULL,
  `qt_desafios_concluidos` int(11) DEFAULT NULL,
  `qt_metas_concluidas` int(11) DEFAULT NULL,
  `qt_emblemas` int(11) DEFAULT NULL,
  `qt_itens` int(11) DEFAULT NULL,
  `qt_vida` int(11) DEFAULT NULL,
  `vl_dinehiro` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_individuo_sys_configuracao1_idx` (`id_configuracao`),
  CONSTRAINT `fk_individuo_sys_configuracao1` FOREIGN KEY (`id_configuracao`) REFERENCES `sys_configuracao` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1165 DEFAULT CHARSET=utf8;

/*Data for the table `individuo` */

insert  into `individuo`(`id`,`id_externo`,`id_configuracao`,`nm_individuo`,`fg_ativo`,`dt_ultima_sincronizacao`,`xp_atual`,`qt_atividades_concluidas`,`qt_desafios_concluidos`,`qt_metas_concluidas`,`qt_emblemas`,`qt_itens`,`qt_vida`,`vl_dinehiro`) values 
(1104,37,1,'Felipe Lorenz',1,'2016-06-16 00:39:07',0,0,0,0,0,0,100,250),
(1105,4,1,'Lisiane Mello',1,'2016-06-16 00:39:06',0,0,0,0,0,0,100,250),
(1106,5,1,'Amanda Brum',1,'2016-06-16 00:39:06',0,0,0,0,0,0,100,250),
(1107,6,1,'Carlos Mergen',1,'2016-06-16 00:39:06',0,0,0,0,0,0,100,250),
(1108,7,1,'Jaime Fernando Neis',1,'2016-06-16 00:39:06',0,0,0,0,0,0,100,250),
(1109,8,1,'Eduardo K.',1,'2016-06-16 00:39:06',0,0,0,0,0,0,100,250),
(1110,9,1,'Tauame Pacce',1,'2016-06-16 00:39:06',0,0,0,0,0,0,100,250),
(1111,10,1,'Ana Julia',1,'2016-06-16 00:39:06',0,0,0,0,0,0,100,250),
(1112,11,1,'Everton Thomas',1,'2016-06-16 00:39:06',0,0,0,0,0,0,100,250),
(1113,12,1,'Henrique S.',1,'2016-06-16 00:39:06',0,0,0,0,0,0,100,250),
(1114,13,1,'Gabriel Rocha',1,'2016-06-16 00:39:06',0,0,0,0,0,0,100,250),
(1115,14,1,'Fabricio Wendt',1,'2016-06-16 00:39:06',0,0,0,0,0,0,100,250),
(1116,15,1,'Suellen Trarbach',1,'2016-06-16 00:39:06',0,0,0,0,0,0,100,250),
(1117,16,1,'Julio Cesar Machado',1,'2016-06-16 00:39:06',0,0,0,0,0,0,100,250),
(1118,80,1,'Rogério Mugnol',1,'2016-06-16 00:39:07',0,0,0,0,0,0,100,250),
(1119,18,1,'Robson Farias',1,'2016-06-16 00:39:06',0,0,0,0,0,0,100,250),
(1120,19,1,'Leandro N.',1,'2016-06-16 00:39:06',0,0,0,0,0,0,100,250),
(1121,20,1,'Marcio P.',1,'2016-06-16 00:39:06',0,0,0,0,0,0,100,250),
(1122,21,1,'Cássio Tatsch',1,'2016-06-16 00:39:06',0,0,0,0,0,0,100,250),
(1123,22,1,'Mateus Felipe',1,'2016-06-16 00:39:06',0,0,0,0,0,0,100,250),
(1124,23,1,'Felipe Fetzer',1,'2016-06-16 00:39:06',0,0,0,0,0,0,100,250),
(1125,25,1,'Guilherme R.',1,'2016-06-16 00:39:06',0,0,0,0,0,0,100,250),
(1126,26,1,'Luiz Gustavo Rupp',1,'2016-06-16 00:39:06',0,0,0,0,0,0,100,250),
(1127,30,1,'Robson Baum',1,'2016-06-16 00:39:06',0,0,0,0,0,0,100,250),
(1128,31,1,'Fernando S.',1,'2016-06-16 00:39:07',0,0,0,0,0,0,100,250),
(1129,32,1,'Marciel Goergen',1,'2016-06-16 00:39:07',0,0,0,0,0,0,100,250),
(1130,34,1,'Fabiano Kist',1,'2016-06-16 00:39:07',0,0,0,0,0,0,100,250),
(1131,35,1,'Daniel Costa',1,'2016-06-16 00:39:07',0,0,0,0,0,0,100,250),
(1132,39,1,'Angelica K.',1,'2016-06-16 00:39:07',0,0,0,0,0,0,100,250),
(1133,40,1,'Douglas Reinicke',1,'2016-06-16 00:39:07',0,0,0,0,0,0,100,250),
(1134,41,1,'Marco Lima',1,'2016-06-16 00:39:07',0,0,0,0,0,0,100,250),
(1135,42,1,'Camila Berwig',1,'2016-06-16 00:39:07',0,0,0,0,0,0,100,250),
(1136,43,1,'Charles G.',1,'2016-06-16 00:39:07',0,0,0,0,0,0,100,250),
(1137,44,1,'Adler Schilling',1,'2016-06-16 00:39:07',0,0,0,0,0,0,100,250),
(1138,45,1,'Junio Santos',1,'2016-06-16 00:39:07',0,0,0,0,0,0,100,250),
(1139,50,1,'Julio Rodrigues',1,'2016-06-16 00:39:07',0,0,0,0,0,0,100,250),
(1140,51,1,'Anderson A.',1,'2016-06-16 00:39:07',0,0,0,0,0,0,100,250),
(1141,53,1,'Debora Lohane',1,'2016-06-16 00:39:07',0,0,0,0,0,0,100,250),
(1142,54,1,'Delton C.',1,'2016-06-16 00:39:07',0,0,0,0,0,0,100,250),
(1143,55,1,'DNIT @',1,'2016-06-16 00:39:07',0,0,0,0,0,0,100,250),
(1144,57,1,'Sergio Ricardo',1,'2016-06-16 00:39:07',0,0,0,0,0,0,100,250),
(1145,58,1,'Nicke Manarim',1,'2016-06-16 00:39:07',0,0,0,0,0,0,100,250),
(1146,59,1,'Luciane Richter',1,'2016-06-16 00:39:07',0,0,0,0,0,0,100,250),
(1147,60,1,'Mauricio X.Silva',1,'2016-06-16 00:39:07',0,0,0,0,0,0,100,250),
(1148,61,1,'Pedro Araujo',1,'2016-06-16 00:39:07',0,0,0,0,0,0,100,250),
(1149,62,1,'Luciano Santos',1,'2016-06-16 00:39:07',0,0,0,0,0,0,100,250),
(1150,63,1,'Emanuel Lucas',1,'2016-06-16 00:39:07',0,0,0,0,0,0,100,250),
(1151,64,1,'Leonardo Teixeira',1,'2016-06-16 00:39:07',0,0,0,0,0,0,100,250),
(1152,65,1,'Kelvin Alves',1,'2016-06-16 00:39:07',0,0,0,0,0,0,100,250),
(1153,66,1,'Sara Moraes',1,'2016-06-16 00:39:07',0,0,0,0,0,0,100,250),
(1154,67,1,'Yuri Meira',1,'2016-06-16 00:39:07',0,0,0,0,0,0,100,250),
(1155,68,1,'Rodrigo Baroni',1,'2016-06-16 00:39:07',0,0,0,0,0,0,100,250),
(1156,69,1,'Jose Claudio M.',1,'2016-06-16 00:39:07',0,0,0,0,0,0,100,250),
(1157,70,1,'Juliana S.',1,'2016-06-16 00:39:07',0,0,0,0,0,0,100,250),
(1158,71,1,'Ricardo Valezan',1,'2016-06-16 00:39:07',0,0,0,0,0,0,100,250),
(1159,72,1,'Tamara Arend de Freitas',1,'2016-06-16 00:39:07',0,0,0,0,0,0,100,250),
(1160,75,1,'Teu Solano',1,'2016-06-16 00:39:07',0,0,0,0,0,0,100,250),
(1161,76,1,'Sahra Regnet',1,'2016-06-16 00:39:07',0,0,0,0,0,0,100,250),
(1162,77,1,'Anderson Rodrigues',1,'2016-06-16 00:39:07',0,0,0,0,0,0,100,250),
(1163,78,1,'Vitor M. Silva',1,'2016-06-16 00:39:07',0,0,0,0,0,0,100,250),
(1164,79,1,'Jose A. Görck',1,'2016-06-16 00:39:07',0,0,0,0,0,0,100,250);

/*Table structure for table `individuo_atividade` */

DROP TABLE IF EXISTS `individuo_atividade`;

CREATE TABLE `individuo_atividade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_externo` int(11) NOT NULL,
  `id_individuo` int(11) NOT NULL,
  `id_desafio` int(11) NOT NULL,
  `id_configuracao` int(11) NOT NULL,
  `dt_ocorrencia` datetime DEFAULT NULL,
  `vl_atingido` float DEFAULT NULL,
  `dt_atingido` datetime DEFAULT NULL,
  `sg_atingido` varchar(45) DEFAULT NULL,
  `vl_planejado` float DEFAULT NULL,
  `dt_planejado` datetime DEFAULT NULL,
  `sg_planejado` varchar(45) DEFAULT NULL,
  `dt_ultima_sincronizacao` datetime NOT NULL,
  `fg_ativo` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_individuo_atividade_individuo1_idx` (`id_individuo`),
  KEY `fk_individuo_atividade_sys_configuracao1_idx` (`id_configuracao`),
  KEY `fk_individuo_atividade_desafio1_idx` (`id_desafio`),
  CONSTRAINT `fk_individuo_atividade_desafio1` FOREIGN KEY (`id_desafio`) REFERENCES `desafio` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_individuo_atividade_individuo1` FOREIGN KEY (`id_individuo`) REFERENCES `individuo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_individuo_atividade_sys_configuracao1` FOREIGN KEY (`id_configuracao`) REFERENCES `sys_configuracao` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7796 DEFAULT CHARSET=utf8;

/*Data for the table `individuo_atividade` */

/*Table structure for table `individuo_nivel` */

DROP TABLE IF EXISTS `individuo_nivel`;

CREATE TABLE `individuo_nivel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dt_inicio` datetime DEFAULT NULL,
  `dt_fim` datetime DEFAULT NULL,
  `fg_atual` tinyint(1) NOT NULL DEFAULT '1',
  `id_individuo` int(11) NOT NULL,
  `id_nivel` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_individuo_nivel_nivel1_idx` (`id_nivel`),
  KEY `fk_individuo_nivel_individuo1_idx` (`id_individuo`),
  CONSTRAINT `fk_individuo_nivel_individuo1` FOREIGN KEY (`id_individuo`) REFERENCES `individuo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_individuo_nivel_nivel1` FOREIGN KEY (`id_nivel`) REFERENCES `nivel` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `individuo_nivel` */

/*Table structure for table `item` */

DROP TABLE IF EXISTS `item`;

CREATE TABLE `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nm_item` varchar(255) NOT NULL,
  `vl_item` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `item` */

/*Table structure for table `meta` */

DROP TABLE IF EXISTS `meta`;

CREATE TABLE `meta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_desafio` int(11) NOT NULL,
  `nm_meta` varchar(255) NOT NULL,
  `dt_deadline` datetime DEFAULT NULL,
  `vl_atingir` int(11) DEFAULT NULL,
  `sg_atingir` varchar(45) DEFAULT NULL,
  `xp_reconpensa` int(11) DEFAULT NULL,
  `vl_reconpensa` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_meta_desafio1_idx` (`id_desafio`),
  CONSTRAINT `fk_meta_desafio1` FOREIGN KEY (`id_desafio`) REFERENCES `desafio` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `meta` */

/*Table structure for table `nivel` */

DROP TABLE IF EXISTS `nivel`;

CREATE TABLE `nivel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nr_nivel` int(11) NOT NULL,
  `xp_proximo_nivel` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `nivel` */

/*Table structure for table `reconpensa_emblema` */

DROP TABLE IF EXISTS `reconpensa_emblema`;

CREATE TABLE `reconpensa_emblema` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_meta` int(11) NOT NULL,
  `id_emblema` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reconpensa_emblema_meta1_idx` (`id_meta`),
  KEY `fk_reconpensa_emblema_emblema1_idx` (`id_emblema`),
  CONSTRAINT `fk_reconpensa_emblema_emblema1` FOREIGN KEY (`id_emblema`) REFERENCES `emblema` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_reconpensa_emblema_meta1` FOREIGN KEY (`id_meta`) REFERENCES `meta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `reconpensa_emblema` */

/*Table structure for table `reconpensa_item` */

DROP TABLE IF EXISTS `reconpensa_item`;

CREATE TABLE `reconpensa_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_meta` int(11) NOT NULL,
  `id_item` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reconpensa_item_meta1_idx` (`id_meta`),
  KEY `fk_reconpensa_item_item1_idx` (`id_item`),
  CONSTRAINT `fk_reconpensa_item_item1` FOREIGN KEY (`id_item`) REFERENCES `item` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_reconpensa_item_meta1` FOREIGN KEY (`id_meta`) REFERENCES `meta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `reconpensa_item` */

/*Table structure for table `sys_atributo` */

DROP TABLE IF EXISTS `sys_atributo`;

CREATE TABLE `sys_atributo` (
  `id` int(11) NOT NULL,
  `nm_atributo` varchar(45) NOT NULL,
  `id_tabela` int(11) NOT NULL,
  `id_tipo_atributo` int(11) NOT NULL,
  `fg_coluna_view` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_sys_atributo_sys_tabela_idx` (`id_tabela`),
  KEY `fk_sys_atributo_sys_tipo_atributo1_idx` (`id_tipo_atributo`),
  CONSTRAINT `fk_sys_atributo_sys_tabela` FOREIGN KEY (`id_tabela`) REFERENCES `sys_tabela` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sys_atributo_sys_tipo_atributo1` FOREIGN KEY (`id_tipo_atributo`) REFERENCES `sys_tipo_atributo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_atributo` */

insert  into `sys_atributo`(`id`,`nm_atributo`,`id_tabela`,`id_tipo_atributo`,`fg_coluna_view`) values 
(1,'nm_desafio',1,2,0),
(2,'nm_grupo',2,2,1),
(3,'xp_atual',2,1,0),
(4,'qt_atividades_concluidas',2,1,0),
(5,'qt_desafios_concluidos',2,1,0),
(6,'qt_metas_concluidas',2,1,0),
(7,'qt_emblemas',2,1,0),
(8,'qt_itens',2,1,0),
(9,'vl_dinheiro',2,1,0),
(12,'nm_individuo',4,2,1),
(13,'xp_atual',4,1,0),
(14,'qt_atividades_concluidas',4,1,0),
(15,'qt_desafios_concluidos',4,1,0),
(16,'qt_metas_concluidas',4,1,0),
(17,'qt_emblemas',4,1,0),
(18,'qt_itens',4,1,0),
(19,'qt_vida',4,1,0),
(20,'vl_dinheiro',4,1,0),
(23,'dt_ocorrencia',5,3,1),
(24,'vl_atingido',5,1,1),
(25,'dt_atingido',5,3,1),
(26,'sg_atingido',5,2,1),
(29,'dt_ocorrencia',6,3,1),
(30,'vl_atingido',6,1,1),
(31,'dt_atingido',6,3,1),
(32,'sg_atingido',6,2,1),
(33,'nm_grupo',4,2,1),
(34,'nm_desafio',6,2,0),
(35,'nm_desafio',5,2,0);

/*Table structure for table `sys_automacao` */

DROP TABLE IF EXISTS `sys_automacao`;

CREATE TABLE `sys_automacao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_configuracao` int(11) NOT NULL,
  `nr_dia_semana` int(11) NOT NULL,
  `hr_automacao` time DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_sys_automacao_sys_configuracao1_idx` (`id_configuracao`),
  CONSTRAINT `fk_sys_automacao_sys_configuracao1` FOREIGN KEY (`id_configuracao`) REFERENCES `sys_configuracao` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `sys_automacao` */

insert  into `sys_automacao`(`id`,`id_configuracao`,`nr_dia_semana`,`hr_automacao`) values 
(1,1,1,NULL),
(2,1,2,NULL),
(3,1,3,'05:00:00'),
(4,1,4,NULL),
(5,1,5,NULL),
(6,1,6,NULL),
(7,1,7,'00:01:00');

/*Table structure for table `sys_configuracao` */

DROP TABLE IF EXISTS `sys_configuracao`;

CREATE TABLE `sys_configuracao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_identificador` int(11) DEFAULT NULL,
  `nm_configuracao` varchar(45) NOT NULL,
  `sg_tipo_importacao` int(11) NOT NULL COMMENT '1 = banco de dados\n2 = csv',
  `sg_tipo_bd` int(11) DEFAULT NULL COMMENT '1 - postgres\n2 - mysql',
  `nr_ip_host` varchar(20) DEFAULT NULL,
  `nr_port` int(11) DEFAULT NULL,
  `nm_schema` varchar(45) DEFAULT NULL,
  `nm_database` varchar(45) DEFAULT NULL,
  `nm_user` varchar(45) DEFAULT NULL,
  `cd_pass` varchar(45) DEFAULT NULL,
  `ds_diretorio_arquivos` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_ideantificador_UNIQUE` (`id_identificador`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `sys_configuracao` */

insert  into `sys_configuracao`(`id`,`id_identificador`,`nm_configuracao`,`sg_tipo_importacao`,`sg_tipo_bd`,`nr_ip_host`,`nr_port`,`nm_schema`,`nm_database`,`nm_user`,`cd_pass`,`ds_diretorio_arquivos`) values 
(1,1,'Gestão Idealogic',1,1,'localhost',5432,'public','redmine','postgres','postgres','');

/*Table structure for table `sys_operacao` */

DROP TABLE IF EXISTS `sys_operacao`;

CREATE TABLE `sys_operacao` (
  `id` int(11) NOT NULL,
  `nm_operacao` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_operacao` */

insert  into `sys_operacao`(`id`,`nm_operacao`) values 
(1,'Igual'),
(2,'Diferente'),
(3,'Maior'),
(4,'Menor'),
(5,'Contém');

/*Table structure for table `sys_regra` */

DROP TABLE IF EXISTS `sys_regra`;

CREATE TABLE `sys_regra` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_regra_tabela` int(11) NOT NULL,
  `id_atributo` int(11) NOT NULL,
  `id_operacao` int(11) NOT NULL,
  `vl_regra` varchar(45) NOT NULL,
  `sg_tipo_regra` int(11) NOT NULL COMMENT '1 - filtro\n2 - remocao',
  `vl_regra_novo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_sys_regra_sys_regra_tabela1_idx` (`id_regra_tabela`),
  KEY `fk_sys_regra_sys_atributo1_idx` (`id_atributo`),
  KEY `fk_sys_regra_sys_operacao1_idx` (`id_operacao`),
  CONSTRAINT `fk_sys_regra_sys_atributo1` FOREIGN KEY (`id_atributo`) REFERENCES `sys_atributo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sys_regra_sys_operacao1` FOREIGN KEY (`id_operacao`) REFERENCES `sys_operacao` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sys_regra_sys_regra_tabela1` FOREIGN KEY (`id_regra_tabela`) REFERENCES `sys_regra_tabela` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

/*Data for the table `sys_regra` */

insert  into `sys_regra`(`id`,`id_regra_tabela`,`id_atributo`,`id_operacao`,`vl_regra`,`sg_tipo_regra`,`vl_regra_novo`) values 
(1,1,1,1,'Produtividade',2,NULL),
(2,4,13,3,'150',2,NULL),
(3,4,15,4,'10',2,NULL),
(5,4,19,3,'1',2,NULL),
(6,4,12,2,'null',1,NULL),
(18,1,1,1,'Produtividade',1,NULL),
(19,1,1,1,'Vendas',1,NULL),
(20,1,1,1,'Pontualidade',1,NULL),
(21,4,12,2,'Redmine Admin',1,NULL),
(22,4,33,1,'null',3,'DESENVOLVIMENTO'),
(23,4,13,1,'null',3,'0'),
(26,4,16,1,'null',3,'0'),
(27,4,18,1,'null',3,'0'),
(28,4,17,1,'null',3,'0'),
(29,4,15,1,'null',3,'0'),
(30,4,14,1,'null',3,'0'),
(31,4,20,1,'null',3,'250'),
(32,4,19,1,'null',3,'100'),
(34,4,33,1,'FSW',3,'Desenvolvimento'),
(35,2,4,1,'null',3,'0'),
(36,2,5,1,'null',3,'0'),
(37,2,7,1,'null',3,'0'),
(38,2,8,1,'null',3,'0'),
(39,2,6,1,'null',3,'0'),
(40,2,9,1,'null',3,'1000');

/*Table structure for table `sys_regra_extracao` */

DROP TABLE IF EXISTS `sys_regra_extracao`;

CREATE TABLE `sys_regra_extracao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_configuracao` int(11) NOT NULL,
  `nm_view` varchar(45) NOT NULL,
  `sql_view` varchar(1500) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_sys_regra_extracao_sys_configuracao1_idx` (`id_configuracao`),
  CONSTRAINT `fk_sys_regra_extracao_sys_configuracao1` FOREIGN KEY (`id_configuracao`) REFERENCES `sys_configuracao` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `sys_regra_extracao` */

insert  into `sys_regra_extracao`(`id`,`id_configuracao`,`nm_view`,`sql_view`) values 
(1,1,'vw_individuo_atividade','SELECT 1, 2, 3, 4, 5'),
(2,1,'vw_grupo_atividade','regraExtracaoList'),
(3,1,'vw_individuo_grupo','SELECT u.id AS id_individuo,\r\n            (btrim(u.firstname::text) || \' \'::text) || btrim(u.lastname::text) AS nm_individuo,\r\n            u.created_on AS dt_nascimento,\r\n            g.id AS id_grupo,\r\n            g.lastname AS nm_grupo\r\n           FROM users u\r\n             LEFT JOIN groups_users gu ON gu.user_id = u.id\r\n             LEFT JOIN users g ON g.id = gu.group_id AND lower(g.type::text) = \'group\'::text\r\n          WHERE lower(u.type::text) = \'user\'::text\r\n          ORDER BY u.firstname, u.lastname, g.lastname');

/*Table structure for table `sys_regra_tabela` */

DROP TABLE IF EXISTS `sys_regra_tabela`;

CREATE TABLE `sys_regra_tabela` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fg_importar` tinyint(1) NOT NULL DEFAULT '1' COMMENT 'Define se esta tabela ira ser importada pela ferramenta',
  `id_tabela` int(11) NOT NULL,
  `id_configuracao` int(11) NOT NULL,
  `sg_tipo_insercao` int(11) NOT NULL COMMENT '1 - apenas inserir\n2 - inserir e altear',
  `sg_tipo_remocao` int(11) NOT NULL COMMENT '1 - registros nao sincronizados\n2 - não remover\n3 - regra_remocao',
  PRIMARY KEY (`id`),
  KEY `fk_sys_regra_tabela_sys_tabela1_idx` (`id_tabela`),
  KEY `fk_sys_regra_tabela_sys_configuracao1_idx` (`id_configuracao`),
  CONSTRAINT `fk_sys_regra_tabela_sys_configuracao1` FOREIGN KEY (`id_configuracao`) REFERENCES `sys_configuracao` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sys_regra_tabela_sys_tabela1` FOREIGN KEY (`id_tabela`) REFERENCES `sys_tabela` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `sys_regra_tabela` */

insert  into `sys_regra_tabela`(`id`,`fg_importar`,`id_tabela`,`id_configuracao`,`sg_tipo_insercao`,`sg_tipo_remocao`) values 
(1,1,1,1,2,2),
(2,1,2,1,2,2),
(3,1,3,1,2,1),
(4,1,4,1,2,1),
(5,1,5,1,2,1),
(6,1,6,1,2,1);

/*Table structure for table `sys_tabela` */

DROP TABLE IF EXISTS `sys_tabela`;

CREATE TABLE `sys_tabela` (
  `id` int(11) NOT NULL,
  `nm_tabela` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_tabela` */

insert  into `sys_tabela`(`id`,`nm_tabela`) values 
(1,'desafio'),
(2,'grupo'),
(3,'grupo_individuo'),
(4,'individuo'),
(5,'individuo_atividade'),
(6,'grupo_atividade');

/*Table structure for table `sys_tipo_atributo` */

DROP TABLE IF EXISTS `sys_tipo_atributo`;

CREATE TABLE `sys_tipo_atributo` (
  `id` int(11) NOT NULL,
  `nm_tipo_atributo` varchar(45) NOT NULL COMMENT 'numerico\nalfanumerico\ndata\nbooleano',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_tipo_atributo` */

insert  into `sys_tipo_atributo`(`id`,`nm_tipo_atributo`) values 
(1,'Numérico'),
(2,'Alfanumérico'),
(3,'Data');

/*Table structure for table `sys_tipo_atributo_operacao` */

DROP TABLE IF EXISTS `sys_tipo_atributo_operacao`;

CREATE TABLE `sys_tipo_atributo_operacao` (
  `id` int(11) NOT NULL,
  `id_operacao` int(11) NOT NULL,
  `id_tipo_atributo` int(11) NOT NULL,
  `nm_operacao_sql` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_sys_tipo_atributo_operacao_sys_operacao1_idx` (`id_operacao`),
  KEY `fk_sys_tipo_atributo_operacao_sys_tipo_atributo1_idx` (`id_tipo_atributo`),
  CONSTRAINT `fk_sys_tipo_atributo_operacao_sys_operacao1` FOREIGN KEY (`id_operacao`) REFERENCES `sys_operacao` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sys_tipo_atributo_operacao_sys_tipo_atributo1` FOREIGN KEY (`id_tipo_atributo`) REFERENCES `sys_tipo_atributo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_tipo_atributo_operacao` */

insert  into `sys_tipo_atributo_operacao`(`id`,`id_operacao`,`id_tipo_atributo`,`nm_operacao_sql`) values 
(1,1,1,'= #VAL#'),
(2,2,1,'<> #VAL#'),
(3,3,1,'> #VAL#'),
(4,4,1,'< #VAL#'),
(5,1,2,'LIKE \'#VAL#\''),
(6,2,2,'NOT LIKE \'#VAL#\''),
(7,5,2,'LIKE \'%#VAL#%\''),
(8,1,3,'= #VAL#'),
(9,2,3,'<> #VAL#'),
(10,3,3,'> #VAL#'),
(11,4,3,'< #VAL#');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
