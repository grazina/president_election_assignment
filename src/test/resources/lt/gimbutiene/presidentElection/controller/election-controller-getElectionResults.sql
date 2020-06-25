DELETE FROM voter;
DELETE FROM region;
DELETE FROM candidate;

INSERT INTO candidate (id, number, name, agenda) VALUES
    (1, 1, 'Gitanas Nausėda', 'Lietuva – transatlantinėje erdvėje'),
    (2, 2, 'Ingrida Šimonytė', 'Vakarų vertybės ir į vakarus orietuota užsienio politika');


INSERT INTO region (id, name) VALUES
    (1, 'Alytaus apskr.');

INSERT INTO voter (id, region_id, selected_candidate_id) VALUES
    (1, 1, 1),
    (2, 1, 1),
    (3, 1, 1),
    (4, 1, null),
    (5, 1, 2),
    (6, 1, 2),
    (7, 1, 1);
