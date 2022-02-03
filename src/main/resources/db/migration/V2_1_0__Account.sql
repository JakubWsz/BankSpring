CREATE TABLE IF NOT EXISTS account (
 id bigserial PRIMARY KEY NOT NULL,
 account_type smallint NOT NULL,
 account_number varchar(40) NOT NULL,
 currency_type varchar(3) NOT NULL,
 amount decimal(20,2) NOT NULL);

 create index account_i_pk on account(id);