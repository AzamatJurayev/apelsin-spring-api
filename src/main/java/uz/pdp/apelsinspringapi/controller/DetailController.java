package uz.pdp.apelsinspringapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apelsinspringapi.dto.ApiResponse;
import uz.pdp.apelsinspringapi.dto.DetailDTO;
import uz.pdp.apelsinspringapi.service.DetailService;

@RestController
@RequestMapping("detail")
public class DetailController {

    @Autowired
    DetailService detailService;

    @GetMapping
    public ApiResponse getAll(){
        return detailService.getAll();
    }

    @PostMapping
    public ApiResponse save(@RequestBody DetailDTO detailDTO){
        return detailService.save(detailDTO);
    }

    @GetMapping("/{id}")
    public ApiResponse getOne(@PathVariable Integer id){
        return detailService.getOne(id);
    }

    @PutMapping("/{id}")
    public ApiResponse edit(@PathVariable Integer id,@RequestBody DetailDTO detailDTO){
        return detailService.edit(id,detailDTO);
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        return detailService.delete(id);
    }
}
