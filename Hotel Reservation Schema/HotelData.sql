use hoteldb;
	/* RoomType*/
insert into roomtype(Type, StandardOccupancy, MaximumOccupancy, price)
values('Double', '2', '4', '174.99');
insert into roomtype(Type, StandardOccupancy, MaximumOccupancy, price)
values('Single', '2', '2', '149.99');
insert into roomtype(Type, StandardOccupancy, MaximumOccupancy, price)
values('Suite', '3', '8', '399.99');

		/*Room*/
insert into room(roomid,IsAda, RoomTypeId)
values('201', '0', '1');
insert into room( roomid,IsAda, RoomTypeId)
values('202', '0', '1');
insert into room( roomid,IsAda, RoomTypeId)
values('203', '0', '1');
insert into room( roomid,IsAda, RoomTypeId)
values('204', '0', '1');
insert into room( roomid,IsAda, RoomTypeId)
values('205', '0', '2');
insert into room( roomid,IsAda, RoomTypeId)
values('206', '0', '2');
insert into room( roomid,IsAda, RoomTypeId)
values('207', '0', '2');
insert into room( roomid,IsAda, RoomTypeId)
values('208', '0', '2');
insert into room( roomid,IsAda, RoomTypeId)
values('301', '0', '1');
insert into room(roomid,IsAda, RoomTypeId)
values('302', '0', '1');
insert into room( roomid,IsAda, RoomTypeId)
values('303', '0', '1');
insert into room( roomid,IsAda, RoomTypeId)
values('304', '0', '1');
insert into room( roomid,IsAda, RoomTypeId)
values('305', '0', '2');
insert into room( roomid,IsAda, RoomTypeId)
values('306', '0', '2');
insert into room(roomid,IsAda, RoomTypeId)
values('307', '0', '2');
insert into room( roomid,IsAda, RoomTypeId)
values('308', '0', '2');
insert into room(roomid,IsAda, RoomTypeId)
values('401', '1', '3');
insert into room( roomid,IsAda, RoomTypeId)
values('402', '1', '3');

			/*Amenities*/
INSERT INTO `hoteldb`.`amenities` (`AmenitiesId`, `Name`, `Price`) VALUES ('1', 'Refrigerator ', '0');
INSERT INTO `hoteldb`.`amenities` (`AmenitiesId`, `Name`, `Price`) VALUES ('2', 'Microwave', '0');
INSERT INTO `hoteldb`.`amenities` (`AmenitiesId`, `Name`, `Price`) VALUES ('3', 'Jacuzzi ', '25.00');
				/*Guest*/
INSERT guest (Name, Address, City, State, Zip, Phone)
  VALUES ("Hussein", "4504 3rd ave south", "Minneapolis", "MN", "55419", "(952) 228-3307");
  INSERT guest (Name, Address, City, State, Zip, Phone)
  VALUES ("Mack Simmer", "379 Old Shore Street", "Council Bluffs", "IA", "51501", "(291) 553-0508");
INSERT guest (Name, Address, City, State, Zip, Phone)
  VALUES ("Bettyann Seery", "750 Wintergreen Dr", "Wasilla", "AK", "99654", "(478) 277-9632");
INSERT guest (Name, Address, City, State, Zip, Phone)
  VALUES ("Duane Cullison", "9662 Foxrun Lane", "Harlingen", "TX", "78552", "(308) 494-0198");
INSERT guest (Name, Address, City, State, Zip, Phone)
  VALUES ("Karie Yang", "9378 W. Augusta Ave", "West Deptford", "NJ", "08096", "(214) 730-0298");
INSERT guest (Name, Address, City, State, Zip, Phone)
  VALUES ("Aurore Lipton", "762 Wild Rose Street", "Saginaw", "MI", "48601", "(377) 507-0974");
INSERT guest (Name, Address, City, State, Zip, Phone)
  VALUES ("Zachery Luechtefeld", "7 Poplar Dr", "Arvada", "CO", "80003", "(814) 485-2615");
INSERT guest (Name, Address, City, State, Zip, Phone)
  VALUES ("Jeremiah Pendergrass", "70 Oakwood St", "Zion", "IL", "60099", "(279) 491-0960");
INSERT guest (Name, Address, City, State, Zip, Phone)
  VALUES ("Walter Holaway", "7556 Arrowhead St", "Cumberland", "RI", "02864", "(446) 396-6785");
