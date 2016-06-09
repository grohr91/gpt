-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema gpt
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema gpt
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `gpt` DEFAULT CHARACTER SET utf8 ;
USE `gpt` ;

-- -----------------------------------------------------
-- Table `gpt`.`sys_configuracao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gpt`.`sys_configuracao` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nm_configuracao` VARCHAR(45) NOT NULL,
  `sg_tipo_importacao` INT NOT NULL COMMENT '1 = banco de dados\n2 = csv',
  `sg_tipo_bd` INT NULL COMMENT '1 - postgres\n2 - mysql',
  `nr_ip_host` VARCHAR(20) NULL,
  `nr_port` INT NULL,
  `nm_schema` VARCHAR(45) NULL,
  `nm_database` VARCHAR(45) NULL,
  `nm_user` VARCHAR(45) NULL,
  `cd_pass` VARCHAR(45) NULL,
  `ds_diretorio_arquivos` VARCHAR(255) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gpt`.`sys_tabela`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gpt`.`sys_tabela` (
  `id` INT NOT NULL,
  `nm_tabela` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gpt`.`sys_tipo_atributo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gpt`.`sys_tipo_atributo` (
  `id` INT NOT NULL,
  `nm_tipo_atributo` VARCHAR(45) NOT NULL COMMENT 'numerico\nalfanumerico\ndata\nbooleano',
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gpt`.`sys_atributo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gpt`.`sys_atributo` (
  `id` INT NOT NULL,
  `nm_atributo` VARCHAR(45) NOT NULL,
  `id_tabela` INT NOT NULL,
  `id_tipo_atributo` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_sys_atributo_sys_tabela_idx` (`id_tabela` ASC),
  INDEX `fk_sys_atributo_sys_tipo_atributo1_idx` (`id_tipo_atributo` ASC),
  CONSTRAINT `fk_sys_atributo_sys_tabela`
    FOREIGN KEY (`id_tabela`)
    REFERENCES `gpt`.`sys_tabela` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sys_atributo_sys_tipo_atributo1`
    FOREIGN KEY (`id_tipo_atributo`)
    REFERENCES `gpt`.`sys_tipo_atributo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gpt`.`sys_operacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gpt`.`sys_operacao` (
  `id` INT NOT NULL,
  `nm_operacao` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gpt`.`sys_tipo_atributo_operacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gpt`.`sys_tipo_atributo_operacao` (
  `id` INT NOT NULL,
  `id_operacao` INT NOT NULL,
  `id_tipo_atributo` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_sys_tipo_atributo_operacao_sys_operacao1_idx` (`id_operacao` ASC),
  INDEX `fk_sys_tipo_atributo_operacao_sys_tipo_atributo1_idx` (`id_tipo_atributo` ASC),
  CONSTRAINT `fk_sys_tipo_atributo_operacao_sys_operacao1`
    FOREIGN KEY (`id_operacao`)
    REFERENCES `gpt`.`sys_operacao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sys_tipo_atributo_operacao_sys_tipo_atributo1`
    FOREIGN KEY (`id_tipo_atributo`)
    REFERENCES `gpt`.`sys_tipo_atributo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gpt`.`sys_regra_tabela`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gpt`.`sys_regra_tabela` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fg_importar` TINYINT(1) NOT NULL DEFAULT 1 COMMENT 'Define se esta tabela ira ser importada pela ferramenta',
  `id_tabela` INT NOT NULL,
  `id_configuracao` INT NOT NULL,
  `sg_tipo_insercao` INT NOT NULL COMMENT '1 - apenas inserir\n2 - inserir e altear',
  `sg_tipo_remocao` INT NOT NULL COMMENT '1 - registros nao sincronizados\n2 -  não remover\n3 - regra_remocao',
  PRIMARY KEY (`id`),
  INDEX `fk_sys_regra_tabela_sys_tabela1_idx` (`id_tabela` ASC),
  INDEX `fk_sys_regra_tabela_sys_configuracao1_idx` (`id_configuracao` ASC),
  CONSTRAINT `fk_sys_regra_tabela_sys_tabela1`
    FOREIGN KEY (`id_tabela`)
    REFERENCES `gpt`.`sys_tabela` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sys_regra_tabela_sys_configuracao1`
    FOREIGN KEY (`id_configuracao`)
    REFERENCES `gpt`.`sys_configuracao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gpt`.`sys_regra_filtro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gpt`.`sys_regra_filtro` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fg_ativo` TINYINT(1) NOT NULL DEFAULT 1,
  `id_tabela` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_sys_regra_filtro_sys_tabela1_idx` (`id_tabela` ASC),
  CONSTRAINT `fk_sys_regra_filtro_sys_tabela1`
    FOREIGN KEY (`id_tabela`)
    REFERENCES `gpt`.`sys_tabela` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gpt`.`sys_regra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gpt`.`sys_regra` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `vl_regra` VARCHAR(45) NOT NULL,
  `id_regra_tabela` INT NULL,
  `id_regra_filtro` INT NULL,
  `id_atributo` INT NOT NULL,
  `id_operacao` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_sys_regra_sys_regra_tabela1_idx` (`id_regra_tabela` ASC),
  INDEX `fk_sys_regra_sys_regra_filtro1_idx` (`id_regra_filtro` ASC),
  INDEX `fk_sys_regra_sys_atributo1_idx` (`id_atributo` ASC),
  INDEX `fk_sys_regra_sys_operacao1_idx` (`id_operacao` ASC),
  CONSTRAINT `fk_sys_regra_sys_regra_tabela1`
    FOREIGN KEY (`id_regra_tabela`)
    REFERENCES `gpt`.`sys_regra_tabela` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sys_regra_sys_regra_filtro1`
    FOREIGN KEY (`id_regra_filtro`)
    REFERENCES `gpt`.`sys_regra_filtro` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sys_regra_sys_atributo1`
    FOREIGN KEY (`id_atributo`)
    REFERENCES `gpt`.`sys_atributo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sys_regra_sys_operacao1`
    FOREIGN KEY (`id_operacao`)
    REFERENCES `gpt`.`sys_operacao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gpt`.`individuo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gpt`.`individuo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_externo` INT NOT NULL,
  `id_configuracao` INT NOT NULL,
  `nm_individuo` VARCHAR(255) NOT NULL,
  `fg_ativo` TINYINT(1) NOT NULL DEFAULT 1,
  `dt_ultima_atualizacao` DATETIME NOT NULL,
  `xp_atual` INT NULL,
  `qt_atividades_concluidas` INT NULL,
  `qt_desafios_concluidos` INT NULL,
  `qt_metas_concluidas` INT NULL,
  `qt_emblemas` INT NULL,
  `qt_itens` INT NULL,
  `qt_vida` INT NULL,
  `vl_dinehiro` FLOAT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_individuo_sys_configuracao1_idx` (`id_configuracao` ASC),
  CONSTRAINT `fk_individuo_sys_configuracao1`
    FOREIGN KEY (`id_configuracao`)
    REFERENCES `gpt`.`sys_configuracao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gpt`.`grupo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gpt`.`grupo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_externo` INT NULL,
  `id_configuracao` INT NOT NULL,
  `nm_grupo` VARCHAR(255) NOT NULL,
  `fg_ativo` TINYINT(1) NOT NULL DEFAULT 1,
  `dt_ultima_atualizacao` DATETIME NOT NULL,
  `xp_atual` INT NULL,
  `qt_atividades_concluidas` INT NULL,
  `qt_desafios_concluidos` INT NULL,
  `qt_metas_concluidas` INT NULL,
  `qt_emblemas` INT NULL,
  `qt_itens` INT NULL,
  `vl_dinehiro` FLOAT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_grupo_sys_configuracao1_idx` (`id_configuracao` ASC),
  CONSTRAINT `fk_grupo_sys_configuracao1`
    FOREIGN KEY (`id_configuracao`)
    REFERENCES `gpt`.`sys_configuracao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gpt`.`grupo_individuo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gpt`.`grupo_individuo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_configuracao` INT NOT NULL,
  `id_grupo` INT NOT NULL,
  `id_individuo` INT NOT NULL,
  `fg_ativo` TINYINT(1) NOT NULL DEFAULT 1,
  `dt_ultima_atualizacao` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_grupo_individuo_grupo1_idx` (`id_grupo` ASC),
  INDEX `fk_grupo_individuo_sys_configuracao1_idx` (`id_configuracao` ASC),
  INDEX `fk_grupo_individuo_individuo1_idx` (`id_individuo` ASC),
  CONSTRAINT `fk_grupo_individuo_grupo1`
    FOREIGN KEY (`id_grupo`)
    REFERENCES `gpt`.`grupo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_grupo_individuo_sys_configuracao1`
    FOREIGN KEY (`id_configuracao`)
    REFERENCES `gpt`.`sys_configuracao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_grupo_individuo_individuo1`
    FOREIGN KEY (`id_individuo`)
    REFERENCES `gpt`.`individuo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gpt`.`desafio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gpt`.`desafio` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_externo` INT NOT NULL,
  `id_configuracao` INT NOT NULL,
  `nm_desafio` VARCHAR(255) NOT NULL,
  `dt_ultima_sincronizacao` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_desafio_sys_configuracao1_idx` (`id_configuracao` ASC),
  CONSTRAINT `fk_desafio_sys_configuracao1`
    FOREIGN KEY (`id_configuracao`)
    REFERENCES `gpt`.`sys_configuracao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gpt`.`individuo_atividade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gpt`.`individuo_atividade` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_externo` INT NOT NULL,
  `id_individuo` INT NOT NULL,
  `id_desafio` INT NOT NULL,
  `id_configuracao` INT NOT NULL,
  `dt_ocorrencia` DATETIME NULL,
  `vl_atingido` FLOAT NULL,
  `dt_atingido` DATETIME NULL,
  `sg_atingido` VARCHAR(45) NULL,
  `dt_ultima_sincronizacao` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_individuo_atividade_individuo1_idx` (`id_individuo` ASC),
  INDEX `fk_individuo_atividade_sys_configuracao1_idx` (`id_configuracao` ASC),
  INDEX `fk_individuo_atividade_desafio1_idx` (`id_desafio` ASC),
  CONSTRAINT `fk_individuo_atividade_individuo1`
    FOREIGN KEY (`id_individuo`)
    REFERENCES `gpt`.`individuo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_individuo_atividade_sys_configuracao1`
    FOREIGN KEY (`id_configuracao`)
    REFERENCES `gpt`.`sys_configuracao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_individuo_atividade_desafio1`
    FOREIGN KEY (`id_desafio`)
    REFERENCES `gpt`.`desafio` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gpt`.`grupo_atividade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gpt`.`grupo_atividade` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_externo` INT NOT NULL,
  `id_grupo` INT NOT NULL,
  `id_desafio` INT NOT NULL,
  `id_configuracao` INT NOT NULL,
  `dt_ocorrencia` DATETIME NULL,
  `vl_atingido` FLOAT NULL,
  `dt_atingido` DATETIME NULL,
  `sg_atingido` VARCHAR(45) NULL,
  `dt_ultima_sincronizacao` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_grupo_atividade_grupo1_idx` (`id_grupo` ASC),
  INDEX `fk_grupo_atividade_sys_configuracao1_idx` (`id_configuracao` ASC),
  INDEX `fk_grupo_atividade_desafio1_idx` (`id_desafio` ASC),
  CONSTRAINT `fk_grupo_atividade_grupo1`
    FOREIGN KEY (`id_grupo`)
    REFERENCES `gpt`.`grupo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_grupo_atividade_sys_configuracao1`
    FOREIGN KEY (`id_configuracao`)
    REFERENCES `gpt`.`sys_configuracao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_grupo_atividade_desafio1`
    FOREIGN KEY (`id_desafio`)
    REFERENCES `gpt`.`desafio` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gpt`.`nivel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gpt`.`nivel` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nr_nivel` INT NOT NULL,
  `xp_proximo_nivel` FLOAT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gpt`.`individuo_nivel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gpt`.`individuo_nivel` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `dt_inicio` DATETIME NULL,
  `dt_fim` DATETIME NULL,
  `fg_atual` TINYINT(1) NOT NULL DEFAULT 1,
  `id_individuo` INT NOT NULL,
  `id_nivel` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_individuo_nivel_nivel1_idx` (`id_nivel` ASC),
  INDEX `fk_individuo_nivel_individuo1_idx` (`id_individuo` ASC),
  CONSTRAINT `fk_individuo_nivel_nivel1`
    FOREIGN KEY (`id_nivel`)
    REFERENCES `gpt`.`nivel` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_individuo_nivel_individuo1`
    FOREIGN KEY (`id_individuo`)
    REFERENCES `gpt`.`individuo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gpt`.`meta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gpt`.`meta` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_desafio` INT NOT NULL,
  `nm_meta` VARCHAR(255) NOT NULL,
  `dt_deadline` DATETIME NULL,
  `vl_atingir` INT NULL,
  `sg_atingir` VARCHAR(45) NULL,
  `xp_reconpensa` INT NULL,
  `vl_reconpensa` FLOAT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_meta_desafio1_idx` (`id_desafio` ASC),
  CONSTRAINT `fk_meta_desafio1`
    FOREIGN KEY (`id_desafio`)
    REFERENCES `gpt`.`desafio` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gpt`.`emblema`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gpt`.`emblema` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nm_emblema` VARCHAR(255) NOT NULL,
  `vl_emblema` FLOAT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gpt`.`reconpensa_emblema`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gpt`.`reconpensa_emblema` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_meta` INT NOT NULL,
  `id_emblema` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_reconpensa_emblema_meta1_idx` (`id_meta` ASC),
  INDEX `fk_reconpensa_emblema_emblema1_idx` (`id_emblema` ASC),
  CONSTRAINT `fk_reconpensa_emblema_meta1`
    FOREIGN KEY (`id_meta`)
    REFERENCES `gpt`.`meta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reconpensa_emblema_emblema1`
    FOREIGN KEY (`id_emblema`)
    REFERENCES `gpt`.`emblema` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gpt`.`item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gpt`.`item` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nm_item` VARCHAR(255) NOT NULL,
  `vl_item` FLOAT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gpt`.`reconpensa_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gpt`.`reconpensa_item` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_meta` INT NOT NULL,
  `id_item` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_reconpensa_item_meta1_idx` (`id_meta` ASC),
  INDEX `fk_reconpensa_item_item1_idx` (`id_item` ASC),
  CONSTRAINT `fk_reconpensa_item_meta1`
    FOREIGN KEY (`id_meta`)
    REFERENCES `gpt`.`meta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reconpensa_item_item1`
    FOREIGN KEY (`id_item`)
    REFERENCES `gpt`.`item` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
