package sk.streetofcode.taskmanagementsystem.implemation.jdbc.service;

import org.springframework.stereotype.Service;
import sk.streetofcode.taskmanagementsystem.api.UserService;
import sk.streetofcode.taskmanagementsystem.api.request.UserAddRequest;
import sk.streetofcode.taskmanagementsystem.domain.User;
import sk.streetofcode.taskmanagementsystem.implemation.jdbc.repository.UserJdbcRepository;

import java.util.List;
@Service
public class UserServiceJdbcImpl implements UserService {
    private final UserJdbcRepository repository;
    public UserServiceJdbcImpl(UserJdbcRepository repository){
        this.repository = repository;
    }
    @Override
    public long add(UserAddRequest request) {
        return repository.add(request);
    }

    @Override
    public void delete(long id) {
        repository.delete(id);

    }

    @Override
    public User get(long id) {
        return repository.getByID(id);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }
}
