
    create table blogs (
       blog_id bigint not null auto_increment,
        blogged_date datetime,
        content varchar(255) not null,
        date_string varchar(255),
        image varchar(50) default NULL,
        title varchar(255) not null,
        blogger_user_id bigint,
        primary key (blog_id)
    ) engine=InnoDB

    create table blogs_comments (
       blog_blog_id bigint not null,
        comments_comment_id bigint not null
    ) engine=InnoDB

    create table comments (
       comment_id bigint not null auto_increment,
        content varchar(255) not null,
        commenter_user_id bigint,
        primary key (comment_id)
    ) engine=InnoDB

    create table posts (
       post_id bigint not null auto_increment,
        category varchar(255) not null,
        content varchar(255) not null,
        date_string varchar(255),
        image varchar(50) default NULL,
        posted_date datetime,
        title varchar(255) not null,
        primary key (post_id)
    ) engine=InnoDB

    create table posts_comments (
       post_post_id bigint not null,
        comments_comment_id bigint not null
    ) engine=InnoDB

    create table roles (
       id bigint not null auto_increment,
        role varchar(10) default 'USER',
        primary key (id)
    ) engine=InnoDB

    create table user_role (
       user_id bigint not null,
        role_id bigint not null,
        primary key (user_id, role_id)
    ) engine=InnoDB

    create table users (
       user_id bigint not null auto_increment,
       enabled int,
        date_string varchar(255),
        email varchar(255),
        joining_date datetime,
        name varchar(255),
        password varchar(255),
        sex varchar(255),
        primary key (user_id)
    ) engine=InnoDB

    alter table users
       add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email)

    alter table blogs
       add constraint FK9b8vkbu11uvcdjguqmnuy3ox9
       foreign key (blogger_user_id)
       references users (user_id)

    alter table blogs_comments
       add constraint FKdxo10ylp58aw8vc7nhg2cjt95
       foreign key (comments_comment_id)
       references comments (comment_id)

    alter table blogs_comments
       add constraint FKl478181p6qfxiuxcb7rpfamm4
       foreign key (blog_blog_id)
       references blogs (blog_id)

    alter table comments
       add constraint FKpsv7livucf5f1od6p39814jvh
       foreign key (commenter_user_id)
       references users (user_id)

    alter table posts_comments
       add constraint FK608b8p55kej4ce02qk9uim6be
       foreign key (comments_comment_id)
       references comments (comment_id)

    alter table posts_comments
       add constraint FKiq8a7nqb171ojc9xk99lxoisd
       foreign key (post_post_id)
       references posts (post_id)

    alter table user_role
       add constraint FKt7e7djp752sqn6w22i6ocqy6q
       foreign key (role_id)
       references roles (id)

    alter table user_role
       add constraint FKj345gk1bovqvfame88rcx7yyx
       foreign key (user_id)
       references users (user_id)
