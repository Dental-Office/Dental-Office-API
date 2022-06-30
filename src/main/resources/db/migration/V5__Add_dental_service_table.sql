CREATE TABLE dental_service (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    service_name VARCHAR NOT NULL
)