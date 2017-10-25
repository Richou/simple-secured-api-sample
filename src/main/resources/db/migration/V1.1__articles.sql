CREATE TABLE articles
(
  id UUID PRIMARY KEY NOT NULL,
  user_id UUID NOT NULL REFERENCES users(id),
  title VARCHAR(255),
  content VARCHAR,
  date_creation TIMESTAMP,
  date_updated TIMESTAMP
);

CREATE TABLE categories
(
  id UUID PRIMARY KEY NOT NULL,
  key VARCHAR(32) NOT NULL,
  CONSTRAINT category_key_uk UNIQUE(key)
);

CREATE TABLE articles_categories
(
  article_id UUID NOT NULL REFERENCES articles(id) ON DELETE CASCADE,
  categorie_id UUID NOT NULL REFERENCES categories(id),
  PRIMARY KEY (article_id, categorie_id)
);

INSERT INTO categories(id, key) VALUES ('d434ecd6-46c3-4b02-ad30-8ce3636d481b', 'COOKING');
INSERT INTO categories(id, key) VALUES ('0b0f2a36-c0be-4473-a196-2d04688554d0', 'TECHNOLOGY');