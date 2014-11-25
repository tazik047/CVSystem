-- ----------------------------------------------------------------------------
-- MySQL Workbench Migration
-- Migrated Schemata: CVSystem
-- Source Schemata: CVSystem
-- Created: Tue Nov 25 23:06:27 2014
-- ----------------------------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;;

-- ----------------------------------------------------------------------------
-- Schema CVSystem
-- ----------------------------------------------------------------------------
DROP SCHEMA IF EXISTS `CVSystem` ;
CREATE SCHEMA IF NOT EXISTS `CVSystem` ;

-- ----------------------------------------------------------------------------
-- Table CVSystem.Companies
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `CVSystem`.`Companies` (
  `CompaniesId` BIGINT NOT NULL,
  `Title` LONGTEXT NOT NULL,
  `Phone` LONGTEXT NOT NULL,
  `PhoneRespPerson` LONGTEXT NOT NULL,
  `Email` LONGTEXT NOT NULL,
  `FIORespPerson` LONGTEXT NOT NULL,
  `Skype` LONGTEXT NULL,
  `Active` TINYINT UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`CompaniesId`),
  CONSTRAINT `FK_Companies_Users`
    FOREIGN KEY (`CompaniesId`)
    REFERENCES `CVSystem`.`Users` (`UsersId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

-- ----------------------------------------------------------------------------
-- Table CVSystem.CVs
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `CVSystem`.`CVs` (
  `CVsId` BIGINT NOT NULL,
  `PurposesId` BIGINT NOT NULL,
  `Qualities` LONGTEXT NULL,
  `Others` LONGTEXT NULL,
  `DateStamp` DATETIME(6) NOT NULL,
  PRIMARY KEY (`CVsId`),
  CONSTRAINT `FK_CVs_Purposes`
    FOREIGN KEY (`PurposesId`)
    REFERENCES `CVSystem`.`Purposes` (`PurposesId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CVs_Students`
    FOREIGN KEY (`CVsId`)
    REFERENCES `CVSystem`.`Students` (`StudentsId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

-- ----------------------------------------------------------------------------
-- Table CVSystem.Educations
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `CVSystem`.`Educations` (
  `EducationsId` BIGINT NOT NULL AUTO_INCREMENT,
  `StartYear` INT NOT NULL,
  `EndYear` INT NOT NULL,
  `NameOfInstitution` LONGTEXT NOT NULL,
  `Specialty` LONGTEXT NOT NULL,
  `Faculty` LONGTEXT NULL,
  `CVsId` BIGINT NOT NULL,
  PRIMARY KEY (`EducationsId`),
  CONSTRAINT `FK_Educations_CVs`
    FOREIGN KEY (`CVsId`)
    REFERENCES `CVSystem`.`CVs` (`CVsId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

-- ----------------------------------------------------------------------------
-- Table CVSystem.Faculties
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `CVSystem`.`Faculties` (
  `FacultiesId` BIGINT NOT NULL AUTO_INCREMENT,
  `Title` LONGTEXT NULL,
  PRIMARY KEY (`FacultiesId`));

-- ----------------------------------------------------------------------------
-- Table CVSystem.Groups
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `CVSystem`.`Groups` (
  `GroupsId` BIGINT NOT NULL AUTO_INCREMENT,
  `FacultiesId` BIGINT NOT NULL,
  `Title` LONGTEXT NOT NULL,
  PRIMARY KEY (`GroupsId`),
  CONSTRAINT `FK_Groups_Faculties`
    FOREIGN KEY (`FacultiesId`)
    REFERENCES `CVSystem`.`Faculties` (`FacultiesId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

-- ----------------------------------------------------------------------------
-- Table CVSystem.Languages
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `CVSystem`.`Languages` (
  `LanguagesId` BIGINT NOT NULL AUTO_INCREMENT,
  `Title` LONGTEXT NOT NULL,
  `TitleEN` LONGTEXT NULL,
  `TitleUA` LONGTEXT NULL,
  PRIMARY KEY (`LanguagesId`));

-- ----------------------------------------------------------------------------
-- Table CVSystem.LanguagesCVs
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `CVSystem`.`LanguagesCVs` (
  `LanguagesCVsId` BIGINT NOT NULL AUTO_INCREMENT,
  `LanguagesId` BIGINT NOT NULL,
  `CVsId` BIGINT NOT NULL,
  `Level` INT NOT NULL,
  PRIMARY KEY (`LanguagesCVsId`),
  CONSTRAINT `FK_LanguagesCVs_CVs`
    FOREIGN KEY (`CVsId`)
    REFERENCES `CVSystem`.`CVs` (`CVsId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_LanguagesCVs_Languages`
    FOREIGN KEY (`LanguagesId`)
    REFERENCES `CVSystem`.`Languages` (`LanguagesId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- ----------------------------------------------------------------------------
-- Table CVSystem.Pass
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `CVSystem`.`Pass` (
  `AccessPass` LONGTEXT NOT NULL);

-- ----------------------------------------------------------------------------
-- Table CVSystem.ProgramLanguages
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `CVSystem`.`ProgramLanguages` (
  `ProgramLanguagesId` BIGINT NOT NULL AUTO_INCREMENT,
  `Title` LONGTEXT NOT NULL,
  PRIMARY KEY (`ProgramLanguagesId`));

-- ----------------------------------------------------------------------------
-- Table CVSystem.ProgramLanguagesCVs
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `CVSystem`.`ProgramLanguagesCVs` (
  `ProgramLanguagesCVsId` BIGINT NOT NULL AUTO_INCREMENT,
  `ProgramLanguagesId` BIGINT NOT NULL,
  `CVsId` BIGINT NOT NULL,
  `Level` INT NOT NULL DEFAULT 1,
  PRIMARY KEY (`ProgramLanguagesCVsId`),
  CONSTRAINT `FK_ProgramLanguagesCVs_CVs`
    FOREIGN KEY (`CVsId`)
    REFERENCES `CVSystem`.`CVs` (`CVsId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_ProgramLanguagesCVs_ProgramLanguages`
    FOREIGN KEY (`ProgramLanguagesId`)
    REFERENCES `CVSystem`.`ProgramLanguages` (`ProgramLanguagesId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- ----------------------------------------------------------------------------
-- Table CVSystem.Purposes
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `CVSystem`.`Purposes` (
  `PurposesId` BIGINT NOT NULL AUTO_INCREMENT,
  `Title` LONGTEXT NOT NULL,
  `TitleEN` LONGTEXT NULL,
  `TitleUA` LONGTEXT NULL,
  PRIMARY KEY (`PurposesId`));

-- ----------------------------------------------------------------------------
-- Table CVSystem.Roles
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `CVSystem`.`Roles` (
  `Roles` BIGINT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`Roles`));

-- ----------------------------------------------------------------------------
-- Table CVSystem.Sertificats
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `CVSystem`.`Sertificats` (
  `SertificatsId` BIGINT NOT NULL AUTO_INCREMENT,
  `Name` LONGTEXT NOT NULL,
  `Year` INT NOT NULL,
  `CVsId` BIGINT NOT NULL,
  PRIMARY KEY (`SertificatsId`),
  CONSTRAINT `FK_Sertificats_CVs`
    FOREIGN KEY (`CVsId`)
    REFERENCES `CVSystem`.`CVs` (`CVsId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

-- ----------------------------------------------------------------------------
-- Table CVSystem.Students
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `CVSystem`.`Students` (
  `StudentsId` BIGINT NOT NULL AUTO_INCREMENT,
  `Surname` LONGTEXT NOT NULL,
  `Firstname` LONGTEXT NOT NULL,
  `Patronymic` LONGTEXT NULL,
  `Email` LONGTEXT NOT NULL,
  `Phone` LONGTEXT NOT NULL,
  `Birthday` DATE NOT NULL,
  `GroupsId` BIGINT NOT NULL,
  `Address` LONGTEXT NULL,
  `Skype` VARCHAR(50) NULL,
  PRIMARY KEY (`StudentsId`),
  CONSTRAINT `FK_Students_Groups`
    FOREIGN KEY (`GroupsId`)
    REFERENCES `CVSystem`.`Groups` (`GroupsId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- ----------------------------------------------------------------------------
-- Table CVSystem.Users
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `CVSystem`.`Users` (
  `UsersId` BIGINT NOT NULL AUTO_INCREMENT,
  `Role` TINYINT UNSIGNED NOT NULL,
  `Login` LONGTEXT NOT NULL,
  `Password` LONGTEXT NOT NULL,
  PRIMARY KEY (`UsersId`));

-- ----------------------------------------------------------------------------
-- Table CVSystem.WorkExps
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `CVSystem`.`WorkExps` (
  `WorkExpsId` BIGINT NOT NULL AUTO_INCREMENT,
  `StartDate` INT NOT NULL,
  `Duration` INT NULL,
  `TypeDuration` INT NULL,
  `NameOfInstitution` LONGTEXT NOT NULL,
  `Role` LONGTEXT NOT NULL,
  `CVsId` BIGINT NOT NULL,
  `IsNow` TINYINT UNSIGNED NULL,
  PRIMARY KEY (`WorkExpsId`),
  CONSTRAINT `FK_WorkExps_CVs`
    FOREIGN KEY (`CVsId`)
    REFERENCES `CVSystem`.`CVs` (`CVsId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

-- ----------------------------------------------------------------------------
-- Table CVSystem.sysdiagrams
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `CVSystem`.`sysdiagrams` (
  `name` VARCHAR(160) NOT NULL,
  `principal_id` INT NOT NULL,
  `diagram_id` INT NOT NULL AUTO_INCREMENT,
  `version` INT NULL,
  `definition` LONGBLOB NULL,
  PRIMARY KEY (`diagram_id`),
  UNIQUE INDEX `UK_principal_name` (`principal_id` ASC, `name` ASC));
SET FOREIGN_KEY_CHECKS = 1;;
