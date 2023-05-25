CREATE EXTENSION IF NOT EXISTS pgcrypto;

INSERT INTO member (id, first_name, last_name, email, password, role, soft_delete, user_type)
VALUES
  (1, 'Edvinas', 'Alimas', 'edvinas.alimas@devridge.com', crypt('password', gen_salt('bf')), 'Team Lead', false, 'User'),
  (2, 'Raigardas', 'Tautkus', 'raitaut@gmail.com', crypt('password', gen_salt('bf')), 'Full-Stack', false, 'User'),
  (3, 'Tadas', 'Laurinaitis', 'tadas.laurinaitis@gmail.com', crypt('password', gen_salt('bf')), 'Full-Stack', false, 'User'),
  (4, 'Sarunas', 'Jurevicius', 'sarunasj82@gmail.com', crypt('password', gen_salt('bf')), 'Full-Stack', false, 'User'),
  (5, 'Danylo', 'Abramov', 'danyaa2908@gmail.com', crypt('password', gen_salt('bf')), 'Full-Stack', false, 'User'),
  (6, 'Matas', 'Raila', 'matasraila123@gmail.com', crypt('password', gen_salt('bf')), 'Full-Stack', false, 'User'),
  (7, 'Simas', 'Jonelis', 'jonelissimas@gmail.com', crypt('password', gen_salt('bf')), 'Full-Stack', false, 'User')
  ON CONFLICT (email) DO NOTHING;