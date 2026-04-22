USE larryjunedatabases;

CREATE TABLE ManagerAccount(
	managerAccountID INT,
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
