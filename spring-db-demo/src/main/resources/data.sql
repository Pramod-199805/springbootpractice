create table person
(
  id integer not null,
  birth_date timestamp,
  location varchar(255),
  name varchar(255),
  primary key (id)
);

INSERT INTO PERSON (ID, NAME, LOCATION, BIRTH_DATE ) VALUES(10001,  'Ranga', 'Hyderabad',CURRENT_TIMESTAMP);
INSERT INTO PERSON (ID, NAME, LOCATION, BIRTH_DATE ) VALUES(10002,  'James', 'New York',CURRENT_TIMESTAMP);
INSERT INTO PERSON (ID, NAME, LOCATION, BIRTH_DATE ) VALUES(10003,  'Pieter', 'Amsterdam',CURRENT_TIMESTAMP);
