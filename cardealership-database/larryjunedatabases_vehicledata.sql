-- MySQL dump 10.13  Distrib 8.0.45, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: larryjunedatabases
-- ------------------------------------------------------
-- Server version	8.0.45

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
-- Table structure for table `vehicledata`
--

DROP TABLE IF EXISTS `vehicledata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicledata` (
  `vehicleID` int NOT NULL AUTO_INCREMENT,
  `vinNumber` char(17) DEFAULT NULL,
  `price` int DEFAULT NULL,
  `maker` char(255) DEFAULT NULL,
  `model` char(255) DEFAULT NULL,
  `color` char(255) DEFAULT NULL,
  `modelYear` int DEFAULT NULL,
  `bodyStyle` char(255) DEFAULT NULL,
  `isUsed` tinyint(1) DEFAULT NULL,
  `mileage` int DEFAULT NULL,
  `carStatus` tinyint(1) DEFAULT NULL,
  `prevOwnerCount` int DEFAULT NULL,
  PRIMARY KEY (`vehicleID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicledata`
--

LOCK TABLES `vehicledata` WRITE;
/*!40000 ALTER TABLE `vehicledata` DISABLE KEYS */;
INSERT INTO `vehicledata` VALUES (1,'1HGCM82633A004352',18500,'Honda','Accord','Black',2018,'Sedan',1,62000,1,1),(2,'2FTRX18W1XCA01234',24500,'Ford','F-150','Blue',2020,'Truck',1,45000,1,1),(3,'3VWFE21C04M000111',13200,'Volkswagen','Jetta','White',2017,'Sedan',1,78000,1,2),(4,'5YJ3E1EA7KF000222',35999,'Tesla','Model 3','Red',2021,'Sedan',0,12000,1,0),(5,'1NXBR32E54Z123456',9800,'Toyota','Corolla','Silver',2015,'Sedan',1,99000,0,2),(6,'JN1CV6AP8CM123789',27500,'Nissan','370Z','Yellow',2019,'Coupe',1,34000,1,1),(7,'WA1LFAFP2DA123456',31000,'Audi','Q5','Gray',2020,'SUV',1,41000,1,1),(8,'2C3CDXHG0JH123987',22000,'Dodge','Charger','Black',2018,'Sedan',1,55000,1,2),(9,'1FA6P8TH5H5123456',26000,'Ford','Mustang','Blue',2019,'Coupe',1,30000,1,1),(10,'SALWR2RV5GA123321',42000,'Land Rover','Range Rover Sport','White',2021,'SUV',0,15000,1,0);
/*!40000 ALTER TABLE `vehicledata` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-13 23:17:22
