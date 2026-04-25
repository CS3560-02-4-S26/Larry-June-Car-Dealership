USE larryjunedatabase;

CREATE TABLE damage(
	damageID INT PRIMARY KEY auto_increment,
    vehicleID INT,
    locationOfDamage VARCHAR(255),
    severity VARCHAR(255),
    repairCost INT, 
    accidentID INT,
    airbagDeployment BOOLEAN,
    FOREIGN KEY (vehicleID) REFERENCES vehicleData(vehicleID),
    FOREIGN KEY (accidentID) REFERENCES AccidentData(accidentID)
);


INSERT INTO damage (
    vehicleID, 
    locationOfDamage,
    severity,
    repairCost, 
    accidentID,
    airbagDeployment
)
VALUES 
	(1, 'Rear bumper', 'Moderate', 3200, 1, 0),
	(4, 'Front suspension', 'Severe', 7800, 5, 0),
    (6, 'Front end frame', 'Severe', 10500, 2, 1),
    (7, 'Hood and grille', 'Severe', 6000, 3, 0),
    (8, 'Multiple panels', 'Severe', 15000, 4, 1);

SELECT * FROM damage;

DROP TABLE damage;
