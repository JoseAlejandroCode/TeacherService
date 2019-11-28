package com.microservice.teacher.repository;

import com.microservice.teacher.model.document.Teacher;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface TeacherRepository extends ReactiveMongoRepository<Teacher, String> {
   Flux<Teacher> findByIdInstitute(String idInstitute);
   Flux<Teacher> findByFullNameIgnoreCaseLike(String fullName);
   Mono<Teacher> findByNumberDocument(String numberDocument);
   Flux<Teacher> findByBirthdateBetween(Date dateStart, Date dateEnd);
}
