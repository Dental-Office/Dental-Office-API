CREATE TABLE material (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    material_name VARCHAR NOT NULL,
    quantity VARCHAR NOT NULL
)