package com.example.vibeapp.post;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository {

    @PersistenceContext
    private EntityManager em;

    /**
     * 게시글 저장 (C)
     * persist(): 엔티티를 영속성 컨텍스트에 관리하여 DB에 저장함
     */
    public void save(Post post) {
        if (post.getNo() == null) {
            em.persist(post);
        } else {
            em.merge(post);
        }
    }

    /**
     * ID로 게시글 조회 (R)
     * find(): 1차 캐시 또는 DB에서 식별자로 인티티를 조회함
     */
    public Optional<Post> findById(Long no) {
        return Optional.ofNullable(em.find(Post.class, no));
    }

    /**
     * 게시글 총 개수 조회
     * JPQL을 사용하여 전체 개수를 파악함
     */
    public int count() {
        return em.createQuery("select count(p) from Post p", Long.class)
                .getSingleResult().intValue();
    }

    /**
     * 게시글 목록 조회 - 페이징 처리 포함 (R)
     * JPQL과 setFirstResult/setMaxResults를 사용하여 페이징을 구현함
     */
    public List<Post> findAll(int offset, int limit) {
        return em.createQuery("select p from Post p order by p.no desc", Post.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    /**
     * 게시글 삭제 (D)
     * remove(): 영속성 컨텍스트에서 엔티티를 삭제하고 DB 반영 예약
     */
    public void deleteById(Long no) {
        findById(no).ifPresent(post -> em.remove(post));
    }

    /**
     * 조회수 증가
     * 변경 감지(Dirty Checking) 활용: 트랜잭션 내에서 엔티티를 수정하면 자동 업데이트됨
     */
    public void incrementViews(Long no) {
        findById(no).ifPresent(post -> {
            post.setViews(post.getViews() + 1);
        });
    }
}
