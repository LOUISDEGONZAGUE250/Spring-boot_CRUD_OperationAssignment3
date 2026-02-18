-- SQL script to create database and verify
-- Run this in pgAdmin or psql

-- Create database if not exists (run once)
SELECT 'CREATE DATABASE ecommerce_db' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'ecommerce_db')\gexec

-- Connect to ecommerce_db and verify
\c ecommerce_db

-- List tables
\dt
