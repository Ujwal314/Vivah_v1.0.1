-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: mark1
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `address_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`address_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `address_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,1,'Pune','Maharashtra','411001','India'),(2,2,'Dharwad','Karnataka','59004','India'),(4,4,'Dharwad','Karnataka','59004','India');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `caste`
--

DROP TABLE IF EXISTS `caste`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `caste` (
  `caste_id` int NOT NULL AUTO_INCREMENT,
  `caste_name` varchar(100) NOT NULL,
  `religion_id` int DEFAULT NULL,
  PRIMARY KEY (`caste_id`),
  KEY `religion_id` (`religion_id`),
  CONSTRAINT `caste_ibfk_1` FOREIGN KEY (`religion_id`) REFERENCES `religion` (`religion_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caste`
--

LOCK TABLES `caste` WRITE;
/*!40000 ALTER TABLE `caste` DISABLE KEYS */;
INSERT INTO `caste` VALUES (1,'Brahmin',6),(2,'Kshatriya',6),(3,'Vaishya',6),(4,'Shudra',6),(5,'Dalit',6),(6,'Adivasi',6),(7,'Kayastha',6),(8,'Rajput',6),(9,'Yadav',6),(10,'Kurmi',6),(11,'Jat',6),(12,'Gupta',6),(13,'Nair',6),(14,'Maratha',6),(15,'Koli',6),(16,'Baniya',6),(17,'Lingayat',6),(18,'Vokkaliga',6),(19,'Reddy',6),(20,'Kamma',6),(21,'Sunni',3),(22,'Shai',3);
/*!40000 ALTER TABLE `caste` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `family_details`
--

DROP TABLE IF EXISTS `family_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `family_details` (
  `family_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `father_name` varchar(255) DEFAULT NULL,
  `mother_name` varchar(255) DEFAULT NULL,
  `siblings_count` int DEFAULT NULL,
  `annual_income` double DEFAULT NULL,
  PRIMARY KEY (`family_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `family_details_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `family_details`
--

LOCK TABLES `family_details` WRITE;
/*!40000 ALTER TABLE `family_details` DISABLE KEYS */;
INSERT INTO `family_details` VALUES (1,1,'Ramesh Sharma','Sita Sharma',2,850000),(3,2,'Jinga','Jonga',1,850000);
/*!40000 ALTER TABLE `family_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gotra`
--

DROP TABLE IF EXISTS `gotra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gotra` (
  `gotra_id` int NOT NULL AUTO_INCREMENT,
  `gotra_name` varchar(100) NOT NULL,
  PRIMARY KEY (`gotra_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gotra`
--

LOCK TABLES `gotra` WRITE;
/*!40000 ALTER TABLE `gotra` DISABLE KEYS */;
INSERT INTO `gotra` VALUES (1,'Atri'),(2,'Bharadwaj'),(3,'Gautam'),(4,'Jamadagni'),(5,'Kashyap'),(6,'Vashishtha'),(7,'Vishwamitra');
/*!40000 ALTER TABLE `gotra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `media`
--

DROP TABLE IF EXISTS `media`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `media` (
  `photo_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `photo_url` varchar(500) NOT NULL,
  `uploaded_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `photo` longblob NOT NULL,
  PRIMARY KEY (`photo_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `media_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `media`
--

LOCK TABLES `media` WRITE;
/*!40000 ALTER TABLE `media` DISABLE KEYS */;
/*!40000 ALTER TABLE `media` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nakshatra`
--

DROP TABLE IF EXISTS `nakshatra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nakshatra` (
  `nakshatra_id` int NOT NULL AUTO_INCREMENT,
  `nakshatra_name` varchar(100) NOT NULL,
  PRIMARY KEY (`nakshatra_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nakshatra`
--

LOCK TABLES `nakshatra` WRITE;
/*!40000 ALTER TABLE `nakshatra` DISABLE KEYS */;
INSERT INTO `nakshatra` VALUES (1,'Aswini'),(2,'Bharani'),(3,'Krittika'),(4,'Rohini'),(5,'Mirigasirisam'),(6,'Thiruvathirai'),(7,'Punarpusam'),(8,'Poosam'),(9,'Ayilyam'),(10,'Magam'),(11,'Pooram'),(12,'Utthiram'),(13,'Astham'),(14,'Chittirai'),(15,'Swati'),(16,'Visakam'),(17,'Anusham'),(18,'Ketai'),(19,'Mulam'),(20,'Pooradam'),(21,'Utthiradam'),(22,'Thiruvonam'),(23,'Avittam'),(24,'Sathayam'),(25,'Pooratadhi'),(26,'Utthiratadhi'),(27,'Revati');
/*!40000 ALTER TABLE `nakshatra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rashi`
--

DROP TABLE IF EXISTS `rashi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rashi` (
  `rashi_id` int NOT NULL AUTO_INCREMENT,
  `rashi_name` varchar(100) NOT NULL,
  PRIMARY KEY (`rashi_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rashi`
--

LOCK TABLES `rashi` WRITE;
/*!40000 ALTER TABLE `rashi` DISABLE KEYS */;
INSERT INTO `rashi` VALUES (1,'Mesha'),(2,'Vrishabha'),(3,'Mithuna'),(4,'Karka'),(5,'Simha'),(6,'Kanya'),(7,'Tula'),(8,'Vrishchika'),(9,'Dhanu'),(10,'Makara'),(11,'Kumbha'),(12,'Meena');
/*!40000 ALTER TABLE `rashi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `religion`
--

DROP TABLE IF EXISTS `religion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `religion` (
  `religion_id` int NOT NULL AUTO_INCREMENT,
  `religion_name` varchar(100) NOT NULL,
  PRIMARY KEY (`religion_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `religion`
--

LOCK TABLES `religion` WRITE;
/*!40000 ALTER TABLE `religion` DISABLE KEYS */;
INSERT INTO `religion` VALUES (1,'Christianity'),(2,'Judaism'),(3,'Islam'),(4,'Buddhism'),(5,'Zoroastrian'),(6,'Hindu'),(7,'Sikh'),(8,'Shinto'),(9,'Baha’i'),(10,'Taoism'),(11,'Jain'),(12,'Confucianism'),(13,'Syncretic'),(14,'Religions'),(15,'Animist'),(16,'Non-Religious'),(17,'Others'),(18,'Muslim');
/*!40000 ALTER TABLE `religion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `fname` varchar(255) NOT NULL,
  `lname` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `dob` varchar(255) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `marital_status` varchar(20) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `is_active` tinyint(1) DEFAULT '1',
  `active` bit(1) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Madhu','H','madhu_updated','2003-06-12',NULL,NULL,'madhu_updated@example.com','0123456789','$2a$10$yCdqONtKxss.NPx0m2xtLeav1zI/S6vDAgm1nKxvmVWIYyjsjkC4y',1,_binary ''),(2,'Prateek','H','prateek_updated','2003-08-21',NULL,NULL,'prateek_updated@example.com','999999999','$2a$10$7B3f/RjhA2gsqMEVw9fi1.vumDQ9dYLRNlJEF1pBHFKwv9rC3yCdi',1,_binary ''),(3,'xyz','H','xyz_updated','2003-08-21',NULL,NULL,'xyz_updated@example.com','999999999','$2a$10$jiX1ujuP1HHJGKq/NCu/vurwSgIovwrJv1V3/n1aNfWaKUznj4qxq',1,_binary ''),(4,'santoshi','H','santoshi_updated','2003-08-21',NULL,NULL,'santoshi_updated@example.com','999999999','$2a$10$bvn2WY35b.eZ3hYM0HwVZOZkWDqdlWSvAbEBO6mrKhsPXe/eCEn8e',1,_binary '');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_profile`
--

DROP TABLE IF EXISTS `user_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_profile` (
  `profile_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `age` int DEFAULT NULL,
  `height` double DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `salary_package` double DEFAULT NULL,
  `job_location` varchar(255) DEFAULT NULL,
  `education` varchar(255) DEFAULT NULL,
  `occupation` varchar(255) DEFAULT NULL,
  `mangalik` tinyint(1) DEFAULT NULL,
  `disability` tinyint(1) DEFAULT NULL,
  `disablity_type` varchar(255) DEFAULT NULL,
  `blood_group` varchar(255) DEFAULT NULL,
  `rashi_id` int DEFAULT NULL,
  `nakshatra_id` int DEFAULT NULL,
  `gotra_id` int DEFAULT NULL,
  `paada` int DEFAULT NULL,
  `caste_id` int DEFAULT NULL,
  `religion_id` int DEFAULT NULL,
  `gender` bit(1) DEFAULT NULL,
  `marital_status` varchar(255) DEFAULT NULL,
  `address_id` bigint DEFAULT NULL,
  `family_id` bigint DEFAULT NULL,
  PRIMARY KEY (`profile_id`),
  UNIQUE KEY `user_id` (`user_id`),
  UNIQUE KEY `UKsl22qp555clcdwdh6q1rw8u76` (`address_id`),
  UNIQUE KEY `UKbplcn5nkkjw1e8gfwdi4ey8vb` (`family_id`),
  KEY `rashi_id` (`rashi_id`),
  KEY `nakshatra_id` (`nakshatra_id`),
  KEY `gotra_id` (`gotra_id`),
  KEY `caste_id` (`caste_id`),
  KEY `religion_id` (`religion_id`),
  CONSTRAINT `FK91nwqlnevj0euwg719pdvy2ub` FOREIGN KEY (`family_id`) REFERENCES `family_details` (`family_id`),
  CONSTRAINT `FKt7ck1erxe5phbhrdk1cn92h52` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`),
  CONSTRAINT `user_profile_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE,
  CONSTRAINT `user_profile_ibfk_2` FOREIGN KEY (`rashi_id`) REFERENCES `rashi` (`rashi_id`) ON DELETE SET NULL,
  CONSTRAINT `user_profile_ibfk_3` FOREIGN KEY (`nakshatra_id`) REFERENCES `nakshatra` (`nakshatra_id`) ON DELETE SET NULL,
  CONSTRAINT `user_profile_ibfk_4` FOREIGN KEY (`gotra_id`) REFERENCES `gotra` (`gotra_id`) ON DELETE SET NULL,
  CONSTRAINT `user_profile_ibfk_5` FOREIGN KEY (`caste_id`) REFERENCES `caste` (`caste_id`) ON DELETE SET NULL,
  CONSTRAINT `user_profile_ibfk_6` FOREIGN KEY (`religion_id`) REFERENCES `religion` (`religion_id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_profile`
--

LOCK TABLES `user_profile` WRITE;
/*!40000 ALTER TABLE `user_profile` DISABLE KEYS */;
INSERT INTO `user_profile` VALUES (1,1,22,5.7,45,700000,'Banglore','B.Tech in Computers','Software Engineer',0,1,NULL,'O+',1,1,2,1,3,2,_binary '','Divorced',NULL,NULL),(2,2,22,5.7,45,700000,'Banglore','B.Tech in Computers','Software Engineer',0,1,NULL,'O+',1,1,2,1,3,2,_binary '\0','Divorced',NULL,NULL),(3,4,22,5.7,45,700000,'Banglore','B.Tech in Computers','Software Engineer',0,1,NULL,'O+',1,1,2,1,3,2,_binary '','Divorced',NULL,NULL);
/*!40000 ALTER TABLE `user_profile` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-10-07 14:13:58
