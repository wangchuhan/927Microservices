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
-- Table structure for table `scenicspots`
--

DROP TABLE IF EXISTS `scenicspots`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `scenicspots` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `location` varchar(255) DEFAULT NULL,
  `capacity` int DEFAULT NULL,
  `description` text,
  `price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scenicspots`
--

LOCK TABLES `scenicspots` WRITE;
/*!40000 ALTER TABLE `scenicspots` DISABLE KEYS */;
INSERT INTO `scenicspots` VALUES (1,'Sydney Opera House','Sydney',20,'A famous performing arts center and iconic landmark.',50.00),(2,'Bondi Beach','Sydney',20,'A popular beach known for surfing and coastal walks.',0.00),(3,'Taronga Zoo','Sydney',20,'A large zoo with stunning views of Sydney Harbour.',45.00),(4,'Sydney Harbour Bridge','Sydney',20,'An iconic bridge offering BridgeClimb experiences.',80.00),(5,'Royal Botanic Garden','Sydney',20,'A vast garden in the heart of the city with beautiful plants and landscapes.',0.00),(6,'Darling Harbour','Sydney',20,'A lively waterfront area with restaurants, shops, and attractions.',0.00),(7,'Luna Park','Sydney',20,'A historic amusement park with rides and attractions.',30.00),(8,'Featherdale Wildlife Park','Sydney',20,'A wildlife park home to native Australian animals.',40.00),(9,'Hyde Park','Sydney',20,'The oldest public parkland in Australia, located in the CBD.',0.00),(10,'Manly Beach','Sydney',20,'A scenic beach with ferry access and plenty of shops and cafes.',0.00),(11,'Mount Keira','Wollongong',20,'A lookout point offering panoramic views of Wollongong and the coast.',0.00),(12,'Nan Tien Temple','Wollongong',20,'The largest Buddhist temple in the Southern Hemisphere.',10.00),(13,'Wollongong Botanic Garden','Wollongong',20,'A beautiful garden with native and exotic plants.',0.00),(14,'Science Space','Wollongong',20,'An interactive science museum and planetarium.',20.00),(15,'Wattamolla Beach','Wollongong',20,'A secluded beach with a lagoon and picnic area in the Royal National Park.',0.00),(16,'Sublime Point Lookout','Wollongong',20,'A lookout offering views of the Illawarra escarpment and coastline.',0.00),(17,'Illawarra Fly Treetop Walk','Wollongong',20,'A treetop walk offering elevated views of the surrounding rainforest.',25.00),(18,'Sea Cliff Bridge','Wollongong',20,'A scenic bridge along the coast, popular for walking and photography.',0.00),(19,'Bald Hill Lookout','Wollongong',20,'A famous hang gliding spot offering spectacular coastal views.',0.00),(20,'Minnamurra Rainforest','Wollongong',20,'A rainforest with walking trails and waterfalls in the Illawarra region.',15.00);
/*!40000 ALTER TABLE `scenicspots` ENABLE KEYS */;
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
