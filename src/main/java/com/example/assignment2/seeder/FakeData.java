package com.example.assignment2.seeder;

import com.example.assignment2.entity.AptechClass;
import com.example.assignment2.repository.AptechClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class FakeData {

    private final AptechClassRepository aptechClassRepository;

    public FakeData(AptechClassRepository clazzRepository) {
        this.aptechClassRepository = clazzRepository;
    }

    @EventListener
    public void appReady(ApplicationReadyEvent event) {

        if(aptechClassRepository.findAll().size() <= 0){
            aptechClassRepository.save(new AptechClass("T1708E"));
            aptechClassRepository.save(new AptechClass("T1709E"));
            aptechClassRepository.save(new AptechClass("T1708M"));
            aptechClassRepository.save(new AptechClass("T1807A"));
            aptechClassRepository.save(new AptechClass("T1711B"));
        }
    }
}
