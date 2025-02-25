package br.com.fiap.api_rest_aula01.repository;

import br.com.fiap.api_rest_aula01.repository.ClienteRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.fiap.api_rest_aula01.model.Cliente;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
