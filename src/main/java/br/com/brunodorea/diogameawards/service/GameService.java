package br.com.brunodorea.diogameawards.service;

import br.com.brunodorea.diogameawards.domain.model.Game;

import java.util.List;

public interface GameService {
    List<Game> findAll();

    Game findById(Long id);

    void insert(Game game);

    void update(Long id, Game game);

    void delete(Long id);
}
