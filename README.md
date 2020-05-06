# Seminar registration portal

This project is built complete using core java. It is a GUI application for booking halls for seminars and lectures. It uses features of core java like Socket programming,  Multi-threading, asynchronous callbacks and Java Swing is used to build the GUI. It is connected with the mysql database (JDBC).


# Installation and configuration


## Prerequisites
1. Eclipse IDE
2. MySQL workbench installed ([Download from here](https://dev.mysql.com/downloads/workbench/))
3. Mysql connector jar file ([Download from here](https://www.mysql.com/products/connector/))




## Configuring mysql server

1.  After installing the MySQL workbench, create a local connection and set a password.
2. Import the database using the **db** folder which contains all the sql files.
3. The database will be set up.


## Running the project in eclipse

1. In eclipse, import this project you just cloned. Now remember this application has a client-server architecture.
2. So go to **/src/test** and run the file second.java.
3. You will see a message on the console saying the server is started.
4. Then go to **/src/GUI** and start the Client_GUI.java. It will start the client side GUI.
5. The project is running. Try different menu options and see how it works.

Here are few screenshots of the project.

<img src="imgs/Screenshot%20(7).png" width="49%">   <img src="imgs/Screenshot%20(11).png" width="49%">



## Troubleshooting

1. Make sure all jar files have been added. Check the deployment assembly of the eclipse project  and add mysql-connector  in the list.
2. If mysql is not connected, check the database name and password in MySQL workbench. Password="root" and database name="hotelmanagement".
3. If you want to use different password and database name, goto **/src/jdbcCode** and open connect.java. Edit line 24.


## Author

**Sarita Joshi**
**TE Computer Engineering student at PICT**


