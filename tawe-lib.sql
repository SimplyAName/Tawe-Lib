-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 09, 2018 at 11:57 PM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tawe-lib`
--

-- --------------------------------------------------------

--
-- Table structure for table `book_tbl`
--

CREATE TABLE `book_tbl` (
  `resourceid` int(11) NOT NULL,
  `author` char(30) DEFAULT NULL,
  `publisher` char(30) DEFAULT NULL,
  `genre` char(20) DEFAULT NULL,
  `isbn` int(11) DEFAULT NULL,
  `language` char(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `book_tbl`
--

INSERT INTO `book_tbl` (`resourceid`, `author`, `publisher`, `genre`, `isbn`, `language`) VALUES
(1, 'Leo Tolstoy', 'Penguin Classics', 'Novel', 2147483647, 'Russian'),
(2, 'William Shakespeare', 'Bloomsbury Publishing', 'Play', 2147483647, 'English'),
(3, 'Pride and Prejudice', 'T.Egerton', 'Novel', 2147483647, 'English'),
(4, 'Harper Lee', 'J.B.Lippincott', 'Southern Gothic', 2147483647, 'English'),
(5, 'William Shakespeare', 'Oxford University Press', 'Play', 2147483647, 'English');

-- --------------------------------------------------------

--
-- Table structure for table `copy_tbl`
--

CREATE TABLE `copy_tbl` (
  `copyid` int(11) NOT NULL,
  `resourceid` int(11) DEFAULT NULL,
  `active` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `copy_tbl`
--

INSERT INTO `copy_tbl` (`copyid`, `resourceid`, `active`) VALUES
(1, 1, 1),
(2, 2, 1),
(3, 3, 1),
(4, 3, 1),
(5, 4, 1),
(6, 5, 1),
(7, 5, 1),
(8, 5, 1),
(9, 1, 0),
(10, 6, 1),
(11, 6, 1),
(12, 7, 1),
(13, 8, 1),
(14, 9, 1),
(15, 10, 1);

-- --------------------------------------------------------

--
-- Table structure for table `dvd_tbl`
--

CREATE TABLE `dvd_tbl` (
  `resourceid` int(11) NOT NULL,
  `director` char(30) DEFAULT NULL,
  `runtime` int(11) DEFAULT NULL,
  `subid` int(11) DEFAULT NULL,
  `language` char(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dvd_tbl`
--

INSERT INTO `dvd_tbl` (`resourceid`, `director`, `runtime`, `subid`, `language`) VALUES
(6, 'David Fincher', 127, 2, 'Spanish'),
(7, 'Quentin Tarantino', 154, 1, 'English'),
(8, 'Anthony C. Ferrante', 90, 3, 'French');

-- --------------------------------------------------------

--
-- Table structure for table `fine_tbl`
--

