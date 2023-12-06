package com.example.tmdt.service.impl;

import com.example.tmdt.dto.CommentDTO;
import com.example.tmdt.dto.UserDTO;
import com.example.tmdt.mapper.CommentMapper;
import com.example.tmdt.mapper.ProductMapper;
import com.example.tmdt.model.Comment;
import com.example.tmdt.model.Notification;
import com.example.tmdt.model.User;
import com.example.tmdt.repository.CommentRepository;
import com.example.tmdt.repository.NotificationRepository;
import com.example.tmdt.repository.ProductRepository;
import com.example.tmdt.repository.UserRepository;
import com.example.tmdt.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService implements ICommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public void save(CommentDTO dto) {
        Comment comment = commentMapper.toEntity(dto);
        User user = userRepository.findUserByAccount_Id(comment.getAccount().getId());
        comment.setUser(user);
        if (comment.getId() == null) {
            comment.setCreateAt(LocalDateTime.now());
            Notification notification = new Notification();
            notification.setTitle("Thông báo shop");
            notification.setContent("Đã đánh giá");
            notification.setCreateAt(LocalDateTime.now());
            notification.setShop(productRepository.findById(dto.getProduct().getId()).get().getShop());
            notification.setAccount(dto.getAccount());
            notification.setAvatar(user.getAvatar());
            notification.setProduct(productMapper.toEntity(dto.getProduct()));
            notificationRepository.save(notification);
        } else {
            Notification notification = new Notification();
            notification.setTitle("Thông báo user");
            notification.setContent("Đã phản hồi đánh giá");
            notification.setCreateAt(LocalDateTime.now());
            notification.setShop(dto.getProduct().getShop());
            notification.setAccount(dto.getAccount());
            notification.setProduct(productMapper.toEntity(dto.getProduct()));
            notification.setAvatar(dto.getProduct().getShop().getAvatar());
            notificationRepository.save(notification);
        }
        commentRepository.save(comment);
    }

    @Override
    public void delete(Long id) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            comment.setStatus("1");
            commentRepository.save(comment);
        }
    }

    @Override
    public CommentDTO findOne(Long id) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            return commentMapper.toDto(comment);
        }
        return null;
    }

    @Override
    public List<CommentDTO> findAll() {
        List<Comment> comments = commentRepository.findAll();
        return commentMapper.toDto(comments);
    }

    @Override
    public List<CommentDTO> findByIdProduct(Long id) {
        List<Comment> comments = commentRepository.findAllByProduct_Id(id);
        if (comments != null) {
            return commentMapper.toDto(comments);
        }
        return null;
    }

    @Override
    public CommentDTO findComment(Long idAccount, Long idProduct) {
        Optional<Comment> commentOptional = commentRepository.findComment(idAccount, idProduct);
        if (commentOptional.isPresent()) {
            return commentMapper.toDto(commentOptional.get());
        }
        return null;
    }

    @Override
    public List<CommentDTO> findCmtByAcc(Long id) {
        List<Comment> comments = commentRepository.findAllByAccount_Id(id);
        if (!comments.isEmpty()) {
            return commentMapper.toDto(comments);
        }
        return null;
    }
}