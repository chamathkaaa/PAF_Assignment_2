-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 05, 2020 at 08:17 AM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `healthcaredb`
--

-- --------------------------------------------------------

--
-- Table structure for table `doctors`
--

CREATE TABLE `doctors` (
  `DoctorID` int(5) NOT NULL,
  `DoctorName` varchar(100) NOT NULL,
  `NIC` varchar(15) NOT NULL,
  `Address` varchar(500) NOT NULL,
  `MobileNo` int(10) NOT NULL,
  `Email` varchar(200) NOT NULL,
  `Specialization` varchar(250) NOT NULL,
  `HospitalName` varchar(200) NOT NULL,
  `DepartmentName` varchar(200) NOT NULL,
  `Status` varchar(15) NOT NULL DEFAULT 'Accepted'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `doctors`
--

INSERT INTO `doctors` (`DoctorID`, `DoctorName`, `NIC`, `Address`, `MobileNo`, `Email`, `Specialization`, `HospitalName`, `DepartmentName`, `Status`) VALUES
(1, 'Ariana Grande', '948575962V', 'Malabe', 774589622, 'araiana@gmail.com', 'Eye', 'Nawaloka Hospital', 'Eye', 'Accepted'),
(2, 'Taylor Swift', '894512635V', 'Colombo', 774518544, 'taytay@gmail.com', 'Eye', 'Asiri Hospital', 'Eye', 'Accepted'),
(3, 'Harry Styles', '961254129V', 'Colombo 2', 771256322, 'harry@gmail.com', 'ENT', 'Durdans Hospital', 'ENT', 'Accepted'),
(5, 'Chandler Bing', '604712599V', 'Kaduwela', 724589651, 'chandler@gmail.com', 'Eye', 'Golden Key Eye Hospital', 'Eye', 'Accepted'),
(6, 'Chainsmokers', '884517525V', 'Kaduwala', 774582144, 'chan@gmail.com', 'Eye', 'Nawaloka Hospital', 'Eye', 'Accepted'),
(7, 'Chainsmokers1', '884517525V', 'Kaduwala', 774582144, 'chan@gmail.com', 'Eye', 'Nawaloka Hospital', 'Eye', 'Accepted'),
(8, 'Chainsmokers2', '884517525V', 'Kaduwala', 774582144, 'chan@gmail.com', 'Eye', 'Nawaloka Hospital', 'Eye', 'Accepted'),
(9, 'Chainsmokers3', '884517525V', 'Kaduwala', 774582144, 'chan@gmail.com', 'Eye', 'Nawaloka Hospital', 'Eye', 'Accepted'),
(14, 'Jimmy Fallon', '785695236V', 'Colombo', 771111111, 'jimmy@gmail.com', 'ENT', 'Asiri Hospital', 'ENT', 'Accepted'),
(16, 'Ellen DeGeneres', '598745632V', 'Colombo', 1234, 'ellen@gmail.com', 'Eye', 'Asiri Hospital', 'Eye', 'Accepted'),
(17, 'Trevor Noah', '895647523V', 'Gampaha', 774589655, 'trevor@gmail.com', 'Dental', 'Golden Key Hospital', 'Dental', 'Accepted');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `doctors`
--
ALTER TABLE `doctors`
  ADD PRIMARY KEY (`DoctorID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `doctors`
--
ALTER TABLE `doctors`
  MODIFY `DoctorID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
