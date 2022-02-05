CREATE TABLE IF NOT EXISTS "user" (
 id bigserial PRIMARY KEY NOT NULL,
 login varchar(40) NOT NULL,
 password varchar(40) NOT NULL,
 email varchar(40) NOT NULL,
 pin varchar(40) NOT NULL,
 modification_date timestamp,
 is_deleted bool);

create index user_i_pk on "user"(id);