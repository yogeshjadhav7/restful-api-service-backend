# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table intellecto_app_version (
  version                       varchar(255) not null,
  `mandatory`                   integer,
  constraint pk_intellecto_app_version primary key (version)
);

create table intellecto_friends (
  id                            bigint auto_increment not null,
  `user_id`                     bigint,
  `friend_id`                   bigint,
  `user_winning_efficiency`     double,
  `friend_winning_efficiency`   double,
  `user_winning_count`          bigint,
  `friend_winning_count`        bigint,
  `last_game_time`              bigint,
  constraint pk_intellecto_friends primary key (id)
);

create table intellecto_robot_info (
  `user_id`                     bigint auto_increment not null,
  `behaviour_count`             integer,
  `trained_on_behaviour_count`  integer,
  `training_count`              integer,
  `is_training_in_progress`     integer,
  `last_training_started_on`    bigint,
  `last_training_ended_on`      bigint,
  `queued_on`                   bigint,
  `is_queued`                   integer,
  constraint pk_intellecto_robot_info primary key (`user_id`)
);

create table intellecto_users (
  `id`                          bigint auto_increment not null,
  `username`                    varchar(255),
  `email`                       varchar(255),
  `phone_number`                varchar(255),
  `country`                     varchar(255),
  `verified`                    tinyint(1) default 0,
  `device`                      varchar(255),
  constraint pk_intellecto_users primary key (`id`)
);

create table intellecto_users_behaviour (
  id                            bigint auto_increment not null,
  `user_id`                     bigint,
  `game_state`                  varchar(255),
  `user_response`               integer,
  constraint pk_intellecto_users_behaviour primary key (id)
);

create table intellecto_users_controls (
  `id`                          bigint auto_increment not null,
  `otp`                         varchar(255),
  `otp_used`                    tinyint(1) default 0,
  `last_otp_sent_at`            bigint,
  `otp_sent`                    integer,
  `blocked_till`                bigint,
  `ads_flag`                    tinyint(1) default 0,
  constraint pk_intellecto_users_controls primary key (`id`)
);

create table intellecto_users_game_info (
  `id`                          bigint auto_increment not null,
  `user_info`                   varchar(255),
  `opponent_info`               varchar(255),
  `opponent_game_info`          varchar(255),
  `game_info`                   varchar(255),
  constraint pk_intellecto_users_game_info primary key (`id`)
);

create table intellecto_users_notifications (
  `user_id`                     bigint auto_increment not null,
  `token`                       varchar(255),
  `is_notification_on`          integer,
  constraint pk_intellecto_users_notifications primary key (`user_id`)
);

create table led7_data (
  `id`                          integer auto_increment not null,
  `segment_state`               varchar(255),
  `type`                        varchar(255),
  `request_count`               integer,
  `probability_0`               float,
  `probability_1`               float,
  `probability_2`               float,
  `probability_3`               float,
  `probability_4`               float,
  `probability_5`               float,
  `probability_6`               float,
  `probability_7`               float,
  `probability_8`               float,
  `probability_9`               float,
  constraint pk_led7_data primary key (`id`)
);


# --- !Downs

drop table if exists intellecto_app_version;

drop table if exists intellecto_friends;

drop table if exists intellecto_robot_info;

drop table if exists intellecto_users;

drop table if exists intellecto_users_behaviour;

drop table if exists intellecto_users_controls;

drop table if exists intellecto_users_game_info;

drop table if exists intellecto_users_notifications;

drop table if exists led7_data;

