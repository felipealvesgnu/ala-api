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
import org.springframework.transaction.annotation.Transactional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Transactional
    public Pessoa salvar(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @Transactional
    public Pessoa atualizar(Pessoa pessoa) {
        //TODO - finish here
//        Pessoa pessoaSalva = buscarPessoaPeloId(id);
//        pessoaSalva.getEnderecos().clear();
//        pessoaSalva.getEnderecos().addAll(pessoa.getEnderecos());
//        BeanUtils.copyProperties(pessoa, pessoaSalva, "id");
//        return pessoaRepository.save(pessoaSalva);

        return pessoaRepository.save(pessoa);
    }

    @Transactional
    public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {
        Pessoa pessoSalva = buscarPeloId(id);
        pessoSalva.setAtivo(ativo);
        pessoaRepository.save(pessoSalva);
    }

    public List<Pessoa> listar() {
        return pessoaRepository.findAll();
    }

    public Pessoa buscarPeloId(Long id) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(id);
        return pessoaOptional.orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    @Transactional
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
