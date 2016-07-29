CREATE TABLE answers
(
    answerId INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    questionId INT(11),
    text TEXT NOT NULL,
    correct TINYINT(1) NOT NULL,
    CONSTRAINT fkQuestion FOREIGN KEY (questionId) REFERENCES questions (questionId) ON DELETE CASCADE
);
CREATE INDEX fkQuestion_idx ON answers (questionId);
CREATE TABLE questions
(
    questionId INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    testId INT(11) NOT NULL,
    task TEXT,
    CONSTRAINT fkTest FOREIGN KEY (testId) REFERENCES tests (testId) ON DELETE CASCADE
);
CREATE INDEX fkTest_idx ON questions (testId);
CREATE TABLE results
(
    resultId INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    userId INT(11) NOT NULL,
    testId INT(11) NOT NULL,
    rate INT(11) NOT NULL,
    CONSTRAINT fk_Result_User FOREIGN KEY (userId) REFERENCES users (userId),
    CONSTRAINT fk_Result_Test FOREIGN KEY (testId) REFERENCES tests (testId)
);
CREATE INDEX fk0_idx ON results (userId);
CREATE INDEX fkTest_idx ON results (testId);
CREATE TABLE subjects
(
    subjectId INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL
);
CREATE TABLE tests
(
    testId INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    subjectId INT(11) NOT NULL,
    caption VARCHAR(45),
    CONSTRAINT fkSubject FOREIGN KEY (subjectId) REFERENCES subjects (subjectId) ON DELETE CASCADE
);
CREATE INDEX fkSubject_idx ON tests (subjectId);
CREATE UNIQUE INDEX id_UNIQUE ON tests (testId);
CREATE TABLE users
(
    userId INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    login VARCHAR(45) NOT NULL,
    password CHAR(32),
    firstName VARCHAR(45),
    lastName VARCHAR(45),
    role VARCHAR(10) DEFAULT 'user' NOT NULL,
    email VARCHAR(45) NOT NULL
);
CREATE UNIQUE INDEX email_UNIQUE ON users (email);
CREATE UNIQUE INDEX ID_UNIQUE ON users (userId);
CREATE UNIQUE INDEX login_UNIQUE ON users (login);