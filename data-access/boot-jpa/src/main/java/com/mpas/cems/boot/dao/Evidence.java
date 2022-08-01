
package com.mpas.cems.boot.dao;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;
import java.util.Set;


@Entity
public class Evidence extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "case_fk", nullable = false)
    private CriminalCase criminalCase;

    @ManyToOne
    @JoinColumn(name = "storage_fk", nullable = false)
    private Storage storage;

    @NotEmpty
    @Column(name= "evidence_number", unique = true, nullable = false)
    private String number;

    @NotEmpty
    private String itemName;

    //very big text
    private String notes;

    private Boolean archived = false;

    @OneToMany(mappedBy = "evidence")
    private Set<TrackEntry> trackEntries;

    public CriminalCase getCriminalCase() {
        return criminalCase;
    }

    public void setCriminalCase(CriminalCase criminalCase) {
        this.criminalCase = criminalCase;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    private void setTrackEntries(Set<TrackEntry> trackEntries) {
        this.trackEntries = trackEntries;
    }

    public boolean addTrackEntry(TrackEntry trackEntry){
        trackEntry.setEvidence(this);
        return trackEntries.add(trackEntry);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        var evidence = (Evidence) o;
        return Objects.equals(criminalCase, evidence.criminalCase) &&
                Objects.equals(number, evidence.number) &&
                Objects.equals(itemName, evidence.itemName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), criminalCase, number, itemName);
    }
}
