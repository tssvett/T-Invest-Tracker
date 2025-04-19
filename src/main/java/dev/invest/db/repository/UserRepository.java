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

    public Optional<UsersRecord> findByEmail(String email) {
        return dslContext.selectFrom(Users.USERS)
                .where(Users.USERS.EMAIL.eq(email))
                .fetchOptional();
    }

    public Optional<UsersRecord> findByLogin(String login) {
        return dslContext.selectFrom(Users.USERS)
                .where(Users.USERS.LOGIN.eq(login))
                .fetchOptional();
    }

    public UsersRecord save(UsersRecord usersRecord) {
        UUID userId = usersRecord.getUserId() == null
                ? UUID.randomUUID()
                : usersRecord.getUserId();
        return dslContext.insertInto(Users.USERS,
                        Users.USERS.USER_ID,
                        Users.USERS.LOGIN,
                        Users.USERS.PASSWORD,
                        Users.USERS.EMAIL)
                .values(userId,
                        usersRecord.getLogin(),
                        usersRecord.getPassword(),
                        usersRecord.getEmail())
                .onDuplicateKeyIgnore()
                .returning().fetchOne();
    }

    public Optional<UsersRecord> update(UUID id, UpdateUserRequest userDto) {
        return dslContext.update(Users.USERS)
                .set(Users.USERS.LOGIN, userDto.login())
                .set(Users.USERS.EMAIL, userDto.email())
                .where(Users.USERS.USER_ID.eq(id))
                .returning().fetchOptional();
    }

    public Optional<UsersRecord> updatePassword(UUID id, String password) {
        return dslContext.update(Users.USERS)
                .set(Users.USERS.PASSWORD, password)
                .where(Users.USERS.USER_ID.eq(id))
                .returning().fetchOptional();
    }

    public Optional<UsersRecord> delete(UUID userId) {
        return dslContext.deleteFrom(Users.USERS)
                .where(Users.USERS.USER_ID.eq(userId))
                .returning().fetchOptional();
    }
}
