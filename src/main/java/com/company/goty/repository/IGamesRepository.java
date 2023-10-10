package com.company.goty.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.goty.entity.Games;

public interface IGamesRepository extends JpaRepository<Games, String>  {

}
