CREATE TABLE IF NOT EXISTS invest.share (
    figi TEXT NOT NULL,
    ticker TEXT UNIQUE NOT NULL,
    class_code TEXT NOT NULL,
    isin TEXT NOT NULL,
    lot INT NOT NULL,
    currency TEXT NOT NULL,
    klong NUMERIC NOT NULL,
    kshort NUMERIC NOT NULL,
    dlong NUMERIC NOT NULL,
    dshort NUMERIC NOT NULL,
    dlong_min NUMERIC NOT NULL,
    dshort_min NUMERIC NOT NULL,
    short_enabled_flag BOOLEAN NOT NULL,
    name TEXT NOT NULL,
    exchange TEXT NOT NULL,
    ipo_date TIMESTAMP NOT NULL,
    issue_size BIGINT NOT NULL,
    country_of_risk TEXT NOT NULL,
    country_of_risk_name TEXT NOT NULL,
    sector TEXT NOT NULL,
    issue_size_plan BIGINT NOT NULL,
    nominal NUMERIC NOT NULL,
    trading_status TEXT NOT NULL,
    otc_flag BOOLEAN NOT NULL,
    buy_available_flag BOOLEAN NOT NULL,
    sell_available_flag BOOLEAN NOT NULL,
    div_yield_flag BOOLEAN NOT NULL,
    share_type TEXT NOT NULL,
    min_price_increment NUMERIC NOT NULL,
    api_trade_available_flag BOOLEAN NOT NULL,
    uid UUID NOT NULL,
    real_exchange TEXT NOT NULL,
    position_uid UUID NOT NULL,
    asset_uid UUID  UNIQUE NOT NULL,
    instrument_exchange TEXT NOT NULL,
    for_iis_flag BOOLEAN NOT NULL,
    for_qual_investor_flag BOOLEAN NOT NULL,
    weekend_flag BOOLEAN NOT NULL,
    blocked_tca_flag BOOLEAN NOT NULL,
    liquidity_flag BOOLEAN NOT NULL,
    first_1min_candle_date TIMESTAMP NOT NULL,
    first_1day_candle_date TIMESTAMP NOT NULL,
    brand TEXT NOT NULL,
    dlong_client NUMERIC NOT NULL,
    dshort_client NUMERIC NOT NULL,
    CONSTRAINT share_pk PRIMARY KEY (uid)
);

COMMENT ON TABLE invest.share IS 'Таблица для хранения информации об акциях.';

COMMENT ON COLUMN invest.share.figi IS 'FIGI-идентификатор инструмента.';
COMMENT ON COLUMN invest.share.ticker IS 'Тикер инструмента.';
COMMENT ON COLUMN invest.share.class_code IS 'Класс-код (секция торгов).';
COMMENT ON COLUMN invest.share.isin IS 'ISIN-идентификатор инструмента.';
COMMENT ON COLUMN invest.share.lot IS 'Лотность инструмента. Возможно совершение операций только на
 количества ценной бумаги, кратные параметру lot.';
COMMENT ON COLUMN invest.share.currency IS 'Валюта расчётов.';
COMMENT ON COLUMN invest.share.klong IS 'Коэффициент ставки риска длинной позиции по клиенту.
 2 – клиент со стандартным уровнем риска (КСУР); 1 – клиент с повышенным уровнем риска (КПУР).';
COMMENT ON COLUMN invest.share.kshort IS 'Коэффициент ставки риска короткой позиции по клиенту.
 2 – клиент со стандартным уровнем риска (КСУР); 1 – клиент с повышенным уровнем риска (КПУР).';
