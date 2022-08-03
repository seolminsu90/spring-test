
CREATE TABLE IF NOT EXISTS schedule (
    id INT NOT NULL auto_increment ,
    reg_date datetime not null,
    title VARCHAR(256) not null,
    start_time time not null,
    end_time time not null,
    writer VARCHAR(16) not null,
    alarm INT,
    comment VARCHAR(2048),
    PRIMARY KEY(id)
);

INSERT INTO schedule(reg_date, title, start_time, end_time, writer, alarm, comment)
VALUES 
('2021-10-05', '프로젝트 회의', '20:00:00', '22:00:00', 'ch4njun', 1, 'SAMPLE1'),
('2021-10-06', '프로젝트 회의', '20:00:00', '22:00:00', 'ch4njun', 2, 'SAMPLE1'),
('2021-10-07', '프로젝트 회의', '20:00:00', '22:00:00', 'ch4njun', 3, 'SAMPLE1'),
('2021-10-08', '프로젝트 회의', '20:00:00', '22:00:00', 'ch4njun', 4, 'SAMPLE1'),
('2021-10-09', '프로젝트 회의', '20:00:00', '22:00:00', 'ch4njun', 5, 'SAMPLE1'),
('2021-10-10', '프로젝트 회의', '20:00:00', '22:00:00', 'ch4njun', 1, 'SAMPLE1')
;