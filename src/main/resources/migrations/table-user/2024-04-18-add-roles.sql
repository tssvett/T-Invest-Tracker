create type invest.user_role as ENUM ('ROLE_USER', 'ROLE_ADMIN');

ALTER TABLE invest.users ADD COLUMN role invest.user_role NOT NULL DEFAULT 'ROLE_USER';
