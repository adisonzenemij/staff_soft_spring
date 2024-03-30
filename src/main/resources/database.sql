-- Eliminar la base de datos si existe
DROP DATABASE IF EXISTS bd_staff_software_spring;

-- Crear la base de datos si no existe
CREATE DATABASE IF NOT EXISTS bd_staff_software_spring CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;;

-- Eliminar la tabla si existe
DROP TABLE IF EXISTS sy_authentication;
DROP TABLE IF EXISTS tg_role_data;
DROP TABLE IF EXISTS tg_user_data;
DROP TABLE IF EXISTS tg_user_ip;
DROP TABLE IF EXISTS tg_user_test;