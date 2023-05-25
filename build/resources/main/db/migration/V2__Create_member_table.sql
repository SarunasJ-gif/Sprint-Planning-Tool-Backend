CREATE TABLE member
(
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(100),
    soft_delete BOOLEAN DEFAULT 'false',
    user_type VARCHAR(255) DEFAULT 'User'
)