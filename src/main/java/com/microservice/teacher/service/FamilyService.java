package com.microservice.teacher.service;

import com.microservice.teacher.model.dto.FamilyDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FamilyService {
  Mono<FamilyDto> save(FamilyDto familyDto);
  Mono<FamilyDto> update(FamilyDto familyDto, String id);
  Mono<Void> deleteByStudent(String idStudent);
  Flux<FamilyDto> findByStudent(String idStudent);
}
