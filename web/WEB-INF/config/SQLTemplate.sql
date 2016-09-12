-- MySQL dump 10.13  Distrib 5.6.26, for Win32 (x86)
--
-- Host: localhost    Database: pcms
-- ------------------------------------------------------
-- Server version	5.6.26

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
drop database if exists pcms;
create database pcms;
use pcms;
--
-- Table structure for table `customcolumn`
--

DROP TABLE IF EXISTS `customcolumn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customcolumn` (
  `name` varchar(100) NOT NULL,
  `described` text NOT NULL,
  `tableName` varchar(200) NOT NULL,
  `updateTime` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customcolumn`
--

LOCK TABLES `customcolumn` WRITE;
/*!40000 ALTER TABLE `customcolumn` DISABLE KEYS */;
/*!40000 ALTER TABLE `customcolumn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customsetting`
--

DROP TABLE IF EXISTS `customsetting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customsetting` (
  `articleCustomAttributes` varchar(500) DEFAULT NULL,
  `customAttributes` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customsetting`
--

LOCK TABLES `customsetting` WRITE;
/*!40000 ALTER TABLE `customsetting` DISABLE KEYS */;
/*!40000 ALTER TABLE `customsetting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customtable`
--

DROP TABLE IF EXISTS `customtable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customtable` (
  `name` varchar(200) NOT NULL,
  `tagName` bit(1) DEFAULT NULL,
  `customProperties` text,
  `updateTime` date DEFAULT NULL,
  `temp` text,
  PRIMARY KEY (`name`),
  UNIQUE KEY `tagName` (`tagName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customtable`
--

LOCK TABLES `customtable` WRITE;
/*!40000 ALTER TABLE `customtable` DISABLE KEYS */;
/*!40000 ALTER TABLE `customtable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `displaysetting`
--

DROP TABLE IF EXISTS `displaysetting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `displaysetting` (
  `smallImageWidth` int(11) DEFAULT '80',
  `smallImageHeight` int(11) DEFAULT '80',
  `decimalCount` int(11) DEFAULT '2',
  `ArticleListCount` int(11) DEFAULT '10',
  `ArticleListCountForIndex` int(11) DEFAULT '10',
  `productListCount` int(11) DEFAULT '10',
  `productListCountForIndex` int(11) DEFAULT '5'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `displaysetting`
--

LOCK TABLES `displaysetting` WRITE;
/*!40000 ALTER TABLE `displaysetting` DISABLE KEYS */;
/*!40000 ALTER TABLE `displaysetting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `en_us`
--

DROP TABLE IF EXISTS `en_us`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `en_us` (
  `name` varchar(50) NOT NULL,
  `value` varchar(500) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `en_us`
--

LOCK TABLES `en_us` WRITE;
/*!40000 ALTER TABLE `en_us` DISABLE KEYS */;
/*!40000 ALTER TABLE `en_us` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `keyassignments`
--

DROP TABLE IF EXISTS `keyassignments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `keyassignments` (
  `value` text,
  `type` varchar(100) DEFAULT NULL,
  `describing` text,
  `name` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `keyassignments`
--

LOCK TABLES `keyassignments` WRITE;
/*!40000 ALTER TABLE `keyassignments` DISABLE KEYS */;
/*!40000 ALTER TABLE `keyassignments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log` (
  `id` varchar(50) NOT NULL,
  `updateTime` date DEFAULT NULL,
  `user` varchar(100) DEFAULT NULL,
  `ipAddress` varchar(100) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `param` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user` (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mailsettting`
--

DROP TABLE IF EXISTS `mailsettting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mailsettting` (
  `mailServe` int(11) DEFAULT '1',
  `smtpserver` varchar(500) DEFAULT NULL,
  `port` varchar(20) DEFAULT NULL,
  `isUseSsl` int(11) DEFAULT '1',
  `sendMailAddress` varchar(100) DEFAULT NULL,
  `sendMailPassword` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mailsettting`
--

LOCK TABLES `mailsettting` WRITE;
/*!40000 ALTER TABLE `mailsettting` DISABLE KEYS */;
/*!40000 ALTER TABLE `mailsettting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `id` varchar(30) NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  `action` bit(1) DEFAULT NULL,
  `path` bit(1) DEFAULT NULL,
  `updateTime` date DEFAULT NULL,
  `userType` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plugs`
--

DROP TABLE IF EXISTS `plugs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plugs` (
  `id` varchar(50) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `type` int(11) DEFAULT '1',
  `params` text,
  `action` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plugs`
--

LOCK TABLES `plugs` WRITE;
/*!40000 ALTER TABLE `plugs` DISABLE KEYS */;
/*!40000 ALTER TABLE `plugs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `routinesetting`
--

DROP TABLE IF EXISTS `routinesetting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `routinesetting` (
  `siteName` varchar(200) DEFAULT NULL,
  `siteTitle` varchar(200) DEFAULT NULL,
  `siteKey` varchar(200) DEFAULT NULL,
  `siteDes` varchar(500) DEFAULT NULL,
  `logo` varchar(250) DEFAULT NULL,
  `companyAddress` varchar(300) DEFAULT NULL,
  `isUse` int(11) DEFAULT '1',
  `icp` varchar(500) DEFAULT NULL,
  `customerTel` varchar(30) DEFAULT NULL,
  `fax` varchar(50) DEFAULT NULL,
  `customerQQ` varchar(15) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `language` int(11) DEFAULT NULL,
  `urlWrite` varchar(200) DEFAULT NULL,
  `isUseSiteMap` int(11) DEFAULT '1',
  `isUseVerificationCode` int(11) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `routinesetting`
--

LOCK TABLES `routinesetting` WRITE;
/*!40000 ALTER TABLE `routinesetting` DISABLE KEYS */;
/*!40000 ALTER TABLE `routinesetting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` varchar(100) NOT NULL,
  `name` varchar(150) NOT NULL,
  `password` varchar(100) NOT NULL,
  `updateTime` date DEFAULT NULL,
  `headPortrait` varchar(250) NOT NULL,
  `tel` varchar(25) DEFAULT NULL,
  `mail` varchar(50) DEFAULT NULL,
  `type` int(11) NOT NULL DEFAULT '1',
  `mobile` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zh_cn`
--

DROP TABLE IF EXISTS `zh_cn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zh_cn` (
  `name` varchar(20) NOT NULL,
  `value` varchar(300) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zh_cn`
--

LOCK TABLES `zh_cn` WRITE;
/*!40000 ALTER TABLE `zh_cn` DISABLE KEYS */;
/*!40000 ALTER TABLE `zh_cn` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-12 15:49:42

