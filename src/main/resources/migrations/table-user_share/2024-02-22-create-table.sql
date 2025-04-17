CREATE TABLE IF NOT EXISTS invest.users_share (
    user_id UUID NOT NULL,
    share_uid UUID NOT NULL,
    CONSTRAINT user_share_pk PRIMARY KEY (user_id, share_uid),
    CONSTRAINT user_share_user_fk FOREIGN KEY (user_id) REFERENCES invest.users (user_id) ON DELETE CASCADE,
    CONSTRAINT user_share_share_fk FOREIGN KEY (share_uid) REFERENCES invest.share (uid) ON DELETE CASCADE
);

COMMENT ON TABLE invest.users_share IS 'Таблица для хранения связи между пользователями и акциями (n:n)';

COMMENT ON COLUMN invest.users_share.user_id IS 'Уникальный идентификатор пользователя';
COMMENT ON COLUMN invest.users_share.share_uid IS 'Уникальный идентификатор акции';
