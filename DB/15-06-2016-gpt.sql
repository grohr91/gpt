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
  `dt_ultima_atualizacao` datetime NOT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `grupo` */

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
  `dt_ultima_atualizacao` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_grupo_individuo_grupo1_idx` (`id_grupo`),
  KEY `fk_grupo_individuo_sys_configuracao1_idx` (`id_configuracao`),
  KEY `fk_grupo_individuo_individuo1_idx` (`id_individuo`),
  CONSTRAINT `fk_grupo_individuo_grupo1` FOREIGN KEY (`id_grupo`) REFERENCES `grupo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_grupo_individuo_individuo1` FOREIGN KEY (`id_individuo`) REFERENCES `individuo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_grupo_individuo_sys_configuracao1` FOREIGN KEY (`id_configuracao`) REFERENCES `sys_configuracao` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `grupo_individuo` */

/*Table structure for table `individuo` */

DROP TABLE IF EXISTS `individuo`;

CREATE TABLE `individuo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_externo` int(11) NOT NULL,
  `id_configuracao` int(11) NOT NULL,
  `nm_individuo` varchar(255) NOT NULL,
  `fg_ativo` tinyint(1) NOT NULL DEFAULT '1',
  `dt_ultima_atualizacao` datetime NOT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `individuo` */

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
  PRIMARY KEY (`id`),
  KEY `fk_individuo_atividade_individuo1_idx` (`id_individuo`),
  KEY `fk_individuo_atividade_sys_configuracao1_idx` (`id_configuracao`),
  KEY `fk_individuo_atividade_desafio1_idx` (`id_desafio`),
  CONSTRAINT `fk_individuo_atividade_desafio1` FOREIGN KEY (`id_desafio`) REFERENCES `desafio` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_individuo_atividade_individuo1` FOREIGN KEY (`id_individuo`) REFERENCES `individuo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_individuo_atividade_sys_configuracao1` FOREIGN KEY (`id_configuracao`) REFERENCES `sys_configuracao` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
(1,'nm_desafio',1,2,1),
(2,'nm_grupo',2,2,1),
(3,'xp_atual',2,1,0),
(4,'qt_atividades_concluidas',2,1,0),
(5,'qt_desafio_concluidos',2,1,0),
(6,'qt_metas_concluidas',2,1,0),
(7,'qt_emblemas',2,1,0),
(8,'qt_itens',2,1,0),
(9,'vl_dinheiro',2,1,0),
(10,'individuo.nm_individuo',3,2,1),
(11,'grupo.nm_grupo',3,2,1),
(12,'nm_individuo',4,2,1),
(13,'xp_atual',4,1,0),
(14,'qt_atividades_concluidas',4,1,0),
(15,'qt_desafios_concluidos',4,1,0),
(16,'qt_metas_concluidas',4,1,0),
(17,'qt_emblemas',4,1,0),
(18,'qt_itens',4,1,0),
(19,'qt_vida',4,1,0),
(20,'qt_dinheiro',4,1,0),
(21,'individuo.nm_individuo',5,2,1),
(22,'desafio.nm_desafio',5,2,1),
(23,'dt_ocorrencia',5,3,1),
(24,'vl_atingido',5,1,1),
(25,'dt_atingido',5,3,1),
(26,'sg_atingido',5,2,1),
(27,'grupo.nm_grupo',6,2,1),
(28,'desafio.nm_desafio',6,2,1),
(29,'dt_ocorrencia',6,3,1),
(30,'vl_atingido',6,1,1),
(31,'dt_atingido',6,3,1),
(32,'sg_atingido',6,2,1);

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
  PRIMARY KEY (`id`),
  KEY `fk_sys_regra_sys_regra_tabela1_idx` (`id_regra_tabela`),
  KEY `fk_sys_regra_sys_atributo1_idx` (`id_atributo`),
  KEY `fk_sys_regra_sys_operacao1_idx` (`id_operacao`),
  CONSTRAINT `fk_sys_regra_sys_atributo1` FOREIGN KEY (`id_atributo`) REFERENCES `sys_atributo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sys_regra_sys_operacao1` FOREIGN KEY (`id_operacao`) REFERENCES `sys_operacao` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sys_regra_sys_regra_tabela1` FOREIGN KEY (`id_regra_tabela`) REFERENCES `sys_regra_tabela` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `sys_regra` */

insert  into `sys_regra`(`id`,`id_regra_tabela`,`id_atributo`,`id_operacao`,`vl_regra`,`sg_tipo_regra`) values 
(1,1,1,1,'Produtividade',2),
(2,4,13,3,'150',2),
(3,4,15,4,'10',2),
(5,4,19,3,'1',2),
(6,4,12,2,'null',1),
(7,4,20,1,'0',1),
(17,1,1,5,'Pontualidade',1),
(18,1,1,1,'Produtividade',1),
(19,1,1,1,'Vendas',1);

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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

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
