package com.fiap.authenticacao.domain.usecase;

import com.fiap.authenticacao.domain.exception.LoginInvalidoException;
import com.fiap.authenticacao.domain.exception.SenhaFracaException;
import com.fiap.authenticacao.domain.model.Usuario;
import com.fiap.authenticacao.domain.model.valueObject.Senha;
import com.fiap.authenticacao.domain.model.valueObject.UserName;
import com.fiap.authenticacao.domain.ports.in.IUsuarioUseCasePort;
import com.fiap.authenticacao.domain.ports.out.IUsuarioRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
public class UsuarioUseCase implements IUsuarioUseCasePort {
    private final IUsuarioRepositoryPort repository;

    @Override
    public Optional<Usuario> realizaLogin(Usuario usuario) throws LoginInvalidoException, SenhaFracaException {
        if ((Objects.isNull(usuario.getNome())) && (Objects.isNull(usuario.getMatricula()))) {
            throw new LoginInvalidoException();
        }
        if (Objects.isNull(usuario.getSenha())) {
            throw new SenhaFracaException();
        }
        if (Objects.nonNull(usuario.getNome())) {
            return repository.realizaLogin(usuario.getNome(), usuario.getSenha());
        }
        return repository.realizaLogin(usuario.getMatricula(), usuario.getSenha());
    }

    @Override
    public Optional<Usuario> localizaPorNome(UserName nome) throws LoginInvalidoException {
        return repository.localizaPorNome(nome);
    }

    @Override
    public Usuario cadastra(Usuario usuario) throws LoginInvalidoException, SenhaFracaException {
        if (localizaPorNome(usuario.getNome()).isPresent()) {

        }
        return repository.cadastra(usuario);
    }
}
