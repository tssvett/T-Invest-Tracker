CREATE TABLE IF NOT EXISTS invest.fundamental_forecast (
    asset_uid UUID NOT NULL,
    currency TEXT NOT NULL,
    market_capitalization DOUBLE PRECISION NOT NULL,
    high_price_last_52_weeks DOUBLE PRECISION NOT NULL,
    low_price_last_52_weeks DOUBLE PRECISION NOT NULL,
    average_daily_volume_last_10_days DOUBLE PRECISION NOT NULL,
    average_daily_volume_last_4_weeks DOUBLE PRECISION NOT NULL,
    beta DOUBLE PRECISION NOT NULL,
    free_float DOUBLE PRECISION NOT NULL,
    forward_annual_dividend_yield DOUBLE PRECISION NOT NULL,
    shares_outstanding DOUBLE PRECISION NOT NULL,
    revenue_ttm DOUBLE PRECISION NOT NULL,
    ebitda_ttm DOUBLE PRECISION NOT NULL,
    net_income_ttm DOUBLE PRECISION NOT NULL,
    eps_ttm DOUBLE PRECISION NOT NULL,
    diluted_eps_ttm DOUBLE PRECISION NOT NULL,
    free_cash_flow_ttm DOUBLE PRECISION NOT NULL,
    five_year_annual_revenue_growth_rate DOUBLE PRECISION NOT NULL,
    three_year_annual_revenue_growth_rate DOUBLE PRECISION NOT NULL,
    pe_ratio_ttm DOUBLE PRECISION NOT NULL,
    price_to_sales_ttm DOUBLE PRECISION NOT NULL,
    price_to_book_ttm DOUBLE PRECISION NOT NULL,
    price_to_free_cash_flow_ttm DOUBLE PRECISION NOT NULL,
    total_enterprise_value_mrq DOUBLE PRECISION NOT NULL,
    ev_to_ebitda_mrq DOUBLE PRECISION NOT NULL,
    net_margin_mrq DOUBLE PRECISION NOT NULL,
    net_interest_margin_mrq DOUBLE PRECISION NOT NULL,
    roe DOUBLE PRECISION NOT NULL,
    roa DOUBLE PRECISION NOT NULL,
    roic DOUBLE PRECISION NOT NULL,
    total_debt_mrq DOUBLE PRECISION NOT NULL,
    total_debt_to_equity_mrq DOUBLE PRECISION NOT NULL,
    total_debt_to_ebitda_mrq DOUBLE PRECISION NOT NULL,
    free_cash_flow_to_price DOUBLE PRECISION NOT NULL,
    net_debt_to_ebitda DOUBLE PRECISION NOT NULL,
    current_ratio_mrq DOUBLE PRECISION NOT NULL,
    fixed_charge_coverage_ratio_fy DOUBLE PRECISION NOT NULL,
    dividend_yield_daily_ttm DOUBLE PRECISION NOT NULL,
    dividend_rate_ttm DOUBLE PRECISION NOT NULL,
    dividends_per_share DOUBLE PRECISION NOT NULL,
    five_years_average_dividend_yield DOUBLE PRECISION NOT NULL,
    five_year_annual_dividend_growth_rate DOUBLE PRECISION NOT NULL,
    dividend_payout_ratio_fy DOUBLE PRECISION NOT NULL,
    buy_back_ttm DOUBLE PRECISION NOT NULL,
    one_year_annual_revenue_growth_rate DOUBLE PRECISION NOT NULL,
    domicile_indicator_code TEXT NOT NULL,
    adr_to_common_share_ratio DOUBLE PRECISION NOT NULL,
    number_of_employees DOUBLE PRECISION NOT NULL,
    ex_dividend_date TIMESTAMP NOT NULL,
    fiscal_period_start_date TIMESTAMP NOT NULL,
    fiscal_period_end_date TIMESTAMP NOT NULL,
    revenue_change_five_years DOUBLE PRECISION NOT NULL,
    eps_change_five_years DOUBLE PRECISION NOT NULL,
    ebitda_change_five_years DOUBLE PRECISION NOT NULL,
    total_debt_change_five_years DOUBLE PRECISION NOT NULL,
    ev_to_sales DOUBLE PRECISION NOT NULL,
    CONSTRAINT fundamental_forecast_pk PRIMARY KEY (asset_uid),
    CONSTRAINT fundamental_forecast_fk FOREIGN KEY (asset_uid) REFERENCES invest.share (asset_uid)
);

