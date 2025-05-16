-- Categories for GuitarShop
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Acoustic guitars with warm tones', 'Acoustic Guitars');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Electric guitars for rock and metal', 'Electric Guitars');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Bass guitars with deep sound', 'Bass Guitars');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Amplifiers and effect pedals', 'Amps & Pedals');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Strings, straps, cases and more', 'Accessories');

-- Insert products for 'Acoustic Guitars'
INSERT INTO public.product (id, available_quantity, description, name, price, category_id)
VALUES
    (nextval('product_seq'), 10, 'Yamaha FG800 with solid spruce top', 'Yamaha FG800', 114990, (SELECT id FROM category WHERE name = 'Acoustic Guitars')),
    (nextval('product_seq'), 12, 'Fender CD-60 Dreadnought with natural finish', 'Fender CD-60', 100000, (SELECT id FROM category WHERE name = 'Acoustic Guitars')),
    (nextval('product_seq'), 8, 'Taylor GS Mini with rich sound', 'Taylor GS Mini', 300000, (SELECT id FROM category WHERE name = 'Acoustic Guitars')),
    (nextval('product_seq'), 15, 'Epiphone DR-100 beginner-friendly guitar', 'Epiphone DR-100', 85000, (SELECT id FROM category WHERE name = 'Acoustic Guitars')),
    (nextval('product_seq'), 6, 'Martin LX1 Little Martin travel guitar', 'Little Martin LX1', 215000, (SELECT id FROM category WHERE name = 'Acoustic Guitars'));

-- Insert products for 'Electric Guitars'
INSERT INTO public.product (id, available_quantity, description, name, price, category_id)
VALUES
    (nextval('product_seq'), 7, 'Fender Player Stratocaster with maple neck', 'Fender Stratocaster', 375000, (SELECT id FROM category WHERE name = 'Electric Guitars')),
    (nextval('product_seq'), 9, 'Gibson Les Paul Studio with humbuckers', 'Gibson Les Paul', 600000, (SELECT id FROM category WHERE name = 'Electric Guitars')),
    (nextval('product_seq'), 11, 'Ibanez RG series for metal players', 'Ibanez RG450DX', 250000, (SELECT id FROM category WHERE name = 'Electric Guitars')),
    (nextval('product_seq'), 13, 'PRS SE Custom 24 with dual humbuckers', 'PRS SE Custom 24', 425000, (SELECT id FROM category WHERE name = 'Electric Guitars')),
    (nextval('product_seq'), 5, 'Squier Classic Vibe Telecaster', 'Squier Telecaster', 225000, (SELECT id FROM category WHERE name = 'Electric Guitars'));

-- Insert products for 'Bass Guitars'
INSERT INTO public.product (id, available_quantity, description, name, price, category_id)
VALUES
    (nextval('product_seq'), 6, 'Fender Player Jazz Bass with alder body', 'Fender Jazz Bass', 375000, (SELECT id FROM category WHERE name = 'Bass Guitars')),
    (nextval('product_seq'), 8, 'Yamaha TRBX304 active bass guitar', 'Yamaha TRBX304', 190000, (SELECT id FROM category WHERE name = 'Bass Guitars')),
    (nextval('product_seq'), 10, 'Ibanez GSR200 beginner bass', 'Ibanez GSR200', 115000, (SELECT id FROM category WHERE name = 'Bass Guitars')),
    (nextval('product_seq'), 4, 'Squier Affinity Precision Bass', 'Squier P-Bass', 150000, (SELECT id FROM category WHERE name = 'Bass Guitars')),
    (nextval('product_seq'), 7, 'Epiphone Thunderbird IV', 'Epiphone Thunderbird', 200000, (SELECT id FROM category WHERE name = 'Bass Guitars'));

-- Insert products for 'Amps & Pedals'
INSERT INTO public.product (id, available_quantity, description, name, price, category_id)
VALUES
    (nextval('product_seq'), 10, 'Boss DS-1 distortion pedal', 'Boss DS-1', 30000, (SELECT id FROM category WHERE name = 'Amps & Pedals')),
    (nextval('product_seq'), 12, 'Line 6 Spider V 30 MkII modeling amp', 'Line 6 Spider V', 100000, (SELECT id FROM category WHERE name = 'Amps & Pedals')),
    (nextval('product_seq'), 8, 'Marshall Code25 combo amplifier', 'Marshall Code25', 125000, (SELECT id FROM category WHERE name = 'Amps & Pedals')),
    (nextval('product_seq'), 6, 'Electro-Harmonix Big Muff fuzz pedal', 'Big Muff Pi', 45000, (SELECT id FROM category WHERE name = 'Amps & Pedals')),
    (nextval('product_seq'), 9, 'Fender Mustang LT25 digital amp', 'Fender Mustang LT25', 90000, (SELECT id FROM category WHERE name = 'Amps & Pedals'));

-- Insert products for 'Accessories'
INSERT INTO public.product (id, available_quantity, description, name, price, category_id)
VALUES
    (nextval('product_seq'), 20, 'Ernie Ball Regular Slinky 10-46 strings', 'Ernie Ball Strings', 3500, (SELECT id FROM category WHERE name = 'Accessories')),
    (nextval('product_seq'), 18, 'Guitar stand with neck support', 'Guitar Stand', 10000, (SELECT id FROM category WHERE name = 'Accessories')),
    (nextval('product_seq'), 16, 'Leather guitar strap with padding', 'Leather Strap', 12500, (SELECT id FROM category WHERE name = 'Accessories')),
    (nextval('product_seq'), 22, 'Hardshell case for electric guitars', 'Electric Guitar Case', 45000, (SELECT id FROM category WHERE name = 'Accessories')),
    (nextval('product_seq'), 25, 'Clip-on digital guitar tuner', 'Clip-on Tuner', 7500, (SELECT id FROM category WHERE name = 'Accessories'));
