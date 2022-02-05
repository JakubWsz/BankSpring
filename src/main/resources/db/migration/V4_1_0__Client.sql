CREATE TABLE IF NOT EXISTS "client" (
 id bigserial PRIMARY KEY NOT NULL,
 user_id bigserial NOT NULL,
 firstName varchar(40) NOT NULL,
 lastName varchar(40) NOT NULL,
 idCardNumber varchar(40) NOT NULL,
 modification_date timestamp,
 is_deleted bool,
     CONSTRAINT fk_user
     FOREIGN KEY(user_id)
     REFERENCES "user"
);

create index client_i_pk on client(id);
create index client_i_fk on "user"(id);