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

import com.apps.staff_software_spring.persistence.entity.TgRoleDataEntity;
import com.apps.staff_software_spring.persistence.repository.TgRoleDataRepository;
import com.apps.staff_software_spring.persistence.repository.conjunct.TgRoleDataConjunct;
import com.apps.staff_software_spring.persistence.repository.pagesort.TgRoleDataPageSort;
import com.apps.staff_software_spring.persistence.repository.query.TgRoleDataQuery;
import com.apps.staff_software_spring.service.dto.TgRoleDataDto;

@Service
public class TgRoleDataService {
    private final TgRoleDataRepository tgRoleDataRepository;
    private final TgRoleDataPageSort tgRoleDataPageSort;
    private final TgRoleDataConjunct tgRoleDataConjunct;
    private final TgRoleDataQuery tgRoleDataQuery;

    @Autowired
    public TgRoleDataService(
            TgRoleDataRepository tgRoleDataRepository,
            TgRoleDataPageSort tgRoleDataPageSort,
            TgRoleDataConjunct tgRoleDataConjunct,
            TgRoleDataQuery tgRoleDataQuery
    ) {
        this.tgRoleDataRepository = tgRoleDataRepository;
        this.tgRoleDataPageSort = tgRoleDataPageSort;
        this.tgRoleDataConjunct = tgRoleDataConjunct;
        this.tgRoleDataQuery = tgRoleDataQuery;
    }

    public List<TgRoleDataEntity> getAll() {
        return this.tgRoleDataRepository.findAll();
    }
    
    public List<TgRoleDataEntity> getAllLazy() {
        List<TgRoleDataEntity> tgRoleDataEntitys = this.tgRoleDataRepository.findAll();
        tgRoleDataEntitys.forEach(
            data -> System.out.println(
                data.getTgRoleGroup().getCdName()
            )
        );
        return tgRoleDataEntitys;
    }
    
    public TgRoleDataEntity getById(int idRegister) {
        return this.tgRoleDataRepository.findById(idRegister).orElse(null);
    }

    public List<TgRoleDataEntity> getOrderByColumn(String column) {
        List<TgRoleDataEntity> response = null;
        if ("idRegister".equals(column)) {
            response = this.tgRoleDataRepository.findAllByOrderByIdRegister();
        }
        if ("cdName".equals(column)) {
            response = this.tgRoleDataRepository.findAllByOrderByCdName();
        }
        if ("tgRoleGroup".equals(column)) {
            response = this.tgRoleDataRepository.findAllByOrderByTgRoleGroup();
        }
        return response;
    }

    public <T> List<TgRoleDataEntity> getSearchData(String column, T data) {
        List<TgRoleDataEntity> response = null;
        if ("idRegister".equals(column) && data instanceof Integer) {
            response = this.tgRoleDataRepository.findAllByIdRegister((Integer) data);
        }
        if ("cdName".equals(column) && data instanceof String) {
            response = this.tgRoleDataRepository.findAllByCdNameIgnoreCase((String) data);
        }
        if ("tgRoleGroup".equals(column) && data instanceof Integer) {
            response = this.tgRoleDataRepository.findAllByTgRoleGroup_IdRegister((Integer) data);
        }
        return response;
    }

    public List<TgRoleDataEntity> getAtDateCreate() {
        LocalDateTime today = LocalDate.now().atTime(0,0);
        return this.tgRoleDataRepository.findAllByAdCreatedDateAfter(today);
    }

    public List<TgRoleDataEntity> getAtDateUpdate() {
        LocalDateTime today = LocalDate.now().atTime(0,0);
        return this.tgRoleDataRepository.findAllByAdModifiedDateAfter(today);
    }



    public Page<TgRoleDataEntity> pageAll(int page, int elements) {
        Pageable pageRequest = PageRequest.of(page, elements);
        return this.tgRoleDataPageSort.findAll(pageRequest);
    }

    public Page<TgRoleDataEntity> pageSortCol(
        int page, int elements, String sortBy, String sortDir
    ) {
        Sort sortData = Sort.by(Sort.Direction.fromString(sortDir), sortBy);
        Pageable pageRequest = PageRequest.of(page, elements, sortData);
        return this.tgRoleDataPageSort.findBy(pageRequest);
    }



    public int natCountAll() {
        return this.tgRoleDataConjunct.countAll();
    }

    public List<TgRoleDataEntity> natIdRegister(String idRegister) {
        return this.tgRoleDataConjunct.findIdRegister(idRegister);
    }



    public TgRoleDataEntity queryCdName(String cdName) {
        return this.tgRoleDataQuery.findByCdName(cdName);
    }

    public TgRoleDataEntity queryTgRoleGroup(Integer tgRoleGroup) {
        return this.tgRoleDataQuery.findByTgRoleGroup(tgRoleGroup);
    }



    public TgRoleDataEntity save(TgRoleDataEntity tgRoleDataEntity) {
        return this.tgRoleDataRepository.save(tgRoleDataEntity);
    }

    @Transactional
    public void updateDto(TgRoleDataDto tgRoleDataDto) {
        this.tgRoleDataConjunct.updateDto(tgRoleDataDto);
    }



    public void deleteAll() {
        this.tgRoleDataRepository.deleteAll();
    }

    public void deleteById(int idRegister) {
        this.tgRoleDataRepository.deleteById(idRegister);
    }

    public void deleteByIdAll(List<Integer> ids) {
        for (Integer id : ids) {
            this.tgRoleDataRepository.deleteById(id);
        }
    }



    public boolean existsById(int idRegister) {
        return this.tgRoleDataRepository.existsById(idRegister);
    }
}
