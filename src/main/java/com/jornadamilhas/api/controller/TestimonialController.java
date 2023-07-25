package com.jornadamilhas.api.controller;

import com.jornadamilhas.api.model.testimonial.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping
public class TestimonialController
{
    private static final String DEFAULT_REQUEST_PATH = "depoimentos";
    private static final int RANDOM_TESTIMONIALS_QUANTITY = 3;
    final private TestimonialRepository repository;

    public TestimonialController(TestimonialRepository testimonialRepository)
    {
        this.repository = testimonialRepository;
    }

    @PostMapping(DEFAULT_REQUEST_PATH)
    @Transactional
    public ResponseEntity postTestimonial(@RequestBody @Valid TestimonialPostData testimonialPostData, UriComponentsBuilder uriComponentsBuilder)
    {
        Testimonial testimonial = new Testimonial(testimonialPostData);
        repository.save(testimonial);
        return ResponseEntity.created(uriComponentsBuilder.path("/depoimentos/{id}").buildAndExpand(testimonial.getId()).toUri()).body(new TestimonialGetData(testimonial));
    }

    @GetMapping(DEFAULT_REQUEST_PATH + "/{id}")
    public ResponseEntity getDetailedTestimonial(@PathVariable Long id)
    {
        if(repository.getReferenceById(id).isActive())
            return ResponseEntity.ok(new TestimonialGetData(repository.getReferenceById(id)));
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping(DEFAULT_REQUEST_PATH + "-home")
    public ResponseEntity<List<TestimonialGetData>> getRandomTestimonials()
    {
        List testimonialDataList = new ArrayList<>(repository.findAll().stream().filter(testimonial -> testimonial.isActive()).map(TestimonialGetData::new).toList());
        List randomSelectedTestimonialDataList = new ArrayList<TestimonialGetData>();
        Random randomizer = new Random();

        for(int counter = 0; counter < RANDOM_TESTIMONIALS_QUANTITY && counter < testimonialDataList.size(); counter++)
        {
            int randomIndex = randomizer.nextInt(testimonialDataList.size());
            randomSelectedTestimonialDataList.add(testimonialDataList.get(randomIndex));
            testimonialDataList.remove(randomIndex);
        }

        return ResponseEntity.ok(randomSelectedTestimonialDataList);
    }

    @PutMapping(DEFAULT_REQUEST_PATH)
    @Transactional
    public ResponseEntity putTestimonial(@RequestBody @Valid TestimonialPutData testimonialPutData)
    {
        Testimonial testimonial = repository.getReferenceById(testimonialPutData.id());
        testimonial.dataUpdate(testimonialPutData);
        return ResponseEntity.ok(new TestimonialGetData(testimonial));
    }

    @DeleteMapping(DEFAULT_REQUEST_PATH + "/{id}")
    @Transactional
    public ResponseEntity deleteTestimonial(@PathVariable Long id)
    {
        repository.getReferenceById(id).delete();
        return ResponseEntity.noContent().build();
    }
}