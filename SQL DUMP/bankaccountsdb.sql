-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 20, 2017 at 06:16 AM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bankaccountsdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `bank_accounts`
--

CREATE TABLE `bank_accounts` (
  `bank_account_id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `account_number` varchar(255) DEFAULT NULL,
  `credit_amount` int(11) DEFAULT NULL,
  `currency` varchar(5) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `bank_account_type_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bank_accounts`
--

INSERT INTO `bank_accounts` (`bank_account_id`, `name`, `account_number`, `credit_amount`, `currency`, `user_id`, `bank_account_type_id`) VALUES
(1, 'Kartica za shoping', 'BA123456781', 450, 'BAM', 2, 1),
(2, 'Stedna knjizica', 'BA123456782', 3000, 'BAM', 2, 3),
(3, 'Tekuci racun', 'BA123456783', 1472, 'BAM', 2, 1),
(9, 'Kartica za paypal', 'US1234567890', 100, 'USD', 2, 2),
(10, 'Kartica za shoping', 'BA123456784', 5, 'BAM', 1, 1),
(11, 'Stedna knjizica', 'US1234567891', 5, 'USD', 1, 3),
(12, 'Tekuci racun', 'BA123456785', 5, 'BAM', 1, 1),
(13, 'Kartica za paypal', 'EU1234567893', 100, 'EUR', 1, 2),
(14, 'Zenina kartica', 'BA123456786', 450, 'BAM', 3, 1),
(15, 'Djecija stednja', 'BA123456787', 500, 'BAM', 3, 3),
(16, 'Tekuci racun', 'BA123456788', 1024, 'BAM', 3, 1),
(17, 'Kartica za ljetovanje', 'EU1234567892', 500, 'EUR', 3, 2),
(18, 'Tekuci racun 1', 'BA123456789', 424, 'BAM', 4, 1),
(19, 'Stednja za penziju', 'RS1234567890', 2000, 'RSD', 4, 3),
(20, 'Tekuci racun 2', 'BA123456790', 1024, 'BAM', 4, 1),
(21, 'Kartica za zimovanje', 'EU1234567891', 1200, 'EUR', 4, 2),
(22, 'Tekuci racun 1', 'BA123456791', 350, 'BAM', 5, 1),
(23, 'Stednja za penziju', '2342453334', 2000, 'HRK', 5, 3),
(24, 'Tekuci racun 2', 'BA123456792', 1024, 'BAM', 5, 1),
(25, 'Kartica za zimovanje', 'EU1234567890', 1200, 'EUR', 5, 2);

-- --------------------------------------------------------

--
-- Table structure for table `bank_account_types`
--

CREATE TABLE `bank_account_types` (
  `bank_account_type_id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bank_account_types`
--

INSERT INTO `bank_account_types` (`bank_account_type_id`, `name`) VALUES
(1, 'Tekuci'),
(2, 'Devizni'),
(3, 'Stedni'),
(4, 'Oroceni');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bank_accounts`
--
ALTER TABLE `bank_accounts`
  ADD PRIMARY KEY (`bank_account_id`),
  ADD KEY `FK_co6mo1bgf4m3ywfm8bq32g6k` (`bank_account_type_id`);

--
-- Indexes for table `bank_account_types`
--
ALTER TABLE `bank_account_types`
  ADD PRIMARY KEY (`bank_account_type_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bank_accounts`
--
ALTER TABLE `bank_accounts`
  MODIFY `bank_account_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
--
-- AUTO_INCREMENT for table `bank_account_types`
--
ALTER TABLE `bank_account_types`
  MODIFY `bank_account_type_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
