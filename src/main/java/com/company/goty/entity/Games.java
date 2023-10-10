package com.company.goty.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="goty")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Games {

	@Id
	private String id;
	private String name;
	private Integer votos;
	private String url;
}
