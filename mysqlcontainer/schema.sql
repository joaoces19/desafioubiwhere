-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Company`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Company` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Company` (
  `idCompany` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(45) NULL COMMENT '',
  `address` VARCHAR(45) NULL COMMENT '',
  `number` VARCHAR(45) NULL COMMENT '',
  PRIMARY KEY (`idCompany`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Person`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Person` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Person` (
  `idPerson` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(45) NULL COMMENT '',
  `address` VARCHAR(45) NULL COMMENT '',
  `fkIdCompany` INT NULL DEFAULT 0 COMMENT '',
  PRIMARY KEY (`idPerson`)  COMMENT '',
  INDEX `fk_Person_Company_idx` (`fkIdCompany` ASC)  COMMENT '',
  CONSTRAINT `fk_Person_Company`
    FOREIGN KEY (`fkIdCompany`)
    REFERENCES `mydb`.`Company` (`idCompany`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Phonenumber`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Phonenumber` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Phonenumber` (
  `idPhonenumber` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `number` VARCHAR(45) NULL COMMENT '',
  `fkIdPerson` INT NOT NULL COMMENT '',
  PRIMARY KEY (`idPhonenumber`)  COMMENT '',
  INDEX `fk_Phonenumber_Person1_idx` (`fkIdPerson` ASC)  COMMENT '',
  CONSTRAINT `fk_Phonenumber_Person1`
    FOREIGN KEY (`fkIdPerson`)
    REFERENCES `mydb`.`Person` (`idPerson`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
