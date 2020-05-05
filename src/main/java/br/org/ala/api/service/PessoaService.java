package br.org.ala.api.service;

import br.org.ala.api.model.Pessoa;
import br.org.ala.api.repository.PessoaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa salvar(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Pessoa atualizar(Long id, Pessoa pessoa) {
        Pessoa pessoaSalva = buscarPessoaPeloId(id);
        pessoaSalva.getEnderecos().clear();
        pessoaSalva.getEnderecos().addAll(pessoa.getEnderecos());
        BeanUtils.copyProperties(pessoa, pessoaSalva, "id");

        return pessoaRepository.save(pessoaSalva);
    }

    public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {
        Pessoa pessoSalva = buscarPessoaPeloId(id);
        pessoSalva.setAtivo(ativo);
        pessoaRepository.save(pessoSalva);
    }

    private Pessoa buscarPessoaPeloId(Long id) {
        Optional<Pessoa> pessoaSalva = pessoaRepository.findById(id);

        if (pessoaSalva.isPresent()) {
            return pessoaSalva.get();
        }
        throw new EmptyResultDataAccessException(1);
    }

    public List<Pessoa> listar(){
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> buscarPeloId(Long id) {
        return pessoaRepository.findById(id);
    }

    public void excluir(Long id) {
        try {
            pessoaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }

    }

    public Optional<List<Pessoa>> listarPorNome(String nome) {
        return pessoaRepository.findByNomeContaining(nome);
    }
}
