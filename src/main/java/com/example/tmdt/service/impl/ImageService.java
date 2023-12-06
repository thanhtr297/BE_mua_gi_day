package com.example.tmdt.service.impl;

import com.example.tmdt.dto.ImageDTO;
import com.example.tmdt.mapper.ImageMapper;
import com.example.tmdt.model.fkProduct.Image;
import com.example.tmdt.repository.ImageRepository;
import com.example.tmdt.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService implements IImageService {
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ImageMapper imageMapper;
    @Override
    public void save(ImageDTO dto) {
        Image image = imageMapper.toEntity(dto);
        imageMapper.toDto(imageRepository.save(image));
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ImageDTO findOne(Long id) {
        Optional<Image> imageOptional = imageRepository.findById(id);
        if (imageOptional.isPresent()) {
            Image image = imageOptional.get();
            return imageMapper.toDto(image);
        } return null;
    }

    @Override
    public List<ImageDTO> findAll() {
        List<Image> images = imageRepository.findAll();
        return imageMapper.toDto(images);
    }
}
