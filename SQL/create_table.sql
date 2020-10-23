
CREATE TABLE public.students (
	ra int8 NOT NULL,
	"name" varchar(50) NOT NULL,
	email varchar(255) NOT NULL,
	cpf varchar(50) NOT NULL,
	CONSTRAINT alunos_cpf_key UNIQUE (cpf),
	CONSTRAINT alunos_pkey PRIMARY KEY (ra)
);