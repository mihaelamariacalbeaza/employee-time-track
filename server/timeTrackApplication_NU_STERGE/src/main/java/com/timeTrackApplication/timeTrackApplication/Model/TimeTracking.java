package com.timeTrackApplication.timeTrackApplication.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TimeTrackTable")

public class TimeTracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTimeTrack", nullable = false)
    private Integer idTimeTrack;

    @Column(name = "IdEmployee")

    private Integer idEmployee;

    @Column(name = "punchIn")
    private String punchIn;

    @Column(name = "punchOut")
    private String punchOut;


}
