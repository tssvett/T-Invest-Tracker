package dev.invest.mapper;

import dev.invest.db.jooq.org.jooq.generated.invest.tables.records.FundamentalForecastRecord;
import dev.invest.model.fundamental.FundamentalDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import ru.tinkoff.piapi.contract.v1.GetAssetFundamentalsResponse.StatisticResponse;
import ru.tinkoff.piapi.contract.v1.Quotation;

import java.math.BigDecimal;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface FundamentalMapper {
    @Mapping(source = "assetUid", target = "assetId")
    @Mapping(source = "currency", target = "currency")
    @Mapping(source = "marketCapitalization", target = "marketCapitalization", qualifiedByName = "toBigDecimal")
    @Mapping(source = "highPriceLast_52Weeks", target = "highPriceLast52Weeks", qualifiedByName = "toBigDecimal")
    @Mapping(source = "lowPriceLast_52Weeks", target = "lowPriceLast52Weeks", qualifiedByName = "toBigDecimal")
    @Mapping(source = "averageDailyVolumeLast_10Days", target = "averageDailyVolumeLast10Days",
            qualifiedByName = "toBigDecimal")
    @Mapping(source = "averageDailyVolumeLast_4Weeks", target = "averageDailyVolumeLast4Weeks",
            qualifiedByName = "toBigDecimal")
    @Mapping(source = "revenueTtm", target = "revenueTtm", qualifiedByName = "toBigDecimal")
    @Mapping(source = "freeCashFlowTtm", target = "freeCashFlowTtm", qualifiedByName = "toBigDecimal")
    @Mapping(source = "threeYearAnnualRevenueGrowthRate", target = "threeYearAnnualRevenueGrowthRate", qualifiedByName = "toBigDecimal")
    @Mapping(source = "dividendYieldDailyTtm", target = "dividendYieldDailyTtm", qualifiedByName = "toBigDecimal")
    @Mapping(source = "dividendRateTtm", target = "dividendRateTtm", qualifiedByName = "toBigDecimal")
    @Mapping(source = "dividendsPerShare", target = "dividendsPerShare", qualifiedByName = "toBigDecimal")
    FundamentalDto toModel(FundamentalForecastRecord record);


    @Named("toBigDecimal")
    default BigDecimal toBigDecimal(Double value) {
        return BigDecimal.valueOf(value);
    }
}