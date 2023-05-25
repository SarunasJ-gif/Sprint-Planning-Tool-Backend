ALTER TABLE working_day
DROP CONSTRAINT fk_sprint;

ALTER TABLE working_day
ADD CONSTRAINT fk_sprint FOREIGN KEY (sprint_id) REFERENCES sprint(id) ON DELETE CASCADE;

ALTER TABLE working_day
DROP CONSTRAINT fk_member;

ALTER TABLE working_day
ADD CONSTRAINT fk_member FOREIGN KEY (member_id) REFERENCES member(id) ON DELETE CASCADE;

ALTER TABLE initial_working_day
DROP CONSTRAINT fk_sprint;

ALTER TABLE initial_working_day
ADD CONSTRAINT fk_sprint FOREIGN KEY (sprint_id) REFERENCES sprint(id) ON DELETE CASCADE;

ALTER TABLE initial_working_day
DROP CONSTRAINT fk_member;

ALTER TABLE initial_working_day
ADD CONSTRAINT fk_member FOREIGN KEY (member_id) REFERENCES member(id) ON DELETE CASCADE;


