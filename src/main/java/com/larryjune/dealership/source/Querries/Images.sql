USE larryjunedatabase;

CREATE TABLE images(
	imageID INT PRIMARY KEY auto_increment,
    vehicleID INT,
    imageURL CHAR(255),
    FOREIGN KEY (vehicleID) REFERENCES vehicleData(vehicleID)
);


INSERT INTO images (vehicleID, imageURL)
VALUES 
	(5, "TOYOTA.png");

SELECT * FROM images;

DROP TABLE images;
