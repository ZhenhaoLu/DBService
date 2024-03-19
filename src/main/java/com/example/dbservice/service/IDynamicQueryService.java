package com.example.dbservice.service;

import com.example.dbservice.pojo.dto.DynamicQueryDTO;

import java.util.List;

public interface IDynamicQueryService {
    List<DynamicQueryDTO> specialSearch(Integer age, String name);
}
