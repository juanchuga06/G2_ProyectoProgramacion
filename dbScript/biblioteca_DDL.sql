-- database: ../database/biblioteca.db
/*
copyRight EPN 2025
Maye_Ana
DDL : Definicion de las tablas de la base de datos
*/
DROP TABLE IF EXISTS Direccion;
DROP TABLE IF EXISTS Alquiler;
DROP TABLE IF EXISTS Bibliotecario;
DROP TABLE IF EXISTS Cliente;
DROP TABLE IF EXISTS Libro;
DROP TABLE IF EXISTS EstadoAlquiler;
DROP TABLE IF EXISTS EstadoCivil;
DROP TABLE IF EXISTS Sexo;
DROP TABLE IF EXISTS Autor;
DROP TABLE IF EXISTS Editorial;
DROP TABLE IF EXISTS GeneroLibro;
DROP TABLE IF EXISTS Portada;


CREATE TABLE GeneroLibro(
    IdGeneroLibro           INTEGER PRIMARY KEY AUTOINCREMENT,
    NombreGeneroLibro       VARCHAR(15) NOT NULL,
    Estado                  VARCHAR(1) DEFAULT 'A',
    FechaCreacion           DATETIME NOT NULL DEFAULT (datetime('now','localtime')),
    FechaModificacion       DATETIME
);

CREATE TABLE Editorial(
    IdEditorial             INTEGER PRIMARY KEY AUTOINCREMENT,
    NombreEditorial         VARCHAR(20) NOT NULL,
    Estado                  VARCHAR(1) DEFAULT 'A',
    FechaCreacion           DATETIME NOT NULL DEFAULT (datetime('now','localtime')),
    FechaModificacion       DATETIME
);

CREATE TABLE Autor(
    IdAutor                 INTEGER PRIMARY KEY AUTOINCREMENT,
    NombreAutor             VARCHAR(20) NOT NULL,
    Estado                  VARCHAR(1) DEFAULT 'A',
    FechaCreacion           DATETIME NOT NULL DEFAULT (datetime('now','localtime')),
    FechaModificacion       DATETIME
);

CREATE TABLE Sexo(
    IdSexo              INTEGER PRIMARY KEY AUTOINCREMENT,
    Nombre              VARCHAR(15) NOT NULL,
    Estado              VARCHAR(1) DEFAULT 'A',
    FechaCreacion       DATETIME NOT NULL DEFAULT (datetime('now','localtime')),
    FechaModificacion   DATETIME
);

CREATE TABLE EstadoCivil (
    IdEstadoCivil       INTEGER PRIMARY KEY AUTOINCREMENT,
    Nombre              VARCHAR(15) NOT NULL UNIQUE,
    Estado              VARCHAR(1) DEFAULT 'A',
    FechaCreacion       DATETIME NOT NULL DEFAULT (datetime('now','localtime')),
    FechaModificacion   DATETIME
);

CREATE TABLE EstadoAlquiler (
    IdEstadoAlquiler       INTEGER PRIMARY KEY AUTOINCREMENT,
    Nombre                 VARCHAR(20) NOT NULL UNIQUE,
    Estado                 VARCHAT(1) DEFAULT 'A',
    FechaCreacion          DATETIME NOT NULL DEFAULT (datetime('now','localtime')),
    FechaModificacion      DATETIME
);

