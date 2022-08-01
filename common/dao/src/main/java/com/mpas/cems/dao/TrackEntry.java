package com.mpas.cems.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mpas.cems.util.DateProcessor;
import com.mpas.cems.util.TrackAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@SequenceGenerator(name = "seqTrackEntryGen", allocationSize = 1)
@Entity
@Table(name = "TRACK_ENTRY")
public class TrackEntry extends AbstractEntity {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateProcessor.DATE_FORMAT)
    @NotNull
    @Column(name = "track_date", nullable = false)
    @DateTimeFormat(pattern = DateProcessor.DATE_FORMAT)
    protected LocalDateTime date;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "evidence_fk", nullable = false)
    private Evidence evidence;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "detective_fk", nullable = false)
    private Detective detective;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TrackAction action;

    @NotEmpty
    @Column
    private String reason;

    public TrackEntry() {
        super();
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Evidence getEvidence() {
        return evidence;
    }

    public void setEvidence(Evidence evidence) {
        this.evidence = evidence;
    }

    public Detective getDetective() {
        return detective;
    }

    public void setDetective(Detective detective) {
        this.detective = detective;
    }

    public TrackAction getAction() {
        return action;
    }

    public void setAction(TrackAction action) {
        this.action = action;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
