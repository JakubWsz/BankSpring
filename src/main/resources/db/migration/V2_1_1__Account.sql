ALTER TABLE account
    add column modification_date timestamp,
    add column  is_deleted bool;