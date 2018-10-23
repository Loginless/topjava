DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (user_id, date_time, description, calories) VALUES
  (100000, '2018/05/23T10:00', 'Potato', 1300),
  (100000, '2018/05/23T13:00', 'Fish and Mushrooms', 1200),
  (100000, '2018/05/23T20:00', 'Soup', 400),
  (100000, '2018/06/05T10:00', 'Beer and Chips', 5000),
  (100000, '2018/06/05T15:00', 'Sweets', 1000),
  (100000, '2018/06/05T21:00', 'Biscuits', 250),
  (100001, '2018/10/23T08:00', 'Breakfast', 2500),
  (100001, '2018/10/23T20:00', 'Dinner', 3500);
