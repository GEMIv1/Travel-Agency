package com.example.Travel_agency.interfaces;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface IBookingRepository <T> {
    boolean save(T entity);
    void update(T entity);
    List<T> getAll();
}
