package com.webflux.demo.controller;

import com.webflux.demo.SpringWebfluxApplicationTests;
import com.webflux.demo.entity.ScheduleDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

// webflux 관련 테스트 참조
// https://docs.spring.io/spring-security/site/docs/5.2.11.RELEASE/reference/html/test-webflux.html
@Slf4j
class CalendarControllerTest extends SpringWebfluxApplicationTests {

    @Autowired
    private CalendarController calendarController;

    @Test
    void getSchedules() {
        log.info(" ::::: getSchedules ::::: ");
        Mono<Long> cnt = calendarController.getSchedules()
                .count();

        long count = cnt.block();
        log.info("CalendarCount {}", count);
    }

    @Test
    void getSchedulesOne() {
        log.info(" ::::: getSchedulesOne ::::: ");
        long idx = 1L;
        calendarController.getSchedulesOne(idx).subscribe();
    }

    @Test
        //@Transactional("transactionManager")
        // https://github.com/spring-projects/spring-framework/issues/24226 아직 안되나본데? 음..못찾겠따 아직
    void setSchedule() {
        log.info(" ::::: setSchedule ::::: ");

        ScheduleDto dto = new ScheduleDto();

        dto.setRegDate(LocalDateTime.now());
        dto.setTitle("Test Title");
        dto.setStartTime(LocalTime.now());
        dto.setEndTime(LocalTime.now());
        dto.setWriter("TestWriter");
        dto.setAlarm(12);
        dto.setComment("Test Comment");

        calendarController.setSchedule(dto)
                .log()
                .block();
    }

    @Test
        //@Transactional("transactionManager")
    void updateSchedule() {
        log.info(" ::::: updateSchedule ::::: ");

        long updateIdx = 1;
        final String updateTitle = "Update Title";

        ScheduleDto scheduleDTO = calendarController.getSchedulesOne(updateIdx)
                .flatMap(dt -> {
                    dt.setTitle(updateTitle);
                    return calendarController.updateSchedule(dt.getId(), dt);
                }).block();

        log.info("scheduleDTO.getTitle() {}", scheduleDTO.getTitle());
        log.info("updateTitle {}", updateTitle);

        assertEquals(scheduleDTO.getTitle(), updateTitle);
    }

    @Test
        //@Transactional("transactionManager")
    void deleteSchedule() {
        log.info(" ::::: deleteSchedule ::::: ");
        long deleteIdx = 4L;

        ScheduleDto result = calendarController.deleteSchedule(deleteIdx)
                .then(calendarController.getSchedulesOne(deleteIdx))
                .block();

        log.info("result {} ", result);
        assertEquals(result, null);
    }
}