USE larryjunedatabase;

CREATE TABLE ManagerAccount(
	managerAccountID INT PRIMARY KEY,
    managerstatus VARCHAR(255),
    FOREIGN KEY (managerAccountID) REFERENCES EmployeeAccount(employeeAccountID)
);


INSERT INTO ManagerAccount (
    managerAccountID,
    managerstatus
)
VALUES 
	(3, "GONE"),
	(7, "GONE");

SELECT * FROM ManagerAccount;

DROP TABLE ManagerAccount;
