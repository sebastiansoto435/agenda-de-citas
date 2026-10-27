package com.agenda.citas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    private final List<Cita> citas = new ArrayList<>();
    private Long contador = 1L;

    // Mostrar todas las citas
    @GetMapping
    public List<Cita> listarCitas() {
        return citas;
    }

    // Buscar cita por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cita> buscarCita(@PathVariable Long id) {

        for (Cita cita : citas) {
            if (cita.getId().equals(id)) {
                return ResponseEntity.ok(cita);
            }
        }

        return ResponseEntity.notFound().build();
    }

    // Agregar cita
    @PostMapping
    public ResponseEntity<Cita> agregarCita(@RequestBody Cita cita) {

        cita.setId(contador++);
        citas.add(cita);

        return ResponseEntity.status(HttpStatus.CREATED).body(cita);
    }

    // Actualizar cita
    @PutMapping("/{id}")
    public ResponseEntity<Cita> actualizarCita(
            @PathVariable Long id,
            @RequestBody Cita datos) {

        for (Cita cita : citas) {

            if (cita.getId().equals(id)) {

                cita.setNombre(datos.getNombre());
                cita.setFecha(datos.getFecha());
                cita.setHora(datos.getHora());
                cita.setMotivo(datos.getMotivo());

                return ResponseEntity.ok(cita);
            }
        }

        return ResponseEntity.notFound().build();
    }

    // Eliminar cita
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCita(@PathVariable Long id) {

        for (Cita cita : citas) {

            if (cita.getId().equals(id)) {
                citas.remove(cita);
                return ResponseEntity.noContent().build();
            }
        }

        return ResponseEntity.notFound().build();
    }
}