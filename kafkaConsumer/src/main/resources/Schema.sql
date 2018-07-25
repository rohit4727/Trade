DROP TABLE IF EXISTS trade_live_feed;

CREATE TABLE trade_live_feed (
  trade_id int(11) NOT NULL,
  broker varchar(255) DEFAULT NULL,
  currency varchar(255) DEFAULT NULL,
  instrument_type varchar(255) DEFAULT NULL,
  security varchar(255) DEFAULT NULL,
  trade_date date DEFAULT NULL,
  trade_price double NOT NULL,
  trade_time time DEFAULT NULL,
  PRIMARY KEY (trade_id)
);