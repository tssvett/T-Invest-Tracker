alter table invest.share
ADD COLUMN invedaily_change NUMERIC;

alter table invest.share
ADD COLUMN volume_share NUMERIC;

alter table invest.share
ADD COLUMN market_capitalization NUMERIC;
