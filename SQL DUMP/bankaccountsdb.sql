-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 09, 2017 at 04:31 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `bankaccountsdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `bank_accounts`
--

CREATE TABLE IF NOT EXISTS `bank_accounts` (
  `bank_account_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `account_number` varchar(255) DEFAULT NULL,
  `credit_amount` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `bank_account_type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`bank_account_id`),
  KEY `FK_co6mo1bgf4m3ywfm8bq32g6k` (`bank_account_type_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `bank_accounts`
--

INSERT INTO `bank_accounts` (`bank_account_id`, `name`, `account_number`, `credit_amount`, `user_id`, `bank_account_type_id`) VALUES
(1, 'Keno', '123', 5, 1, 1),
(2, 'Anes', '123', 5, 2, NULL),
(3, 'Irfan', '123', 5, 2, NULL),
(4, 'Edis', '123', 5, 2, NULL),
(5, 'Edis', '123', 5, 2, 1),
(6, 'Edis', '123', 5, 2, NULL),
(7, 'Edis', '123', 5, 2, 2),
(8, 'Edis', '123', 5, 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `bank_account_types`
--

CREATE TABLE IF NOT EXISTS `bank_account_types` (
  `bank_account_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bank_account_type_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `bank_account_types`
--

INSERT INTO `bank_account_types` (`bank_account_type_id`, `name`) VALUES
(1, 'Tekuci'),
(2, 'Devizni'),
(3, 'Stedni'),
(4, 'Oroceni');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
