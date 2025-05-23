package dev.invest.db.repository;

import static dev.invest.db.jooq.org.jooq.generated.invest.tables.UsersShare.USERS_SHARE;
import dev.invest.db.jooq.org.jooq.generated.invest.tables.records.UsersShareRecord;
import java.util.List;
import java.util.UUID;
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
                        USERS_SHARE.SHARE_UID,
                        USERS_SHARE.SHARE_COUNT,
                        USERS_SHARE.SHARE_PRICE)
                .values(
                        usersShareRecord.getUserId(),
                        usersShareRecord.getShareUid(),
                        usersShareRecord.getShareCount(),
                        usersShareRecord.getSharePrice())
                .onDuplicateKeyIgnore()
                .execute();
    }

    public List<UsersShareRecord> findByUserId(UUID userId) {
        return dslContext.selectFrom(USERS_SHARE)
                .where(USERS_SHARE.USER_ID.eq(userId))
                .fetch();
    }

    public List<UsersShareRecord> findByShareUid(UUID shareUid) {
        return dslContext.selectFrom(USERS_SHARE)
                .where(USERS_SHARE.SHARE_UID.eq(shareUid))
                .fetch();
    }

    public int update(UsersShareRecord usersShareRecord) {
        return dslContext.update(USERS_SHARE)
                .set(USERS_SHARE.USER_ID, usersShareRecord.getUserId())
                .set(USERS_SHARE.SHARE_UID, usersShareRecord.getShareUid())
                .where(USERS_SHARE.USER_ID.eq(usersShareRecord.getUserId())
                        .and(USERS_SHARE.SHARE_UID.eq(usersShareRecord.getShareUid())))
                .execute();
    }

    public int delete(UUID userId, UUID shareUid) {
        return dslContext.deleteFrom(USERS_SHARE)
                .where(USERS_SHARE.USER_ID.eq(userId)
                        .and(USERS_SHARE.SHARE_UID.eq(shareUid)))
                .execute();
    }

    public int deleteByUserId(UUID userId) {
        return dslContext.deleteFrom(USERS_SHARE)
                .where(USERS_SHARE.USER_ID.eq(userId))
                .execute();
    }

    public int deleteByShareUid(UUID shareUid) {
        return dslContext.deleteFrom(USERS_SHARE)
                .where(USERS_SHARE.SHARE_UID.eq(shareUid))
                .execute();
    }
}
