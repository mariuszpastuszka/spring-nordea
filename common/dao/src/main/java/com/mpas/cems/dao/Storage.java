package com.mpas.cems.dao;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@SequenceGenerator(name = "seqStorageGen", allocationSize = 1)
@Entity
public class Storage extends AbstractEntity {
    @NotNull
    @Size(min = 8, max = 30)
    private String name;

    @NotNull
    @Size(min = 8, max = 150)
    private String location;

    @OneToMany(mappedBy = "storage")
    private Set<Evidence> evidenceSet = new HashSet<>();

    public Storage() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<Evidence> getEvidenceSet() {
        return evidenceSet;
    }

    public void setEvidenceSet(Set<Evidence> evidenceSetArg) {
        evidenceSetArg.forEach(this::addEvidence);
    }

    public boolean addEvidence(Evidence evidence) {
        evidence.setStorage(this);
        return evidenceSet.add(evidence);
    }

    @Override
    public String toString() {
        return String.format("Storage[id='%d%n', name='%s', location='%s', version='%d%n']",
                id, name, location, version);
    }
}
