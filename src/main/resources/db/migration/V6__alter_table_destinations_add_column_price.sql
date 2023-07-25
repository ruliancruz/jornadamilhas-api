ALTER TABLE destinations ADD price tinyint;
ALTER TABLE destinations MODIFY COLUMN price float not null;