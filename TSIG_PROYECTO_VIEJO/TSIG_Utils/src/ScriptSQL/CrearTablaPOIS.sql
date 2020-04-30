-- Table: public.pois

-- DROP TABLE public.pois;

CREATE TABLE public.pois
(
    gid integer NOT NULL DEFAULT nextval('pois_gid_seq'::regclass),
    id bigint,
    geometry geometry,
    nombre character varying COLLATE pg_catalog."default",
    dtype character varying COLLATE pg_catalog."default",
    puntaje numeric(5,2),
    CONSTRAINT pois_pkey PRIMARY KEY (gid)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.pois
    OWNER to tecnoinf;

GRANT ALL ON TABLE public.pois TO tecnoinf;

GRANT ALL ON TABLE public.pois TO PUBLIC;

-- Index: sidx_pois

-- DROP INDEX public.sidx_pois;

CREATE INDEX sidx_pois
    ON public.pois USING gist
    (geometry)
    TABLESPACE pg_default;