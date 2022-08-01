
package com.mpas.cems.dj.services.wrappers;

import com.mpas.cems.dao.Detective;
import com.mpas.cems.util.CaseStatus;
import com.mpas.cems.util.EmploymentStatus;
import com.mpas.cems.util.Rank;

import java.util.Set;


public class DetectiveWrapper {

    private Long id;
    private String name;
    private String badgeNumber;
    private Rank rank;
    private Boolean armed;
    private EmploymentStatus status;
    private Long casesResolved;
    private Long casesInProgress;

    private boolean empty = true;

    public DetectiveWrapper() {
    }

    public DetectiveWrapper(Detective detective) {
        this.id = detective.getId();
        this.name = detective.getPerson().getFirstName().concat( detective.getPerson().getLastName());
        this.badgeNumber = detective.getBadgeNumber();
        this.rank = detective.getRank();
        this.armed = detective.getArmed();
        this.status = detective.getStatus();
        this.casesResolved = detective.getCriminalCases().stream().filter(cc -> cc.getStatus() == CaseStatus.CLOSED).count();
        this.casesInProgress = detective.getCriminalCases().stream().filter(cc -> Set.of(CaseStatus.UNDER_INVESTIGATION, CaseStatus.IN_COURT, CaseStatus.COLD).contains(cc.getStatus())).count();
        this.empty = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Long getCasesResolved() {
        return casesResolved;
    }

    public Long getCasesInProgress() {
        return casesInProgress;
    }

    public boolean isEmpty() {
        return empty;
    }
}
