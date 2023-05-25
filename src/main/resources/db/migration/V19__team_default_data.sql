INSERT INTO team (id, name, completed_projects, completed_tasks)
VALUES (1, 'W-Team', 8, 359)
ON CONFLICT (id) DO NOTHING;