# 927Microservices

## Toolchains Used

- Java Version: 21
- Maven Version: 3.9.9
- RabbitMQ Version: 3.13.7
- Erlang Version: 27.0.1
- MySQL Version: 8.0.39

## Project Description

This is a microservices-based project, which includes multiple services such as `consumer` and `provider`.

## Prerequisite

Following tools are required before we begin the execution:

### 1. Install Erlang (Required for RabbitMQ)

RabbitMQ depends on Erlang, so Erlang must be installed.

You can download and install Erlang from the official website: [Erlang Installation Guide](https://www.erlang.org/downloads).

### 2. Install RabbitMQ

Once Erlang is installed, proceed to install RabbitMQ. You can download and install RabbitMQ from the official guide: [RabbitMQ Installation Guide](https://www.rabbitmq.com/download.html).

### 3. Modify Email Configuration

Open the `consumer/src/main/resources/application.yml` file and update the following section with a test email setting:

```
mail:
  host: smtp.your-email-provider.com   # Replace with test email provider's SMTP server
  port: 587                            # Replace with correct port (e.g., 587 for TLS, 465 for SSL)
  username: your-email@example.com     # Replace with test email address
  password: your-email-auth-code        # Replace with test email authorization code (not the login password) 
```

#### 4. Setup Database

1. Install MySQL
    You can download and install MySQL from the official website: [MySQL Installation Guide](https://dev.mysql.com/downloads/mysql/).
    Make sure to install MySQL version 8.0.39 (or compatible) to ensure full compatibility with this project.

2. Create Databases and Users
    Create two databases: provider_db and consumer_db as per the configurations in the application.yml file. Create a database user and assign appropriate permissions for both databases.
   
   ```mysql
   -- Create the provider_db database
   CREATE DATABASE provider_db;
   
   -- Create the consumer_db database
   CREATE DATABASE consumer_db;
   
   -- Create a user and assign permissions for both databases
   CREATE USER 'root'@'localhost' IDENTIFIED BY '123456';
   
   -- Grant the user privileges for provider_db
   GRANT ALL PRIVILEGES ON provider_db.* TO 'root'@'localhost';
   
   -- Grant the user privileges for consumer_db
   GRANT ALL PRIVILEGES ON consumer_db.* TO 'root'@'localhost';
   
   -- Refresh privileges
   FLUSH PRIVILEGES;
   ```

3. Execute SQL Scripts
   In the following path: `927Microservices/sql/`, you will find some SQL scripts for creating the tables and inserting initial data. These scripts include table definitions for the provider_db and consumer_db, as well as INSERT statements for populating the tables with test data. Execute the SQL scripts in your MySQL environment to set up the database structure and initial data.

### 5. Start project

After starting  RabbitMQ server you can turn on microservices (You should use an IDE so it will auto configure for you):

1. Install all maven dependencies by running `mvn clean install`

2. Execute eureka-server/src/main/java/com/scu927/EurekaServerApplication.java

3. Execute provider/src/main/java/com/scu927/ProviderApplication.java

4. Execute consumer/src/main/java/com/scu927/ConsumerApplication.java

### 6. Test & Play with APIs

1. Testing with Swagger
   You can use Swagger for API testing, which provides a more user-friendly visual interface. Once the project is running, you can access Swagger UI at the following [URL](http://127.0.0.1:8082/swagger-ui/index.html)
   
   - Testing steps:
     First, use the login API to authenticate. The username is 8259665, and the password is 123. You can also create your own account in the user table of the database by following the format of the first entry.
     After a successful login, Swagger will return a JWT token. Click the Authorize button (lock icon) in the top right corner of the Swagger UI and input the JWT token you received in the format Bearer <your_token>, then click "Authorize." After authorization, close the dialog and proceed with testing the other APIs.

2. Testing with Postman
   You can also use Postman for testing the APIs. Please refer to the final report for specific parameters and request formats.

## One More Thing

Scripts for Task F (i) generating process logs are located in the following path: `/927Microservices/provider/src/main/java/com/scu927/mock/`.


