
USE `eif209_2001_p01` ;
INSERT INTO `eif209_2001_p01`.`moneda`
	(`nombre`, `descripcion`, `simbolo`,
    `tipo_cambio_compra`, `tipo_cambio_venta`, `numueroUnico`)
	VALUES
		('CRC', 'Colón', '₡', '1.0', '1.0','1'),
		('USD', 'Dólar EEUU', '$', '560.0', '570.0','2'),
		('EUR', 'Euro', '€', '700.0', '720.0','3')
	;
SELECT * FROM  `eif209_2001_p01`.`moneda`;


INSERT INTO `eif209_2001_p01`.`usuario`
 (`id_usuario`, `clave_acceso`, `clave_vencida`, `rol`) 
VALUES
 ('304760577', '304760577', '0', '0'),
 ('1', '1', '1', '1'),
 ('0', '0', '0', '0')
 ;
SELECT * FROM eif209_2001_p01.usuario;