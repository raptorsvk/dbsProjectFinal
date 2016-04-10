
----------------------------------*
--**|  Create Tables Script  |****|
----------------------------------*
-- Tabulka mesta
CREATE  TABLE mesta (
  nazov varchar(30) PRIMARY KEY
);

-- zamestnanci // pocas pocas nejakeho obdobia
CREATE  TABLE zamestnanci (
  id SERIAL PRIMARY KEY,
  meno varchar(20) not null,
  priezvisko varchar(20) not null,
  datum_narodenia date not null,
  rodne_cislo varchar(10),
  obciansky_preukaz varchar(8)
);


-- tabulka bufety
CREATE  TABLE bufety (
  id SERIAL PRIMARY KEY,
  nazov varchar(20) not null,
  mesto varchar not null,
  FOREIGN KEY(mesto) REFERENCES mesta(nazov) 
);


-- tabulka zamestany v bufete // pocas pocas nejakeho obdobia
CREATE  TABLE pracovne_pomery ( 
  id SERIAL PRIMARY KEY,
  bufet SERIAL REFERENCES bufety(id),
  zamestanec SERIAL REFERENCES zamestnanci(id),
  pracovna_pozicia varchar(20), 
  zaciatok_pp date,
  kONiec_pp date
);

-- tabulka sklady
CREATE  TABLE sklady (
  id SERIAL PRIMARY KEY,
  nazov varchar(20),
  mesto varchar REFERENCES mesta(nazov),
  bufet serial REFERENCES bufety(id),
  rozloha_skladu float
  );
  
  CREATE  TABLE kategoria_tovaru (
  nazov varchar(30) PRIMARY KEY
);

CREATE  TABLE tovar (
  id serial PRIMARY KEY,
  nazov varchar(20) ,
  kategoria varchar  REFERENCES kategoria_tovaru(nazov),
  ciarovy_kod varchar(20),
  vyrobca varchar(20),
  krajina_povodu varchar(20),
  hmotnost float, -- v gramoch
  hodnota float,
  );
  
  CREATE  TABLE tovar_v_skladoch (
  id SERIAL PRIMARY KEY,
  sklad serial REFERENCES sklady(id),
  tovar serial REFERENCES tovar(id),
  pocet_kusov int
  );
  

  CREATE  TABLE zaznamy_presunov_tovaru(
  id SERIAL PRIMARY KEY,
  zdrojovy_sklad serial REFERENCES sklady(id),
  cielovy_sklad serial  REFERENCES sklady(id),
  tovar serial REFERENCES tovar(id),
  pocet_kusov int,
  date date,
  time time  
 );

 ---------------------------------------*
 --**|  Add data to Tables skript  |****|
 ---------------------------------------*
 -- Mal by Fungovat
 

INSERT INTO mesta (nazov) VALUES
('Bratislava'),
('Košice'),
('Banská BYstrica'),
('Trnava');


INSERT INTO zamestnanci (meno,priezvisko,datum_narodenia,rodne_cislo,obciansky_preukaz) VALUES
('Robert','Kapuca','5.9.1202','1255547878','BB558844'),
('Ibrahim','Maiga','5.9.1967','1355547803','BB558803'),
('Ladislav','Meliško','5.9.1972','1455547804','BB558804'),
('Radovan','Huncút','5.9.1985','1255547805','BB558805');


INSERT INTO bufety (nazov,mesto,veduci_bufetu) VALUES
('Bufet_1_BA','Bratislava',1),
('Bufet_2_BA','Bratislava',1),
('Bufet_1_KE','Košice',2),
('Bufet_2_KE','Košice',2),
('Bufet_1_BB','Banská BYstrica',2),
('Bufet_1_TT','Trnava',3);


