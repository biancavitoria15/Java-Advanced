package br.com.fiap.api_rest_aula01.controller;

import br.com.fiap.api_rest_aula01.dto.ClienteRequest;
import br.com.fiap.api_rest_aula01.dto.ClienteResponse;
import br.com.fiap.api_rest_aula01.model.Cliente;
import br.com.fiap.api_rest_aula01.repository.ClienteRepository;
import br.com.fiap.api_rest_aula01.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/clientes", produces = {application/json})
public class ClienteController {
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ClienteService clienteService;

    // Create, Read, Update, Delete - CRUD
    // Post, Get, Put, Delete - Verbos HTTP correspondentes

    @Operation(summary = "Cria um novo cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso")
                   content = {@Content(mediaType = "application/json",
                   schema = @Schema(implementation = Cliente.class))}),
                   @APIResponse(responseCode = "400", description = "Atributos informados são invalidos",
                   content = @Content(schema = @Schema()))
    })
    @PostMapping
    public ResponseEntity<Cliente> createCliente(@Valid @RequestBody ClienteRequest cliente) {
        Cliente clienteSalvo = clienteRepository.save(clienteService.requestToCliente(cliente));
        return new ResponseEntity<>(clienteSalvo, HttpStatus.CREATED);
    }

    @Operation(summary = "Retorna uma lista de clientes")
    @GetMapping
    public ResponseEntity<Page<ClienteResponse>> readClientes(@RequestParam(required = true) int page) {
        Pageable pageable = PageRequest.of(page, 2, Sort.by("categoria").ascending().and(Sort.by("nome").ascending()));
        return new ResponseEntity<>(clienteService.findAll(pageable), HttpStatus.OK);
    }

    // PathVariable = parâmetro diretamente na URL, ex: /clientes/1
    // RequestParam = parâmetro como query, ex: /clientes/?id=1
    @Operation(summary = "Retorna um cliente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso")
            content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = Cliente.class))}),
@APIResponse(responseCode = "400", description = "Nenhum cliente encontrado",
        content = @Content(schema = @Schema()))
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> readCliente(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(clienteService.clienteToResponse(cliente.get()), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente cadastrado com sucesso")
            content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = Cliente.class))}),
@APIResponse(responseCode = "400", description = "Atributos informados são invalidos",
        content = @Content(schema = @Schema()))
    })
    @Operation(summary = "Atualiza um cliente existente")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id,
                                                 @RequestBody Cliente cliente) {
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);
        if (clienteExistente.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        cliente.setId(clienteExistente.get().getId());
        Cliente clienteAtualizado = clienteRepository.save(cliente);
        return new ResponseEntity<>(clienteAtualizado, HttpStatus.CREATED);
    }
    @Operation(summary = "Exclui um cliente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Nenhum cliente encontrado para excluir")
            content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = Cliente.class))}),
@APIResponse(responseCode = "204", description = "Cliente excuido com sucesso",
        content = @Content(schema = @Schema()))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteRepository.deleteById(id);
        if (clienteExistente.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

