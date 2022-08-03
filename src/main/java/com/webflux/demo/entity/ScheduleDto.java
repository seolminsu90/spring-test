package com.webflux.demo.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDto {
	private Long id;
	private LocalDateTime regDate;
	private String title;
	private LocalTime startTime;
	private LocalTime endTime;
	private String writer;
	private Integer alarm;
	private String comment;

	public static ScheduleDto of(Schedule schedule) {
		return new ScheduleDto(schedule.getId(), schedule.getRegDate(),
				schedule.getTitle(), schedule.getStartTime(), schedule.getEndTime(), schedule.getWriter(),
				schedule.getAlarm(), schedule.getComment());
	}
}
