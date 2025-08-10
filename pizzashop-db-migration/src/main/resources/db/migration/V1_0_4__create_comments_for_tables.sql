comment on table catalog.Categories is 'Таблица для хранения информации о категориях';
comment on column catalog.Categories.id is 'Уникальный идентификатор категории';
comment on column catalog.Categories.name is 'Наименование категории';

comment on table catalog.Products is 'Таблица для хранения информации о продуктах';
comment on column catalog.Products.id is 'Уникальный идентификатор продукта';
comment on column catalog.Products.name is 'Наименование продукта';
comment on column catalog.Products.price is 'Стоимость продукта';
comment on column catalog.Products.category_id is 'Уникальный идентификатор категории, к которой принадлежит продукт';

comment on table client.Users is 'Таблица для хранения информации о пользователях';
comment on column client.Users.id is 'Уникальный идентификатор пользователя';
comment on column client.Users.username is 'Имя пользователя';
comment on column client.Users.password is 'Пароль пользователя';

comment on table orders.Orders is 'Таблица для хранения информации о заказах';
comment on column orders.Orders.id is 'Уникальный идентификатор заказа';
comment on column orders.Orders.customer_id is 'Уникальный идентификатор заказчика';
comment on column orders.Orders.total is 'Суммарная стоимость заказа';
comment on column orders.Orders.order_date is 'Дата создания заказа';
comment on column orders.Orders.updated_at is 'Время последнего обновления данных о заказе';
comment on column orders.Orders.status is 'Статус заказа';
comment on column orders.Orders.products is 'Информация о продуктах, входящих в заказ в формате k:v - productId:quantity';