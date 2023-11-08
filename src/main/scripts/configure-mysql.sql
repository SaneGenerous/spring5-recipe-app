#Create databases
CREATE DATABASE msk_dev;
CREATE DATABASE msk_prod;

#Create database service accounts
CREATE USER 'msk_dev_user'@'localhost' IDENTIFIED BY 'mohammed';
CREATE USER 'msk_prod_user'@'localhost' IDENTIFIED BY 'mohammed';
CREATE USER 'msk_dev_user'@'%' IDENTIFIED BY 'mohammed';
CREATE USER 'msk_prod_user'@'%' IDENTIFIED BY 'mohammed';

#Database grants
GRANT SELECT ON msk_dev.* to 'msk_dev_user'@'localhost';
GRANT INSERT ON msk_dev.* to 'msk_dev_user'@'localhost';
GRANT DELETE ON msk_dev.* to 'msk_dev_user'@'localhost';
GRANT UPDATE ON msk_dev.* to 'msk_dev_user'@'localhost';
GRANT SELECT ON msk_prod.* to 'msk_prod_user'@'localhost';
GRANT INSERT ON msk_prod.* to 'msk_prod_user'@'localhost';
GRANT DELETE ON msk_prod.* to 'msk_prod_user'@'localhost';
GRANT UPDATE ON msk_prod.* to 'msk_prod_user'@'localhost';
GRANT SELECT ON msk_dev.* to 'msk_dev_user'@'%';
GRANT INSERT ON msk_dev.* to 'msk_dev_user'@'%';
GRANT DELETE ON msk_dev.* to 'msk_dev_user'@'%';
GRANT UPDATE ON msk_dev.* to 'msk_dev_user'@'%';
GRANT SELECT ON msk_prod.* to 'msk_prod_user'@'%';
GRANT INSERT ON msk_prod.* to 'msk_prod_user'@'%';
GRANT DELETE ON msk_prod.* to 'msk_prod_user'@'%';
GRANT UPDATE ON msk_prod.* to 'msk_prod_user'@'%';