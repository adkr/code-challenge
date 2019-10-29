package pl.adkr.hsbc.challenge.following.entity.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByLogin(String login);
}
