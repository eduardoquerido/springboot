drop user 'user'@'localhost';

drop schema uol;

create schema uol;

use uol;

create user 'user'@'localhost' identified by 'pass123';

grant select, insert, delete, update on uol.* to user@'localhost';

create table registration (
  registration_id bigint unsigned not null auto_increment,
  registration_email varchar(255) not null,
  registration_password varchar(255) not null,
  registration_name varchar(255) not null,
  registration_cellphone varchar(100) not null,
  primary key (registration_id),
  unique key uni_registration_email (registration_email)
);

create table profile (
  profile_id bigint unsigned not null auto_increment,
  profile_uuid varchar(100) not null,
  profile_audio_hash varchar(255) not null,
  profile_webgl_hash varchar(255) not null,
  profile_canvas_hash varchar(255) not null,
  profile_user_agent varchar(255) not null,
  profile_fonts varchar(255) not null,
  primary key (profile_id),
  unique key uni_profile_uuid (profile_uuid)
);

create table profile_registration (
  profile_id bigint unsigned not null,
  registration_id bigint unsigned not null,
  primary key (profile_id, registration_id),
  foreign key pr_profile_fk (profile_id) references profile (profile_id) on delete restrict on update cascade,
  foreign key pr_registration_fk (registration_id) references registration (registration_id) on delete restrict on update cascade
);
