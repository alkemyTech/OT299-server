INSERT INTO SAMPLE(ID, DESCRIPTION)
VALUES (1, 'My first sample');

INSERT INTO ACTIVITIES(ID, NAME, CONTENT, IMAGE)
VALUES (1, 'Apoyo Escolar para el nivel Primario', 'El espacio de apoyo escolar es el corazón del área educativa. Se realizan los talleres de lunes a jueves de 10 a 12 horas y de 14 a 16 horas en el contraturno.',
        'https://educacion30.b-cdn.net/wp-content/uploads/2017/09/primaria-978x652.jpg'),
       (2, 'Apoyo Escolar Nivel Secundaria', 'Del mismo modo que en primaria, este taller es el corazón del área secundaria. Se realizan talleres de lunes a viernes de 10 a 12 horas y de 16 a 18 horas en el contraturno.',
        'https://3.files.edl.io/1675/21/11/16/032634-497df7d6-806d-4262-9436-4d3854941149.jpg'),
       (3, 'Tutorías', 'Es un programa destinado a jóvenes a partir del tercer año de secundaria, cuyo objetivo es garantizar su permanencia en la escuela y construir un proyecto de vida que da sentido al colegio.',
        'https://www.unir.net/wp-content/uploads/2019/11/Foto-profesora-tutotia-alumnos-unir.jpg'),
       (4, 'Programas Educativos', 'Mediante nuestros programas educativos, buscamos incrementar la capacidad intelectual, moral y afectiva de las personas de acuerdo con la cultura y las normas de convivencia de la sociedad a la que pertenecen.',
        'https://img.interempresas.net/fotos/2675911.jpeg');

INSERT INTO ROLES(ID, NAME, DESCRIPTION)
VALUES  (1,'Administrator','All privileges'),
        (2,'Contributor','Limited access');

INSERT INTO USERS(ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, PHOTO, ROLE_ID)
VALUES  (1, 'Maria','Iraola','mariairaola@somosmas.com','SomosUnaOng_1001','https://drive.google.com/file/d/1CIsJVHyui6__A0MNAxL6hyg3Trz3ZbyW/view?usp=sharing', 1),
        (2, 'Marita','Gomez','maritagomez@somosmas.com','GenerandoConciencia_1010','https://drive.google.com/file/d/1splwoyAysQ_uPBnkwCy8uJwrAwGIuDYo/view?usp=sharing',1),
        (3, 'Miriam', 'Rodriguez', 'miriamrodriguez@somosmas.com', 'MuchosMasMiembros_1011', 'https://drive.google.com/file/d/1PNHzyL26jzsHltv281_rc3vEKdtTg2dF/view?usp=sharing', 1),
        (4, 'Cecilia','Mendez','ceciliamendez@somosmas.com','CreandoNuevasOportunidades_1100','https://drive.google.com/file/d/18Jub8i5qQnjBpuR-EsVx9Xtc0tzS2dmx/view?usp=sharing',1),
        (5, 'Mario','Fuetes','mariofuentes@somosmas.com','VamosArriba_1101','',1),
        (6, 'AdministratorMock1','Mock1','Mock1@somosmas.com',"Mock1",'',1),
        (7, 'AdministratorMock2','Mock2','Mock2@somosmas.com','Mock2','',1),
        (8, 'AdministratorMock3','Mock3','Mock3@somosmas.com','Mock3','',1),
        (9, 'AdministratorMock4','Mock4','Mock4@somosmas.com','Mock4','',1),
        (10, 'AdministratorMock5','Mock5','Mock5@somosmas.com','Mock5','',1),
        (11, 'ContributorMock6','Mock6','Mock6@somosmas.com',"Mock6",'',2),
        (12, 'ContributorMock7','Mock7','Mock7@somosmas.com','Mock7','',2),
        (13, 'ContributorMock8','Mock8','Mock8@somosmas.com','Mock8','',2),
        (14, 'ContributorMock9','Mock9','Mock9@somosmas.com','Mock9','',2),
        (15, 'ContributorMock10','Mock10','Mock10@somosmas.com','Mock10','',2),
        (16, 'Rodrigo','Fuentes','rodrigofuentes@somosmas.com','SomosMas_1000','https://drive.google.com/file/d/1ukP7z8kzFREyCbsJxzizHckgvfQ80KKt/view?usp=sharing',2),
        (17, 'Marco','Fernandez','marcofernandez@somosmas.com','CuerpoEnMovimiento_0001','https://drive.google.com/file/d/156uGhIMSWD_iOmvZEy3-UnGFLhvLW9r5/view?usp=sharing',2),
        (18, 'ContributorMock11','Mock11','Mock11@somosmas.com','Mock11','',2),
        (19, 'ContributorMock12','Mock12','Mock12@somosmas.com','Mock12','',2),
        (20, 'ContributorMock13','Mock13','Mock13@somosmas.com','Mock13','',2);



INSERT INTO SLIDES(ID, IMAGE_URL, SLIDE_TEXT, SLIDE_ORDER)
VALUES  (1,'www.imaginejemplo1.com','Slide de ejemplo 1', 'Primero'),
        (2,'www.imaginejemplo2.com','Slide de ejemplo 2', 'Segundo'),
        (3,'www.imaginejemplo3.com','Slide de ejemplo 3', 'Tercero');
