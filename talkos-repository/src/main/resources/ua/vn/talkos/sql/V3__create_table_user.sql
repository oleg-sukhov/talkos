CREATE TABLE IF NOT EXISTS "User" (
  id BIGSERIAL NOT NULL,
  username VARCHAR(45) NOT NULL UNIQUE,
  password VARCHAR(60) NOT NULL,
  enabled BOOLEAN NOT NULL DEFAULT true,
  PRIMARY KEY (id));