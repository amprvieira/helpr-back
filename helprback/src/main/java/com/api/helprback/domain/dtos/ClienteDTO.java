package com.api.helprback.domain.dtos;


import com.api.helprback.domain.Cliente;
import com.api.helprback.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class ClienteDTO implements Serializable {
    private static final long serialVersionUID=1L;

    protected Integer id;
    @NotNull(message = "Nome é obrigatório")
    protected String nome;

    @NotNull( message = "CPF é obrigatório")
    protected String cpf;

    @NotNull(message="Email é obrigatório")
    protected String email;

    @NotNull(message = "Senha é obrigatório")
    protected String senha;

    protected Set<Integer> perfis = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCriacao = LocalDate.now();

    public ClienteDTO() {
        super();
        addPerfis(Perfil.CLIENTE);
    }

      public ClienteDTO(Cliente obj) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = obj.getDataCriacao();
        addPerfis(Perfil.CLIENTE);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(x->Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfis(Perfil perfis){
        this.perfis.add(perfis.getCodigo());
    }

    public LocalDate getDateCriacao() {
        return dataCriacao;
    }

    public void setDateCriacao(LocalDate dateCriacao) {
        this.dataCriacao = dateCriacao;
    }
}
