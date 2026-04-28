USE larryjunedatabase;

CREATE TABLE Sale(
	saleID INT PRIMARY KEY auto_increment,
    vehicleID INT,
    employeeAccountID INT,
    customerAccountID INT,
    dateOFSale DATE, 
    amountPaid DECIMAL(15,2),
    FOREIGN KEY (vehicleID) REFERENCES vehicleData(vehicleID),
    FOREIGN KEY (employeeAccountID) REFERENCES EmployeeAccount(employeeAccountID),
    FOREIGN KEY (customerAccountID) REFERENCES CustomerAccount(customerAccountID)
);


INSERT INTO Sale (
    vehicleID,
    employeeAccountID,
    customerAccountID,
    dateOFSale, 
    amountPaid
)
VALUES 
	(1, 1, 4, '2025-07-01', 18000),
	(2, 2, 5, '2025-07-03', 24000),
	(10, 3, 4, '2025-07-22', 41500);

SELECT * FROM Sale;

DROP TABLE Sale;
