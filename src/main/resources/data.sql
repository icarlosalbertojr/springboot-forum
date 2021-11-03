INSERT INTO FORUM_USER(id, full_Name, email, password) VALUES (1, 'Carlos Alberto', 'alberto@test.com', '123456');
INSERT INTO FORUM_USER(id, full_Name, email, password) VALUES (2, 'Carlos Bezerra', 'bezerra@test.com', '123456');
INSERT INTO COURSE(id, course_name, category) VALUES (1, 'Spring Boot 2', 'Programming');
INSERT INTO COURSE(id, course_name, category) VALUES (2, 'Advanced Java', 'Programming');
INSERT INTO TOPIC(id, title, message, course_id, author_id) VALUES (1, 'Problem Spring 2', 'How to do CRUD using Spring 2?', 1, 1);
INSERT INTO TOPIC(id, title, message, course_id, author_id) VALUES (2, 'Problem Spring 3', 'How to do CRUD using Spring 3?', 2, 2);
INSERT INTO TOPIC(id, title, message, course_id, author_id) VALUES (3, 'Problem Spring 4', 'How to do CRUD using Spring 4?', 1, 2);
