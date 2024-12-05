-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: shoptest
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `attribute_type`
--

DROP TABLE IF EXISTS `attribute_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attribute_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attribute_type`
--

LOCK TABLES `attribute_type` WRITE;
/*!40000 ALTER TABLE `attribute_type` DISABLE KEYS */;
INSERT INTO `attribute_type` VALUES (1,'Màu sắc'),(2,'Chất liệu'),(3,'Kích thước');
/*!40000 ALTER TABLE `attribute_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attribute_value`
--

DROP TABLE IF EXISTS `attribute_value`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attribute_value` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `attribute_type_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7c5rn1xrmawvnaxxb5el7fyqr` (`attribute_type_id`),
  CONSTRAINT `FK7c5rn1xrmawvnaxxb5el7fyqr` FOREIGN KEY (`attribute_type_id`) REFERENCES `attribute_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attribute_value`
--

LOCK TABLES `attribute_value` WRITE;
/*!40000 ALTER TABLE `attribute_value` DISABLE KEYS */;
INSERT INTO `attribute_value` VALUES (1,'Đỏ',1),(2,'Xanh',1),(3,'Đen',1),(4,'Vàng',1),(5,'Nhựa',2),(6,'Kim loại',2),(7,'Gỗ',2),(8,'15cm',3),(9,'20cm',3),(10,'30cm',3);
/*!40000 ALTER TABLE `attribute_value` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `parent_id` bigint DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Danh mục các loại bút và phụ kiện liên quan',0,'Bút - Viết'),(2,'Danh mục dụng cụ học sinh',0,'Dụng cụ học sinh'),(3,'Danh mục dụng cụ vẽ',0,'Dụng Cụ Vẽ'),(4,'Danh mục dụng cụ văn phòng',0,'Dụng cụ văn phòng'),(5,'Danh mục sản phẩm điện tử',0,'Sản Phẩm Điện Tử'),(6,'Danh mục các loại bút gel',1,'Bút Gel-Bút Nước-Ruột Bút Gel'),(7,'Danh mục các loại bút nước',1,'Bút Chì-Ruột Bút Chì'),(8,'Phụ kiện ruột bút gel',1,'Bút Bi-Ruột Bút Bi'),(9,'Danh mục máy tính điện tử',5,'Máy tính điện tử');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hdn`
--

DROP TABLE IF EXISTS `hdn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hdn` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` date DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `supplier` varchar(255) DEFAULT NULL,
  `total_amount` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hdn`
--

LOCK TABLES `hdn` WRITE;
/*!40000 ALTER TABLE `hdn` DISABLE KEYS */;
INSERT INTO `hdn` VALUES (1,'2024-12-05','Hd1','Test',NULL),(2,'2024-12-06','Hd2','CtyA',2),(3,'2024-12-06','Hd3','CtyB',3),(4,'2024-12-10','Hd4','CtyC',6),(5,'2024-11-23','Hd5','CtyH',10),(6,'2024-12-06','test','Demo',6000);
/*!40000 ALTER TABLE `hdn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hdn_detail`
--

DROP TABLE IF EXISTS `hdn_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hdn_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `quantity` smallint DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `unit_price` double DEFAULT NULL,
  `hdn_id` bigint NOT NULL,
  `product_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKftdtm1x9i7alhq1m033phx0d5` (`hdn_id`),
  KEY `FK9u98upm09i8foou2rlncemgwf` (`product_id`),
  CONSTRAINT `FK9u98upm09i8foou2rlncemgwf` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKftdtm1x9i7alhq1m033phx0d5` FOREIGN KEY (`hdn_id`) REFERENCES `hdn` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hdn_detail`
--

LOCK TABLES `hdn_detail` WRITE;
/*!40000 ALTER TABLE `hdn_detail` DISABLE KEYS */;
INSERT INTO `hdn_detail` VALUES (1,1,1,1,1,1),(2,3,3,1,2,2),(3,1,1,1,3,3),(4,1,1,1,4,7),(5,1,1,1,5,8),(6,2,6000,3000,6,1);
/*!40000 ALTER TABLE `hdn_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `discount` double DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` smallint DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK551losx9j75ss5d6bfsqvijna` (`product_id`),
  KEY `FKhrk6dqsylympwsf88br40dcok` (`order_id`),
  CONSTRAINT `FK551losx9j75ss5d6bfsqvijna` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKhrk6dqsylympwsf88br40dcok` FOREIGN KEY (`order_id`) REFERENCES `order_product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` VALUES (5,NULL,NULL,1,2,2),(6,NULL,NULL,1,3,3),(7,NULL,NULL,1,4,7),(8,NULL,NULL,1,5,8),(10,NULL,NULL,1,1,1);
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_product`
--

DROP TABLE IF EXISTS `order_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `grand_total` double DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `shipping` double DEFAULT NULL,
  `status` enum('CANCELED','IN_CART','PURCHASED','SHIPPED','SUCCESS') DEFAULT NULL,
  `sub_total` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo9xn5tfed9g2dqctro3cg0h0` (`user_id`),
  CONSTRAINT `FKo9xn5tfed9g2dqctro3cg0h0` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_product`
--

LOCK TABLES `order_product` WRITE;
/*!40000 ALTER TABLE `order_product` DISABLE KEYS */;
INSERT INTO `order_product` VALUES (1,'','','2024-12-05',1500,23510,'',10,'IN_CART',25000,23510,'2024-12-06',1),(2,'123 Đường ABC, Quận 1, TP. HCM','Mua sắm thiết bị văn phòng','2024-12-01',10.5,1000,'0909123456',30,'PURCHASED',950,980,'2024-12-01',2),(3,'456 Đường DEF, Quận 3, TP. HCM','Mua sách giáo khoa và dụng cụ học tập','2024-12-02',15,1200,'0909123457',40,'SHIPPED',1150,1190,'2024-12-02',3),(4,'789 Đường GHI, Quận 5, TP. HCM','Đơn hàng đang xử lý','2024-12-03',0,500,'0909123458',20,'IN_CART',480,500,'2024-12-03',4),(5,'101 Đường JKL, Quận 7, TP. HCM','Mua hàng khuyến mãi cuối năm','2024-12-04',25,2000,'0909123459',50,'SUCCESS',1950,2000,'2024-12-04',5);
/*!40000 ALTER TABLE `order_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` date DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `inventory` smallint DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` smallint DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'2024-12-04',10,106,5000,1,'Bút Bi - Xanh Nhựa 15cm','but-bi.jpg'),(2,'2024-12-04',5,50,20000,1,'Thước Kẻ - Đen Kim Loại 30cm','thuoc-ke.jpg'),(3,'2024-12-04',15,150,3000,1,'Bút Chì - Đỏ Gỗ 20cm','but-chi.jpg'),(7,'2024-12-06',10,20,5000,1,'Compa','compa.jpg'),(8,'2024-12-06',6,50,20000,1,'Máy tính Casio Fx580','casio.jpg');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_category`
--

DROP TABLE IF EXISTS `product_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_category` (
  `product_id` bigint NOT NULL,
  `category_id` bigint NOT NULL,
  KEY `FKkud35ls1d40wpjb5htpp14q4e` (`category_id`),
  KEY `FK2k3smhbruedlcrvu6clued06x` (`product_id`),
  CONSTRAINT `FK2k3smhbruedlcrvu6clued06x` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKkud35ls1d40wpjb5htpp14q4e` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_category`
--

LOCK TABLES `product_category` WRITE;
/*!40000 ALTER TABLE `product_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_detail`
--

DROP TABLE IF EXISTS `product_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `attribute_val_id` int DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK41hg7ishftwgnmtdux9wpnjev` (`attribute_val_id`),
  KEY `FKilxoi77ctyin6jn9robktb16c` (`product_id`),
  CONSTRAINT `FK41hg7ishftwgnmtdux9wpnjev` FOREIGN KEY (`attribute_val_id`) REFERENCES `attribute_value` (`id`),
  CONSTRAINT `FKilxoi77ctyin6jn9robktb16c` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_detail`
--

LOCK TABLES `product_detail` WRITE;
/*!40000 ALTER TABLE `product_detail` DISABLE KEYS */;
INSERT INTO `product_detail` VALUES (1,2,1),(2,5,1),(3,8,1),(4,3,2),(5,6,2),(6,10,2),(7,1,3),(8,7,3),(9,9,3);
/*!40000 ALTER TABLE `product_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_review`
--

DROP TABLE IF EXISTS `product_review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_review` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `published` tinyint DEFAULT NULL,
  `published_at` date DEFAULT NULL,
  `rating` smallint DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `product_id` bigint NOT NULL,
  `parent_id` bigint DEFAULT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkaqmhakwt05p3n0px81b9pdya` (`product_id`),
  KEY `FKmw8bh2e19mh773k1wcu20xtc1` (`parent_id`),
  KEY `FK78cdr7qgrm9sp9igada7vk4xp` (`user_id`),
  CONSTRAINT `FK78cdr7qgrm9sp9igada7vk4xp` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKkaqmhakwt05p3n0px81b9pdya` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKmw8bh2e19mh773k1wcu20xtc1` FOREIGN KEY (`parent_id`) REFERENCES `product_review` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_review`
--

LOCK TABLES `product_review` WRITE;
/*!40000 ALTER TABLE `product_review` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id_role` int NOT NULL AUTO_INCREMENT,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'User'),(2,'Manager'),(3,'Admin');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `roleid` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2ovmsl4hvm5vu1w8i308r5j6w` (`roleid`),
  CONSTRAINT `FK2ovmsl4hvm5vu1w8i308r5j6w` FOREIGN KEY (`roleid`) REFERENCES `role` (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'avatar.jpg','user@user.com','1234567890','user1','1234',1),(2,'avatar1.jpg','john.doe@example.com','1234567890','John Doe','password123',1),(3,'avatar2.jpg','jane.smith@example.com','0987654321','Jane Smith','password456',2),(4,'avatar3.jpg','alice.williams@example.com','1122334455','Alice Williams','password789',3),(5,'avatar4.jpg','bob.johnson@example.com','5566778899','Bob Johnson','password321',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-06  5:29:16
