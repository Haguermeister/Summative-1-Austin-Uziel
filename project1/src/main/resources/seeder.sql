USE game_store;

INSERT INTO sales_tax_rate(state, rate)
VALUES ('AL', .05),

       ('AK', .06),

       ('AZ', .04),

       ('AR', .06),

       ('CA', .06),

       ('CO', .04),

       ('CT', .03),

       ('DE', .05),

       ('FL', .06),

       ('GA', .07),

       ('HI', .05),

       ('ID', .03),

       ('IL', .05),

       ('IN', .05),

       ('IA', .04),

       ('KS', .06),

       ('KY', .04),

       ('LA', .05),

       ('ME', .03),

       ('MD', .07),

       ('MA', .05),

       ('MI', .06),

       ('MN', .06),

       ('MS', .05),

       ('MO', .05),

       ('MT', .03),

       ('NE', .04),

       ('NV', .04),

       ('NH', .06),

       ('NJ', .05),

       ('NM', .05),

       ('NY', .06),

       ('NC', .05),

       ('ND', .05),

       ('OH', .04),

       ('OK', .04),

       ('OR', .07),

       ('PA', .06),

       ('RI', .06),

       ('SC', .06),

       ('SD', .06),

       ('TN', .05),

       ('TX', .03),

       ('UT', .04),

       ('VT', .07),

       ('VA', .06),

       ('WA', .05),

       ('WV', .05),

       ('WI', .03),

       ('WY', .04);


INSERT INTO processing_fee(product_type, fee)
VALUES ('Consoles', 14.99),
       ('T-shirts', 1.98),
       ('Games', 1.49);


INSERT INTO invoice(invoice_id, name, street, city, state, zipcode,
                    item_type, item_id, unit_price, quantity, subtotal, tax, processing_fee, total)
VALUES (1, 'Uziel', '123 main', 'some city', 'IN', 12345, 'Game', 1, 23.33, 4, 20.99, 4.99, 5.99, 40.99);

INSERT INTO console(console_id, model, manufacturer, memory_amount, processor, price, quantity)
VALUES (1, 'gamecube', 'nintendo', '2GB', 'medium', 100, 200),
       (2, 'playstation', 'sony', '200GB', 'fast', 300, 2000),
       (3, 'xbox one', 'microsoft', '200GB', 'fast', 400, 2000);
INSERT INTO game(game_id, title, esrb_rating, description, price, studio, quantity)
VALUES (1, 'god of war', 'M 18+',
        'God of War is an action-adventure game franchise created by David Jaffe at Sony''s Santa Monica Studio. ',
        50.99, 'sony pictures digital', 100),
       ( 2, 'Assasins creed', 'M 18+'
       , 'Assassin''s Creed is an open-world action-adventure stealth video game franchise published by Ubisoft and developed mainly by its studio Ubisoft Montreal'
       , 40.99, 'ubisoft', 100),
       (3, 'Super smash bros', 'Everyone 10+'
           ,
        'Super Smash Bros.[a] is a crossover fighting game series published by Nintendo.',
        59.99, 'nintendo', 300);

INSERT INTO t_shirt(t_shirt_id, size, color, description, price, quantity)
VALUES (1, 'medium', 'Blue', 'A nice cotton t-shirt', 15.99, 300),
       (2, 'large', 'red', 'A large red cotton t-shirt', 14.99, 300),
       (3, 'small', 'yellow', 'A small cotton t-shirt', 18.99, 300),
       (4, 'x-large', 'brown', 'A big cotton brown t-shirt', 12.99, 300);
