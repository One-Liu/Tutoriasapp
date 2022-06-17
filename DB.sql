create table if not exists estudiantes_problematicasacademicas
(
    id                      int auto_increment,
    idEstudiante            int not null,
    idProblematicaAcademica int not null,
    primary key (id, idEstudiante, idProblematicaAcademica)
);

create index fk_Estudiante_ProblematicaAcademica_Estudiante1_idx
    on estudiantes_problematicasacademicas (idEstudiante);

create index fk_Estudiante_ProblematicaAcademica_ProblematicaAcademica1_idx
    on estudiantes_problematicasacademicas (idProblematicaAcademica);

create table if not exists experiencia_educativa
(
    id         int auto_increment,
    nombreEE   varchar(50) charset utf8mb3 null,
    nrc        varchar(5)                  not null,
    idProfesor int                         not null,
    primary key (id, idProfesor)
);

create table if not exists fecha_cierre_entrega_reporte
(
    id    int auto_increment
        primary key,
    fecha date null
);

create table if not exists periodo_escolar
(
    id           int auto_increment
        primary key,
    fechaInicio  date not null,
    fechaTermino date not null
);

create table if not exists programa_educativo
(
    id                      int auto_increment
        primary key,
    nombreProgramaEducativo varchar(50) charset utf8mb3 not null
);

create table if not exists persona
(
    id                  int auto_increment
        primary key,
    nombre              varchar(45) charset utf8mb3 not null,
    apellidoPaterno     varchar(45) charset utf8mb3 not null,
    apellidoMaterno     varchar(45) charset utf8mb3 not null,
    idProgramaEducativo int                         null,
    constraint persona_ibfk_1
        foreign key (idProgramaEducativo) references programa_educativo (id)
);

create table if not exists estudiante
(
    id               int auto_increment,
    matricula        varchar(9) charset utf8mb3 not null,
    enRiesgo         tinyint(1) default 0       null,
    idTutorAcademico int                        not null,
    idPersona        int                        not null,
    primary key (id, idTutorAcademico, idPersona),
    constraint fk_Estudiante_Persona1
        foreign key (idPersona) references persona (id)
);

create index idProgramaEducativo
    on persona (idProgramaEducativo);

create table if not exists profesor
(
    id        int auto_increment,
    idPersona int not null,
    primary key (id, idPersona),
    constraint fk_Profesor_Persona1
        foreign key (idPersona) references persona (id)
);

create table if not exists problematica_academica
(
    id                              int auto_increment,
    descripcion                     varchar(254) charset utf8mb3 not null,
    idExperienciaEducativa          int                          not null,
    idSolucionProblematicaAcademica int                          not null,
    idSesionDeTutoriaAcademica      int                          not null,
    titulo                          varchar(50) charset utf8mb3  null,
    idProfesor                      int                          null,
    primary key (id, idExperienciaEducativa, idSolucionProblematicaAcademica, idSesionDeTutoriaAcademica),
    constraint problematica_academica_ibfk_1
        foreign key (idProfesor) references profesor (id)
);

create index fk_ProblematicaAcademica_ExperienciaEducativa1_idx
    on problematica_academica (idExperienciaEducativa);

create index fk_spa_idSolucionProblematicaAcademica_idx
    on problematica_academica (idSolucionProblematicaAcademica);

create index idProfesor
    on problematica_academica (idProfesor);

create index fk_Profesor_Persona1_idx
    on profesor (idPersona);

create table if not exists sesion_de_tutoria_academica
(
    id               int auto_increment,
    fecha            date              not null,
    ocurrio          tinyint default 0 not null,
    idPeriodoEscolar int               not null,
    primary key (id, idPeriodoEscolar),
    constraint fk_SesionDeTutoriaAcademica_PeriodoEscolar1
        foreign key (idPeriodoEscolar) references periodo_escolar (id)
);

