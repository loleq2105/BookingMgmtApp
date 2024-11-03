DROP TABLE IF EXISTS booking;
DROP TABLE IF EXISTS guests;
DROP TABLE IF EXISTS rooms;

CREATE TABLE IF NOT EXISTS guests (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(50) NOT NULL,
  phone varchar(20) DEFAULT NULL,
  email varchar(50) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS rooms (
  id int(11) NOT NULL AUTO_INCREMENT,
  number varchar(3) NOT NULL,
  capacity int(11) NOT NULL,
  type_id int(11) NOT NULL,
  price int(11) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS booking (
  id int(11) NOT NULL AUTO_INCREMENT,
  guest_id int(11) NOT NULL,
  room_id int(11) NOT NULL,
  start_date date NOT NULL,
  end_date date NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_booking_guest FOREIGN KEY (guest_id) REFERENCES guests (id) ON DELETE CASCADE,
  CONSTRAINT fk_booking_room FOREIGN KEY (room_id) REFERENCES rooms (id) ON DELETE CASCADE
);
