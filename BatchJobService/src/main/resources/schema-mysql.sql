--CREATE DATABASE IF NOT EXISTS `jobprogress`;
USE `jobscheduler`;

CREATE TABLE IF NOT EXISTS `JOB_PROGRESS_DATA` (
  `job_id` bigint(20) NOT NULL,
  `total_line_count` int default NULL,
  `writer_line_count` int default NULL,
  `status` SMALLINT default NULL,
  FOREIGN KEY(job_id) REFERENCES schedule_job_details(id),
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `JOB_PROGRESS_DATA` (`job_id`, `total_line_count`, `writer_line_count`,`status`) VALUES
	(1, 100000, 100000, 1),
	(2, 100000, 10000, 2),
	(3, 100000, 80000, 0); 