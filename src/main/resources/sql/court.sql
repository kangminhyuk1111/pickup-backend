CREATE TABLE court (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       location VARCHAR(255) NOT NULL,
                       address VARCHAR(255) NOT NULL,
                       latitude DOUBLE NOT NULL,
                       longitude DOUBLE NOT NULL,
                       hoops INT NOT NULL,
                       surface VARCHAR(50) NOT NULL,
                       lighting BOOLEAN NOT NULL,
                       parking BOOLEAN NOT NULL,
                       rating DOUBLE NOT NULL,
                       best_time VARCHAR(50)
);

INSERT INTO court (name, location, address, latitude, longitude, hoops, surface, lighting, parking, rating, best_time)
VALUES
    ('잠실 한강공원 농구장', '송파구', '서울 송파구 잠실동 1-1', 37.5185, 127.0765, 4, '아스팔트', true, true, 4.5, '저녁'),
    ('반포 종합운동장', '서초구', '서울 서초구 반포동 123', 37.5038, 127.0087, 6, '고무', true, true, 4.8, '아침'),
    ('강남 도곡공원 농구장', '강남구', '서울 강남구 도곡동 123-45', 37.4867, 127.0454, 2, '아스팔트', true, false, 4.2, '저녁'),
    ('양재천 농구장', '서초구', '서울 서초구 양재동 236', 37.4734, 127.0432, 4, '고무', true, true, 4.6, '오후'),
    ('천호공원 농구장', '강동구', '서울 강동구 천호동 456', 37.5454, 127.1234, 2, '아스팔트', false, true, 4.0, '오전'),
    ('올림픽공원 농구장', '송파구', '서울 송파구 방이동 88', 37.5202, 127.1212, 6, '고무', true, true, 4.9, '저녁'),
    ('동작 달빛농구장', '동작구', '서울 동작구 상도동 789', 37.5012, 126.9432, 4, '아스팔트', true, false, 4.3, '저녁'),
    ('뚝섬한강공원 농구장', '광진구', '서울 광진구 자양동 410-1', 37.5297, 127.0668, 4, '고무', true, true, 4.7, '저녁');

CREATE TABLE court_images (
                              court_id BIGINT,
                              image_url VARCHAR(255),
                              FOREIGN KEY (court_id) REFERENCES court(id)
);

CREATE TABLE court_facilities (
                                  court_id BIGINT,
                                  facility VARCHAR(50),
                                  FOREIGN KEY (court_id) REFERENCES court(id)
);

INSERT INTO court_images (court_id, image_url)
VALUES
    (1, '/img/court/jamsil.jpg'),
    (2, '/img/court/banpo.jpg'),
    (3, '/img/court/dogok.jpg'),
    (4, '/img/court/yangjae.jpg'),
    (5, '/img/court/cheonho.jpg'),
    (6, '/img/court/olympicpark.jpg'),
    (7, '/img/court/boramae.jpg'),
    (8, '/img/court/dduksum.jpg');

INSERT INTO court_facilities (court_id, facility)
VALUES
    (1, '화장실'),
    (1, '편의점'),
    (1, '벤치'),
    (2, '화장실'),
    (2, '주차장'),
    (2, '음수대'),
    (3, '벤치'),
    (3, '음수대'),
    (4, '화장실'),
    (4, '주차장'),
    (4, '벤치'),
    (5, '화장실'),
    (5, '벤치'),
    (6, '화장실'),
    (6, '편의점'),
    (6, '주차장'),
    (6, '음수대'),
    (7, '화장실'),
    (7, '벤치'),
    (7, '음수대'),
    (8, '화장실'),
    (8, '편의점'),
    (8, '주차장'),
    (8, '벤치');CREATE TABLE court (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     name VARCHAR(255) NOT NULL,
                                     location VARCHAR(255) NOT NULL,
                                     address VARCHAR(255) NOT NULL,
                                     latitude DOUBLE NOT NULL,
                                     longitude DOUBLE NOT NULL,
                                     hoops INT NOT NULL,
                                     surface VARCHAR(50) NOT NULL,
                                     lighting BOOLEAN NOT NULL,
                                     parking BOOLEAN NOT NULL,
                                     rating DOUBLE NOT NULL,
                                     best_time VARCHAR(50)
              );

