package com.webflux.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webflux.demo.entity.Schedule;
import com.webflux.demo.entity.ScheduleDto;
import com.webflux.demo.repository.ScheduleRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final ScheduleRepository scheduleRepository;

    public Flux<ScheduleDto> retrieveSchedules() {
        return scheduleRepository.findAll().map(ScheduleDto::of);
    }

    public Mono<ScheduleDto> retrieveSchedules(long idx) {
        return scheduleRepository.findById(idx).map(ScheduleDto::of);
    }

    @Transactional
    public Mono<ScheduleDto> saveSchedule(ScheduleDto scheduleDto) {
        return scheduleRepository.save(Schedule.of(scheduleDto)).map(ScheduleDto::of);
    }

    @Transactional
    public Mono<ScheduleDto> updateSchedule(long idx, ScheduleDto scheduleDto) {
        return scheduleRepository.findById(idx).map(schedule -> {
            if (scheduleDto.getTitle() != null)
                schedule.setTitle(scheduleDto.getTitle());
            if (scheduleDto.getStartTime() != null)
                schedule.setStartTime(scheduleDto.getStartTime());
            if (scheduleDto.getEndTime() != null)
                schedule.setEndTime(scheduleDto.getEndTime());
            if (scheduleDto.getComment() != null)
                schedule.setComment(scheduleDto.getComment());
            if (scheduleDto.getAlarm() != null)
                schedule.setAlarm(scheduleDto.getAlarm());
            return schedule;
        }).flatMap(schedule -> scheduleRepository.save(schedule).map(ScheduleDto::of));
    }

    @Transactional
    public Mono<Void> deleteSchedule(long idx) {
        return scheduleRepository.deleteById(idx);
    }
}
