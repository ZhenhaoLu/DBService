package com.example.dbservice.service.impl;

import com.example.dbservice.pojo.dto.DynamicQueryDTO;
import com.example.dbservice.repository.DynamicQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DynamicQueryService {

    private final DynamicQueryRepository dynamicQueryRepository;

    @Autowired
    public DynamicQueryService(DynamicQueryRepository dynamicQueryRepository){
        this.dynamicQueryRepository = dynamicQueryRepository;
    }

    public List<DynamicQueryDTO> specialSearch(Integer age, String name){
        return this.dynamicQueryRepository.specialSearch(age, name);
    }

}
