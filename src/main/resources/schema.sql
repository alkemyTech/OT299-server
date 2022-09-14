CREATE TABLE IF NOT EXISTS SAMPLE(
  id           BIGINT(50)  NOT NULL AUTO_INCREMENT,
  description  VARCHAR(50) DEFAULT NULL,
  created_at    TIMESTAMP   DEFAULT NOW(),
  updated_at    TIMESTAMP   DEFAULT NOW(),
  deleted      BIT         DEFAULT 0,
  PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS ORGANIZATIONS(
  id_org BIGINT(50)  NOT NULL AUTO_INCREMENT,
  name  VARCHAR(50) NOT NULL,
  image VARCHAR(100) NOT NULL,
  address VARCHAR(50) NULL,
  phone INTEGER NULL,
  email VARCHAR(50) NOT NULL,
  welcome_text TEXT NOT NULL,
  about_us_text TEXT NULL,
  created_at    TIMESTAMP   DEFAULT NOW(),
  updated_at    TIMESTAMP   DEFAULT NOW(),
  deleted      BIT       DEFAULT 0,
  PRIMARY KEY (id_org)
);