COMMENT ON TABLE invest.fundamental_forecast IS 'Таблица для хранения фундаментальных показателей по активу.';

COMMENT ON COLUMN invest.fundamental_forecast.asset_uid IS 'Идентификатор актива.';
COMMENT ON COLUMN invest.fundamental_forecast.currency IS 'Валюта.';
COMMENT ON COLUMN invest.fundamental_forecast.market_capitalization IS 'Рыночная капитализация.';
COMMENT ON COLUMN invest.fundamental_forecast.high_price_last_52_weeks IS 'Максимум за год.';
COMMENT ON COLUMN invest.fundamental_forecast.low_price_last_52_weeks IS 'Минимум за год.';
COMMENT ON COLUMN invest.fundamental_forecast.average_daily_volume_last_10_days IS 'Средний объём торгов за 10 дней.';
COMMENT ON COLUMN invest.fundamental_forecast.average_daily_volume_last_4_weeks IS 'Средний объём торгов за месяц.';
COMMENT ON COLUMN invest.fundamental_forecast.beta IS 'Бета.';
COMMENT ON COLUMN invest.fundamental_forecast.free_float IS 'Доля акций в свободном обращении.';
COMMENT ON COLUMN invest.fundamental_forecast.forward_annual_dividend_yield IS 'Процент форвардной дивидендной
 доходности по отношению к цене акций.';
COMMENT ON COLUMN invest.fundamental_forecast.shares_outstanding IS 'Количество акций в обращении.';
COMMENT ON COLUMN invest.fundamental_forecast.revenue_ttm IS 'Выручка.';
COMMENT ON COLUMN invest.fundamental_forecast.ebitda_ttm IS 'EBITDA — прибыль до вычета процентов, налогов,
 износа и амортизации.';
COMMENT ON COLUMN invest.fundamental_forecast.net_income_ttm IS 'Чистая прибыль.';
COMMENT ON COLUMN invest.fundamental_forecast.eps_ttm IS 'EPS — величина чистой прибыли компании,
 которая приходится на каждую обыкновенную акцию.';
COMMENT ON COLUMN invest.fundamental_forecast.diluted_eps_ttm IS 'EPS компании с допущением, что все конвертируемые
 ценные бумаги компании были сконвертированы в обыкновенные акции.';
COMMENT ON COLUMN invest.fundamental_forecast.free_cash_flow_ttm IS 'Свободный денежный поток.';
COMMENT ON COLUMN invest.fundamental_forecast.five_year_annual_revenue_growth_rate IS 'Среднегодовой рocт выручки
 за 5 лет.';
COMMENT ON COLUMN invest.fundamental_forecast.three_year_annual_revenue_growth_rate IS 'Среднегодовой рocт выручки
 за 3 года.';
COMMENT ON COLUMN invest.fundamental_forecast.pe_ratio_ttm IS 'Соотношение рыночной капитализации компании к её
 чистой прибыли.';
COMMENT ON COLUMN invest.fundamental_forecast.price_to_sales_ttm IS 'Соотношение рыночной капитализации компании
 к её выручке.';
COMMENT ON COLUMN invest.fundamental_forecast.price_to_book_ttm IS 'Соотношение рыночной капитализации компании
 к её балансовой стоимости.';
COMMENT ON COLUMN invest.fundamental_forecast.price_to_free_cash_flow_ttm IS 'Соотношение рыночной капитализации
 компании к её свободному денежному потоку.';
