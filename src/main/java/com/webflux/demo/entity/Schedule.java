package com.webflux.demo.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Table
@Getter
@Setter
@AllArgsConstructor
@ToString
//@DynamicUpdate spring-boot-starter-data-r2dbc 엔 없나?;;
public class Schedule {
    @Id
    private Long id;
    private LocalDateTime regDate;
    private String title;
    private LocalTime startTime;
    private LocalTime endTime;
    private String writer;
    private Integer alarm;
    private String comment;

    public static Schedule of(ScheduleDto scheduleDto) {
        return new Schedule(scheduleDto.getId(), scheduleDto.getRegDate(),
                scheduleDto.getTitle(), scheduleDto.getStartTime(), scheduleDto.getEndTime(), scheduleDto.getWriter(),
                scheduleDto.getAlarm(), scheduleDto.getComment());
    }
}