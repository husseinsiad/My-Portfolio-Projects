/*1:Write a query that returns a list of reservations that end in July 2023, including the name of the guest, 
the room number(s), and the reservation dates.*/
use hoteldb;
SELECT
  g.name,
  r.roomid,
  re.StartDate,
  re.EndDate
FROM room r
JOIN roomreservation rr
  ON r.roomid = rr.roomid
JOIN reservation re
  ON re.reservationid = rr.reservationid
JOIN guest g
  ON g.guestid = re.guestid
WHERE re.EndDate BETWEEN "2023-07-01" AND "2023-07-31";
 
/*2:Write a query that returns a list of all reservations for rooms with a jacuzzi, displaying the guest's name,
 the room number, and the dates of the reservation.*/
SELECT
  g.name,
  r.roomid,
  re.StartDate,
  re.EndDate
FROM room r
JOIN roomreservation rr
  ON r.roomid = rr.roomid
JOIN reservation re
  ON re.reservationid = rr.reservationid
JOIN guest g
  ON g.guestid = re.guestid
JOIN roomamenities ra
  ON ra.roomid = r.roomid
JOIN amenities a
  ON ra.amenitiesid = a.amenitiesid
WHERE a.amenitiesid = 3;
 
	/*3: Write a query that returns all the rooms reserved for a specific guest, 
    including the guest's name, the room(s) reserved, the starting date of the reservation, 
    and how many people were included in the reservation. 
    (Choose a guest's name from the existing data.)*/
    
	SELECT
  g.name,
  r.roomid,
  re.StartDate,
  rr.adults,
  rr.children
FROM room r
JOIN roomreservation rr
  ON r.roomid = rr.roomid
JOIN reservation re
  ON re.reservationid = rr.reservationid
JOIN guest g
  ON g.guestid = re.guestid
WHERE g.name = "Wilfred Vise";
 
 /*4: Write a query that returns a list of rooms, reservation ID, and per-room cost for each reservation.
 The results should include all rooms, whether or not there is a reservation associated with the room.*/
	SELECT
  r.roomid,
  rr.reservationid,
  rt.price
FROM roomtype rt
JOIN room r
  ON r.roomtypeid = rt.roomtypeid
LEFT JOIN roomreservation rr
  ON rr.roomid = r.roomid
ORDER BY 1;

/* 5: Write a query that returns all the rooms accommodating at least three guests 
and that are reserved on any date in April 2023.*/
		SELECT
  r.roomid,
  rt.MaximumOccupancy
FROM roomtype rt
JOIN room r
  ON r.roomtypeid = rt.roomtypeid
JOIN roomreservation rr
  ON rr.roomid = r.roomid
JOIN reservation re
  ON rr.reservationid = re.reservationid
WHERE rt.MaximumOccupancy <= 3
AND re.startdate BETWEEN "2023-04-01" AND "2023-04-30";
/*6: Write a query that returns a list of all guest names and the number of reservations per guest, 
 sorted starting with the guest with the most reservations and then by the guest's last name.*/
	SELECT
  g.Name,
  COUNT(rr.reservationid) NumberOfReservations
FROM guest g
JOIN reservation r
  ON g.guestid = r.guestid
JOIN roomreservation rr
  ON r.reservationid = rr.reservationid
GROUP BY g.name
ORDER BY NumberOfReservations DESC;
/*7: Write a query that displays the name, address, and phone number of a guest based on their phone number. 
(Choose a phone number from the existing data.)*/
	SELECT
  Name,
  Address,
  phone
FROM guest
WHERE phone = "9522283307"