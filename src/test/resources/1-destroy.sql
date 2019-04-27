-- drop tables with foreign keys first ---
-- (alternatively, drop foreign keys first) --
CREATE DATABASE IF NOT EXISTS `academi-cymraeg`;
USE `academi-cymraeg`;

DROP TABLE IF EXISTS Results;
DROP TABLE IF EXISTS Dictionary;
DROP TABLE IF EXISTS Welsh;
DROP TABLE IF EXISTS English;
DROP TABLE IF EXISTS User;