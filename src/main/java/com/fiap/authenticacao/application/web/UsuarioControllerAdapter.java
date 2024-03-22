package com.fiap.authenticacao.application.web;

import com.fiap.authenticacao.application.web.configuration.JwtService;
import com.fiap.authenticacao.application.web.request.UsuarioRequest;
import com.fiap.authenticacao.application.web.response.JwtResponse;
import com.fiap.authenticacao.domain.model.Usuario;
import com.fiap.authenticacao.domain.model.valueObject.Senha;
import com.fiap.authenticacao.domain.model.valueObject.UserName;
import com.fiap.authenticacao.domain.ports.in.IUsuarioUseCasePort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioControllerAdapter {

    private final IUsuarioUseCasePort usuarioUseCasePort;

    @PostMapping(value = {"/", ""}, produces = "application/json")
    public ResponseEntity<?> cadastrar(@RequestBody @Valid UsuarioRequest usuario) {
        return ResponseEntity.ok(usuarioUseCasePort.cadastra(new UsuarioRequest().to(usuario)));
    }
    @PutMapping(value = {"/{id}", ""}, produces = "application/json")
    public ResponseEntity<?> editar(@RequestBody @Valid UsuarioRequest usuario, @PathVariable UUID id) {
        return ResponseEntity.ok(usuarioUseCasePort.atualizar(new UsuarioRequest().to(usuario), id));
    }
    @DeleteMapping(value = {"/{id}", ""})
    public ResponseEntity<?> excluir(@PathVariable UUID id) {
        usuarioUseCasePort.excluir(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = {"/", ""}, produces = "application/json")
    public ResponseEntity<?> obterTodosUsuarios() {
        return ResponseEntity.ok(usuarioUseCasePort.obterTodos());
    }
}
