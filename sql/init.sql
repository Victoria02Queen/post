CREATE DATABASE post;

CREATE TABLE PostOffice(
    id SERIAL PRIMARY KEY,
    name VARCHAR(60),
    address VARCHAR(80)
);

CREATE TABLE Employee(
    id SERIAL PRIMARY KEY,
    fullName VARCHAR(70),
    phone INT,
    id_post INT,
    FOREIGN KEY (id_post) REFERENCES PostOffice(id)
);

CREATE TABLE Customer (
    id SERIAL PRIMARY KEY,
    fullName VARCHAR(60),
    phone VARCHAR(70) UNIQUE,
    address VARCHAR(70)
);

CREATE TABLE Package (
    id SERIAL PRIMARY KEY,
    name VARCHAR(60),
    phone VARCHAR (70),
    trackNumber VARCHAR(60) UNIQUE,
    id_recipient INTEGER REFERENCES Customer(id),
    id_sender INTEGER REFERENCES Customer(id)
);

CREATE TABLE Location(
    id SERIAL PRIMARY KEY,
    trackNumber VARCHAR(60) UNIQUE REFERENCES Package(trackNumber),
    location VARCHAR(60)
);