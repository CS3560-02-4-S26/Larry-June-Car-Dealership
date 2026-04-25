USE larryjunedatabase;

CREATE TABLE EmployeeAccount(
	employeeAccountID INT,
	totalSales DECIMAL(15, 2),
    FOREIGN KEY (employeeAccountID) REFERENCES Accounts(accountID)
);


INSERT INTO EmployeeAccount (
	employeeAccountID,
    totalSales
)
VALUES 
	(1, 15),
	(2, 22),
	(3, 40),
	(6, 10),
	(7, 55);

SELECT * FROM EmployeeAccount;

DROP TABLE EmployeeAccount;
