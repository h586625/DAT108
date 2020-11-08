DROP SCHEMA IF EXISTS dat108oblig4 CASCADE;
CREATE SCHEMA dat108oblig4;
SET search_path = dat108oblig4;


CREATE TABLE deltager 
(
   mobil        VARCHAR,
   pwd_hash		VARCHAR,
   pwd_salt		VARCHAR,
   fornavn      VARCHAR,
   etternavn	VARCHAR,
   kjonn		VARCHAR,
   PRIMARY KEY (mobil)
);

INSERT INTO deltager VALUES
('66666666', -- passord: qwerty
'DF32FB5C3D132F276CA0E9B582ADA7E7B72CA1E5DE58C35D86C378A9446EE005', '38943AF5CA14AE5C1B9438FBB20233CA', 'Christoffer', 'Riis', 'mann');