INSERT INTO pracovne_pomery (bufet,zamestanec,pracovna_pozicia,zaciatok_pp,kONiec_pp) VALUES
(1,1,'predavac','1.1.2005','2.9.2009'),
(3,2,'predavac','2.1.2005',NULL),
(1,3,'skladnik','3.1.2005',NULL),
(1,4,'veduci pobocky','4.2.2006',NULL);

INSERT INTO sklady (nazov,mesto,bufet,veduci_skladu,rozloha_skladu) VALUES
('Sklad_1_BA','Bratislava',1,3,55.4),
('Sklad_2_BA','Bratislava',2,4,70);

INSERT INTO kategoria_tovaru (nazov) VALUES
('nealkoholicke napoje'),
('alkoholicke napoje'),
('pecivo'),
('sladkosti');

INSERT INTO tovar (nazov,kategoria,ciarovy_kod,vyrobca,krajina_povodu,hmotnost,hodnota) VALUES
('kávenky','sladkosti','555444333222','Sedita','Slovensko',50,0.50),
('mila','sladkosti','777571333222','Opavia','Slovensko',45,0.45);

INSERT INTO tovar_v_skladoch (sklad,tovar,pocet_kusov) VALUES
(1,1,5),
(2,1,14);

INSERT INTO zaznamy_presunov_tovaru (tovar,zdrojovy_sklad,cielovy_sklad,pocet_kusov,date,time) VALUES 
(2,1,2,2,'1.1.2016','15:20')


 ---------------------------------------*
 --**|  Add data to Tables skript  |****|
 ---------------------------------------*
 -- NEVIEM CI FUNGUJE !!!!!!!


INSERT INTO bufety VALUES (1, 'Bufet_1_BA', 'Bratislava', 1);
INSERT INTO bufety VALUES (2, 'Bufet_2_BA', 'Bratislava', 1);
INSERT INTO bufety VALUES (3, 'Bufet_1_KE', 'Košice', 2);
INSERT INTO bufety VALUES (4, 'Bufet_2_KE', 'Košice', 2);
INSERT INTO bufety VALUES (5, 'Bufet_1_BB', 'Banská Bystrica', 2);
INSERT INTO bufety VALUES (6, 'Bufet_1_TT', 'Trnava', 3);



INSERT INTO kategoria_tovaru VALUES ('nealkoholicke napoje');
INSERT INTO kategoria_tovaru VALUES ('alkoholicke napoje');
INSERT INTO kategoria_tovaru VALUES ('pecivo');
INSERT INTO kategoria_tovaru VALUES ('sladkosti');


INSERT INTO mesta VALUES ('Bratislava');
INSERT INTO mesta VALUES ('Košice');
INSERT INTO mesta VALUES ('Banská Bystrica');
INSERT INTO mesta VALUES ('Trnava');


INSERT INTO pracovne_pomery VALUES (1, 1, 1, 'predavac', '2005-01-01', '2009-09-02');
INSERT INTO pracovne_pomery VALUES (2, 3, 2, 'predavac', '2005-01-02', NULL);
INSERT INTO pracovne_pomery VALUES (3, 1, 3, 'skladnik', '2005-01-03', NULL);
INSERT INTO pracovne_pomery VALUES (4, 1, 4, 'veduci pobocky', '2006-02-04', NULL);




INSERT INTO sklady VALUES (1, 'Sklad_1_BA', 'Bratislava', 1, 3, 55.399999999999999);
INSERT INTO sklady VALUES (2, 'Sklad_2_BA', 'Bratislava', 2, 4, 70);
INSERT INTO sklady VALUES (3, 'Sklad_3_BA', 'Bratislava', 1, 3, 55.399999999999999);


