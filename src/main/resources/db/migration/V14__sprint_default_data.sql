INSERT INTO sprint (id, title, start_date, end_date, is_active, is_historical)
VALUES
  (1, '"Cognizant Softvision"- App Engineering', '2023-04-24', '2023-04-28', false, true),
  (2, '"Cognizant Softvision"- UX Workshop', '2023-05-01', '2023-05-05', true, false)
  ON CONFLICT (id) DO NOTHING;