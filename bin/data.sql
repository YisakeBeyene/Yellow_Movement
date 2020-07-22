

INSERT INTO `users` (`date_string`, `email`, `enabled`, `joining_date`, `name`, `password`, `sex`) VALUES ('3:14 AM - Wed, Jan 19', 'admin1@admin.com', '1', '2013-01-19 03:14:07', 'Admin 1', '0000', 'female');
INSERT INTO `users` (`date_string`, `email`, `enabled`, `joining_date`, `name`, `password`, `sex`) VALUES ('5:14 AM - Sun, Feb 09', 'example1@example.com', '1', '2015-02-09 05:14:07', 'User 1', '0000', 'female');
INSERT INTO `users` (`date_string`, `email`, `enabled`, `joining_date`, `name`, `password`, `sex`) VALUES ('12:25 AM - Mon, Nov 25', 'example2@example.com', '1', '2017-11-25 12:25:07', 'User 2', '0000', 'male');
INSERT INTO `users` (`date_string`, `email`, `enabled`, `joining_date`, `name`, `password`, `sex`) VALUES ('9:24 AM - Tue, Sep 10', 'example1@example.com', '1', '2018-09-10 09:24:07', 'User 3', '0000', 'female');

INSERT INTO `roles` (`role`) VALUES ('ADMIN');
INSERT INTO `roles` (`role`) VALUES ('USER');
INSERT INTO `roles` (`role`) VALUES ('BLOGGER');

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES ('1', '1');
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES ('2', '2');
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES ('3', '2');
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES ('4', '3');


INSERT `posts` (`category`, `content`, `date_string`, `posted_date`, `title`) VALUES ('General', 'Here goes the contents of the first post in the entry', '10:35 AM - Fri, Oct 05, 2010', '2010-10-05 10:35:07', 'Post 1');
INSERT `posts` (`category`, `content`, `date_string`, `posted_date`, `title`) VALUES ('Science and Technology', 'Then goes the second post with the blah and blah.', '12:25 AM - Mon, Nov 25, 2017', '2017-11-25 12:25:07', 'Post 2');
INSERT `posts` (`category`, `content`, `date_string`, `posted_date`, `title`) VALUES ('Feminist Clubs', 'And here i declare post thy third with his grace and glory', '2:20 AM - Sun, Jan 30, 2017', '2017-1-30 2:20:07', 'Post 3');
INSERT `posts` (`category`, `content`, `date_string`, `posted_date`, `title`) VALUES ('Entertainment', 'Fourthly, we all die and nothing in life matters', '12:25 AM - Mon, Apr 1, 2018', '2018-4-1 12:25:07', 'Post 4');


INSERT `posts` (`category`, `content`, `date_string`, `posted_date`, `title`) VALUES ('Entertainment', 'Additional Posts are also very important in certain scenarios.\n such as this one\n and this', '5:25 AM - Wed, Dec 25, 2017', '2016-12-25 5:25:07', 'Post 5');
