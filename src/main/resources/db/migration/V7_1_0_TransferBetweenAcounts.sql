CREATE TABLE IF NOT EXISTS transfer_between_accounts
(
    id                bigserial PRIMARY KEY NOT NULL,
    created_at        timestamp             NOT NULL,
    account_number    varchar(40)           NOT NULL,
    currency_type     char(3)               NOT NULL,
    amount            decimal(20, 2)        NOT NULL,
    balance_before    decimal(20, 2)        NOT NULL,
    modification_date timestamp             NOT NULL,
    is_deleted        bool                  NOT NULL
);

create index transfer_between_accounts_i_pk on transfer_between_accounts (id);
