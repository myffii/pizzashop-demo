package org.pizzashop.services;

import lombok.RequiredArgsConstructor;
import org.pizzashop.clients.CategoryApiClient;
import org.pizzashop.clients.OrderApiClient;
import org.pizzashop.dto.OrderDTO;
import org.pizzashop.dto.ProductResponseDTO;
import org.pizzashop.dto.UserDTO;
import org.pizzashop.dto.UserResponseDTO;
import org.pizzashop.exceptions.UserNotFoundException;
import org.pizzashop.mappers.UserMapper;
import org.pizzashop.mappers.UserResponseMapper;
import org.pizzashop.models.User;
import org.pizzashop.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserResponseMapper userResponseMapper;
    private final CategoryApiClient categoryApiClient;
    private final OrderApiClient orderApiClient;

    @Transactional
    public User save(UserDTO userDTO) {
        User user = userMapper.toUser(userDTO);
        return userRepository.save(user);
    }

    public UserResponseDTO findById(Long id) {
        UserResponseDTO response = userResponseMapper.toUserResponse(
                userRepository.findById(id).orElseThrow(UserNotFoundException::new));
        return response;
    }

    public List<ProductResponseDTO> getMenu(int page, int size) {
        return categoryApiClient.getMenu(page, size);
    }

    public Long createOrder(OrderDTO orderDTO) {
        return orderApiClient.createOrder(orderDTO);
    }
}
