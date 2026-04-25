USE larryjunedatabase;

CREATE TABLE vehicleData(
	vehicleID INT PRIMARY KEY auto_increment,
    vinNumber CHAR(17),
    price DECIMAL(15, 2),
    maker VARCHAR(255),
    model VARCHAR(255),
    color VARCHAR(255),
    modelYear INT,
    bodyStyle CHAR(255),
    isUsed BOOLEAN,
    mileage INT,
    carStatus VARCHAR(255),
    prevOwnerCount INT
);

INSERT INTO vehicledata (vinNumber, price, maker, model, color, modelYear, bodyStyle, isUsed, mileage, carStatus, prevOwnerCount)
VALUES 
	('1HGCM82633A004352', 18500, 'Honda', 'Accord', 'Black', 2018, 'Sedan', 1, 62000, 1, 1),
	('2FTRX18W1XCA01234', 24500, 'Ford', 'F-150', 'Blue', 2020, 'Truck', 1, 45000, 1, 1),
	('3VWFE21C04M000111', 13200, 'Volkswagen', 'Jetta', 'White', 2017, 'Sedan', 1, 78000, 1, 2),
	('5YJ3E1EA7KF000222', 35999, 'Tesla', 'Model 3', 'Red', 2021, 'Sedan', 0, 12000, 1, 0),
	('1NXBR32E54Z123456', 9800, 'Toyota', 'Corolla', 'Silver', 2015, 'Sedan', 1, 99000, 0, 2),
	('JN1CV6AP8CM123789', 27500, 'Nissan', '370Z', 'Yellow', 2019, 'Coupe', 1, 34000, 1, 1),
	('WA1LFAFP2DA123456', 31000, 'Audi', 'Q5', 'Gray', 2020, 'SUV', 1, 41000, 1, 1),
	('2C3CDXHG0JH123987', 22000, 'Dodge', 'Charger', 'Black', 2018, 'Sedan', 1, 55000, 1, 2),
	('1FA6P8TH5H5123456', 26000, 'Ford', 'Mustang', 'Blue', 2019, 'Coupe', 1, 30000, 1, 1),
	('SALWR2RV5GA123321', 42000, 'Land Rover', 'Range Rover Sport', 'White', 2021, 'SUV', 0, 15000, 1, 0);
    
SELECT * FROM vehicleData;

SELECT * FROM vehicleData WHERE maker = "Toyota";

DROP TABLE vehicleData;
