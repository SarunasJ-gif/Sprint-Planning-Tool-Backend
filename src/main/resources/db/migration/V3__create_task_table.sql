CREATE TABLE task
(
id SERIAL PRIMARY KEY,
key_value VARCHAR(255) NOT NULL UNIQUE,
key_color VARCHAR(255),
description VARCHAR(255),
type VARCHAR(255),
old_points INTEGER,
remaining_points INTEGER,
new_points INTEGER,
sprint_id INT,
CONSTRAINT fk_sprint FOREIGN KEY(sprint_id) REFERENCES sprint(id)
)