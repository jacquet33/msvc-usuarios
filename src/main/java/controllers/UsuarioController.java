package controllers;

import models.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.UsuarioService;

import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping you can put based route o first level route with RequestMapping
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @GetMapping
    public List<Usuario> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detail(@PathVariable Long id){
         Optional<Usuario> usuarioOptional = service.findByID(id);
         if (usuarioOptional.isPresent()) {
             return ResponseEntity.ok(usuarioOptional.get());
         }
         return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Usuario usuario){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Usuario usuario, @PathVariable Long id){
        Optional<Usuario> o = service.findByID(id);
        if(o.isPresent()){
            Usuario usuarioDb = o.get();
            usuarioDb.setEmail(usuario.getEmail());
            usuarioDb.setNombre(usuario.getNombre());
            usuarioDb.setPassword(usuario.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(usuarioDb));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Usuario> o = service.findByID(id);
        if(o.isPresent()){
            service.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
