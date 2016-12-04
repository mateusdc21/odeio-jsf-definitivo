CREATE OR UPDATE TABLE usuario (
	id SERIAL PRIMARY KEY NOT NULL,
	name TEXT NOT NULL,
	email TEXT NOT NULL,
	password TEXT NOT NULL,
	dateofbirth TEXT NOT NULL,
	avatar TEXT NOT NULL,
	username TEXT NOT NULL
);

CREATE TABLE posts (
	id SERIAL PRIMARY KEY NOT NULL,
	usuario INTEGER REFERENCES usuario (id),
	message TEXT NOT NULL,
	latitude TEXT,
	longitude TEXT,
	createdat VARCHAR(255) NOT NULL -- unix timestamp
);

CREATE TABLE likes (
  usuario INTEGER REFERENCES usuario (id),
  post INTEGER REFERENCES posts (id),
  UNIQUE (usuario, post)
);

CREATE TABLE beer_recipe (
  id SERIAL PRIMARY KEY NOT NULL,
  usuario INTEGER REFERENCES usuario (id),
  name TEXT NOT NULL,
  description TEXT NOT NULL,
  style TEXT NOT NULL,
  statistics TEXT NOT NULL,
  ingredients TEXT NOT NULL,
  production TEXT NOT NULL,
  UNIQUE(name)
);

CREATE TABLE beer_recipe_rating (
  usuario INTEGER REFERENCES usuario (id),
  beer_recipe INTEGER REFERENCES beer_recipe (id),
  rating INTEGER NOT NULL CHECK (rating > 0 AND rating < 6),
  UNIQUE (usuario, beer_recipe)
);

