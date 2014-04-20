-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	5.6.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `mydb`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `mydb` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `mydb`;

--
-- Table structure for table `file`
--

DROP TABLE IF EXISTS `file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `file` (
  `fid` int(11) NOT NULL AUTO_INCREMENT,
  `fname` varchar(45) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `Directory` varchar(45) DEFAULT NULL,
  `hash` varchar(45) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `uid` int(11) NOT NULL,
  `isDir` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`fid`,`uid`),
  KEY `fk_File_User` (`uid`),
  CONSTRAINT `fk_File_User` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file`
--

LOCK TABLES `file` WRITE;
/*!40000 ALTER TABLE `file` DISABLE KEYS */;
INSERT INTO `file` VALUES (1,'testdir_Updated',NULL,NULL,NULL,0,1,1),(2,'testdir',NULL,NULL,NULL,0,1,1),(3,'testdir',NULL,NULL,NULL,0,1,1),(4,'testdir',NULL,NULL,NULL,0,1,1),(5,'testdir',NULL,NULL,NULL,0,1,1),(6,'testfile',NULL,'testdir',NULL,0,1,0),(7,'testdir',NULL,NULL,NULL,0,1,1),(8,'testdir',NULL,NULL,NULL,0,1,1),(9,'testdir',NULL,NULL,NULL,0,1,1),(10,'testdir',NULL,NULL,NULL,0,1,1),(11,'testdir',NULL,NULL,NULL,0,1,1),(12,'testdir',NULL,NULL,NULL,0,1,1),(13,'testdir',NULL,NULL,NULL,0,1,1),(14,'testdir',NULL,NULL,NULL,0,1,1),(15,'testdir',NULL,NULL,NULL,0,1,1),(16,'testdir',NULL,NULL,NULL,0,1,1),(17,'testdir',NULL,NULL,NULL,0,1,1),(18,'testdir',NULL,NULL,NULL,0,1,1),(20,'testdir',NULL,NULL,NULL,0,1,1),(22,'testdir',NULL,NULL,NULL,0,1,1),(23,'testdir',NULL,NULL,NULL,0,1,1),(24,'testdir',NULL,NULL,NULL,0,1,1),(26,'testdir',NULL,NULL,NULL,0,1,1),(28,'testdir',NULL,NULL,NULL,0,1,1);
/*!40000 ALTER TABLE `file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `history`
--

DROP TABLE IF EXISTS `history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `history` (
  `hid` int(11) NOT NULL AUTO_INCREMENT,
  `timestamp` int(11) DEFAULT NULL,
  `uid` int(11) NOT NULL,
  `File_fid_old` int(11) NOT NULL,
  `File_fid_new` int(11) NOT NULL,
  `action` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`hid`,`uid`,`File_fid_old`,`File_fid_new`),
  KEY `fk_File_User` (`uid`),
  KEY `fk_History_File_old` (`File_fid_old`),
  KEY `fk_History_File_new` (`File_fid_new`),
  CONSTRAINT `fk_File_User0` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_History_File_old` FOREIGN KEY (`File_fid_old`) REFERENCES `file` (`fid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_History_File_new` FOREIGN KEY (`File_fid_new`) REFERENCES `file` (`fid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history`
--

LOCK TABLES `history` WRITE;
/*!40000 ALTER TABLE `history` DISABLE KEYS */;
/*!40000 ALTER TABLE `history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `uname` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `current_version` int(11) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'sw','111','sw@a.com',0),(2,'zf','111','f@a.com',0),(3,'testuserUpdated','asdf','test@test.com',0),(4,'testuser','asdf','test@test.com',0),(5,'testuser','asdf','test@test.com',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-04-19 22:43:32
