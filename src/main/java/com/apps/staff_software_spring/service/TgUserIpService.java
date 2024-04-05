package com.apps.staff_software_spring.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apps.staff_software_spring.persistence.entity.TgUserIpEntity;
import com.apps.staff_software_spring.persistence.repository.TgUserIpRepository;
import com.apps.staff_software_spring.persistence.repository.conjunct.TgUserIpConjunct;
import com.apps.staff_software_spring.persistence.repository.pagesort.TgUserIpPageSort;
import com.apps.staff_software_spring.persistence.repository.query.TgUserIpQuery;
import com.apps.staff_software_spring.service.dto.TgUserIpDto;

@Service
public class TgUserIpService {
    private final TgUserIpRepository tgUserIpRepository;
    private final TgUserIpPageSort tgUserIpPageSort;
    private final TgUserIpConjunct tgUserIpConjunct;
    private final TgUserIpQuery tgUserIpQuery;

    @Autowired
    public TgUserIpService(
        TgUserIpRepository tgUserIpRepository,
        TgUserIpPageSort tgUserIpPageSort,
        TgUserIpConjunct tgUserIpConjunct,
        TgUserIpQuery tgUserIpQuery
    ) {
        this.tgUserIpRepository = tgUserIpRepository;
        this.tgUserIpPageSort = tgUserIpPageSort;
        this.tgUserIpConjunct = tgUserIpConjunct;
        this.tgUserIpQuery = tgUserIpQuery;
    }
    
    public List<TgUserIpEntity> getAll() {
        return this.tgUserIpRepository.findAll();
    }
    
    public List<TgUserIpEntity> getAllLazy() {
        List<TgUserIpEntity> tgUserIpEntitys = this.tgUserIpRepository.findAll();
        tgUserIpEntitys.forEach(
            data -> System.out.println(
                data.getTgUserData().getCdLogin()
            )
        );
        return tgUserIpEntitys;
    }

    public TgUserIpEntity getById(int idRegister) {
        return this.tgUserIpRepository.findById(idRegister).orElse(null);
    }

    public List<TgUserIpEntity> getOrderByColumn(String column) {
        List<TgUserIpEntity> response = null;
        if ("idRegister".equals(column)) {
            response = this.tgUserIpRepository.findAllByOrderByIdRegister();
        }
        if ("cdAddress".equals(column)) {
            response = this.tgUserIpRepository.findAllByOrderByCdAddress();
        }
        if ("tgUserData".equals(column)) {
            response = this.tgUserIpRepository.findAllByOrderByTgUserData();
        }
        return response;
    }

    public <T> List<TgUserIpEntity> getSearchData(String column, T data) {
        List<TgUserIpEntity> response = null;
        if ("idRegister".equals(column) && data instanceof Integer) {
            response = this.tgUserIpRepository.findAllByIdRegister((Integer) data);
        }
        if ("cdAddress".equals(column) && data instanceof String) {
            response = this.tgUserIpRepository.findAllByCdAddressIgnoreCase((String) data);
        }
        if ("tgUserData".equals(column) && data instanceof Integer) {
            response = this.tgUserIpRepository.findAllByTgUserData_IdRegister((Integer) data);
        }
        return response;
    }

    public List<TgUserIpEntity> getAtDateCreate() {
        LocalDateTime today = LocalDate.now().atTime(0,0);
        return this.tgUserIpRepository.findAllByAtCreatedDateAfter(today);
    }

    public List<TgUserIpEntity> getAtDateUpdate() {
        LocalDateTime today = LocalDate.now().atTime(0,0);
        return this.tgUserIpRepository.findAllByAtModifiedDateAfter(today);
    }



    public Page<TgUserIpEntity> pageAll(int page, int elements) {
        Pageable pageRequest = PageRequest.of(page, elements);
        return this.tgUserIpPageSort.findAll(pageRequest);
    }

    public Page<TgUserIpEntity> pageSortCol(
        int page, int elements, String sortBy, String sortDir
    ) {
        Sort sortData = Sort.by(Sort.Direction.fromString(sortDir), sortBy);
        Pageable pageRequest = PageRequest.of(page, elements, sortData);
        return this.tgUserIpPageSort.findBy(pageRequest);
    }



    public int natCountAll() {
        return this.tgUserIpConjunct.countAll();
    }

    public List<TgUserIpEntity> natIdRegister(String idRegister) {
        return this.tgUserIpConjunct.findIdRegister(idRegister);
    }



    public TgUserIpEntity queryCdAddress(String dAddress) {
        return this.tgUserIpQuery.findByCdAddress(dAddress);
    }

    public TgUserIpEntity queryTgUserData(Integer tgUserData) {
        return this.tgUserIpQuery.findByTgUserData(tgUserData);
    }



    public TgUserIpEntity save(TgUserIpEntity tgUserIpEntity) {
        return this.tgUserIpRepository.save(tgUserIpEntity);
    }

    @Transactional
    public void updateDto(TgUserIpDto tgUserIpDto) {
        this.tgUserIpConjunct.updateDto(tgUserIpDto);
    }



    public void deleteAll() {
        this.tgUserIpRepository.deleteAll();
    }

    public void deleteById(int idRegister) {
        this.tgUserIpRepository.deleteById(idRegister);
    }

    public void deleteByIdAll(List<Integer> ids) {
        for (Integer id : ids) {
            this.tgUserIpRepository.deleteById(id);
        }
    }



    public boolean existsById(int idRegister) {
        return this.tgUserIpRepository.existsById(idRegister);
    }
}
