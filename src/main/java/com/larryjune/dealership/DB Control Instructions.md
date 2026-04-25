# Importing the Database

1. Go to MySQL Workbench and connect to your MySQL server
2. Go to the _Server_ menu and select _Data Import_.
3. Select the second option and select `larryjunedatabase.sql` from the `source` folder
4. Check if you have the right database selected which should be "larryjunedatabase"
5. Press Start Import
  - (If you have a small screen size change your resolution on system settings)
6. Go to the DBConnection.java File on your text editor
7. Make sure the urls last data entry is the name of the schema you created
    (Ideally "larryjunedatabase")
    The user set as the user that created the database (Usually "root")
    And the Password as whatever you put in when setting up MySQL server
    (Ideally "ILoveCars2000")

With this the database should be set up and you can freely use the fetch functions
on the DBControl class.
