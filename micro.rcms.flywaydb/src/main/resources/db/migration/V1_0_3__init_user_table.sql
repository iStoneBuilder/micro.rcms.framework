-- 用户主表
CREATE TABLE IF NOT EXISTS `tpl_user_t` (
  `tenant_id` VARCHAR(100) NOT NULL COMMENT '租户ID',
  `enterprise_id` VARCHAR(100) NOT NULL COMMENT '企业（商户）ID',
  `USER_ID` VARCHAR(100) NOT NULL,
  `USER_ACCOUNT` VARCHAR(100) NOT NULL COMMENT '用户账号',
  `USER_NAME` VARCHAR(100) NOT NULL COMMENT '用户名称',
  `PASSWORD` VARCHAR(100) NOT NULL COMMENT '用户密码',
  `CREATED_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) NOT NULL DEFAULT 'UNKNOWN',
  `UPDATED_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) NOT NULL DEFAULT 'UNKNOWN',
  PRIMARY KEY (`USER_ID`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;