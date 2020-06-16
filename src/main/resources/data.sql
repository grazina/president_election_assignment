INSERT INTO candidate (number, name, agenda) VALUES
    (1, 'Vytenis Povilas Andriukaitis', 'Socialinė politika, socialinės valstybės kūrimo šalininkas'),
    (2, 'Arvydas Juozaitis', 'Už tradicinės vertybes'),
    (3, 'Valentinas Mazuronis', 'Tautinės valstybės puoselėjimas'),
    (4, 'Gitanas Nausėda', 'Lietuva – transatlantinėje erdvėje'),
    (5, 'Mindaugas Puidokas', 'Stipri tradicinė šeima'),
    (6, 'Naglis Puteikis', 'Teisingumas teismų sektoriuje, kova su elitu'),
    (7, 'Saulius Skvernelis', 'Aš jumis pasirūpinsiu, o tuos, kurie daro negerus darbus – nubausiu'),
    (8, 'Ingrida Šimonytė', 'Vakarų vertybės ir į vakarus orietuota užsienio politika'),
    (9, 'Valdemar Tomaševski', 'Už tradicines vertybes');

INSERT INTO region (id, name) VALUES
    (1, 'Alytaus apskr.'),
    (2, 'Kauno apskr.'),
    (3, 'Klaipėdos apskr.'),
    (4, 'Marijampolės apskr.'),
    (5, 'Panevėžio apskr.'),
    (6, 'Šiaulių apskr.'),
    (7, 'Tauragės apskr.'),
    (8, 'Telšių apskr.'),
    (9, 'Utenos apskr.'),
    (10, 'Vilniaus apskr.');


INSERT INTO voter (id, region_id) VALUES
    (1, 1),
    (2, 1),
    (3, 1),
    (4, 2),
    (5, 2),
    (6, 2),
    (7, 3),
    (8, 3),
    (9, 3),
    (10, 4),
    (11, 4),
    (12, 4),
    (13, 5),
    (14, 5),
    (15, 5);
