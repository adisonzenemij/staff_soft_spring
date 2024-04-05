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

import com.apps.staff_software_spring.persistence.entity.TgRoleGroupEntity;
import com.apps.staff_software_spring.persistence.repository.TgRoleGroupRepository;
import com.apps.staff_software_spring.persistence.repository.conjunct.TgRoleGroupConjunct;
import com.apps.staff_software_spring.persistence.repository.pagesort.TgRoleGroupPageSort;
import com.apps.staff_software_spring.persistence.repository.query.TgRoleGroupQuery;
import com.apps.staff_software_spring.service.dto.TgRoleGroupDto;

@Service
public class TgRoleGroupService {
    private final TgRoleGroupRepository tgRoleGroupRepository;
    private final TgRoleGroupPageSort tgRoleGroupPageSort;
    private final TgRoleGroupConjunct tgRoleGroupConjunct;
    private final TgRoleGroupQuery tgRoleGroupQuery;

    @Autowired
    public TgRoleGroupService(
            TgRoleGroupRepository tgRoleGroupRepository,
            TgRoleGroupPageSort tgRoleGroupPageSort,
            TgRoleGroupConjunct tgRoleGroupConjunct,
            TgRoleGroupQuery tgRoleGroupQuery
    ) {
        this.tgRoleGroupRepository = tgRoleGroupRepository;
        this.tgRoleGroupPageSort = tgRoleGroupPageSort;
        this.tgRoleGroupConjunct = tgRoleGroupConjunct;
        this.tgRoleGroupQuery = tgRoleGroupQuery;
    }

    public List<TgRoleGroupEntity> getAll() {
        return this.tgRoleGroupRepository.findAll();
    }
    
    public TgRoleGroupEntity getById(int idRegister) {
        return this.tgRoleGroupRepository.findById(idRegister).orElse(null);
    }

    public List<TgRoleGroupEntity> getOrderByColumn(String column) {
        List<TgRoleGroupEntity> response = null;
        if ("idRegister".equals(column)) {
            response = this.tgRoleGroupRepository.findAllByOrderByIdRegister();
        }
        if ("cdName".equals(column)) {
            response = this.tgRoleGroupRepository.findAllByOrderByCdName();
        }
        return response;
    }

    public <T> List<TgRoleGroupEntity> getSearchData(String column, T data) {
        List<TgRoleGroupEntity> response = null;
        if ("idRegister".equals(column) && data instanceof Integer) {
            response = this.tgRoleGroupRepository.findAllByIdRegister((Integer) data);
        }
        if ("cdName".equals(column) && data instanceof String) {
            response = this.tgRoleGroupRepository.findAllByCdNameIgnoreCase((String) data);
        }
        return response;
    }

    public List<TgRoleGroupEntity> getAtDateCreate() {
        LocalDateTime today = LocalDate.now().atTime(0,0);
        return this.tgRoleGroupRepository.findAllByAtCreatedDateAfter(today);
    }

    public List<TgRoleGroupEntity> getAtDateUpdate() {
        LocalDateTime today = LocalDate.now().atTime(0,0);
        return this.tgRoleGroupRepository.findAllByAtModifiedDateAfter(today);
    }



    public Page<TgRoleGroupEntity> pageAll(int page, int elements) {
        Pageable pageRequest = PageRequest.of(page, elements);
        return this.tgRoleGroupPageSort.findAll(pageRequest);
    }

    public Page<TgRoleGroupEntity> pageSortCol(
        int page, int elements, String sortBy, String sortDir
    ) {
        Sort sortData = Sort.by(Sort.Direction.fromString(sortDir), sortBy);
        Pageable pageRequest = PageRequest.of(page, elements, sortData);
        return this.tgRoleGroupPageSort.findBy(pageRequest);
    }



    public int natCountAll() {
        return this.tgRoleGroupConjunct.countAll();
    }

    public List<TgRoleGroupEntity> natIdRegister(String idRegister) {
        return this.tgRoleGroupConjunct.findIdRegister(idRegister);
    }

    

    public TgRoleGroupEntity queryCdName(String cdName) {
        return this.tgRoleGroupQuery.findByCdName(cdName);
    }



    public TgRoleGroupEntity save(TgRoleGroupEntity tgRoleGroupEntity) {
        return this.tgRoleGroupRepository.save(tgRoleGroupEntity);
    }

    @Transactional
    public void updateDto(TgRoleGroupDto tgRoleGroupDto) {
        this.tgRoleGroupConjunct.updateDto(tgRoleGroupDto);
    }



    public void deleteAll() {
        this.tgRoleGroupRepository.deleteAll();
    }

    public void deleteAllById(List<Integer> ids) {
        for (Integer id : ids) {
            this.tgRoleGroupRepository.deleteById(id);
        }
    }

    public void deleteById(int idRegister) {
        this.tgRoleGroupRepository.deleteById(idRegister);
    }



    public boolean existsById(int idRegister) {
        return this.tgRoleGroupRepository.existsById(idRegister);
    }
}
