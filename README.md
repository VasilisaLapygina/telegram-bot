# telegram-bot
telegram-bot

Подключение к бд: psql -U postgres -W 
create user telegram_bot with password 'telegram_bot';
create database telegram_bot owner telegram_bot;


CREATE TABLE friend (
    id uuid NOT NULL,
    name VARCHAR(255),
    nickname VARCHAR(255),
    birthday VARCHAR(255),
    chat BIGINT, 
    mode VARCHAR(255), 
    last_command VARCHAR(255),
    PRIMARY KEY (id)
);

INSERT INTO friend (id, name, nickname, birthday)
VALUES ('2a01d764-2931-4a3d-9f02-e2434c6f9cc3', 'Обрубова Василиса', '@vasilisa_obrubova', '04.04.1997'),
('93dfe091-e1d3-4917-980b-b144f3cc0950', 'Обрубов Максим', '@user_undef', '05.08.1994'),
('fb2a0e21-6596-4461-bb57-97f2bca4d6d7', 'Елецкая Анна', '@Anna_Eletsky', '11.07.1998'),
('b60578f9-1624-4275-bb52-d13a42ec03c6', 'Устинова Анастасия', '@Ustinova_Anastasia1', '26.07.1997'),
('a7f7d577-e991-4c87-a308-96d53d293dcc', 'Кочеткова Олеся', '@kochetkova_olesun', '29.08'),
('637d4eda-a34f-4589-b657-446a10f80043', 'Гоняев Андрей', '@agonyaev', '10.06.1994'),
('0f61a180-f9b3-4b6f-9a68-c3f14cc7bcd1', 'Шопырев Денис', '@test_user_9', '12.10.1997'),
('0adc8974-daf7-4178-8694-ca12f4897189', 'Корнилов Сергей', '@KamradSirGrey', '23.06.1997'),
('85ea52ab-9963-43d6-addc-bc72cd3739a7', 'Ширяева Елизавета', '@LizaShiryaeva', '24.04.1997'),
('cb719afc-86be-4c52-8b47-ec5ca9832165', 'Родионова Анастасия', '@j_nans', '24.12'),
('f3284c29-0b3d-4aaf-ae20-24b7b79c1252', 'Черезов Владимир', '@x69760d0a', '24.08'),
('566541c3-aac7-4597-89f9-562a265285cb', 'Ханова Анастасия', '@n_hanova', '17.10'),
('92daca48-ff8a-4cda-a841-36e88e8d42ae', 'Туманова Татьяна', '@ribusick', '23.02'),
('3a1d5277-ae41-43cf-8142-6947364d08fd', 'Курочкин Михаил', '@mikhail_kurochkin', '24.12'),
('ec74fd27-92c6-4c43-99fc-7e7e23683485', 'Пронин Максим', '@itrealrew', '13.05');

