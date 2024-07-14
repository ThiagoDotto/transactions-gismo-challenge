CREATE TABLE IF NOT EXISTS operation
(
    operation_id SERIAL PRIMARY KEY,
    description  VARCHAR
);

CREATE TABLE IF NOT EXISTS account
(
    account_id      SERIAL PRIMARY KEY,
    document_number BIGINT UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS transaction
(
    transaction_id SERIAL PRIMARY KEY,
    account_id     INTEGER,
    operation_id   INTEGER,
    amount         NUMERIC(7, 1),
    eventDate      TIMESTAMP default LOCALTIMESTAMP,
    CONSTRAINT fk_account foreign key (account_id) references account (account_id),
    CONSTRAINT fk_operation foreign key (operation_id) references operation (operation_id)
);

insert into operation (description) VALUES ('COMPRA A VISTA');
insert into operation (description) VALUES ('COMPRA PARCELADA');
insert into operation (description) VALUES ('SAQUE');
insert into operation (description) VALUES ('PAGAMENTO');