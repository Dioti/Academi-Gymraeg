INSERT INTO User (username, password, email, forename, permissions)
VALUES
	("eeu900", "admin_password", "eeu900@bangor.ac.uk", "Natasha", "admin"),
    ("johnsmith", "password", "john.smith@gmail.com", "John", "instructor"),
    ("janesmith", "another_password", "jane.smith@hotmail.co.uk", "Jane", "student");
    

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