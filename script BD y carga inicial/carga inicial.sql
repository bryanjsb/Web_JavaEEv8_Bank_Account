
USE `eif209_2001_p01` ;

INSERT INTO `eif209_2001_p01`.`moneda`
	(`nombre`, `descripcion`, `simbolo`, `tipo_cambio_compra`, `tipo_cambio_venta`)
	VALUES
		('CRC', 'Colón', '₡', '1.0', '1.0'),
		('USD', 'Dólar EEUU', '$', '560.0', '570.0'),
		('EUR', 'Euro', '€', '700.0', '720.0')
	;
SELECT * FROM  `eif209_2001_p01`.`moneda`;

INSERT INTO `eif209_2001_p01`.`usuario`
	(`id_usuario`, `clave_acceso`, `clave_vencida`, `rol`)
	VALUES
		('117370774', '117370774', 0, 0),
		('304760577', '304760577', 1, 1),
		('116600828', '116600828', 1, 0),
        ('116760798', '116760798', 0, 1),
        ('114450585', '114450585', 0, 0),
        ('116960426', '116960426', 1, 0),
        ('117870182', '117870182', 0, 1),
        ('117620191', '117620191', 0, 1)
	;
SELECT * FROM  `eif209_2001_p01`.`usuario`;

INSERT INTO `eif209_2001_p01`.`cliente`
	(`id_cliente`, `usuario_id_usuario`, `apellidos`, `nombre`, `telefono`)
	VALUES 
    ('117370774', '117370774', 'Hibbert Morales', 'Tannya', '87362514')
    ,('304760577', '304760577', 'Sanchez Brenes', 'Bryan', '62814679')
    ,('116600828', '116600828', 'Naranjo Bonilla', 'Carlos', '64792290')
    ,('116760798', '116760798', 'Castro Arroyo', 'Priscilla', '87937291')
    ,('114450585', '114450585', 'Chavarría Bonilla', 'Marvin', '89463728')
    ,('116960426', '116960426', 'Collado Salvatierra', 'Pamela', '67839201')
    ,('117870182', '117870182', 'Vargas Benambourg', 'Luis', '67892190')
    ,('117620191', '117620191', 'Garita Gutiérrez', 'Rebecca', '86793562')
	;
SELECT * FROM  `eif209_2001_p01`.`cliente`;


INSERT INTO `eif209_2001_p01`.`tipo_cuenta` 
(`id_tipo_cuenta`, `descripción`, `tasa_interés`)
 VALUES ('1', 'Cuenta De Ahorros', '0.03'),
 ('2', 'Cuenta Corriente', '0.05'),
  ('3', 'Cuenta De Nómina', '0.37'),
  ('4', 'Cuenta de Valores', '0.2')
  
  ;
SELECT * FROM `eif209_2001_p01.tipo_cuenta`;



INSERT INTO `eif209_2001_p01`.`movimiento` (`id_movimiento`, `cuenta_num_cuenta`, `monto`, `fecha`, `aplicado`, `movimientocol`)
 VALUES ('1', '1', '1', '1', '1', '1')
,  ('2', '2', '2', '2', '2', '2')
, ('3', '3', '3', '3', '3', '3');

SELECT * FROM `eif209_2001_p01.movimiento`;