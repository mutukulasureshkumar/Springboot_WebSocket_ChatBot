CREATE DATABASE chatbot;

USE chatbot;

CREATE TABLE nodes (
    id int NOT NULL AUTO_INCREMENT,
    node varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE relations (
    id int NOT NULL AUTO_INCREMENT,
    parent_node_id int NOT NULL,
    child_node_id int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (parent_node_id) REFERENCES nodes(id),
	FOREIGN KEY (child_node_id) REFERENCES nodes(id),
	UNIQUE KEY `uk_relations` (parent_node_id, child_node_id)
);

CREATE INDEX idx_nodes ON nodes (id);

CREATE INDEX idx_relations ON relations (id, parent_node_id, child_node_id);

INSERT INTO nodes (node) VALUES ('Apple' ), ('Phone'), ('Laptop'), ('Iphone 6' ), ('Iphone 7'), ('Mac Book'), ('Mac Pro'), ('Mac Air'), ('6S'), ('6S Plus'), ('$600'), ('$700'), ('$800'), ('$300'), ('$400');

INSERT INTO relations (parent_node_id, child_node_id) VALUES (1,2), (1,3), (2,4), (2,5), (3,6), (3,7), (3,8), (4,9), (4,10), (6,11), (7,12), (8,13), (9,14), (10,15);

CREATE TABLE vocabulary (
    id int NOT NULL AUTO_INCREMENT,
    message TEXT NOT NULL,
    PRIMARY KEY (id)
);

UPDATE `chatbot`.`vocabulary` SET `message` = 'Hello <<name>>!! This is ECLBot, I will help you to know the Apple products price. Which Apple product price you want to get to know? Is that <<relations>>?' WHERE (`id` = '1');
UPDATE `chatbot`.`vocabulary` SET `message` = 'Thank you so much <<name>>, See you again!!' WHERE (`id` = '6');


INSERT INTO vocabulary (message) VALUES ("Hello <<name>>!!, How are you? This is ECLBot, What would you like to know about Apple products? \nIs that <<relations>>?"),
("That's interesting <<name>>!!, What would you like to know about <<node>>, is that <<relations>>?"), ("Great decission <<name>>, by the way which model <<node>> whould you like to enquire about? Is that <<relations>>"),
("The price of <<node>> is : <<relations>>"), ("Thanks you <<name>> for contacting ECLBot. Its a wonderful chat!! would you mind giving me a feedback?"), ("Thank you so much <<name>>, See you again!! Bye");

ALTER TABLE relations ADD vocabulary_id int;

ALTER TABLE relations ADD FOREIGN KEY (vocabulary_id) REFERENCES vocabulary(id);

UPDATE relations SET vocabulary_id=1 WHERE parent_node_id=1;

UPDATE relations SET vocabulary_id=2 WHERE parent_node_id IN (2,3);

UPDATE relations SET vocabulary_id=3 WHERE parent_node_id IN (4);

UPDATE relations SET vocabulary_id=4 WHERE parent_node_id IN (6,7,8,9,10);

UPDATE vocabulary SET message="Hello <<name>>!!, How are you? This is ECLBot, I will help you to know the Apple products price. Which Apple product price you want to get to know? Is that <<relations>>?" WHERE id=1;

UPDATE vocabulary SET message="That's interesting <<name>>!! which series <<node>> are you looking for? is that <<relations>>?" WHERE id=2;

UPDATE vocabulary SET message="Thanks you <<name>> for contacting ECLBot. Its a wonderful chat!! would you mind giving me a feedback?" WHERE id=5;
UPDATE vocabulary SET message="Thank you so much <<name>>, See you again!! Bye." WHERE id=6;


INSERT INTO nodes (node) VALUES ('7S' ), ('7S Plus');

INSERT INTO relations (parent_node_id, child_node_id) VALUES (5,16), (5,17);

UPDATE relations SET vocabulary_id=3 WHERE parent_node_id IN (5);

INSERT INTO relations (parent_node_id, child_node_id, vocabulary_id) VALUES (16,12,4), (17,13,4);

ALTER TABLE relations ADD had_next_relations int;

UPDATE relations SET had_next_relations=1 WHERE child_node_id IN (2,3,4,5,6,7,8,9,10,16,17);

UPDATE relations SET had_next_relations=0 WHERE child_node_id IN (11,12,13,14,15);
