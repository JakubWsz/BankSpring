CREATE TABLE IF NOT EXISTS transfer_self
(
    id                bigserial PRIMARY KEY NOT NULL,
    created_at        timestamp             NOT NULL,
    accountNumber     varchar(40)           NOT NULL,
    transferType      char(1)               NOT NULL,
    currencyType      char(3)               NOT NULL,
    amount            decimal(20, 2)        NOT NULL,
    balanceBefore     decimal(20, 2)        NOT NULL,
    modification_date timestamp             NOT NULL,
    is_deleted        bool                  NOT NULL
);

create index transfer_self_i_pk on transfer_self (id);