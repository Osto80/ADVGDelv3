CREATE DATABASE adreviews;

CREATE USER adreview_user WITH PASSWORD 'addemo';

GRANT ALL PRIVILEGES ON DATABASE adreviews to adreview_user;

select * from app_user;
select * from review;
select * from game;

DROP TABLE IF EXISTS app_user;
DROP TABLE IF EXISTS review;
DROP TABLE IF EXISTS game;



