package br.org.ala.api.service;

import br.org.ala.api.model.Pessoa;
import br.org.ala.api.repository.PessoaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Page<Pessoa> listar(Pageable pageable) {
        return pessoaRepository.findAll(pageable);
    }

    public List<Pessoa> listarAtivos() {
        Optional<List<Pessoa>> allByAtivoFalse = pessoaRepository.findByAtivoTrue();

        return allByAtivoFalse.orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public List<Pessoa> listarInativos() {
        Optional<List<Pessoa>> pessoasInativas = pessoaRepository.findByAtivoFalse();

        return pessoasInativas.orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public Page<Pessoa> listarPorNome(String nome, Pageable pageable) {
        return pessoaRepository.findByNomeContaining(nome, pageable);
    }

    @Transactional
    public Pessoa salvar(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @Transactional
    public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {
        Pessoa pessoSalva = buscarPeloId(id);
        pessoSalva.setAtivo(ativo);
        pessoaRepository.save(pessoSalva);
    }

    @Transactional
    public void excluir(Long id) {
        try {
            pessoaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
    }

    public Pessoa buscarPeloId(Long id) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(id);
        return pessoaOptional.orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

}
