package com.springrest.springrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.springrest.springrest.entities.Load;
import com.springrest.springrest.services.LoadService;

@RestController
public class MyController {

    @Autowired
    private LoadService loadService;

    @GetMapping("/load")
    public List<Load> getLoad() {
        return this.loadService.getLoad();
    }

    @GetMapping("/load/{loadId}")
    public Load getLoads(@PathVariable String loadId) {
        return this.loadService.getLoads(Long.parseLong(loadId));
    }

    @PostMapping("/load")
    public Load addLoad(@RequestBody Load load) {
        return this.loadService.addLoad(load);
    }

    @PutMapping("/load/{loadId}")
    public Load updateLoad(@PathVariable String loadId, @RequestBody Load load) {
        return this.loadService.updateLoad(Long.parseLong(loadId), load);
    }

    @DeleteMapping("/load/{loadId}")
    public ResponseEntity<HttpStatus> deleteLoad(@PathVariable String loadId) {
        try {
            this.loadService.deleteLoad(Long.parseLong(loadId));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
