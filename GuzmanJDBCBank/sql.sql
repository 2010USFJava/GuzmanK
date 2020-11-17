-- DROP DATABASE bank;

CREATE DATABASE bank
WITH
OWNER = postgres
TABLESPACE = pg_default


-- DROP TABLE public.customers;

CREATE TABLE bank.public.customers (

id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
name varchar(300) COLLATE pg_catalog."default" NOT NULL,
username varchar(300) COLLATE pg_catalog."default" NOT NULL,
password varchar(300) COLLATE pg_catalog."default" NOT NULL,
bio text COLLATE pg_catalog."default",
married boolean DEFAULT false,
CONSTRAINT customers_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.customers
OWNER to postgres;


-- DROP TABLE public.employees;

CREATE TABLE public.employees (

id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
name varchar(300) COLLATE pg_catalog."default" NOT NULL,
username varchar(300) COLLATE pg_catalog."default" NOT NULL,
password varchar(300) COLLATE pg_catalog."default" NOT NULL,
job_title varchar(300) COLLATE pg_catalog."default" NOT NULL,
CONSTRAINT employees_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.employees
OWNER to postgres;


-- DROP TABLE public.simple_account;

CREATE TABLE public.simple_account (

id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
name varchar(300) COLLATE pg_catalog."default" NOT NULL,
balance bigint DEFAULT 0,
status integer NOT NULL,
customer_id bigint NOT NULL,
CONSTRAINT simple_account_pkey PRIMARY KEY (id),
CONSTRAINT "Simple_Account_Customer_FK" FOREIGN KEY (customer_id)
REFERENCES public.customers (id) MATCH SIMPLE
ON UPDATE NO ACTION
ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.simple_account
OWNER to postgres;

-- DROP TABLE public.joint_account;

CREATE TABLE public.joint_account (

id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
name varchar(300) COLLATE pg_catalog."default" NOT NULL,
balance bigint DEFAULT 0,
status integer NOT NULL,
CONSTRAINT joint_account_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.joint_account
OWNER to postgres;



-- DROP TABLE public.joint_acc_customers;

CREATE TABLE public.joint_acc_customers (

coustomer_id bigint NOT NULL,
account_id bigint NOT NULL,
CONSTRAINT "customer_joint_account_FK" FOREIGN KEY (customer_id)
REFERENCES public.customers (id) MATCH SIMPLE
ON UPDATE NO ACTION
ON DELETE NO ACTION,
CONSTRAINT "joint_account_ID_FK" FOREIGN KEY (account_id)
REFERENCES public.joint_account (id) MATCH SIMPLE
ON UPDATE NO ACTION
ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.joint_acc_customers
OWNER to postgres;
