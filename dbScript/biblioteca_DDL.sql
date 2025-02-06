-- database: ../database/biblioteca.db
/*
copyRight EPN 2025
Maye_Ana
DDL : Definicion de las tablas de la base de datos
*/
DROP TABLE IF EXISTS Factura;
DROP TABLE IF EXISTS Venta;
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
    Estado                   VARCHAR(1) DEFAULT 'A',
    FechaCreacion            DATETIME NOT NULL DEFAULT (datetime('now','localtime')),
    FechaModificacion        DATETIME,
    IdGeneroLibro            INTEGER NOT NULL REFERENCES GeneroLibro(IdGeneroLibro),
    IdEditorial              INTEGER NOT NULL REFERENCES Editorial(IdEditorial),
    IdAutor                  INTEGER NOT NULL REFERENCES  Autor(IdAutor),
    CodigoBarras             VARCHAR(20) NOT NULL UNIQUE,
    CodigoISBN               VARCHAR(20) NOT NULL UNIQUE
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
    FechaAlquiler       DATETIME NOT NULL DEFAULT (datetime('now','localtime')),
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
    CalleSecundaria     VARCHAR(30) NOT NULL,
    Estado              VARCHAR(1)  DEFAULT 'A',
    FechaCreacion       DATETIME NOT NULL DEFAULT (datetime('now','localtime')),
    FechaModificacion   DATETIME,
    IdCliente           INTEGER REFERENCES Cliente(IdCliente),
    IdBibliotecario     INTEGER REFERENCES Bibliotecario(IdBibliotecario)
);

CREATE TABLE Venta (
    IdVenta             INTEGER PRIMARY KEY AUTOINCREMENT,
    CantidadLibros      INTEGER NOT NULL,
    TotalLibros         DECIMAL(10, 2) NOT NULL,
    Descuento           BOOLEAN NOT NULL,
    TotalPagar          DECIMAL(10, 2) NOT NULL,
    Estado              VARCHAR(1) DEFAULT 'A',
    FechaVenta          DATETIME NOT NULL DEFAULT (datetime('now','localtime')),
    FechaModificacion   DATETIME,
    IdLibro             INTEGER NOT NULL REFERENCES Libro(IdLibro),
    IdCliente           INTEGER NOT NULL REFERENCES Cliente(IdCliente),
    IdBibliotecario     INTEGER NOT NULL REFERENCES Bibliotecario(IdBibliotecario)
);

CREATE TABLE Factura (
    IdFactura             INTEGER PRIMARY KEY AUTOINCREMENT,
    DireccionLocal        VARCHAR(50) NOT NULL,
    TelefonoLocal         VARCHAR(10) NOT NULL,
    CorreoElectronico     VARCHAR(30) NOT NULL,
    FechaEmision          DATETIME NOT NULL DEFAULT (datetime('now','localtime')),
    NumeroFactura         VARCHAR(12) NOT NULL,
    DetallesCompra        VARCHAR(150) NOT NULL,
    IdCliente             INTEGER NOT NULL,
    IdVenta               INTEGER NOT NULL,
    FOREIGN KEY (IdCliente)  REFERENCES Cliente(IdCliente),
    FOREIGN KEY (IdVenta)    REFERENCES Venta(IdVenta)
    CONSTRAINT Factura_Datos UNIQUE (DireccionLocal, TelefonoLocal, CorreoElectronico)
);


