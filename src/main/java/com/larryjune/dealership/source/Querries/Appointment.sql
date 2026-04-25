USE larryjunedatabase;

CREATE TABLE Appointment(
	employeeAccountID INT,
    customerAccountID INT,
    apointmentDate DATE,
    typeOfAppointment CHAR(255),
    FOREIGN KEY (customerAccountID) REFERENCES CustomerAccount(customerAccountID),
    FOREIGN KEY (employeeAccountID) REFERENCES EmployeeAccount(employeeAccountID)
);


INSERT INTO Appointment (
    employeeAccountID,
    customerAccountID,
    apointmentDate,
    typeOfAppointment
)
VALUES 
	(1, 4, '2025-07-02', 'Test Drive'),
	(2, 5, '2025-07-04', 'Financing Consultation'),
	(3, 8, '2025-07-06', 'Vehicle Inquiry'),
	(6, 4, '2025-07-09', 'Service Follow-up'),
	(7, 5, '2025-07-11', 'Trade-in Evaluation'),
	(1, 8, '2025-07-13', 'Test Drive'),
	(2, 4, '2025-07-16', 'Purchase Discussion'),
	(3, 5, '2025-07-19', 'Final Paperwork'),
	(6, 8, '2025-07-21', 'Warranty Consultation'),
	(7, 4, '2025-07-23', 'Vehicle Pickup');

SELECT * FROM Appointment;

DROP TABLE Appointment;
