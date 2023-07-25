package com.jornadamilhas.api.controller;

import com.jornadamilhas.api.model.destination.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("destinos")
public class DestinationController
{
    final private DestinationRepository repository;

    public DestinationController(DestinationRepository destinationRepository)
    {
        this.repository = destinationRepository;
    }

    @PostMapping
    public ResponseEntity postDestination(@RequestBody @Valid DestinationPostData destinationPostData, UriComponentsBuilder uriComponentsBuilder)
    {
        Destination destination = new Destination(destinationPostData);
        repository.save(destination);
        return ResponseEntity.created(uriComponentsBuilder.path("/destinos/{id}").buildAndExpand(destination.getId()).toUri()).body(new DestinationGetData(destination));
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<DestinationGetData>> getDestinations(@RequestParam String name)
    {
        return ResponseEntity.ok(repository.findAll().stream().filter(destination -> destination.getName().equals(name) && destination.isActive() == true).map(DestinationGetData::new).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity getDetailedDestination(@PathVariable Long id)
    {
        if(repository.getReferenceById(id).isActive())
            return ResponseEntity.ok(new DestinationGetData(repository.getReferenceById(id)));
        else
            return ResponseEntity.notFound().build();
    }

    @PutMapping()
    @Transactional
    public ResponseEntity putDestination(@RequestBody @Valid DestinationPutData destinationPutData)
    {
        Destination destination = repository.getReferenceById(destinationPutData.id());
        destination.dataUpdate(destinationPutData);
        return ResponseEntity.ok(new DestinationGetData(destination));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteDestination(@PathVariable Long id)
    {
        repository.getReferenceById(id).delete();
        return ResponseEntity.noContent().build();
    }
}
