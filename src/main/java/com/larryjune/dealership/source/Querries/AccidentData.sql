USE larryjunedatabase;

CREATE TABLE AccidentData(
	accidentID INT PRIMARY KEY auto_increment,
    vehicleID INT,
    dateOFAccident DATE, 
	severity VARCHAR(255),
    descOfAccident VARCHAR(255),
	airbagDeployment BOOLEAN,
    FOREIGN KEY (vehicleID) REFERENCES vehicledata(vehicleID)
);


INSERT INTO AccidentData (
    vehicleID,
    dateOFAccident, 
    severity,
    descOfAccident,
    airbagDeployment
)
VALUES 
	(1, '2025-01-12','Bad' , 'Rear-end collision at stoplight', 0),
    (6, '2025-04-10', 'Bad' ,'Intersection T-bone accident', 0),
	(7, '2025-05-01', 'Bad' ,'Deer collision on rural road', 1),
	(8, '2025-05-19','really Bad' , 'Multi-car freeway pileup', 0),
    (4, '2025-10-05', 'Bad' ,'Single vehicle curb collision', 1);

SELECT * FROM AccidentData;

DROP TABLE AccidentData;
