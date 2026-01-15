-- database: storage\Databases\ant.sqlite
DROP TABLE IF EXISTS qmAntCiberDron;
DROP TABLE IF EXISTS qmHormiga;
DROP TABLE IF EXISTS qmSexo;
DROP TABLE IF EXISTS qmEstado;
DROP TABLE IF EXISTS qmHormigaTipo;
DROP TABLE IF EXISTS qmAlimentoTipo; 
 
CREATE TABLE qmAlimentoTipo(
    IdAlimentoTipo INTEGER PRIMARY KEY AUTOINCREMENT
    ,Nombre         VARCHAR(15)  NOT NULL UNIQUE
    ,Descripcion    VARCHAR(100) NULL
    ,Estado         VARCHAR(1)  NOT NULL DEFAULT 'A'
    ,FechaCreacion  DATETIME NOT NULL  DEFAULT (datetime('now','localtime'))
    ,FechaModifica  DATETIME NOT NULL  DEFAULT (datetime('now','localtime'))
);
CREATE TABLE qmHormigaTipo (
     IdHormigaTipo  INTEGER PRIMARY KEY AUTOINCREMENT
    ,Nombre         VARCHAR(15)  NOT NULL UNIQUE
    ,Descripcion    VARCHAR(100) NULL
    ,Estado         VARCHAR(1)  NOT NULL DEFAULT 'A'
    ,FechaCreacion  DATETIME NOT NULL  DEFAULT (datetime('now','localtime'))
    ,FechaModifica  DATETIME NOT NULL  DEFAULT (datetime('now','localtime'))
);
CREATE TABLE qmEstado (
     IdEstado       INTEGER PRIMARY KEY AUTOINCREMENT
    ,Nombre         VARCHAR(15)  NOT NULL UNIQUE
    ,Descripcion    VARCHAR(100) NULL
    ,Estado         VARCHAR(1)  NOT NULL DEFAULT 'A'
    ,FechaCreacion  DATETIME NOT NULL  DEFAULT (datetime('now','localtime'))
    ,FechaModifica  DATETIME NOT NULL  DEFAULT (datetime('now','localtime'))
);
CREATE TABLE qmSexo (
     IdSexo         INTEGER PRIMARY KEY AUTOINCREMENT
    ,Nombre         VARCHAR(15)  NOT NULL UNIQUE
    ,Descripcion    VARCHAR(100) NULL
    ,Estado         VARCHAR(1)  NOT NULL DEFAULT 'A'
    ,FechaCreacion  DATETIME NOT NULL  DEFAULT (datetime('now','localtime'))
    ,FechaModifica  DATETIME NOT NULL  DEFAULT (datetime('now','localtime'))
);
CREATE TABLE qmHormiga (
     IdHormiga      INTEGER PRIMARY KEY AUTOINCREMENT
    ,IdHormigaTipo  INTEGER NOT NULL REFERENCES qmHormigaTipo (IdHormigaTipo)
    ,IdSexo         INTEGER NOT NULL REFERENCES qmSexo        (IdSexo)
    ,IdEstado       INTEGER NOT NULL REFERENCES qmEstado      (IdEstado)
    ,Nombre         VARCHAR(20) NOT NULL  UNIQUE
    ,Descripcion    VARCHAR(20) NULL

    ,Estado         VARCHAR(1)  NOT NULL DEFAULT 'A'
    ,FechaCreacion  DATETIME NOT NULL  DEFAULT (datetime('now','localtime'))
    ,FechaModifica  DATETIME NOT NULL  DEFAULT (datetime('now','localtime'))
);
CREATE TABLE qmAntCiberDron (
     IdAntCiberDron     INTEGER PRIMARY KEY AUTOINCREMENT
    ,Serie              VARCHAR(10) NOT NULL  UNIQUE
    ,Estado             VARCHAR(1)  NOT NULL DEFAULT 'A'
    ,FechaCreacion      DATETIME NOT NULL  DEFAULT (datetime('now','localtime'))
    ,FechaModifica      DATETIME NOT NULL  DEFAULT (datetime('now','localtime'))
);

-- Insert initial data into qmSexo table
INSERT INTO qmSexo 
 (Nombre, Descripcion)  VALUES 
 ('Macho'  ,' masculino')
,('Hembra' ,' femenina') 
,('Hibrido',' Hibrido')
,('Asexual',' Asexual');

INSERT INTO qmAlimentoTipo
 (Nombre, Descripcion)  VALUES
 ('Carnivoro'   ,'Azucar')
,('Herbívoro' ,'Proteina')
,('Omnívoro'  ,'Lipidico')
,('Nectarivoro','Vitaminico');

INSERT INTO qmHormigaTipo
 (Nombre, Descripcion)  VALUES 
 ('Larva'     ,' en etapa de larva')
,('Soldado'   ,' encargada de la defensa')
,('Rastreadora',' encargada de buscar alimento')
,('Reina'     ,' encargada de la reproducción')
,('Zángano'   ,' macho para reproducción');

INSERT INTO qmEstado
 (Nombre, Descripcion)  VALUES 
 ('Vive' ,' está viva'),
 ('Muere',' ha muerto'),
 ('Finge',' su muerte');

INSERT INTO qmAntCiberDron
(Serie)     VALUES 
('S001'),
('S002'),
('S003'),
('S004');
    
INSERT INTO qmHormiga
(IdHormigaTipo, IdSexo, IdEstado, Nombre, Descripcion) values 
(1, 2, 1, 'Hormiga1', 'Primera hormiga'),
(2, 1, 1, 'Hormiga2', 'Segunda hormiga'),
(3, 2, 1, 'Hormiga3', 'Tercera hormiga'),
(4, 1, 1, 'Hormiga4', 'Cuarta hormiga');

select * from qmSexo;
select * from qmHormigaTipo;
select * from qmEstado;
select * from qmAlimentoTipo;
SELECT * FROM qmHormiga;


DROP VIEW IF EXISTS vwHormiga;

CREATE VIEW vwHormiga AS
SELECT 
     H.IdHormiga
    ,HT.Nombre AS Tipo
    ,S.Nombre  AS Sexo
    ,E.Nombre  AS EstadoHormiga
    ,H.Nombre  AS Nombre
    ,H.Descripcion
    ,H.Estado
    ,H.FechaCreacion
    ,H.FechaModifica
FROM qmHormiga H
JOIN qmHormigaTipo    HT ON H.IdHormigaTipo = HT.IdHormigaTipo
JOIN qmSexo                   S   ON H.IdSexo        = S.IdSexo
JOIN qmEstado               E    ON H.IdEstado      = E.IdEstado
WHERE H.Estado = 'A';

SELECT * FROM vwHormiga;

SELECT IdHormiga
,Tipo
,Sexo
,EstadoHormiga
,Nombre
,Descripcion
,Estado
,FechaCreacion
,FechaModifica  
FROM vwHormiga;


SELECT * FROM qmHormiga;



UPDATE qmEstado   SET Nombre = 'VIVA'
WHERE IdEstado = 1;

UPDATE qmEstado   SET Nombre = 'MUERTA'
WHERE IdEstado = 2;

UPDATE qmEstado   SET qmEstado = 'X'
WHERE IdEstado = 3;

select * from qmEstado;