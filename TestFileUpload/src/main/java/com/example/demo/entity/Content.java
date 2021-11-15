package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Content {
	@Id 
	   @GeneratedValue( strategy=GenerationType.IDENTITY )
private int id;
	private String name;
private String transactionType;
private String	transactionID;
private double Amount;
private String date;
private String status ="pending";

@ManyToOne(cascade=CascadeType.ALL)
@JoinColumn(name = "file_id")
private Files files;
	
	
	
}
