USE larryjunedatabases;

CREATE TABLE CustomerAccount(
	customerAccountID INT,
    FOREIGN KEY (customerAccountID) REFERENCES Accounts(accountID)
);


INSERT INTO CustomerAccount (
    customerAccountID
)
VALUES 
	(4),
	(5),
	(8);

SELECT * FROM CustomerAccount;

DROP TABLE CustomerAccount;