INSERT INTO tovar VALUES (1, 'kávenky', 'sladkosti', '555444333222', 'Sedita', 'Slovensko', 50, 0.5, true);
INSERT INTO tovar VALUES (7, 'Kofola 2L', 'nealkoholicke napoje', '5554478979', 'Napoje s.r.o', 'Slovensko', 2000, 0.89000000000000001, true);
INSERT INTO tovar VALUES (9, 'Fanta 2L', 'alkoholicke napoje', '5554478979', 'Napoje s.r.o', 'Slovensko', 1000, 0.80000000000000004, true);
INSERT INTO tovar VALUES (5, 'Oškvarkový pagáč', 'pecivo', '5554548484', 'Super Pekarne a.s', 'Slovensko', 75, 0.76000000000000001, true);
INSERT INTO tovar VALUES (2, 'Mila', 'sladkosti', '777571333222', 'Opavia', 'Slovensko', 45, 0.48999999999999999, true);
INSERT INTO tovar VALUES (6, 'Osie hniezdo', 'pecivo', '5554489484', 'Super Pekarne a.s', 'Slovensko', 40, 0.59999999999999998, true);
INSERT INTO tovar VALUES (10, 'Fanta 2L ver2', 'alkoholicke napoje', '5554478979', 'Napoje s.r.o', 'Slovensko', 1000, 0.81999999999999995, true);
INSERT INTO tovar VALUES (11, 'Tvarohová štrudla', 'pecivo', '5554548556', 'Super Pekarne a.s', 'Slovensko', 72, 0.65000000000000002, true);
INSERT INTO tovar VALUES (4, 'Tvarohový koláč', 'pecivo', '5554548484', 'Super Pekarne a.s', 'Slovensko', 70, 0.73999999999999999, true);
INSERT INTO tovar VALUES (3, 'Makový štrudla', 'pecivo', '5554548484', 'Super Pekarne a.s', 'Slovensko', 70, 0.71999999999999997, true);
INSERT INTO tovar VALUES (8, 'Sprite 2L', 'nealkoholicke napoje', '5554478979', 'Napoje s.r.o', 'Slovensko', 2000, 0.72999999999999998, true);
INSERT INTO tovar VALUES (12, 'Oškvarkový pagáč 3', 'pecivo', '5554548484', '', 'Slovensko', 75, 0.34999999999999998, true);



SELECT pg_catalog.setval('tovar_id_seq', 12, true);



INSERT INTO tovar_v_skladoch VALUES (6, 1, 3, 20);
INSERT INTO tovar_v_skladoch VALUES (18, 2, 4, 38);
INSERT INTO tovar_v_skladoch VALUES (12, 1, 5, 12);
INSERT INTO tovar_v_skladoch VALUES (13, 2, 5, 10);
INSERT INTO tovar_v_skladoch VALUES (20, 3, 5, 10);
INSERT INTO tovar_v_skladoch VALUES (22, 3, 4, 20);
INSERT INTO tovar_v_skladoch VALUES (9, 1, 4, 30);
INSERT INTO tovar_v_skladoch VALUES (7, 2, 3, 10);
INSERT INTO tovar_v_skladoch VALUES (8, 3, 3, 15);
INSERT INTO tovar_v_skladoch VALUES (17, 3, 6, 19);
INSERT INTO tovar_v_skladoch VALUES (5, 3, 1, 8);
INSERT INTO tovar_v_skladoch VALUES (24, 2, 1, 10);
INSERT INTO tovar_v_skladoch VALUES (16, 2, 6, 16);
INSERT INTO tovar_v_skladoch VALUES (15, 1, 6, 10);
INSERT INTO tovar_v_skladoch VALUES (1, 1, 1, 11);



INSERT INTO zamestnanci VALUES (1, 'Robert', 'Kapuca', '1202-09-05', '1255547878', 'BB558844');
INSERT INTO zamestnanci VALUES (2, 'Ibrahim', 'Maiga', '1967-09-05', '1355547803', 'BB558803');
INSERT INTO zamestnanci VALUES (3, 'Ladislav', 'Meliško', '1972-09-05', '1455547804', 'BB558804');
INSERT INTO zamestnanci VALUES (4, 'Radovan', 'Huncút', '1985-09-05', '1255547805', 'BB558805');



