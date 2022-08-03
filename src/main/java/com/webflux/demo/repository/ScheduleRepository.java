package com.webflux.demo.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.webflux.demo.entity.Schedule;

import reactor.core.publisher.Flux;

public interface ScheduleRepository extends ReactiveCrudRepository<Schedule, Long> {
}
