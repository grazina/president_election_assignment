DELETE FROM voter;
DELETE FROM region;
DELETE FROM candidate;

INSERT INTO candidate (id, number, name, agenda) VALUES
    (1, 1, 'Vytenis Povilas Andriukaitis', 'Socialinė politika, socialinės valstybės kūrimo šalininkas'),
    (2, 2, 'Arvydas Juozaitis', 'Už tradicinės vertybes'),
    (3, 3, 'Valentinas Mazuronis', 'Tautinės valstybės puoselėjimas');

INSERT INTO region (id, name) VALUES
    (1, 'Alytaus apskr.'),
    (2, 'Kauno apskr.');

INSERT INTO voter (id, region_id, selected_candidate_id) VALUES
    (1, 1, 1),
    (2, 1, 1),
    (3, 1, 2),
    (4, 2, 1),
    (5, 2, 1);