INSERT INTO zaznamy_presunov_tovaru VALUES (1, 1, 2, 2, 2, '2016-01-01', '15:20:00');
INSERT INTO zaznamy_presunov_tovaru VALUES (2, 2, 1, 1, 2, '2016-04-04', '01:23:36');
INSERT INTO zaznamy_presunov_tovaru VALUES (3, 2, 1, 1, 1, '2016-04-04', '01:25:08');
INSERT INTO zaznamy_presunov_tovaru VALUES (4, 2, 1, 1, 1, '2016-04-04', '01:29:13');
INSERT INTO zaznamy_presunov_tovaru VALUES (5, 2, 1, 1, 1, '2016-04-04', '01:29:20');
INSERT INTO zaznamy_presunov_tovaru VALUES (6, 1, 3, 1, 2, '2016-04-04', '01:44:40');
INSERT INTO zaznamy_presunov_tovaru VALUES (7, 2, 1, 4, 15, '2016-04-04', '10:11:29');
INSERT INTO zaznamy_presunov_tovaru VALUES (8, 2, 3, 4, 15, '2016-04-04', '13:18:15');
INSERT INTO zaznamy_presunov_tovaru VALUES (9, 2, 1, 3, 10, '2016-04-04', '15:02:54');
INSERT INTO zaznamy_presunov_tovaru VALUES (10, 3, 1, 4, 3, '2016-04-04', '21:22:28');
INSERT INTO zaznamy_presunov_tovaru VALUES (11, 3, 1, 4, 2, '2016-04-04', '21:32:28');
INSERT INTO zaznamy_presunov_tovaru VALUES (12, 3, 1, 5, 4, '2016-04-04', '21:32:44');
INSERT INTO zaznamy_presunov_tovaru VALUES (13, 2, 1, 6, 5, '2016-04-04', '21:34:30');
INSERT INTO zaznamy_presunov_tovaru VALUES (14, 3, 1, 4, 20, '2016-04-04', '21:36:43');
INSERT INTO zaznamy_presunov_tovaru VALUES (15, 3, 1, 4, 20, '2016-04-04', '21:40:36');
INSERT INTO zaznamy_presunov_tovaru VALUES (16, 1, 3, 4, 20, '2016-04-04', '21:41:12');
INSERT INTO zaznamy_presunov_tovaru VALUES (17, 2, 1, 1, 9, '2016-04-04', '21:46:53');
INSERT INTO zaznamy_presunov_tovaru VALUES (18, 3, 2, 3, 5, '2016-04-04', '23:01:32');
INSERT INTO zaznamy_presunov_tovaru VALUES (19, 3, 1, 1, 1, '2016-04-05', '00:38:21');
INSERT INTO zaznamy_presunov_tovaru VALUES (20, 3, 2, 6, 1, '2016-04-05', '00:38:25');
INSERT INTO zaznamy_presunov_tovaru VALUES (21, 3, 2, 1, 5, '2016-04-05', '12:25:45');
INSERT INTO zaznamy_presunov_tovaru VALUES (22, 1, 2, 1, 3, '2016-04-05', '13:36:08');
INSERT INTO zaznamy_presunov_tovaru VALUES (23, 1, 3, 1, 2, '2016-04-05', '13:45:15');
INSERT INTO zaznamy_presunov_tovaru VALUES (24, 1, 2, 1, 1, '2016-04-05', '13:45:27');
INSERT INTO zaznamy_presunov_tovaru VALUES (25, 2, 1, 1, 9, '2016-04-05', '13:45:38');
INSERT INTO zaznamy_presunov_tovaru VALUES (26, 1, 2, 1, 10, '2016-04-05', '13:45:56');
INSERT INTO zaznamy_presunov_tovaru VALUES (27, 1, 2, 6, 5, '2016-04-05', '17:48:42');
















 
 
