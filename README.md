# 927Microservices

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

### 3. start project
after starting  RabbitMQ you can start project
1 run eureka-server/src/main/java/com/scu927/EurekaServerApplication.java

2 run provider/src/main/java/com/scu927/ProviderApplication.java

3 run consumer/src/main/java/com/scu927/ConsumerApplication.java