-- Manuelles Erstellen der 'users' Tabelle
CREATE TABLE IF NOT EXISTS users (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY, -- ID wird automatisch generiert
                                     username VARCHAR(255) NOT NULL UNIQUE,
                                     password VARCHAR(255) NOT NULL
);

-- Manuelles Erstellen der 'wishlist_items' Tabelle
CREATE TABLE IF NOT EXISTS wishlist_items (
                                              id BIGINT AUTO_INCREMENT PRIMARY KEY, -- ID wird automatisch generiert
                                              user_id BIGINT NOT NULL,
                                              item_name VARCHAR(255) NOT NULL,
                                              FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Testbenutzer erstellen (Passwörter sind mit BCrypt verschlüsselt)
INSERT INTO users (username, password) VALUES ('testuser', '$2a$10$yGe0GGz9hPT6KBg0Ivw5wepOeEJZaCh8Rek7qPmfE3nC2hFglkpY2');
INSERT INTO users (username, password) VALUES ('seconduser', '$2a$10$yGe0GGz9hPT6KBg0Ivw5wepOeEJZaCh8Rek7qPmfE3nC2hFglkpY2');

-- Wunschlisteneinträge für Benutzer 1 erstellen
INSERT INTO wishlist_items (user_id, item_name) VALUES (1, 'Neue Sneakers');
INSERT INTO wishlist_items (user_id, item_name) VALUES (1, 'Coole Jacke');

-- Wunschlisteneinträge für Benutzer 2 erstellen
INSERT INTO wishlist_items (user_id, item_name) VALUES (2, 'Laptop Rucksack');
INSERT INTO wishlist_items (user_id, item_name) VALUES (2, 'Smartwatch');
