-- Categories for GuitarShop
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Acoustic guitars with warm tones', 'Acoustic Guitars');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Electric guitars for rock and metal', 'Electric Guitars');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Bass guitars with deep sound', 'Bass Guitars');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Amplifiers and effect pedals', 'Amps & Pedals');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Strings, straps, cases and more', 'Accessories');

-- Insert products for 'Acoustic Guitars'
INSERT INTO public.product (id, available_quantity, description, name, price, photo, category_id)
VALUES
    (nextval('product_seq'), 10, 'Yamaha FG800 with solid spruce top', 'Yamaha FG800', 114990, 'https://resources.cdn-kaspi.kz/img/m/p/h66/hc0/63834322599966.jpg?format=gallery-large', (SELECT id FROM category WHERE name = 'Acoustic Guitars')),
    (nextval('product_seq'), 12, 'Fender CD-60 Dreadnought with natural finish', 'Fender CD-60', 100000, 'https://resources.cdn-kaspi.kz/img/m/p/h36/heb/63759461416990.jpg?format=gallery-large', (SELECT id FROM category WHERE name = 'Acoustic Guitars')),
    (nextval('product_seq'), 8, 'Taylor GS Mini with rich sound', 'Taylor GS Mini', 300000, 'https://images.musicstore.de/images/1280/taylor-gs-mini-acoustic-travel-guitar-_1_GIT0019831-000.jpg', (SELECT id FROM category WHERE name = 'Acoustic Guitars')),
    (nextval('product_seq'), 15, 'Epiphone DR-100 beginner-friendly guitar', 'Epiphone DR-100', 85000, 'https://resources.cdn-kaspi.kz/img/m/p/h8b/h83/84874984357918.jpg?format=gallery-medium', (SELECT id FROM category WHERE name = 'Acoustic Guitars')),
    (nextval('product_seq'), 6, 'Martin LX1 Little Martin travel guitar', 'Little Martin LX1', 215000, 'https://i.ebayimg.com/images/g/ih4AAOSwC6dn5Yjh/s-l1200.jpg', (SELECT id FROM category WHERE name = 'Acoustic Guitars'));

