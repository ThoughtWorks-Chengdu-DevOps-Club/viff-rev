ALTER TABLE viff_storage.viff_file_item
   ADD COLUMN is_same BOOLEAN NOT NULL;
ALTER TABLE viff_storage.viff_file_item
   ADD COLUMN similarity DECIMAL(10, 0) NOT NULL;