INSERT INTO court (name, location, address, latitude, longitude, hoops, surface, lighting, parking, rating, best_time)
VALUES
    ('잠실 한강공원 농구장', '송파구', '서울 송파구 잠실동 1-1', 37.5185, 127.0765, 4, '아스팔트', true, true, 4.5, '저녁'),
    ('반포 종합운동장', '서초구', '서울 서초구 반포동 123', 37.5038, 127.0087, 6, '고무', true, true, 4.8, '아침'),
    ('강남 도곡공원 농구장', '강남구', '서울 강남구 도곡동 123-45', 37.4867, 127.0454, 2, '아스팔트', true, false, 4.2, '저녁'),
    ('양재천 농구장', '서초구', '서울 서초구 양재동 236', 37.4734, 127.0432, 4, '고무', true, true, 4.6, '오후'),
    ('천호공원 농구장', '강동구', '서울 강동구 천호동 456', 37.5454, 127.1234, 2, '아스팔트', false, true, 4.0, '오전'),
    ('올림픽공원 농구장', '송파구', '서울 송파구 방이동 88', 37.5202, 127.1212, 6, '고무', true, true, 4.9, '저녁'),
    ('동작 달빛농구장', '동작구', '서울 동작구 상도동 789', 37.5012, 126.9432, 4, '아스팔트', true, false, 4.3, '저녁'),
    ('뚝섬한강공원 농구장', '광진구', '서울 광진구 자양동 410-1', 37.5297, 127.0668, 4, '고무', true, true, 4.7, '저녁');

CREATE TABLE court_images (
                              court_id BIGINT,
                              image_url VARCHAR(255),
                              FOREIGN KEY (court_id) REFERENCES court(id)
);

CREATE TABLE court_facilities (
                                  court_id BIGINT,
                                  facility VARCHAR(50),
                                  FOREIGN KEY (court_id) REFERENCES court(id)
);

INSERT INTO court_images (court_id, image_url)
VALUES
    (1, '/img/court/jamsil.jpg'),
    (2, '/img/court/banpo.jpg'),
    (3, '/img/court/dogok.jpg'),
    (4, '/img/court/yangjae.jpg'),
    (5, '/img/court/cheonho.jpg'),
    (6, '/img/court/olympicpark.jpg'),
    (7, '/img/court/boramae.jpg'),
    (8, '/img/court/dduksum.jpg');

INSERT INTO court_facilities (court_id, facility)
VALUES
    (1, '화장실'),
    (1, '편의점'),
    (1, '벤치'),
    (2, '화장실'),
    (2, '주차장'),
    (2, '음수대'),
    (3, '벤치'),
    (3, '음수대'),
    (4, '화장실'),
    (4, '주차장'),
    (4, '벤치'),
    (5, '화장실'),
    (5, '벤치'),
    (6, '화장실'),
    (6, '편의점'),
    (6, '주차장'),
    (6, '음수대'),
    (7, '화장실'),
    (7, '벤치'),
    (7, '음수대'),
    (8, '화장실'),
    (8, '편의점'),
    (8, '주차장'),
    (8, '벤치');

select * from court;

select * from court_facilities;

select * from court_images;

SELECT c.id, c.name, c.location, c.address, c.latitude, c.longitude, c.hoops, c.surface, c.lighting, c.parking, c.rating, c.best_time,
       GROUP_CONCAT(DISTINCT ci.image_url ORDER BY ci.image_url SEPARATOR ',') AS image_urls,
       GROUP_CONCAT(DISTINCT cf.facility ORDER BY cf.facility SEPARATOR ',') AS facilities
FROM court c
         LEFT JOIN court_images ci ON c.id = ci.court_id
         LEFT JOIN court_facilities cf ON c.id = cf.court_id
GROUP BY c.id;