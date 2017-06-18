-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: transactionsdb
-- ------------------------------------------------------
-- Server version	5.7.16-log

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
-- Current Database: `transactionsdb`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `transactionsdb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `transactionsdb`;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction` (
  `transaction_id` int(11) NOT NULL AUTO_INCREMENT,
  `transaction_type_id` int(11) NOT NULL,
  `transaction_status_id` int(11) NOT NULL,
  `bank_account_sender_id` int(11) NOT NULL,
  `bank_account_receiver_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `payment_id` int(11) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `fk_transaction_transaction_type1_idx` (`transaction_type_id`),
  KEY `fk_transaction_transaction_status1_idx` (`transaction_status_id`),
  CONSTRAINT `fk_transaction_transaction_status1` FOREIGN KEY (`transaction_status_id`) REFERENCES `transaction_status` (`transaction_status_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_transaction_transaction_type1` FOREIGN KEY (`transaction_type_id`) REFERENCES `transaction_type` (`transaction_type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,1,1,21321,2321123,1,1,'1993-02-03'),(2,1,1,21321,2321123,1,1,'1993-02-03'),(3,1,1,21321,2321123,2,1,'1993-02-03'),(4,1,1,1232111,1232111,1,1,'2017-06-15'),(5,1,1,1,2,1,1,'2017-06-17'),(6,1,1,42,42,1,1,'2017-06-17'),(7,1,1,28,28,1,1,'2017-06-17'),(8,1,1,59,59,1,1,'2017-06-17'),(9,1,1,39,39,1,1,'2017-06-17'),(10,1,1,88,88,1,1,'2017-06-17'),(11,1,1,89,89,1,1,'2017-06-17'),(12,1,1,78,78,1,1,'2017-06-17'),(13,1,1,38,38,1,1,'2017-06-17');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction_status`
--

DROP TABLE IF EXISTS `transaction_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction_status` (
  `transaction_status_id` int(11) NOT NULL AUTO_INCREMENT,
  `transaction_status_name` varchar(145) NOT NULL,
  PRIMARY KEY (`transaction_status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_status`
--

LOCK TABLES `transaction_status` WRITE;
/*!40000 ALTER TABLE `transaction_status` DISABLE KEYS */;
INSERT INTO `transaction_status` VALUES (1,'Prosla'),(2,'Nije prosla');
/*!40000 ALTER TABLE `transaction_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction_type`
--

DROP TABLE IF EXISTS `transaction_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction_type` (
  `transaction_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `transaction_type_name` varchar(45) NOT NULL,
  PRIMARY KEY (`transaction_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_type`
--

LOCK TABLES `transaction_type` WRITE;
/*!40000 ALTER TABLE `transaction_type` DISABLE KEYS */;
INSERT INTO `transaction_type` VALUES (1,'Domaca'),(2,'Vanjska');
/*!40000 ALTER TABLE `transaction_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `usersdb`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `usersdb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `usersdb`;

--
-- Table structure for table `app_user`
--

DROP TABLE IF EXISTS `app_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `birth_date` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `jmbg` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `user_picture_id` int(11) DEFAULT NULL,
  `user_type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlroir20h7kqn8m9l5m6vwjbu3` (`user_picture_id`),
  KEY `FKnwd188l5juq4llwmk5fb68ljg` (`user_type_id`),
  CONSTRAINT `FKlroir20h7kqn8m9l5m6vwjbu3` FOREIGN KEY (`user_picture_id`) REFERENCES `user_picture` (`user_picture_id`),
  CONSTRAINT `FKnwd188l5juq4llwmk5fb68ljg` FOREIGN KEY (`user_type_id`) REFERENCES `user_type` (`user_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_user`
--

LOCK TABLES `app_user` WRITE;
/*!40000 ALTER TABLE `app_user` DISABLE KEYS */;
INSERT INTO `app_user` VALUES (1,'Trg Solidarnosti','2004-04-04 00:00:00','mujomujic@gmail.com','Mujo','123123123','Mujic','$2a$10$bnC26zz//2cavYoSCrlHdecWF8tkGfPodlHcYwlACBBwJvcEf0p2G','svlada3@gmail.com',1,1),(2,'Trg Solidarnosti','2004-04-04 00:00:00','neko@gmail.com','Hamo','123123123','Hamic','$2a$10$bnC26zz//2cavYoSCrlHdecWF8tkGfPodlHcYwlACBBwJvcEf0p2G','svlada2@gmail.com',1,1);
/*!40000 ALTER TABLE `app_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payments`
--

DROP TABLE IF EXISTS `payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payments` (
  `payment_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `payment_date` datetime NOT NULL,
  `payment_purpose` varchar(255) NOT NULL,
  `receiver_bank_acc_num` varchar(255) NOT NULL,
  `receiver_name` varchar(255) NOT NULL,
  `sneder_bank_acc_num` varchar(255) NOT NULL,
  `sender_name` varchar(255) NOT NULL,
  `payment_type` varchar(255) NOT NULL,
  `type_description` varchar(255) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`payment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payments`
--

LOCK TABLES `payments` WRITE;
/*!40000 ALTER TABLE `payments` DISABLE KEYS */;
/*!40000 ALTER TABLE `payments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_picture`
--

DROP TABLE IF EXISTS `user_picture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_picture` (
  `user_picture_id` int(11) NOT NULL AUTO_INCREMENT,
  `server_location` varchar(255) DEFAULT NULL,
  `upload_date` datetime DEFAULT NULL,
  `using_this_picture` bit(1) DEFAULT NULL,
  PRIMARY KEY (`user_picture_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_picture`
--

LOCK TABLES `user_picture` WRITE;
/*!40000 ALTER TABLE `user_picture` DISABLE KEYS */;
INSERT INTO `user_picture` VALUES (1,'sd','2005-04-04 00:00:00',NULL),(2,'sd','2005-04-04 00:00:00',NULL);
/*!40000 ALTER TABLE `user_picture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `role` varchar(255) NOT NULL,
  `app_user_id` int(11) NOT NULL,
  PRIMARY KEY (`role`,`app_user_id`),
  KEY `FKj16wg2x08hwytvgys4y9idf4b` (`app_user_id`),
  CONSTRAINT `FKj16wg2x08hwytvgys4y9idf4b` FOREIGN KEY (`app_user_id`) REFERENCES `app_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES ('ADMIN',1),('USER',2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_type`
--

DROP TABLE IF EXISTS `user_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_type` (
  `user_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_type_name` varchar(255) NOT NULL,
  PRIMARY KEY (`user_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_type`
--

LOCK TABLES `user_type` WRITE;
/*!40000 ALTER TABLE `user_type` DISABLE KEYS */;
INSERT INTO `user_type` VALUES (1,'asd'),(2,'asd');
/*!40000 ALTER TABLE `user_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `paymentsdb`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `paymentsdb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `paymentsdb`;

--
-- Table structure for table `payment_types`
--

DROP TABLE IF EXISTS `payment_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment_types` (
  `payment_type_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `payment_type_name` varchar(255) NOT NULL,
  PRIMARY KEY (`payment_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_types`
--

LOCK TABLES `payment_types` WRITE;
/*!40000 ALTER TABLE `payment_types` DISABLE KEYS */;
INSERT INTO `payment_types` VALUES (1,'InterniTransfer');
/*!40000 ALTER TABLE `payment_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payments`
--

DROP TABLE IF EXISTS `payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payments` (
  `payment_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `payment_date` datetime NOT NULL,
  `payment_purpose` varchar(255) NOT NULL,
  `receiver_bank_acc_num` varchar(255) NOT NULL,
  `receiver_name` varchar(255) NOT NULL,
  `sneder_bank_acc_num` varchar(255) NOT NULL,
  `sender_name` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `type_description` varchar(255) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `payment_type` bigint(20) NOT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `FKhb0kio5jsd80fxbq9y0w89eo9` (`payment_type`),
  CONSTRAINT `FKhb0kio5jsd80fxbq9y0w89eo9` FOREIGN KEY (`payment_type`) REFERENCES `payment_types` (`payment_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payments`
--

LOCK TABLES `payments` WRITE;
/*!40000 ALTER TABLE `payments` DISABLE KEYS */;
INSERT INTO `payments` VALUES (1,23,'2005-06-06 00:00:00','asd','2','sdfdsf','1','xcxycx','1','sdfsdf',1,1);
/*!40000 ALTER TABLE `payments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `templatesdb`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `templatesdb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `templatesdb`;

--
-- Table structure for table `template`
--

DROP TABLE IF EXISTS `template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `template` (
  `template_id` int(11) NOT NULL AUTO_INCREMENT,
  `payment_purpose` varchar(255) DEFAULT NULL,
  `payment_type_id` int(11) DEFAULT NULL,
  `receiver_bank_acc_number` varchar(255) DEFAULT NULL,
  `receiver_name` varchar(255) DEFAULT NULL,
  `sender_bank_acc_number` varchar(255) DEFAULT NULL,
  `sender_name` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`template_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `template`
--

LOCK TABLES `template` WRITE;
/*!40000 ALTER TABLE `template` DISABLE KEYS */;
INSERT INTO `template` VALUES (1,'sadasdasdasdasd',1,'33333','Mesud Klisura','22222','asdsad',1),(2,'sadasdasdasdasd',1,'33333','Mesud Klisura','22222','asdsad',1),(3,'sadasdasdasdasd',1,'33333','Klisura Mesud','22222','asdsad',1),(4,'sadasdasdasdasd',1,'33333','Mesud Klisura','22222','asdsad',1);
/*!40000 ALTER TABLE `template` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `bankaccountsdb`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `bankaccountsdb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `bankaccountsdb`;

--
-- Table structure for table `bank_account_types`
--

DROP TABLE IF EXISTS `bank_account_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bank_account_types` (
  `bank_account_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bank_account_type_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank_account_types`
--

LOCK TABLES `bank_account_types` WRITE;
/*!40000 ALTER TABLE `bank_account_types` DISABLE KEYS */;
INSERT INTO `bank_account_types` VALUES (1,'Tekuci'),(2,'Devizni'),(3,'Stedni'),(4,'Oroceni');
/*!40000 ALTER TABLE `bank_account_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bank_accounts`
--

DROP TABLE IF EXISTS `bank_accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bank_accounts` (
  `bank_account_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `account_number` varchar(255) DEFAULT NULL,
  `credit_amount` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `bank_account_type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`bank_account_id`),
  KEY `FK_co6mo1bgf4m3ywfm8bq32g6k` (`bank_account_type_id`)
) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank_accounts`
--

LOCK TABLES `bank_accounts` WRITE;
/*!40000 ALTER TABLE `bank_accounts` DISABLE KEYS */;
INSERT INTO `bank_accounts` VALUES (23,'Keno','1',377,1,1),(3,'Irfan','3',400,1,1),(4,'Edis','4',400,2,1),(5,'Edis','5',400,2,1),(6,'Edis','6',400,2,1),(7,'Edis','7',400,2,2),(8,'Edis','8',400,2,2),(24,'Anes','2',423,2,1);
/*!40000 ALTER TABLE `bank_accounts` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-18 22:46:46
