ALTER TABLE users ADD role varchar(20);
UPDATE users SET role = "ADMIN";