  drop database if exists vendingDb;
  create database vendingDb;
  use vendingDb;
  create table item(
	itemid varchar(20) primary key,
     itemName varchar(30),
    itemCost numeric(5,2),
    numberOfItemsInInventory integer
  );

 