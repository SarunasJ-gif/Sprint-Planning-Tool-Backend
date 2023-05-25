CREATE TABLE sprint
(
    id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    start_date DATE,
    end_date DATE
)