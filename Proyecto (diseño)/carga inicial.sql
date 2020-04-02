
USE `eif209_2001_p01` ;
INSERT INTO `eif209_2001_p01`.`moneda`
	(`nombre`, `descripcion`, `simbolo`, `tipo_cambio_compra`, `tipo_cambio_venta`)
	VALUES
		('CRC', 'Colón', '₡', '1.0', '1.0'),
		('USD', 'Dólar EEUU', '$', '560.0', '570.0'),
		('EUR', 'Euro', '€', '700.0', '720.0')
	;
SELECT * FROM  `eif209_2001_p01`.`moneda`;
