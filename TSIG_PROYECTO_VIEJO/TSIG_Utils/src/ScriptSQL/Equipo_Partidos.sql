
--Equipos
insert into public.equipopais(nombrepais) values('Rusia');
insert into public.equipopais(nombrepais) values('Arabia Saudita');
insert into public.equipopais(nombrepais) values('Egipto');
insert into public.equipopais(nombrepais) values('Uruguay');

insert into public.equipopais(nombrepais) values('España');
insert into public.equipopais(nombrepais) values('Irán');
insert into public.equipopais(nombrepais) values('Portugal');
insert into public.equipopais(nombrepais) values('Marruecos');

insert into public.equipopais(nombrepais) values('Francia');
insert into public.equipopais(nombrepais) values('Australia');
insert into public.equipopais(nombrepais) values('Perú');
insert into public.equipopais(nombrepais) values('Dinamarca');

insert into public.equipopais(nombrepais) values('Argentina');
insert into public.equipopais(nombrepais) values('Islandia');
insert into public.equipopais(nombrepais) values('Croacia');
insert into public.equipopais(nombrepais) values('Nigeria');

insert into public.equipopais(nombrepais) values('Costa Rica');
insert into public.equipopais(nombrepais) values('Brasil');
insert into public.equipopais(nombrepais) values('Serbia');
insert into public.equipopais(nombrepais) values('Suiza');

insert into public.equipopais(nombrepais) values('Alemania');
insert into public.equipopais(nombrepais) values('México');
insert into public.equipopais(nombrepais) values('Suecia');
insert into public.equipopais(nombrepais) values('Corea del Sur');

insert into public.equipopais(nombrepais) values('Bélgica');
insert into public.equipopais(nombrepais) values('Panamá');
insert into public.equipopais(nombrepais) values('Túnez');
insert into public.equipopais(nombrepais) values('Inglaterra');

insert into public.equipopais(nombrepais) values('Polonia');
insert into public.equipopais(nombrepais) values('Senegal');
insert into public.equipopais(nombrepais) values('Colombia');
insert into public.equipopais(nombrepais) values('Japón');

--Partidos--
	--Grupo A
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-14 12:00:00', 5,0,'GRUPO_A', 'Rusia', 'Arabia Saudita', null);
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-15 09:00:00', 0,1,'GRUPO_A', 'Egipto', 'Uruguay', null);
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-19 15:00:00', 3,1,'GRUPO_A', 'Rusia', 'Egipto', null);
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-20 12:00:00', 0,0,'GRUPO_A', 'Uruguay', 'Arabia Saudita', null);
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-25 11:00:00', 0,0,'GRUPO_A', 'Arabia Saudita', 'Egipto', null);
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-25 11:00:00', 0,0,'GRUPO_A', 'Uruguay', 'Rusia', null);

	--Grupo B
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-15 12:00:00', 0,1,'GRUPO_B', 'Marruecos', 'Irán', null);
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-15 15:00:00', 3,3,'GRUPO_B', 'Portugal', 'España', null);
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-20 09:00:00', 0,0,'GRUPO_B', 'Portugal', 'Marruecos', null);
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-20 15:00:00', 0,0,'GRUPO_B', 'Irán', 'España', null);
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-25 15:00:00', 0,0,'GRUPO_B', 'España', 'Marruecos', null);
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-25 15:00:00', 0,0,'GRUPO_B', 'Irán', 'Portugal', null);

	--Grupo C
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-16 07:00:00', 2,1,'GRUPO_C', 'Francia', 'Australia', null);
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-16 13:00:00', 0,1,'GRUPO_C', 'Perú', 'Dinamarca', null);
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-21 12:00:00', 0,0,'GRUPO_C', 'Francia', 'Perú', null);
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-21 09:00:00', 0,0,'GRUPO_C', 'Dinamarca', 'Australia', null);
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-26 11:00:00', 0,0,'GRUPO_C', 'Australia', 'Perú', null);
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-26 11:00:00', 0,0,'GRUPO_C', 'Dinamarca', 'Francia', null);

	--Grupo D
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-16 10:00:00', 1,1,'GRUPO_D', 'Argentina', 'Islandia', null);
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-16 16:00:00', 2,0,'GRUPO_D', 'Croacia', 'Nigeria', null);
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-21 15:00:00', 0,0,'GRUPO_D', 'Argentina', 'Croacia', null);
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-22 12:00:00', 0,0,'GRUPO_D', 'Nigeria', 'Islandia', null);
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-26 15:00:00', 0,0,'GRUPO_D', 'Islandia', 'Croacia', null);
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-26 15:00:00', 0,0,'GRUPO_D', 'Nigeria', 'Argentina', null);

	--Grupo E
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-17 09:00:00', 0,1,'GRUPO_E', 'Costa Rica', 'Serbia', null);
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-17 15:00:00', 1,1,'GRUPO_E', 'Brasil', 'Suiza', null);
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-22 09:00:00', 0,0,'GRUPO_E', 'Brasil', 'Costa Rica', null);
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-22 15:00:00', 0,0,'GRUPO_E', 'Serbia', 'Suiza', null);
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-27 15:00:00', 0,0,'GRUPO_E', 'Suiza', 'Costa Rica', null);
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-27 15:00:00', 0,0,'GRUPO_E', 'Serbia', 'Brasil', null);

	--Grupo F
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-17 12:00:00', 0,1,'GRUPO_F', 'Alemania', 'México', null);
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-18 09:00:00', 1,0,'GRUPO_F', 'Suecia', 'Corea del Sur', null);
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-23 15:00:00', 0,0,'GRUPO_F', 'Alemania', 'Suecia', null);
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-23 12:00:00', 0,0,'GRUPO_F', 'Corea del Sur', 'México', null);
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-27 11:00:00', 0,0,'GRUPO_F', 'México', 'Suecia', null);
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-27 11:00:00', 0,0,'GRUPO_F', 'Corea del Sur', 'Alemania', null);

	--Grupo G
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-18 12:00:00', 3,0,'GRUPO_G', 'Bélgica', 'Panamá', null);
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-18 15:00:00', 1,2,'GRUPO_G', 'Túnez', 'Inglaterra', null);
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-23 09:00:00', 0,0,'GRUPO_G', 'Bélgica', 'Túnez', null);
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-24 09:00:00', 0,0,'GRUPO_G', 'Inglaterra', 'Panamá', null);
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-28 15:00:00', 0,0,'GRUPO_G', 'Panamá', 'Túnez', null);
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-28 15:00:00', 0,0,'GRUPO_G', 'Inglaterra', 'Bélgica', null);

	--Grupo H
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-19 12:00:00', 1,2,'GRUPO_H', 'Polonia', 'Senegal', null);
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-19 09:00:00', 1,2,'GRUPO_H', 'Colombia', 'Japón', null);
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-24 12:00:00', 0,0,'GRUPO_H', 'Japón', 'Senegal', null);
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-24 15:00:00', 0,0,'GRUPO_H', 'Polonia', 'Colombia', null);
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-28 11:00:00', 0,0,'GRUPO_H', 'Senegal', 'Colombia', null);
insert into public.partido(fecha, goles_1, goles_2, tipo, equipo_1_nombrepais, equipo_2_nombrepais, se_enfrentan_en_gid) VALUES ('2018-06-28 11:00:00', 0,0,'GRUPO_H', 'Japón', 'Polonia', null);