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
-- Table structure for table `table_reservations`
--

DROP TABLE IF EXISTS `table_reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `table_reservations` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `table_id` bigint NOT NULL,
  `reservation_date` varchar(10) NOT NULL,
  `time_slot` varchar(50) NOT NULL,
  `quantity` int NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `table_id` (`table_id`),
  CONSTRAINT `table_reservations_ibfk_1` FOREIGN KEY (`table_id`) REFERENCES `tables` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `table_reservations`
--

LOCK TABLES `table_reservations` WRITE;
/*!40000 ALTER TABLE `table_reservations` DISABLE KEYS */;
INSERT INTO `table_reservations` VALUES (1,11,'2024-09-15','18:00-20:00',4,'john_doe','john_doe@example.com','John Doe','123-456-7890'),(2,13,'2024-09-15','18:00-20:00',4,'john_doe','john_doe@example.com','John Doe','123-456-7890'),(3,14,'2024-09-15','18:00-20:00',4,'john_doe','john_doe@example.com','John Doe','123-456-7890'),(4,15,'2024-09-15','18:00-20:00',4,'john_doe','john_doe@example.com','John Doe','123-456-7890'),(5,17,'2024-09-15','18:00-20:00',4,'john_doe','john_doe@example.com','John Doe','123-456-7890'),(6,18,'2024-09-15','18:00-20:00',4,'john_doe','john_doe@example.com','John Doe','123-456-7890'),(7,19,'2024-09-15','18:00-20:00',4,'john_doe','john_doe@example.com','John Doe','123-456-7890'),(8,21,'2024-09-15','18:00-20:00',4,'john_doe','john_doe@example.com','John Doe','123-456-7890'),(9,22,'2024-09-15','18:00-20:00',4,'john_doe','john_doe@example.com','John Doe','123-456-7890'),(10,23,'2024-09-15','18:00-20:00',4,'john_doe','john_doe@example.com','John Doe','123-456-7890'),(11,24,'2024-09-15','18:00-20:00',4,'john_doe','john_doe@example.com','John Doe','123-456-7890'),(12,25,'2024-09-15','18:00-20:00',4,'john_doe','john_doe@example.com','John Doe','123-456-7890'),(13,27,'2024-09-15','18:00-20:00',4,'john_doe','john_doe@example.com','John Doe','123-456-7890'),(14,28,'2024-09-15','18:00-20:00',4,'john_doe','john_doe@example.com','John Doe','123-456-7890'),(15,29,'2024-09-15','18:00-20:00',4,'john_doe','john_doe@example.com','John Doe','123-456-7890'),(16,30,'2024-09-15','18:00-20:00',4,'john_doe','john_doe@example.com','John Doe','123-456-7890'),(17,11,'','18:00-20:00',4,'john_doe','john_doe@example.com','John Doe','123-456-7890'),(18,11,'2024-09-15','1:00-20:00',4,'john_doe','john_doe@example.com','John Doe','123-456-7890'),(19,13,'2024-09-15','1:00-20:00',4,'john_doe','john_doe@example.com','John Doe','123-456-7890'),(20,14,'2024-09-15','1:00-20:00',4,'john_doe','john_doe@example.com','John Doe','123-456-7890'),(21,11,'2024-09-15','08:00-10:00',4,'john_doe','john_doe@example.com','John Doe','123-456-7890'),(22,11,'2024-09-15','16:00-18:00',4,'john_doe','john_doe@example.com','John Doe','123-456-7890'),(23,13,'2024-09-15','16:00-18:00',4,'john_doe','john_doe@example.com','John Doe','123-456-7890'),(24,13,'2024-09-15','08:00-10:00',4,'8259665','wangchuhan161@163.com','Chuhan Wang','112233665544'),(25,31,'2024-09-15','08:00-10:00',4,'8259665','wangchuhan161@163.com','Chuhan Wang','112233665544'),(26,33,'2024-09-15','08:00-10:00',4,'8259665','wangchuhan161@163.com','Chuhan Wang','112233665544'),(27,34,'2024-09-15','08:00-10:00',4,'8259665','wangchuhan161@163.com','Chuhan Wang','112233665544'),(28,35,'2024-09-15','08:00-10:00',4,'8259665','wangchuhan161@163.com','Chuhan Wang','112233665544'),(29,37,'2024-09-15','08:00-10:00',4,'8259665','wangchuhan161@163.com','Chuhan Wang','112233665544'),(30,38,'2024-09-15','08:00-10:00',4,'8259665','wangchuhan161@163.com','Chuhan Wang','112233665544'),(31,39,'2024-09-15','08:00-10:00',4,'8259665','wangchuhan161@163.com','Chuhan Wang','112233665544');
/*!40000 ALTER TABLE `table_reservations` ENABLE KEYS */;
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
