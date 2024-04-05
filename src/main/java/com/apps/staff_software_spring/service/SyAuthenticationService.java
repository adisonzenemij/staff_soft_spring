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

import com.apps.staff_software_spring.persistence.entity.SyAuthenticationEntity;
import com.apps.staff_software_spring.persistence.repository.SyAuthenticationRepository;
import com.apps.staff_software_spring.persistence.repository.conjunct.SyAuthenticationConjunct;
import com.apps.staff_software_spring.persistence.repository.pagesort.SyAuthenticationPageSort;
import com.apps.staff_software_spring.persistence.repository.query.SyAuthenticationQuery;
import com.apps.staff_software_spring.service.dto.SyAuthenticationDto;

@Service
public class SyAuthenticationService {
    private final SyAuthenticationRepository syAuthenticationRepository;
    private final SyAuthenticationPageSort syAuthenticationPageSort;
    private final SyAuthenticationConjunct syAuthenticationConjunct;
    private final SyAuthenticationQuery syAuthenticationQuery;

    @Autowired
    public SyAuthenticationService(
            SyAuthenticationRepository syAuthenticationRepository,
            SyAuthenticationPageSort syAuthenticationPageSort,
            SyAuthenticationConjunct syAuthenticationConjunct,
            SyAuthenticationQuery syAuthenticationQuery
    ) {
        this.syAuthenticationRepository = syAuthenticationRepository;
        this.syAuthenticationPageSort = syAuthenticationPageSort;
        this.syAuthenticationConjunct = syAuthenticationConjunct;
        this.syAuthenticationQuery = syAuthenticationQuery;
    }

    public List<SyAuthenticationEntity> getAll() {
        return this.syAuthenticationRepository.findAll();
    }
    
    public SyAuthenticationEntity getById(int idRegister) {
        return this.syAuthenticationRepository.findById(idRegister).orElse(null);
    }

    public List<SyAuthenticationEntity> getOrderByColumn(String column) {
        List<SyAuthenticationEntity> response = null;
        if ("idRegister".equals(column)) {
            response = this.syAuthenticationRepository.findAllByOrderByIdRegister();
        }
        if ("cdName".equals(column)) {
            response = this.syAuthenticationRepository.findAllByOrderByCdName();
        }
        return response;
    }

    public <T> List<SyAuthenticationEntity> getSearchData(String column, T data) {
        List<SyAuthenticationEntity> response = null;
        if ("idRegister".equals(column) && data instanceof Integer) {
            response = this.syAuthenticationRepository.findAllByIdRegister((Integer) data);
        }
        if ("cdName".equals(column) && data instanceof String) {
            response = this.syAuthenticationRepository.findAllByCdNameIgnoreCase((String) data);
        }
        return response;
    }

    public List<SyAuthenticationEntity> getAtDateCreate() {
        LocalDateTime today = LocalDate.now().atTime(0,0);
        return this.syAuthenticationRepository.findAllByAtCreatedDateAfter(today);
    }

    public List<SyAuthenticationEntity> getAtDateUpdate() {
        LocalDateTime today = LocalDate.now().atTime(0,0);
        return this.syAuthenticationRepository.findAllByAtModifiedDateAfter(today);
    }



    public Page<SyAuthenticationEntity> pageAll(int page, int elements) {
        Pageable pageRequest = PageRequest.of(page, elements);
        return this.syAuthenticationPageSort.findAll(pageRequest);
    }

    public Page<SyAuthenticationEntity> pageSortCol(
        int page, int elements, String sortBy, String sortDir
    ) {
        Sort sortData = Sort.by(Sort.Direction.fromString(sortDir), sortBy);
        Pageable pageRequest = PageRequest.of(page, elements, sortData);
        return this.syAuthenticationPageSort.findBy(pageRequest);
    }



    public int natCountAll() {
        return this.syAuthenticationConjunct.countAll();
    }

    public List<SyAuthenticationEntity> natIdRegister(String idRegister) {
        return this.syAuthenticationConjunct.findIdRegister(idRegister);
    }

    

    public SyAuthenticationEntity queryCdName(String cdName) {
        return this.syAuthenticationQuery.findByCdName(cdName);
    }



    public SyAuthenticationEntity save(SyAuthenticationEntity syAuthenticationEntity) {
        return this.syAuthenticationRepository.save(syAuthenticationEntity);
    }

    @Transactional
    public void updateDto(SyAuthenticationDto syAuthenticationDto) {
        this.syAuthenticationConjunct.updateDto(syAuthenticationDto);
    }



    public void deleteAll() {
        this.syAuthenticationRepository.deleteAll();
    }

    public void deleteAllById(List<Integer> ids) {
        for (Integer id : ids) {
            this.syAuthenticationRepository.deleteById(id);
        }
    }

    public void deleteById(int idRegister) {
        this.syAuthenticationRepository.deleteById(idRegister);
    }



    public boolean existsById(int idRegister) {
        return this.syAuthenticationRepository.existsById(idRegister);
    }
}
