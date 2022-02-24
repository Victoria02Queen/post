CREATE DATABASE post;


CREATE TABLE PostOffice(
    id SERIAL PRIMARY KEY,
    name VARCHAR(60) NOT NULL ,
    address VARCHAR(80) NOT NULL
);

CREATE TABLE Employee(
    id SERIAL PRIMARY KEY,
    fullName VARCHAR(70) NOT NULL ,
    phone VARCHAR(20) NOT NULL ,
    id_post INT NOT NULL ,
    FOREIGN KEY (id_post) REFERENCES PostOffice(id)
);


CREATE TABLE Customer (
    id SERIAL PRIMARY KEY,
    fullName VARCHAR(60) NOT NULL ,
    phone VARCHAR(70) UNIQUE,
    address VARCHAR(70) NOT NULL,
    password VARCHAR(60) NOT NULL
);


CREATE TABLE Package (
    id SERIAL PRIMARY KEY,
    name VARCHAR(60) NOT NULL ,
    trackNumber VARCHAR(60) UNIQUE,
    id_recipient INTEGER REFERENCES Customer(id),
    id_sender INTEGER REFERENCES Customer(id)
);

CREATE TABLE Location(
    id SERIAL PRIMARY KEY,
    trackNumber VARCHAR(60) UNIQUE REFERENCES Package(trackNumber),
    location VARCHAR(60) NOT NULL
);
