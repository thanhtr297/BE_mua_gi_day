package com.example.tmdt.mapper;

import com.example.tmdt.dto.CommentDTO;
import com.example.tmdt.model.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")

public interface CommentMapper extends EntityMapper<CommentDTO, Comment> {
}
