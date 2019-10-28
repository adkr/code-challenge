package pl.adkr.hsbc.challenge.posting.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.adkr.hsbc.challenge.posting.entity.PostEntity;
import pl.adkr.hsbc.challenge.posting.entity.PostEntityConverter;
import pl.adkr.hsbc.challenge.posting.entity.PostRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService implements PostProvider, PostStore {

    private final PostRepository postRepository;
    private final PostEntityConverter postEntityConverter;
    private final PostConverter postConverter;

    @Autowired
    public PostService(PostRepository postRepository, PostEntityConverter postEntityConverter, PostConverter postConverter) {
        this.postRepository = postRepository;
        this.postEntityConverter = postEntityConverter;
        this.postConverter = postConverter;
    }

    @Override
    public Optional<Post> getPost(Long id) {
        Optional<PostEntity> postEntity = postRepository.findById(id);
        return postEntity.flatMap(postConverter::toPost);
    }

    @Override
    public List<Post> getPostsForUser(Long userId) {
        Collection<PostEntity> postEntities = postRepository.findAllByUserIdOrderByCreateDateTimeDesc(userId);
        return postEntities
                .stream()
                .map(postConverter::toPost)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Post> storePost(String message, Long userId) {
        PostEntity savedPost = postRepository.save(postEntityConverter.toPostEntity(message, userId, LocalDateTime.now()));
        return postConverter.toPost(savedPost);
    }
}
