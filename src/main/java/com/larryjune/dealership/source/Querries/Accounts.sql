USE larryjunedatabase;

CREATE TABLE Accounts(
	accountID INT PRIMARY KEY auto_increment,
	firstName VARCHAR(255),
    lastName VARCHAR(255),
    phone VARCHAR(20),
    accountPassword VARCHAR(30),
    email VARCHAR(255) UNIQUE,
    shippingAddress VARCHAR(255)
);


INSERT INTO Accounts (
    firstName,
    lastName,
    phone,
    accountPassword,
    email,
    shippingAddress
)
VALUES 
	('John', 'Smith', '951-555-1001', "abcdefg", "afhoawufa@gmail.com","1234 Seseme Street, NY"),   -- 1 (Employee)
	('Sarah', 'Johnson', '951-555-1002', "abcdefg", "gafafawf@gmail.com","1234 Seseme Street, NY"),-- 2 (Employee)
	('Mike', 'Brown', '951-555-1003', "abcdefg", "rhgshsahsre@gmail.com","1234 Seseme Street, NY"),   -- 3 (Manager)
	('Emily', 'Davis', '951-555-1004', "abcdefg", "fafawfawfsawd@gmail.com","1234 Seseme Street, NY"),  -- 4 (Customer)
	('Chris', 'Wilson', '951-555-1005', "abcdefg", "gfaeghsrhrs@gmail.com","1234 Seseme Street, NY"), -- 5 (Customer)
	('Laura', 'Martinez', '951-555-1006', "abcdefg", "fawfsdawfsda@gmail.com","1234 Seseme Street, NY"),-- 6 (Employee)
	('David', 'Anderson', '951-555-1007', "abcdefg", "hthdhshr@gmail.com","1234 Seseme Street, NY"),-- 7 (Manager)
	('Sophia', 'Taylor', '951-555-1008', "abcdefg", "fawfgrheshsf@gmail.com","1234 Seseme Street, NY"); -- 8 (Customer)

SELECT * FROM Accounts;

DROP TABLE Accounts;
