BEGIN;

# 指数表
DROP TABLE IF EXISTS `crawler_index`;
CREATE TABLE `crawler_index` (
  `id`            INT         NOT NULL AUTO_INCREMENT
  COMMENT '数据库自增ID',
  `created_time`  DATETIME    NOT NULL DEFAULT '1970-01-01 00:00:00'
  COMMENT '数据创建时间',
  `modified_time` DATETIME    NOT NULL DEFAULT '1970-01-01 00:00:00'
  COMMENT '数据修改时间',

  `index_code`    VARCHAR(20) NOT NULL
  COMMENT '指数编码',
  `index_name`    VARCHAR(40) NOT NULL
  COMMENT '指数编码',

  `is_followed`   TINYINT     NOT NULL DEFAULT 0
  COMMENT '是否在跟踪买：0不在跟踪，1在跟踪',

  `data_source`   VARCHAR(20) NOT NULL
  COMMENT '数据来源：蛋卷基金',

  PRIMARY KEY (`id`),
  UNIQUE KEY `uk`(`index_code`),
  INDEX `idx_request_day`(`index_code`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT '指数表';

# 原始数据sql
INSERT IGNORE INTO crawler_index (created_time, modified_time, index_code, index_name, data_source) VALUES (now(), now(), "CSI930782", "500低波", "蛋卷基金");
INSERT IGNORE INTO crawler_index (created_time, modified_time, index_code, index_name, data_source) VALUES (now(), now(), "CSIH11136", "中国互联", "蛋卷基金");
INSERT IGNORE INTO crawler_index (created_time, modified_time, index_code, index_name, data_source) VALUES (now(), now(), "CSIH30269", "红利低波", "蛋卷基金");
INSERT IGNORE INTO crawler_index (created_time, modified_time, index_code, index_name, data_source) VALUES (now(), now(), "CSPSADRP", "标普红利", "蛋卷基金");
INSERT IGNORE INTO crawler_index (created_time, modified_time, index_code, index_name, data_source) VALUES (now(), now(), "HKHSCEI", "国企指数", "蛋卷基金");
INSERT IGNORE INTO crawler_index (created_time, modified_time, index_code, index_name, data_source) VALUES (now(), now(), "HKHSI", "恒生指数", "蛋卷基金");
INSERT IGNORE INTO crawler_index (created_time, modified_time, index_code, index_name, data_source) VALUES (now(), now(), "HSFML25", "香港大盘", "蛋卷基金");
INSERT IGNORE INTO crawler_index (created_time, modified_time, index_code, index_name, data_source) VALUES (now(), now(), "NDX", "纳指100", "蛋卷基金");
INSERT IGNORE INTO crawler_index (created_time, modified_time, index_code, index_name, data_source) VALUES (now(), now(), "SH000016", "上证50", "蛋卷基金");
INSERT IGNORE INTO crawler_index (created_time, modified_time, index_code, index_name, data_source) VALUES (now(), now(), "SH000170", "50AH优选", "蛋卷基金");
INSERT IGNORE INTO crawler_index (created_time, modified_time, index_code, index_name, data_source) VALUES (now(), now(), "SH000300", "沪深300", "蛋卷基金");
INSERT IGNORE INTO crawler_index (created_time, modified_time, index_code, index_name, data_source) VALUES (now(), now(), "SH000807", "食品饮料", "蛋卷基金");
INSERT IGNORE INTO crawler_index (created_time, modified_time, index_code, index_name, data_source) VALUES (now(), now(), "SH000827", "中证环保", "蛋卷基金");
INSERT IGNORE INTO crawler_index (created_time, modified_time, index_code, index_name, data_source) VALUES (now(), now(), "SH000852", "中证1000", "蛋卷基金");
INSERT IGNORE INTO crawler_index (created_time, modified_time, index_code, index_name, data_source) VALUES (now(), now(), "SH000903", "中证100", "蛋卷基金");
INSERT IGNORE INTO crawler_index (created_time, modified_time, index_code, index_name, data_source) VALUES (now(), now(), "SH000905", "中证500", "蛋卷基金");
INSERT IGNORE INTO crawler_index (created_time, modified_time, index_code, index_name, data_source) VALUES (now(), now(), "SH000922", "中证红利", "蛋卷基金");
INSERT IGNORE INTO crawler_index (created_time, modified_time, index_code, index_name, data_source) VALUES (now(), now(), "SH000925", "基本面50", "蛋卷基金");
INSERT IGNORE INTO crawler_index (created_time, modified_time, index_code, index_name, data_source) VALUES (now(), now(), "SH000933", "中证医药", "蛋卷基金");
INSERT IGNORE INTO crawler_index (created_time, modified_time, index_code, index_name, data_source) VALUES (now(), now(), "SH000986", "全指能源", "蛋卷基金");
INSERT IGNORE INTO crawler_index (created_time, modified_time, index_code, index_name, data_source) VALUES (now(), now(), "SH000987", "全指材料", "蛋卷基金");
INSERT IGNORE INTO crawler_index (created_time, modified_time, index_code, index_name, data_source) VALUES (now(), now(), "SH000989", "全指可选", "蛋卷基金");
INSERT IGNORE INTO crawler_index (created_time, modified_time, index_code, index_name, data_source) VALUES (now(), now(), "SH000990", "全指消费", "蛋卷基金");
INSERT IGNORE INTO crawler_index (created_time, modified_time, index_code, index_name, data_source) VALUES (now(), now(), "SH000991", "全指医药", "蛋卷基金");
INSERT IGNORE INTO crawler_index (created_time, modified_time, index_code, index_name, data_source) VALUES (now(), now(), "SH000993", "全指信息", "蛋卷基金");
INSERT IGNORE INTO crawler_index (created_time, modified_time, index_code, index_name, data_source) VALUES (now(), now(), "SP500", "标普500", "蛋卷基金");
INSERT IGNORE INTO crawler_index (created_time, modified_time, index_code, index_name, data_source) VALUES (now(), now(), "SPHCMSHP", "香港中小", "蛋卷基金");
INSERT IGNORE INTO crawler_index (created_time, modified_time, index_code, index_name, data_source) VALUES (now(), now(), "SZ399006", "创业板", "蛋卷基金");
INSERT IGNORE INTO crawler_index (created_time, modified_time, index_code, index_name, data_source) VALUES (now(), now(), "SZ399324", "深证红利", "蛋卷基金");
INSERT IGNORE INTO crawler_index (created_time, modified_time, index_code, index_name, data_source) VALUES (now(), now(), "SZ399330", "深证100", "蛋卷基金");
INSERT IGNORE INTO crawler_index (created_time, modified_time, index_code, index_name, data_source) VALUES (now(), now(), "SZ399550", "央视50", "蛋卷基金");
INSERT IGNORE INTO crawler_index (created_time, modified_time, index_code, index_name, data_source) VALUES (now(), now(), "SZ399812", "养老产业", "蛋卷基金");
INSERT IGNORE INTO crawler_index (created_time, modified_time, index_code, index_name, data_source) VALUES (now(), now(), "SZ399967", "中证军工", "蛋卷基金");
INSERT IGNORE INTO crawler_index (created_time, modified_time, index_code, index_name, data_source) VALUES (now(), now(), "SZ399975", "证券公司", "蛋卷基金");
INSERT IGNORE INTO crawler_index (created_time, modified_time, index_code, index_name, data_source) VALUES (now(), now(), "SZ399986", "中证银行", "蛋卷基金");
INSERT IGNORE INTO crawler_index (created_time, modified_time, index_code, index_name, data_source) VALUES (now(), now(), "SZ399997", "中证白酒", "蛋卷基金");
INSERT IGNORE INTO crawler_index (created_time, modified_time, index_code, index_name, data_source) VALUES (now(), now(), "SZ399998", "中证煤炭", "蛋卷基金");

# 标记为跟踪
UPDATE crawler_index
SET is_followed = 1
WHERE id IN (11, 12, 13, 16, 18, 19, 23, 24, 28, 33, 34);

COMMIT;