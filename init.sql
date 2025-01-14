create table exam.COMMENT
(
    seq          bigint auto_increment
        primary key,
    postSeq      bigint       not null,
    userSeq      bigint       not null,
    content      varchar(255) not null,
    registerDate datetime(6)  not null,
    updateDate   datetime(6)  not null
);

create table exam.POST
(
    seq          bigint auto_increment
        primary key,
    userSeq      bigint       not null,
    content      varchar(255) not null,
    title        varchar(255) not null,
    registerDate datetime(6)  not null,
    updateDate   datetime(6)  not null
);

create table exam.POST_ATTACH_FILE
(
    postSeq      bigint       not null,
    seq          bigint auto_increment
        primary key,
    fileName     varchar(255) not null,
    filePath     varchar(255) not null,
    registerDate datetime(6)  not null
);

create table exam.USER
(
    seq          bigint auto_increment
        primary key,
    email        varchar(255) not null,
    name         varchar(255) not null,
    nickname     varchar(255) not null,
    password     varchar(255) not null,
    registerDate datetime(6)  not null,
    updateDate   datetime(6)  not null,
    constraint USER_pk
        unique (email)
);

create table exam.USER_ROLE
(
    userSeq  bigint       not null
        primary key,
    roleCode varchar(255) not null
);

