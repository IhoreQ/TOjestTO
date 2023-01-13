-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: chewmaker
-- ------------------------------------------------------
-- Server version	5.5.5-10.6.7-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ingredients`
--

DROP TABLE IF EXISTS `ingredients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredients` (
  `id_ingredient` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `id_ingriedient_type` int(11) NOT NULL,
  PRIMARY KEY (`id_ingredient`),
  UNIQUE KEY `id_ingredient_UNIQUE` (`id_ingredient`),
  KEY `ingredients_ingredients_types_id_ingredient_type_fk` (`id_ingriedient_type`),
  CONSTRAINT `ingredients_ingredients_types_id_ingredient_type_fk` FOREIGN KEY (`id_ingriedient_type`) REFERENCES `ingredients_types` (`id_ingredient_type`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredients`
--

LOCK TABLES `ingredients` WRITE;
/*!40000 ALTER TABLE `ingredients` DISABLE KEYS */;
INSERT INTO `ingredients` (`id_ingredient`, `name`, `id_ingriedient_type`) VALUES (1,'Egg',5),(2,'Milk',5),(3,'Butter',5),(4,'Gouda',5),(5,'Mozzarella',5),(6,'Cheddar',5),(7,'Feta',5),(8,'Yoghurt',5),(9,'Margarine',5),(10,'Cottage Cheese',5),(11,'Cream',5),(12,'Kefir',5),(13,'Onion',2),(14,'Garlic',2),(15,'Carrot',2),(16,'Pepper',2),(17,'Potato',2),(18,'Tomato',2),(19,'Mushroom',2),(20,'Celery',2),(21,'Leek',2),(22,'Zucchini',2),(23,'Cucumber',2),(24,'Corn',2),(25,'Cabbage',2),(26,'Beetroot',2),(27,'Lettuce',2),(28,'Spinach',2),(29,'Broccoli',2),(30,'Apple',3),(31,'Lemon',3),(32,'Strawberry',3),(33,'Orange',3),(34,'Banana',3),(35,'Raspberry',3),(36,'Plum',3),(37,'Pear',3),(38,'Pineapple',3),(39,'Peach',3),(40,'Lime',3),(41,'Berry',3),(42,'Cherry',3),(43,'Ham',4),(44,'Bacon',4),(45,'Sausage',4),(46,'White Sausage',4),(47,'Chicken Breast',4),(48,'Minced Meat',4),(49,'Pork',4),(50,'Whole Chicken',4),(51,'Chicken Thigh',4),(52,'Ribs',4),(53,'Chicken Wings',4),(54,'Beef',4),(55,'Turkey Breast',4),(56,'Flour',1),(57,'Pasta',1),(58,'Sugar',1),(59,'Rice',1),(60,'Bread',1),(61,'Oil',1),(62,'Corn Flakes',1),(63,'Tortilla',1),(64,'Olive Oil',1),(65,'Potato Flour',1),(66,'Cake Flour',1),(67,'Semolina',1),(68,'Buckwheat Groats',1),(69,'Millet',1),(70,'Beans',2),(71,'Chickpea',2),(72,'Peas',2),(73,'Lentils',2),(74,'Sweet Chilli Sauce',7),(75,'Ketchup',7),(76,'Mayonnaise',7),(77,'Mustard',7),(78,'Soy Sauce',7),(79,'Vinegar',7),(80,'Pesto',7),(81,'Oregano',6),(82,'Cinnamon',6),(83,'Rosemary',6),(84,'Garlic Powder',6),(85,'Thyme',6),(86,'Paprika',6),(87,'Curry',6),(88,'Nutmeg',6),(89,'Cumin',6),(90,'Cauliflower',2),(91,'Red Onion',2),(92,'Sweet Onion',2),(93,'Dill Weed',2);
/*!40000 ALTER TABLE `ingredients` ENABLE KEYS */;
UNLOCK TABLES;
