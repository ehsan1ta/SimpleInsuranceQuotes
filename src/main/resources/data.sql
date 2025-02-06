INSERT INTO provider (id, name) VALUES (1, 'Dubai National Insurance');
INSERT INTO provider (id, name) VALUES (2, 'Oman Insurance Company');
INSERT INTO provider (id, name) VALUES (3, 'Qatar Insurance Company');

INSERT INTO quote (id, coverage_type, price, provider_id) VALUES (1, 'Health', 1200.00, 1);
INSERT INTO quote (id, coverage_type, price, provider_id) VALUES (2, 'Car', 800.00, 1);
INSERT INTO quote (id, coverage_type, price, provider_id) VALUES (3, 'Life', 1500.00, 2);
INSERT INTO quote (id, coverage_type, price, provider_id) VALUES (4, 'Home', 600.00, 3);
INSERT INTO quote (id, coverage_type, price, provider_id) VALUES (5, 'Travel', 300.00, 3);