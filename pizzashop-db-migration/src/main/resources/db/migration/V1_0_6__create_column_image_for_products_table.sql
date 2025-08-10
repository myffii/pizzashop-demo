alter table catalog.products
    add column image bytea;

comment on column catalog.products.image is 'Изображение продукта';