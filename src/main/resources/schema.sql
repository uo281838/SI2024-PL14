--Primero se deben borrar todas las tablas (de detalle a maestro) y lugo anyadirlas (de maestro a detalle)
--(en este caso en cada aplicacion se usa solo una tabla, por lo que no hace falta)

--Para giis.demo.tkrun:
--drop table Carreras;
--create table Carreras (id int primary key not null, inicio date not null, fin date not null, fecha date not null, descr varchar(32), check(inicio<=fin), check(fin<fecha));


CREATE TABLE USUARIO (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    dni TEXT NOT NULL,
    password TEXT NOT NULL,
    rol TEXT CHECK (rol IN ('SOCIO', 'NO_SOCIO', 'ADMIN')) NOT NULL,
    estado TEXT CHECK (estado IN ('ACTIVO', 'MOROSO')) DEFAULT 'ACTIVO',
    recibos_pendientes INTEGER DEFAULT 0,
    fecha_registro DATE DEFAULT CURRENT_DATE
);

CREATE TABLE INSTALACION (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    tipo TEXT NOT NULL, -- piscina, pista tenis, pista futbol, etc.
    aforo_maximo INTEGER NOT NULL,
    estado TEXT CHECK (estado IN ('DISPONIBLE', 'CUARENTENA')) DEFAULT 'DISPONIBLE'
);

CREATE TABLE RESERVA_INSTALACION (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    usuario_id INTEGER NOT NULL,
    instalacion_id INTEGER NOT NULL,
    fecha DATE NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL,
    pagado BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (usuario_id) REFERENCES USUARIO(id),
    FOREIGN KEY (instalacion_id) REFERENCES INSTALACION(id)
);

CREATE TABLE PERIODO_INSCRIPCION (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    fecha_inicio_socios DATE NOT NULL,
    fecha_fin_socios DATE NOT NULL,
    fecha_fin_no_socios DATE NOT NULL
);

CREATE TABLE ACTIVIDAD (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    descripcion TEXT,
    instalacion_id INTEGER NOT NULL,
    aforo_maximo INTEGER NOT NULL,
    coste_socio DECIMAL(10,2) NOT NULL,
    coste_no_socio DECIMAL(10,2) NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    dias TEXT NOT NULL, -- Ejemplo: 'Lunes,Martes,Viernes'
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL,
    periodo_inscripcion_id,
    FOREIGN KEY (instalacion_id) REFERENCES INSTALACION(id),
    FOREIGN KEY (periodo_inscripcion_id) REFERENCES PERIODO_INSCRIPCION(id)
);

CREATE TABLE INSCRIPCION_ACTIVIDAD (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    usuario_id INTEGER,
    actividad_id INTEGER NOT NULL,
    pagado BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (usuario_id) REFERENCES USUARIO(id),
    FOREIGN KEY (actividad_id) REFERENCES ACTIVIDAD(id)
);

CREATE TABLE PAGO (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    usuario_id INTEGER NOT NULL,
    inscripcion_actividad_id INTEGER,
    reserva_instalacion_id INTEGER,
    monto DECIMAL(10,2) NOT NULL,
    concepto TEXT NOT NULL, -- Cuota, reserva, actividad
    fecha_pago DATE DEFAULT CURRENT_DATE,
    FOREIGN KEY (usuario_id) REFERENCES USUARIO(id),
    FOREIGN KEY (inscripcion_actividad_id) REFERENCES INSCRIPCION_ACTIVIDAD(id),
    FOREIGN KEY (reserva_instalacion_id) REFERENCES RESERVA_INSTALACION(id)
);

CREATE TABLE CONFIGURACION (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    clave TEXT UNIQUE NOT NULL,
    valor TEXT NOT NULL
);

INSERT INTO CONFIGURACION (clave, valor) VALUES
('hora_apertura', '09:00'),
('hora_cierre', '21:00'),
('reserva_antelacion_max_dias', '15'),
('max_horas_por_dia', '4'),
('max_horas_seguidas', '2'),
('max_horas_totales_reservadas', '10'),
('max_recibos_pendientes_para_moroso', '2');