CREATE TABLE Libro(
    IdLibro                  INTEGER PRIMARY KEY AUTOINCREMENT,
    Titulo                   VARCHAR(30) NOT NULL,
    NumeroEdicion            INTEGER NOT NULL,
    NumeroEjemplares         INTEGER NOT NULL,
    FechaPublicacion         VARCHAR(4) NOT NULL,
    Precio                   DECIMAL(10,2) NOT NULL,
    Estado                   VARCHAR(1) DEFAULT 'A',
    FechaCreacion            DATETIME NOT NULL DEFAULT (datetime('now','localtime')),
    FechaModificacion        DATETIME,
    IdGeneroLibro            INTEGER NOT NULL REFERENCES GeneroLibro(IdGeneroLibro),
    IdEditorial              INTEGER NOT NULL REFERENCES Editorial(IdEditorial),
    IdAutor                  INTEGER NOT NULL REFERENCES  Autor(IdAutor),
    CodigoBarras             VARCHAR(20) NOT NULL UNIQUE,
    CodigoISBN               VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE Portada(
    IdPortada                INTEGER PRIMARY KEY AUTOINCREMENT,
    Portada                  BLOB,
    IdLibro                  INTEGER NOT NULL REFERENCES Libro(IdLibro),
    Estado                   VARCHAR(1) DEFAULT 'A',
    FechaCreacion            DATETIME NOT NULL DEFAULT (datetime('now','localtime')),
    FechaModificacion        DATETIME
);

CREATE TABLE Cliente(
    IdCliente                INTEGER PRIMARY KEY AUTOINCREMENT,
    Nombre                   VARCHAR(30) NOT NULL,
    Apellido                 VARCHAR(30) NOT NULL,
    Cedula                   VARCHAR(10) NOT NULL UNIQUE,
    Telefono                 VARCHAR(10) NOT NULL,
    CorreoElectronico        VARCHAR(30) NOT NULL UNIQUE,
    Estado                   VARCHAR(1)  DEFAULT 'A',
    FechaCreacion            DATETIME    NOT NULL DEFAULT (datetime('now','localtime')),
    FechaModificacion        DATETIME,
    IdEstadoCivil            INTEGER NOT NULL REFERENCES EstadoCivil(IdEstadoCivil),
    IdSexo                   INTEGER NOT NULL REFERENCES Sexo(IdSexo)
);

CREATE TABLE Bibliotecario(
    IdBibliotecario          INTEGER PRIMARY KEY AUTOINCREMENT,
    Nombre                   VARCHAR(30) NOT NULL,
    Apellido                 VARCHAR(30) NOT NULL,
    Cedula                   VARCHAR(10) NOT NULL UNIQUE,
    Telefono                 VARCHAR(10)  NOT NULL UNIQUE,
    CorreoElectronico        VARCHAR(30) NOT NULL UNIQUE,
    Usuario                  VARCHAR(30) NOT NULL UNIQUE,
    Contrasenia              VARCHAR(30) NOT NULL UNIQUE,
    Estado                   VARCHAR(1)  DEFAULT 'A',
    FechaCreacion            DATETIME    NOT NULL DEFAULT (datetime('now','localtime')),
    FechaModificacion        DATETIME,
    IdEstadoCivil            INTEGER     NOT NULL REFERENCES EstadoCivil(IdEstadoCivil),
    IdSexo                   INTEGER     NOT NULL REFERENCES Sexo(IdSexo)
);

CREATE TABLE Alquiler (
    IdAlquiler          INTEGER PRIMARY KEY AUTOINCREMENT,
    Estado              VARCHAR(1) DEFAULT 'A',
    FechaAlquiler       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FechaDevolucion     DATETIME,
    FechaModificacion   DATETIME,
    IdLibro             INTEGER NOT NULL REFERENCES Libro(IdLibro),
    IdCliente           INTEGER NOT NULL REFERENCES Cliente(IdCliente),
    IdBibliotecario     INTEGER NOT NULL REFERENCES Bibliotecario(IdBibliotecario),
    IdEstadoAlquiler    INTEGER NOT NULL REFERENCES EstadoAlquiler(IdEstadoAlquiler)
);

CREATE TABLE Direccion(
    IdDireccion         INTEGER PRIMARY KEY AUTOINCREMENT,
    CallePrincipal      VARCHAR(30) NOT NULL,
    CalleSecundaria    VARCHAR(30) NOT NULL,
    Estado              VARCHAR(1)  DEFAULT 'A',
    FechaCreacion       DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FechaModificacion   DATETIME,
    IdCliente           INTEGER REFERENCES Cliente(IdCliente),
    IdBibliotecario     INTEGER REFERENCES Bibliotecario(IdBibliotecario)
);
