package pl.adkr.hsbc.challenge.following.entity.followingers;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Transactional(readOnly = true)
public interface FollowingersRepository extends CrudRepository<FollowingersEntity, FollowingersEntityId> {

    Set<FollowingersEntity> findAllByIdFollowerId(Long followerId);

    Set<FollowingersEntity> findAllByIdFollowingId(Long followingId);
}
