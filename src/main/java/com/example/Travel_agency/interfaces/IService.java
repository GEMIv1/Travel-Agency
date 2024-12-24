package com.example.Travel_agency.interfaces;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface IService {

    Object performOperation(String operationType, Map<String, Object> params);
}
