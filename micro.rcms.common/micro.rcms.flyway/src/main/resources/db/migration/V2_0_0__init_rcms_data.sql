-- 初始化平台企业数据
INSERT INTO `tpl_fram_enterprise_t` (`parent_id`,`id`, `name`, `tenant_id`, `sort`,`level`, `type`,`status`, `principal`, `phone`, `email`, `remark`, `CREATED_TIME`, `CREATED_BY`, `UPDATED_TIME`, `UPDATED_BY`) VALUES (0,100000000000, 'RCMS平台','100000000000', 1,1,'platform', 'Y','RCMS', '13265044565', '', '', NOW(), 'system',NOW(),'system');
-- 初始化平台账户数据
INSERT INTO `tpl_fram_account_t` (`tenant_id`,`enterprise_id`,`account_code`, `account_name`, `account_type`, `password`, `account_roles`, `status`,`CREATED_TIME`, `CREATED_BY`, `UPDATED_TIME`, `UPDATED_BY`) VALUES ('100000000000','100000000000', 'superAdmin', '超级管理员', 'account', 'e10adc3949', 'platformAdmin', 'Y',NOW(), 'system',NOW(),'system');
-- 初始化平台角色数据
INSERT INTO `tpl_fram_role_t` (`tenant_id`,`enterprise_id`,`parent_id`, `id`, `code`, `name`, `description`, `CREATED_TIME`, `CREATED_BY`, `UPDATED_TIME`, `UPDATED_BY`) VALUES (100000000000,100000000000, 0, 1, 'platformAdmin', '平台管理员', '平台管理员角色', NOW(), 'system',NOW(),'system');
