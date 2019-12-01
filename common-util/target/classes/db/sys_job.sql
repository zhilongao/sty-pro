CREATE database job;

CREATE TABLE `sys_job` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `job_name` varchar(512) NOT NULL COMMENT '任务名称',
  `job_group` varchar(512) NOT NULL COMMENT '任务组名',
  `job_cron` varchar(512) NOT NULL COMMENT '时间表达式',
  `job_class_path` varchar(1024) NOT NULL COMMENT '类路径,全类型',
  `job_data_map` varchar(1024) DEFAULT NULL COMMENT '传递map参数',
  `job_status` int(2) NOT NULL COMMENT '状态:1启用 0停用',
  `job_describe` varchar(1024) DEFAULT NULL COMMENT '任务功能描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- 插入3条数据，3个任务
-- 注意第三条，是一个发送邮件的任务，需要改成你自己的QQ和授权码。不知道什么是授权码的自己百度。
INSERT INTO `sys_job` (`id`, `job_name`, `job_group`, `job_cron`, `job_class_path`, `job_data_map`, `job_status`, `job_describe`) VALUES (22, 'test', 'test', '*/20 * * * * ?', 'com.common.task.SendMessageTask', NULL, 1, 'a job a');