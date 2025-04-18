CREATE TABLE IF NOT EXISTS invest.users (
    user_id UUID NOT NULL,
    login text NOT NULL UNIQUE,
    password text NOT NULL,
    CONSTRAINT users_pk PRIMARY KEY (user_id)
);

COMMENT ON TABLE invest.users IS 'Таблица для хранения пользователей';

COMMENT ON COLUMN invest.users.user_id IS 'Уникальный идентификатор пользователя';
COMMENT ON COLUMN invest.users.login IS 'Логин пользователя';
COMMENT ON COLUMN invest.users.password IS 'Пароль пользователя';