COMMENT ON COLUMN invest.fundamental_forecast.total_enterprise_value_mrq IS 'Рыночная стоимость компании.';
COMMENT ON COLUMN invest.fundamental_forecast.ev_to_ebitda_mrq IS 'Соотношение EV и EBITDA.';
COMMENT ON COLUMN invest.fundamental_forecast.net_margin_mrq IS 'Маржа чистой прибыли.';
COMMENT ON COLUMN invest.fundamental_forecast.net_interest_margin_mrq IS 'Рентабельность чистой прибыли.';
COMMENT ON COLUMN invest.fundamental_forecast.roe IS 'Рентабельность собственного капитала.';
COMMENT ON COLUMN invest.fundamental_forecast.roa IS 'Рентабельность активов.';
COMMENT ON COLUMN invest.fundamental_forecast.roic IS 'Рентабельность активов.';
COMMENT ON COLUMN invest.fundamental_forecast.total_debt_mrq IS 'Сумма краткосрочных и долгосрочных обязательств
 компании.';
COMMENT ON COLUMN invest.fundamental_forecast.total_debt_to_equity_mrq IS 'Соотношение долга к собственному капиталу.';
COMMENT ON COLUMN invest.fundamental_forecast.total_debt_to_ebitda_mrq IS 'Total Debt/EBITDA.';
COMMENT ON COLUMN invest.fundamental_forecast.free_cash_flow_to_price IS 'Отношение свободного кэша к стоимости.';
COMMENT ON COLUMN invest.fundamental_forecast.net_debt_to_ebitda IS 'Отношение чистого долга к EBITDA.';
COMMENT ON COLUMN invest.fundamental_forecast.current_ratio_mrq IS 'Коэффициент текущей ликвидности.';
COMMENT ON COLUMN invest.fundamental_forecast.fixed_charge_coverage_ratio_fy IS 'Коэффициент покрытия фиксированных
 платежей — FCCR.';
COMMENT ON COLUMN invest.fundamental_forecast.dividend_yield_daily_ttm IS 'Дивидендная доходность за 12 месяцев.';
COMMENT ON COLUMN invest.fundamental_forecast.dividend_rate_ttm IS 'Выплаченные дивиденды за 12 месяцев.';
COMMENT ON COLUMN invest.fundamental_forecast.dividends_per_share IS 'Значение дивидендов на акцию.';
COMMENT ON COLUMN invest.fundamental_forecast.five_years_average_dividend_yield IS 'Средняя дивидендная доходность
 за 5 лет.';
COMMENT ON COLUMN invest.fundamental_forecast.five_year_annual_dividend_growth_rate IS 'Среднегодовой рост дивидендов
 за 5 лет.';
COMMENT ON COLUMN invest.fundamental_forecast.dividend_payout_ratio_fy IS 'Процент чистой прибыли, уходящий на выплату
 дивидендов.';
COMMENT ON COLUMN invest.fundamental_forecast.buy_back_ttm IS 'Деньги, потраченные на обратный выкуп акций.';
COMMENT ON COLUMN invest.fundamental_forecast.one_year_annual_revenue_growth_rate IS 'Рост выручки за 1 год.';
COMMENT ON COLUMN invest.fundamental_forecast.domicile_indicator_code IS 'Код страны.';
COMMENT ON COLUMN invest.fundamental_forecast.adr_to_common_share_ratio IS 'Соотношение депозитарной расписки
 к акциям.';
COMMENT ON COLUMN invest.fundamental_forecast.number_of_employees IS 'Количество сотрудников.';
COMMENT ON COLUMN invest.fundamental_forecast.ex_dividend_date IS 'Дата экс-дивиденда.';
COMMENT ON COLUMN invest.fundamental_forecast.fiscal_period_start_date IS 'Начало фискального периода.';
COMMENT ON COLUMN invest.fundamental_forecast.fiscal_period_end_date IS 'Окончание фискального периода.';
COMMENT ON COLUMN invest.fundamental_forecast.revenue_change_five_years IS 'Изменение общего дохода за 5 лет.';
COMMENT ON COLUMN invest.fundamental_forecast.eps_change_five_years IS 'Изменение EPS за 5 лет.';
COMMENT ON COLUMN invest.fundamental_forecast.ebitda_change_five_years IS 'Изменение EBIDTA за 5 лет.';
COMMENT ON COLUMN invest.fundamental_forecast.total_debt_change_five_years IS 'Изменение общей задолженности
 за 5 лет.';
COMMENT ON COLUMN invest.fundamental_forecast.ev_to_sales IS 'Отношение EV к выручке.';
