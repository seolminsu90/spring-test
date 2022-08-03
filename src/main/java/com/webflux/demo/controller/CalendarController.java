package com.webflux.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webflux.demo.entity.ScheduleDto;
import com.webflux.demo.service.CalendarService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/calendar")
@RequiredArgsConstructor
public class CalendarController {
    private final CalendarService calendarService;

    @GetMapping("/schedules")
    public Flux<ScheduleDto> getSchedules() {

        return calendarService.retrieveSchedules();
    }

    @GetMapping("/schedules/{idx}")
    public Mono<ScheduleDto> getSchedulesOne(@PathVariable long idx) {
        return calendarService.retrieveSchedules(idx);
    }

    @PostMapping("/schedules")
    public Mono<ScheduleDto> setSchedule(@RequestBody ScheduleDto scheduleDto) {
        return calendarService.saveSchedule(scheduleDto);
    }

    @PutMapping("/schedules/{idx}")
    public Mono<ScheduleDto> updateSchedule(@PathVariable long idx, @RequestBody ScheduleDto scheduleDto) {
        return calendarService.updateSchedule(idx, scheduleDto);
    }

    @DeleteMapping("/schedules/{idx}")
    public Mono<Void> deleteSchedule(@PathVariable long idx) {
        return calendarService.deleteSchedule(idx);
    }
}