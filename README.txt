Versiones:

Spring Boot:3.1.0
Java: 17
Postgres: 10
Junit: 4.13.2
Mockito: 3.12.4

COMANDOS:

URL_BASE=http://localhost:8080/api

URIS=   -/auth/login
        -/places/find

Compilar Proyecto:
    mvn clean package
    // En este paso se ejecutaran las pruebas(PlaceTest y UserTests)

Lanzar Proyecto:
    java -jar target/demo-0.0.1-SNAPSHOT.jar

DDL Base de datos Postgres10:

NOMBRE DE DB:equifax

PUERTO:5432

CREACION DB:

    -- Database: equifax

    -- DROP DATABASE IF EXISTS equifax;

    CREATE DATABASE equifax
        WITH
        OWNER = postgres
        ENCODING = 'UTF8'
        LC_COLLATE = 'Spanish_Chile.1252'
        LC_CTYPE = 'Spanish_Chile.1252'
        TABLESPACE = pg_default
        CONNECTION LIMIT = -1
        IS_TEMPLATE = False;

CREACION DE TABLAS(Aunque que spring las autogenera):

    -- public.place definition

    -- Drop table

    -- DROP TABLE public.place;

    CREATE TABLE public.place (
        id bigserial NOT NULL,
        latitude float8 NOT NULL,
        longitude float8 NOT NULL,
        name varchar(255) NULL,
        address varchar(255) NULL,
        CONSTRAINT place_pkey PRIMARY KEY (id)
    );

    -- public.users definition

    -- Drop table

    -- DROP TABLE public.users;

    CREATE TABLE public.users (
        id serial4 NOT NULL,
        email varchar(50) NOT NULL,
        "password" varchar(64) NOT NULL,
        CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email),
        CONSTRAINT users_pkey PRIMARY KEY (id)
    );


CREACION DE DATOS DE PRUEBA:

    TABLA PLACE:

    INSERT INTO public.place
    (latitude, longitude, "name", address)
    VALUES(40.7128, -74.006, 'Place 1', 'Address');
    INSERT INTO public.place
    (latitude, longitude, "name", address)
    VALUES(51.5074, -0.1278, 'Place 2', 'Address');
    INSERT INTO public.place
    (latitude, longitude, "name", address)
    VALUES(48.8566, 2.3522, 'Place 3', 'Address');
    INSERT INTO public.place
    (latitude, longitude, "name", address)
    VALUES(40.7128, -74.006, 'Place 1', 'Address');
    INSERT INTO public.place
    (latitude, longitude, "name", address)
    VALUES(51.5074, -0.1278, 'Place 2', 'Address');
    INSERT INTO public.place
    (latitude, longitude, "name", address)
    VALUES(48.8566, 2.3522, 'Place 3', 'Address');

    TABLA USERS(usuario=test@equifax.com y password=12345678):

    INSERT INTO public.users
    (email, "password")
    VALUES('test@equifax.com', '$2a$10$V5CbFra8h4bmwS8GKjMCje2h4539K0W6x0zR7FlHuY/dYdR.OTsM.');










