package com.microservice.teacher.controller;

import com.microservice.teacher.component.TeacherConverter;
import com.microservice.teacher.model.dto.TeacherDto;
import com.microservice.teacher.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Api(value="teachers", description="Operations pertaining to teachers")
@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

  @Autowired
  private TeacherService teacherService;

  @ApiOperation(value = "View a list of available teachers", response = TeacherDto.class)
  @GetMapping
  public Mono<ResponseEntity<Flux<TeacherDto>>> findAll(){
    return Mono.just(ResponseEntity
            .ok().contentType(MediaType.APPLICATION_JSON).body(teacherService.findAll()));
  }

  @ApiOperation(value = "View a teacher by ID", response = TeacherDto.class)
  @GetMapping("/{id}")
  public Mono<ResponseEntity<TeacherDto>> finById(@PathVariable String id){
    return teacherService.findById(id)
            .map(teacher -> ResponseEntity
            .ok().contentType(MediaType.APPLICATION_JSON).body(teacher));
  }

  @ApiOperation(value = "Save a teacher", response = TeacherDto.class)
  @PostMapping
  public  Mono<ResponseEntity<TeacherDto>> save(@Valid @RequestBody TeacherDto teacher) {
    return teacherService.create(teacher)
            .map(s -> ResponseEntity
            .created(URI.create("/api/teachers")).contentType(MediaType.APPLICATION_JSON).body(s));
  }

  @ApiOperation(value = "Update a teacher", response = TeacherDto.class)
  @PutMapping("/{id}")
  public Mono<ResponseEntity<TeacherDto>> update(@RequestBody TeacherDto teacher, @PathVariable String id){
    return teacherService.update(teacher, id)
            .map(s -> ResponseEntity
                .created(URI.create("/api/teachers")).contentType(MediaType.APPLICATION_JSON).body(s));
  }

  @ApiOperation(value = "Delete of available teacher", response = Mono.class)
  @DeleteMapping("/{id}")
  public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
    return teacherService.delete(id)
            .flatMap(p -> Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
  }

  @ApiOperation(value = "View a teacher by name", response = TeacherDto.class)
  @GetMapping("/name/{fullName}")
  public Mono<ResponseEntity<Flux<TeacherDto>>> findByFullName(@PathVariable String fullName) {
    return Mono.just(ResponseEntity
            .ok().contentType(MediaType.APPLICATION_JSON)
            .body(teacherService.findByFullNameLikeIgnoreCase(fullName.toUpperCase())));
  }

  @ApiOperation(value = "View a teacher by number document", response = TeacherDto.class)
  @GetMapping("/document/{numberDocument}")
  public Mono<ResponseEntity<TeacherDto>> findByNumberDocument(@PathVariable String numberDocument) {
    return teacherService.findByNumberDocument(numberDocument)
            .map(teacher -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(teacher));
  }

  @ApiOperation(value = "View a teacher by birth date between two dates", response = TeacherDto.class)
  @GetMapping("/birthdate/{dateStart}/{dateEnd}")
  public Mono<ResponseEntity<Flux<TeacherDto>>> findByBirthdate(@PathVariable String dateStart,
                                                                @PathVariable String dateEnd) throws ParseException {
    return Mono.just(ResponseEntity
            .ok().contentType(MediaType.APPLICATION_JSON)
            .body(teacherService.findByBirthdate(new SimpleDateFormat("yyyy-MM-dd").parse(dateStart),
                    new SimpleDateFormat("yyyy-MM-dd").parse(dateEnd))));
  }

  @GetMapping("/institute/{idInstitute}")
  public Mono<ResponseEntity<Flux<TeacherDto>>> findByIdInstitute(@PathVariable String idInstitute)  {
    return Mono.just(ResponseEntity
            .ok().contentType(MediaType.APPLICATION_JSON)
            .body(teacherService.findByIdInstitute(idInstitute)));
  }
}
