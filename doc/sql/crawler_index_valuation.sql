BEGIN;

# 指数估值数据表
DROP TABLE IF EXISTS `crawler_index_valuation`;
CREATE TABLE `crawler_index_valuation` (
  `id`            INT          NOT NULL AUTO_INCREMENT
  COMMENT '数据库自增ID',
  `created_time`  DATETIME     NOT NULL DEFAULT '1970-01-01 00:00:00'
  COMMENT '数据创建时间',
  `modified_time` DATETIME     NOT NULL DEFAULT '1970-01-01 00:00:00'
  COMMENT '数据修改时间',

  `request_day`   INT          NOT NULL
  COMMENT '请求日期，即哪一天的估值',

  `index_code`    VARCHAR(20)  NOT NULL
  COMMENT '指数编码',
  `index_name`    VARCHAR(40)  NOT NULL
  COMMENT '指数编码',

  `pe`            VARCHAR(10)  NULL
  COMMENT 'PE值',
  `pb`            VARCHAR(10)  NULL
  COMMENT 'PB值',
  `pe_percentile` VARCHAR(10)  NULL
  COMMENT 'PE分位点',
  `pb_percentile` VARCHAR(10)  NULL
  COMMENT 'PB分位点',
  `roe`           VARCHAR(10)  NULL
  COMMENT '净利润/净资产',
  `yeild`         VARCHAR(10)  NULL
  COMMENT '股息率',
  `eva_type`      VARCHAR(40)  NULL
  COMMENT '估值当前水平',
  `eva_type_int`  VARCHAR(40)  NULL
  COMMENT '估值当前水平int值',
  `url`           VARCHAR(120) NULL
  COMMENT '对应的基金的地址',
  `bond_yeild`    VARCHAR(10)  NULL
  COMMENT '',
  `begin_at`      VARCHAR(20)  NULL
  COMMENT '数据开始时间',
  `begin_at_str`  VARCHAR(20)  NULL
  COMMENT '数据开始时间文本值',

  `data_source`   VARCHAR(20)  NOT NULL
  COMMENT '数据来源：蛋卷基金估值',

  PRIMARY KEY (`id`),
  UNIQUE KEY `uk`(`request_day`, `index_code`),
  INDEX `idx_request_day`(`request_day`),
  INDEX `idx_index_code`(`index_code`),
  INDEX `idx_eva_type`(`eva_type`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT '指数估值数据表';

COMMIT;