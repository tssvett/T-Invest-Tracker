package dev.invest.mapper;

import dev.invest.db.jooq.org.jooq.generated.invest.tables.records.UsersShareRecord;
import dev.invest.model.portfolio.PortfolioDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PortfolioMapper {

    @Mapping(source = "shareUid", target = "shareId")
    PortfolioDto toModel(UsersShareRecord usersShareRecord);

    @Mapping(source = "shareId", target = "shareUid")
    UsersShareRecord toEntity(PortfolioDto portfolioDto);
}
