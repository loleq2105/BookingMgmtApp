INSERT INTO guests (name, phone, email) VALUES
('Arlene Maklovich', '+23 233 433 234', 'arlie@doubleclick.biz'),
('Richardine Stonewall', '+58 923 482 034', 'yeah@yahoo.com'),
('Paweł Afroamerykanowicz', '+43 543 645 234', 'pafroamerykanowicz@bije.zone');

INSERT INTO room_types (name) VALUES
('Apartament'),
('Kawalerka'),
('Ekonomiczy'),
('Standard');

INSERT INTO rooms (number, capacity, type_id, price) VALUES
('22', 2, 1, 60),
('1', 1, 2, 84);

INSERT INTO booking (guest_id, room_id, start_date, end_date) VALUES
(1, 2, '2024-11-25', '2024-11-30'),
(2, 1, '2024-11-01', '2024-11-08'),
(3, 1, '2024-10-09', '2024-10-23');