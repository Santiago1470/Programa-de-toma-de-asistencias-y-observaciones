-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3307
-- Tiempo de generación: 18-06-2024 a las 22:39:40
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `registroasistencias`
--
CREATE DATABASE IF NOT EXISTS `registroasistencias` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `registroasistencias`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_administrador`
--

CREATE TABLE `tbl_administrador` (
  `id_admin` int(15) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `habilitacion` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Truncar tablas antes de insertar `tbl_administrador`
--

TRUNCATE TABLE `tbl_administrador`;
--
-- Volcado de datos para la tabla `tbl_administrador`
--

INSERT INTO `tbl_administrador` (`id_admin`, `nombre`, `password`, `correo`, `habilitacion`) VALUES
(1, 'Admin', '12345', 'admindefecto@correo.com', 1),
(2, 'Admin2', 'password', 'admincorreo@correo.com', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_asistencia`
--

CREATE TABLE `tbl_asistencia` (
  `id_asis` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `asistir` tinyint(1) NOT NULL,
  `doc_asis` int(11) NOT NULL,
  `est_asis` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Truncar tablas antes de insertar `tbl_asistencia`
--

TRUNCATE TABLE `tbl_asistencia`;
--
-- Volcado de datos para la tabla `tbl_asistencia`
--

INSERT INTO `tbl_asistencia` (`id_asis`, `fecha`, `asistir`, `doc_asis`, `est_asis`) VALUES
(1, '2023-11-19', 1, 1, 1),
(2, '2023-11-19', 0, 1, 2),
(3, '2023-11-20', 0, 1, 1),
(4, '2023-11-20', 1, 1, 2),
(5, '2023-11-20', 0, 2, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_docente`
--

CREATE TABLE `tbl_docente` (
  `id_doc` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `habilitacion` tinyint(1) NOT NULL,
  `admin_doc` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Truncar tablas antes de insertar `tbl_docente`
--

TRUNCATE TABLE `tbl_docente`;
--
-- Volcado de datos para la tabla `tbl_docente`
--

INSERT INTO `tbl_docente` (`id_doc`, `nombre`, `password`, `correo`, `habilitacion`, `admin_doc`) VALUES
(1, 'Judy Marcela Moreno Ospina', 'password', 'judym.morenoo@konradlorenz.edu.co', 1, 1),
(2, 'Fernanda Jimenez Cortés', 'password2', 'fernanda.jimenezc@konradlorenz.edu.co', 1, 1),
(3, 'Juan Pablo Prieto Diaz', 'password3', 'juanp.prietod@konradlorenz.edu.co', 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_estudiante`
--

CREATE TABLE `tbl_estudiante` (
  `id_estudiante` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `habilitacion` tinyint(1) NOT NULL,
  `doc_asignado` int(11) DEFAULT NULL,
  `admin_est` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Truncar tablas antes de insertar `tbl_estudiante`
--

TRUNCATE TABLE `tbl_estudiante`;
--
-- Volcado de datos para la tabla `tbl_estudiante`
--

INSERT INTO `tbl_estudiante` (`id_estudiante`, `nombre`, `password`, `correo`, `habilitacion`, `doc_asignado`, `admin_est`) VALUES
(1, 'Andres Felipe Patarroyo Muñoz', 'password', 'andresf.patarroyom@konradlorenz.edu.co', 1, 1, 1),
(2, 'Santiago Jair Torres Rivera', 'contraseña', 'santiagoj.torresr@konradlorenz.edu.co', 1, 1, 1),
(3, 'Sebastian Palomino Martínez', 'password3', 'sebastian.palominom@konradlorenz.edu.co', 1, 2, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_observacion`
--

CREATE TABLE `tbl_observacion` (
  `id_observ` int(11) NOT NULL,
  `observacion` varchar(200) NOT NULL,
  `fecha` date NOT NULL,
  `doc_observ` int(11) NOT NULL,
  `est_observ` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Truncar tablas antes de insertar `tbl_observacion`
--

TRUNCATE TABLE `tbl_observacion`;
--
-- Volcado de datos para la tabla `tbl_observacion`
--

INSERT INTO `tbl_observacion` (`id_observ`, `observacion`, `fecha`, `doc_observ`, `est_observ`) VALUES
(1, 'Hizo todo, no perdió exámenes.', '2023-11-20', 1, 1),
(2, 'Buen estudiante.', '2023-11-21', 1, 2),
(3, 'Pasó la materia.', '2023-11-21', 2, 3);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tbl_administrador`
--
ALTER TABLE `tbl_administrador`
  ADD PRIMARY KEY (`id_admin`);

--
-- Indices de la tabla `tbl_asistencia`
--
ALTER TABLE `tbl_asistencia`
  ADD PRIMARY KEY (`id_asis`),
  ADD KEY `fk_asis_doc` (`doc_asis`),
  ADD KEY `fk_asis_est` (`est_asis`);

--
-- Indices de la tabla `tbl_docente`
--
ALTER TABLE `tbl_docente`
  ADD PRIMARY KEY (`id_doc`),
  ADD KEY `fk_doc_admin` (`admin_doc`);

--
-- Indices de la tabla `tbl_estudiante`
--
ALTER TABLE `tbl_estudiante`
  ADD PRIMARY KEY (`id_estudiante`),
  ADD KEY `fk_doc_asignado` (`doc_asignado`) USING BTREE,
  ADD KEY `fk_est_admin` (`admin_est`);

--
-- Indices de la tabla `tbl_observacion`
--
ALTER TABLE `tbl_observacion`
  ADD PRIMARY KEY (`id_observ`),
  ADD KEY `fk_observ_doc` (`doc_observ`),
  ADD KEY `fk_observ_est` (`est_observ`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `tbl_asistencia`
--
ALTER TABLE `tbl_asistencia`
  MODIFY `id_asis` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `tbl_docente`
--
ALTER TABLE `tbl_docente`
  MODIFY `id_doc` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `tbl_estudiante`
--
ALTER TABLE `tbl_estudiante`
  MODIFY `id_estudiante` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `tbl_observacion`
--
ALTER TABLE `tbl_observacion`
  MODIFY `id_observ` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `tbl_asistencia`
--
ALTER TABLE `tbl_asistencia`
  ADD CONSTRAINT `tbl_asistencia_ibfk_1` FOREIGN KEY (`est_asis`) REFERENCES `tbl_estudiante` (`id_estudiante`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `tbl_asistencia_ibfk_2` FOREIGN KEY (`doc_asis`) REFERENCES `tbl_docente` (`id_doc`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tbl_docente`
--
ALTER TABLE `tbl_docente`
  ADD CONSTRAINT `tbl_docente_ibfk_1` FOREIGN KEY (`admin_doc`) REFERENCES `tbl_administrador` (`id_admin`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tbl_estudiante`
--
ALTER TABLE `tbl_estudiante`
  ADD CONSTRAINT `tbl_estudiante_ibfk_1` FOREIGN KEY (`admin_est`) REFERENCES `tbl_administrador` (`id_admin`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `tbl_estudiante_ibfk_2` FOREIGN KEY (`doc_asignado`) REFERENCES `tbl_docente` (`id_doc`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tbl_observacion`
--
ALTER TABLE `tbl_observacion`
  ADD CONSTRAINT `tbl_observacion_ibfk_1` FOREIGN KEY (`est_observ`) REFERENCES `tbl_estudiante` (`id_estudiante`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `tbl_observacion_ibfk_2` FOREIGN KEY (`doc_observ`) REFERENCES `tbl_docente` (`id_doc`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
