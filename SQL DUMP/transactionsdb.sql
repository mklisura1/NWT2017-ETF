-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 18, 2017 at 05:47 AM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `transactionsdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE IF NOT EXISTS `transaction` (
  `transaction_id` int(11) NOT NULL AUTO_INCREMENT,
  `bank_account_receiver_id` int(11) DEFAULT NULL,
  `bank_account_sender_id` int(11) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `payment_id` int(11) DEFAULT NULL,
  `receiver_id` int(11) DEFAULT NULL,
  `sender_id` int(11) DEFAULT NULL,
  `transaction_status_id` int(11) DEFAULT NULL,
  `transaction_type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `FKdb3nt6iipyx0tqg3synr73fpu` (`transaction_status_id`),
  KEY `FKnl0vpl01y6vu03hkpi4xupugo` (`transaction_type_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`transaction_id`, `bank_account_receiver_id`, `bank_account_sender_id`, `date`, `payment_id`, `receiver_id`, `sender_id`, `transaction_status_id`, `transaction_type_id`) VALUES
(1, 10, 11, '2017-06-14 00:00:00', 1, 1, 1, 1, 1),
(2, 1, 11, '2017-06-12 00:00:00', 2, 2, 1, 1, 2),
(3, 1, 10, '2017-06-30 00:00:00', 2, 2, 1, 1, 2),
(4, 10, 12, '2017-06-01 00:00:00', 2, 1, 1, 1, 2),
(5, 10, 13, '2017-06-17 00:00:00', 2, 1, 1, 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `transaction_status`
--

CREATE TABLE IF NOT EXISTS `transaction_status` (
  `transaction_status_id` int(11) NOT NULL AUTO_INCREMENT,
  `transaction_status_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`transaction_status_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `transaction_status`
--

INSERT INTO `transaction_status` (`transaction_status_id`, `transaction_status_name`) VALUES
(1, 'Finished');

-- --------------------------------------------------------

--
-- Table structure for table `transaction_type`
--

CREATE TABLE IF NOT EXISTS `transaction_type` (
  `transaction_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `transaction_type_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`transaction_type_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `transaction_type`
--

INSERT INTO `transaction_type` (`transaction_type_id`, `transaction_type_name`) VALUES
(1, 'Kupovina'),
(2, 'Plata');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `FKnl0vpl01y6vu03hkpi4xupugo` FOREIGN KEY (`transaction_type_id`) REFERENCES `transaction_type` (`transaction_type_id`),
  ADD CONSTRAINT `FKdb3nt6iipyx0tqg3synr73fpu` FOREIGN KEY (`transaction_status_id`) REFERENCES `transaction_status` (`transaction_status_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
