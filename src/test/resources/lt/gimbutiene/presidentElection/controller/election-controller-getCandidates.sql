DELETE FROM voter;
DELETE FROM region;
DELETE FROM candidate;

INSERT INTO candidate (number, name, agenda) VALUES
    (2, 'Gitanas Nausėda', 'Lietuva – transatlantinėje erdvėje'),
    (6, 'Vytenis Povilas Andriukaitis', 'Socialinė politika, socialinės valstybės kūrimo šalininkas'),
    (4, 'Arvydas Juozaitis', 'Už tradicinės vertybes'),
    (9, 'Valentinas Mazuronis', 'Tautinės valstybės puoselėjimas'),
    (5, 'Ingrida Šimonytė', 'Vakarų vertybės ir į vakarus orietuota užsienio politika'),
    (8, 'Naglis Puteikis', 'Teisingumas teismų sektoriuje, kova su elitu'),
    (3, 'Valdemar Tomaševski', 'Už tradicines vertybes'),
    (7, 'Saulius Skvernelis', 'Aš jumis pasirūpinsiu, o tuos, kurie daro negerus darbus – nubausiu'),
    (1, 'Mindaugas Puidokas', 'Stipri tradicinė šeima');