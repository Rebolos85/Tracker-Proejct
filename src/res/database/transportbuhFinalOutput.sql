-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: transporthubfinalproject
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customer_id_num` int NOT NULL AUTO_INCREMENT COMMENT 'Decide in meeting if ID is autoincrement or determined by our code''s logic',
  `order_id_num` int NOT NULL,
  `customername` varchar(60) NOT NULL,
  `address` varchar(100) NOT NULL COMMENT 'replace gui design''s textfield with textarea and textpane',
  `customerphone` varchar(11) NOT NULL,
  PRIMARY KEY (`customer_id_num`),
  UNIQUE KEY `customer_id_#_UNIQUE` (`customer_id_num`),
  KEY `order_id_#_idx` (`order_id_num`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,2,'Gwapo','Nazareth','0999999999'),(2,2,'Gwapa','Camaman-an','2'),(3,10005,'Roberto','Ramonal Village','00665555593'),(4,10007,'Roberto','Rebolos','09312312'),(5,10009,'Roberto','Rebolos','9931313443'),(6,10011,'Jotaku Rebolos','31th  Nazareth Cagayan de Oro City','01231313131'),(7,10013,'Ester','31th Nazareth Cagayan de Oro City','312321 312'),(8,10015,'Ester','Allie','09069255596'),(9,10019,'John Tex Ortaliz Rara','Consolacion','09069255596'),(10,10021,'John Tex Ortaliz Rara','Consolacion','09069255596');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager_account`
--

DROP TABLE IF EXISTS `manager_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manager_account` (
  `account_id_num` int NOT NULL AUTO_INCREMENT,
  `firstname` varchar(55) NOT NULL,
  `lastname` varchar(55) NOT NULL,
  `middleInitial` varchar(55) NOT NULL,
  `username` varchar(55) NOT NULL,
  `password` varchar(45) NOT NULL,
  `confirmPassword` varchar(55) NOT NULL,
  `masterKeyID` varchar(55) NOT NULL,
  `phoneNumber` varchar(20) NOT NULL,
  `age` int NOT NULL,
  `day` int NOT NULL,
  `month` varchar(55) NOT NULL,
  `year` int NOT NULL,
  PRIMARY KEY (`account_id_num`),
  UNIQUE KEY `account_id_#_UNIQUE` (`account_id_num`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `password_UNIQUE` (`password`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager_account`
--

