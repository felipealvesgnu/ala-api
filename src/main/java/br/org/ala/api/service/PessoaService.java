//package br.org.ala.api.service;
//
//import br.org.ala.api.repository.PessoaFisicaRepository;
//import java.util.Optional;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class PessoaService {
//
//    @Autowired
//    private PessoaFisicaRepository pessoaRepository;
//
//    public Pessoa atualizar(Long id, Pessoa pessoa) {
//        Pessoa pessoSalva = buscarPessoaPeloId(id);
//        BeanUtils.copyProperties(pessoa, pessoSalva, "id");
//        return pessoaRepository.save(pessoSalva);
//    }
//
//    public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {
//        Pessoa pessoSalva = buscarPessoaPeloId(id);
//        pessoSalva.setAtivo(ativo);
//        pessoaRepository.save(pessoSalva);
//    }
//
//    private Pessoa buscarPessoaPeloId(Long id) {
//        Optional<Pessoa> pessoaSalva = pessoaRepository.findById(id);
//
//        if (pessoaSalva.isPresent()) {
//            return pessoaSalva.get();
//        }
//        throw new EmptyResultDataAccessException(1);
//    }
//}
