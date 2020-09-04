package com.carLease.carLease.model;

import javax.persistence.*;

@Entity
public class CreditApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    private Boolean leasingDecisionStatus;
    private String vinNumber;
    private Long personId;
    private Double personIncome;
    private Long coApplicantId;
    private Double coApplicantIncome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isLeasingDecisionStatus() {
        return leasingDecisionStatus;
    }

    public void setLeasingDecisionStatus(Boolean leasingDecisionStatus) {
        this.leasingDecisionStatus = leasingDecisionStatus;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Double getPersonIncome() {
        return personIncome;
    }

    public void setPersonIncome(Double personIncome) {
        this.personIncome = personIncome;
    }

    public Long getCoApplicantId() {
        return coApplicantId;
    }

    public void setCoApplicantId(Long coApplicantId) {
        this.coApplicantId = coApplicantId;
    }

    public Double getCoApplicantIncome() {
        return coApplicantIncome;
    }

    public void setCoApplicantIncome(Double coApplicantIncome) {
        this.coApplicantIncome = coApplicantIncome;
    }
}
