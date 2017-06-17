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
-- Database: `usersdb`
--

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
