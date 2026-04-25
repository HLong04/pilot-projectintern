-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: db_managementshop
-- ------------------------------------------------------
-- Server version	8.0.42

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
-- Table structure for table `branches`
--

DROP TABLE IF EXISTS `branches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `branches` (
  `BranchID` int NOT NULL AUTO_INCREMENT,
  `BranchName` varchar(255) NOT NULL,
  `Province` varchar(255) DEFAULT NULL,
  `Address` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `branch_name` varchar(255) NOT NULL,
  `is_active` bit(1) NOT NULL,
  PRIMARY KEY (`BranchID`),
  KEY `Province` (`Province`),
  CONSTRAINT `branches_ibfk_1` FOREIGN KEY (`Province`) REFERENCES `provinces` (`ProvinceName`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cartitems`
--

DROP TABLE IF EXISTS `cartitems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cartitems` (
  `CartID` int NOT NULL,
  `ProductID` int NOT NULL,
  `Quantity` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`CartID`,`ProductID`),
  KEY `ProductID` (`ProductID`),
  CONSTRAINT `cartitems_ibfk_1` FOREIGN KEY (`CartID`) REFERENCES `carts` (`CartID`),
  CONSTRAINT `cartitems_ibfk_2` FOREIGN KEY (`ProductID`) REFERENCES `products` (`ProductID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `carts`
--

DROP TABLE IF EXISTS `carts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carts` (
  `CartID` int NOT NULL AUTO_INCREMENT,
  `CustomerID` int NOT NULL,
  `CreatedTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UpdatedTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_time` datetime(6) DEFAULT NULL,
  `updated_time` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`CartID`),
  UNIQUE KEY `CustomerID` (`CustomerID`),
  CONSTRAINT `carts_ibfk_1` FOREIGN KEY (`CustomerID`) REFERENCES `customers` (`CustomerID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `CategoryID` int NOT NULL AUTO_INCREMENT,
  `CategoryName` varchar(255) NOT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `category_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CategoryID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `CustomerID` int NOT NULL AUTO_INCREMENT,
  `CustomerName` varchar(255) NOT NULL,
  `ContactName` varchar(255) NOT NULL,
  `Province` varchar(255) DEFAULT NULL,
  `Address` varchar(255) DEFAULT NULL,
  `Phone` varchar(255) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `IsLocked` tinyint(1) DEFAULT NULL,
  `contact_name` varchar(255) NOT NULL,
  `customer_name` varchar(255) NOT NULL,
  `is_locked` bit(1) DEFAULT NULL,
  PRIMARY KEY (`CustomerID`),
  KEY `Province` (`Province`),
  CONSTRAINT `customers_ibfk_1` FOREIGN KEY (`Province`) REFERENCES `provinces` (`ProvinceName`)
) ENGINE=InnoDB AUTO_INCREMENT=4793 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `EmployeeID` int NOT NULL AUTO_INCREMENT,
  `FullName` varchar(255) NOT NULL,
  `BirthDate` date DEFAULT NULL,
  `Address` varchar(255) DEFAULT NULL,
  `Phone` varchar(255) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `Photo` varchar(255) DEFAULT NULL,
  `IsWorking` tinyint(1) DEFAULT NULL,
  `RoleNames` varchar(500) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `full_name` varchar(255) NOT NULL,
  `is_working` bit(1) DEFAULT NULL,
  `role_names` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`EmployeeID`),
  UNIQUE KEY `Email` (`Email`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `orderdetails`
--

DROP TABLE IF EXISTS `orderdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderdetails` (
  `OrderID` int NOT NULL,
  `ProductID` int NOT NULL,
  `Quantity` int NOT NULL,
  `SalePrice` decimal(18,2) NOT NULL,
  `sale_price` decimal(38,2) DEFAULT NULL,
  PRIMARY KEY (`OrderID`,`ProductID`),
  KEY `ProductID` (`ProductID`),
  CONSTRAINT `orderdetails_ibfk_1` FOREIGN KEY (`OrderID`) REFERENCES `orders` (`OrderID`),
  CONSTRAINT `orderdetails_ibfk_2` FOREIGN KEY (`ProductID`) REFERENCES `products` (`ProductID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `OrderID` int NOT NULL AUTO_INCREMENT,
  `CustomerID` int DEFAULT NULL,
  `OrderTime` datetime NOT NULL,
  `DeliveryProvince` varchar(255) DEFAULT NULL,
  `DeliveryAddress` varchar(255) DEFAULT NULL,
  `EmployeeID` int DEFAULT NULL,
  `AcceptTime` datetime DEFAULT NULL,
  `ShipperID` int DEFAULT NULL,
  `ShippedTime` datetime DEFAULT NULL,
  `FinishedTime` datetime DEFAULT NULL,
  `Status` int NOT NULL,
  `BranchID` int DEFAULT NULL,
  `VoucherID` int DEFAULT NULL,
  `DiscountAmount` decimal(18,2) NOT NULL DEFAULT '0.00',
  `accept_time` datetime(6) DEFAULT NULL,
  `delivery_address` varchar(255) DEFAULT NULL,
  `delivery_province` varchar(255) DEFAULT NULL,
  `discount_amount` decimal(38,2) NOT NULL,
  `finished_time` datetime(6) DEFAULT NULL,
  `order_time` datetime(6) DEFAULT NULL,
  `shipped_time` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`OrderID`),
  KEY `CustomerID` (`CustomerID`),
  KEY `EmployeeID` (`EmployeeID`),
  KEY `ShipperID` (`ShipperID`),
  KEY `Status` (`Status`),
  KEY `BranchID` (`BranchID`),
  KEY `VoucherID` (`VoucherID`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`CustomerID`) REFERENCES `customers` (`CustomerID`),
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`EmployeeID`) REFERENCES `employees` (`EmployeeID`),
  CONSTRAINT `orders_ibfk_3` FOREIGN KEY (`ShipperID`) REFERENCES `shippers` (`ShipperID`),
  CONSTRAINT `orders_ibfk_4` FOREIGN KEY (`Status`) REFERENCES `orderstatus` (`Status`),
  CONSTRAINT `orders_ibfk_5` FOREIGN KEY (`BranchID`) REFERENCES `branches` (`BranchID`),
  CONSTRAINT `orders_ibfk_6` FOREIGN KEY (`VoucherID`) REFERENCES `vouchers` (`VoucherID`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `orderstatus`
--

DROP TABLE IF EXISTS `orderstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderstatus` (
  `Status` int NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`Status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `payments`
--

DROP TABLE IF EXISTS `payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payments` (
  `PaymentID` int NOT NULL AUTO_INCREMENT,
  `OrderID` int NOT NULL,
  `PaymentMethod` varchar(50) NOT NULL,
  `TransactionNo` varchar(255) DEFAULT NULL,
  `amount` decimal(38,2) DEFAULT NULL,
  `PaymentTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `PaymentStatus` varchar(50) NOT NULL,
  `payment_method` varchar(255) DEFAULT NULL,
  `payment_status` varchar(255) DEFAULT NULL,
  `payment_time` datetime(6) DEFAULT NULL,
  `transaction_no` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`PaymentID`),
  KEY `OrderID` (`OrderID`),
  CONSTRAINT `payments_ibfk_1` FOREIGN KEY (`OrderID`) REFERENCES `orders` (`OrderID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `productattributes`
--

DROP TABLE IF EXISTS `productattributes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productattributes` (
  `AttributeID` bigint NOT NULL AUTO_INCREMENT,
  `ProductID` int NOT NULL,
  `AttributeName` varchar(255) NOT NULL,
  `AttributeValue` varchar(500) NOT NULL,
  `DisplayOrder` int NOT NULL,
  `attribute_name` varchar(255) NOT NULL,
  `attribute_value` varchar(255) NOT NULL,
  `display_order` int DEFAULT NULL,
  PRIMARY KEY (`AttributeID`),
  KEY `ProductID` (`ProductID`),
  CONSTRAINT `productattributes_ibfk_1` FOREIGN KEY (`ProductID`) REFERENCES `products` (`ProductID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `productphotos`
--

DROP TABLE IF EXISTS `productphotos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productphotos` (
  `PhotoID` bigint NOT NULL AUTO_INCREMENT,
  `ProductID` int NOT NULL,
  `Photo` varchar(255) NOT NULL,
  `Description` varchar(255) NOT NULL,
  `DisplayOrder` int NOT NULL,
  `IsHidden` tinyint(1) NOT NULL,
  `display_order` int DEFAULT NULL,
  `is_hidden` bit(1) DEFAULT NULL,
  PRIMARY KEY (`PhotoID`),
  KEY `ProductID` (`ProductID`),
  CONSTRAINT `productphotos_ibfk_1` FOREIGN KEY (`ProductID`) REFERENCES `products` (`ProductID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `ProductID` int NOT NULL AUTO_INCREMENT,
  `ProductName` varchar(255) NOT NULL,
  `ProductDescription` text,
  `SupplierID` int DEFAULT NULL,
  `CategoryID` int DEFAULT NULL,
  `Unit` varchar(255) NOT NULL,
  `price` decimal(38,2) DEFAULT NULL,
  `Photo` varchar(255) DEFAULT NULL,
  `IsSelling` tinyint(1) DEFAULT NULL,
  `is_selling` bit(1) DEFAULT NULL,
  `product_description` text,
  `product_name` varchar(255) NOT NULL,
  PRIMARY KEY (`ProductID`),
  KEY `CategoryID` (`CategoryID`),
  KEY `SupplierID` (`SupplierID`),
  CONSTRAINT `products_ibfk_1` FOREIGN KEY (`CategoryID`) REFERENCES `categories` (`CategoryID`),
  CONSTRAINT `products_ibfk_2` FOREIGN KEY (`SupplierID`) REFERENCES `suppliers` (`SupplierID`)
) ENGINE=InnoDB AUTO_INCREMENT=770 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `productstocks`
--

DROP TABLE IF EXISTS `productstocks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productstocks` (
  `ProductID` int NOT NULL,
  `BranchID` int NOT NULL,
  `StockQuantity` int NOT NULL DEFAULT '0',
  `stock_quantity` int DEFAULT NULL,
  PRIMARY KEY (`ProductID`,`BranchID`),
  KEY `BranchID` (`BranchID`),
  CONSTRAINT `productstocks_ibfk_1` FOREIGN KEY (`ProductID`) REFERENCES `products` (`ProductID`),
  CONSTRAINT `productstocks_ibfk_2` FOREIGN KEY (`BranchID`) REFERENCES `branches` (`BranchID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `provinces`
--

DROP TABLE IF EXISTS `provinces`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `provinces` (
  `ProvinceName` varchar(255) NOT NULL,
  `province_name` varchar(255) NOT NULL,
  PRIMARY KEY (`ProvinceName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `shippers`
--

DROP TABLE IF EXISTS `shippers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shippers` (
  `ShipperID` int NOT NULL AUTO_INCREMENT,
  `ShipperName` varchar(255) NOT NULL,
  `Phone` varchar(255) DEFAULT NULL,
  `shipper_name` varchar(255) NOT NULL,
  PRIMARY KEY (`ShipperID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `suppliers`
--

DROP TABLE IF EXISTS `suppliers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `suppliers` (
  `SupplierID` int NOT NULL AUTO_INCREMENT,
  `SupplierName` varchar(255) NOT NULL,
  `ContactName` varchar(255) NOT NULL,
  `Province` varchar(255) DEFAULT NULL,
  `Address` varchar(255) DEFAULT NULL,
  `Phone` varchar(255) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `contact_name` varchar(255) NOT NULL,
  `supplier_name` varchar(255) NOT NULL,
  PRIMARY KEY (`SupplierID`),
  KEY `Province` (`Province`),
  CONSTRAINT `suppliers_ibfk_1` FOREIGN KEY (`Province`) REFERENCES `provinces` (`ProvinceName`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `vouchers`
--

DROP TABLE IF EXISTS `vouchers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vouchers` (
  `VoucherID` int NOT NULL AUTO_INCREMENT,
  `VoucherCode` varchar(50) NOT NULL,
  `DiscountPercent` decimal(5,2) DEFAULT NULL,
  `DiscountAmount` decimal(18,2) DEFAULT NULL,
  `MaxDiscountAmount` decimal(18,2) DEFAULT NULL,
  `MinOrderValue` decimal(18,2) DEFAULT NULL,
  `StartDate` datetime NOT NULL,
  `EndDate` datetime NOT NULL,
  `UsageLimit` int DEFAULT NULL,
  `UsedCount` int NOT NULL DEFAULT '0',
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `discount_amount` decimal(38,2) DEFAULT NULL,
  `discount_percent` decimal(38,2) DEFAULT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `start_date` datetime(6) DEFAULT NULL,
  `usage_limit` int DEFAULT NULL,
  `used_count` int DEFAULT NULL,
  `voucher_code` varchar(255) NOT NULL,
  PRIMARY KEY (`VoucherID`),
  UNIQUE KEY `VoucherCode` (`VoucherCode`),
  UNIQUE KEY `UKhvqsc8qffpt5okjmyot3a4b77` (`voucher_code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-25 12:15:13
