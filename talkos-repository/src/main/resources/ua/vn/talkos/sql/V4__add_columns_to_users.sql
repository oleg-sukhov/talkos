ALTER TABLE users
  ADD COLUMN first_name VARCHAR(100),
  ADD COLUMN last_name  VARCHAR(100),
  ADD COLUMN email      VARCHAR(100),
  ADD CONSTRAINT email_unique UNIQUE(email);