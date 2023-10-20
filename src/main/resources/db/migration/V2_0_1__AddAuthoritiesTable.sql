-- Table: public.authorities

-- DROP TABLE IF EXISTS public.authorities;

CREATE TABLE IF NOT EXISTS public.authorities
(
    username text COLLATE pg_catalog."default",
    authority text COLLATE pg_catalog."default"
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.authorities
    OWNER to postgres;