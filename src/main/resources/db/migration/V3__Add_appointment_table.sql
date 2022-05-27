CREATE TABLE appointment (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    patient_id UUID NOT NULL references patient(id),
    "date" VARCHAR NOT NULL,
    "time" VARCHAR NOT NULL
);



