CREATE TABLE record (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    patient_id UUID NOT NULL references patient(id),
    material_id UUID NOT NULL references material(id)
)