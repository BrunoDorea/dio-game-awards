package br.com.brunodorea.diogameawards.service.impl;

import br.com.brunodorea.diogameawards.domain.model.Game;
import br.com.brunodorea.diogameawards.domain.model.GameRepository;
import br.com.brunodorea.diogameawards.service.GameService;
import br.com.brunodorea.diogameawards.service.exception.BusinessException;
import br.com.brunodorea.diogameawards.service.exception.NoContentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository repository;

    @Override
    public List<Game> findAll() {
        List<Game> games = repository.findAll();
        return games;
    }

    @Override
    public Game findById(Long id) {
        Optional<Game> game = repository.findById(id);
        return game.orElseThrow(() -> new NoContentException());
    }

    @Override
    public void insert(Game game) {
        if(Objects.nonNull(game.getId())) {
            throw new BusinessException("Os IDs para alteração são divergentes.");
        }
        repository.save(game);
    }

    @Override
    public void update(Long id, Game game) {
        Game gameDb = findById(id);
        if(gameDb.getId().equals(game.getId())) {
            repository.save(game);
        } else {
            throw new BusinessException("Os IDs para alteração são divergentes.");
        }
    }

    @Override
    public void delete(Long id) {
        Game gameDb = findById(id);
        repository.delete(gameDb);
    }

    @Override
    public void vote(Long id) {
        Game gameDb = findById(id);
        gameDb.setVotes(gameDb.getVotes() + 1);
        update(id, gameDb);
    }
}
