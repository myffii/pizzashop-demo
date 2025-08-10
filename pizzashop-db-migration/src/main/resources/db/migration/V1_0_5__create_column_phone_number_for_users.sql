alter table client.Users
    add column phone_number varchar(12) not null check (phone_number ~ '^\+7[0-9]{10}$') unique;

comment on column client.Users.phone_number is 'Уникальный номер телефона, начинающийся с +7 и состоящий из 12 символов';