INSERT guest (Name, Address, City, State, Zip, Phone)
  VALUES ("Wilfred Vise", "77 West Surrey Street", "Oswego", "NY", "13126", "(834) 727-1001");
INSERT guest (Name, Address, City, State, Zip, Phone)
  VALUES ("Maritza Tilton", "939Linda Rd", "Burke", "VA", "22015", "(446) 351-6860");
INSERT guest (Name, Address, City, State, Zip, Phone)
  VALUES ("Joleen Tison", "87 Queen St", "Drexel Hill", "PA", "19026", "(231) 893-2755");

				/* Reservation*/
             
insert into reservation(GuestId,StartDate,EndDate)value( (select guestid from guest where name="Mack Simmer"),		"2023-2-2",	"2023-2-4");
insert into reservation(GuestId,StartDate,EndDate)value( (select guestid from guest where name="Bettyann Seery"),		"2023-2-5",	"2023-2-10" );
insert into reservation(GuestId,StartDate,EndDate)value( (select guestid from guest where name="Duane Cullison"),	 	"2023-2-22", "2023-2-24");
insert into reservation(GuestId,StartDate,EndDate)value( (select guestid from guest where name="Karie Yang"),	 	"2023-3-6",	 "2023-3-7" );
insert into reservation(GuestId,StartDate,EndDate)value( (select guestid from guest where name="Hussein"),	 	"2023-3-17",  "2023-3-20" );
insert into reservation(GuestId,StartDate,EndDate)value( (select guestid from guest where name="Aurore Lipton"),	 	"2023-3-18",  "2023-3-23" );
insert into reservation(GuestId,StartDate,EndDate)value( (select guestid from guest where name="Zachery Luechtefeld"),	 	"2023-3-29",  "2023-3-31" );
insert into reservation(GuestId,StartDate,EndDate)value( (select guestid from guest where name="Jeremiah Pendergrass"),	 	"2023-3-31",  "2023-4-5" );
insert into reservation(GuestId,StartDate,EndDate)value( (select guestid from guest where name="Walter Holaway"),	 	"2023-4-9",	  "2023-4-13" );
insert into reservation(GuestId,StartDate,EndDate)value( (select guestid from guest where name="Wilfred Vise"),	 "2023-4-23", "2023-4-24" );
insert into reservation(GuestId,StartDate,EndDate)value( (select guestid from guest where name="Maritza Tilton"), 	"2023-5-30",  "2023-6-2" );
insert into reservation(GuestId,StartDate,EndDate)value( (select guestid from guest where name="Joleen Tison"),	 "2023-6-10", "2023-6-14" );
insert into reservation(GuestId,StartDate,EndDate)value( (select guestid from guest where name="Joleen Tison"),	 "2023-6-10", "2023-6-14" );
insert into reservation(GuestId,StartDate,EndDate)value( (select guestid from guest where name="Aurore Lipton"),	 	"2023-6-17",  "2023-6-18" );
insert into reservation(GuestId,StartDate,EndDate)value( (select guestid from guest where name="Hussein"),	 	"2023-6-28",  "2023-7-2" );
insert into reservation(GuestId,StartDate,EndDate)value( (select guestid from guest where name="Walter Holaway"),		 "2023-7-13", "2023-7-14"	 );
insert into reservation(GuestId,StartDate,EndDate)value( (select guestid from guest where name="Wilfred Vise"),	"2023-7-18",  "2023-7-21" );
insert into reservation(GuestId,StartDate,EndDate)value( (select guestid from guest where name="Bettyann Seery"),	 	"2023-7-28",  "2023-7-29" );
insert into reservation(GuestId,StartDate,EndDate)value( (select guestid from guest where name="Bettyann Seery"),	 	"2023-8-30",   "2023-9-1" );
insert into reservation(GuestId,StartDate,EndDate)value( (select guestid from guest where name="Mack Simmer"),	 	"2023-9-16",   "2023-9-17" );
insert into reservation(GuestId,StartDate,EndDate)value( (select guestid from guest where name="Karie Yang"),	 	"2023-9-13",	"2023-9-15" );
insert into reservation(GuestId,StartDate,EndDate)value( (select guestid from guest where name="Duane Cullison"),	 	"2023-11-22",	"2023-11-25" );
insert into reservation(GuestId,StartDate,EndDate)value( (select guestid from guest where name="Mack Simmer"),	 	"2023-11-22",	"2023-11-25" );
insert into reservation(GuestId,StartDate,EndDate)value( (select guestid from guest where name="Mack Simmer"),	 	"2023-11-22",	"2023-11-25" );
insert into reservation(GuestId,StartDate,EndDate)value( (select guestid from guest where name="Maritza Tilton"),	"2023-12-24",	"2023-12-28" );

				/*RoomAmenities*/
