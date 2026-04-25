USE larryjunedatabase;

CREATE TABLE Service(
	serviceID INT PRIMARY KEY auto_increment,
    vehicleID INT,
    dateOfService DATE,
    descriptionOFService VARCHAR(255),
    cost DECIMAL(15,2), 
    mileage INT,
    FOREIGN KEY (vehicleID) REFERENCES vehicledata(vehicleID)
);


INSERT INTO Service (
    vehicleID,
    dateOfService,
    descriptionOFService,
    cost, 
    mileage
)
VALUES 
	(3, '2025-03-01', 'Transmission fluid flush', 300, 76000),
	(6, '2025-04-18', 'Clutch adjustment and alignment', 600, 32000),
	(7, '2025-05-02', 'Oil change and air filter replacement', 180, 40000),
	(10,'2025-06-28', 'Detailing and full inspection service', 200, 14000);;

SELECT * FROM Service;

DROP TABLE Service;
