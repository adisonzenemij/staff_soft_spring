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

import com.apps.staff_software_spring.persistence.entity.TgRoleFuncEntity;
import com.apps.staff_software_spring.persistence.repository.TgRoleFuncRepository;
import com.apps.staff_software_spring.persistence.repository.conjunct.TgRoleFuncConjunct;
import com.apps.staff_software_spring.persistence.repository.pagesort.TgRoleFuncPageSort;
import com.apps.staff_software_spring.persistence.repository.query.TgRoleFuncQuery;
import com.apps.staff_software_spring.service.dto.TgRoleFuncDto;

@Service
public class TgRoleFuncService {
    private final TgRoleFuncRepository tgRoleFuncRepository;
    private final TgRoleFuncPageSort tgRoleFuncPageSort;
    private final TgRoleFuncConjunct tgRoleFuncConjunct;
    private final TgRoleFuncQuery tgRoleFuncQuery;

    @Autowired
    public TgRoleFuncService(
            TgRoleFuncRepository tgRoleFuncRepository,
            TgRoleFuncPageSort tgRoleFuncPageSort,
            TgRoleFuncConjunct tgRoleFuncConjunct,
            TgRoleFuncQuery tgRoleFuncQuery
    ) {
        this.tgRoleFuncRepository = tgRoleFuncRepository;
        this.tgRoleFuncPageSort = tgRoleFuncPageSort;
        this.tgRoleFuncConjunct = tgRoleFuncConjunct;
        this.tgRoleFuncQuery = tgRoleFuncQuery;
    }

    public List<TgRoleFuncEntity> getAll() {
        return this.tgRoleFuncRepository.findAll();
    }
    
    public TgRoleFuncEntity getById(int idRegister) {
        return this.tgRoleFuncRepository.findById(idRegister).orElse(null);
    }

    public List<TgRoleFuncEntity> getOrderByColumn(String column) {
        List<TgRoleFuncEntity> response = null;
        if ("idRegister".equals(column)) {
            response = this.tgRoleFuncRepository.findAllByOrderByIdRegister();
        }
        if ("cdAcronym".equals(column)) {
            response = this.tgRoleFuncRepository.findAllByOrderByCdAcronym();
        }
        if ("cdName".equals(column)) {
            response = this.tgRoleFuncRepository.findAllByOrderByCdName();
        }
        return response;
    }

    public <T> List<TgRoleFuncEntity> getSearchData(String column, T data) {
        List<TgRoleFuncEntity> response = null;
        if ("idRegister".equals(column) && data instanceof Integer) {
            response = this.tgRoleFuncRepository.findAllByIdRegister((Integer) data);
        }
        if ("cdAcronym".equals(column) && data instanceof String) {
            response = this.tgRoleFuncRepository.findAllByCdAcronymIgnoreCase((String) data);
        }
        if ("cdName".equals(column) && data instanceof String) {
            response = this.tgRoleFuncRepository.findAllByCdNameIgnoreCase((String) data);
        }
        return response;
    }

    public List<TgRoleFuncEntity> getAtDateCreate() {
        LocalDateTime today = LocalDate.now().atTime(0,0);
        return this.tgRoleFuncRepository.findAllByAtCreatedDateAfter(today);
    }

    public List<TgRoleFuncEntity> getAtDateUpdate() {
        LocalDateTime today = LocalDate.now().atTime(0,0);
        return this.tgRoleFuncRepository.findAllByAtModifiedDateAfter(today);
    }



    public Page<TgRoleFuncEntity> pageAll(int page, int elements) {
        Pageable pageRequest = PageRequest.of(page, elements);
        return this.tgRoleFuncPageSort.findAll(pageRequest);
    }

    public Page<TgRoleFuncEntity> pageSortCol(
        int page, int elements, String sortBy, String sortDir
    ) {
        Sort sortData = Sort.by(Sort.Direction.fromString(sortDir), sortBy);
        Pageable pageRequest = PageRequest.of(page, elements, sortData);
        return this.tgRoleFuncPageSort.findBy(pageRequest);
    }



    public int natCountAll() {
        return this.tgRoleFuncConjunct.countAll();
    }

    public List<TgRoleFuncEntity> natIdRegister(String idRegister) {
        return this.tgRoleFuncConjunct.findIdRegister(idRegister);
    }



    public TgRoleFuncEntity queryCdAcronym(String cdAcronym) {
        return this.tgRoleFuncQuery.findByCdAcronym(cdAcronym);
    }

    public TgRoleFuncEntity queryCdName(String cdName) {
        return this.tgRoleFuncQuery.findByCdName(cdName);
    }



    public TgRoleFuncEntity save(TgRoleFuncEntity tgRoleFuncEntity) {
        return this.tgRoleFuncRepository.save(tgRoleFuncEntity);
    }

    @Transactional
    public void updateDto(TgRoleFuncDto tgRoleFuncDto) {
        this.tgRoleFuncConjunct.updateDto(tgRoleFuncDto);
    }



    public void deleteAll() {
        this.tgRoleFuncRepository.deleteAll();
    }

    public void deleteAllById(List<Integer> ids) {
        for (Integer id : ids) {
            this.tgRoleFuncRepository.deleteById(id);
        }
    }

    public void deleteById(int idRegister) {
        this.tgRoleFuncRepository.deleteById(idRegister);
    }



    public boolean existsById(int idRegister) {
        return this.tgRoleFuncRepository.existsById(idRegister);
    }
}
