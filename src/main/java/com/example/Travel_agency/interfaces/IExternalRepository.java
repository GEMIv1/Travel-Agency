package com.example.Travel_agency.interfaces;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface IExternalRepository <T> {
    List<T> getAll();
}
