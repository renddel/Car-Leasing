package com.carLease.carLease.service;

import com.carLease.carLease.expections.RequestProblemException;
import com.carLease.carLease.model.CreditApplication;
import com.carLease.carLease.repository.CreditApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditApplicationService {

    final double MINIMUM_INCOME_THRESHOLD_IN_EUROS = 600;

    @Autowired
    CreditApplicationRepository creditApplicationRepository;

    public List<CreditApplication> getAll() {
        return creditApplicationRepository.findAll();
    }

    public CreditApplication getOne(Long id) {
        return creditApplicationRepository.findById(id).get();
    }

    public CreditApplication save(CreditApplication creditApplication) {
        if (
                creditApplication.getPersonId() == null
                        || creditApplication.getCoApplicantId() == null
                        && creditApplication.getCoApplicantIncome() != null
                        || creditApplication.getPersonId() == creditApplication.getCoApplicantId()
        ) {
            throw new RequestProblemException("Submitted data was not correct");
        }
        if(creditApplication.getCoApplicantId()!=null && creditApplication.getCoApplicantIncome()==null){
            creditApplication.setCoApplicantIncome(0.0);
        }
        makeDecision(creditApplication);
        return creditApplicationRepository.saveAndFlush(creditApplication);

    }

    public void makeDecision(CreditApplication creditApplication) {
        try {
            double applicantsIncome = creditApplication.getPersonIncome();
            double coApplicantsIncome = creditApplication.getCoApplicantIncome();
            boolean decision = applicantsIncome + coApplicantsIncome >= MINIMUM_INCOME_THRESHOLD_IN_EUROS;
            creditApplication.setLeasingDecisionStatus(decision);
        } catch (Exception e) {
            throw new RequestProblemException("Submitted data was not correct");
        }

    }
}
