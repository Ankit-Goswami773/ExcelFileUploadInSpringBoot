package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Content;

public interface ContentRepo  extends JpaRepository<Content, Integer>{

}
