use HotelManagement

-- TABLAS
CREATE TABLE T_Alquileres
(
	Cod_Alq              NUMBER NOT NULL ,
	Cos_Alq              INTEGER NULL ,
	Obs_Alq              VARCHAR2(250) NULL ,
	Fec_Ent              DATE NULL ,
	Fec_Sal              DATE NULL ,
	Hor_Sal              TIMESTAMP(6) NULL ,
	Hor_Ent              TIMESTAMP NULL ,
	Cod_Hab              NUMBER NULL ,
	COD_CLI              NUMBER NULL ,
	COD_REG              NUMBER NULL ,
	Est_Alq              VARCHAR2(1) NULL 
);
ALTER TABLE T_Alquileres
	ADD  PRIMARY KEY (Cod_Alq);

CREATE TABLE T_Cargo
(
	Cod_Car              NUMBER NOT NULL ,
	Nom_Car              VARCHAR2(80) NULL ,
	Est_Car              VARCHAR2(1) NULL 
);
ALTER TABLE T_Cargo
	ADD  PRIMARY KEY (Cod_Car);

CREATE TABLE T_Cliente
(
	COD_CLI              NUMBER NOT NULL,
    DNI_Cli              CHAR(8) NULL ,
	Nom_Cli              VARCHAR2(80) NULL ,
	Ape_Cli              VARCHAR2(80) NULL ,
	idUbi                INTEGER NULL ,
    NAC_CLI               VARCHAR2(80) NULL ,
	Dir_Cli              VARCHAR2(250) NULL ,
	Est_Cli              VARCHAR2(1) NULL 
);
ALTER TABLE T_Cliente
	ADD  PRIMARY KEY (COD_CLI);

CREATE TABLE T_Habitaciones
(
	Cod_Hab              NUMBER NOT NULL ,
	Num_Hab              INTEGER NULL ,
	Estado_Hab           VARCHAR2(1) NULL ,
	Des_Est              VARCHAR2(250) NULL ,
	Cos_Hab              INTEGER NULL ,
	Des_Hab              VARCHAR2(250) NULL ,
	Cod_Tip              NUMBER NULL ,
	Est_Hab              VARCHAR2(1) NULL 
);
ALTER TABLE T_Habitaciones
	ADD  PRIMARY KEY (Cod_Hab);

CREATE TABLE T_Registrador
(
	COD_REG              NUMBER NOT NULL,
    DNI_Reg              CHAR(8) NULL ,
	Nom_Reg              VARCHAR2(80) NULL ,
	Ape_Reg              VARCHAR2(80) NULL ,
	idUbi                INTEGER NULL ,
	Cod_Car              NUMBER NULL ,
	Niv_Reg              NUMBER NULL ,
	Pas_Reg              VARCHAR2(80) NULL ,
	Est_Reg              VARCHAR2(1) NULL 
);
ALTER TABLE T_Registrador
	ADD  PRIMARY KEY (COD_REG);
    

CREATE TABLE T_TipoHabitacion
(
	Cod_Tip              NUMBER NOT NULL ,
	Tip_Hab              VARCHAR2(80) NULL ,
	Est_Tip              VARCHAR2(1) NULL 
);
ALTER TABLE T_TipoHabitacion
	ADD  PRIMARY KEY (Cod_Tip);

CREATE TABLE Ubigeo
(
	idUbi                INTEGER NOT NULL ,
	DepUbi               VARCHAR2(50) NULL ,
	provUbi              VARCHAR2(50) NULL ,
	distUbi              VARCHAR2(50) NULL 
);
ALTER TABLE Ubigeo
	ADD  PRIMARY KEY (idUbi);

-- RELACIONES 

ALTER TABLE T_Alquileres
	ADD (FOREIGN KEY (Cod_Hab) REFERENCES T_Habitaciones (Cod_Hab) ON DELETE SET NULL);
    
ALTER TABLE T_Alquileres
	ADD (FOREIGN KEY (COD_CLI) REFERENCES T_Cliente (COD_CLI) ON DELETE SET NULL);
    
ALTER TABLE T_Alquileres
	ADD (FOREIGN KEY (COD_REG) REFERENCES T_Registrador (COD_REG) ON DELETE SET NULL);
    
ALTER TABLE T_Cliente
	ADD (FOREIGN KEY (idUbi) REFERENCES Ubigeo (idUbi) ON DELETE SET NULL);
    
ALTER TABLE T_Habitaciones
	ADD (FOREIGN KEY (Cod_Tip) REFERENCES T_TipoHabitacion (Cod_Tip) ON DELETE SET NULL);
    
ALTER TABLE T_Registrador
	ADD (FOREIGN KEY (idUbi) REFERENCES Ubigeo (idUbi) ON DELETE SET NULL);

ALTER TABLE T_Registrador
	ADD (FOREIGN KEY (Cod_Car) REFERENCES T_Cargo (Cod_Car) ON DELETE SET NULL);
    
-- PRUEBAS
    SELECT * FROM T_CLIENTE;
    
-- LISTAR
    select Dni_Cli, Nom_Cli,Ape_Cli,IdUbi,Dir_Cli,NAC_CLI From T_CLIENTE;

    
    Select Dni_Cli, Nom_Cli,Ape_Cli,(Ubigeo.DEPUBI || '-' || Ubigeo.PROVUBI || '-' ||Ubigeo.DISTUBI) as NomUbigeo from T_CLIENTE 
    inner join Ubigeo on T_CLIENTE.IDUBI = Ubigeo.IDUBI
    where EST_Cli LIKE 'A' ORDER BY Ape_Cli ASC;
    
    Select DNI_CLI, NOM_CLI,APE_CLI,(Ubigeo.DEPUBI || '-' || Ubigeo.PROVUBI || '-' ||Ubigeo.DISTUBI) as NomUbigeo,DIR_CLI,NAC_CLI from T_CLIENTE 
                        inner join Ubigeo on T_CLIENTE.IDUBI = Ubigeo.IDUBI
                    where EST_Cli LIKE 'A' ORDER BY Ape_Cli ASC
    
SELECT * FROM T_CLIENTE WHERE Est_cli like 'A';
    
-- LOGIN    
    Select DNI_REG, NOM_REG, APE_REG,Niv_Reg  From T_REGISTRADOR where DNI_REG like '75873508' and Pas_Reg like '12345' and Est_Reg like 'A';
    
-- ELIMINAR 
    Update T_CLIENTE set EST_CLI='I' where DNI_CLI = ?
    

