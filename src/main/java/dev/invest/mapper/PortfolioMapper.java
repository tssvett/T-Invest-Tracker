package dev.invest.mapper;

import dev.invest.db.jooq.org.jooq.generated.invest.tables.records.UsersShareRecord;
import dev.invest.model.portfolio.OwnedShareCreateDto;
import dev.invest.model.portfolio.OwnedShareDto;
import java.math.BigInteger;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PortfolioMapper {

    @Mapping(source = "shareUid", target = "shareId")
    OwnedShareDto toModel(UsersShareRecord usersShareRecord);

    @Mapping(source = "shareId", target = "shareUid")
    UsersShareRecord toEntity(OwnedShareDto ownedShareDto);

    @Mapping(source = "ownedShareDto.shareId", target = "shareUid")
    UsersShareRecord toEntity(OwnedShareCreateDto ownedShareDto, BigInteger sharePrice, Integer id);
}
