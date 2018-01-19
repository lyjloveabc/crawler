BEGIN;

# 基金表
DROP TABLE IF EXISTS `crawler_fund`;
CREATE TABLE `crawler_fund` (
  `id`            INT         NOT NULL AUTO_INCREMENT
  COMMENT '数据库自增ID',
  `created_time`  DATETIME    NOT NULL DEFAULT '1970-01-01 00:00:00'
  COMMENT '数据创建时间',
  `modified_time` DATETIME    NOT NULL DEFAULT '1970-01-01 00:00:00'
  COMMENT '数据修改时间',

  `code`          VARCHAR(20) NOT NULL
  COMMENT '指数编码',
  `name`          VARCHAR(40) NOT NULL
  COMMENT '指数编码',

  `data_source`   VARCHAR(20) NOT NULL
  COMMENT '数据来源：陆金所基金',

  PRIMARY KEY (`id`),
  UNIQUE KEY `uk`(`code`),
  INDEX `idx_code`(`code`),
  INDEX `idx_name`(`name`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT '基金表';

COMMIT;