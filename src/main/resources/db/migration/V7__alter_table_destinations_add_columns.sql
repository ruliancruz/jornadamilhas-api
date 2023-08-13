ALTER TABLE destinations MODIFY COLUMN photo_path varchar(255) not null;
ALTER TABLE destinations ADD photo_path2 varchar(255) not null;
ALTER TABLE destinations ADD meta varchar(160) not null;
ALTER TABLE destinations ADD description varchar(255);