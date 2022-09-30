CREATE TABLE IF NOT EXISTS SAMPLE
(
    id          BIGINT(50) NOT NULL AUTO_INCREMENT,
    description VARCHAR(50) DEFAULT NULL,
    created_at  TIMESTAMP   DEFAULT NOW(),
    updated_at  TIMESTAMP   DEFAULT NOW(),
    deleted     BIT         DEFAULT 0,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS ORGANIZATIONS
(
    id            BIGINT(50)   NOT NULL AUTO_INCREMENT,
    name          VARCHAR(50)  NOT NULL,
    image         VARCHAR(100) NOT NULL,
    address       VARCHAR(50),
    phone         INTEGER,
    email         VARCHAR(50)  NOT NULL,
    welcome_text  TEXT         NOT NULL,
    about_us_text TEXT,
    facebook      VARCHAR(100),
    linkedin      VARCHAR(100),
    instagram     VARCHAR(100),
    created_at    TIMESTAMP DEFAULT NOW(),
    updated_at    TIMESTAMP DEFAULT NOW(),
    deleted       BIT       DEFAULT 0,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS ACTIVITIES
(
    id         BIGINT(50)   NOT NULL AUTO_INCREMENT,
    name       VARCHAR(100) NOT NULL,
    content    VARCHAR(255) NOT NULL,
    image      VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW(),
    deleted    BIT       DEFAULT 0,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS CATEGORIES
(
    id          BIGINT(50)  NOT NULL AUTO_INCREMENT,
    name        VARCHAR(50) NOT NULL,
    description VARCHAR(50) DEFAULT NULL,
    image       VARCHAR(50) DEFAULT NULL,
    created_at  TIMESTAMP   DEFAULT NOW(),
    updated_at  TIMESTAMP   DEFAULT NOW(),
    deleted    BIT       DEFAULT 0,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS NEWS
(
    id         BIGINT(50)   NOT NULL AUTO_INCREMENT,
    name       VARCHAR(50)  NOT NULL,
    content    TEXT         NOT NULL,
    image      VARCHAR(100) NOT NULL,
    category_id BIGINT(50) NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW(),
    deleted    BIT       DEFAULT 0,
    PRIMARY KEY (id),
    FOREIGN KEY (category_id) REFERENCES CATEGORIES (id)

);

CREATE TABLE IF NOT EXISTS ROLES(
  id BIGINT(50) NOT NULL AUTO_INCREMENT,
  name          VARCHAR(50) NOT NULL,
  description   VARCHAR(50) NULL,
  created_at    TIMESTAMP   DEFAULT NOW(),
  updated_at    TIMESTAMP   DEFAULT NOW(),
  deleted       BIT         DEFAULT 0,
  PRIMARY KEY (id)
  );

CREATE TABLE IF NOT EXISTS USERS(
  id        BIGINT(50)   NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(50)  NOT NULL,
  last_name  VARCHAR(50)  NOT NULL,
  email     VARCHAR(50)  NULL,
  password  VARCHAR(50)  NULL,
  photo     VARCHAR(255)  NULL,
  role_id   BIGINT(50)   NOT NULL,
  created_at    TIMESTAMP   DEFAULT NOW(),
  updated_at    TIMESTAMP   DEFAULT NOW(),
  deleted       BIT         DEFAULT 0,
  PRIMARY KEY (id),
  CONSTRAINT fkroles FOREIGN KEY (role_id) REFERENCES ROLES(id)
);
CREATE TABLE IF NOT EXISTS TESTIMONIALS
(
    id         BIGINT(50)  NOT NULL AUTO_INCREMENT,
    name       VARCHAR(50) NOT NULL,
    image      VARCHAR(100),
    content    VARCHAR(200),
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW(),
    deleted    BIT       DEFAULT 0,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS COMMENTS
(
    id         BIGINT(50) NOT NULL AUTO_INCREMENT,
    body       TEXT,
    user_id    BIGINT(50) NOT NULL,
    news_id    BIGINT(50) NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW(),
    deleted    BIT       DEFAULT 0,
    PRIMARY KEY (id),
    CONSTRAINT fkusers FOREIGN KEY (user_id) REFERENCES USERS (id),
    CONSTRAINT fknews FOREIGN KEY (news_id) REFERENCES NEWS (id)

);
CREATE TABLE IF NOT EXISTS MEMBERS(
  id   BIGINT(50)  NOT NULL AUTO_INCREMENT,
  name VARCHAR(150) NOT NULL,
  facebook_url VARCHAR(255) NOT NULL,
  instagram_url VARCHAR(255) NOT NULL,
  linkedin_url VARCHAR(255) NOT NULL,
  image VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  created_at    TIMESTAMP   DEFAULT NOW(),
  updated_at    TIMESTAMP   DEFAULT NOW(),
  deleted       BIT         DEFAULT 0,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS CONTACTS
(
    id         BIGINT(50)  NOT NULL AUTO_INCREMENT,
    name       VARCHAR(50) NOT NULL,
    phone      BIGINT(50),
    email      VARCHAR(50),
    message    VARCHAR(50),
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW(),
    deleted_at TIMESTAMP DEFAULT NOW(),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS SLIDES
(
    id          BIGINT(50) NOT NULL AUTO_INCREMENT,
    image_url   VARCHAR(100),
    slide_text  VARCHAR(200),
    slide_order BIGINT(50) UNIQUE,
    created_at  TIMESTAMP DEFAULT NOW(),
    updated_at  TIMESTAMP DEFAULT NOW(),
    deleted     BIT       DEFAULT 0,
    PRIMARY KEY (id),
    CONSTRAINT fkorganization FOREIGN KEY (organization_id) REFERENCES ORGANIZATIONS(id)
);