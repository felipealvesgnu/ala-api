package br.org.ala.api.service;

import br.org.ala.api.model.Associado;
import br.org.ala.api.model.Pessoa;
import br.org.ala.api.repository.AssociadoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AssociadoService {

    @Autowired
    private AssociadoRepository associadoRepository;

    public Page<Associado> listar(Pageable pageable) {
        return associadoRepository.findAll(pageable);
    }

    public List<Associado> listarAtivos() {
        Optional<List<Associado>> allByAtivoFalse = associadoRepository.findByAtivoTrue();

        return allByAtivoFalse.orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public List<Associado> listarInativos() {
        Optional<List<Associado>> associadosInativos = associadoRepository.findByAtivoFalse();

        return associadosInativos.orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public Page<Associado> listarPorNome(String nome, Pageable pageable) {
        return associadoRepository.findByNomeContaining(nome, pageable);
    }

    @Transactional
    public Associado salvar(Associado associado) {
        return associadoRepository.save(associado);
    }

    @Transactional
    public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {
        Associado associadoSalvo = buscarPeloId(id);
        associadoSalvo.setAtivo(ativo);
        associadoRepository.save(associadoSalvo);
    }

    @Transactional
    public void excluir(Long id) {
        try {
            associadoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
    }

    public Associado buscarPeloId(Long id) {
        Optional<Associado> associadoOptional = associadoRepository.findById(id);
        return associadoOptional.orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

}
