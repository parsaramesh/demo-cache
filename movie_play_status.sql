-- Table: public.movie_play_status

-- DROP TABLE public.movie_play_status;

CREATE TABLE public.movie_play_status
(
  id bigint NOT NULL DEFAULT nextval('movie_play_status_id_seq'::regclass),
  movie_name character varying(200) NOT NULL,
  status character varying(10) NOT NULL,
  release_date timestamp(6) without time zone NOT NULL
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.movie_play_status
  OWNER TO postgres;
