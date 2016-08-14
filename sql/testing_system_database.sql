SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema TestingSystem
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema TestingSystem
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `TestingSystem` DEFAULT CHARACTER SET utf8 ;
USE `TestingSystem` ;

-- -----------------------------------------------------
-- Table `TestingSystem`.`Subjects`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TestingSystem`.`Subjects` (
  `subjectId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`subjectId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TestingSystem`.`Tests`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TestingSystem`.`Tests` (
  `testId` INT NOT NULL AUTO_INCREMENT,
  `subjectId` INT NOT NULL,
  `caption` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`testId`),
  INDEX `fkSubject_idx` (`subjectId` ASC),
  CONSTRAINT `fkSubject`
    FOREIGN KEY (`subjectId`)
    REFERENCES `TestingSystem`.`Subjects` (`subjectId`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TestingSystem`.`Questions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TestingSystem`.`Questions` (
  `questionId` INT NOT NULL AUTO_INCREMENT,
  `testId` INT NOT NULL,
  `task` TEXT NOT NULL,
  PRIMARY KEY (`questionId`),
  INDEX `fkTest_idx` (`testId` ASC),
  CONSTRAINT `fkTest`
    FOREIGN KEY (`testId`)
    REFERENCES `TestingSystem`.`Tests` (`testId`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TestingSystem`.`Users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TestingSystem`.`Users` (
  `userId` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` CHAR(32) NOT NULL,
  `firstName` VARCHAR(45) NOT NULL,
  `lastName` VARCHAR(45) NOT NULL,
  `role` VARCHAR(10) NOT NULL DEFAULT 'user',
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TestingSystem`.`Results`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TestingSystem`.`Results` (
  `resultId` INT NOT NULL AUTO_INCREMENT,
  `userId` INT NOT NULL,
  `testId` INT NOT NULL,
  `rate` INT NOT NULL,
  PRIMARY KEY (`resultId`),
  INDEX `fk0_idx` (`userId` ASC),
  INDEX `fkTest_idx` (`testId` ASC),
  CONSTRAINT `fk_Result_User`
    FOREIGN KEY (`userId`)
    REFERENCES `TestingSystem`.`Users` (`userId`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Result_Test`
    FOREIGN KEY (`testId`)
    REFERENCES `TestingSystem`.`Tests` (`testId`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TestingSystem`.`Answers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TestingSystem`.`Answers` (
  `answerId` INT NOT NULL AUTO_INCREMENT,
  `questionId` INT NOT NULL,
  `text` TEXT NOT NULL,
  `correct` TINYINT(1) NOT NULL,
  PRIMARY KEY (`answerId`),
  INDEX `fkQuestion_idx` (`questionId` ASC),
  CONSTRAINT `fkQuestion`
    FOREIGN KEY (`questionId`)
    REFERENCES `TestingSystem`.`Questions` (`questionId`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `TestingSystem`.`Users`
-- -----------------------------------------------------
START TRANSACTION;
USE `TestingSystem`;
INSERT INTO `TestingSystem`.`Users` (`userId`, `login`, `password`, `firstName`, `lastName`, `role`, `email`) VALUES (0, 'admin', '5f4dcc3b5aa765d61d8327deb882cf99', 'Admin', 'Admin', 'Admin', 'admin@gmail.com');

COMMIT;

