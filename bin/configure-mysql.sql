# connect to mysal and run as root user
# Create Databases
CREATE DATABASE if not exists yellow_movement_site_-secure;

# Create user
CREATE USER if not exists 'yellow_movement_user'@'localhost' IDENTIFIED BY 'P@$$w0rd';

# Database Grants
GRANT SELECT, INSERT, UPDATE, DELETE ON yellow_movement_site_secure.* to 'yellow_movement_user'@'localhost';