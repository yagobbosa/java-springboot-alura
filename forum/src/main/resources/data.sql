insert into User (name, email, password) values ('Student', 'yago@gmail.com', '$2a$10$O8jeuOweBH89viYFMu3wM.ch2hSdzNl8XtYNojrZoip5YDHGDg6la');
insert into User (name, email, password) values ('Moderator', 'moderador@gmail.com', '$2a$10$O8jeuOweBH89viYFMu3wM.ch2hSdzNl8XtYNojrZoip5YDHGDg6la');

insert into Profile (id, name) values (1, 'ROLE_STUDENT');
insert into Profile (id, name) values (2, 'ROLE_MODERATOR');

insert into User_Profile (user_id, profile_id) values (1, 1);
insert into User_Profile (user_id, profile_id) values (2, 2);

insert into Course (name, category) values ('Spring Boot', 'Programming');
insert into Course (name, category) values ('HTML 5', 'Front-end');

insert into Topic (title, message, creation_date, status, author_id, course_id) values ('Query 1', 'Error creating project', '2022-04-24 21:00:00', 'NOT_ANSWERED', 1, 1);
insert into Topic (title, message, creation_date, status, author_id, course_id) values ('Query 2', 'Project does not compile', '2022-04-24 22:00:00', 'NOT_ANSWERED', 1, 1);
insert into Topic (title, message, creation_date, status, author_id, course_id) values ('Query 3', 'Tag HTML', '2022-04-24 23:00:00', 'NOT_ANSWERED', 1, 2);