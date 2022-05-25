package br.com.consultorio.Controller;

import br.com.consultorio.Service.HistoricoService;
import br.com.consultorio.entity.Historico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/historicos")
public class HistoricoController {
    @Autowired
    private HistoricoService historicoService;

    @GetMapping("/{idHistorico}")
    public ResponseEntity<Historico> getById(@PathVariable("idHistorico") Long idHistorico)
    {
        return ResponseEntity.ok().body(this.historicoService.findById(idHistorico).get());
    }

    @GetMapping
    public ResponseEntity<Page<Historico>> findAll(Pageable pageable){
        return ResponseEntity.ok().body(this.historicoService.listAll(pageable));
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody Historico historico)
    {
        try{
            this.historicoService.saveInsertHistorico(historico);
            return ResponseEntity.ok().body(("Historico Cadastrado com Sucesso!"));
        }catch (RuntimeException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
