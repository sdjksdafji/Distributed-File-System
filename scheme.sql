SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE=TRADITIONAL;

DROP SCHEMA IF EXISTS mydb ;
CREATE SCHEMA IF NOT EXISTS mydb DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE mydb ;

-- -----------------------------------------------------
-- Table mydb.User
-- -----------------------------------------------------
DROP TABLE IF EXISTS mydb.User ;

CREATE  TABLE IF NOT EXISTS mydb.User (
  uid INT NOT NULL AUTO_INCREMENT,
  uname VARCHAR(45) NULL ,
  password VARCHAR(45) NULL ,
  email VARCHAR(45) NULL ,
  current_version INT NULL ,
  PRIMARY KEY (uid) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table mydb.File
-- -----------------------------------------------------
DROP TABLE IF EXISTS mydb.File ;

CREATE  TABLE IF NOT EXISTS mydb.File (
  fid INT NOT NULL AUTO_INCREMENT,
  fname VARCHAR(45) NULL ,
  location VARCHAR(45) NULL ,
  directory VARCHAR(45) NULL ,
  hash VARCHAR(45) NULL ,
  version INT NULL ,
  uid INT NOT NULL ,
  isDir TINYINT(1) NULL ,
  PRIMARY KEY (fid, uid) ,
  INDEX fk_File_User (uid ASC) ,
  CONSTRAINT fk_File_User
    FOREIGN KEY (uid )
    REFERENCES mydb.User (uid )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table mydb.History
-- -----------------------------------------------------
DROP TABLE IF EXISTS mydb.History ;

CREATE  TABLE IF NOT EXISTS mydb.History (
  hid INT NOT NULL AUTO_INCREMENT,
  timestamp INT NULL ,
  uid INT NOT NULL ,
  File_fid_old INT NOT NULL ,
  File_fid_new INT NOT NULL ,
  action VARCHAR(45) NULL ,
  PRIMARY KEY (hid, uid, File_fid_old, File_fid_new) ,
  INDEX fk_File_User (uid ASC) ,
  INDEX fk_History_File_old (File_fid_old ASC) ,
  INDEX fk_History_File_new (File_fid_new ASC) ,
  CONSTRAINT fk_File_User0
    FOREIGN KEY (uid )
    REFERENCES mydb.User (uid )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_History_File_old
    FOREIGN KEY (File_fid_old )
    REFERENCES mydb.File (fid )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_History_File_new
    FOREIGN KEY (File_fid_new )
    REFERENCES mydb.File (fid )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
