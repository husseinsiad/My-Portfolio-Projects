drop database if EXISTS hoteldb;
CREATE DATABASE hoteldb;
use hoteldb;

CREATE TABLE RoomType (
  RoomTypeId integer PRIMARY KEY auto_increment,
  `Type` varchar(30) NOT NULL,
  StandardOccupancy integer NOT NULL,
  MaximumOccupancy integer NOT NULL,
  price numeric(5, 2) NOT NULL
);

CREATE TABLE Room (
  RoomId integer PRIMARY KEY auto_increment,
  IsAda boolean NOT NULL,
  RoomTypeId integer NULL,
  CONSTRAINT RoomFK FOREIGN KEY (RoomTypeId)
  REFERENCES RoomType (RoomTypeId)
);

CREATE TABLE Amenities (
  AmenitiesId integer PRIMARY KEY auto_increment,
  `Name` varchar(30) NOT NULL,
  Price numeric(5, 2) NOT NULL
);

CREATE TABLE Guest (
  GuestId integer PRIMARY KEY auto_increment,
  `Name` varchar(30) NOT NULL,
  Address varchar(50) NOT NULL,
  City varchar(30) NOT NULL,
  State char(2) NOT NULL,
  Zip integer NOT NULL,
  Phone varchar(30)
);
 
CREATE TABLE Reservation (
  ReservationId integer PRIMARY KEY auto_increment,
  StartDate date NOT NULL,
  EndDate date NOT NULL,
  GuestId integer NULL,
  CONSTRAINT GuestFK FOREIGN KEY (GuestID)
  REFERENCES Guest (GuestID)
);

CREATE TABLE RoomAmenities (
  RoomAmenitiesId integer PRIMARY KEY auto_increment,
  RoomId integer NULL,
  AmenitiesId integer NULL,
  CONSTRAINT RoomsFK FOREIGN KEY (RoomId)
  REFERENCES Room (RoomId),
  CONSTRAINT AmenitiesFK FOREIGN KEY (AmenitiesID)
  REFERENCES Amenities (AmenitiesID)
);

CREATE TABLE RoomReservation (
  RoomReservationId integer PRIMARY KEY auto_increment,
  ReservationId integer NULL,
  RoomId integer NULL,
  Adults integer NOT NULL,
  Children integer NOT NULL,
  CONSTRAINT ReservationIdFK FOREIGN KEY (ReservationId)
  REFERENCES Reservation (ReservationId),
  CONSTRAINT RoomIdFK FOREIGN KEY (RoomId)
  REFERENCES Room (RoomId)
)