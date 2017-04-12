drop database if exists memotournament_bbdd;
create database if not exists memotournament_bbdd;
use memotournament_bbdd;
create table if not exists Usuari (
    nickname varchar(50),
    nom varchar(50),
    clau_usuari varchar(6),
    data_registre timestamp,
    puntuacio_total int,
    horas_jugadas float,
    bloqued int,
    primary key (nickname))engine = innodb;
create table if not exists Partida (
	id_partida int auto_increment,
	nickname varchar(50) not null,
	puntuacio int,
    modalitat varchar(50),
    data_partida timestamp,
    primary key (id_partida))engine = innodb;
    alter table Partida add constraint nickname foreign key (nickname) references Usuari(nickname);