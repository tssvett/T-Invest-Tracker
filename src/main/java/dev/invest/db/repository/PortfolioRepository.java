package dev.invest.db.repository;

import dev.invest.db.jooq.org.jooq.generated.invest.tables.UsersShare;
import dev.invest.db.jooq.org.jooq.generated.invest.tables.records.UsersShareRecord;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PortfolioRepository {
    private final DSLContext dslContext;

    public UsersShareRecord save(UsersShareRecord usersShare) {
        return dslContext.insertInto(UsersShare.USERS_SHARE)
                .set(UsersShare.USERS_SHARE.USER_ID, usersShare.getUserId())
                .set(UsersShare.USERS_SHARE.SHARE_UID, usersShare.getShareUid())
                .returning()
                .fetchOne();
    }

    public List<UsersShareRecord> findSharesByUserId(UUID userId) {
        return dslContext.selectFrom(UsersShare.USERS_SHARE)
                .where(UsersShare.USERS_SHARE.USER_ID.eq(userId))
                .fetch();
    }

    public List<UsersShareRecord> findUsersByShareUid(UUID shareUid) {
        return dslContext.selectFrom(UsersShare.USERS_SHARE)
                .where(UsersShare.USERS_SHARE.SHARE_UID.eq(shareUid))
                .fetch();
    }

    public UsersShareRecord update(UsersShareRecord usersShare) {
        return dslContext.update(UsersShare.USERS_SHARE)
                .set(UsersShare.USERS_SHARE.USER_ID, usersShare.getUserId())
                .set(UsersShare.USERS_SHARE.SHARE_UID, usersShare.getShareUid())
                .where(UsersShare.USERS_SHARE.USER_ID.eq(usersShare.getUserId()))
                .and(UsersShare.USERS_SHARE.SHARE_UID.eq(usersShare.getShareUid()))
                .returning()
                .fetchOne();
    }

    public int delete(UUID userId, UUID shareUid) {
        return dslContext.deleteFrom(UsersShare.USERS_SHARE)
                .where(UsersShare.USERS_SHARE.USER_ID.eq(userId))
                .and(UsersShare.USERS_SHARE.SHARE_UID.eq(shareUid))
                .execute();
    }
}
