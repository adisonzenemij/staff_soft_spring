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

import com.apps.staff_software_spring.persistence.entity.TgRoleActionEntity;
import com.apps.staff_software_spring.persistence.repository.TgRoleActionRepository;
import com.apps.staff_software_spring.persistence.repository.conjunct.TgRoleActionConjunct;
import com.apps.staff_software_spring.persistence.repository.pagesort.TgRoleActionPageSort;
import com.apps.staff_software_spring.persistence.repository.query.TgRoleActionQuery;
import com.apps.staff_software_spring.service.dto.TgRoleActionDto;

@Service
public class TgRoleActionService {
    private final TgRoleActionRepository tgRoleActionRepository;
    private final TgRoleActionPageSort tgRoleActionPageSort;
    private final TgRoleActionConjunct tgRoleActionConjunct;
    private final TgRoleActionQuery tgRoleActionQuery;

    @Autowired
    public TgRoleActionService(
            TgRoleActionRepository tgRoleActionRepository,
            TgRoleActionPageSort tgRoleActionPageSort,
            TgRoleActionConjunct tgRoleActionConjunct,
            TgRoleActionQuery tgRoleActionQuery
    ) {
        this.tgRoleActionRepository = tgRoleActionRepository;
        this.tgRoleActionPageSort = tgRoleActionPageSort;
        this.tgRoleActionConjunct = tgRoleActionConjunct;
        this.tgRoleActionQuery = tgRoleActionQuery;
    }

    public List<TgRoleActionEntity> getAll() {
        return this.tgRoleActionRepository.findAll();
    }
    
    public TgRoleActionEntity getById(int idRegister) {
        return this.tgRoleActionRepository.findById(idRegister).orElse(null);
    }

    public List<TgRoleActionEntity> getOrderByColumn(String column) {
        List<TgRoleActionEntity> response = null;
        if ("idRegister".equals(column)) {
            response = this.tgRoleActionRepository.findAllByOrderByIdRegister();
        }
        if ("cdAcronym".equals(column)) {
            response = this.tgRoleActionRepository.findAllByOrderByCdAcronym();
        }
        if ("cdName".equals(column)) {
            response = this.tgRoleActionRepository.findAllByOrderByCdName();
        }
        if ("tgRoleFunc".equals(column)) {
            response = this.tgRoleActionRepository.findAllByOrderByTgRoleFunc();
        }
        return response;
    }

    public <T> List<TgRoleActionEntity> getSearchData(String column, T data) {
        List<TgRoleActionEntity> response = null;
        if ("idRegister".equals(column) && data instanceof Integer) {
            response = this.tgRoleActionRepository.findAllByIdRegister((Integer) data);
        }
        if ("cdAcronym".equals(column) && data instanceof String) {
            response = this.tgRoleActionRepository.findAllByCdAcronymIgnoreCase((String) data);
        }
        if ("cdName".equals(column) && data instanceof String) {
            response = this.tgRoleActionRepository.findAllByCdNameIgnoreCase((String) data);
        }
        if ("tgRoleFunc".equals(column) && data instanceof Integer) {
            response = this.tgRoleActionRepository.findAllByTgRoleFunc_IdRegister((Integer) data);
        }
        return response;
    }

    public List<TgRoleActionEntity> getAtDateCreate() {
        LocalDateTime today = LocalDate.now().atTime(0,0);
        return this.tgRoleActionRepository.findAllByAdCreatedDateAfter(today);
    }

    public List<TgRoleActionEntity> getAtDateUpdate() {
        LocalDateTime today = LocalDate.now().atTime(0,0);
        return this.tgRoleActionRepository.findAllByAdModifiedDateAfter(today);
    }



    public Page<TgRoleActionEntity> pageAll(int page, int elements) {
        Pageable pageRequest = PageRequest.of(page, elements);
        return this.tgRoleActionPageSort.findAll(pageRequest);
    }

    public Page<TgRoleActionEntity> pageSortCol(
        int page, int elements, String sortBy, String sortDir
    ) {
        Sort sortData = Sort.by(Sort.Direction.fromString(sortDir), sortBy);
        Pageable pageRequest = PageRequest.of(page, elements, sortData);
        return this.tgRoleActionPageSort.findBy(pageRequest);
    }



    public int natCountAll() {
        return this.tgRoleActionConjunct.countAll();
    }

    public List<TgRoleActionEntity> natIdRegister(String idRegister) {
        return this.tgRoleActionConjunct.findIdRegister(idRegister);
    }



    public TgRoleActionEntity queryCdAcronym(String cdAcronym) {
        return this.tgRoleActionQuery.findByCdAcronym(cdAcronym);
    }

    public TgRoleActionEntity queryCdName(String cdName) {
        return this.tgRoleActionQuery.findByCdName(cdName);
    }

    public TgRoleActionEntity queryTgRoleFunc(Integer tgRoleFunc) {
        return this.tgRoleActionQuery.findByTgRoleFunc(tgRoleFunc);
    }



    public TgRoleActionEntity save(TgRoleActionEntity tgRoleActionEntity) {
        return this.tgRoleActionRepository.save(tgRoleActionEntity);
    }

    @Transactional
    public void updateDto(TgRoleActionDto tgRoleActionDto) {
        this.tgRoleActionConjunct.updateDto(tgRoleActionDto);
    }



    public void deleteAll() {
        this.tgRoleActionRepository.deleteAll();
    }

    public void deleteById(int idRegister) {
        this.tgRoleActionRepository.deleteById(idRegister);
    }

    public void deleteByIdAll(List<Integer> ids) {
        for (Integer id : ids) {
            this.tgRoleActionRepository.deleteById(id);
        }
    }



    public boolean existsById(int idRegister) {
        return this.tgRoleActionRepository.existsById(idRegister);
    }
}
