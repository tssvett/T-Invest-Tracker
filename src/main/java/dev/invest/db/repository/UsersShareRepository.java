package dev.invest.db.repository;

import static dev.invest.db.jooq.org.jooq.generated.invest.tables.UsersShare.USERS_SHARE;
import dev.invest.db.jooq.org.jooq.generated.invest.tables.records.UsersShareRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UsersShareRepository {
    private final DSLContext dslContext;

    public int save(UsersShareRecord usersShareRecord) {
        return dslContext
                .insertInto(USERS_SHARE,
                        USERS_SHARE.USER_ID,
                        USERS_SHARE.SHARE_UID)
                .values(
                        usersShareRecord.getUserId(),
                        usersShareRecord.getShareUid()
                )
                .onDuplicateKeyIgnore()
                .execute();
    }
}
