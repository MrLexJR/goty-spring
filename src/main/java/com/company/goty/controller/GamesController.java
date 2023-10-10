package com.company.goty.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.goty.dto.GamesDTO;
import com.company.goty.entity.Games;
import com.company.goty.repository.IGamesRepository;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api/v1/goty")
public class GamesController {

	@Autowired
	private IGamesRepository gamesRepository;

	@GetMapping
	public List<Games> getAllGames() {
		return gamesRepository.findAll();
	}

	@GetMapping("/{id}")
	public GamesDTO searchGame(@PathVariable String id) {
		Optional<Games> games = gamesRepository.findById(id);
		GamesDTO gamesDTO = new GamesDTO();
		if (games.isPresent()) {
			gamesDTO.setId(games.get().getId());
			gamesDTO.setName(games.get().getName());
			gamesDTO.setVotos(games.get().getVotos());
			gamesDTO.setUrl(games.get().getUrl());
		}
		return gamesDTO;
	}

	@GetMapping("/vote/{id}")
	public ResponseEntity<Object> voteGame(@PathVariable String id) {
		Optional<Games> optionalGame = gamesRepository.findById(id);
		if (optionalGame.isPresent()) {
			Games game = optionalGame.get();

			game.setVotos(game.getVotos() + 1);

			gamesRepository.save(game);

			// Crear un mensaje de respuesta
			String mensaje = "Gracias por votar por " + game.getName();
			Map<String, Object> response = new HashMap<>();
			response.put("ok", true);
			response.put("mensaje", mensaje);

			return ResponseEntity.ok(response);
		} else {
			String mensaje = "No existe un juego con ese ID " + id;
			Map<String, Object> response = new HashMap<>();
			response.put("ok", false);
			response.put("mensaje", mensaje);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
	}

}
