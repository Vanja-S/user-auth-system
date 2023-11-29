USE
`employee_directory`;

DROP TABLE IF EXISTS `roles`;
DROP TABLE IF EXISTS `members`;

--
-- Table structure for table `members`
--

CREATE TABLE `members`
(
    `username`   varchar(50) NOT NULL,
    `first_name` varchar(45) DEFAULT NULL,
    `last_name`  varchar(45) DEFAULT NULL,
    `email`      varchar(45) DEFAULT NULL,
    `pw`         char(68)    NOT NULL,
    `active`     tinyint     NOT NULL,
    PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `members`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- A generation tool is avail at: https://www.luv2code.com/generate-bcrypt-password
--
-- Default passwords here are: fun123
--

INSERT INTO `members`
VALUES ('john11', 'John', 'Andrews', 'leslie@tv.com',
        '{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q', 1),
       ('mary11', 'Mary', 'Baumgarten', 'emma@tv.com',
        '{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q', 1),
       ('susan11', 'Susan', 'Gupta', 'avani@tv.com',
        '{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q', 1);


--
-- Table structure for table `authorities`
--

CREATE TABLE `roles`
(
    `username` varchar(50) NOT NULL,
    `role`     varchar(50) NOT NULL,
    UNIQUE KEY `authorities5_idx_1` (`username`,`role`),
    CONSTRAINT `authorities5_ibfk_1` FOREIGN KEY (`username`) REFERENCES `members` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `roles`
--

INSERT INTO `roles`
VALUES ('john11', 'ROLE_EMPLOYEE'),
       ('mary11', 'ROLE_EMPLOYEE'),
       ('mary11', 'ROLE_MANAGER'),
       ('susan11', 'ROLE_EMPLOYEE'),
       ('susan11', 'ROLE_MANAGER'),
       ('susan11', 'ROLE_ADMIN');
