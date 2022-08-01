package com.mpas.cems.dao;

import com.mpas.cems.util.CaseStatus;
import com.mpas.cems.util.CaseType;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


@SequenceGenerator(name = "seqCriminalCaseGen", allocationSize = 1)
@Entity
@Table(name = "CRIMINAL_CASE")
public class CriminalCase extends AbstractEntity {

    @NotEmpty
    @Column(name = "case_number", unique = true, nullable = false)
    private String number;

    @NotNull
    @Column(name = "case_type")
    @Enumerated(EnumType.STRING)
    private CaseType type;

    @NotEmpty
    @Column(name = "short_description")
    private String shortDescription;

    private String detailedDescription;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CaseStatus status;

    //very big text
    private String notes;

    @OneToMany(mappedBy = "criminalCase", cascade = CascadeType.PERSIST)
    private Set<Evidence> evidenceSet = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "LEAD_INVESTIGATOR", nullable = false)
    private Detective leadInvestigator;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "working_detective_case",
            joinColumns = @JoinColumn(name = "case_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "detective_id", referencedColumnName = "id"))
    private Set<Detective> assigned = new HashSet<>();

    public CriminalCase() {
        super();
        this.status = CaseStatus.SUBMITTED;
        this.type = CaseType.UNCATEGORIZED;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public CaseType getType() {
        return type;
    }

    public void setType(CaseType type) {
        this.type = type;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    public CaseStatus getStatus() {
        return status;
    }

    public void setStatus(CaseStatus status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Set<Evidence> getEvidenceSet() {
        return evidenceSet;
    }

    public void setEvidenceSet(Set<Evidence> evidenceSetArg) {
        evidenceSetArg.forEach(this::addEvidence);
    }

    public boolean addEvidence(Evidence evidence) {
        evidence.setCriminalCase(this);
        return evidenceSet.add(evidence);
    }

    public Detective getLeadInvestigator() {
        return leadInvestigator;
    }

    public void setLeadInvestigator(Detective leadInvestigator) {
        this.leadInvestigator = leadInvestigator;
    }

    public Set<Detective> getAssigned() {
        return assigned;
    }

    public void setAssigned(Set<Detective> assignedArg) {
        assignedArg.forEach(this::addDetective);
    }

    //case is assigned to the detective, always use this method
    public boolean addDetective(Detective detective) {
        detective.addCase(this);
        return assigned.add(detective);
    }
}
