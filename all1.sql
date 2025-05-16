-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: fds
-- ------------------------------------------------------
-- Server version	8.0.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `adminid` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `altphone` varchar(10) NOT NULL,
  `password` varchar(8) NOT NULL,
  PRIMARY KEY (`adminid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(45) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` VALUES ('harshad1@gmail.com','ROLE_RESTAURANT'),('harshad2@gmail.com','ROLE_DELIVERY'),('om1@gmail.com','ROLE_CUSTOMER'),('om2@gmail.com','ROLE_CUSTOMER'),('om3@gmail.com','ROLE_CUSTOMER'),('om4@gmail.com','ROLE_CUSTOMER'),('om6@gmail.com','ROLE_CUSTOMER'),('om7@gmail.com','ROLE_CUSTOMER'),('prafful3@gmail.com','ROLE_CUSTOMER');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customerId` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `email` varchar(45) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `altphone` varchar(10) NOT NULL,
  `Address` varchar(250) NOT NULL,
  `password` varchar(90) NOT NULL,
  PRIMARY KEY (`customerId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Om Prakash Phule','omphule77@gmail.com','7707777228','8626960196','siruseri chennai','Pass@123'),(2,'Harshad Joshi','harshad@gmail.com','4534675423','4537786764','SDB 2 - (G & 1st Flr) SDB 3 - (3 - 6 Flr), Survey No. 602/3, Plot No. 1, ELCOT IT/ ITES - SEZ, Sholinganallur Village','{bcrypt}Pass@123'),(3,'Harshad Joshi','harshad@gmail.com','4534675423','4537786764','SDB 2 - (G & 1st Flr) SDB 3 - (3 - 6 Flr), Survey No. 602/3, Plot No. 1, ELCOT IT/ ITES - SEZ, Sholinganallur Village','$2a$10$dZnprbwuvlFv1EFWcLFXS.IV2hpjBUsLCTqwuSgN/wIPWv3r4/j66'),(4,'Om Phule','om1@gmail.com','7709777228','4534675423','siruseri chennai','$2a$10$6OVYngAtZ8d0WzXeQYL8yukEQWtDPlZhUCG8DKjV0RwU4f/0vx/fu'),(5,'Om Phule','om2@gmail.com','7709777228','4534675423','siruseri chennai','$2a$10$adYajXR.OQG7bVpQmc/BcOqJzt6PZ6uL7mWNSlEFRsoPNElHdbY.O'),(6,'Om Phule','om6@gmail.com','7709777228','7876545676','siruseri chennai','$2a$10$7Kp53B05U32kolGwEUh92ebOd0179X4gwdbTCEwkcY9Lm8e3t7LXq'),(7,'Om Phule','om7@gmail.com','7709777228','7876545676','siruseri chennai','$2a$10$d4g7jZv6oeTjGXS0NXxeGOBD/NKUQ0tyqo2TM1.7olDxmopcNUX2y');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delivery`
--

DROP TABLE IF EXISTS `delivery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `delivery` (
  `agentid` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `altphone` varchar(10) NOT NULL,
  `password` varchar(100) NOT NULL,
  `dob` varchar(10) NOT NULL,
  `vehicle` varchar(10) NOT NULL,
  `adharcard` varchar(12) NOT NULL,
  `address` varchar(128) NOT NULL,
  `flag` varchar(2) NOT NULL,
  PRIMARY KEY (`agentid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery`
--

LOCK TABLES `delivery` WRITE;
/*!40000 ALTER TABLE `delivery` DISABLE KEYS */;
INSERT INTO `delivery` VALUES (1,'Om Prakash Phule','omphule77@gmail.com','7707777228','8626960196','Pass@123','2025-05-13','MH455665','675354687345','siruseri chennai','1'),(2,'Harshad Joshi','harshad2@gmail.com','4537786764','7878787878','$2a$10$qkTOgqZ9z0DZMR9yJcXbne7PR1e/30c2SYyhbucUwyC8PgRvlPhpy','2025-05-14','424423','2342342342','SDB 2 - (G & 1st Flr) SDB 3 - (3 - 6 Flr), Survey 602/3, Plot No. 1, ELCOT IT/ ITES - SEZ, Sholinganallur Village','1');
/*!40000 ALTER TABLE `delivery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant`
--

DROP TABLE IF EXISTS `restaurant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `restaurant` (
  `rid` int NOT NULL AUTO_INCREMENT,
  `rname` varchar(45) NOT NULL,
  `address` varchar(80) NOT NULL,
  `city` varchar(45) NOT NULL,
  `rphone` varchar(10) NOT NULL,
  `gstid` varchar(45) NOT NULL,
  `oname` varchar(45) NOT NULL,
  `oemail` varchar(45) NOT NULL,
  `ophone` varchar(10) NOT NULL,
  `oaltphone` varchar(10) NOT NULL,
  `password` varchar(100) NOT NULL,
  `otime` varchar(15) NOT NULL,
  `ctime` varchar(15) NOT NULL,
  `holiday` varchar(10) NOT NULL,
  `flag` varchar(2) NOT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant`
--

LOCK TABLES `restaurant` WRITE;
/*!40000 ALTER TABLE `restaurant` DISABLE KEYS */;
INSERT INTO `restaurant` VALUES (1,'Anand Restaurant','Siruseri Chennai','Chennai','6578342367','537298363982','Anand Adsule','anand@gmail.com','8767051016','7709777228','Anand@12','09:00','23:00','Wednesday','1'),(2,'Prafful','dczdczxcz','fsdf','34532234','2345234532','prafful','prafful1@gmail.com','334234234','423234242','pass123','15:14','15:17','Monday','1'),(3,'prafful Restro','pandharpur','sangola','7676767676','2345234532','prafful','prafful3@gmail.com','5656565656','2525252525','$2a$10$TRzif5R41ZiCYjN0wMvsRe7aQmeAc37oHWFisrtSKtD38ZSrraRAW','15:25','15:28','Monday','1'),(4,'harshad restro','dghsddf','sdfshdf','7834324734','4234234','harshad','harshad1@gmail.com','45345345','345345','$2a$10$9mhXF0qbXlIbRXDNWoO3ne5EnDsu7XaCU56bVutoTALy/HApclZgm','15:45','15:47','Monday','1');
/*!40000 ALTER TABLE `restaurant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `enabled` varchar(10) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('harshad1@gmail.com','$2a$10$9mhXF0qbXlIbRXDNWoO3ne5EnDsu7XaCU56bVutoTALy/HApclZgm','1'),('harshad2@gmail.com','$2a$10$qkTOgqZ9z0DZMR9yJcXbne7PR1e/30c2SYyhbucUwyC8PgRvlPhpy','1'),('om1@gmail.com','$2a$10$ODpwsQOdlFA2ezahPogewOboVA2GAu8B30RJI6gvd8PFkMtJHXrcW','1'),('om2@gmail.com','{bcrypt}$2a$10$Nk9ErAxbJgRNb0Pv6RgqTuTZsWKh9PncfjnSJUlEV/ERtSLSnntVO','1'),('om3@gmail.com','{noop}pass123','1'),('om4@gmail.com','{noop}pass123','1'),('om6@gmail.com','$2a$10$ortUq5SzaLiyj5tqV/BuMugKnP5pF2Tpka4Syc8cUF8aKD1wkSbcy','1'),('om7@gmail.com','$2a$10$d4g7jZv6oeTjGXS0NXxeGOBD/NKUQ0tyqo2TM1.7olDxmopcNUX2y','1'),('prafful3@gmail.com','$2a$10$TRzif5R41ZiCYjN0wMvsRe7aQmeAc37oHWFisrtSKtD38ZSrraRAW','1');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-15 17:41:18
