CREATE DATABASE IF NOT EXISTS dronepizza;
USE dronepizza;

CREATE USER IF NOT EXISTS 'admin'@'localhost' IDENTIFIED BY 'admin123';
GRANT USAGE ON *.* TO 'admin'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE ON dronepizza.* TO 'admin'@`localhost`;


-- Table for Station
CREATE TABLE IF NOT EXISTS station (
                                       station_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       latitude DOUBLE NOT NULL,
                                       longitude DOUBLE NOT NULL
);

-- Table for Pizza
CREATE TABLE IF NOT EXISTS pizza (
                                     pizza_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     title VARCHAR(255) NOT NULL UNIQUE,
                                     price INT NOT NULL
);

-- Table for Drone
CREATE TABLE IF NOT EXISTS drone (
                                     drone_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     serial_uuid CHAR(36) NOT NULL UNIQUE,
                                     driftsstatus ENUM('ENABLED', 'DISABLED', 'RETIRED') NOT NULL,
                                     station_id BIGINT NOT NULL,
                                     FOREIGN KEY (station_id) REFERENCES station(station_id)
);

-- Table for Delivery
CREATE TABLE IF NOT EXISTS delivery (
                                        delivery_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        address VARCHAR(255) NOT NULL,
                                        expected_delivery TIMESTAMP NOT NULL,
                                        actual_delivery TIMESTAMP,
                                        drone_id BIGINT,
                                        pizza_id BIGINT NOT NULL,
                                        FOREIGN KEY (drone_id) REFERENCES drone(drone_id),
                                        FOREIGN KEY (pizza_id) REFERENCES pizza(pizza_id)
);

FLUSH PRIVILEGES;