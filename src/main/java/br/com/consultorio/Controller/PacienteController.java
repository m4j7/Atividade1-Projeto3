package br.com.consultorio.Controller;

import br.com.consultorio.Service.MedicoService;
import br.com.consultorio.entity.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/medicos")
public class PacienteController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping("/{idMedico}")
    public ResponseEntity<Medico> getById(@PathVariable("idMedico") Long idMedico)
    {
        return ResponseEntity.ok().body(this.medicoService.findById(idMedico).get());
    }

    @GetMapping
    public ResponseEntity<Page<Medico>> findAll(Pageable pageable)
    {
        return ResponseEntity.ok().body(this.medicoService.listAll(pageable));
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody Medico medico)
    {
        try{
            this.medicoService.insert(medico);
            return ResponseEntity.ok().body(("Medico Cadastro com Sucesso!"));
        }catch(RuntimeException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{idMedico}")
    public ResponseEntity<?> update(@PathVariable("idMedico") Long id,
                                    @RequestBody Medico medico)
    {
        try{
            this.medicoService.update(id, medico);
            return ResponseEntity.ok().body(("Medico Atualizado com Sucesso!"));
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/status/{idMedico}")
    public ResponseEntity<?> updateStatus(@PathVariable("idMedico") Long id,
                                          @RequestBody Medico medico)
    {
        try{
            this.medicoService.update(id, medico);
            return ResponseEntity.ok().body(("Medico Atualizado com Sucesso!"));
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
