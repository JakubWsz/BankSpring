CREATE TABLE IF NOT EXISTS operation (
 id bigserial PRIMARY KEY NOT NULL,
 created_at timestamp,
 firstname varchar(40) NOT NULL,
 lastname varchar(40) NOT NULL,
 id_card_number varchar(40) NOT NULL,
 modification_date timestamp,
 is_deleted bool
);

create index operation_i_pk on operation(id);