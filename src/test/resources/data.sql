DELETE FROM photos;
DELETE FROM albums;

INSERT INTO albums (id, title) VALUES (1, 'album 1');

INSERT INTO photos (id, title, url, thumbnail_url, album_id) VALUES (1, 'photo 1', 'www.url_1.com', 'www.thumbnailUrl_1.com', 1);
INSERT INTO photos (id, title, url, thumbnail_url, album_id) VALUES (2, 'photo 2', 'www.url_2.com', 'www.thumbnailUrl_2.com', 1);