CREATE TABLE IF NOT EXISTS SAMPLE(
  id           BIGINT(50)  NOT NULL AUTO_INCREMENT,
  description  VARCHAR(50) DEFAULT NULL,
  created_at    TIMESTAMP   DEFAULT NOW(),
  updated_at    TIMESTAMP   DEFAULT NOW(),
  deleted      BIT         DEFAULT 0,
  PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS COMMENTS(
  id           BIGINT(50)  NOT NULL AUTO_INCREMENT,
  body  TEXT DEFAULT NULL,
  user_id BIGINT(50) NOT NULL,
  news_id BIGINT(50) NOT NULL,
  created_at    TIMESTAMP   DEFAULT NOW(),
  updated_at    TIMESTAMP   DEFAULT NOW(),
  deleted      BIT         DEFAULT 0,
  PRIMARY KEY (id)

);
