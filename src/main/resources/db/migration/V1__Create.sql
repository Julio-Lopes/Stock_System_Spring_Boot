CREATE TABLE IF NOT EXISTS client (
    birthdate DATE NOT NULL,
    enabled BIT NOT NULL,
    id BIGINT NOT NULL AUTO_INCREMENT,
    cpf VARCHAR(14),
    telephone VARCHAR(14),
    cellphone VARCHAR(15),
    email VARCHAR(50),
    name VARCHAR(50) NOT NULL,
    gender ENUM ('male','female') NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS invoice (
    date_hours DATETIME NOT NULL,
    id BIGINT NOT NULL AUTO_INCREMENT,
    supplier_id BIGINT NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS items_invoice (
    quantity INTEGER NOT NULL,
    total FLOAT(23),
    unity_value FLOAT(23) NOT NULL,
    id BIGINT NOT NULL AUTO_INCREMENT,
    invoice_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS items_out_invoice (
    quantity INTEGER NOT NULL,
    total FLOAT(23),
    unity_value FLOAT(23) NOT NULL,
    id BIGINT NOT NULL AUTO_INCREMENT,
    outinvoice_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS out_invoice (
    client_id BIGINT NOT NULL,
    date_hours DATETIME NOT NULL,
    id BIGINT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS products (
    enabled BIT NOT NULL,
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    category ENUM ('CELLPHONE','HOUSEHOLDAPPLICANCE','COMPUTING','FURNITURE') NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS stock_product (
    quantity INTEGER,
    id BIGINT NOT NULL AUTO_INCREMENT,
    product_id BIGINT,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS suppliers (
    enabled BIT NOT NULL,
    id BIGINT NOT NULL AUTO_INCREMENT,
    telephone VARCHAR(14),
    cellphone VARCHAR(15),
    cnpj VARCHAR(18),
    company_name VARCHAR(50) NOT NULL,
    email VARCHAR(50),
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;