-- Table: public.users

-- DROP TABLE IF EXISTS public.users;

CREATE TABLE IF NOT EXISTS public.users
(
    username text COLLATE pg_catalog."default",
    password text COLLATE pg_catalog."default",
    enabled boolean
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.users
    OWNER to postgres;