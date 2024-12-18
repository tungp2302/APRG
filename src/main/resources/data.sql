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

-- Neue Tabelle für geteilte Wunschlisten
CREATE TABLE IF NOT EXISTS shared_wishlists (
                                                id BIGINT AUTO_INCREMENT PRIMARY KEY, -- ID wird automatisch generiert
                                                owner_id BIGINT NOT NULL, -- Benutzer, dem die Wunschliste gehört
                                                recipient_email VARCHAR(255) NOT NULL, -- E-Mail des Empfängers
                                                shared_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Zeitpunkt des Teilens
                                                FOREIGN KEY (owner_id) REFERENCES users(id)
);