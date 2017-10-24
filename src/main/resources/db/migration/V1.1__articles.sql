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
  label VARCHAR(255) NOT NULL
);

CREATE TABLE articles_categories
(
  id UUID PRIMARY KEY NOT NULL,
  article_id UUID NOT NULL REFERENCES articles(id),
  categorie_id UUID NOT NULL REFERENCES categories(id)
);