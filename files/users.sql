-- phpMyAdmin SQL Dump
-- version 4.5.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Creato il: Dic 13, 2016 alle 10:55
-- Versione del server: 5.7.11
-- Versione PHP: 5.6.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `users`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `users`
--

CREATE TABLE `users` (
  `username` text NOT NULL,
  `password` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `users`
--

INSERT INTO `users` (`username`, `password`) VALUES
('baglio.stefano', '05b9115df05a2a467841772eccc969822d884c9e71841050fe9e0893cce7d11b'),
('barisi.michele', '208ad55e4c4200ca42a73ab260efd6df57efccc95aad25fe4d1f378e23a0acb6'),
('corrias.lorenzo', '03fd72f81572805dd59f829b94fd8a6f82077fb435ca2b406d9595718e521afa'),
('deiana.simone', '19a0098e641d4bee278bb5d470d06679ffc5fdc818c3a1c52bfb7f8cde3752d3'),
('calcina.matteo', 'fe301eaaac49b4652b8dfd9fb0e913683ac5600f59370a6261824ab608b4fad7'),
('formilan.giacomo', '104167cdea7960e001f98d09d4f8b7f94ecca6482f6510f82063cfff3a56dc5b'),
('morelli.gabriele', 'b8415f3947f6b04f94736f899be8ef4c33f8d921f185d30017b9c0cea42085ef'),
('prelaz.enea', '7205741178f5606863a0e930095f100f979d92342cc64ec66eeb50be8ec07a2d'),
('sandini.virginia', '0a4992ea442b53e3dca861deac09a8d4987004a8483079b12861080ea4aa1b52'),
('martelli.erik', '02d08359f754fb6b3afcf916e7784a2a41eb8196f5c43d647e368aeb0ad47597'),
('kostic.ivana', 'feee3235626c079da26ff0ccbacba430f809cdf5a8dc7358aee52f05ae2e6ed0'),
('wald.pietro', '09c12d01508bb6b857cf2f84199fb3134b58985806b40d6440a1acf453b2e106'),
('vuletic.dragan', '4868c5b9904cccc2a8fcc5549cb41e0e8228bfdbce757132f0fbf4094602e1b4');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;