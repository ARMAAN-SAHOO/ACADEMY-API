package com.armaan.academyapi.dto.update;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AttendanceUpdateDto {
    @NotNull
    private Long attendanceId;
    @NotNull
    private LocalDateTime time; 
    @NotNull
    private Boolean presence;
}