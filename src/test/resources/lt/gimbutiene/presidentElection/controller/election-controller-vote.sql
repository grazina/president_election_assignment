DELETE FROM voter;
DELETE FROM region;
DELETE FROM candidate;

INSERT INTO candidate (id, number, name, agenda) VALUES
    (1, 1, 'Vytenis Povilas Andriukaitis', 'Socialinė politika');

INSERT INTO region (id, name) VALUES
    (2, 'Alytaus apskr.');

INSERT INTO voter (id, region_id) VALUES
    (3, 2);