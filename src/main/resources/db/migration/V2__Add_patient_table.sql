CREATE TABLE patient (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    first_name VARCHAR NOT NULL,
    last_name VARCHAR NOT NULL,
    birth_date VARCHAR NOT NULL,
    phone_number VARCHAR NOT NULL
);