LOCK TABLES `manager_account` WRITE;
/*!40000 ALTER TABLE `manager_account` DISABLE KEYS */;
INSERT INTO `manager_account` VALUES (1,'','','','JD','bruh','','','',0,0,'0',0),(2,'Roberto','Rebolos','Java','Rebolos143#','Programmerako143#','Programmerako143#','','(3123)- 213- (1231)',0,1,'2',2002),(3,'Roberto','Rebolos','Java','Javadevelopersoon143#','Rebolos143#','Rebolos143#','ShadowFiend','(3132)- 123- (1231)',0,1,'2',2002),(9,'Roberto','Rebolos','Java','Gogoy143#','Javadevelopersoon143#','Javadevelopersoon143#','3131','(3213)- 213- (2131)',0,1,'2',2002),(13,'Roberto','Rebolos','Java','Rebolos143#13123','','','30','(3131)- 231- (2321)',0,1,'2',2002),(14,'Roberto','Rebolos','Java','Budang143#','Roberto143#','Roberto143#','1231231','(3123)- 123- (1231)',0,1,'2',2002),(30,'Roberto','Rebolos','Java','GwapoKo143#','Natalieismyforever143#','Natalieismyforever143#','3131231231','(3123)- 123- (1231)',0,1,'2',2002),(31,'Roberto','Rebolos','Java','Java143#3131','RebolosGwapo143#','RebolosGwapo143#','November','(3212)- 321- (3123)',0,1,'2',2002),(33,'Roberto','Rebolos','Java','Gwapo14344#','Romel143#','Romel143#','312312','(1231)- 231- (2313)',0,1,'2',2002),(34,'Roberto','Rebolos','Java','Grace143#','Ester143#','Ester143#','3123141','(0906)- 925- (5596)',0,1,'2',2002),(37,'Roberto','Rebolos','Java','Ginafy143#','GraceEster143#','GraceEster143#','(3031)- 312- (3131)','Bajao',30,30,'12',2000),(38,'Roberto','Rebolos','JavaIsMyLife','JavaProgramming143#','12345678','12345678','(3123)- 123- (1231)','Roberto',30,30,'12',2000),(40,'Roberto','Rebolos','Java','Zhaskia143%','Zhaskia143#','Zhaskia143#','(0906)- 925- (5596)','30',30,30,'12',2000),(41,'Roberto','Rebolos','Java','Rebolos143#$$','Developersoon143#','Developersoon143#','(3123)- 213- (1231)','31231',30,30,'12',2000),(42,'Rebolos','Roberto','Java','ProgrammingIsMyLife143#','111111111C','111111111C','(3123)- 123- (1231)','31231',30,30,'12',2000),(43,'Roberto','Rebolos','Java','DarrelHerra143#','ZhaskiaGogo143#','ZhaskiaGogo143#','(0906)- 925- (5596)','30',30,30,'12',2000),(44,'Roberto','Rebolos','Java','Gabriel143#','SamimotJay143#','SamimotJay143#','(3131)- 231- (2312)','12312321dada',30,30,'12',2000),(45,'Roberto','Rebolos','Zhaskia','ZhaskiaRonelle143#','RonelleJay143#','RonelleJay143#','(0906)- 925- (5966)','30',30,30,'12',2000),(49,'Roberto','Rebolos','Java','NatalieIsMyLife143#','NatalieForever143#','NatalieForever143#','(3123)- 123- (1231)','312321',30,30,'12',2000),(50,'Roberto','Rebolos','Abejuela','Joshua143#','Joshua143#','Joshua143#','(0906)- 925- (5596)','30',30,31,'12',2002),(51,'Roberto','Rebolos','Java','ZhaskiaToledo143','Toledo143#','Toledo143#','(0906)- 925- (5555)','30',30,30,'12',2000),(52,'Claire','Monis','Java','ClaireMonis143#','Kassie143#','Kassie143#','(0906)- 925- (5559)','313131',30,31,'12',2000),(54,'Roberto','Rebolos','Java','Kassie143#','Kassandra143$','Kassandra143$','(3031)- 231- (2312)','312321',18,30,'12',2000),(55,'Rebolos','Java','Zhaskia','KrystelRyan143#','KrystelGwapo143#','KrystelGwapo143#','(0906)- 925- (5559)','30',18,30,'12',2000),(56,'Roberto','Rebolos','Java','KimDelacerna1#','Kimdelacerna143#','Kimdelacerna143#','(3123)- 213- (1231)','3123123',198,30,'12',2000);
/*!40000 ALTER TABLE `manager_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_item` (
  `order_item_id_num` int NOT NULL AUTO_INCREMENT,
  `order_id_num` int NOT NULL,
  `item_name` varchar(40) NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`order_item_id_num`),
  UNIQUE KEY `order_item_id_#_UNIQUE` (`order_item_id_num`),
  KEY `_idx` (`order_id_num`),
  CONSTRAINT `order id #` FOREIGN KEY (`order_id_num`) REFERENCES `orders` (`order_id_num`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` VALUES (1,1,'RTX',50),(2,10007,'Electric Fan',12),(3,10009,'24inches msi',20),(4,10011,'27 inches monitor',20),(5,10013,'Razer Keyboard2',20),(6,10015,'Mouse Razer',100),(7,10019,'Television 64 inches',20),(8,10021,'1 set of computer',90);
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_status`
--

DROP TABLE IF EXISTS `order_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_status` (
  `order_status_id_num` int NOT NULL AUTO_INCREMENT,
  `account_id_num` int NOT NULL,
  `order_id_num` int NOT NULL,
  `status` varchar(100) NOT NULL,
  `date` date NOT NULL,
  `location` varchar(100) NOT NULL,
  PRIMARY KEY (`order_status_id_num`),
  UNIQUE KEY `order_status_id_#_UNIQUE` (`order_status_id_num`),
  KEY `account id #_idx` (`account_id_num`),
  KEY `order id #_idx` (`order_id_num`),
  CONSTRAINT ` order id #` FOREIGN KEY (`order_id_num`) REFERENCES `orders` (`order_id_num`),
  CONSTRAINT `account id #` FOREIGN KEY (`account_id_num`) REFERENCES `manager_account` (`account_id_num`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_status`
--

LOCK TABLES `order_status` WRITE;
/*!40000 ALTER TABLE `order_status` DISABLE KEYS */;
INSERT INTO `order_status` VALUES (1,1,10015,'Shipped','2000-12-29','31th Nazareth Cagayan de Oro City'),(2,1,10017,'Shipped','2000-12-16','Ramonal Village Camaman an'),(3,1,10021,'Shipped','2000-12-12','Consolacion');
/*!40000 ALTER TABLE `order_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id_num` int NOT NULL AUTO_INCREMENT,
  `account_id_num` int NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`order_id_num`),
  UNIQUE KEY `order_id_#_UNIQUE` (`order_id_num`),
  KEY `account_id_#_idx` (`account_id_num`),
  CONSTRAINT `account_id_#` FOREIGN KEY (`account_id_num`) REFERENCES `manager_account` (`account_id_num`)
) ENGINE=InnoDB AUTO_INCREMENT=10022 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,2,'0000-02-01'),(10000,1,'0001-01-01'),(10002,1,'2020-11-22'),(10003,1,'2020-11-22'),(10004,1,'2020-11-22'),(10005,1,'2020-11-22'),(10006,1,'2020-11-22'),(10007,1,'2020-11-22'),(10008,1,'2020-11-22'),(10009,1,'2020-11-22'),(10010,1,'2020-11-22'),(10011,1,'2020-11-22'),(10012,1,'2020-11-22'),(10013,1,'2020-11-22'),(10014,1,'2020-11-22'),(10015,1,'2020-11-22'),(10016,1,'2020-11-22'),(10017,1,'2020-11-22'),(10018,1,'2020-11-22'),(10019,1,'2020-11-22'),(10020,1,'2020-11-22'),(10021,1,'2020-11-22');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-22 23:51:07
