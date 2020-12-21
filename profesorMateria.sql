-- MySQL dump 10.13  Distrib 5.7.32, for Linux (x86_64)
--
-- Host: localhost    Database: profesorMateria
-- ------------------------------------------------------
-- Server version	5.7.32-0ubuntu0.18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `AUXILIAR`
--

DROP TABLE IF EXISTS `AUXILIAR`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AUXILIAR` (
  `clave` int(11) NOT NULL,
  `activaTrigger` bit(1) NOT NULL,
  `semestre` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`clave`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AUXILIAR`
--

LOCK TABLES `AUXILIAR` WRITE;
/*!40000 ALTER TABLE `AUXILIAR` DISABLE KEYS */;
INSERT INTO `AUXILIAR` VALUES (1,_binary '','20XX-X');
/*!40000 ALTER TABLE `AUXILIAR` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CURSO`
--

DROP TABLE IF EXISTS `CURSO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CURSO` (
  `numEmpleado` varchar(6) NOT NULL,
  `claveMateria` int(11) NOT NULL,
  `grupo` varchar(5) NOT NULL,
  `tipo` varchar(11) NOT NULL,
  `hrsTC` int(11) DEFAULT '0',
  `hrsAsig` int(11) DEFAULT '0',
  PRIMARY KEY (`numEmpleado`,`claveMateria`,`grupo`,`tipo`),
  KEY `claveMateria` (`claveMateria`),
  CONSTRAINT `CURSO_ibfk_1` FOREIGN KEY (`numEmpleado`) REFERENCES `PROFESOR` (`numEmpleado`) ON UPDATE CASCADE,
  CONSTRAINT `CURSO_ibfk_2` FOREIGN KEY (`claveMateria`) REFERENCES `MATERIA` (`claveMateria`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `CURSO_HORARIO`
--

DROP TABLE IF EXISTS `CURSO_HORARIO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CURSO_HORARIO` (
  `numEmpleado` varchar(6) NOT NULL,
  `claveMateria` int(11) NOT NULL,
  `grupo` varchar(5) NOT NULL,
  `tipo` varchar(11) NOT NULL,
  `claveHorario` int(11) NOT NULL,
  PRIMARY KEY (`numEmpleado`,`claveMateria`,`grupo`,`tipo`,`claveHorario`),
  KEY `claveHorario` (`claveHorario`),
  CONSTRAINT `CURSO_HORARIO_ibfk_2` FOREIGN KEY (`claveHorario`) REFERENCES `HORARIO` (`claveHorario`) ON UPDATE CASCADE,
  CONSTRAINT `CURSO_HORARIO_ibfk_3` FOREIGN KEY (`numEmpleado`, `claveMateria`, `grupo`, `tipo`) REFERENCES `CURSO` (`numEmpleado`, `claveMateria`, `grupo`, `tipo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50003 trigger insertHoras
before insert on CURSO_HORARIO
for each row
begin
set @act = (select activaTrigger+0 from AUXILIAR);
set @TC = (select esTiempoCompleto+0 from PROFESOR where numEmpleado=new.numEmpleado);
set @horas = (select (hrFin-hrInicio)/10000 from HORARIO where claveHorario=new.claveHorario);
if @act = 1 then
if @TC = 1 then
update CURSO set hrsTC=hrsTC+@horas where numEmpleado=new.numEmpleado and claveMateria=new.claveMateria and grupo=new.grupo and tipo=new.tipo;
else
update CURSO set hrsAsig=hrsAsig+@horas where numEmpleado=new.numEmpleado and claveMateria=new.claveMateria and grupo=new.grupo and tipo=new.tipo;
end if;
end if;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50003 trigger updateHoras
before update on CURSO_HORARIO
for each row
begin
set @oldTC = (select esTiempoCompleto+0 from PROFESOR where numEmpleado=old.numEmpleado);
set @newTC = (select esTiempoCompleto+0 from PROFESOR where numEmpleado=new.numEmpleado);
set @oldHoras = (select (hrFin-hrInicio)/10000 from HORARIO where claveHorario=old.claveHorario);
set @newHoras = (select (hrFin-hrInicio)/10000 from HORARIO where claveHorario=new.claveHorario);
if @oldTC = 1 then
update CURSO set hrsTC=hrsTC-@oldHoras where numEmpleado=old.numEmpleado and claveMateria=old.claveMateria and grupo=old.grupo and tipo=old.tipo;
else
update CURSO set hrsAsig=hrsAsig-@oldHoras where numEmpleado=old.numEmpleado and claveMateria=old.claveMateria and grupo=old.grupo and tipo=old.tipo;
end if;
if @newTC = 1 then
update CURSO set hrsTC=hrsTC+@newHoras where numEmpleado=new.numEmpleado and claveMateria=new.claveMateria and grupo=new.grupo and tipo=new.tipo;
else
update CURSO set hrsAsig=hrsAsig+@newHoras where numEmpleado=new.numEmpleado and claveMateria=new.claveMateria and grupo=new.grupo and tipo=new.tipo;
end if;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50003 trigger deleteHoras
before delete on CURSO_HORARIO
for each row
begin
set @TC = (select esTiempoCompleto+0 from PROFESOR where numEmpleado=old.numEmpleado);
set @horas = (select (hrFin-hrInicio)/10000 from HORARIO where claveHorario=old.claveHorario);
if @TC = 1 then
update CURSO set hrsTC=hrsTC-@horas where numEmpleado=old.numEmpleado and claveMateria=old.claveMateria and grupo=old.grupo and tipo=old.tipo;
else
update CURSO set hrsAsig=hrsAsig-@horas where numEmpleado=old.numEmpleado and claveMateria=old.claveMateria and grupo=old.grupo and tipo=old.tipo;
end if;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `HORARIO`
--

DROP TABLE IF EXISTS `HORARIO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `HORARIO` (
  `claveHorario` int(11) NOT NULL AUTO_INCREMENT,
  `dia` varchar(9) DEFAULT NULL,
  `turno` varchar(10) DEFAULT NULL,
  `hrInicio` time DEFAULT NULL,
  `hrFin` time DEFAULT NULL,
  PRIMARY KEY (`claveHorario`)
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50003 trigger insertTurno
before insert on HORARIO
for each row
if new.hrInicio < '12:00:00' then
set new.turno='MATUTINO';
elseif new.hrInicio < '18:00:00' then
set new.turno='INTERMEDIO';
else
set new.turno='VESPERTINO';
end if */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50003 trigger updateTurno
before update on HORARIO
for each row
if new.hrInicio < '12:00:00' then
set new.turno='MATUTINO';
elseif new.hrInicio < '18:00:00' then
set new.turno='INTERMEDIO';
else
set new.turno='VESPERTINO';
end if */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `MATERIA`
--

DROP TABLE IF EXISTS `MATERIA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MATERIA` (
  `claveMateria` int(11) NOT NULL,
  `nom` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`claveMateria`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `PROFESOR`
--

DROP TABLE IF EXISTS `PROFESOR`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PROFESOR` (
  `numEmpleado` varchar(6) NOT NULL,
  `nom` varchar(38) DEFAULT NULL,
  `esTiempoCompleto` bit(1) NOT NULL,
  PRIMARY KEY (`numEmpleado`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50003 trigger updateTiempo
before update on PROFESOR
for each row
begin
if old.esTiempoCompleto != new.esTiempoCompleto then
if new.esTiempoCompleto+0 = 1 then
update CURSO set hrsTC=hrsAsig, hrsAsig=0 where numEmpleado=old.numEmpleado;
else
update CURSO set hrsAsig=hrsTC, hrsTC=0 where numEmpleado=old.numEmpleado;
end if;
end if;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-18 12:24:16
