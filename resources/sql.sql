SET SEARCH_PATH = oraz_storage;


CREATE TABLE users (
  id                BIGSERIAL PRIMARY KEY,
  role              CHARACTER VARYING(56)         NOT NULL,
  login             CHARACTER VARYING(12) UNIQUE  NOT NULL,
  password          CHARACTER VARYING(12) UNIQUE  NOT NULL,
  name              CHARACTER VARYING(128),
  last_name         CHARACTER VARYING(128),
  registration_date DATE,
  e_mail            CHARACTER VARYING(128) UNIQUE NOT NULL,
  organizatin       CHARACTER VARYING(256),
  other_information CHARACTER VARYING(512)
);

CREATE TABLE material (
  id           BIGSERIAL PRIMARY KEY,
  name         CHARACTER VARYING(128) UNIQUE NOT NULL,
  description  CHARACTER VARYING(1028),
  availability BOOLEAN DEFAULT TRUE
);

CREATE TABLE product (
  id          BIGSERIAL PRIMARY KEY,
  name        CHARACTER VARYING(256) NOT NULL,
  article     CHARACTER VARYING(128) UNIQUE,
  picture     CHARACTER VARYING(1028),
  value       NUMERIC(10, 2),
  material_id INTEGER REFERENCES material (id)
);

CREATE TABLE rewiew (
  id       BIGSERIAL PRIMARY KEY,
  date DATE,
  user_id BIGINT REFERENCES users (id),
  rewiew   CHARACTER VARYING(2500)

);


CREATE TABLE orders (
  id                BIGSERIAL PRIMARY KEY,
  date              DATE,
  desire_date       DATE,
  phone             INTEGER NOT NULL,
  other_information CHARACTER VARYING(512)
);

CREATE TABLE cart (
  id         BIGSERIAL PRIMARY KEY,
  user_id    BIGINT REFERENCES users (id),
  product_id INTEGER REFERENCES product (id),
  amount     INTEGER NOT NULL,
  order_id   BIGINT REFERENCES orders (id)
);

INSERT INTO users (role, login, password, name, last_name,registration_date,e_mail, organizatin) VALUES
  ('admin', 'admin', '23456789' , 'Ирина', 'Пширкова', '2018-09-08', 'infooraz@gmail.com', 'Sportbeloras'),
  ('user', 'user', '98765432' , 'Светлана', 'Лойко', '2018-09-11', 'tumibug@gmail.com', 'РГУОР');

INSERT INTO material (name, description, availability) VALUES

  ('Meryl',
   'Материал создан крупной испанской компанией «Нилстар» (NYLSTAR SpA). Этот материал является уникальной разновидностью полиамида. '
   ||
   'Однако его структура такова, что он легче, чем полиамид примерно на 1/3. Мерил — ткань, уважаемая спортсменами за его '
   ||
   'водонепроницаемость и ветронепродуваемость. При этом мерил обладает удивительной особенностью — он «дышит», пропуская к телу'
   ||
   ' воздух, и сохраняет тепло. Эта особенность работает даже при длительных тренировках с повышенным потоотделением.'
   ||
   ' После стирки мерил высыхает очень быстро, а также его не обязательно гладить.',
   TRUE),

  ('Dewspo Milky, костюмная',
   '«Текстэль Дьюспо» - 100% полиэфирная ткань полотняного переплетения. ' ||
   'Достаточно плотное полотно с небольшим блеском. Из такого материала часто шьется парадная, красивая форма. ' ||
   'Одежда из дюспо, как правило, выполняется с подкладкой.Также часто используют для изготовления наружной рекламы, декораций, '' ||
   ''флагов и промоодежды. Milky – пропитка белого цвета, нанесённая с лицевой и изнаночной стороны, для усиления яркости цвета '
   ||
   'и снижения прозрачности',
   TRUE),

  ('Dewspo PU,WR, курточная',
   'Ткань Dewspo PU,WR,Milky входит в группу курточных тканей, с водопроницаемой и водоотталкивающей пропиткой, которая '
   ||
   'также придаёт блеск и гладкость поверхности изделий. Ткань Дюспо является современной тканью, из синтетического волокна, '
   ||
   'с полотняным переплетением. Основа ткани – полиамидное волокно, с высококачественной пропиткой, которая устойчива к '
   ||
   'воздействию органических растворителей, жиру, поту. Обозначения пропитки:
PU-ткань обработана бесцветной полиуретановой отделкой с изнаночной стороны;
WR – водоотталкивающая пропитка;',
   TRUE),

  ('Спринт (Эластик)',
   ' Эластик внешне представляет собой абсолютно гладкий материал с одной стороны и мягкий, теплый, напоминающий флис, с другой. '
   ||
   'Материал отлично держит тепло, поэтому подходит для осенне-весеннего периода. Полиэстер 100%',
   TRUE),

  ('Ложная сетка (Тактель)',
   ' 100% полиэфирная ткань ячеистой, несквозной структуры. Ложная сетка — одна из самых популярных тканей для производства '
   ||
   'спортивной формы практически любого назначения. Подходит для изготовления футбольной, баскетбольной, хоккейной формы и не только.',
   TRUE);

INSERT INTO product (name, article, picture, value, material_id) VALUES
  ('Костюм спортивный', 'KP30', 'http://sportoras.com/wp-content/uploads/2017/10/paradnye-kostjumy-11-red-624x945.jpg',
   144.00, 2),
  ('Костюм спортивный', 'KP35', 'http://sportoras.com/wp-content/uploads/2018/04/img_6475-7-1-small-624x882.jpg',
   150.00, 2),
  ('Костюм спортивный', 'KP20', 'http://sportoras.com/wp-content/uploads/2016/08/h62.jpg', 120.00, 3),
  ('Костюм тренировочный', 'FB15', 'http://sportoras.com/wp-content/uploads/2018/04/img_6491-10-1-small-624x882.jpg',
   108.00, 4),
  ('Форма игровая', 'GS8', 'http://sportoras.com/wp-content/uploads/2018/04/img_6581-24-1-small-624x882.jpg', 64.20, 5),
  ('Форма игровая', 'GS6', 'http://sportoras.com/wp-content/uploads/2013/09/img_1370-kopyja1-624x935.jpg', 60.00, 1),
  ('Жилет', 'WG10', 'http://sportoras.com/wp-content/uploads/2016/08/wg1021.jpg', 64.00, 3);

