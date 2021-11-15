package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="fileUploadName")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Files {
   
	 @Id 
	   @GeneratedValue( strategy=GenerationType.IDENTITY )
	 @Column(name="file_id")
	private int id;
	private String filename;
	public Files(String filename) {
		super();
		this.filename = filename;
	}
	
	
}
