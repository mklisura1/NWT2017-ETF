-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 17, 2017 at 07:33 PM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `paymentsdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `payments`
--

CREATE TABLE `payments` (
  `payment_id` bigint(20) NOT NULL,
  `amount` double NOT NULL,
  `payment_date` datetime NOT NULL,
  `payment_purpose` varchar(255) NOT NULL,
  `receiver_bank_acc_num` varchar(255) NOT NULL,
  `receiver_name` varchar(255) NOT NULL,
  `sneder_bank_acc_num` varchar(255) NOT NULL,
  `sender_name` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `type_description` varchar(255) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `payment_type` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `payments`
--

INSERT INTO `payments` (`payment_id`, `amount`, `payment_date`, `payment_purpose`, `receiver_bank_acc_num`, `receiver_name`, `sneder_bank_acc_num`, `sender_name`, `status`, `type_description`, `user_id`, `payment_type`) VALUES
(1, 12, '2017-06-10 17:44:47', 'Internal transfer', 'BA121234330923', 'Hamo Hamic', 'BA121234330923', 'Hamo Hamic', 'Signed', 'InternalPayment', 1, 3),
(3, 321, '2017-06-10 18:26:34', 'Internal transfer', 'BA121234330923', 'Hamo Hamic', 'BA121234330923', 'Hamo Hamic', 'Signed', 'InternalPayment', 1, 3),
(4, 123, '2017-06-10 18:27:40', 'Internal transfer', 'BA121234330923', 'Hamo Hamic', 'BA121234330923', 'Hamo Hamic', 'Waiting', 'InternalPayment', 1, 3),
(5, 123, '2017-06-10 18:28:28', 'Internal transfer', 'BA121234330923', 'Hamo Hamic', 'BA121234330923', 'Hamo Hamic', 'Waiting', 'InternalPayment', 1, 3),
(6, 11, '2017-06-10 18:28:50', 'Internal transfer', 'BA121234330923', 'Hamo Hamic', 'BA121234330923', 'Hamo Hamic', 'Waiting', 'InternalPayment', 1, 3),
(7, 123, '2017-06-10 18:29:32', 'Internal transfer', 'BA121234330923', 'Hamo Hamic', 'BA121234330923', 'Hamo Hamic', 'Waiting', 'InternalPayment', 1, 3),
(8, 123, '2017-06-10 18:41:59', 'Internal transfer', 'BA121234330923', 'Hamo Hamic', 'SI1231231230923', 'Hame u piketa', 'Waiting', 'InternalPayment', 1, 3),
(9, 23, '2017-06-10 18:43:52', 'Internal transfer', 'BA121234330923', 'Hamo Hamic', 'BA121234330923', 'Hamo Hamic', 'Waiting', 'InternalPayment', 1, 3),
(10, 13, '2017-06-10 18:46:38', 'Internal transfer', 'BA121234330923', 'Hamo Hamic', 'BA121234330923', 'Hamo Hamic', 'Waiting', 'InternalPayment', 1, 3),
(11, 11, '2017-06-10 18:48:31', 'Internal transfer', 'BA121234330923', 'Hamo Hamic', 'BA121234330923', 'Hamo Hamic', 'Waiting', 'InternalPayment', 1, 3),
(12, 21, '2017-06-10 18:50:59', 'Internal transfer', 'BA121234330923', 'Hamo Hamic', 'BA121234330923', 'Hamo Hamic', 'Waiting', 'InternalPayment', 1, 3),
(13, 123, '2017-06-10 18:51:40', 'Internal transfer', 'BA121234330923', 'Hamo Hamic', 'BA121234330923', 'Hamo Hamic', 'Waiting', 'InternalPayment', 1, 3),
(14, 123, '2017-06-10 18:55:31', 'Internal transfer', 'BA121234330923', 'Hamo Hamic', 'BA121234330923', 'Hamo Hamic', 'Waiting', 'InternalPayment', 1, 3),
(15, 123, '2017-06-10 18:56:46', 'Internal transfer', 'BA121234330923', 'Hamo Hamic', 'BA121234330923', 'Hamo Hamic', 'Waiting', 'InternalPayment', 1, 3),
(16, 13, '2017-06-10 19:02:47', 'asda', '123', 'asdad', 'BA121234330923', 'Hamo Hamic', 'Waiting', 'DomesticPayment', 1, 1),
(17, 1111, '2017-06-10 19:03:10', 'bbbb', '22222', 'aaaaa', 'BA121234330923', 'Hamo Hamic', 'Waiting', 'DomesticPayment', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `payment_types`
--

CREATE TABLE `payment_types` (
  `payment_type_id` bigint(20) NOT NULL,
  `payment_type_name` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `payment_types`
--

INSERT INTO `payment_types` (`payment_type_id`, `payment_type_name`) VALUES
(1, 'DomesticPayment'),
(2, 'ForeignPayment'),
(3, 'InternalPayment');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `payments`
--
ALTER TABLE `payments`
  ADD PRIMARY KEY (`payment_id`),
  ADD KEY `FKhb0kio5jsd80fxbq9y0w89eo9` (`payment_type`);

--
-- Indexes for table `payment_types`
--
ALTER TABLE `payment_types`
  ADD PRIMARY KEY (`payment_type_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `payments`
--
ALTER TABLE `payments`
  MODIFY `payment_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT for table `payment_types`
--
ALTER TABLE `payment_types`
  MODIFY `payment_type_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
