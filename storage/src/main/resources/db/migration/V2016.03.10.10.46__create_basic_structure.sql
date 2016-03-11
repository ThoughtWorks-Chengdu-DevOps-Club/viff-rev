------------------------------------------------------------------
--  TABLE project
------------------------------------------------------------------

CREATE TABLE project
(
   id             bigint(20),
   time_created   datetime,
   version        bigint(20) DEFAULT 0,
   project_id     varchar(20)
);


------------------------------------------------------------------\
--  TABLE tag
------------------------------------------------------------------

CREATE TABLE tag
(
   id             bigint(20),
   time_created   datetime,
   version        bigint(20) DEFAULT 0,
   project_id     bigint(20),
   tag_name       varchar(20),
   latest_build_id   bigint(20)
);


------------------------------------------------------------------
--  TABLE build
------------------------------------------------------------------

CREATE TABLE build
(
   id             bigint(20),
   time_created   datetime,
   version        bigint(20) DEFAULT 0,
   tag_id         bigint(20),
   build_number   int(10)
);


------------------------------------------------------------------
--  TABLE file
------------------------------------------------------------------

CREATE TABLE file
(
   id             bigint(20),
   time_created   datetime,
   version        bigint(20) DEFAULT 0,
   file_name      varchar(1024),
   file_path      varchar(2048)
);


------------------------------------------------------------------
--  TABLE viff
------------------------------------------------------------------

CREATE TABLE viff
(
   id                bigint(20),
   time_created      datetime,
   version           bigint(20) DEFAULT 0,
   project_id        bigint(20),
   origin_tag_id     bigint(20),
   origin_build_id   bigint(20),
   target_tag_id     bigint(20),
   target_build_id   bigint(20)
);


------------------------------------------------------------------
--  TABLE viff_file_item
------------------------------------------------------------------

CREATE TABLE viff_file_item
(
   id               bigint(20),
   time_created     datetime,
   version          bigint(20) DEFAULT 0,
   origin_file_id   bigint(20),
   target_file_id   bigint(20),
   viff_file_path   varchar(2048)
);


