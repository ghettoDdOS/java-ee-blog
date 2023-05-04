DROP TABLE IF EXISTS author, blog;

CREATE TABLE IF NOT EXISTS author
(
    id serial NOT NULL,
    full_name character varying(30),
    email character varying(30),
    created_at timestamp without time zone,
    CONSTRAINT author_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS blog
(
    id serial NOT NULL,
    title character varying(255),
    content text,
    created_at timestamp without time zone,
    id_author integer NOT NULL,
    CONSTRAINT blog_pkey PRIMARY KEY (id),
    CONSTRAINT blog_author_fkey FOREIGN KEY (id_author)
      REFERENCES public.author (id) ON DELETE SET NULL
);
