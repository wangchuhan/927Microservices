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
-- Table structure for table `tables`
--

DROP TABLE IF EXISTS `tables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tables` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `restaurant_cafe_id` bigint NOT NULL,
  `table_number` int NOT NULL,
  `capacity` int NOT NULL,
  `location` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `restaurant_cafe_id` (`restaurant_cafe_id`),
  CONSTRAINT `tables_ibfk_1` FOREIGN KEY (`restaurant_cafe_id`) REFERENCES `restaurants_cafes` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tables`
--

LOCK TABLES `tables` WRITE;
/*!40000 ALTER TABLE `tables` DISABLE KEYS */;
INSERT INTO `tables` VALUES (11,1,1,4,'Near the window'),(12,1,2,2,'By the entrance'),(13,1,3,6,'Corner table'),(14,1,4,4,'Garden view'),(15,1,5,4,'Patio'),(16,1,6,2,'Balcony'),(17,1,7,4,'Near the kitchen'),(18,1,8,6,'Center area'),(19,1,9,8,'Private room'),(20,1,10,2,'Rooftop'),(21,1,11,4,'Near the fireplace'),(22,1,12,6,'Lounge section'),(23,1,13,4,'Poolside'),(24,1,14,8,'VIP area'),(25,1,15,6,'Main hall'),(26,1,16,2,'Study corner'),(27,1,17,4,'Garden level'),(28,1,18,4,'Beachside view'),(29,1,19,6,'Near the bar'),(30,1,20,4,'Under the tree'),(31,2,1,4,'Near the window'),(32,2,2,2,'By the entrance'),(33,2,3,6,'Corner table'),(34,2,4,4,'Garden view'),(35,2,5,4,'Patio'),(36,2,6,2,'Balcony'),(37,2,7,4,'Near the kitchen'),(38,2,8,6,'Center area'),(39,2,9,8,'Private room'),(40,2,10,2,'Rooftop'),(41,2,11,4,'Near the fireplace'),(42,2,12,6,'Lounge section'),(43,2,13,4,'Poolside'),(44,2,14,8,'VIP area'),(45,2,15,6,'Main hall'),(46,2,16,2,'Study corner'),(47,2,17,4,'Garden level'),(48,2,18,4,'Beachside view'),(49,2,19,6,'Near the bar'),(50,2,20,4,'Under the tree'),(51,3,1,4,'Near the window'),(52,3,2,2,'By the entrance'),(53,3,3,6,'Corner table'),(54,3,4,4,'Garden view'),(55,3,5,4,'Patio'),(56,3,6,2,'Balcony'),(57,3,7,4,'Near the kitchen'),(58,3,8,6,'Center area'),(59,3,9,8,'Private room'),(60,3,10,2,'Rooftop'),(61,3,11,4,'Near the fireplace'),(62,3,12,6,'Lounge section'),(63,3,13,4,'Poolside'),(64,3,14,8,'VIP area'),(65,3,15,6,'Main hall'),(66,3,16,2,'Study corner'),(67,3,17,4,'Garden level'),(68,3,18,4,'Beachside view'),(69,3,19,6,'Near the bar'),(70,3,20,4,'Under the tree'),(71,4,1,4,'Near the window'),(72,4,2,2,'By the entrance'),(73,4,3,6,'Corner table'),(74,4,4,4,'Garden view'),(75,4,5,4,'Patio'),(76,4,6,2,'Balcony'),(77,4,7,4,'Near the kitchen'),(78,4,8,6,'Center area'),(79,4,9,8,'Private room'),(80,4,10,2,'Rooftop'),(81,4,11,4,'Near the fireplace'),(82,4,12,6,'Lounge section'),(83,4,13,4,'Poolside'),(84,4,14,8,'VIP area'),(85,4,15,6,'Main hall'),(86,4,16,2,'Study corner'),(87,4,17,4,'Garden level'),(88,4,18,4,'Beachside view'),(89,4,19,6,'Near the bar'),(90,4,20,4,'Under the tree'),(91,5,1,4,'Near the window'),(92,5,2,2,'By the entrance'),(93,5,3,6,'Corner table'),(94,5,4,4,'Garden view'),(95,5,5,4,'Patio'),(96,5,6,2,'Balcony'),(97,5,7,4,'Near the kitchen'),(98,5,8,6,'Center area'),(99,5,9,8,'Private room'),(100,5,10,2,'Rooftop'),(101,5,11,4,'Near the fireplace'),(102,5,12,6,'Lounge section'),(103,5,13,4,'Poolside'),(104,5,14,8,'VIP area'),(105,5,15,6,'Main hall'),(106,5,16,2,'Study corner'),(107,5,17,4,'Garden level'),(108,5,18,4,'Beachside view'),(109,5,19,6,'Near the bar'),(110,5,20,4,'Under the tree');
/*!40000 ALTER TABLE `tables` ENABLE KEYS */;
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
