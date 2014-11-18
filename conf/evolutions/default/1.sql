# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table post (
  id                        bigint auto_increment not null,
  title                     varchar(255),
  text                      varchar(255),
  is_active                 tinyint(1) default 0,
  created_at                datetime,
  modified_at               datetime,
  user_id                   bigint,
  constraint pk_post primary key (id))
;

create table user (
  id                        bigint auto_increment not null,
  user_name                 varchar(255),
  full_name                 varchar(255),
  email_address             varchar(255),
  pass_word                 varchar(255),
  is_active                 tinyint(1) default 0,
  constraint pk_user primary key (id))
;

alter table post add constraint fk_post_user_1 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_post_user_1 on post (user_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table post;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

