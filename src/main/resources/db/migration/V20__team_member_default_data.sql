INSERT INTO team_member (team_id, member_id)
SELECT t.team_id, t.member_id
FROM (VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (1, 4),
    (1, 5),
    (1, 6),
    (1, 7)
) AS t (team_id, member_id)
WHERE NOT EXISTS (
    SELECT 1
    FROM team_member tm
    WHERE tm.team_id = t.team_id AND tm.member_id = t.member_id
);
