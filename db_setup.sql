-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: ****    Database: ***
-- ------------------------------------------------------
-- Server version	5.7.22-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `intellecto_app_version`
--

DROP TABLE IF EXISTS `intellecto_app_version`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `intellecto_app_version` (
  `version` varchar(10) NOT NULL,
  `mandatory` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`version`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `intellecto_friends`
--

DROP TABLE IF EXISTS `intellecto_friends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `intellecto_friends` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `friend_id` int(11) NOT NULL,
  `user_winning_efficiency` varchar(5) COLLATE latin1_general_cs NOT NULL DEFAULT '0',
  `friend_winning_efficiency` varchar(5) COLLATE latin1_general_cs NOT NULL DEFAULT '0',
  `user_winning_count` int(5) NOT NULL DEFAULT '0',
  `friend_winning_count` int(5) NOT NULL DEFAULT '0',
  `last_game_time` bigint(16) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `userid_friendid_uk` (`user_id`,`friend_id`),
  KEY `index_userid_friendid` (`user_id`,`friend_id`),
  CONSTRAINT `userid_userid_friends_fk` FOREIGN KEY (`user_id`) REFERENCES `intellecto_users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1 COLLATE=latin1_general_cs;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `intellecto_robot_info`
--

DROP TABLE IF EXISTS `intellecto_robot_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `intellecto_robot_info` (
  `user_id` int(11) NOT NULL,
  `behaviour_count` int(11) NOT NULL DEFAULT '0',
  `trained_on_behaviour_count` int(11) NOT NULL DEFAULT '0',
  `training_count` int(11) NOT NULL DEFAULT '0',
  `is_training_in_progress` int(2) NOT NULL DEFAULT '0',
  `last_training_started_on` bigint(16) NOT NULL DEFAULT '0',
  `last_training_ended_on` bigint(16) NOT NULL DEFAULT '0',
  `queued_on` bigint(16) NOT NULL DEFAULT '0',
  `is_queued` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `intellecto_users`
--

DROP TABLE IF EXISTS `intellecto_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `intellecto_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(5) COLLATE latin1_general_cs NOT NULL,
  `email` varchar(256) COLLATE latin1_general_cs NOT NULL,
  `country` varchar(50) COLLATE latin1_general_cs DEFAULT NULL,
  `phone_number` varchar(50) COLLATE latin1_general_cs DEFAULT NULL,
  `verified` tinyint(2) NOT NULL DEFAULT '0',
  `device` varchar(100) COLLATE latin1_general_cs DEFAULT 'DEFAULT',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `index_username_email` (`username`,`email`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=latin1 COLLATE=latin1_general_cs;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `intellecto_users_behaviour`
--

DROP TABLE IF EXISTS `intellecto_users_behaviour`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `intellecto_users_behaviour` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `game_state` varchar(25) COLLATE latin1_general_cs NOT NULL,
  `user_response` int(5) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `userid_gamestate_uk` (`user_id`,`game_state`),
  KEY `index_userid_behaviour` (`user_id`,`game_state`),
  CONSTRAINT `userid_userid_behaviour_fk` FOREIGN KEY (`user_id`) REFERENCES `intellecto_users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3458 DEFAULT CHARSET=latin1 COLLATE=latin1_general_cs;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `intellecto_users_controls`
--

DROP TABLE IF EXISTS `intellecto_users_controls`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `intellecto_users_controls` (
  `id` int(11) NOT NULL,
  `otp` varchar(6) COLLATE latin1_general_cs NOT NULL,
  `otp_used` tinyint(2) NOT NULL DEFAULT '0',
  `last_otp_sent_at` bigint(16) NOT NULL,
  `otp_sent` int(5) NOT NULL DEFAULT '1',
  `blocked_till` bigint(16) DEFAULT '0',
  `ads_flag` tinyint(2) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  CONSTRAINT `id_userid_controls_fk` FOREIGN KEY (`id`) REFERENCES `intellecto_users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_cs;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `intellecto_users_game_info`
--

DROP TABLE IF EXISTS `intellecto_users_game_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `intellecto_users_game_info` (
  `id` int(11) NOT NULL,
  `user_info` varchar(1000) COLLATE latin1_general_cs DEFAULT NULL,
  `game_info` varchar(1000) CHARACTER SET latin1 DEFAULT NULL,
  `opponent_info` varchar(2000) CHARACTER SET latin1 DEFAULT NULL,
  `opponent_game_info` varchar(5000) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `id_userid_gameinfo_fk` FOREIGN KEY (`id`) REFERENCES `intellecto_users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_cs;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `intellecto_users_notifications`
--

DROP TABLE IF EXISTS `intellecto_users_notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `intellecto_users_notifications` (
  `user_id` int(11) NOT NULL,
  `token` varchar(1000) NOT NULL,
  `is_notification_on` int(2) NOT NULL DEFAULT '1',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `led7_data`
--

DROP TABLE IF EXISTS `led7_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `led7_data` (
  `segment_state` varchar(7) NOT NULL,
  `probability_0` decimal(5,2) NOT NULL DEFAULT '0.00',
  `probability_1` decimal(5,2) NOT NULL DEFAULT '0.00',
  `probability_2` decimal(5,2) NOT NULL DEFAULT '0.00',
  `probability_3` decimal(5,2) NOT NULL DEFAULT '0.00',
  `probability_4` decimal(5,2) NOT NULL DEFAULT '0.00',
  `probability_5` decimal(5,2) NOT NULL DEFAULT '0.00',
  `probability_6` decimal(5,2) NOT NULL DEFAULT '0.00',
  `probability_7` decimal(5,2) NOT NULL DEFAULT '0.00',
  `probability_8` decimal(5,2) NOT NULL DEFAULT '0.00',
  `probability_9` decimal(5,2) NOT NULL DEFAULT '0.00',
  `request_count` int(11) NOT NULL DEFAULT '0',
  `type` varchar(10) NOT NULL DEFAULT 'AFFINITY',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3841 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'yogdb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-15 12:43:51
