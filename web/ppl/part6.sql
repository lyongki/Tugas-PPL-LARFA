-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 16, 2020 at 05:04 PM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test`
--

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `cid` int(10) UNSIGNED NOT NULL,
  `title` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `slug` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `status` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`cid`, `title`, `slug`, `status`, `created_at`, `updated_at`) VALUES
(2, 'about us', 'about-us', 'on', '2020-02-14 12:27:26', '2020-02-14 13:32:07'),
(3, 'service', 'service', 'on', '2020-02-14 12:27:45', '2020-02-14 13:32:30'),
(4, 'portfolio', 'portfolio', 'on', '2020-02-14 12:27:50', NULL),
(5, 'clients', 'clients', 'on', '2020-02-14 12:27:53', NULL),
(6, 'team', 'team', 'on', '2020-02-14 12:27:56', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `contents`
--

CREATE TABLE `contents` (
  `conid` int(10) UNSIGNED NOT NULL,
  `title` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `slug` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` longtext COLLATE utf8_unicode_ci NOT NULL,
  `link` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `category` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `status` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `contents`
--

INSERT INTO `contents` (`conid`, `title`, `slug`, `description`, `link`, `image`, `category`, `status`, `created_at`, `updated_at`) VALUES
(1, 'MEMBAHAGIAKAN YATIM DI MOMENT KARIM', 'membahagiakan-yatim-di-moment-karim', 'Dalam satu tahun kalender Hijrlyah, ada empat bulan yang Allah jadikan istimewa. Muharram salah satunya.', NULL, '20021541352main_device_image.png', 'home', 'on', '2020-02-14 21:13:52', NULL),
(2, 'Pengertian dan Macam-Macam Zakat.', 'zakat', '\r\n<p>Zakat&nbsp;adalah&nbsp;sejumlah&nbsp;harta&nbsp;yang&nbsp;wajib&nbsp;dikeluarkan&nbsp;oleh&nbsp;umat&nbsp;Muslim&nbsp;untuk&nbsp;diberikan&nbsp;kepada&nbsp;golongan&nbsp;yang&nbsp;berhak&nbsp;menerima,</p><p>&nbsp;seperti&nbsp;fakir&nbsp;miskin&nbsp;dan&nbsp;semacamnya,&nbsp;sesuai&nbsp;dengan&nbsp;yang&nbsp;ditetapkan&nbsp;oleh&nbsp;syariah.&nbsp;Zakat&nbsp;termasuk&nbsp;rukun&nbsp;Islam&nbsp;ke-4&nbsp;dan&nbsp;menjadi&nbsp;salah&nbsp;satu&nbsp;unsur&nbsp;paling&nbsp;penting&nbsp;dalam&nbsp;menegakkan&nbsp;syariat&nbsp;Islam.&nbsp;</p>\r\n\r\n<p>Oleh&nbsp;karena&nbsp;itu,&nbsp;hukum&nbsp;zakat&nbsp;adalah&nbsp;wajib&nbsp;bagi&nbsp;setiap&nbsp;Muslim&nbsp;yang&nbsp;telah&nbsp;memenuhi&nbsp;syarat-syarat&nbsp;tertentu.&nbsp;Zakat&nbsp;juga&nbsp;merupakan&nbsp;bentuk&nbsp;ibadah&nbsp;seperti&nbsp;shalat,&nbsp;puasa,&nbsp;dan&nbsp;lainnya</p><p>&nbsp;dan&nbsp;telah&nbsp;diatur&nbsp;dengan&nbsp;rinci&nbsp;berdasarkan&nbsp;Al-Quran&nbsp;dan&nbsp;Sunnah.</p>\r\n\r\n', NULL, '20021541352main_device_image.png', 'about us', 'on', '2020-02-15 02:46:02', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `migrations`
--

CREATE TABLE `migrations` (
  `id` int(10) UNSIGNED NOT NULL,
  `migration` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `batch` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `migrations`
--

INSERT INTO `migrations` (`id`, `migration`, `batch`) VALUES
(1, '2014_10_12_000000_create_users_table', 1),
(2, '2014_10_12_100000_create_password_resets_table', 1),
(3, '2020_02_14_013553_create_setups_table', 1),
(4, '2020_02_14_185859_create_categories_table', 2),
(5, '2020_02_15_033137_create_contents_table', 3),
(6, '2020_02_15_181206_create_services_table', 4),
(7, '2020_02_16_141118_create_portfolios_table', 5),
(8, '2020_02_16_141508_create_portcats_table', 6);

-- --------------------------------------------------------

--
-- Table structure for table `password_resets`
--

CREATE TABLE `password_resets` (
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `token` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `portcats`
--

CREATE TABLE `portcats` (
  `pcid` int(10) UNSIGNED NOT NULL,
  `title` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `slug` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `status` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `portcats`
--

INSERT INTO `portcats` (`pcid`, `title`, `slug`, `status`, `created_at`, `updated_at`) VALUES
(1, 'prototype', 'prototype', 'on', '2020-02-16 07:37:18', NULL),
(2, 'design', 'design', 'on', '2020-02-16 07:38:37', NULL),
(3, 'android', 'android', 'on', '2020-02-16 07:38:43', NULL),
(4, 'apple ios', 'apple-ios', 'on', '2020-02-16 07:38:58', NULL),
(5, 'web app', 'web-app', 'on', '2020-02-16 07:39:04', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `portfolios`
--

CREATE TABLE `portfolios` (
  `pid` int(10) UNSIGNED NOT NULL,
  `title` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `slug` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `category` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `status` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `portfolios`
--

INSERT INTO `portfolios` (`pid`, `title`, `slug`, `category`, `image`, `status`, `created_at`, `updated_at`) VALUES
(1, 'apps', 'apps', 'prototype', '20021633126portfolio_pic4.jpg', 'on', '2020-02-16 08:31:26', NULL),
(2, 'tech', 'tech', 'design', '20021633413portfolio_pic1.jpg', 'on', '2020-02-16 08:34:13', NULL),
(3, 'joss', 'joss', 'android', '20021633436portfolio_pic2.jpg', 'on', '2020-02-16 08:34:36', NULL),
(4, 'podo', 'podo', 'apple ios', '20021633454portfolio_pic3.jpg', 'on', '2020-02-16 08:34:54', NULL),
(5, 'ngono', 'ngono', 'web app', '20021633511portfolio_pic5.jpg', 'on', '2020-02-16 08:35:11', NULL),
(6, 'ngene', 'ngene', 'prototype', '20021633532portfolio_pic7.jpg', 'on', '2020-02-16 08:35:32', NULL),
(7, 'okelah', 'okelah', 'android', '20021633557portfolio_pic8.jpg', 'on', '2020-02-16 08:35:57', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `services`
--

CREATE TABLE `services` (
  `sid` int(10) UNSIGNED NOT NULL,
  `title` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `slug` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `icon` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` longtext COLLATE utf8_unicode_ci NOT NULL,
  `status` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `services`
--

INSERT INTO `services` (`sid`, `title`, `slug`, `icon`, `description`, `status`, `created_at`, `updated_at`) VALUES
(1, 'RBA', 'rba', 'android', '<p>Rumah belajar adzkia</p>\r\n', 'on', '2020-02-15 11:36:40', NULL),
(2, 'bus', 'bus', 'apple', '<p>beasiswa untuk surga</p>\r\n', 'on', '2020-02-16 07:03:25', NULL),
(3, 'fery', 'fery', 'android', '<p>agasdgasg</p>\r\n', 'on', '2020-02-16 07:04:14', NULL),
(4, 'eka', 'eka', 'apple', '<p>asdfasdg</p>\r\n', 'on', '2020-02-16 07:04:25', NULL),
(5, 'saputra', 'saputra', 'apple', '<p>sadfasdg</p>\r\n', 'on', '2020-02-16 07:04:38', NULL),
(6, 'dot net', 'dot-net', 'android', '<p>adgasg</p>\r\n', 'on', '2020-02-16 07:04:49', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `setups`
--

CREATE TABLE `setups` (
  `sid` int(10) UNSIGNED NOT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `meta_title` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `contact` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `social` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `setups`
--

INSERT INTO `setups` (`sid`, `image`, `meta_title`, `address`, `contact`, `email`, `social`, `created_at`, `updated_at`) VALUES
(3, '20021470830logo2.png', 'Lazis', 'jl. indrapasta', '089876876868', 'ferycna@gmail.com', 'asdfser', '2020-02-14 00:08:30', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `remember_token` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`cid`);

--
-- Indexes for table `contents`
--
ALTER TABLE `contents`
  ADD PRIMARY KEY (`conid`);

--
-- Indexes for table `migrations`
--
ALTER TABLE `migrations`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `password_resets`
--
ALTER TABLE `password_resets`
  ADD KEY `password_resets_email_index` (`email`),
  ADD KEY `password_resets_token_index` (`token`);

--
-- Indexes for table `portcats`
--
ALTER TABLE `portcats`
  ADD PRIMARY KEY (`pcid`);

--
-- Indexes for table `portfolios`
--
ALTER TABLE `portfolios`
  ADD PRIMARY KEY (`pid`);

--
-- Indexes for table `services`
--
ALTER TABLE `services`
  ADD PRIMARY KEY (`sid`);

--
-- Indexes for table `setups`
--
ALTER TABLE `setups`
  ADD PRIMARY KEY (`sid`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `users_email_unique` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `cid` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `contents`
--
ALTER TABLE `contents`
  MODIFY `conid` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `migrations`
--
ALTER TABLE `migrations`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `portcats`
--
ALTER TABLE `portcats`
  MODIFY `pcid` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `portfolios`
--
ALTER TABLE `portfolios`
  MODIFY `pid` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `services`
--
ALTER TABLE `services`
  MODIFY `sid` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `setups`
--
ALTER TABLE `setups`
  MODIFY `sid` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
