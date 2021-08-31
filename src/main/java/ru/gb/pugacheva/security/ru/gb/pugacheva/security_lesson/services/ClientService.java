package ru.gb.pugacheva.security.ru.gb.pugacheva.security_lesson.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.pugacheva.security.ru.gb.pugacheva.security_lesson.entities.Authority;
import ru.gb.pugacheva.security.ru.gb.pugacheva.security_lesson.entities.Client;
import ru.gb.pugacheva.security.ru.gb.pugacheva.security_lesson.entities.Role;
import ru.gb.pugacheva.security.ru.gb.pugacheva.security_lesson.repositories.ClientRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

//@Service
//@RequiredArgsConstructor
//public class ClientService implements UserDetailsService {
//    private final ClientRepository clientRepository;
//
//    public Optional <Client> findByUsername(String username){
//        return clientRepository.findByUsername(username);
//    }
//
//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
//        Client client = findByUsername(username).orElseThrow(()-> new UsernameNotFoundException(String.format("User '%s' njt found", username)));
//        return new User(client.getUsername(), client.getPassword(), mapRolesToAuthorities(client.getRoles()));
//    }
//
//    private Collection <? extends GrantedAuthority> mapRolesToAuthorities (Collection <Role> roles){
//        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
//    }
//}

@Service
@RequiredArgsConstructor
public class ClientService implements UserDetailsService {
    private final ClientRepository clientRepository;

    public Optional <Client> findByUsername(String username){
        return clientRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Client client = findByUsername(username).orElseThrow(()-> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new User(client.getUsername(), client.getPassword(), mapPersonalAuthoritiesToAuthorities(client.getAuthorities()));
    }

    private Collection <? extends GrantedAuthority> mapPersonalAuthoritiesToAuthorities (Collection <Authority> authorities){
        System.out.println("ПРЕОБРАЗОВАННЫЕ ПРАВА: " + authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.getName())).collect(Collectors.toList()));
        return authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.getName())).collect(Collectors.toList());
    }
}
