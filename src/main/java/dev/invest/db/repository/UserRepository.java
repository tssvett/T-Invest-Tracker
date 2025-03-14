package dev.invest.db.repository;

import dev.invest.db.jooq.org.jooq.generated.invest.tables.Users;
import dev.invest.db.jooq.org.jooq.generated.invest.tables.records.UsersRecord;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final DSLContext dslContext;

    public List<UsersRecord> findAll() {
        return dslContext.selectFrom(Users.USERS)
                .fetch();
    }

    public int save(UsersRecord usersRecord) {
        UUID userid = usersRecord.getUserId() == null
                ? UUID.randomUUID()
                : usersRecord.getUserId();
        return dslContext.insertInto(Users.USERS,
                        Users.USERS.USER_ID,
                        Users.USERS.LOGIN,
                        Users.USERS.PASSWORD)
                .values(userid,
                        usersRecord.getLogin(),
                        usersRecord.getPassword()
                )
                .execute();
    }
}
