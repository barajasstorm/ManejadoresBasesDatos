DROP TABLE IF EXISTS ventasproductos;
DROP TABLE IF EXISTS ventas;
DROP TABLE IF EXISTS cortes;
DROP TABLE IF EXISTS usuarios;
DROP TABLE IF EXISTS compras;
DROP TABLE IF EXISTS productos;
DROP TABLE IF EXISTS clientes;
DROP TABLE IF EXISTS proveedores;
DROP TABLE IF EXISTS ticketformato;


CREATE TABLE ticketformato (
	pk_ticketformatoid SERIAL NOT NULL PRIMARY KEY,
	linea1 varchar(100),
	linea2 varchar(100),
	linea3 varchar(100),
	linea4 varchar(100),
	linea5 varchar(100),
	linea6 varchar(100),
	linea7 varchar(100)
);

CREATE TABLE proveedores (
	pk_proveedorid SERIAL NOT NULL PRIMARY KEY,
	nombre VARCHAR (30) NOT NULL,
	apellidopaterno VARCHAR (30) NOT NULL,
	apellidomaterno VARCHAR (30) NOT NULL,
	rfc VARCHAR (30) NOT NULL,
	telefono VARCHAR (10) NOT NULL	
);

CREATE TABLE clientes (
	pk_clienteid SERIAL NOT NULL PRIMARY KEY,
	nombre VARCHAR (30) NOT NULL,
	apellidopaterno VARCHAR (30) NOT NULL,
	apellidomaterno VARCHAR (30) NOT NULL,
	rfc VARCHAR (30) NOT NULL,
	telefono VARCHAR (10) NOT NULL	
);

CREATE TABLE productos (
	pk_productoid SERIAL NOT NULL PRIMARY KEY,
	nombre VARCHAR (30) NOT NULL,
	preciocompra REAL NOT NULL,
	precioventa REAL NOT NULL,
	existencias INT NOT NULL,
	stockminimo INT NOT NULL,
	activo INT NOT NULL
);


CREATE TABLE compras (
	pk_compraid SERIAL NOT NULL PRIMARY KEY,
	fk_proveedorid INT NOT NULL,
	fk_productoid INT NOT NULL,
	cantidad INT NOT NULL,
	FOREIGN KEY (fk_proveedorid) REFERENCES proveedores(pk_proveedorid),
	FOREIGN KEY (fk_productoid) REFERENCES productos(pk_productoid)
);

CREATE TABLE usuarios (
	pk_usuarioid SERIAL NOT NULL PRIMARY KEY,
	username VARCHAR (30) NOT NULL,
	password VARCHAR (30) NOT NULL,
	nombre VARCHAR (30) NOT NULL,
	apellidopaterno VARCHAR (30) NOT NULL,
	apellidomaterno VARCHAR (20) NOT NULL
);

CREATE TABLE cortes (
	pk_corteid SERIAL NOT NULL PRIMARY KEY,
	ventastotales REAL,
	cantidadtickets INT,
	productosvendidos INT
);

CREATE TABLE ventas (
	pk_ventaid SERIAL NOT NULL PRIMARY KEY,
	fk_usuarioid INT NOT NULL,
	fk_corteid INT NOT NULL,
	fk_clienteid INT,
	numeroticket INT NOT NULL,
	dia INT NOT NULL,
	mes INT NOT NULL,
	ano INT NOT NULL,
	hora VARCHAR (10) NOT NULL,
	cantarticulos INT NOT NULL,
	total REAL NOT NULL,
	FOREIGN KEY (fk_usuarioid) REFERENCES usuarios(pk_usuarioid),
	FOREIGN KEY (fk_corteid) REFERENCES cortes(pk_corteid),
	FOREIGN KEY (fk_clienteid) REFERENCES clientes(pk_clienteid)
);

CREATE TABLE ventasproductos (
	pk_ventaproductoid SERIAL NOT NULL PRIMARY KEY,
	fk_ventaid INT NOT NULL,
	fk_productoid INT NOT NULL,
	cantidad INT NOT NULL,
	importeproducto REAL NOT NULL,
	FOREIGN KEY (fk_ventaid) REFERENCES ventas(pk_ventaid),
	FOREIGN KEY (fk_productoid) REFERENCES productos(pk_productoid)
);







INSERT INTO usuarios (username,password,nombre,apellidopaterno, apellidomaterno) 
	VALUES ('jbarajas','12345','Juan','Barajas','Martinez');

INSERT INTO usuarios (username,password,nombre,apellidopaterno, apellidomaterno) 
	VALUES ('gmartinez','12345','Gerardo','Martinez','Martinez');


INSERT INTO productos (nombre,preciocompra,precioventa,existencias,stockminimo,activo) 
	VALUES ('Coca Cola',10.00,15.00,100,5,1);

INSERT INTO productos (nombre,preciocompra,precioventa,existencias,stockminimo,activo) 
	VALUES ('Pepsi',10.00,15.00,100,5,1);

INSERT INTO productos (nombre,preciocompra,precioventa,existencias,stockminimo,activo) 
	VALUES ('Arroz',2.00,5.00,100,5,1);

INSERT INTO productos (nombre,preciocompra,precioventa,existencias,stockminimo,activo) 
	VALUES ('Leche',10,15.00,20,5,1);

INSERT INTO productos (nombre,preciocompra,precioventa,existencias,stockminimo,activo) 
	VALUES ('Jugo',10,15.00,20,5,1);


INSERT INTO clientes (nombre,apellidopaterno,apellidomaterno,rfc,telefono) 
	VALUES ('Angelina','Jolie','Jolie','AJJ1111111111',3333000000);

INSERT INTO clientes (nombre,apellidopaterno,apellidomaterno,rfc,telefono) 
	VALUES ('Steve','Jobs','Jobs','SJJ2222222222',3333000000);


INSERT INTO proveedores (nombre,apellidopaterno,apellidomaterno,rfc,telefono) 
	VALUES ('Joaquin','Guzman','Loera','JGL11111111111',3333000000);

INSERT INTO proveedores (nombre,apellidopaterno,apellidomaterno,rfc,telefono) 
	VALUES ('Pablo','Excobar','Gaviria','PEG2222222222',3333000000);


INSERT INTO ticketformato (linea1,linea2,linea3,linea4,linea5,linea6,linea7) 
	VALUES ('Mi Tiendita','Direccion 123 Col. Colonia','(123) 000 0000','RFC01234567','', 'Gracias por su compra', 'www.juanbarajas.com');

INSERT INTO cortes (ventastotales,cantidadtickets,productosvendidos) 
	VALUES (NULL, NULL, NULL);

INSERT INTO ventas (fk_usuarioid, fk_corteid, fk_clienteid, numeroticket, dia, mes, ano, hora, cantarticulos, total) 
	VALUES (1, 1, 1, 1000, 11, 08, 2017, '8:52 PM', 2, 100.00);

INSERT INTO ventas (fk_usuarioid, fk_corteid, fk_clienteid, numeroticket, dia, mes, ano, hora, cantarticulos, total) 
	VALUES (1, 1, 1, 1001, 11, 08, 2017, '9:00 PM', 2, 100.00);

INSERT INTO ventasproductos (fk_ventaid, fk_productoid, cantidad, importeproducto) 
	VALUES (1, 1, 2, 100.00);

INSERT INTO ventasproductos (fk_ventaid, fk_productoid, cantidad, importeproducto) 
	VALUES (2, 1, 2, 100.00);