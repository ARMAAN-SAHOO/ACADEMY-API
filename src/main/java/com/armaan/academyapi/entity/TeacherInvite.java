package com.armaan.academyapi.entity;
import java.time.Instant;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherInvite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String token; // unique token

    private boolean used;

    private Instant expiryDate;

}
