DROP PROCEDURE IF EXISTS insertTest;

DELIMITER //

CREATE PROCEDURE `insertTest` (IN subjectId int, IN caption varchar(45), OUT testId int)
BEGIN
	INSERT INTO testingsystem.tests (subjectId, caption) 
	VALUES (subjectId, caption);

	SET testId = last_insert_id();
END //

DELIMITER ;
