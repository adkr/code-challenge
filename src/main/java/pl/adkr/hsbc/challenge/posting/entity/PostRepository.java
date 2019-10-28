package pl.adkr.hsbc.challenge.posting.entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface PostRepository extends CrudRepository<PostEntity, Long> {

    List<PostEntity> findAllByUserIdOrderByCreateDateTimeDesc(Long userId);

}
