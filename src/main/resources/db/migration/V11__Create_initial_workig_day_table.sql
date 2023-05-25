CREATE TABLE initial_working_day
(
id SERIAL PRIMARY KEY,
day DATE,
sprint_id INT,
CONSTRAINT fk_sprint FOREIGN KEY(sprint_id) REFERENCES sprint(id),
member_id INT,
CONSTRAINT fk_member FOREIGN KEY(member_id) REFERENCES member(id),
task_key_value VARCHAR(255)
)