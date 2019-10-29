package pl.adkr.hsbc.challenge.following.entity.followingers;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Transactional(readOnly = true)
public interface FollowingerRepository extends CrudRepository<FollowingerEntity, FollowingerEntityId> {

    Set<FollowingerEntity> findAllByIdFollowerId(Long followerId);

    Set<FollowingerEntity> findAllByIdFollowingId(Long followingId);
}