CREATE TABLE `fine_tbl` (
  `histid` int(11) NOT NULL,
  `amount` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `daysoverdue` int(11) DEFAULT NULL,
  `username` char(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fine_tbl`
--

INSERT INTO `fine_tbl` (`histid`, `amount`, `date`, `daysoverdue`, `username`) VALUES
(1, 400, '2018-10-30', 2, 'harumscarum');

-- --------------------------------------------------------

--
-- Table structure for table `historic_tbl`
--

CREATE TABLE `historic_tbl` (
  `histid` int(11) NOT NULL,
  `copyid` int(11) DEFAULT NULL,
  `datefrom` date DEFAULT NULL,
  `datetil` date DEFAULT NULL,
  `username` char(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `historic_tbl`
--

INSERT INTO `historic_tbl` (`histid`, `copyid`, `datefrom`, `datetil`, `username`) VALUES
(1, 7, '2018-10-14', '2018-10-30', 'harumscarum'),
(2, 12, '2018-10-16', '2018-10-20', 'Dodge73Napper'),
(3, 3, '2018-11-12', '2018-11-20', 'Dodge73Napper'),
(4, 15, '2018-11-20', '2018-11-30', 'johndoe123');

-- --------------------------------------------------------

--
-- Table structure for table `language_tbl`
--

CREATE TABLE `language_tbl` (
  `language` char(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `language_tbl`
--

INSERT INTO `language_tbl` (`language`) VALUES
('English'),
('Spanish'),
('French'),
('Russian');

-- --------------------------------------------------------

--
-- Table structure for table `laptop_tbl`
--

CREATE TABLE `laptop_tbl` (
  `resourceid` int(11) NOT NULL,
  `manufacturer` char(30) DEFAULT NULL,
  `model` char(30) DEFAULT NULL,
  `opsystem` char(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `laptop_tbl`
--

INSERT INTO `laptop_tbl` (`resourceid`, `manufacturer`, `model`, `opsystem`) VALUES
(9, 'Lenovo', 'Thinkpad', 'Windows 7'),
(10, 'Acer', 'Chromebook 14', 'Chrome OS');

-- --------------------------------------------------------

--
-- Table structure for table `librarian_tbl`
--

CREATE TABLE `librarian_tbl` (
  `username` char(30) NOT NULL,
  `empdate` date DEFAULT NULL,
  `staffno` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `librarian_tbl`
--

INSERT INTO `librarian_tbl` (`username`, `empdate`, `staffno`) VALUES
('Quatsch', '2007-10-27', 6513);

-- --------------------------------------------------------

--
-- Table structure for table `out_tbl`
--

CREATE TABLE `out_tbl` (
  `outid` int(11) NOT NULL,
  `copyid` int(11) DEFAULT NULL,
  `datefrom` date DEFAULT NULL,
  `duedate` date DEFAULT NULL,
  `username` char(30) DEFAULT NULL,
  `minloanperiod` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `out_tbl`
--

INSERT INTO `out_tbl` (`outid`, `copyid`, `datefrom`, `duedate`, `username`, `minloanperiod`) VALUES
(1, 4, '2018-11-30', NULL, 'Leechman255200', NULL),
(2, 12, '2018-11-30', '2018-12-14', 'johndoe123', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `request_tbl`
--

CREATE TABLE `request_tbl` (
  `reqid` int(11) NOT NULL,
  `resourceid` int(11) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `username` char(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `request_tbl`
--

INSERT INTO `request_tbl` (`reqid`, `resourceid`, `date`, `username`) VALUES
(1, 7, '2018-11-30 11:00:00', 'Dodge73Napper');

-- --------------------------------------------------------

--
-- Table structure for table `reservation_tbl`
--

CREATE TABLE `reservation_tbl` (
  `resid` int(11) NOT NULL,
  `copyid` int(11) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `username` char(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reservation_tbl`
--

INSERT INTO `reservation_tbl` (`resid`, `copyid`, `date`, `username`) VALUES
(1, 10, '2018-12-08 18:20:01', 'Quatsch');

-- --------------------------------------------------------

--
-- Table structure for table `resource_tbl`
--

CREATE TABLE `resource_tbl` (
  `resourceid` int(11) NOT NULL,
  `type` char(6) DEFAULT NULL,
  `title` char(30) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `imagelocation` char(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `resource_tbl`
--

INSERT INTO `resource_tbl` (`resourceid`, `type`, `title`, `year`, `imagelocation`) VALUES
(1, 'book', 'War and Peace', 1869, 'imagesooksWAR_AND_PEACE.png'),
(2, 'book', 'Hamlet', 1602, 'imagesooksHAMLET.png'),
(3, 'book', 'Pride and Prejudice', 1813, 'imagesooksPRIDE_AND_PREJUDICE'),
(4, 'book', 'To Kill a Mockingbird', 1960, 'imagesooksTO_KILL_A_MOCKINGBI'),
(5, 'book', 'Macbeth', 1606, 'imagesooksMACBETH.png'),
(6, 'dvd', 'Se7en', 1995, 'imagesdvdsSE7EN'),
(7, 'dvd', 'Pulp Fiction', 1994, 'imagesdvdsPULP_FICTION'),
(8, 'dvd', 'Sharknado 5: Global Swarming', 2017, 'imagesdvdsSHARNADO_5'),
(9, 'laptop', 'W-1', 2012, 'imageslaptopsW-1.png'),
(10, 'laptop', 'C-1', 2016, 'imageslaptopsW-2.png');

-- --------------------------------------------------------

--
-- Table structure for table `sublanguage_tbl`
--

CREATE TABLE `sublanguage_tbl` (
  `subid` int(11) NOT NULL,
  `sublanguage` char(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sublanguage_tbl`
--

INSERT INTO `sublanguage_tbl` (`subid`, `sublanguage`) VALUES
(1, 'English'),
(2, 'English'),
(2, 'Spanish'),
(3, 'French');

-- --------------------------------------------------------

--
-- Table structure for table `transaction_tbl`
--

CREATE TABLE `transaction_tbl` (
  `transid` int(11) NOT NULL,
  `username` char(30) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaction_tbl`
--

INSERT INTO `transaction_tbl` (`transid`, `username`, `amount`, `date`) VALUES
(1, 'harumscarum', 300, '2018-11-30 14:18:29');

-- --------------------------------------------------------

--
-- Table structure for table `user_tbl`
--

CREATE TABLE `user_tbl` (
  `username` char(30) NOT NULL,
  `firstnames` char(30) DEFAULT NULL,
  `lastname` char(20) DEFAULT NULL,
  `addrline1` char(30) DEFAULT NULL,
  `postcode` char(7) DEFAULT NULL,
  `phone` char(11) DEFAULT NULL,
  `imagelocation` char(255) DEFAULT NULL,
  `balance` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_tbl`
--

INSERT INTO `user_tbl` (`username`, `firstnames`, `lastname`, `addrline1`, `postcode`, `phone`, `imagelocation`, `balance`) VALUES
('default', 'firstnames', 'lastname', 'exampleAddress', 'EXAMPLE', '11111111111', 'main/users/default1.png', 0),
('default3', '3firstnames', 'lastname', 'exampleAddress', 'EXAMPLE', '11111111110', 'main/users/default1.png', 0),
('Dodge73Napper', 'Libbie', 'Connolly', '45 Main Street', 'MA43UI', '1000000098', 'main/users/default1.png', 0),
('harumscarum', 'Jayden-James', 'Bautista', '123 Leeds Road', 'DB341ER', '4', 'main/users/default1.png', 200),
('johndoe123', 'John', 'Doe', '123 Big Street', 'AZ12ABC', '45445', 'main/users/default1.png', 100),
('Leechman255200', 'Eric', 'Villa', 'Chest', 'CH244AQ', '2', 'main/users/default1.png', 0),
('Quatsch', 'Nuala', 'Head', '1 Cardiff street', 'SA19CF', '786858', 'main/users/default1.png', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `book_tbl`
--
ALTER TABLE `book_tbl`
  ADD PRIMARY KEY (`resourceid`),
  ADD KEY `language` (`language`);

--
-- Indexes for table `copy_tbl`
--
ALTER TABLE `copy_tbl`
  ADD PRIMARY KEY (`copyid`);

--
-- Indexes for table `historic_tbl`
--
ALTER TABLE `historic_tbl`
  ADD PRIMARY KEY (`histid`);

--
-- Indexes for table `out_tbl`
--
ALTER TABLE `out_tbl`
  ADD PRIMARY KEY (`outid`);

--
-- Indexes for table `request_tbl`
--
ALTER TABLE `request_tbl`
  ADD PRIMARY KEY (`reqid`);

--
-- Indexes for table `reservation_tbl`
--
ALTER TABLE `reservation_tbl`
  ADD PRIMARY KEY (`resid`);

--
-- Indexes for table `resource_tbl`
--
ALTER TABLE `resource_tbl`
  ADD PRIMARY KEY (`resourceid`);

--
-- Indexes for table `transaction_tbl`
--
ALTER TABLE `transaction_tbl`
  ADD PRIMARY KEY (`transid`);

--
-- Indexes for table `user_tbl`
--
ALTER TABLE `user_tbl`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `copy_tbl`
--
ALTER TABLE `copy_tbl`
  MODIFY `copyid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `historic_tbl`
--
ALTER TABLE `historic_tbl`
  MODIFY `histid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `out_tbl`
--
ALTER TABLE `out_tbl`
  MODIFY `outid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `request_tbl`
--
ALTER TABLE `request_tbl`
  MODIFY `reqid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `reservation_tbl`
--
ALTER TABLE `reservation_tbl`
  MODIFY `resid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `resource_tbl`
--
ALTER TABLE `resource_tbl`
  MODIFY `resourceid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `transaction_tbl`
--
ALTER TABLE `transaction_tbl`
  MODIFY `transid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
