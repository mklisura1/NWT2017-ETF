-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 20, 2017 at 09:08 AM
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
CREATE DATABASE IF NOT EXISTS `bankaccountsdb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `bankaccountsdb`;

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
(1, 'Kartica za shoping', 'BA123456781', 495, 'BAM', 2, 1),
(2, 'Stedna knjizica', 'BA123456782', 3005, 'BAM', 2, 3),
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
(19, 'Stednja za penziju', 'RS1234567890', 1950, 'RSD', 4, 3),
(20, 'Tekuci racun 2', 'BA123456790', 1024, 'BAM', 4, 1),
(21, 'Kartica za zimovanje', 'EU1234567891', 1200, 'EUR', 4, 2),
(22, 'Tekuci racun 1', 'BA123456791', 350, 'BAM', 5, 1),
(23, 'Stednja za penziju', '2342453334', 2000, 'HRK', 5, 3),
(24, 'Tekuci racun 2', 'BA123456792', 1024, 'BAM', 5, 1),
(25, 'Kartica za zimovanje', 'EU1234567890', 1200, 'EUR', 5, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bank_account_types`
--
ALTER TABLE `bank_account_types`
  ADD PRIMARY KEY (`bank_account_type_id`);

--
-- Indexes for table `bank_accounts`
--
ALTER TABLE `bank_accounts`
  ADD PRIMARY KEY (`bank_account_id`),
  ADD KEY `FK_co6mo1bgf4m3ywfm8bq32g6k` (`bank_account_type_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bank_account_types`
--
ALTER TABLE `bank_account_types`
  MODIFY `bank_account_type_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `bank_accounts`
--
ALTER TABLE `bank_accounts`
  MODIFY `bank_account_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;--
-- Database: `paymentsdb`
--
CREATE DATABASE IF NOT EXISTS `paymentsdb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `paymentsdb`;

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
  `payment_type` varchar(255) NOT NULL,
  `type_description` varchar(255) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `status` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `payments`
--

INSERT INTO `payments` (`payment_id`, `amount`, `payment_date`, `payment_purpose`, `receiver_bank_acc_num`, `receiver_name`, `sneder_bank_acc_num`, `sender_name`, `payment_type`, `type_description`, `user_id`, `status`) VALUES
(72, 50, '2017-06-20 10:48:05', 'Transfer novca ', 'BA123456785', 'Haris Spahic', 'BA123456786', 'Zenina kartica', '1', 'DomesticPayment', 3, 'Waiting'),
(71, 20, '2017-06-20 10:45:54', 'Evo malo para', 'EU1234567892', 'Kenan Mahmutovic', 'US1234567890', 'Kartica za paypal', '2', 'ForeignPayment', 2, 'Waiting'),
(70, 24, '2017-06-20 10:44:42', 'Transfer novca', 'BA123456789', 'Anes Luckin', 'BA123456781', 'Kartica za shoping', '1', 'DomesticPayment', 2, 'Signed'),
(69, 50, '2017-06-20 10:38:06', 'Transfer', 'BA123456781', 'Haris Spahic', 'RS1234567890', 'Stednja za penziju', '2', 'ForeignPayment', 4, 'Signed'),
(68, 50, '2017-06-20 10:35:26', 'Transfer', 'BA123456781', 'Haris Spahic', 'BA123456789', 'Tekuci racun 1', '1', 'DomesticPayment', 4, 'Waiting'),
(67, 50, '2017-06-20 10:32:45', 'Transfer', 'BA123456781', 'Haris Spahic', 'BA123456789', 'Tekuci racun 1', '1', 'DomesticPayment', 4, 'Waiting'),
(66, 50, '2017-06-20 10:30:59', 'Transfer', 'BA123456781', 'Haris S', 'BA123456789', 'Tekuci racun 1', '1', 'DomesticPayment', 4, 'Waiting'),
(65, 50, '2017-06-20 10:28:52', 'Transfer novca', 'BA123456781', 'Haris S', 'BA123456789', 'Tekuci racun 1', '1', 'DomesticPayment', 4, 'Waiting'),
(64, 50, '2017-06-20 10:24:18', 'Transfer novca', 'BA123456781', 'Haris Spahic', 'BA123456789', 'Tekuci racun 1', '1', 'DomesticPayment', 4, 'Waiting'),
(63, 50, '2017-06-20 10:21:36', 'Transfer novca ', 'BA123456781', 'Haris Spahic', 'BA123456789', 'Tekuci racun 1', '1', 'DomesticPayment', 4, 'Waiting'),
(62, 24, '2017-06-20 10:07:57', 'Transfer za Haris Spahic', 'BA123456781', 'Haris Spahic', 'BA123456789', 'Tekuci racun 1', '1', 'DomesticPayment', 4, 'Signed'),
(61, 12, '2017-06-10 17:36:22', 'Internal transfer', 'BA123456789', 'Anes Luckin', 'BA123456783', 'Haris Spahic', '3', 'InternalPayment', 1, 'Waiting'),
(73, 5, '2017-06-20 11:07:14', 'Internal transfer', 'BA123456782', 'Stedna knjizica', 'BA123456781', 'Kartica za shoping', '3', 'InternalPayment', 2, 'Signed');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `payment_types`
--
ALTER TABLE `payment_types`
  ADD PRIMARY KEY (`payment_type_id`);

--
-- Indexes for table `payments`
--
ALTER TABLE `payments`
  ADD PRIMARY KEY (`payment_id`),
  ADD KEY `FKhb0kio5jsd80fxbq9y0w89eo9` (`payment_type`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `payment_types`
--
ALTER TABLE `payment_types`
  MODIFY `payment_type_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=118;
--
-- AUTO_INCREMENT for table `payments`
--
ALTER TABLE `payments`
  MODIFY `payment_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=74;--
-- Database: `proba`
--
CREATE DATABASE IF NOT EXISTS `proba` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `proba`;
--
-- Database: `templatesdb`
--
CREATE DATABASE IF NOT EXISTS `templatesdb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `templatesdb`;

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
(10, 12.029372739713018, 'Racun za elektricnu energiju', 'ForeignPayment', 1, '123123123', 'Elektrodistribucija BIH', '321321321', 'Hamo Hamic', 'Template broj: 9', 1),
(22, 50, 'Transfer novca ', 'DomesticPayment', NULL, 'BA123456785', 'Haris Spahic', 'BA123456786', 'Zenina kartica', 'Template za Harisa', 3),
(21, 20, 'Evo malo para', 'ForeignPayment', NULL, 'EU1234567892', 'Kenan Mahmutovic', 'US1234567890', 'Kartica za paypal', 'Template za Kenu', 2),
(19, 50, 'Transfer', 'ForeignPayment', NULL, 'BA123456781', 'Haris Spahic', 'BA123456789', 'Tekuci racun 1', 'Template za harisa', 4),
(20, 24, 'Transfer novca', 'DomesticPayment', NULL, 'BA123456789', 'Anes Luckin', 'BA123456781', 'Kartica za shoping', 'Transfer za Anesa', 2),
(17, 50, 'Transfer', 'ForeignPayment', NULL, 'BA123456781', 'Haris Spahic', 'BA123456789', 'Tekuci racun 1', 'Template za Harisa', NULL);

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
  MODIFY `template_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;--
-- Database: `transactionsdb`
--
CREATE DATABASE IF NOT EXISTS `transactionsdb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `transactionsdb`;

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `transaction_id` int(11) NOT NULL,
  `bank_account_receiver_id` int(11) DEFAULT NULL,
  `bank_account_sender_id` int(11) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `payment_id` int(11) DEFAULT NULL,
  `receiver_id` int(11) DEFAULT NULL,
  `sender_id` int(11) DEFAULT NULL,
  `transaction_status_id` int(11) DEFAULT NULL,
  `transaction_type_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`transaction_id`, `bank_account_receiver_id`, `bank_account_sender_id`, `date`, `payment_id`, `receiver_id`, `sender_id`, `transaction_status_id`, `transaction_type_id`) VALUES
(1, 10, 1, '2017-06-14 00:00:00', 1, 2, 3, 1, 1),
(2, 1, 14, '2017-06-12 00:00:00', 2, 3, 4, 1, 2),
(3, 13, 3, '2017-06-30 00:00:00', 2, 3, 2, 1, 2),
(4, 22, 19, '2017-06-01 00:00:00', 2, 2, 4, 1, 2),
(5, 25, 13, '2017-06-17 00:00:00', 2, 4, 2, 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `transaction_status`
--

CREATE TABLE `transaction_status` (
  `transaction_status_id` int(11) NOT NULL,
  `transaction_status_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaction_status`
--

INSERT INTO `transaction_status` (`transaction_status_id`, `transaction_status_name`) VALUES
(1, 'Finished');

-- --------------------------------------------------------

--
-- Table structure for table `transaction_type`
--

CREATE TABLE `transaction_type` (
  `transaction_type_id` int(11) NOT NULL,
  `transaction_type_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaction_type`
--

INSERT INTO `transaction_type` (`transaction_type_id`, `transaction_type_name`) VALUES
(1, 'Kupovina'),
(2, 'Plata');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`transaction_id`),
  ADD KEY `FKdb3nt6iipyx0tqg3synr73fpu` (`transaction_status_id`),
  ADD KEY `FKnl0vpl01y6vu03hkpi4xupugo` (`transaction_type_id`);

--
-- Indexes for table `transaction_status`
--
ALTER TABLE `transaction_status`
  ADD PRIMARY KEY (`transaction_status_id`);

--
-- Indexes for table `transaction_type`
--
ALTER TABLE `transaction_type`
  ADD PRIMARY KEY (`transaction_type_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `transaction_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `transaction_status`
--
ALTER TABLE `transaction_status`
  MODIFY `transaction_status_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `transaction_type`
--
ALTER TABLE `transaction_type`
  MODIFY `transaction_type_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `FKdb3nt6iipyx0tqg3synr73fpu` FOREIGN KEY (`transaction_status_id`) REFERENCES `transaction_status` (`transaction_status_id`),
  ADD CONSTRAINT `FKnl0vpl01y6vu03hkpi4xupugo` FOREIGN KEY (`transaction_type_id`) REFERENCES `transaction_type` (`transaction_type_id`);
--
-- Database: `usersdb`
--
CREATE DATABASE IF NOT EXISTS `usersdb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `usersdb`;

-- --------------------------------------------------------

--
-- Table structure for table `app_user`
--

CREATE TABLE `app_user` (
  `id` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `birth_date` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `jmbg` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `user_picture_id` int(11) DEFAULT NULL,
  `user_type_id` int(11) DEFAULT NULL,
  `mobile` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `app_user`
--

INSERT INTO `app_user` (`id`, `address`, `birth_date`, `email`, `first_name`, `jmbg`, `last_name`, `password`, `username`, `user_picture_id`, `user_type_id`, `mobile`) VALUES
(1, 'Trg Solidarnosti', '2004-04-04 00:00:00', 'admin@gmail.com', 'Admin', '123123123', 'Adminovic', '$2a$10$sIkZ3iaWua2F04.0iuRZi.Dfk7VYwKQhBQBr2MK5bl64.TwYLq1hu', 'admin', 1, 1, '0611171'),
(2, 'Trg Solidarnosti', '2004-04-04 00:00:00', 'spahaa13@gmail.com', 'Haris', '123123123', 'Spahic', '$2a$10$4/OK2poVIdIbUU8RWOh7DeqjCCZRgias2vUPRV.1Se3FJuiUwbKVm', 'harisspahic', 1, 1, '061123123'),
(3, 'Otoka oko kruznog 12', '2017-06-17 20:13:35', 'kmahmutovic@gmail.com', 'Kenan', '1232131231', 'Mahmutovic', '$2a$10$DVk0.FfSY.n99Bw/tmx6eeAHbg3U/Q.tJSrW/9pfkIrFM1/TbSTfm', 'kenanmahmutovic', NULL, NULL, '0612313123'),
(4, 'Ilidza bb', '2017-06-17 18:51:02', 'anes@luckin.com', 'Anes', '2312312123', 'Luckin', '$2a$10$MMeXL9BDgIQBuMArPDht7O2xiE/RoTS28GCu.Bq69dbuOE4N6mJMi', 'anesluckin', 1, 1, '066123123'),
(5, 'Stari grad bb', '2017-06-17 20:14:12', 'imehanovic@gmail.com', 'Irfan', '43123123123', 'Mehanovic', '$2a$10$otflz02tALE5KedLvQPsxOfpVTeG1xdnzalFM/w5nSeTAYkyKO7CW', 'irfanmehanovic', NULL, NULL, '066123123123'),
(6, 'Malta 12/a', '2017-06-17 00:00:00', 'mklisura@gmail.com', 'Mesud', '12312312312', 'Klisura', '$2a$10$UbP.rzJ8.mItJe46aqBQPudN415Q14JEzIX7OvJQef66EQ8W1Fhdi', 'mesudklisura', NULL, NULL, '0612312312');

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
  `payment_type` varchar(255) NOT NULL,
  `type_description` varchar(255) DEFAULT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `birth_date` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `jmbg` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_password_hash` varchar(255) DEFAULT NULL,
  `user_password_salt` varchar(255) DEFAULT NULL,
  `user_picture_id` int(11) DEFAULT NULL,
  `user_type_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user_picture`
--

CREATE TABLE `user_picture` (
  `user_picture_id` int(11) NOT NULL,
  `server_location` varchar(255) DEFAULT NULL,
  `upload_date` datetime DEFAULT NULL,
  `using_this_picture` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_picture`
--

INSERT INTO `user_picture` (`user_picture_id`, `server_location`, `upload_date`, `using_this_picture`) VALUES
(1, 'sd', '2005-04-04 00:00:00', NULL),
(2, 'sd', '2005-04-04 00:00:00', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

CREATE TABLE `user_role` (
  `role` varchar(255) NOT NULL,
  `app_user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_role`
--

INSERT INTO `user_role` (`role`, `app_user_id`) VALUES
('ADMIN', 1),
('USER', 2),
('USER', 3),
('USER', 4),
('USER', 5),
('USER', 6);

-- --------------------------------------------------------

--
-- Table structure for table `user_type`
--

CREATE TABLE `user_type` (
  `user_type_id` int(11) NOT NULL,
  `user_type_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_type`
--

INSERT INTO `user_type` (`user_type_id`, `user_type_name`) VALUES
(1, 'asd'),
(2, 'asd');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `app_user`
--
ALTER TABLE `app_user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKlroir20h7kqn8m9l5m6vwjbu3` (`user_picture_id`),
  ADD KEY `FKnwd188l5juq4llwmk5fb68ljg` (`user_type_id`);

--
-- Indexes for table `payments`
--
ALTER TABLE `payments`
  ADD PRIMARY KEY (`payment_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`),
  ADD KEY `FK_oskpntsladvhhed9jnfot54j4` (`user_picture_id`),
  ADD KEY `FK_kvbni5edna2g6ytxj3mhssg2h` (`user_type_id`);

--
-- Indexes for table `user_picture`
--
ALTER TABLE `user_picture`
  ADD PRIMARY KEY (`user_picture_id`);

--
-- Indexes for table `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`role`,`app_user_id`),
  ADD KEY `FKj16wg2x08hwytvgys4y9idf4b` (`app_user_id`);

--
-- Indexes for table `user_type`
--
ALTER TABLE `user_type`
  ADD PRIMARY KEY (`user_type_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `app_user`
--
ALTER TABLE `app_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `payments`
--
ALTER TABLE `payments`
  MODIFY `payment_id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `user_picture`
--
ALTER TABLE `user_picture`
  MODIFY `user_picture_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `user_type`
--
ALTER TABLE `user_type`
  MODIFY `user_type_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `app_user`
--
ALTER TABLE `app_user`
  ADD CONSTRAINT `FKlroir20h7kqn8m9l5m6vwjbu3` FOREIGN KEY (`user_picture_id`) REFERENCES `user_picture` (`user_picture_id`),
  ADD CONSTRAINT `FKnwd188l5juq4llwmk5fb68ljg` FOREIGN KEY (`user_type_id`) REFERENCES `user_type` (`user_type_id`);

--
-- Constraints for table `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FKj16wg2x08hwytvgys4y9idf4b` FOREIGN KEY (`app_user_id`) REFERENCES `app_user` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
