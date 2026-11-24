-- phpMyAdmin SQL Dump
-- version 5.2.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 22 Nov 2026 pada 16.15
-- Versi server: 10.11.16-MariaDB
-- Versi PHP: 8.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Basis data: `db_sikursus`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `peserta`
--

CREATE TABLE `peserta` (
  `id_peserta` int(10) UNSIGNED NOT NULL,
  `nim` varchar(30) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `jenis_kelamin` enum('L','P') NOT NULL COMMENT 'L = Laki-laki, P = Perempuan',
  `prodi` varchar(100) NOT NULL,
  `no_hp` varchar(20) NOT NULL,
  `email` varchar(120) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `peserta`
--

INSERT INTO `peserta` (`id_peserta`, `nim`, `nama`, `jenis_kelamin`, `prodi`, `no_hp`, `email`) VALUES
(1, '2924001', 'Andi Saputra', 'L', 'Informatika', '081234567801', 'andi@example.com'),
(2, '2924006', 'Siti Aisyah', 'P', 'Sistem Informasi', '081234567802', 'siti@example.com'),
(3, '2924003', 'Budi Pratama', 'L', 'Informatika', '081234567803', 'budi@example.com'),
(4, '2924004', 'Dewi Lestari', 'P', 'Manajemen Informatika', '081234567804', 'dewi@example.com'),
(5, '2924005', 'Rizky Maulana', 'L', 'Informatika', '081234567805', 'rizky@example.com');

-- --------------------------------------------------------

--
-- Struktur dari tabel `users`
--

CREATE TABLE `users` (
  `id_user` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `role` enum('ADMIN','OPERATOR') NOT NULL DEFAULT 'OPERATOR',
  `aktif` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `users`
--

INSERT INTO `users` (`id_user`, `username`, `password_hash`, `role`, `aktif`) VALUES
(1, 'admin', '120000:drm/CJnHtPr5heDyXdY0VQ==:ctwUhkUIbEXNpuh3nziAVIOgeVkAdQrn3r2Pb/qHQsg=', 'ADMIN', 1),
(2, 'operator', '120000:CMwe/JixVzHbX829yzXrbQ==:JYZCesFJ6yeCs9s+z/j9iVYaNGNT76S2s5iApMl+D5E=', 'OPERATOR', 1);

--
-- Indeks untuk tabel yang dibuang
--

--
-- Indeks untuk tabel `peserta`
--
ALTER TABLE `peserta`
  ADD PRIMARY KEY (`id_peserta`),
  ADD UNIQUE KEY `uq_peserta_nim` (`nim`),
  ADD KEY `idx_peserta_nama` (`nama`);

--
-- Indeks untuk tabel `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `peserta`
--
ALTER TABLE `peserta`
  MODIFY `id_peserta` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT untuk tabel `users`
--
ALTER TABLE `users`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
