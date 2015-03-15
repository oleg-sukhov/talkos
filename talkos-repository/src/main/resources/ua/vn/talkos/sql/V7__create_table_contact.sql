CREATE TABLE IF NOT EXISTS contact (
  id BIGSERIAL NOT NULL,
  state INTEGER NOT NULL,
  account_id BIGINT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (account_id) REFERENCES account (id)
);