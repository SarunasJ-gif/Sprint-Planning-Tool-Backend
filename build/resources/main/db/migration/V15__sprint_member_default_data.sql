INSERT INTO sprint_member (sprint_id, member_id)
SELECT s.sprint_id, s.member_id
FROM (VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (1, 4),
    (1, 5),
    (1, 6),
    (1, 7),
    (2, 1),
    (2, 2),
    (2, 3),
    (2, 4),
    (2, 5),
    (2, 6),
    (2, 7)
) AS s (sprint_id, member_id)
WHERE NOT EXISTS (
    SELECT 1
    FROM sprint_member sm
    WHERE sm.sprint_id = s.sprint_id AND sm.member_id = s.member_id
);