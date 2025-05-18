alter table invest.users_share
drop constraint user_share_pk;

alter table invest.users_share
add column id SERIAL primary key;