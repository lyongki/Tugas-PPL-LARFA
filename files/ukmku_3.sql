-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 30 Jun 2020 pada 07.54
-- Versi server: 10.1.37-MariaDB
-- Versi PHP: 7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ukmku(3)`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `event`
--

CREATE TABLE `event` (
  `id` int(30) NOT NULL,
  `id_ukm` int(5) NOT NULL,
  `nama_event` varchar(30) NOT NULL,
  `tanggal` datetime NOT NULL,
  `thumbnail` varchar(100) NOT NULL,
  `deskripsi` varchar(200) NOT NULL,
  `evaluasi` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `event`
--

INSERT INTO `event` (`id`, `id_ukm`, `nama_event`, `tanggal`, `thumbnail`, `deskripsi`, `evaluasi`) VALUES
(1, 1, 'Young Muslim Creativity', '2020-02-24 23:49:52', 'http://192.168.43.47/akubisa_services/files/natuna.jpg', 'Young Muslim Creativity adalah acara tahunan yang diadakan oleh UKM BAI Matholiul Anwar. Dalam acara ini terdapat 2 jenis perlombaan, lomba tilawah Al-Quran dan lomba aplikasi islami. Peserta dalam ac', '1. Panitia datang tidak tepat waktu\r\n2. Seringnya miss komunikasi antar panitia\r\n3. Informasi untuk peserta kurang jelas\r\n1. Panitia datang tidak tepat waktu\r\n2. Seringnya miss komunikasi antar panitia\r\n3. Informasi untuk peserta kurang jelas'),
(2, 1, 'Kajian Rutin Senin', '2020-02-24 23:49:52', 'http://192.168.43.47/akubisa_services/files/natuna.jpg', 'Kajian Rutin Senin diperuntukan untuk umum yang diadakan di Masjid Baitul Ilmi Udinus. Kegiatan ini merupakan agenda mingguan UKM BAI Matholiul Anwar.', '1. Kurangnya sosialisasi bahwa kajian ini diperuntukan untuk umum\r\n2. Kurangnya pemateri yang berkualitas'),
(3, 2, 'DINACOM', '2020-02-24 23:49:52', 'http://192.168.43.47/akubisa_services/files/natuna.jpg', 'Dinacom merupakan agenda tahunan UKM DNCC. Dalam agenda ini terdapat 2 rangkaian acara yaitu workshop dan lomba aplikasi', '1. Kurangnya sosialisasi event'),
(4, 1, 'Sharing Kamis Sore', '2020-06-04 15:45:00', 'http://192.168.43.47/akubisa_services/files/natuna.jpg', 'Sharing Kamis sore merpakan kajian rutin setiap Kamis sore yang diadakan di Masjid Baitul Mutaqqin. Acra ini diperuntukan untuk umum dan gratis.', '1. Kurangnya pemateri yang berkompeten\r\n2. Kurangnya sosialisasi tentang event ini kepada mahasiswa UDINUS'),
(5, 2, 'Pelatihan Multimedia', '2020-06-19 19:00:00', 'http://192.168.43.47/akubisa_services/files/natuna.jpg', 'Pelatihan multimedia merupakan event yang diadakan oleh UKM DNCC untuk mahasiswa UDINUS. Pada event ini peserta mendapatkan bimbingan tentang cara penggunaan software editing seperti adobe illustrator', '1. Pelatih masih kurang menguasai materi yang akan disampaikan kepada peserta\r\n2. Kurangnya sosialisasi tentang adanya event ini'),
(6, 2, 'Pelatihan Pemrograman', '2020-06-07 10:00:00', 'http://192.168.43.47/akubisa_services/files/natuna.jpg', 'Pelatihan pemrograman merupakan event bulanan yang diadakan oleh UKM DNCC. Pelatihan ini ditujukan khusus untuk mahasiswa UDINUS. Acara ini gratis dan diadakan di lab komputer UDINUS.', '1. Panitia datang tidak tepat waktu sehingga membuat acara mulai tidak tepat waktu\r\n2. Kurangnya pelatih untuk mendampingi peserta yang masih kesulitan'),
(7, 3, 'Upacara HUT RI', '2020-08-17 06:00:00', 'http://192.168.43.47/akubisa_services/files/natuna.jpg', 'Upacara HUT RI merupakan event tahunan yang diadakan oleh UKM Resimen Mahasiswa UDINUS. Event ini bertempat di lapangan parkir kampus depan. Acara ini dihadiri oleh civitas akademi UDINUS.', '1. Kurangnya latihan pasukkan pengibar bendera\r\n2. Kurangnya dana untuk konsumsi ketika latihan'),
(8, 3, 'Pembekalan Calon Pengurus', '2020-07-14 07:00:00', 'http://192.168.43.47/akubisa_services/files/natuna.jpg', 'Pembekalan Calon Pengurus merupakan agenda tahunan untuk mempersiapkan calon pengurus di periode yang akan mendatang', '1. Kurangnya dana untuk melaksanakan event ini\r\n2. Minat peserta masih kurang'),
(9, 3, 'Makrab', '2020-09-23 13:00:00', 'http://192.168.43.47/akubisa_services/files/natuna.jpg', 'Event ini merupakan sarana untuk mempererat silaturahmi antara pengurus UKM Resimen Mahasiswa dengan anggota baru UKM tersebut', '1. Kurangnya kegiatan yang menarik minat peserta\r\n2. Kurangnya fasilitas yang diberikan oleh pengurus');

-- --------------------------------------------------------

--
-- Struktur dari tabel `inventaris`
--

CREATE TABLE `inventaris` (
  `id` int(30) NOT NULL,
  `id_ukm` int(5) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `jumlah` int(30) NOT NULL,
  `jumlah_dipinjam` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `inventaris`
--

INSERT INTO `inventaris` (`id`, `id_ukm`, `nama`, `jumlah`, `jumlah_dipinjam`) VALUES
(1, 1, 'Printer', 1, 0),
(2, 1, 'PC', 1, 1),
(3, 1, 'Kipas Angin', 2, 2),
(4, 2, 'Lemari', 2, 0),
(5, 2, 'Meja', 3, 1),
(6, 2, 'Kursi', 4, 2),
(7, 3, 'Monitor', 1, 0),
(8, 3, 'Karpet', 2, 1),
(9, 3, 'Kipas Angin', 4, 3);

-- --------------------------------------------------------

--
-- Struktur dari tabel `inventaris_pinjam`
--

CREATE TABLE `inventaris_pinjam` (
  `id` int(11) NOT NULL,
  `id_pengurus` int(5) NOT NULL,
  `id_inventaris` int(5) NOT NULL,
  `jumlah` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `inventaris_pinjam`
--

INSERT INTO `inventaris_pinjam` (`id`, `id_pengurus`, `id_inventaris`, `jumlah`) VALUES
(1, 1, 2, 1),
(2, 3, 3, 1),
(3, 4, 3, 1),
(4, 6, 5, 1),
(5, 5, 6, 1),
(6, 7, 6, 1),
(7, 9, 8, 1),
(8, 12, 9, 2),
(9, 10, 9, 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `kas`
--

CREATE TABLE `kas` (
  `id` int(30) NOT NULL,
  `id_ukm` int(5) NOT NULL,
  `id_uang` int(5) NOT NULL,
  `nim` varchar(30) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `tanggal` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `kas`
--

INSERT INTO `kas` (`id`, `id_ukm`, `id_uang`, `nim`, `nama`, `tanggal`) VALUES
(1, 1, 1, 'A11.2017.10123', 'Abdul Sujono', '2020-02-24 23:49:00'),
(2, 1, 2, 'A11.2016.10456', 'Ahmad Dika', '2020-02-24 08:10:16'),
(3, 1, 3, 'A11.2017.10125', 'Nisa Setiawati', '2020-02-24 23:49:00'),
(4, 2, 4, 'A11.2017.10190', 'Fitri Ayu', '2020-02-25 10:29:00'),
(5, 2, 5, 'A11.2017.10145', 'Budi Sunono', '2020-02-25 23:49:00'),
(6, 2, 6, 'A11.2017.10128', 'Dian Anisa', '2020-02-25 17:49:00'),
(7, 3, 7, 'A11.2017.10143', 'Satria Bagas', '2020-02-26 10:49:00'),
(8, 3, 8, 'A11.2017.10100', 'Harald Junior', '2020-02-26 08:49:00'),
(9, 3, 9, 'A11.2017.10873', 'Erga Novendra', '2020-02-26 06:49:00'),
(10, 1, 10, 'A11.2017.10123', 'Abdul Sujono', '2020-03-24 23:49:00'),
(11, 1, 11, 'A11.2016.10456', 'Ahmad Dika', '2020-03-24 08:10:16'),
(12, 1, 12, 'A11.2017.10125', 'Nisa Setiawati', '2020-03-24 23:49:00'),
(13, 2, 13, 'A11.2017.10190', 'Fitri Ayu', '2020-03-25 10:29:00'),
(14, 2, 14, 'A11.2017.10145', 'Budi Sunono', '2020-03-25 23:49:00'),
(15, 2, 15, 'A11.2017.10128', 'Dian Anisa', '2020-03-25 17:49:00'),
(16, 3, 16, 'A11.2017.10143', 'Satria Bagas', '2020-03-26 10:49:00'),
(17, 3, 17, 'A11.2017.10100', 'Harald Junior', '2020-03-26 08:49:00'),
(18, 3, 18, 'A11.2017.10873', 'Erga Novendra', '2020-03-26 06:49:00'),
(19, 1, 19, 'A11.2017.10123', 'Abdul Sujono', '2020-04-24 23:49:00'),
(20, 1, 20, 'A11.2016.10456', 'Ahmad Dika', '2020-04-24 08:10:16'),
(21, 1, 21, 'A11.2017.10125', 'Nisa Setiawati', '2020-04-24 23:49:00'),
(22, 2, 22, 'A11.2017.10190', 'Fitri Ayu', '2020-04-25 10:29:00'),
(23, 2, 23, 'A11.2017.10145', 'Budi Sunono', '2020-04-25 23:49:00'),
(24, 2, 24, 'A11.2017.10128', 'Dian Anisa', '2020-04-25 17:49:00'),
(25, 3, 25, 'A11.2017.10143', 'Satria Bagas', '2020-04-26 10:49:00'),
(26, 3, 26, 'A11.2017.10100', 'Harald Junior', '2020-04-26 08:49:00'),
(27, 3, 27, 'A11.2017.10873', 'Erga Novendra', '2020-04-26 06:49:00');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pengguna`
--

