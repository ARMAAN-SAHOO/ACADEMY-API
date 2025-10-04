package com.armaan.academyapi.scheduler;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.armaan.academyapi.entity.Exam;
import com.armaan.academyapi.enums.ExamStatus;
import com.armaan.academyapi.repository.ExamRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ExamStatusScheduler {

    private final ExamRepository examRepository;

    @Transactional
    @Scheduled(cron ="0 */60 * * * *")
    public void updateExamStatus(){

        LocalDate date=LocalDate.now();
        LocalTime time=LocalTime.now();

        List<Exam> examsToCheck=examRepository.findByStatus(ExamStatus.SCHEDULED);

        for (Exam exam : examsToCheck) {
            if (isExamFinished(exam, date, time)) {
                exam.setStatus(ExamStatus.COMPLETED);
            }
        }
        if(!examsToCheck.isEmpty()){
            examRepository.saveAll(examsToCheck);
        }
    }

    private boolean isExamFinished(Exam exam,LocalDate today,LocalTime now){
            return exam.getDate().isBefore(today) ||
             (exam.getDate().isEqual(today)&&exam.getEndTime()!=null&&exam.getEndTime().isBefore(now));
    }

}
