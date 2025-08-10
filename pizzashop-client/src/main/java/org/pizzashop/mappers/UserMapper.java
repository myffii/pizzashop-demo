package org.pizzashop.mappers;

import org.mapstruct.Mapper;
import org.pizzashop.dto.UserDTO;
import org.pizzashop.models.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toUserDTO(User user);

    User toUser(UserDTO userDTO);
}
