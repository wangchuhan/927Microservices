# 927Microservices

# Prerequisites
Java Version: 21
Maven Version: 3.9.9
RabbitMQ Version: 3.13.7
Erlang Version: 27.0.1
MySQL Version: 8.0.39
## Project Description
This is a microservices-based project, which includes multiple services such as `consumer` and `provider`.

## Before Running the Project

Before running the project, ensure the following steps are completed:

### 1. Install Erlang (Required for RabbitMQ)

RabbitMQ depends on Erlang, so you must install Erlang before installing RabbitMQ.

- **Erlang Installation Guide**:  
  You can download and install Erlang from the official website: [Erlang Installation Guide](https://www.erlang.org/downloads).

### 2. Install RabbitMQ

Once Erlang is installed, proceed to install RabbitMQ. You can download and install RabbitMQ from the official guide: [RabbitMQ Installation Guide](https://www.rabbitmq.com/download.html).

### 3. Modify Email Configuration

Open the `consumer/src/main/resources/application.yml` file and update the following section with your own email settings:


mail:
  host: smtp.your-email-provider.com   # Replace with your email provider's SMTP server
  port: 587                            # Replace with the correct port (e.g., 587 for TLS, 465 for SSL)
  username: your-email@example.com     # Replace with your email address
  password: your-email-password        # Replace with your email authorization code (not the login password) 

#### 4. 
  1. Install MySQL
   Before running the project, you need to install MySQL. You can download and install MySQL from the official website: https://dev.mysql.com/downloads/mysql/.

   Make sure to install MySQL version 8.0.39 (or compatible) to ensure full compatibility with this project.

  1. Create Databases and Users
  Create the provider_db and consumer_db databases as per the configurations in the application.yml file.
  Create a database user and assign appropriate permissions for both databases.
  DDl:
     -- 创建 provider_db 数据库
     -- Create the provider_db database
       CREATE DATABASE provider_db;

     -- 创建 consumer_db 数据库
     -- Create the consumer_db database
     CREATE DATABASE consumer_db;

     -- 创建用户并为两个数据库赋予权限
    -- Create a user and assign permissions for both databases
     CREATE USER 'root'@'localhost' IDENTIFIED BY '123456';

    -- 为 provider_db 赋予用户权限
    -- Grant the user privileges for provider_db
    GRANT ALL PRIVILEGES ON provider_db.* TO 'root'@'localhost';

    -- 为 consumer_db 赋予用户权限
    -- Grant the user privileges for consumer_db
    GRANT ALL PRIVILEGES ON consumer_db.* TO 'root'@'localhost';

     -- 刷新权限
    -- Refresh privileges
      FLUSH PRIVILEGES;
 2. Execute SQL Scripts
    In the sql folder (on the left), you will find the SQL scripts for creating the tables and inserting initial data. These scripts include table definitions for the provider_db and consumer_db, as well as INSERT statements for populating the tables with test data.
 3. Execution
  Execute the SQL scripts in your MySQL environment to set up the database structure and initial data.



### 5. start project
after starting  RabbitMQ you can start project
1 run eureka-server/src/main/java/com/scu927/EurekaServerApplication.java

2 run provider/src/main/java/com/scu927/ProviderApplication.java

3 run consumer/src/main/java/com/scu927/ConsumerApplication.java