package com.example.tmdt.service;

import com.example.tmdt.dto.CommentDTO;

import java.util.List;
import java.util.Optional;

public interface ICommentService extends BaseService<CommentDTO> {
    List<CommentDTO> findByIdProduct(Long id);
    CommentDTO findComment(Long idAccount, Long idProduct);
    List<CommentDTO> findCmtByAcc(Long id);
}
