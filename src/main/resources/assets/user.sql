-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 28, 2024 at 09:51 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.1.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pidevdesktop`
--

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `numtel` int(11) NOT NULL,
  `role` varchar(255) NOT NULL DEFAULT 'User',
  `image` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `nom`, `prenom`, `email`, `password`, `numtel`, `role`, `image`) VALUES
(31, 'Ala', 'Moussa', 'moussa.ala@esprit.tn', '123', 58409876, 'Admin', 'src\\main\\resources\\assets\\uploads\\86752492-5bad-4f6c-a597-bef4727e3c5d_profileAla.png'),
(32, 'Ala', 'Moussa', 'moussa.ala@esprit.tn', '123', 58409876, 'Admin', 'src\\main\\resources\\assets\\uploads\\86752492-5bad-4f6c-a597-bef4727e3c5d_profileAla.png'),
(33, 'Ala', 'Moussa', 'moussa.ala@esprit.tn', '123', 58409876, 'Admin', 'src\\main\\resources\\assets\\uploads\\86752492-5bad-4f6c-a597-bef4727e3c5d_profileAla.png'),
(34, 'Ala', 'Moussa', 'moussa.ala@esprit.tn', '123', 58409876, 'Admin', 'src\\main\\resources\\assets\\uploads\\86752492-5bad-4f6c-a597-bef4727e3c5d_profileAla.png'),
(35, 'Ala', 'Moussa', 'moussa.ala@esprit.tn', '123', 58409876, 'Admin', 'src\\main\\resources\\assets\\uploads\\86752492-5bad-4f6c-a597-bef4727e3c5d_profileAla.png'),
(36, 'Ala', 'Moussa', 'moussa.ala@esprit.tn', '123', 58409876, 'Admin', 'src\\main\\resources\\assets\\uploads\\86752492-5bad-4f6c-a597-bef4727e3c5d_profileAla.png'),
(37, 'Ala', 'Moussa', 'moussa.ala@esprit.tn', '123', 58409876, 'Admin', 'src\\main\\resources\\assets\\uploads\\86752492-5bad-4f6c-a597-bef4727e3c5d_profileAla.png'),
(38, 'Ala', 'Moussa', 'moussa.ala@esprit.tn', '123', 58409876, 'Admin', 'src\\main\\resources\\assets\\uploads\\86752492-5bad-4f6c-a597-bef4727e3c5d_profileAla.png'),
(39, 'Ala', 'Moussa', 'moussa.ala@esprit.tn', '123', 58409876, 'Admin', 'src\\main\\resources\\assets\\uploads\\86752492-5bad-4f6c-a597-bef4727e3c5d_profileAla.png'),
(40, 'Ala', 'Moussa', 'moussa.ala@esprit.tn', '123', 58409876, 'Admin', 'src\\main\\resources\\assets\\uploads\\86752492-5bad-4f6c-a597-bef4727e3c5d_profileAla.png'),
(41, 'Ala', 'Moussa', 'moussa.ala@esprit.tn', '123', 58409876, 'Admin', 'src\\main\\resources\\assets\\uploads\\86752492-5bad-4f6c-a597-bef4727e3c5d_profileAla.png'),
(42, 'Ala', 'Moussa', 'moussa.ala@esprit.tn', '123', 58409876, 'Admin', 'src\\main\\resources\\assets\\uploads\\86752492-5bad-4f6c-a597-bef4727e3c5d_profileAla.png'),
(43, 'Ala', 'Moussa', 'moussa.ala@esprit.tn', '123', 58409876, 'Admin', 'src\\main\\resources\\assets\\uploads\\86752492-5bad-4f6c-a597-bef4727e3c5d_profileAla.png'),
(44, 'Ala', 'Moussa', 'moussa.ala@esprit.tn', '123', 58409876, 'Admin', 'src\\main\\resources\\assets\\uploads\\86752492-5bad-4f6c-a597-bef4727e3c5d_profileAla.png'),
(45, 'Ala', 'Moussa', 'moussa.ala@esprit.tn', '123', 58409876, 'Admin', 'src\\main\\resources\\assets\\uploads\\86752492-5bad-4f6c-a597-bef4727e3c5d_profileAla.png'),
(46, 'Ala', 'Moussa', 'moussa.ala@esprit.tn', '123', 58409876, 'Admin', 'src\\main\\resources\\assets\\uploads\\86752492-5bad-4f6c-a597-bef4727e3c5d_profileAla.png'),
(47, 'Ala', 'Moussa', 'moussa.ala@esprit.tn', '123', 58409876, 'Admin', 'src\\main\\resources\\assets\\uploads\\86752492-5bad-4f6c-a597-bef4727e3c5d_profileAla.png'),
(48, 'Ala', 'Moussa', 'moussa.ala@esprit.tn', '123', 58409876, 'Admin', 'src\\main\\resources\\assets\\uploads\\86752492-5bad-4f6c-a597-bef4727e3c5d_profileAla.png'),
(49, 'Ala', 'Moussa', 'moussa.ala@esprit.tn', '123', 58409876, 'Admin', 'src\\main\\resources\\assets\\uploads\\86752492-5bad-4f6c-a597-bef4727e3c5d_profileAla.png'),
(50, 'Ala', 'Moussa', 'moussa.ala@esprit.tn', '123', 58409876, 'Admin', 'src\\main\\resources\\assets\\uploads\\86752492-5bad-4f6c-a597-bef4727e3c5d_profileAla.png'),
(51, 'Ala', 'Moussa', 'moussa.ala@esprit.tn', '123', 58409876, 'Admin', 'src\\main\\resources\\assets\\uploads\\86752492-5bad-4f6c-a597-bef4727e3c5d_profileAla.png'),
(52, 'Ala', 'Moussa', 'moussa.ala@esprit.tn', '123', 58409876, 'Admin', 'src\\main\\resources\\assets\\uploads\\86752492-5bad-4f6c-a597-bef4727e3c5d_profileAla.png'),
(53, 'Ala', 'Moussa', 'moussa.ala@esprit.tn', '123', 58409876, 'Admin', 'src\\main\\resources\\assets\\uploads\\86752492-5bad-4f6c-a597-bef4727e3c5d_profileAla.png'),
(54, 'Ala', 'Moussa', 'moussa.ala@esprit.tn', '123', 58409876, 'Admin', 'src\\main\\resources\\assets\\uploads\\86752492-5bad-4f6c-a597-bef4727e3c5d_profileAla.png'),
(55, 'Ala', 'Moussa', 'moussa.ala@esprit.tn', '123', 58409876, 'Admin', 'src\\main\\resources\\assets\\uploads\\86752492-5bad-4f6c-a597-bef4727e3c5d_profileAla.png'),
(56, 'Ala', 'Moussa', 'moussa.ala@esprit.tn', '123', 58409876, 'Admin', 'src\\main\\resources\\assets\\uploads\\86752492-5bad-4f6c-a597-bef4727e3c5d_profileAla.png'),
(57, 'Ala', 'Moussa', 'moussa.ala@esprit.tn', '123', 58409876, 'Admin', 'src\\main\\resources\\assets\\uploads\\86752492-5bad-4f6c-a597-bef4727e3c5d_profileAla.png'),
(58, 'Ala', 'Moussa', 'moussa.ala@esprit.tn', '123', 58409876, 'Admin', 'src\\main\\resources\\assets\\uploads\\86752492-5bad-4f6c-a597-bef4727e3c5d_profileAla.png'),
(59, 'Ala', 'Moussa', 'moussa.ala@esprit.tn', '123', 58409876, 'Admin', 'src\\main\\resources\\assets\\uploads\\86752492-5bad-4f6c-a597-bef4727e3c5d_profileAla.png'),
(60, 'Ala', 'Moussa', 'moussa.ala@esprit.tn', '123', 58409876, 'Admin', 'src\\main\\resources\\assets\\uploads\\86752492-5bad-4f6c-a597-bef4727e3c5d_profileAla.png'),
(61, 'Ala', 'Moussa', 'moussa.ala@esprit.tn', '123', 58409876, 'Admin', 'src\\main\\resources\\assets\\uploads\\86752492-5bad-4f6c-a597-bef4727e3c5d_profileAla.png'),
(62, 'Ala', 'Moussa', 'moussa.ala@esprit.tn', '123', 58409876, 'Admin', 'src\\main\\resources\\assets\\uploads\\86752492-5bad-4f6c-a597-bef4727e3c5d_profileAla.png');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
