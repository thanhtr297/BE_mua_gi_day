package com.example.tmdt.mapper;
import com.example.tmdt.dto.UserDTO;
import com.example.tmdt.model.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "Spring")
public interface UserMapper extends EntityMapper<UserDTO, User>{

}
