CREATE TABLE IF NOT EXISTS `kickstarter`.`quote` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `text` VARCHAR(500) NOT NULL,
  `author` VARCHAR(200) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB