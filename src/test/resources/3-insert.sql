INSERT INTO User (username, password, email, forename, permissions)
VALUES
	("eeu900", "admin_password", "eeu900@bangor.ac.uk", "Natasha", "admin"),
    ("johnsmith", "password", "john.smith@gmail.com", "John", "user"),
    ("janesmith", "anotherpassword", "jane.smith@hotmail.co.uk", "Jane", "user"),
    ("bobbys", "otherpassword", "bSmith@gmail.com", "Robert", "user");
    

INSERT INTO English (word, type)
VALUES
	("time", "noun"),
    ("government", "noun"),
    ("year", "noun"),
    ("fact", "noun"),
    ("week", "noun"),
    ("thing", "noun"),
    ("woman", "noun"),
    ("world", "noun"),
    ("life", "noun"),
    ("hand", "noun");

INSERT INTO Welsh (word, gender, type)
VALUES
	("amser", "M", "noun"),
    ("llwodraeth", "M", "noun"),
    ("blwyddyn", "F", "noun"),
    ("ffaith", "F", "noun"),
    ("wythnos", "F", "noun"),
    ("peth", "M", "noun"),
    ("dynes", "F", "noun"),
    ("byd", "M", "noun"),
    ("bywyd", "M", "noun"),
    ("llaw", "F", "noun");
    
INSERT INTO Results (user_id, test_id, completionDate, score)
VALUES
	("1", "some test", "2019-04-21", 15),
    ("1", "some other test", "2019-04-22", 20),
    ("3", "some test", "2019-03-01", 17),
    ("4", "some other test", "2019-04-17", 15);