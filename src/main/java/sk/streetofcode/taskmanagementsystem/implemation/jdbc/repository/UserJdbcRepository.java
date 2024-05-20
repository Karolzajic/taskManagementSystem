package sk.streetofcode.taskmanagementsystem.implemation.jdbc.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import sk.streetofcode.taskmanagementsystem.api.exception.BadRequestException;
import sk.streetofcode.taskmanagementsystem.api.exception.InternalErrorException;
import sk.streetofcode.taskmanagementsystem.api.exception.ResoureNotFoundException;
import sk.streetofcode.taskmanagementsystem.api.request.UserAddRequest;
import sk.streetofcode.taskmanagementsystem.domain.User;
import sk.streetofcode.taskmanagementsystem.implemation.jdbc.mapper.UserRowMapper;

import java.sql.PreparedStatement;
import java.util.List;
@Repository
public class UserJdbcRepository {
    private final UserRowMapper userRowMapper;
    private final JdbcTemplate jdbcTemplate;
    private  static final Logger logger;
    private static final String GET_ALL;
    private static final String GET_BY_ID;
    private static final String INSERT;
    private static final String DELETE;



    static {
        logger = LoggerFactory.getLogger(UserJdbcRepository.class);
        GET_ALL = "SELECT * FROM user";
        GET_BY_ID = "SELECT * FROM user WHERE ID = ?";
        INSERT = "INSERT INTO user (id, name, email) VALUES (next value for user_id_seq, ?,?);";
        DELETE = "DELETE FROM user WHERE id = ?";

    }

    public UserJdbcRepository(UserRowMapper userRowMapper, JdbcTemplate jdbcTemplate) {
        this.userRowMapper = userRowMapper;
        this.jdbcTemplate = jdbcTemplate;
    }
    public long add(UserAddRequest request) {
        try {
            final KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                final PreparedStatement ps = connection.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1, request.getName());
                ps.setString(2, request.getEmail());
                return ps;

            }, keyHolder);
            if (keyHolder.getKey() == null) {
                logger.error("KeyHolder is null");
                throw new InternalErrorException("Error while adding user");
            }
            return keyHolder.getKey().longValue();
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestException("user with email: " + request.getEmail() + "already exist");
        } catch (DataAccessException e) {
            logger.error("Error while adding user", e);
            throw new InternalErrorException("Error while adding user");
        }
    }

    public void delete(long id){
        try{
            jdbcTemplate.update(DELETE,id);
        } catch (DataAccessException e){
            logger.error("Error while deleting user", e);
            throw new InternalErrorException("Error while deleting user");
        }

    }


    public List<User> getAll(){
        return jdbcTemplate.query(GET_ALL,userRowMapper);
    }

    public User getByID(long id){
        try{
            return jdbcTemplate.queryForObject(GET_BY_ID, userRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResoureNotFoundException("User wiht id " + id + " was not found");
        } catch (DataAccessException e) {
            logger.error("Error while getting user", e);
            throw new InternalErrorException("Error while getting user by id");
        }
    }

}
