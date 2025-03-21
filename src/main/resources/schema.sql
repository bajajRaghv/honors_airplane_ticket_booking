CREATE TABLE FLIGHT (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    flight_number VARCHAR(255),
    origin VARCHAR(255),
    destination VARCHAR(255)
);

CREATE TABLE SCHEDULE (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    flight_id BIGINT,
    departure_time TIMESTAMP,
    arrival_time TIMESTAMP,
    available_seats INT,
    FOREIGN KEY (flight_id) REFERENCES FLIGHT(id)
);

CREATE TABLE TICKET (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    schedule_id BIGINT,
    passenger_name VARCHAR(255),
    seat_number VARCHAR(255),
    price DECIMAL(19,2),
    status VARCHAR(255),
    FOREIGN KEY (schedule_id) REFERENCES SCHEDULE(id)
);