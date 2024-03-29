-- MySQL Script generated by MySQL Workbench
-- Thu Mar 12 00:24:39 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering
SET GLOBAL time_zone = '-6:00';
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema eif209_2001_p01
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `eif209_2001_p01` ;

-- -----------------------------------------------------
-- Schema eif209_2001_p01
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `eif209_2001_p01` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci ;
USE `eif209_2001_p01` ;

-- -----------------------------------------------------
-- Table `eif209_2001_p01`.`usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eif209_2001_p01`.`usuario` ;

CREATE TABLE IF NOT EXISTS `eif209_2001_p01`.`usuario` (
  `id_usuario` VARCHAR(16) NOT NULL,
  `clave_acceso` VARCHAR(45) NOT NULL,
  `clave_vencida` TINYINT NOT NULL DEFAULT 1,
  `rol` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id_usuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eif209_2001_p01`.`cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eif209_2001_p01`.`cliente` ;

CREATE TABLE IF NOT EXISTS `eif209_2001_p01`.`cliente` (
  `id_cliente` VARCHAR(16) NOT NULL,
  `usuario_id_usuario` VARCHAR(16) NOT NULL,
  `apellidos` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `telefono` VARCHAR(45) NULL,
  PRIMARY KEY (`id_cliente`)
    ,
INDEX `fk_cliente_usuario1_idx` (`usuario_id_usuario` ASC),
  CONSTRAINT `fk_cliente_usuario1`
    FOREIGN KEY (`usuario_id_usuario`)
    REFERENCES `eif209_2001_p01`.`usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
   
    )
   
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eif209_2001_p01`.`moneda`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eif209_2001_p01`.`moneda` ;

CREATE TABLE IF NOT EXISTS `eif209_2001_p01`.`moneda` (
  `nombre` VARCHAR(3) NOT NULL,
  `descripcion` VARCHAR(20) NOT NULL,
  `simbolo` VARCHAR(4) NOT NULL,
  `tipo_cambio_compra` DOUBLE NOT NULL,
  `tipo_cambio_venta` DOUBLE NOT NULL,
  PRIMARY KEY (`nombre`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eif209_2001_p01`.`tipo_cuenta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eif209_2001_p01`.`tipo_cuenta` ;

CREATE TABLE IF NOT EXISTS `eif209_2001_p01`.`tipo_cuenta` (
  `id_tipo_cuenta` INT NOT NULL,
  `descripción` VARCHAR(45) NOT NULL,
  `tasa_interés` DOUBLE NOT NULL DEFAULT 0,
  PRIMARY KEY (`id_tipo_cuenta`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eif209_2001_p01`.`cuenta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eif209_2001_p01`.`cuenta` ;

CREATE TABLE IF NOT EXISTS `eif209_2001_p01`.`cuenta` (
  `num_cuenta` VARCHAR(45) NOT NULL,
  `tipo_cuenta_id_tipo_cuenta` INT NOT NULL,
  `cliente_id_cliente` VARCHAR(16) NOT NULL,
  `moneda_nombre` VARCHAR(3) NOT NULL,
  `fecha_creacion` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `limite_transferencia_diaria` DOUBLE NOT NULL DEFAULT 1000000,
  `activa` TINYINT NOT NULL DEFAULT 1,
  `saldo_inicial` DOUBLE NOT NULL DEFAULT 0,
  `fecha_ultima_aplicacion` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `saldo_final` DOUBLE NOT NULL DEFAULT 0,
  PRIMARY KEY (`num_cuenta`),
  INDEX `fk_cuenta_moneda_idx` (`moneda_nombre` ASC),
  INDEX `fk_cuenta_cliente1_idx` (`cliente_id_cliente` ASC),
  INDEX `fk_cuenta_tipo_cuenta1_idx` (`tipo_cuenta_id_tipo_cuenta` ASC),
  CONSTRAINT `fk_cuenta_moneda`
    FOREIGN KEY (`moneda_nombre`)
    REFERENCES `eif209_2001_p01`.`moneda` (`nombre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cuenta_cliente1`
    FOREIGN KEY (`cliente_id_cliente`)
    REFERENCES `eif209_2001_p01`.`cliente` (`id_cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cuenta_tipo_cuenta1`
    FOREIGN KEY (`tipo_cuenta_id_tipo_cuenta`)
    REFERENCES `eif209_2001_p01`.`tipo_cuenta` (`id_tipo_cuenta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eif209_2001_p01`.`movimiento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eif209_2001_p01`.`movimiento` ;

CREATE TABLE IF NOT EXISTS `eif209_2001_p01`.`movimiento` (
  `id_movimiento` INT NOT NULL AUTO_INCREMENT,
  `cuenta_num_cuenta` VARCHAR(45) NOT NULL,
  `monto` DOUBLE NOT NULL DEFAULT 0,
  `fecha` DATETIME NOT NULL DEFAULT NOW(),
  `aplicado` TINYINT NOT NULL DEFAULT 0,
  `movimientocol` VARCHAR(45) NULL DEFAULT 0,
  PRIMARY KEY (`id_movimiento`, `cuenta_num_cuenta`),
  INDEX `fk_movimiento_cuenta1_idx` (`cuenta_num_cuenta` ASC),
  CONSTRAINT `fk_movimiento_cuenta1`
    FOREIGN KEY (`cuenta_num_cuenta`)
    REFERENCES `eif209_2001_p01`.`cuenta` (`num_cuenta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eif209_2001_p01`.`favorita`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eif209_2001_p01`.`favorita` ;

CREATE TABLE IF NOT EXISTS `eif209_2001_p01`.`favorita` (
  `cliente_id_cliente` VARCHAR(16) NOT NULL,
  `cuenta_num_cuenta` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`cliente_id_cliente`, `cuenta_num_cuenta`),
  INDEX `fk_favorita_cliente1_idx` (`cliente_id_cliente` ASC),
  INDEX `fk_favorita_cuenta1_idx` (`cuenta_num_cuenta` ASC),
  CONSTRAINT `fk_favorita_cliente1`
    FOREIGN KEY (`cliente_id_cliente`)
    REFERENCES `eif209_2001_p01`.`cliente` (`id_cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_favorita_cuenta1`
    FOREIGN KEY (`cuenta_num_cuenta`)
    REFERENCES `eif209_2001_p01`.`cuenta` (`num_cuenta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eif209_2001_p01`.`transferencia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eif209_2001_p01`.`transferencia` ;

CREATE TABLE IF NOT EXISTS `eif209_2001_p01`.`transferencia` (
  `id_transferencia` INT NOT NULL AUTO_INCREMENT,
  `cuenta_origen` VARCHAR(45) NOT NULL,
  `cuenta_destino` VARCHAR(45) NOT NULL,
  `monto` VARCHAR(45) NOT NULL DEFAULT 0,
  `fecha` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `aplicado` TINYINT NOT NULL,
  PRIMARY KEY (`id_transferencia`, `cuenta_origen`, `cuenta_destino`),
  INDEX `fk_transferencia_cuenta1_idx` (`cuenta_origen` ASC),
  INDEX `fk_transferencia_cuenta2_idx` (`cuenta_destino` ASC),
  CONSTRAINT `fk_transferencia_cuenta1`
    FOREIGN KEY (`cuenta_origen`)
    REFERENCES `eif209_2001_p01`.`cuenta` (`num_cuenta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_transferencia_cuenta2`
    FOREIGN KEY (`cuenta_destino`)
    REFERENCES `eif209_2001_p01`.`cuenta` (`num_cuenta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
