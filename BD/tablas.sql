create table "user"(
	"id" serial,
    "name" VARCHAR(120) not NULL,
    "lastname" VARCHAR(120) not NULL,
    "email" VARCHAR(120) not NULL,
    "password" VARCHAR(120) not NULL,
    "rol" VARCHAR(4) not NULL,
    "address" VARCHAR(120) not NULL,
    "image" VARCHAR(120) not NULL,
    "cash" INTEGER not null
    );
    
INSERT INTO "user"
("name", "lastname", "email", "password", "rol", "address", "image", "cash")
VALUES('santiago', 'lopez', 'santi@mail.com', '6f8af7679ce5652dbf262cd9f1adbd7fc524b78c42ab38965b6a4c4466add69dca5e35bb83a08e18', 'USER', 'Carrera 123', 'img', 10000000);

ALTER TABLE "user"
ADD CONSTRAINT UK_User_Email
UNIQUE (email);

ALTER TABLE "user"
ADD CONSTRAINT CH_User_Rol
CHECK (
    rol IN ('USER','MECA')
);

drop table "user";


create table "product"(
	"id" serial,
    "name" VARCHAR(120) not NULL,
    "description" VARCHAR(120) not NULL,
    "price" INTEGER not NULL,
    "image" VARCHAR(120) not NULL,
    "status" VARCHAR(120) not NULL
    );