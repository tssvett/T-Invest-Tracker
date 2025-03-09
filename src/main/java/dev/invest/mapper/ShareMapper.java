package dev.invest.mapper;

import dev.invest.model.share.ShareDto;
import java.math.BigDecimal;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import ru.tinkoff.piapi.contract.v1.Quotation;
import ru.tinkoff.piapi.contract.v1.Share;
import dev.invest.db.jooq.org.jooq.generated.invest.tables.records.ShareRecord;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ShareMapper {

    @Mapping(source = "uid", target = "id")
    ShareDto toModel(ShareRecord shareRecord);

    List<ShareDto> toModelList(List<ShareRecord> sharesRecord);

    @Named("toBigDecimal")
    default BigDecimal toBigDecimal(Quotation quotation) {
        long units = quotation.getUnits();
        int nano = quotation.getNano();
        System.out.println("units: " + units + ", nano: " + nano);

        return new BigDecimal(units + "." + nano);
    }
}
