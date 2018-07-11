CREATE TABLE `schedule_job_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `batch_file_path` varchar(255) DEFAULT NULL,
  `job_name` varchar(255) DEFAULT NULL UNIQUE,
  `schedule_date` datetime NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;