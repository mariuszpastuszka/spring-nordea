package com.mpas.cems.dao;

import com.mpas.cems.util.EmploymentStatus;
import com.mpas.cems.util.Rank;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@SequenceGenerator(name = "seqDetectiveGen", allocationSize = 1)
@Entity
public class Detective extends AbstractEntity {

    @NotNull
    @OneToOne
    @JoinColumn(name = "PERSON_ID")
    private Person person;

    @NotEmpty
    @Column(unique = true, nullable = false)
    private String badgeNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Rank rank;

    private Boolean armed = false;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EmploymentStatus status = EmploymentStatus.ACTIVE;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "working_detective_case",
            joinColumns = @JoinColumn(name = "detective_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "case_id", referencedColumnName = "id"))
    private Set<CriminalCase> criminalCases = new HashSet<>();

    @OneToMany(mappedBy = "detective")
    private Set<TrackEntry> trackEntries;

    public Detective() {
        super();
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getBadgeNumber() {
        return badgeNumber;
    }

    public void setBadgeNumber(String badgeNumber) {
        this.badgeNumber = badgeNumber;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Boolean getArmed() {
        return armed;
    }

    public void setArmed(Boolean armed) {
        this.armed = armed;
    }

    public EmploymentStatus getStatus() {
        return status;
    }

    public void setStatus(EmploymentStatus status) {
        this.status = status;
    }

    public Set<CriminalCase> getCriminalCases() {
        return criminalCases;
    }

    private void setCriminalCases(Set<CriminalCase> criminalCases) {
        this.criminalCases = criminalCases;
    }

    boolean addCase(CriminalCase criminalCase) {
        return criminalCases.add(criminalCase);
    }

    public Set<TrackEntry> getTrackEntries() {
        return trackEntries;
    }

    private void setTrackEntries(Set<TrackEntry> trackEntries) {
        this.trackEntries = trackEntries;
    }

    public boolean addTrackEntry(TrackEntry trackEntry) {
        trackEntry.setDetective(this);
        return trackEntries.add(trackEntry);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        var detective = (Detective) o;
        return Objects.equals(person, detective.person) &&
                Objects.equals(badgeNumber, detective.badgeNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), person, badgeNumber);
    }

    @Override
    public String toString() {
        return String.format("Detective\n\t[person='%s', badgeNumber='%s', rank='%s', armed='%s', status='%s']",
                person.toString(), badgeNumber, rank, armed, status);
    }
}