COMMENT ON COLUMN invest.share.dlong IS 'Ставка риска начальной маржи для КСУР лонг.';
COMMENT ON COLUMN invest.share.dshort IS 'Ставка риска начальной маржи для КСУР шорт.';
COMMENT ON COLUMN invest.share.dlong_min IS 'Ставка риска начальной маржи для КПУР лонг.';
COMMENT ON COLUMN invest.share.dshort_min IS 'Ставка риска начальной маржи для КПУР шорт.';
COMMENT ON COLUMN invest.share.short_enabled_flag IS 'Признак доступности для операций в шорт.';
COMMENT ON COLUMN invest.share.name IS 'Название инструмента.';
COMMENT ON COLUMN invest.share.exchange IS 'Торговая площадка (секция биржи).';
COMMENT ON COLUMN invest.share.ipo_date IS 'Дата IPO акции по UTC.';
COMMENT ON COLUMN invest.share.issue_size IS 'Размер выпуска.';
COMMENT ON COLUMN invest.share.country_of_risk IS 'Код страны риска — то есть страны,
 в которой компания ведёт основной бизнес.';
COMMENT ON COLUMN invest.share.country_of_risk_name IS 'Наименование страны риска — то есть страны,
 в которой компания ведёт основной бизнес.';
COMMENT ON COLUMN invest.share.sector IS 'Сектор экономики.';
COMMENT ON COLUMN invest.share.issue_size_plan IS 'Плановый размер выпуска.';
COMMENT ON COLUMN invest.share.nominal IS 'Номинал.';
COMMENT ON COLUMN invest.share.trading_status IS 'Текущий режим торгов инструмента.';
COMMENT ON COLUMN invest.share.otc_flag IS 'Флаг, используемый ранее для определения внебиржевых инструментов.
 На данный момент не используется для торгуемых через API инструментов.';
COMMENT ON COLUMN invest.share.buy_available_flag IS 'Признак доступности для покупки.';
COMMENT ON COLUMN invest.share.sell_available_flag IS 'Признак доступности для продажи.';
COMMENT ON COLUMN invest.share.div_yield_flag IS 'Признак наличия дивидендной доходности.';
COMMENT ON COLUMN invest.share.share_type IS 'Тип акции. Возможные значения — ShareType.';
COMMENT ON COLUMN invest.share.min_price_increment IS 'Шаг цены.';
COMMENT ON COLUMN invest.share.api_trade_available_flag IS 'Возможность торговать инструментом через API.';
COMMENT ON COLUMN invest.share.uid IS 'Уникальный идентификатор инструмента.';
COMMENT ON COLUMN invest.share.real_exchange IS 'Реальная площадка исполнения расчётов (биржа).';
COMMENT ON COLUMN invest.share.position_uid IS 'Уникальный идентификатор позиции инструмента.';
COMMENT ON COLUMN invest.share.asset_uid IS 'Уникальный идентификатор актива.';
COMMENT ON COLUMN invest.share.instrument_exchange IS 'Тип площадки торговли.';
COMMENT ON COLUMN invest.share.for_iis_flag IS 'Признак доступности для ИИС.';
COMMENT ON COLUMN invest.share.for_qual_investor_flag IS 'Флаг, отображающий доступность торговли инструментом
 только для квалифицированных инвесторов.';
COMMENT ON COLUMN invest.share.weekend_flag IS 'Флаг, отображающий доступность торговли инструментом по выходным.';
COMMENT ON COLUMN invest.share.blocked_tca_flag IS 'Флаг заблокированного ТКС.';
COMMENT ON COLUMN invest.share.liquidity_flag IS 'Флаг достаточной ликвидности.';
COMMENT ON COLUMN invest.share.first_1min_candle_date IS 'Дата первой минутной свечи.';
COMMENT ON COLUMN invest.share.first_1day_candle_date IS 'Дата первой дневной свечи.';
COMMENT ON COLUMN invest.share.brand IS 'Информация о бренде.';
COMMENT ON COLUMN invest.share.dlong_client IS 'Ставка риска в лонг, с учетом текущего уровня риска портфеля клиента.';
COMMENT ON COLUMN invest.share.dshort_client IS 'Ставка риска в шорт, с учетом текущего уровня
 риска портфеля клиента.';
