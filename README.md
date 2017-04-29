# Java Devoir 2017: Rental Site 

## Members:
- **CAO** Anh Quân 
- **ĐỖ** Sơn Tùng
- **TRƯƠNG** Quang Hiếu 

## How to run the application

### Run the normal server and normal client
1. Open new terminal.
2. Change the current directory to the production directory:
```
cd /PROJECT_LOCATION/production
```
3. Run the server by execute the command: 
```
java -jar normal_server.jar 
```
4. Open new terminal.
5. Change the current directory to the production directory:
```
cd /PROJECT_LOCATION/production
```
6. Run the client by execute the command: 
```
java -jar normal_client.jar
```

7. Follow the instructions in the How to use section to use the program

### Run the administration server and administration client
1. Open new terminal.
2. Change the current directory to the production directory:
```
cd /PROJECT_LOCATION/production
```
3. Run the server by execute the command: 
```
java -jar administration_server.jar 
```
4. Open new terminal.
5. Change the current directory to the production directory:
```
cd /PROJECT_LOCATION/production
```
6. Run the client by execute the command: 
```
java -jar administration_client.jar
```

7. Follow the instructions in the How to use section to use the program


## How to use

### Normal Client Application
This client is used to access to the server to request several information which is required in this project.

From this Client Application, in order to request information from the server, you need to authorized with the specific account, or you need to register one.

(*For testing purpose, you can use example accounts provided below to access to the server*)

The client will be separate to 2 types: Tenant Client, and Renter Client


**In order to use the application, you can login using the predefined accounts or register new account using the application** 

**Tenant Client** will be able to:
1. List out the available rental with one of the following criteria:
    * All available rental
    * All available rental with the monthly rent below a certain amount
    * All available rental with a certain number of room
2. Reserve an available rental for a exact duration

**Renter Client** will be able to:
1. List all the rental of his/her apartments.
2. List all the tenant of a renter.
3. Check the information about a rental (A Tenant of a Rental).
4. Create new rental.
5. Remove a rental.
6. A new apartment can be created by renter.

### Administration Client Application
Unlike the Casual Client, Administration Client will have the right to access to the server and do several actions dedicated to the admin.
1. Show the list of all the rental
2. The admin can have the right to directly delete a rental.

 

### Example account to login
- Renter: email: renter@gmail.com, password: 12345678
- Tenant: email: tenant@gmail.com, password: 12345678


