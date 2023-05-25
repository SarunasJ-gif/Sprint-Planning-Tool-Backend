package com.sourcery.sprint.workingday.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "working_day")
public class WorkingDayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "working_day_sequence")
    @SequenceGenerator(name = "working_day_sequence", sequenceName = "working_day_sequence", allocationSize = 1, initialValue = 71)
    private Long id;

    @Column(name = "day")
    @JsonFormat(pattern="yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate day;

    @Column(name = "sprint_id")
    private Long sprintId;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "task_key_value")
    private String taskKeyValue;
}
