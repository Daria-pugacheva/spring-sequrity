package ru.gb.pugacheva.security.ru.gb.pugacheva.security_lesson.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.gb.pugacheva.security.ru.gb.pugacheva.security_lesson.entities.Client;

import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository <Client, Long> {

    Optional<Client> findByUsername(String username);
}
