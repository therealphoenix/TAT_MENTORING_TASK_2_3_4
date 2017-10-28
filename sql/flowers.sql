SET FOREIGN_KEY_CHECKS = 0; -- disable a foreign keys check
SET AUTOCOMMIT = 0; -- disable autocommit
START TRANSACTION;

TRUNCATE `flower`;

INSERT INTO `flowershop`.`flower` (`name`, `price`, `country`, `isAvailable`)
VALUES ('Rose', '4', 'China', '1');

INSERT INTO `flowershop`.`flower` (`name`, `price`, `country`, `isAvailable`)
VALUES ('Tulip', '5', 'Netherlands', '1');

INSERT INTO `flowershop`.`flower` (`name`, `price`, `country`, `isAvailable`)
VALUES ('Camomille', '4', 'Russia', '1');

INSERT INTO `flowershop`.`flower` (`name`, `price`, `country`, `isAvailable`)
VALUES ('Rose', '2', 'Belarus', '1');

INSERT INTO `flowershop`.`flower` (`name`, `price`, `country`, `isAvailable`)
VALUES ('Allium', '7', 'Belarus', '1');

INSERT INTO `flowershop`.`flower` (`name`, `price`, `country`, `isAvailable`)
VALUES ('Anemone', '5', 'USA', '1');

INSERT INTO `flowershop`.`flower` (`name`, `price`, `country`, `isAvailable`)
VALUES ('Cornflower', '5', 'China', '1');

INSERT INTO `flowershop`.`flower` (`name`, `price`, `country`, `isAvailable`)
VALUES ('Echinops', '4', 'Canada', '1');

INSERT INTO `flowershop`.`flower` (`name`, `price`, `country`, `isAvailable`)
VALUES ('Vanda', '6', 'Mexico', '1');

SET FOREIGN_KEY_CHECKS = 1; -- enable a foreign keys check
COMMIT;  -- make a commit
SET AUTOCOMMIT = 1 ;