CREATE TABLE `pengguna` (
  `id` int(30) NOT NULL,
  `id_ukm` int(5) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `role` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pengguna`
--

INSERT INTO `pengguna` (`id`, `id_ukm`, `nama`, `username`, `password`, `role`) VALUES
(1, 1, 'Abdul Sujono', 'sujono', 'sujono', 1),
(2, 1, 'Ahmad Dika', 'dika', 'dika', 2),
(3, 1, 'Nisa Setiawati', 'nisa', 'nisa', 3),
(4, 1, 'Yayang Eka Pratiwi', 'yayang', 'yangsayang', 4),
(5, 2, 'Fitri Ayu', 'yuayu', 'yuayu', 1),
(6, 2, 'Budi Sunono', 'budbud', 'budbud', 2),
(7, 2, 'Dian Anisa', 'dian', 'dian', 3),
(8, 2, 'Anya Geraldine', 'anya', 'anya', 4),
(9, 3, 'Satria Bagas', 'satgas', 'satgas', 1),
(10, 3, 'Harald Junior', 'harald', 'harald', 2),
(11, 3, 'Erga Novendra', 'erga', 'erga', 3),
(12, 3, 'Fano Bastian', 'fano', 'fano', 4);

-- --------------------------------------------------------

--
-- Struktur dari tabel `rapat`
--

CREATE TABLE `rapat` (
  `id` int(30) NOT NULL,
  `id_ukm` int(5) NOT NULL,
  `tanggal` datetime NOT NULL,
  `nama` varchar(60) NOT NULL,
  `hasil` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `rapat`
--

INSERT INTO `rapat` (`id`, `id_ukm`, `tanggal`, `nama`, `hasil`) VALUES
(1, 1, '2020-02-24 23:49:52', 'Rapat Panitia Young Muslim Creativity', 'Acara ditunda hingga pandemi Covid-19 selesai'),
(2, 1, '2020-02-24 23:49:52', 'Rapat Divisi Media', '1. Buat poster tentang ramadhan'),
(3, 2, '2020-02-24 23:49:52', 'Rapat Divisi Kaderisasi', '1. Proker kuliah Al-Quran diadakan secara online selama pandemi Covid-19'),
(4, 1, '2020-04-14 13:45:16', 'Rapat Divisi Syiar', 'Kajian Rutin Senin dan Sharing Kamis Sore selama wabah pandemi diadakan secara daring menggunakan Zoom Meeting'),
(5, 2, '2020-05-19 11:14:23', 'Rapat Pelatihan Multimedia', 'Pelatihan ditiadakan sampai wabah pandemi hilang'),
(6, 2, '2020-05-04 10:05:14', 'Rapat Pelatihan Pemrograman', 'Pelatihan diadakan secara online dengan cara membagikan materi melalui youtube dan grub Whatsapp'),
(7, 3, '2020-05-12 13:25:54', 'Rapat Upacara HUT RI', 'Membuat peringatan HUT RI secara online sambil menunggu instruksi lebih lanjut dari rektorat'),
(8, 3, '2020-06-30 16:30:56', 'Rapat Makrab', 'Buat poster dan link pendaftaran untuk anggota baru'),
(9, 3, '2020-06-22 20:18:09', 'Rapat Pembekalan Calon Penguru', 'Buat poster countdown event semenarik mungkin dan buat link untuk mengupload penugasan untuk masing-masing peserta');

-- --------------------------------------------------------

--
-- Struktur dari tabel `surat`
--

CREATE TABLE `surat` (
  `id` int(30) NOT NULL,
  `id_ukm` int(5) NOT NULL,
  `tanggal` datetime NOT NULL,
  `role` int(30) NOT NULL,
  `nama` varchar(60) NOT NULL,
  `file` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `surat`
--

INSERT INTO `surat` (`id`, `id_ukm`, `tanggal`, `role`, `nama`, `file`) VALUES
(1, 1, '2020-02-24 23:49:52', 0, 'Surat Undangan Doa Bersama Seluruh UKM Se-UDINUS', 'http://192.168.43.47/akubisa_services/files/CliffMichae_small.pdf'),
(2, 1, '2020-02-24 23:49:52', 1, 'Surat Peminjaman Ruang Rapat Gedung H UDINUS', 'http://192.168.43.47/akubisa_services/files/CliffMichae_small.pdf'),
(3, 2, '2020-02-24 23:49:52', 1, 'Surat Peminjaman Bis', 'http://192.168.43.47/akubisa_services/files/CliffMichae_small.pdf'),
(4, 1, '2020-02-28 11:17:00', 1, 'Surat izin mengadakan kajian di Masjid Baitul Ilmi UDINUS', 'http://192.168.43.47/akubisa_services/files/CliffMichae_small.pdf'),
(5, 2, '2020-04-15 08:15:10', 1, 'Surat Peminjaman Lab Multimedia', 'http://192.168.43.47/akubisa_services/files/CliffMichae_small.pdf'),
(6, 2, '2020-03-27 09:16:36', 1, 'Surat Peminjaman Lab Komputer ', 'http://192.168.43.47/akubisa_services/files/CliffMichae_small.pdf'),
(7, 3, '2020-06-18 07:28:19', 0, 'Surat Undangan Webinar Menwa UNDIP', 'http://192.168.43.47/akubisa_services/files/CliffMichae_small.pdf'),
(8, 3, '2020-06-15 20:37:13', 0, 'Surat Undangan Webinar Menwa Unisula', 'http://192.168.43.47/akubisa_services/files/CliffMichae_small.pdf'),
(9, 3, '2020-06-19 09:17:35', 0, 'Surat Undangan Silaturahmi UKM se-UDINUS', 'http://192.168.43.47/akubisa_services/files/CliffMichae_small.pdf');

-- --------------------------------------------------------

--
-- Struktur dari tabel `uang`
--

CREATE TABLE `uang` (
  `id` int(30) NOT NULL,
  `id_ukm` int(5) NOT NULL,
  `tanggal` datetime NOT NULL,
  `jumlah` int(10) NOT NULL,
  `role` int(5) NOT NULL,
  `keterangan` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `uang`
--

INSERT INTO `uang` (`id`, `id_ukm`, `tanggal`, `jumlah`, `role`, `keterangan`) VALUES
(1, 1, '2020-02-24 23:49:00', 5000, 0, 'uang kas'),
(2, 1, '2020-02-24 08:10:16', 5000, 0, 'uang kas'),
(3, 1, '2020-02-24 23:49:00', 5000, 0, 'uang kas'),
(4, 2, '2020-02-25 10:29:00', 10000, 0, 'uang kas'),
(5, 2, '2020-02-25 23:49:00', 10000, 0, 'uang kas'),
(6, 2, '2020-02-25 17:49:00', 10000, 0, 'uang kas'),
(7, 3, '2020-02-26 10:49:00', 8000, 0, 'uang kas'),
(8, 3, '2020-02-26 08:49:00', 8000, 0, 'uang kas'),
(9, 3, '2020-02-26 06:49:00', 8000, 0, 'uang kas'),
(10, 1, '2020-03-24 23:49:00', 5000, 0, 'uang kas'),
(11, 1, '2020-03-24 08:10:16', 5000, 0, 'uang kas'),
(12, 1, '2020-03-24 23:49:00', 5000, 0, 'uang kas'),
(13, 2, '2020-03-25 10:29:00', 10000, 0, 'uang kas'),
(14, 2, '2020-03-25 23:49:00', 10000, 0, 'uang kas'),
(15, 2, '2020-03-25 17:49:00', 10000, 0, 'uang kas'),
(16, 3, '2020-03-26 10:49:00', 8000, 0, 'uang kas'),
(17, 3, '2020-03-26 08:49:00', 8000, 0, 'uang kas'),
(18, 3, '2020-03-26 06:49:00', 8000, 0, 'uang kas'),
(19, 1, '2020-04-24 23:49:00', 5000, 0, 'uang kas'),
(20, 1, '2020-04-24 08:10:16', 5000, 0, 'uang kas'),
(21, 1, '2020-04-24 23:49:00', 5000, 0, 'uang kas'),
(22, 2, '2020-04-25 10:29:00', 10000, 0, 'uang kas'),
(23, 2, '2020-04-25 23:49:00', 10000, 0, 'uang kas'),
(24, 2, '2020-04-25 17:49:00', 10000, 0, 'uang kas'),
(25, 3, '2020-04-26 10:49:00', 8000, 0, 'uang kas'),
(26, 3, '2020-04-26 08:49:00', 8000, 0, 'uang kas'),
(27, 3, '2020-04-26 06:49:00', 8000, 0, 'uang kas'),
(28, 1, '2020-04-30 13:20:00', 250000, 0, 'Laba Pembuatan Seragam PDH'),
(29, 1, '2020-05-30 13:20:00', 1000000, 0, 'Sponsor'),
(30, 2, '2020-05-01 15:20:00', 300000, 0, 'Laba Pembuatan Seragam PDH'),
(31, 2, '2020-06-01 15:20:00', 500000, 0, 'Sponsor'),
(32, 3, '2020-05-02 15:20:00', 120000, 0, 'Laba Pembuatan Seragam PDH'),
(33, 3, '2020-06-02 15:20:00', 1200000, 0, 'Sponsor'),
(34, 1, '2020-05-31 13:20:00', 1000000, 1, 'Sumbangan Bencana'),
(35, 1, '2020-06-01 07:20:00', 100000, 1, 'Perbaikan inventaris (kipas angin)'),
(36, 1, '2020-06-03 07:20:00', 50000, 1, 'Cetak poster'),
(37, 2, '2020-06-10 15:20:00', 200000, 1, 'Sumbangan Bencana'),
(38, 2, '2020-06-11 15:20:00', 80000, 1, 'Perbaikan inventaris (printer)'),
(39, 2, '2020-06-12 15:20:00', 150000, 1, 'Pembuatan x-banner'),
(40, 3, '2020-06-28 15:20:00', 200000, 1, 'Pembuatan roll banner'),
(41, 3, '2020-06-29 15:20:00', 90000, 1, 'Pembelian tinta printer'),
(42, 3, '2020-06-29 08:20:00', 250000, 1, 'Pembelian lemari');

-- --------------------------------------------------------

--
-- Struktur dari tabel `ukm`
--

CREATE TABLE `ukm` (
  `id` int(11) NOT NULL,
  `nama_ukm` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `ukm`
--

INSERT INTO `ukm` (`id`, `nama_ukm`) VALUES
(1, 'BAI Matholiul Anwar'),
(2, 'DNCC'),
(3, 'Resimen Mahasiswa');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `inventaris`
--
ALTER TABLE `inventaris`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `inventaris_pinjam`
--
ALTER TABLE `inventaris_pinjam`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `kas`
--
ALTER TABLE `kas`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `pengguna`
--
ALTER TABLE `pengguna`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `rapat`
--
ALTER TABLE `rapat`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `surat`
--
ALTER TABLE `surat`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `uang`
--
ALTER TABLE `uang`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `ukm`
--
ALTER TABLE `ukm`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `event`
--
ALTER TABLE `event`
  MODIFY `id` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT untuk tabel `inventaris`
--
ALTER TABLE `inventaris`
  MODIFY `id` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT untuk tabel `inventaris_pinjam`
--
ALTER TABLE `inventaris_pinjam`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT untuk tabel `kas`
--
ALTER TABLE `kas`
  MODIFY `id` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT untuk tabel `pengguna`
--
ALTER TABLE `pengguna`
  MODIFY `id` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT untuk tabel `rapat`
--
ALTER TABLE `rapat`
  MODIFY `id` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT untuk tabel `surat`
--
ALTER TABLE `surat`
  MODIFY `id` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT untuk tabel `uang`
--
ALTER TABLE `uang`
  MODIFY `id` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT untuk tabel `ukm`
--
ALTER TABLE `ukm`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