insert into roomamenities(RoomId,AmenitiesId)value(308,	2);
insert into roomamenities(RoomId,AmenitiesId)value(203,	3);
insert into roomamenities(RoomId,AmenitiesId)value(305,	3);
insert into roomamenities(RoomId,AmenitiesId)value(201,	3);
insert into roomamenities(RoomId,AmenitiesId)value(307,	3);
insert into roomamenities(RoomId,AmenitiesId)value(302,	1);
insert into roomamenities(RoomId,AmenitiesId)value(202,	1);
insert into roomamenities(RoomId,AmenitiesId)value(304,	1);
insert into roomamenities(RoomId,AmenitiesId)value(301,	3);
insert into roomamenities(RoomId,AmenitiesId)value(207,	3);
insert into roomamenities(RoomId,AmenitiesId)value(401,	3);
insert into roomamenities(RoomId,AmenitiesId)value(206,	1);
insert into roomamenities(RoomId,AmenitiesId)value(208,	2);
insert into roomamenities(RoomId,AmenitiesId)value(304,	1);
insert into roomamenities(RoomId,AmenitiesId)value(205,	3);
insert into roomamenities(RoomId,AmenitiesId)value(204,	1);
insert into roomamenities(RoomId,AmenitiesId)value(303,	3);
insert into roomamenities(RoomId,AmenitiesId)value(305,	3);
insert into roomamenities(RoomId,AmenitiesId)value(208,	2);
insert into roomamenities(RoomId,AmenitiesId)value(203,	3);
insert into roomamenities(RoomId,AmenitiesId)value(401,	3);
insert into roomamenities(RoomId,AmenitiesId)value(206,	2);
insert into roomamenities(RoomId,AmenitiesId)value(301,	3);
insert into roomamenities(RoomId,AmenitiesId)value(302,	1);


		/*Room Reservation*/
 insert into roomreservation(reservationid,roomid,adults,children)
 values(1,308,1,0);
 insert into roomreservation(reservationid,roomid,adults,children)
 values(2,203,2,1);
 insert into roomreservation(reservationid,roomid,adults,children)
 values(3,305,2,0);
  insert into roomreservation(reservationid,roomid,adults,children)
 values(4,201,2,2);
  insert into roomreservation(reservationid,roomid,adults,children)
 values(5,307,1,1);
 insert into roomreservation(reservationid,roomid,adults,children)
 values(6,302,3,0);
insert into roomreservation(reservationid,roomid,adults,children)
 values(7,202,2,2);
 insert into roomreservation(reservationid,roomid,adults,children)
 values(8,304,2,0);
  insert into roomreservation(reservationid,roomid,adults,children)
 values(9,301,1,0);
 insert into roomreservation(reservationid,roomid,adults,children)
 values(10,207,1,1);
 insert into roomreservation(reservationid,roomid,adults,children)
 values(11,401,2,4);
  insert into roomreservation(reservationid,roomid,adults,children)
 values(12,206,2,0);
  insert into roomreservation(reservationid,roomid,adults,children)
 values(13,208,1,0);
 insert into roomreservation(reservationid,roomid,adults,children)
 values(14,304,3,0);
 insert into roomreservation(reservationid,roomid,adults,children)
 values(15,205,2,0);
 insert into roomreservation(reservationid,roomid,adults,children)
 values(16,204,3,1);
 insert into roomreservation(reservationid,roomid,adults,children)
 values(17,401,4,2);
 insert into roomreservation(reservationid,roomid,adults,children)
 values(18,303,2,1);
 insert into roomreservation(reservationid,roomid,adults,children)
 values(19,305,1,0);
 insert into roomreservation(reservationid,roomid,adults,children)
 values(20,208,2,0);
 insert into roomreservation(reservationid,roomid,adults,children)
 values(21,203,2,2);
 insert into roomreservation(reservationid,roomid,adults,children)
 values(22,401,2,2);
 insert into roomreservation(reservationid,roomid,adults,children)
 values(23,206,2,0);
 insert into roomreservation(reservationid,roomid,adults,children)
 values(24,301,2,2);
  insert into roomreservation(reservationid,roomid,adults,children)
 values(25,302,2,0);
  
 