package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Files;

public interface FileRepo extends JpaRepository<Files, Integer> {

}
