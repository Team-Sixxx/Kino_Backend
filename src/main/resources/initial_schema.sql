USE kinodb;
create table employee (employee_id bigint not null auto_increment, email varchar(255), name varchar(255) not null, password varchar(255) not null, phone varchar(255), role varchar(255), primary key (employee_id)) engine=InnoDB;
create table film (extra_screenings bit not null, film_duration integer, rating integer, end_date datetime(6) not null, film_id bigint not null auto_increment, release_date datetime(6) not null, start_time datetime(6) not null, description TEXT, genre varchar(255), title varchar(255) not null, primary key (film_id)) engine=InnoDB;
create table membership (end_date datetime(6) not null, membership_id bigint not null auto_increment, start_date datetime(6) not null, user_id bigint, status enum ('ACTIVE','INACTIVE','CANCELLED'), primary key (membership_id)) engine=InnoDB;
create table preference (preference_id bigint not null auto_increment, user_id bigint, category varchar(255), primary key (preference_id)) engine=InnoDB;
create table role (role_name varchar(255) not null, primary key (role_name)) engine=InnoDB;
create table screening (number_of_seats integer not null, end_time datetime(6) not null, film_id bigint, screening_id bigint not null auto_increment, start_time datetime(6) not null, theater_id bigint, primary key (screening_id)) engine=InnoDB;
create table screening_history (attendees integer not null, end_time datetime(6) not null, screening_id bigint not null, start_time datetime(6) not null, theater_id bigint not null, primary key (screening_id, theater_id)) engine=InnoDB;

create table theater (number_of_rows integer not null, seats_per_row integer not null, theater_id bigint not null auto_increment, primary key (theater_id)) engine=InnoDB;

CREATE TABLE Seat (
    `row_number` INTEGER,
    seat_number INTEGER,
    seat_id BIGINT NOT NULL AUTO_INCREMENT,
    theater_id BIGINT,
    status VARCHAR(255),
    PRIMARY KEY (seat_id)
) ENGINE=InnoDB;
create table ticket (price float(53) not null, screening_id bigint, seat_id bigint, ticket_id bigint not null auto_increment, seat_number varchar(255), status enum ('Available','Booked','Cancelled'), primary key (ticket_id)) engine=InnoDB;
create table user (user_id bigint not null auto_increment, email varchar(255) not null, name varchar(255) not null, password varchar(255) not null, username varchar(255) not null, primary key (user_id)) engine=InnoDB;
create table user_roles (user_username varchar(50) not null, role_role_name varchar(255) not null, primary key (user_username, role_role_name)) engine=InnoDB;
create table user_with_roles (enabled bit not null, created datetime(6), edited datetime(6), discriminator_type varchar(31) not null, email varchar(50) not null, username varchar(50) not null, password varchar(60) not null, primary key (username)) engine=InnoDB;
alter table ticket add constraint UK_3yhl9h2vv803mhf1jpk8puq4a unique (seat_id);
alter table user add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email);
alter table user add constraint UK_sb8bbouer5wak8vyiiy4pf2bx unique (username);
alter table user_with_roles add constraint UK_7llpsvtlaiyka7qv7aw3nywl9 unique (email);
alter table membership add constraint FKjp7ht675da9n751xycuwii77s foreign key (user_id) references user (user_id);
alter table preference add constraint FKsv3xgeggccs516fm0ng21o1t0 foreign key (user_id) references user (user_id);
alter table screening add constraint FKr5f8ggthy4oigd31nbq91csww foreign key (film_id) references film (film_id);
alter table screening add constraint FKcamygvcxv5aywcmjogp6uayre foreign key (theater_id) references theater (theater_id);
alter table screening_history add constraint FKe0qpov70eux00br2rht26sbif foreign key (theater_id) references theater (theater_id);
alter table screening_history add constraint FKmtexgwem9dwrs5wljrunu4m96 foreign key (screening_id) references screening (screening_id);
alter table seat add constraint FKgik5885qsff01sxe7v3kqjrhx foreign key (theater_id) references theater (theater_id);
alter table ticket add constraint FKslsbfjfvsw5v43w11jm31x0c6 foreign key (screening_id) references screening (screening_id);
alter table ticket add constraint FKqahao9a85drt47ikjp0b8syvh foreign key (seat_id) references seat (seat_id);
alter table user_roles add constraint FK2cxih12qg115wmuxrn7uqy1ev foreign key (role_role_name) references role (role_name);
alter table user_roles add constraint FKn2p3ur18g3rpkt7blvyn8am5c foreign key (user_username) references user_with_roles (username);