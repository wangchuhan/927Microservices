-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: provider_db
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `tour_booking`
--

DROP TABLE IF EXISTS `tour_booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tour_booking` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `scenic_spot_id` bigint NOT NULL DEFAULT '0',
  `booking_date` varchar(50) DEFAULT NULL,
  `time_slot` varchar(50) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `phone_number` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `booking_ibfk_1` (`scenic_spot_id`),
  CONSTRAINT `tour_booking_ibfk_1` FOREIGN KEY (`scenic_spot_id`) REFERENCES `scenicspots` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1833020739326869507 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tour_booking`
--

LOCK TABLES `tour_booking` WRITE;
/*!40000 ALTER TABLE `tour_booking` DISABLE KEYS */;
INSERT INTO `tour_booking` VALUES (1832662140423565314,'john_doe',1,'2024-09-15','08:00-10:00',2,'john_doe@example.com',NULL,NULL),(1832668179202113537,'john_doe',1,'2024-09-15','08:00-10:00',5,'john_doe@example.com',NULL,NULL),(1832668328896823298,'john_doe',1,'2024-09-15','08:00-10:00',5,'john_doe@example.com',NULL,NULL),(1832668441560023041,'john_doe',1,'2024-09-15','08:00-10:00',5,'john_doe@example.com',NULL,NULL),(1832670293601103873,'john_doe',1,'2024-09-15','08:00-10:00',1,'john_doe@example.com',NULL,NULL),(1833000411347402754,'8259665',1,'2024-09-15','08:00-10:00',2,'wangchuhan161@163.com',NULL,NULL),(1833004795594604546,'8259665',1,'2024-09-15','12:00-14:00',2,'wangchuhan161@163.com',NULL,NULL),(1833004967342964738,'8259665',1,'2024-09-15','12:00-14:00',2,'wangchuhan161@163.com',NULL,NULL),(1833005386433687553,'8259665',1,'2024-09-15','12:00-14:00',2,'wangchuhan161@163.com',NULL,NULL),(1833005748917915649,'8259665',1,'2024-09-15','12:00-14:00',2,'wangchuhan161@163.com',NULL,NULL),(1833006252246970370,'8259665',1,'2024-09-15','12:00-14:00',2,'wangchuhan161@163.com',NULL,NULL),(1833020739326869506,'8259665',1,'2024-09-15','12:00-14:00',2,'wangchuhan161@163.com','Chuhan Wang','112233665544');
/*!40000 ALTER TABLE `tour_booking` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-11  9:20:35
