CREATE  TABLE users (
  username VARCHAR(45) NOT NULL,
  password VARCHAR(60) NOT NULL,
  enabled BOOLEAN NOT NULL DEFAULT true,
  PRIMARY KEY (username));