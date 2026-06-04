En SQL la relación 1:N entre Agencia y Satelite se implementa con una foreign key AGENCIA_ID en la tabla SATELITES que referencia el ID de AGENCIAS. Esto significa que una agencia puede tener muchos satélites pero un satélite solo pertenece a una agencia.

En Java se representa con private Agencia agencia dentro de la clase Satelite, en vez de guardar solo el id

Usamos private Agencia agencia en vez de private int agenciaId porque así tenemos acceso al objeto completo y podemos llamar a satelite.getAgencia().getNombre() directamente. 

PreparedStatement evita la inyección SQL. Asi que mas que nada se usa por seguridad