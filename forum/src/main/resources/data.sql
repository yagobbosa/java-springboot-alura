insert into User (name, email, password) values ('yago', 'yago@gmail.com', '$2a$10$O8jeuOweBH89viYFMu3wM.ch2hSdzNl8XtYNojrZoip5YDHGDg6la');

insert into Course (name, category) values ('Spring Boot', 'Programming');
insert into Course (name, category) values ('HTML 5', 'Front-end');

insert into Topic (title, message, creation_date, status, author_id, course_id) values ('Query 1', 'Error creating project', '2022-04-24 21:00:00', 'NOT_ANSWERED', 1, 1);

insert into Topic (title, message, creation_date, status, author_id, course_id) values ('Query 2', 'Project does not compile', '2022-04-24 22:00:00', 'NOT_ANSWERED', 1, 1);
	
insert into Topic (title, message, creation_date, status, author_id, course_id) values ('Query 3', 'Tag HTML', '2022-04-24 23:00:00', 'NOT_ANSWERED', 1, 2);