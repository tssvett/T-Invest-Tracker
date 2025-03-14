package dev.invest.mapper;

import dev.invest.model.forecast.ForecastDto;
import java.math.BigDecimal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import ru.tinkoff.piapi.contract.v1.GetForecastResponse.ConsensusItem;
import ru.tinkoff.piapi.contract.v1.Quotation;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ForecastMapper {

    @Mapping(source = "uid", target = "id")
    @Mapping(source = "currentPrice", target = "currentPrice", qualifiedByName = "toBigDecimal")
    @Mapping(source = "consensus", target = "consensus", qualifiedByName = "toBigDecimal")
    @Mapping(source = "minTarget", target = "minTarget", qualifiedByName = "toBigDecimal")
    @Mapping(source = "maxTarget", target = "maxTarget", qualifiedByName = "toBigDecimal")
    @Mapping(source = "priceChange", target = "priceChange", qualifiedByName = "toBigDecimal")
    @Mapping(source = "priceChangeRel", target = "priceChangeRel", qualifiedByName = "toBigDecimal")
    ForecastDto toModel(ConsensusItem forecast);

    @Named("toBigDecimal")
    default BigDecimal toBigDecimal(Quotation quotation) {
        long units = quotation.getUnits();
        int nano = quotation.getNano();

        return new BigDecimal(units + "." + nano);
    }
}