-- Insert products for 'Electric Guitars'
INSERT INTO public.product (id, available_quantity, description, name, price, photo, category_id)
VALUES
    (nextval('product_seq'), 7, 'Fender Player Stratocaster with maple neck', 'Fender Stratocaster', 375000, 'https://sc1.musik-produktiv.com/pic-010148768l/fender-american-vintage-ii-1961-stratocaster-fiesta-red.jpg', (SELECT id FROM category WHERE name = 'Electric Guitars')),
    (nextval('product_seq'), 9, 'Gibson Les Paul Studio with humbuckers', 'Gibson Les Paul', 600000, 'https://upload.wikimedia.org/wikipedia/commons/thumb/1/1c/Full_front_R9_Les_Paul.jpg/1200px-Full_front_R9_Les_Paul.jpg', (SELECT id FROM category WHERE name = 'Electric Guitars')),
    (nextval('product_seq'), 11, 'Ibanez RG series for metal players', 'Ibanez RG450DX', 250000, 'https://m.media-amazon.com/images/I/71NkVxPiZWL.jpg', (SELECT id FROM category WHERE name = 'Electric Guitars')),
    (nextval('product_seq'), 13, 'PRS SE Custom 24 with dual humbuckers', 'PRS SE Custom 24', 425000, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRIhDNP_3h68-Ro0el3MBJpyh--pSC-KDthMw&s', (SELECT id FROM category WHERE name = 'Electric Guitars')),
    (nextval('product_seq'), 5, 'Squier Classic Vibe Telecaster', 'Squier Telecaster', 225000, 'https://muzzone.kz/images/detailed/24/Fender_40th_Anniversary_Telecaster%C2%AE_Gold_Edition_Black.jpg', (SELECT id FROM category WHERE name = 'Electric Guitars'));

-- Insert products for 'Bass Guitars'
INSERT INTO public.product (id, available_quantity, description, name, price, photo, category_id)
VALUES
    (nextval('product_seq'), 6, 'Fender Player Jazz Bass with alder body', 'Fender Jazz Bass', 375000, 'https://media.sweetwater.com/m/products/image/54ebb747b4lfdiPSxFJxTpLnHC4LaFDQBTFFgI4T.png?quality=82&ha=54ebb747b40486b6', (SELECT id FROM category WHERE name = 'Bass Guitars')),
    (nextval('product_seq'), 8, 'Yamaha TRBX304 active bass guitar', 'Yamaha TRBX304', 190000, 'https://www.1music.kz/upload/iblock/056/056b13292bc7ed4aba2ca65016f52113.jpeg', (SELECT id FROM category WHERE name = 'Bass Guitars')),
    (nextval('product_seq'), 10, 'Ibanez GSR200 beginner bass', 'Ibanez GSR200', 115000, 'https://m.media-amazon.com/images/I/71Ywm555g0L.jpg', (SELECT id FROM category WHERE name = 'Bass Guitars')),
    (nextval('product_seq'), 4, 'Squier Affinity Precision Bass', 'Squier P-Bass', 150000, 'https://gitaraclub.ru/upload/iblock/bb3/hes21ytk2p6l9j0tb7a90if0loyj6j6u.jpg', (SELECT id FROM category WHERE name = 'Bass Guitars')),
    (nextval('product_seq'), 7, 'Epiphone Thunderbird IV', 'Epiphone Thunderbird', 200000, 'https://images.ctfassets.net/m8onsx4mm13s/0WfYTs87N2am1NiTBucriV/143c2d5812314a1c5dbffb7389dbfdd2/__static.gibson.com_product-images_Epiphone_EPII67714_Tobacco_Sunburst_EBTVTSNH1_front.jpg', (SELECT id FROM category WHERE name = 'Bass Guitars'));

-- Insert products for 'Amps & Pedals'
INSERT INTO public.product (id, available_quantity, description, name, price, photo, category_id)
VALUES
    (nextval('product_seq'), 10, 'Boss DS-1 distortion pedal', 'Boss DS-1', 30000, 'https://resources.cdn-kaspi.kz/img/m/p/h25/h59/64009687138334.jpg?format=gallery-medium', (SELECT id FROM category WHERE name = 'Amps & Pedals')),
    (nextval('product_seq'), 12, 'Line 6 Spider V 30 MkII modeling amp', 'Line 6 Spider V', 100000, 'https://www.1music.kz/upload/iblock/c70/c70a0ae39fcb7b4b8536233429a7a3b5.jpeg', (SELECT id FROM category WHERE name = 'Amps & Pedals')),
    (nextval('product_seq'), 8, 'Marshall Code25 combo amplifier', 'Marshall Code25', 125000, 'https://muzzone.kz/images/detailed/12/0205.jpg', (SELECT id FROM category WHERE name = 'Amps & Pedals')),
    (nextval('product_seq'), 6, 'Electro-Harmonix Big Muff fuzz pedal', 'Big Muff Pi', 45000, 'https://m.media-amazon.com/images/I/71WB-ME9mOL.jpg', (SELECT id FROM category WHERE name = 'Amps & Pedals')),
    (nextval('product_seq'), 9, 'Fender Mustang LT25 digital amp', 'Fender Mustang LT25', 90000, 'https://resources.cdn-kaspi.kz/img/m/p/he5/ha9/64217004081182.jpg?format=gallery-large', (SELECT id FROM category WHERE name = 'Amps & Pedals'));

-- Insert products for 'Accessories'
INSERT INTO public.product (id, available_quantity, description, name, price, photo, category_id)
VALUES
    (nextval('product_seq'), 20, 'Ernie Ball Regular Slinky 10-46 strings', 'Ernie Ball Strings', 3500, 'https://resources.cdn-kaspi.kz/img/m/p/haa/ha2/64214109061150.jpg?format=gallery-medium', (SELECT id FROM category WHERE name = 'Accessories')),
    (nextval('product_seq'), 18, 'Guitar stand with neck support', 'Guitar Stand', 10000, 'https://resources.cdn-kaspi.kz/img/m/p/h7b/ha2/64167550156830.jpg?format=gallery-medium', (SELECT id FROM category WHERE name = 'Accessories')),
    (nextval('product_seq'), 16, 'Leather guitar strap with padding', 'Leather Strap', 12500, 'https://resources.cdn-kaspi.kz/img/m/p/hd5/h26/64495804186654.jpg?format=gallery-medium  ', (SELECT id FROM category WHERE name = 'Accessories')),
    (nextval('product_seq'), 22, 'Hardshell case for electric guitars', 'Electric Guitar Case', 45000, 'https://resources.cdn-kaspi.kz/img/m/p/h6b/h16/85064867643422.jpg?format=gallery-medium', (SELECT id FROM category WHERE name = 'Accessories')),
    (nextval('product_seq'), 25, 'Clip-on digital guitar tuner', 'Clip-on Tuner', 7500, 'https://resources.cdn-kaspi.kz/img/m/p/hf3/h22/81084555755550.jpg?format=gallery-medium', (SELECT id FROM category WHERE name = 'Accessories'));
