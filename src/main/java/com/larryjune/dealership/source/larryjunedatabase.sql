CREATE DATABASE  IF NOT EXISTS `larryjunedatabase` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `larryjunedatabase`;
-- MySQL dump 10.13  Distrib 8.4.9, for Linux (x86_64)
--
-- Host: 192.168.2.31    Database: larryjunedatabase
-- ------------------------------------------------------
-- Server version	8.4.9

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
-- Table structure for table `AccidentData`
--

DROP TABLE IF EXISTS `AccidentData`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `AccidentData` (
  `accidentID` int NOT NULL AUTO_INCREMENT,
  `vehicleID` int DEFAULT NULL,
  `dateOFAccident` date DEFAULT NULL,
  `severity` varchar(255) DEFAULT NULL,
  `descOfAccident` varchar(255) DEFAULT NULL,
  `airbagDeployment` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`accidentID`),
  KEY `vehicleID` (`vehicleID`),
  CONSTRAINT `AccidentData_ibfk_1` FOREIGN KEY (`vehicleID`) REFERENCES `vehicleData` (`vehicleID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AccidentData`
--

LOCK TABLES `AccidentData` WRITE;
/*!40000 ALTER TABLE `AccidentData` DISABLE KEYS */;
INSERT INTO `AccidentData` VALUES (1,1,'2025-01-12','Bad','Rear-end collision at stoplight',0),(2,6,'2025-04-10','Bad','Intersection T-bone accident',0),(3,7,'2025-05-01','Bad','Deer collision on rural road',1),(4,8,'2025-05-19','really Bad','Multi-car freeway pileup',0),(5,4,'2025-10-05','Bad','Single vehicle curb collision',1);
/*!40000 ALTER TABLE `AccidentData` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Accounts`
--

DROP TABLE IF EXISTS `Accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Accounts` (
  `accountID` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `accountPassword` varchar(30) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `shippingAddress` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`accountID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Accounts`
--

LOCK TABLES `Accounts` WRITE;
/*!40000 ALTER TABLE `Accounts` DISABLE KEYS */;
INSERT INTO `Accounts` VALUES (1,'John','Smith','951-555-1001','abcdefg','email@gmail.com','1234 Seseme Street, NY'),(2,'Sarah','Johnson','951-555-1002','abcdefg','email@gmail.com','1234 Seseme Street, NY'),(3,'Mike','Brown','951-555-1003','abcdefg','email@gmail.com','1234 Seseme Street, NY'),(4,'Emily','Davis','951-555-1004','abcdefg','email@gmail.com','1234 Seseme Street, NY'),(5,'Chris','Wilson','951-555-1005','abcdefg','email@gmail.com','1234 Seseme Street, NY'),(6,'Laura','Martinez','951-555-1006','abcdefg','email@gmail.com','1234 Seseme Street, NY'),(7,'David','Anderson','951-555-1007','abcdefg','email@gmail.com','1234 Seseme Street, NY'),(8,'Sophia','Taylor','951-555-1008','abcdefg','email@gmail.com','1234 Seseme Street, NY');
/*!40000 ALTER TABLE `Accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Appointment`
--

DROP TABLE IF EXISTS `Appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Appointment` (
  `employeeAccountID` int DEFAULT NULL,
  `customerAccountID` int DEFAULT NULL,
  `apointmentDate` date DEFAULT NULL,
  `typeOfAppointment` char(255) DEFAULT NULL,
  KEY `customerAccountID` (`customerAccountID`),
  KEY `employeeAccountID` (`employeeAccountID`),
  CONSTRAINT `Appointment_ibfk_1` FOREIGN KEY (`customerAccountID`) REFERENCES `CustomerAccount` (`customerAccountID`),
  CONSTRAINT `Appointment_ibfk_2` FOREIGN KEY (`employeeAccountID`) REFERENCES `EmployeeAccount` (`employeeAccountID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Appointment`
--

LOCK TABLES `Appointment` WRITE;
/*!40000 ALTER TABLE `Appointment` DISABLE KEYS */;
INSERT INTO `Appointment` VALUES (1,4,'2025-07-02','Test Drive'),(2,5,'2025-07-04','Financing Consultation'),(3,8,'2025-07-06','Vehicle Inquiry'),(6,4,'2025-07-09','Service Follow-up'),(7,5,'2025-07-11','Trade-in Evaluation'),(1,8,'2025-07-13','Test Drive'),(2,4,'2025-07-16','Purchase Discussion'),(3,5,'2025-07-19','Final Paperwork'),(6,8,'2025-07-21','Warranty Consultation'),(7,4,'2025-07-23','Vehicle Pickup');
/*!40000 ALTER TABLE `Appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CustomerAccount`
--

DROP TABLE IF EXISTS `CustomerAccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `CustomerAccount` (
  `customerAccountID` int NOT NULL,
  PRIMARY KEY (`customerAccountID`),
  CONSTRAINT `CustomerAccount_ibfk_1` FOREIGN KEY (`customerAccountID`) REFERENCES `Accounts` (`accountID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CustomerAccount`
--

LOCK TABLES `CustomerAccount` WRITE;
/*!40000 ALTER TABLE `CustomerAccount` DISABLE KEYS */;
INSERT INTO `CustomerAccount` VALUES (4),(5),(8);
/*!40000 ALTER TABLE `CustomerAccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EmployeeAccount`
--

DROP TABLE IF EXISTS `EmployeeAccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `EmployeeAccount` (
  `employeeAccountID` int NOT NULL,
  `totalSales` decimal(15,2) DEFAULT NULL,
  PRIMARY KEY (`employeeAccountID`),
  CONSTRAINT `EmployeeAccount_ibfk_1` FOREIGN KEY (`employeeAccountID`) REFERENCES `Accounts` (`accountID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EmployeeAccount`
--

LOCK TABLES `EmployeeAccount` WRITE;
/*!40000 ALTER TABLE `EmployeeAccount` DISABLE KEYS */;
INSERT INTO `EmployeeAccount` VALUES (1,15.00),(2,22.00),(3,40.00),(6,10.00),(7,55.00);
/*!40000 ALTER TABLE `EmployeeAccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ManagerAccount`
--

DROP TABLE IF EXISTS `ManagerAccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ManagerAccount` (
  `managerAccountID` int DEFAULT NULL,
  `managerstatus` varchar(255) DEFAULT NULL,
  KEY `managerAccountID` (`managerAccountID`),
  CONSTRAINT `ManagerAccount_ibfk_1` FOREIGN KEY (`managerAccountID`) REFERENCES `EmployeeAccount` (`employeeAccountID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ManagerAccount`
--

LOCK TABLES `ManagerAccount` WRITE;
/*!40000 ALTER TABLE `ManagerAccount` DISABLE KEYS */;
INSERT INTO `ManagerAccount` VALUES (3,'GONE'),(7,'GONE');
/*!40000 ALTER TABLE `ManagerAccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Sale`
--

DROP TABLE IF EXISTS `Sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Sale` (
  `saleID` int NOT NULL AUTO_INCREMENT,
  `vehicleID` int DEFAULT NULL,
  `employeeAccountID` int DEFAULT NULL,
  `customerAccountID` int DEFAULT NULL,
  `dateOFSale` date DEFAULT NULL,
  `amountPaid` decimal(15,2) DEFAULT NULL,
  PRIMARY KEY (`saleID`),
  KEY `vehicleID` (`vehicleID`),
  KEY `employeeAccountID` (`employeeAccountID`),
  KEY `customerAccountID` (`customerAccountID`),
  CONSTRAINT `Sale_ibfk_1` FOREIGN KEY (`vehicleID`) REFERENCES `vehicleData` (`vehicleID`),
  CONSTRAINT `Sale_ibfk_2` FOREIGN KEY (`employeeAccountID`) REFERENCES `EmployeeAccount` (`employeeAccountID`),
  CONSTRAINT `Sale_ibfk_3` FOREIGN KEY (`customerAccountID`) REFERENCES `CustomerAccount` (`customerAccountID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Sale`
--

LOCK TABLES `Sale` WRITE;
/*!40000 ALTER TABLE `Sale` DISABLE KEYS */;
INSERT INTO `Sale` VALUES (1,1,1,4,'2025-07-01',18000.00),(2,2,2,5,'2025-07-03',24000.00),(3,10,3,4,'2025-07-22',41500.00);
/*!40000 ALTER TABLE `Sale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Service`
--

DROP TABLE IF EXISTS `Service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Service` (
  `serviceID` int NOT NULL AUTO_INCREMENT,
  `vehicleID` int DEFAULT NULL,
  `dateOfService` date DEFAULT NULL,
  `descriptionOFService` varchar(255) DEFAULT NULL,
  `cost` decimal(15,2) DEFAULT NULL,
  `mileage` int DEFAULT NULL,
  PRIMARY KEY (`serviceID`),
  KEY `vehicleID` (`vehicleID`),
  CONSTRAINT `Service_ibfk_1` FOREIGN KEY (`vehicleID`) REFERENCES `vehicleData` (`vehicleID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Service`
--

LOCK TABLES `Service` WRITE;
/*!40000 ALTER TABLE `Service` DISABLE KEYS */;
INSERT INTO `Service` VALUES (1,3,'2025-03-01','Transmission fluid flush',300.00,76000),(2,6,'2025-04-18','Clutch adjustment and alignment',600.00,32000),(3,7,'2025-05-02','Oil change and air filter replacement',180.00,40000),(4,10,'2025-06-28','Detailing and full inspection service',200.00,14000);
/*!40000 ALTER TABLE `Service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `damage`
--

DROP TABLE IF EXISTS `damage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `damage` (
  `damageID` int NOT NULL AUTO_INCREMENT,
  `vehicleID` int DEFAULT NULL,
  `locationOfDamage` varchar(255) DEFAULT NULL,
  `severity` varchar(255) DEFAULT NULL,
  `repairCost` int DEFAULT NULL,
  `accidentID` int DEFAULT NULL,
  `airbagDeployment` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`damageID`),
  KEY `vehicleID` (`vehicleID`),
  KEY `accidentID` (`accidentID`),
  CONSTRAINT `damage_ibfk_1` FOREIGN KEY (`vehicleID`) REFERENCES `vehicleData` (`vehicleID`),
  CONSTRAINT `damage_ibfk_2` FOREIGN KEY (`accidentID`) REFERENCES `AccidentData` (`accidentID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `damage`
--

LOCK TABLES `damage` WRITE;
/*!40000 ALTER TABLE `damage` DISABLE KEYS */;
INSERT INTO `damage` VALUES (1,1,'Rear bumper','Moderate',3200,1,0),(2,4,'Front suspension','Severe',7800,5,0),(3,6,'Front end frame','Severe',10500,2,1),(4,7,'Hood and grille','Severe',6000,3,0),(5,8,'Multiple panels','Severe',15000,4,1);
/*!40000 ALTER TABLE `damage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `images`
--

DROP TABLE IF EXISTS `images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `images` (
  `imageID` int NOT NULL AUTO_INCREMENT,
  `vehicleID` int DEFAULT NULL,
  `imageURL` char(255) DEFAULT NULL,
  PRIMARY KEY (`imageID`),
  KEY `vehicleID` (`vehicleID`),
  CONSTRAINT `images_ibfk_1` FOREIGN KEY (`vehicleID`) REFERENCES `vehicleData` (`vehicleID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `images`
--

LOCK TABLES `images` WRITE;
/*!40000 ALTER TABLE `images` DISABLE KEYS */;
INSERT INTO `images` VALUES (1,5,'TOYOTA.png');
/*!40000 ALTER TABLE `images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicleData`
--

DROP TABLE IF EXISTS `vehicleData`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicleData` (
  `vehicleID` int NOT NULL AUTO_INCREMENT,
  `vinNumber` char(17) DEFAULT NULL,
  `price` decimal(15,2) DEFAULT NULL,
  `maker` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  `modelYear` int DEFAULT NULL,
  `bodyStyle` char(255) DEFAULT NULL,
  `isUsed` tinyint(1) DEFAULT NULL,
  `mileage` int DEFAULT NULL,
  `carStatus` varchar(255) DEFAULT NULL,
  `prevOwnerCount` int DEFAULT NULL,
  PRIMARY KEY (`vehicleID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicleData`
--

LOCK TABLES `vehicleData` WRITE;
/*!40000 ALTER TABLE `vehicleData` DISABLE KEYS */;
INSERT INTO `vehicleData` VALUES (1,'1HGCM82633A004352',18500.00,'Honda','Accord','Black',2018,'Sedan',1,62000,'1',1),(2,'2FTRX18W1XCA01234',24500.00,'Ford','F-150','Blue',2020,'Truck',1,45000,'1',1),(3,'3VWFE21C04M000111',13200.00,'Volkswagen','Jetta','White',2017,'Sedan',1,78000,'1',2),(4,'5YJ3E1EA7KF000222',35999.00,'Tesla','Model 3','Red',2021,'Sedan',0,12000,'1',0),(5,'1NXBR32E54Z123456',9800.00,'Toyota','Corolla','Silver',2015,'Sedan',1,99000,'0',2),(6,'JN1CV6AP8CM123789',27500.00,'Nissan','370Z','Yellow',2019,'Coupe',1,34000,'1',1),(7,'WA1LFAFP2DA123456',31000.00,'Audi','Q5','Gray',2020,'SUV',1,41000,'1',1),(8,'2C3CDXHG0JH123987',22000.00,'Dodge','Charger','Black',2018,'Sedan',1,55000,'1',2),(9,'1FA6P8TH5H5123456',26000.00,'Ford','Mustang','Blue',2019,'Coupe',1,30000,'1',1),(10,'SALWR2RV5GA123321',42000.00,'Land Rover','Range Rover Sport','White',2021,'SUV',0,15000,'1',0);
/*!40000 ALTER TABLE `vehicleData` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-25 13:12:28
