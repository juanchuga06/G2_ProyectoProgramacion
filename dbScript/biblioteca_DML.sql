-- database: ../database/biblioteca.db
/*
copyRight EPN 2025
Maye_Ana
DML : Llenar datos en la tablas correspondientes
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

INSERT INTO GeneroLibro(NombreGeneroLibro)VALUES
('Accion'),
('Terror'),
('Biografia'),
('Ciencia Ficcion'),
('Romance'),
('Infantil'),
('Historia'),
('Poesia'),
('Novela'),
('Politica');

INSERT INTO Editorial(NombreEditorial)VALUES
('Planeta'),
('Santillana'),
('Anaya'),
('SM'),
('Oxford'),
('Bruño'),
('Edebé'),
('Everest'),
('Alfaguara'),
('Siruela');

INSERT INTO Autor(NombreAutor)VALUES
('Gabriel Garcia Marquez'),
('J.R.R. Tolkien'),
('Jorge Luis Borges'),
('Mario Vargas Llosa'),
('Dan Brown'),
('Jane Austen'),
('Marco Aurelio'),
('J.K. Rowling'),
('John Steinbeck'),
('Ana Frank');

INSERT INTO Sexo(Nombre)VALUES
('Masculino'),
('Femenino'),
('Otros');

INSERT INTO EstadoCivil(Nombre)VALUES
('Soltero'),
('Casado'),
('Union Libre'),
('Divorciado'),
('Viudo');

INSERT INTO EstadoAlquiler(Nombre)VALUES
('Alquilado no devuelto'),
('Devuelto'),
('Invalido');

INSERT INTO Libro(Titulo, NumeroEdicion, NumeroEjemplares, FechaPublicacion, IdGeneroLibro, IdEditorial, IdAutor, CodigoBarras, CodigoISBN)VALUES
('El diario de Ana Frank',   '1',  '4', '1947', 3, 5, 10, '9789584277954', '978-95842-7795-4'),
('Matar a un ruiseñor',      '3',  '5', '1960', 5, 4,  3, '9788418271809', '978-84182-7180-9'),
('Orgullo y prejuicio',      '7',  '8', '1567', 8, 7,  5, '9786562390476', '978-65623-9047-6'),
('Don Quijote de la Mancha', '5',  '7', '1605', 3, 2,  4, '9785392561292', '978-53925-6129-2'),
('Dune',                     '9',  '4', '1965', 8, 8,  8, '9788641700442', '978-86417-0044-2'),
('El señor de los anillos',  '1',  '5', '1954', 4, 5,  2, '9781646228409', '978-16462-2840-9'),
('Los Fuegos del Hambre',    '4',  '3', '1988', 6, 7, 10, '9785907151031', '978-59071-5103-1'),
('El alquimista',            '6',  '2', '1988', 6, 4,  5, '9782545898687', '978-25458-8986-7'),
('1984',                     '9',  '4', '2000', 9,10,  8, '9787791409953', '978-77914-0995-3'),
('El código Da Vinci',       '7',  '1', '1777', 3, 1,  1, '9785958639663', '978-59586-3966-3');

INSERT INTO Bibliotecario(Nombre, Apellido, Cedula, Telefono, CorreoElectronico, Usuario, Contrasenia, IdEstadoCivil, IdSexo)VALUES
('Juan Andres',     'Lopez Arrelano', '1745896238',  '0962748173', 'andres.IO@gmail.com',        'Andres_Lopez', '123456Guapo',    1, 1),
('Maria Lourdes',   'Noboa Correa',   '1892733784',  '0927563833', 'maria_Luis@gmail.com',       'Noboa_Maria',  '199999Flor',     3, 2),
('Fernanda Ana',    'Quijote Mancha', '1783939394',  '0973647467', 'quijote.ana@gmail.com',      'Quijote_Ana',  'Lamasbella2025', 2, 2),
('Brayan Luis',     'Java Black',     '1997384848',  '0982838383', 'java_Liss@gmail.com',        'Java_Brayan',  'Programar67',    4, 1),
('Rodrigo Arnulfo', 'Perez Pallares', '1256304987',  '0995726485', 'Rodrigo.Perez@gmail.com',    'RodriP3',      '12356089',       1, 1),
('Maria Fernanda',  'Lopez Garcia',   '1758945632',  '0987451236', 'Maria.Lopez@gmail.com',      'MariaL89',     'maria1234',      2, 2),
('Carlos Andres',   'Mendoza Torres', '1102458974',  '0968745210', 'Carlos.Mendoza@gmail.com',   'CarlosM10',    'mendozA12',      1, 1),
('Sofia Alejandra', 'Vargas Salinas', '1803698745',  '0974521368', 'Sofia.Vargas@gmail.com',     'SofiV99',      'sofiaPass',      3, 2),
('Juan Sebastian',  'Ortega Mejia',   '1205789634',  '0958745236', 'Juan.Ortega@gmail.com',      'JuanSeba20',   'juanPass12',     1, 1),
('Camila Valentina','Fernandez Rios', '1702398547',  '0936587412', 'Camila.Fernandez@gmail.com', 'CamiFer23',    'camila321',      2, 2);



INSERT INTO Direccion(CallePrincipal, CalleSecundaria, IdCliente, IdBibliotecario)VALUES
('Capitan Geovanny Calles', 'Ulpiano Becerra',        1, null),
('Los Pinos',               'Gaspar de bein',      null,    2),
('Los buenos vecinos',      'La flor rosada',         9, null),
('Simon Bolivar',           'La parada 10',        null,    7),
('Los angeles',             'Manuela Canizares',      5, null),
('Marco Chiriboga',         'Oe6',                    8, null),
('Los Arupos',              'Martha Bucaram',      null,    5),
('Tomas de berlanga',       'Diego de Vasquez',       7, null),
('Los Shyrirs',             'Republica del salvador', null ,3),
('Sucre' ,                  'Espejo',                 null, 5);

INSERT INTO Cliente(Nombre, Apellido, Cedula, Telefono, CorreoElectronico, IdEstadoCivil, IdSexo)VALUES
('Mayerli Anahi',   'Chavez Toapanata', '1728678160', '0987090787', 'mayerlianachavez15@gmail.com', 1, 2),
('Luis Alberto',    'Gomez Martinez',   '1105987456', '0998745123', 'Luis.Gomez@gmail.com',         1, 1),
('Ana Beatriz',     'Hernandez Rivera', '1204789632', '0974123658', 'Ana.Hernandez@gmail.com',      2, 2),
('Miguel Angel',    'Rojas Castro',     '1802365478', '0965214783', 'Miguel.Rojas@gmail.com',       3, 1),
('Elena Patricia',  'Santos Vega',      '1506987452', '0958741236', 'Elena.Santos@gmail.com',       1, 2),
('Fernando Emilio', 'Vasquez Ortiz',    '1703456987', '0987452361', 'Fernando.Vasquez@gmail.com',   2, 1),
('Isabel Cristina', 'Ramirez Figueroa', '1608745236', '0945214783', 'Isabel.Ramirez@gmail.com',     3, 2),
('Carlos Daniel',   'Fuentes Proanio',  '1726399957', '0984772255', 'carlos-daniel2009@hotmail.com',1, 1),
('Luis Fernando',   'Salinas Vega',     '1845698732', '0945632187', 'luis.salinas@gmail.com',       3, 1),
('Camila Isabela',  'Torres Martinez',  '1234567890', '0974523689', 'camila.torres@gmail.com',      4, 2);

INSERT INTO Alquiler(FechaAlquiler, FechaDevolucion, IdLibro, IdCliente, IdBibliotecario, IdEstadoAlquiler)VALUES
('15/01/2025',         null,  2, 4, 1, 1),
('03/01/2025', '15/01/2025',  1, 1, 1, 2),
('27/01/2025', '28/01/2025',  3, 5, 2, 3),
('30/01/2025',         null,  7, 8, 9, 1),
('01/01/2025', '02/01/2025',  4, 2, 3, 2),
('25/01/2024', '04/03/2024',  5, 3, 1, 2),
('30/05/2024',         null,  2, 7, 3, 1),
('10/02/2024',         null,  5, 9, 5, 1),
('05/01/2025', '30/01/2025',  3, 2, 5, 2),
('03/02/2024', '10/02/2024',  5, 3, 2, 2);

INSERT INTO Venta (CantidadLibros, TotalLibros, Descuento, TotalPagar, IdLibro, IdCliente, IdBibliotecario) VALUES
(2, 50.00, 'Si', 45.00,   1, 3, 2),
(1, 30.00, 'No', 30.00,   2, 5, 1),
(3, 75.00, 'Si', 65.00,   3, 7, 4),
(5, 120.00, 'Si', 105.00, 4, 2, 3),
(4, 90.00, 'No', 90.00,   5, 6, 5),
(2, 40.00, 'No', 40.00,   6, 1, 2),
(1, 20.00, 'Si', 18.00,   7, 8, 3),
(6, 150.00, 'Si', 130.00, 8, 4, 1),
(3, 70.00, 'No', 70.00,   9, 9, 4),
(2, 45.00, 'Si', 40.00,   10, 10, 5);


INSERT INTO Factura(DireccionLocal, TelefonoLocal, CorreoElectronico, FechaEmision, NumeroFactura, IdCliente, DetallesCompra, IdVenta)VALUES
('Av. Amazonas 123',        '0998745632', 'contacto@libreriaquito.com',       '2025-01-15', 'FAC-2025-001', 3, '2 libros de literatura',  1),
('Calle 10 de Agosto 456',  '0987456123', 'info@bibliotecacentral.com',       '2025-01-20', 'FAC-2025-002', 5, '1 libro de historia',     2),
('Av. Naciones Unidas 789', '0974123658', 'ventas@librosguayaquil.com',       '2025-01-25', 'FAC-2025-003', 7, '3 libros de ciencia',     3),
('Calle Sucre 321',         '0965234789', 'facturacion@bibliotecacuenca.com', '2025-02-01', 'FAC-2025-004', 2, '5 libros de matematicas', 4),
('Av. Simon Bolivar 852',   '0958741236', 'contacto@libreriacuenca.com',      '2025-02-05', 'FAC-2025-005', 6, '4 libros de arte',        5),
('Calle Rocafuerte 741',    '0945214783', 'info@bibliotecaesmeraldas.com',    '2025-02-10', 'FAC-2025-006', 1, '2 libros de filosofia',   6),
('Av. Pichincha 369',       '0936587412', 'ventas@librosloja.com',            '2025-02-15', 'FAC-2025-007', 8, '1 libro de economia',     7),
('Calle Olmedo 258',        '0923658741', 'facturas@bibliotecamanabi.com',    '2025-02-18', 'FAC-2025-008', 4, '6 libros de tecnologia',  8),
('Av. 6 de Diciembre 147',  '0914785236', 'info@libreriatunja.com',           '2025-02-20', 'FAC-2025-009', 9, '3 libros de ingenieria',  9),
('Calle Bolivar 963',       '0902365874', 'contacto@bibliotecapasto.com',     '2025-02-25', 'FAC-2025-010', 10, '2 libros de medicina',  10);


SELECT
    l.IdLibro               as  Codigo,
    l.Titulo,
    l.NumeroEdicion,
    l.NumeroEjemplares,
    l.FechaPublicacion,
    l.Estado,
    l.FechaCreacion,
    l.FechaModificacion,
    gl.NombreGeneroLibro    as Genero,
    e.NombreEditorial       as Editorial,
    a.NombreAutor           as NombreAutor,
    l.CodigoBarras,
    l.CodigoISBN
FROM Libro              as  l
INNER JOIN GeneroLibro  as  gl ON l.IdGeneroLibro = gl.IdGeneroLibro
INNER JOIN Editorial    as  e  ON l.IdEditorial = e.IdEditorial
INNER JOIN Autor        as  a  ON l.IdAutor = a.IdAutor
WHERE l.Estado = 'A';


SELECT
    c.IdCliente              as  Codigo,
    c.Nombre,
    c.Apellido,
    c.Cedula,
    c.Telefono,
    c.CorreoElectronico,
    c.Estado,
    c.FechaCreacion,
    c.FechaModificacion,
    ec.Nombre                as EstadoCivil,
    s.Nombre                 as Sexo
FROM Cliente             as c
INNER JOIN EstadoCivil   as ec   ON c.IdEstadoCivil = ec.IdEstadoCivil
INNER JOIN Sexo          as s    ON c.IdSexo = s.IdSexo
WHERE c.Estado = 'A';


SELECT
    d.IdDireccion               as Codigo,
    d.CallePrincipal,
    d.CalleSecundaria,
    d.Estado,
    d.FechaCreacion,
    d.FechaModificacion,
    c.Nombre                    as Cliente,
    b.Nombre                    as Bibliotecario
FROM Direccion as d
LEFT JOIN Cliente as c ON d.IdCliente = c.IdCliente
LEFT JOIN Bibliotecario as b ON d.IdBibliotecario = b.IdBibliotecario
WHERE d.Estado = 'A';


SELECT
    b.IdBibliotecario       as Codigo,
    b.Nombre,
    b.Apellido,
    b.Cedula,
    b.Telefono,
    b.CorreoElectronico,
    b.Usuario,
    b.Contrasenia,
    b.Estado,
    b.FechaCreacion,
    b.FechaModificacion,
    ec.Nombre               as EstadoCivil,
    s.Nombre                as Sexo
FROM Bibliotecario b
INNER JOIN EstadoCivil as ec ON b.IdEstadoCivil = ec.IdEstadoCivil
INNER JOIN Sexo as s ON b.IdSexo = s.IdSexo
WHERE b.Estado = 'A';


SELECT
    a.IdAlquiler        as Codigo,
    a.Estado,
    a.FechaAlquiler,
    a.FechaDevolucion,
    a.FechaModificacion,
    l.Titulo            as Libro,
    c.Nombre            as Cliente,
    b.Nombre            as Bibliotecario,
    ea.Nombre           as EstadoAlquiler
FROM Alquiler as a
INNER JOIN Libro as l ON a.IdLibro = l.IdLibro
INNER JOIN Cliente as c ON a.IdCliente = c.IdCliente
INNER JOIN Bibliotecario as b ON a.IdBibliotecario = b.IdBibliotecario
INNER JOIN EstadoAlquiler as ea ON a.IdEstadoAlquiler = ea.IdEstadoAlquiler
WHERE a.Estado = 'A';


SELECT
    v.IdVenta           as codigo,
    v.CantidadLibros,
    v.TotalLibros,
    v.Descuento,
    v.TotalPagar,
    v.Estado,
    v.FechaVenta,
    v.FechaDevolucion,
    v.FechaCreacion,
    v.FechaModificacion,
    l.IdLibro            as Libro,
    c.Nombre             as Nombre,
    c.Apellido           as Apellido,
    c.Cedula             as Cedula,
    c.Telefono           as Telefono,
    c.CorreoElectronico  as CorreoElectronico,
    b.Nombre             as Bibliotecario
FROM Venta as v
INNER JOIN Libro as l on v.IdLibro = l.IdLibro
INNER JOIN Cliente as c on v.IdCliente = c.IdCliente
INNER JOIN Bibliotecario as b on v.IdBibliotecario = b.IdBibliotecario
WHERE v.Estado = 'A';



SELECT
    f.IdFactura          as Codigo,
    f.DireccionLocal,
    f.TelefonoLocal,
    f.CorreoElectronico,
    f.FechaEmision,
    f.NumeroFactura,
    c.Nombre             as Nombre,
    c.Apellido           as Apellido,
    c.Cedula             as Cedula,
    c.Telefono           as Telefono,
    c.CorreoElectronico  as CorreoElectronico,
    f.DetallesCompra,
    v.CantidadLibros     as Detalles,
    v.TotalLibros        as Subtotal,
    v.Descuento          as Descuento,
    v.TotalPagar         as TotalPagar
FROM Factura as f
INNER JOIN Venta as v on f.IdVenta = v.IdVenta
INNER JOIN Cliente as c on v.IdCliente = c.IdCliente;

SELECT IdSexo
    ,Nombre
    ,Estado
    ,FechaCreacion
    ,FechaModificacion
    FROM Sexo
    WHERE   Estado ='A';

SELECT IdAutor
    ,NombreAutor
    ,Estado
    ,FechaCreacion
    ,FechaModificacion
FROM    Autor;


SELECT * FROM Sexo WHERE Estado = ?;
