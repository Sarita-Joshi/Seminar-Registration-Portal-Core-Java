-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: hotelmanagement
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `seminar`
--

DROP TABLE IF EXISTS `seminar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `seminar` (
  `sid` int(3) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `domain` varchar(100) DEFAULT NULL,
  `duration` decimal(4,2) DEFAULT NULL,
  `capacity` int(5) DEFAULT NULL,
  `price` decimal(6,2) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seminar`
--

LOCK TABLES `seminar` WRITE;
/*!40000 ALTER TABLE `seminar` DISABLE KEYS */;
INSERT INTO `seminar` VALUES (1,'android dev','android ev',3.00,10,100.00,'2019-10-07'),(2,'django','web dev',2.00,5,200.00,'2019-09-30'),(3,'ML','AI',4.00,7,150.00,'2019-09-28'),(4,'visual studio','coding',1.00,20,20.00,'2019-09-19'),(5,'ww','www',3.30,4,1000.00,'2019-09-22'),(6,'angular js','web dev',2.00,20,200.00,'2019-09-20'),(7,'asdasda','vbcvbvn',2.00,10,200.00,'2019-10-10'),(8,'i','i',2.00,2,10.00,'2020-02-02'),(9,'u','u',2.00,2,1.00,'2020-02-02'),(10,'MongoDB tutorials','dbms',2.00,20,100.00,'2019-10-10'),(11,'flutter ','android dev',2.00,20,200.00,'2019-11-11'),(12,'face detection','machine learning',2.00,20,200.00,'2020-05-02');
/*!40000 ALTER TABLE `seminar` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-06 13:16:22
