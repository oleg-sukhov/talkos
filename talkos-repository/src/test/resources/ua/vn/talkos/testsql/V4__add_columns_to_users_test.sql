ALTER TABLE users
  ADD CO first_name VARCHAR(100),
  ADD last_name  VARCHAR(100),
  ADD email      VARCHAR(100),
  ADD CONSTRAINT email_unique UNIQUE(email);