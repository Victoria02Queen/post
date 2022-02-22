CREATE DATABASE post;

CREATE TABLE PostOffice(
    id SERIAL PRIMARY KEY,
    name VARCHAR(60),
    address VARCHAR(80)
);
DROP TABLE Employee;
CREATE TABLE Employee(
    id SERIAL PRIMARY KEY,
    fullName VARCHAR(70),
    phone VARCHAR(20),
    id_post INT NOT NULL ,
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
    trackNumber VARCHAR(60) UNIQUE,
    id_recipient INTEGER REFERENCES Customer(id),
    id_sender INTEGER REFERENCES Customer(id)
);

CREATE TABLE Location(
    id SERIAL PRIMARY KEY,
    trackNumber VARCHAR(60) UNIQUE REFERENCES Package(trackNumber),
    location VARCHAR(60)
);