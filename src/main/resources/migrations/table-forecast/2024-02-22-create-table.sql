CREATE TABLE IF NOT EXISTS invest.forecast (
    uid UUID NOT NULL,
    ticker text NOT NULL,
    company text NOT NULL,
    recomendation text NOT NULL,
    recomendation_date timestamp NOT NULL,
    currency text NOT NULL,
    current_price numeric NOT NULL,
    target_price numeric NOT NULL,
    price_change numeric NOT NULL,
    price_change_rel numeric NOT NULL,
    show_name text NOT NULL,
    CONSTRAINT forecast_pk PRIMARY KEY (uid),
    CONSTRAINT forecast_fk FOREIGN KEY (ticker) REFERENCES invest.share (ticker)
);

COMMENT ON TABLE invest.forecast IS 'Таблица для хранения прогноза по акции';
COMMENT ON COLUMN invest.forecast.uid IS 'Уникальный идентификатор инструмента';
COMMENT ON COLUMN invest.forecast.ticker IS 'Тикер инструмента';
COMMENT ON COLUMN invest.forecast.company IS 'Название компании, давшей прогноз';
COMMENT ON COLUMN invest.forecast.recomendation IS 'Прогноз. Энам с 4 значениями';
COMMENT ON COLUMN invest.forecast.recomendation_date IS 'Дата прогноза';
COMMENT ON COLUMN invest.forecast.currency IS 'Валюта';
COMMENT ON COLUMN invest.forecast.current_price IS 'Текущая цена';
COMMENT ON COLUMN invest.forecast.target_price IS 'Прогнозируемая цена';
COMMENT ON COLUMN invest.forecast.price_change IS 'Изменение цены';
COMMENT ON COLUMN invest.forecast.price_change_rel IS 'Относительное изменение цены';
COMMENT ON COLUMN invest.forecast.show_name IS 'Название инструмента';
