package org.pizzashop.mappers;

import org.mapstruct.Mapper;
import org.pizzashop.dto.UserResponseDTO;
import org.pizzashop.models.User;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {

    UserResponseDTO toUserResponse(User user);

    User toUser(UserResponseDTO userResponseDTO);
}
