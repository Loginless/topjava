package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.UsersUtil;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {

    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
    private Map<Integer, User> repositoryUsers = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        UsersUtil.USERS.forEach(this::save);
    }

    @Override
    public User save(User user) {
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
            repositoryUsers.put(user.getId(), user);
            return user;
        }
        // treat case: update, but absent in storage
        return repositoryUsers.computeIfPresent(user.getId(), (id, oldUser) -> user);
    }

    @Override
    public boolean delete(int id) {
        if (repositoryUsers.remove(id) != null) {
            log.info("delete {}", id);
            return true;
        } else return false;
    }

    @Override
    public User get(int id) {
        log.info("get {}", id);
        return repositoryUsers.get(id);
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        return repositoryUsers.values().stream().sorted(Comparator.comparing(User::getName)).collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        return repositoryUsers.values().stream().filter(user -> email.equals(user.getEmail())).findFirst().orElse(null);
    }
}
