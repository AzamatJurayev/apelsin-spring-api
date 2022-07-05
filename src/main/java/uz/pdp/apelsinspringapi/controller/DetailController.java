package uz.pdp.apelsinspringapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apelsinspringapi.dto.ApiResponse;
import uz.pdp.apelsinspringapi.dto.DetailDTO;
import uz.pdp.apelsinspringapi.dto.DetailDTO;
import uz.pdp.apelsinspringapi.service.DetailService;

@RestController
@RequestMapping("detail")
public class DetailController {

    @Autowired
    DetailService detailService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(detailService.getAll());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody DetailDTO detail){
        ApiResponse save = detailService.save(detail);
        return ResponseEntity.status(save.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(save);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id){
        ApiResponse response = detailService.getOne(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Integer id,@RequestBody DetailDTO detail){
        ApiResponse response = detailService.edit(id, detail);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        ApiResponse response = detailService.delete(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }
}
