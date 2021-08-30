package ru.gb.pugacheva.security.ru.gb.pugacheva.security_lesson.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.gb.pugacheva.security.ru.gb.pugacheva.security_lesson.entities.Role;

@Repository
public interface RoleRepository extends CrudRepository <Role, Long> {
}
