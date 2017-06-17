-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 17, 2017 at 07:32 PM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `templatesdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `template`
--

CREATE TABLE `template` (
  `template_id` int(11) NOT NULL,
  `amount` double DEFAULT NULL,
  `payment_purpose` varchar(255) DEFAULT NULL,
  `payment_type` varchar(255) DEFAULT NULL,
  `payment_type_id` int(11) DEFAULT NULL,
  `receiver_bank_acc_number` varchar(255) DEFAULT NULL,
  `receiver_name` varchar(255) DEFAULT NULL,
  `sender_bank_acc_number` varchar(255) DEFAULT NULL,
  `sender_name` varchar(255) DEFAULT NULL,
  `template_name` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `template`
--

INSERT INTO `template` (`template_id`, `amount`, `payment_purpose`, `payment_type`, `payment_type_id`, `receiver_bank_acc_number`, `receiver_name`, `sender_bank_acc_number`, `sender_name`, `template_name`, `user_id`) VALUES
(1, 44.79322610387597, 'Racun za elektricnu energiju', 'InternalPayment', 1, '123123123', 'Elektrodistribucija BIH', '321321321', 'Hamo Hamic', 'Template broj: 0', 1),
(2, 35.78426599274277, 'Racun za elektricnu energiju', 'DometicPayment', 1, '123123123', 'Elektrodistribucija BIH', '321321321', 'Hamo Hamic', 'Template broj: 1', 1),
(3, 15.540921727462676, 'Racun za elektricnu energiju', 'InternalPayment', 1, '123123123', 'Elektrodistribucija BIH', '321321321', 'Hamo Hamic', 'Template broj: 2', 1),
(4, 1.510544317465834, 'Racun za elektricnu energiju', 'ForeignPayment', 1, '123123123', 'Elektrodistribucija BIH', '321321321', 'Hamo Hamic', 'Template broj: 3', 1),
(5, 6.792218237691172, 'Racun za elektricnu energiju', 'InternalPayment', 1, '123123123', 'Elektrodistribucija BIH', '321321321', 'Hamo Hamic', 'Template broj: 4', 1),
(6, 55.468684634055165, 'Racun za elektricnu energiju', 'DometicPayment', 1, '123123123', 'Elektrodistribucija BIH', '321321321', 'Hamo Hamic', 'Template broj: 5', 1),
(7, 80.13307240613466, 'Racun za elektricnu energiju', 'InternalPayment', 1, '123123123', 'Elektrodistribucija BIH', '321321321', 'Hamo Hamic', 'Template broj: 6', 1),
(8, 54.76999776005034, 'Racun za elektricnu energiju', 'DometicPayment', 1, '123123123', 'Elektrodistribucija BIH', '321321321', 'Hamo Hamic', 'Template broj: 7', 1),
(9, 17.53105187025412, 'Racun za elektricnu energiju', 'InternalPayment', 1, '123123123', 'Elektrodistribucija BIH', '321321321', 'Hamo Hamic', 'Template broj: 8', 1),
(10, 12.029372739713018, 'Racun za elektricnu energiju', 'ForeignPayment', 1, '123123123', 'Elektrodistribucija BIH', '321321321', 'Hamo Hamic', 'Template broj: 9', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `template`
--
ALTER TABLE `template`
  ADD PRIMARY KEY (`template_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `template`
--
ALTER TABLE `template`
  MODIFY `template_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
