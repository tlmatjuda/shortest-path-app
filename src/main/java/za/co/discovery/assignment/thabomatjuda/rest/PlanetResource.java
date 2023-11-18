package za.co.discovery.assignment.thabomatjuda.rest;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import za.co.discovery.assignment.thabomatjuda.model.BaseResponse;

@RestController
@RequestMapping("/planets")
public class PlanetResource {

    @GetMapping
    public ResponseEntity<Object> fetchAll() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
