-- Table: public.movie

-- DROP TABLE public.movie;

CREATE TABLE public.movie
(
  id integer NOT NULL,
  name character varying(100) NOT NULL,
  director character varying(40) NOT NULL,
  CONSTRAINT movie_pkey PRIMARY KEY (name)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.movie
  OWNER TO postgres;
