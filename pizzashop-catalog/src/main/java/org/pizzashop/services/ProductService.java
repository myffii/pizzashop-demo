package org.pizzashop.services;

import lombok.RequiredArgsConstructor;
import org.pizzashop.dto.ProductDTO;
import org.pizzashop.dto.ProductResponseDTO;
import org.pizzashop.exceptions.ProductNotFoundException;
import org.pizzashop.mappers.ProductMapper;
import org.pizzashop.mappers.ProductResponseDTOMapper;
import org.pizzashop.models.Product;
import org.pizzashop.repositories.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductResponseDTOMapper productResponseDTOMapper;

    public List<ProductResponseDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        var responseList = productResponseDTOMapper
                .toProductResponseList((productRepository.findAll(pageable).getContent()));
        return responseList;
    }

    @Transactional
    public Product save(ProductDTO productDTO) {
        var product = productMapper.toProduct(productDTO);
        product.setImage(new byte[0]);
        return productRepository.save(product);
    }

    public ProductResponseDTO findById(Long id) {
        ProductResponseDTO response = productResponseDTOMapper.toProductResponse(productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new));
        return response;
    }

    @Transactional
    public ProductResponseDTO uploadImage(Long id, MultipartFile file) throws IOException {
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        product.setImage(file.getBytes());
        var productSaved = productRepository.save(product);
        return productResponseDTOMapper.toProductResponse(productSaved);
    }

    public byte[] getProductImageById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        return product.getImage();
    }
}
