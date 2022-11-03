INSERT INTO usuarios
(nombre,apellido,nombre_usuario,contrasena)
VALUES ('admin','admin','admin','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq');

INSERT INTO roles (rol_nombre) VALUES ('ROLE_USER');
INSERT INTO roles (rol_nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuario_roles (usuario_id, roles_id) VALUES (1, 1);
INSERT INTO usuario_roles (usuario_id, roles_id) VALUES (1, 2);
