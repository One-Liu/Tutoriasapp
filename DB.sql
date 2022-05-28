-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema TutoriasAppDB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema TutoriasAppDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `TutoriasAppDB` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `TutoriasAppDB` ;

-- -----------------------------------------------------
-- Table `TutoriasAppDB`.`persona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TutoriasAppDB`.`persona` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) CHARACTER SET 'utf8' NOT NULL,
  `apellidoPaterno` VARCHAR(45) CHARACTER SET 'utf8' NOT NULL,
  `apellidoMaterno` VARCHAR(45) CHARACTER SET 'utf8' NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 48
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `TutoriasAppDB`.`programa_educativo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TutoriasAppDB`.`programa_educativo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombreProgramaEducativo` VARCHAR(50) CHARACTER SET 'utf8' NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `TutoriasAppDB`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TutoriasAppDB`.`usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Contrasena` VARCHAR(250) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `correoInstitucional` VARCHAR(45) CHARACTER SET 'utf8' NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `TutoriasAppDB`.`coordinador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TutoriasAppDB`.`coordinador` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idProgramaEducativo` INT NOT NULL,
  `idPersona` INT NOT NULL,
  `idUsuario` INT NOT NULL,
  PRIMARY KEY (`id`, `idProgramaEducativo`, `idPersona`, `idUsuario`),
  INDEX `fk_Coordinador_ProgramaEducativo1_idx` (`idProgramaEducativo` ASC) VISIBLE,
  INDEX `fk_Coordinador_Persona1_idx` (`idPersona` ASC) VISIBLE,
  INDEX `fk_coordinador_usuario1_idx` (`idUsuario` ASC) VISIBLE,
  CONSTRAINT `fk_Coordinador_Persona1`
    FOREIGN KEY (`idPersona`)
    REFERENCES `TutoriasAppDB`.`persona` (`id`),
  CONSTRAINT `fk_Coordinador_ProgramaEducativo1`
    FOREIGN KEY (`idProgramaEducativo`)
    REFERENCES `TutoriasAppDB`.`programa_educativo` (`id`),
  CONSTRAINT `fk_coordinador_usuario1`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `TutoriasAppDB`.`usuario` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `TutoriasAppDB`.`estudiante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TutoriasAppDB`.`estudiante` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `matricula` VARCHAR(9) CHARACTER SET 'utf8' NOT NULL,
  `idTutorAcademico` INT NOT NULL,
  `idProgramaEducativo` INT NOT NULL,
  `idPersona` INT NOT NULL,
  PRIMARY KEY (`id`, `idTutorAcademico`, `idProgramaEducativo`, `idPersona`),
  INDEX `fk_Estudiante_TutorAcademico1_idx` (`idTutorAcademico` ASC) VISIBLE,
  INDEX `fk_Estudiante_ProgramaEducativo1_idx` (`idProgramaEducativo` ASC) VISIBLE,
  INDEX `fk_Estudiante_Persona1_idx` (`idPersona` ASC) VISIBLE,
  CONSTRAINT `fk_Estudiante_Persona1`
    FOREIGN KEY (`idPersona`)
    REFERENCES `TutoriasAppDB`.`persona` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `TutoriasAppDB`.`estudiantes_problematicasacademicas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TutoriasAppDB`.`estudiantes_problematicasacademicas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idEstudiante` INT NOT NULL,
  `idProblematicaAcademica` INT NOT NULL,
  PRIMARY KEY (`id`, `idEstudiante`, `idProblematicaAcademica`),
  INDEX `fk_Estudiante_ProblematicaAcademica_Estudiante1_idx` (`idEstudiante` ASC) VISIBLE,
  INDEX `fk_Estudiante_ProblematicaAcademica_ProblematicaAcademica1_idx` (`idProblematicaAcademica` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `TutoriasAppDB`.`profesor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TutoriasAppDB`.`profesor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idPersona` INT NOT NULL,
  PRIMARY KEY (`id`, `idPersona`),
  INDEX `fk_Profesor_Persona1_idx` (`idPersona` ASC) VISIBLE,
  CONSTRAINT `fk_Profesor_Persona1`
    FOREIGN KEY (`idPersona`)
    REFERENCES `TutoriasAppDB`.`persona` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 29
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `TutoriasAppDB`.`experiencia_educativa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TutoriasAppDB`.`experiencia_educativa` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombreEE` VARCHAR(50) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `nrc` VARCHAR(5) NOT NULL,
  `idProfesor` INT NOT NULL,
  PRIMARY KEY (`id`, `idProfesor`),
  INDEX `fk_ExperienciaEducativa_Profesor1_idx` (`idProfesor` ASC) VISIBLE,
  CONSTRAINT `fk_ExperienciaEducativa_Profesor1`
    FOREIGN KEY (`idProfesor`)
    REFERENCES `TutoriasAppDB`.`profesor` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `TutoriasAppDB`.`fecha_cierre_entrega_reporte`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TutoriasAppDB`.`fecha_cierre_entrega_reporte` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fecha` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `TutoriasAppDB`.`periodo_escolar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TutoriasAppDB`.`periodo_escolar` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fechaInicio` DATE NOT NULL,
  `fechaTermino` DATE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `TutoriasAppDB`.`sesion_de_tutoria_academica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TutoriasAppDB`.`sesion_de_tutoria_academica` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fecha` DATE NOT NULL,
  `idPeriodoEscolar` INT NOT NULL,
  PRIMARY KEY (`id`, `idPeriodoEscolar`),
  INDEX `fk_SesionDeTutoriaAcademica_PeriodoEscolar1_idx` (`idPeriodoEscolar` ASC) VISIBLE,
  CONSTRAINT `fk_SesionDeTutoriaAcademica_PeriodoEscolar1`
    FOREIGN KEY (`idPeriodoEscolar`)
    REFERENCES `TutoriasAppDB`.`periodo_escolar` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `TutoriasAppDB`.`horario_de_sesion_de_tutoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TutoriasAppDB`.`horario_de_sesion_de_tutoria` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `hora` TIME NOT NULL,
  `idEstudiante` INT NOT NULL,
  `idSesionDeTutoriaAcademica` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `horarioDeSesionDeTutoria_estudiante_idx` (`idEstudiante` ASC) VISIBLE,
  INDEX `horarioDeSesionDeTutoria_sesionDeTutoriasAcademica_idx` (`idSesionDeTutoriaAcademica` ASC) VISIBLE,
  CONSTRAINT `horarioDeSesionDeTutoria_estudiante`
    FOREIGN KEY (`idEstudiante`)
    REFERENCES `TutoriasAppDB`.`estudiante` (`id`),
  CONSTRAINT `horarioDeSesionDeTutoria_sesionDeTutoriasAcademica`
    FOREIGN KEY (`idSesionDeTutoriaAcademica`)
    REFERENCES `TutoriasAppDB`.`sesion_de_tutoria_academica` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `TutoriasAppDB`.`jefe_de_carrera`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TutoriasAppDB`.`jefe_de_carrera` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idPersona` INT NOT NULL,
  `idUsuario` INT NOT NULL,
  PRIMARY KEY (`id`, `idPersona`, `idUsuario`),
  INDEX `fk_Jefes_de_carrera_personas_idx` (`idPersona` ASC) VISIBLE,
  INDEX `fk_Jefe_de_carrera_usuario1_idx` (`idUsuario` ASC) VISIBLE,
  CONSTRAINT `fk_Jefe_de_carrera_usuario1`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `TutoriasAppDB`.`usuario` (`id`),
  CONSTRAINT `fk_Jefes_de_carrera_personas`
    FOREIGN KEY (`idPersona`)
    REFERENCES `TutoriasAppDB`.`persona` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `TutoriasAppDB`.`lista_de_asistencia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TutoriasAppDB`.`lista_de_asistencia` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `hora` TIME NOT NULL,
  `idEstudiante` INT NOT NULL,
  `idSesionDeTutoriaAcademica` INT NOT NULL,
  PRIMARY KEY (`id`, `idEstudiante`, `idSesionDeTutoriaAcademica`),
  INDEX `fk_ListaDeAsistencias_Estudiante1_idx` (`idEstudiante` ASC) VISIBLE,
  INDEX `fk_ListaDeAsistencias_SesionDeTutoriaAcademica1_idx` (`idSesionDeTutoriaAcademica` ASC) VISIBLE,
  CONSTRAINT `fk_ListaDeAsistencias_Estudiante1`
    FOREIGN KEY (`idEstudiante`)
    REFERENCES `TutoriasAppDB`.`estudiante` (`id`),
  CONSTRAINT `fk_ListaDeAsistencias_SesionDeTutoriaAcademica1`
    FOREIGN KEY (`idSesionDeTutoriaAcademica`)
    REFERENCES `TutoriasAppDB`.`sesion_de_tutoria_academica` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `TutoriasAppDB`.`problematica_academica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TutoriasAppDB`.`problematica_academica` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(254) CHARACTER SET 'utf8' NOT NULL,
  `idExperienciaEducativa` INT NOT NULL,
  `idsolucionProblematicaAcademica` INT NOT NULL,
  `idSesionDeTutoriaAcademica` INT NOT NULL,
  PRIMARY KEY (`id`, `idExperienciaEducativa`, `idsolucionProblematicaAcademica`, `idSesionDeTutoriaAcademica`),
  INDEX `fk_ProblematicaAcademica_ExperienciaEducativa1_idx` (`idExperienciaEducativa` ASC) VISIBLE,
  INDEX `fk_spa_idSolucionProblematicaAcademica_idx` (`idsolucionProblematicaAcademica` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `TutoriasAppDB`.`tutor_academico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TutoriasAppDB`.`tutor_academico` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idPersona` INT NOT NULL,
  `idUsuario` INT NOT NULL,
  PRIMARY KEY (`id`, `idPersona`, `idUsuario`),
  INDEX `fk_TutorAcademico_Persona_idx` (`idPersona` ASC) VISIBLE,
  INDEX `fk_tutor_academico_usuario1_idx` (`idUsuario` ASC) VISIBLE,
  CONSTRAINT `fk_tutor_academico_usuario1`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `TutoriasAppDB`.`usuario` (`id`),
  CONSTRAINT `fk_TutorAcademico_Persona`
    FOREIGN KEY (`idPersona`)
    REFERENCES `TutoriasAppDB`.`persona` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 21
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `TutoriasAppDB`.`reporte_de_tutoria_academica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TutoriasAppDB`.`reporte_de_tutoria_academica` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descripcionGeneral` VARCHAR(250) CHARACTER SET 'utf8' NOT NULL,
  `idSesionDeTutoriaAcademica` INT NOT NULL,
  `idTutorAcademico` INT NOT NULL,
  `idFechaCierreEntregaReporte` INT NOT NULL,
  PRIMARY KEY (`id`, `idSesionDeTutoriaAcademica`, `idTutorAcademico`, `idFechaCierreEntregaReporte`),
  INDEX `fk_ReporteDeTutoriaAcademica_SesionDeTutoriaAcademica1_idx` (`idSesionDeTutoriaAcademica` ASC) VISIBLE,
  INDEX `fk_ReporteDeTutoriaAcademica_TutorAcademico1_idx` (`idTutorAcademico` ASC) VISIBLE,
  CONSTRAINT `fk_ReporteDeTutoriaAcademica_SesionDeTutoriaAcademica1`
    FOREIGN KEY (`idSesionDeTutoriaAcademica`)
    REFERENCES `TutoriasAppDB`.`sesion_de_tutoria_academica` (`id`),
  CONSTRAINT `fk_ReporteDeTutoriaAcademica_TutorAcademico1`
    FOREIGN KEY (`idTutorAcademico`)
    REFERENCES `TutoriasAppDB`.`tutor_academico` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `TutoriasAppDB`.`solucion_a_problematica_academica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TutoriasAppDB`.`solucion_a_problematica_academica` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(250) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;