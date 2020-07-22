# connect to mysql and run as root user
# Create Databases
CREATE DATABASE if not exists yellow_movement_site;

# Create user
CREATE USER if not exists 'yellow_movement_user'@'localhost' IDENTIFIED BY 'P@$$w0rd';

# Database Grants
GRANT ALL ON yellow_movement_site.* to 'yellow_movement_user'@'localhost';