create table if not exists lista_de_asistencia
(
    id                         int auto_increment,
    hora                       time              not null,
    asistio                    tinyint default 0 null,
    idEstudiante               int               not null,
    idSesionDeTutoriaAcademica int               not null,
    primary key (id, idEstudiante, idSesionDeTutoriaAcademica),
    constraint fk_ListaDeAsistencias_Estudiante1
        foreign key (idEstudiante) references estudiante (id),
    constraint fk_ListaDeAsistencias_SesionDeTutoriaAcademica1
        foreign key (idSesionDeTutoriaAcademica) references sesion_de_tutoria_academica (id)
);

create index fk_ListaDeAsistencias_Estudiante1_idx
    on lista_de_asistencia (idEstudiante);

create index fk_ListaDeAsistencias_SesionDeTutoriaAcademica1_idx
    on lista_de_asistencia (idSesionDeTutoriaAcademica);

create index fk_SesionDeTutoriaAcademica_PeriodoEscolar1_idx
    on sesion_de_tutoria_academica (idPeriodoEscolar);

create table if not exists solucion_a_problematica_academica
(
    id          int auto_increment
        primary key,
    descripcion varchar(250) null
);

create table if not exists usuario
(
    id                  int auto_increment
        primary key,
    contrasena          varchar(250) charset utf8mb3 null,
    correoInstitucional varchar(45) charset utf8mb3  not null
);

create table if not exists coordinador
(
    id        int auto_increment,
    idPersona int not null,
    idUsuario int not null,
    primary key (id, idPersona, idUsuario),
    constraint fk_Coordinador_Persona1
        foreign key (idPersona) references persona (id),
    constraint fk_coordinador_usuario1
        foreign key (idUsuario) references usuario (id)
);

create table if not exists jefe_de_carrera
(
    id        int auto_increment,
    idPersona int not null,
    idUsuario int not null,
    primary key (id, idPersona, idUsuario),
    constraint fk_Jefe_de_carrera_usuario1
        foreign key (idUsuario) references usuario (id),
    constraint fk_Jefes_de_carrera_personas
        foreign key (idPersona) references persona (id)
);

create index fk_Jefe_de_carrera_usuario1_idx
    on jefe_de_carrera (idUsuario);

create index fk_Jefes_de_carrera_personas_idx
    on jefe_de_carrera (idPersona);

create table if not exists tutor_academico
(
    id        int auto_increment,
    idPersona int not null,
    idUsuario int not null,
    primary key (id, idPersona, idUsuario),
    constraint fk_TutorAcademico_Persona
        foreign key (idPersona) references persona (id),
    constraint fk_tutor_academico_usuario1
        foreign key (idUsuario) references usuario (id)
);

create table if not exists reporte_de_tutoria_academica
(
    id                          int auto_increment,
    descripcionGeneral          varchar(250) charset utf8mb3 not null,
    idSesionDeTutoriaAcademica  int                          not null,
    idTutorAcademico            int                          not null,
    idFechaCierreEntregaReporte int                          not null,
    primary key (id, idSesionDeTutoriaAcademica, idTutorAcademico, idFechaCierreEntregaReporte),
    constraint fk_ReporteDeTutoriaAcademica_SesionDeTutoriaAcademica1
        foreign key (idSesionDeTutoriaAcademica) references sesion_de_tutoria_academica (id),
    constraint fk_ReporteDeTutoriaAcademica_TutorAcademico1
        foreign key (idTutorAcademico) references tutor_academico (id)
);

create index fk_ReporteDeTutoriaAcademica_SesionDeTutoriaAcademica1_idx
    on reporte_de_tutoria_academica (idSesionDeTutoriaAcademica);

create index fk_ReporteDeTutoriaAcademica_TutorAcademico1_idx
    on reporte_de_tutoria_academica (idTutorAcademico);

create index fk_TutorAcademico_Persona_idx
    on tutor_academico (idPersona);

create index fk_tutor_academico_usuario1_idx
    on tutor_academico (idUsuario);