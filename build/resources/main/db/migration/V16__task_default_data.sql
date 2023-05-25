INSERT INTO task (id, key_value, key_color, description, type, old_points, remaining_points, new_points, sprint_id)
VALUES
  (1, 'SDF-101', '#EC4226', 'Implement Spring Security', 'Goal', 4, 4, 5, 1),
  (2, 'SDF-102', '#ec6b26', 'Optimize Database Queries', 'Goal', 5, 14, 115, 1),
  (3, 'SDF-103', '#96ec26', 'Implement Data Caching', 'Goal', 8, 91, 11, 1),
  (4, 'SDF-104', '#2682ec', 'Create Rest API Functionality', 'Goal', 5, 5, 5, 1),
  (5, 'SDF-105', '#2661ec', 'Create ability update User data', 'Goal', 4, 4, 5, 1),
  (6, 'SDF-106', '#44ec26', 'Fix bugs', 'Goal', 2, 1, 15, 1),
  (7, 'SDF-107', '#ec2690', 'Perform Unit Tests', 'Goal', 14, 6, 8, 1),
  (8, 'SDF-108', '#ecbb26', 'Create Register Page', 'Goal', 9, 13, 55, 2),
  (9, 'SDF-109', '#26d5ec', 'Create Login Page', 'Goal', 44, 11, 12, 2),
  (10, 'SDF-110', '#26ec51', 'Create Main Page', 'Goal', 15, 12, 13, 2),
  (11, 'SDF-111', '#ecc126', 'Create Product Page', 'Goal', 17, 9, 19, 2),
  (12, 'SDF-112', '#263aec', 'Design Main Page', 'Goal', 17, 16, 18, 2),
  (13, 'SDF-113', '#26e5ec', 'Design Product Page', 'Goal', 43, 41, 5, 2),
  (14, 'SDF-114', '#dc26ec', 'Connect FE/BE', 'Goal', 16, 4, 21, 2)
  ON CONFLICT (id) DO NOTHING;