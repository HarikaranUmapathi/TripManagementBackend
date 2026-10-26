CREATE TABLE driver_entity
(
    id             BIGINT       NOT NULL,
    driver_name    VARCHAR(255) NOT NULL,
    experience     INT          NOT NULL,
    contact_number BIGINT       NOT NULL,
    CONSTRAINT pk_driverentity PRIMARY KEY (id)
);

CREATE TABLE trip_entity
(
    trip_id             INT AUTO_INCREMENT PRIMARY KEY,

    booked_by           VARCHAR(100),
    booked_date         DATE,
    duration            VARCHAR(50),
    booked_on           VARCHAR(50),
    total_trip_distance VARCHAR(50),

    driver_id           INT,
    vehicle_id          BIGINT,

    soft_delete         BOOLEAN DEFAULT FALSE,

    created_date        DATE,
    updated_date        DATE,

    CONSTRAINT fk_trip_driver
        FOREIGN KEY (driver_id)
            REFERENCES driver_entity (id),

    CONSTRAINT fk_trip_vehicle
        FOREIGN KEY (vehicle_id)
            REFERENCES vehicle_entity (vehicle_id)
);

CREATE TABLE vehicle_entity
(
    vehicle_id          INT NOT NULL,
    vehicle_type        VARCHAR(255) NULL,
    registration_number BIGINT NULL,
    is_insured          BIT(1) NULL,
    CONSTRAINT pk_vehicleentity PRIMARY KEY (vehicle_id)
);

ALTER TABLE driver_entity
    ADD CONSTRAINT uc_driverentity_contactnumber UNIQUE (contact_number);

ALTER TABLE driver_entity
    ADD CONSTRAINT uc_driverentity_drivername UNIQUE (driver_name);

ALTER TABLE vehicle_entity
    ADD CONSTRAINT uc_vehicleentity_registrationnumber UNIQUE (registration_number);

ALTER TABLE trip_entity
    ADD CONSTRAINT FK_TRIPENTITY_ON_ASSIGNEDDRIVER FOREIGN KEY (assigned_driver) REFERENCES driver_entity (driver_name);

ALTER TABLE trip_entity
    ADD CONSTRAINT FK_TRIPENTITY_ON_ASSIGNEDVEHICLE FOREIGN KEY (assigned_vehicle) REFERENCES vehicle_entity (vehicle_id);

