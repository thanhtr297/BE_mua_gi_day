package com.example.tmdt.repository;
import com.example.tmdt.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "SELECT c.* FROM comment c JOIN (SELECT user_id, MAX(id) AS max_id FROM Comment WHERE product_id = :productId GROUP BY user_id) latest_comments ON c.user_id = latest_comments.user_id AND c.id = latest_comments.max_id WHERE c.product_id = :productId",nativeQuery = true)
    List<Comment> findAllByProduct_Id(@Param("productId")Long productId);
    List<Comment> findAllByAccount_Id(Long id);
    @Query(value = "select * from comment where account_id = :idAccount and product_id = :idProduct",nativeQuery = true)
    Optional <Comment> findComment(@Param("idAccount")Long idAccount, @Param("idProduct")Long idProduct);
}
