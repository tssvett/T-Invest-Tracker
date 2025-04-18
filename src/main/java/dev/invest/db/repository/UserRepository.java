package dev.invest.db.repository;

import dev.invest.db.jooq.org.jooq.generated.invest.tables.Users;
import dev.invest.db.jooq.org.jooq.generated.invest.tables.records.UsersRecord;
import dev.invest.model.user.UpdateUserRequest;
import java.util.List;
import java.util.Optional;
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

    public Optional<UsersRecord> getById(UUID userId) {
        return dslContext.selectFrom(Users.USERS)
                .where(Users.USERS.USER_ID.eq(userId))
                .fetchOptional();
    }

    public UsersRecord save(UsersRecord usersRecord) {
        UUID userId = usersRecord.getUserId() == null
                ? UUID.randomUUID()
                : usersRecord.getUserId();
        return dslContext.insertInto(Users.USERS,
                        Users.USERS.USER_ID,
                        Users.USERS.LOGIN,
                        Users.USERS.PASSWORD)
                .values(userId,
                        usersRecord.getLogin(),
                        usersRecord.getPassword())
                .onDuplicateKeyIgnore()
                .returning().fetchOne();
    }

    public Optional<UsersRecord> update(UUID id,UpdateUserRequest usersRecord) {
        return dslContext.update(Users.USERS)
                .set(Users.USERS.LOGIN, usersRecord.login())
                .where(Users.USERS.USER_ID.eq(id))
                .returning().fetchOptional();
    }

    public Optional<UsersRecord> delete(UUID userId) {
        return dslContext.deleteFrom(Users.USERS)
                .where(Users.USERS.USER_ID.eq(userId))
                .returning().fetchOptional();
    }
}
