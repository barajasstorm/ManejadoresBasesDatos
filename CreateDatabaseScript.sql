DROP TABLE IF EXISTS usuarios;
DROP TABLE IF EXISTS productos;
DROP TABLE IF EXISTS clientes;
DROP TABLE IF EXISTS proveedores;
DROP TABLE IF EXISTS ventas;
DROP TABLE IF EXISTS ventasproductos;
DROP TABLE IF EXISTS compras;
DROP TABLE IF EXISTS ticketformato;

CREATE TABLE usuarios (
	pk_usuarioid SERIAL NOT NULL PRIMARY KEY,
	username varchar (30) NOT NULL,
	password varchar (30) NOT NULL,
	nombre varchar (30) NOT NULL,
	apellidopaterno varchar (30) NOT NULL,
	apellidomaterno varchar (20) NOT NULL
);

CREATE TABLE productos (
	pk_productoid SERIAL NOT NULL PRIMARY KEY,
	nombre varchar (30) NOT NULL,
	preciocompra REAL NOT NULL,
	precioventa REAL NOT NULL,
	existencias INT NOT NULL,
	stockminimo INT NOT NULL,
	activo INT NOT NULL
);

CREATE TABLE clientes (
	pk_clienteid SERIAL NOT NULL PRIMARY KEY,
	nombre varchar (30) NOT NULL,
	apellidopaterno varchar (30) NOT NULL,
	apellidomaterno varchar (30) NOT NULL,
	rfc varchar (30) NOT NULL,
	telefono INT NOT NULL	
);

CREATE TABLE proveedores (
	pk_proveedorid SERIAL NOT NULL PRIMARY KEY,
	nombre varchar (30) NOT NULL,
	rfc varchar (30) NOT NULL,
	telefono INT NOT NULL,
	ciudad varchar (30) NOT NULL,
	estado varchar (30) NOT NULL	
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
	hora varchar (10) NOT NULL,
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

CREATE TABLE compras (
	pk_compraid SERIAL NOT NULL PRIMARY KEY,
	fk_proveedorid INT NOT NULL,
	fk_productoid INT NOT NULL,
	cantidad INT NOT NULL,
	FOREIGN KEY (fk_proveedorid) REFERENCES proveedores(pk_proveedorid),
	FOREIGN KEY (fk_productoid) REFERENCES productos(pk_productoid)
);

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


insert into usuarios (username,password,nombre,apellidopaterno, apellidomaterno) 
	values ('jbarajas','12345','Juan','Barajas','Martinez');

insert into usuarios (username,password,nombre,apellidopaterno, apellidomaterno) 
	values ('gmartinez','12345','Gerardo','Martinez','Martinez');


insert into productos (nombre,preciocompra,precioventa,existencias,stockminimo,activo) 
	values ('Coca Cola',10.00,15.00,100,5,1);

insert into productos (nombre,preciocompra,precioventa,existencias,stockminimo,activo) 
	values ('Pepsi',10.00,15.00,100,5,1);

insert into productos (nombre,preciocompra,precioventa,existencias,stockminimo,activo) 
	values ('Arroz',2.00,5.00,100,5,1);

insert into productos (nombre,preciocompra,precioventa,existencias,stockminimo,activo) 
	values ('iPhone',10000.00,15000.00,20,5,1);

insert into productos (nombre,preciocompra,precioventa,existencias,stockminimo,activo) 
	values ('Mac Book Pro',20000.00,25000.00,20,5,1);


insert into clientes (nombre,apellidopaterno,apellidomaterno,rfc,telefono) 
	values ('Angelina','Jolie','Jolie','XXXX0000X0X0X0X',3333000000);

insert into clientes (nombre,apellidopaterno,apellidomaterno,rfc,telefono) 
	values ('Steve','Jobs','Jobs','XXXX0000X0X0X0X',3333000000);


insert into ticketformato (linea1,linea2,linea3,linea4,linea5,linea6,linea7) 
	values ('Mi Tiendita','Direccion 123 Col. Colonia','(123) 000 0000','RFC01234567','', 'Gracias por su compra', 'www.juanbarajas.com');


