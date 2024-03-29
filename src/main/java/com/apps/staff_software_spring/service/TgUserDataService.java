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

import com.apps.staff_software_spring.persistence.entity.TgUserDataEntity;
import com.apps.staff_software_spring.persistence.entity.TgUserDataEntity;
import com.apps.staff_software_spring.persistence.repository.TgUserDataRepository;
import com.apps.staff_software_spring.persistence.repository.conjunct.TgUserDataConjunct;
import com.apps.staff_software_spring.persistence.repository.pagesort.TgUserDataPageSort;
import com.apps.staff_software_spring.persistence.repository.query.TgUserDataQuery;
import com.apps.staff_software_spring.service.dto.TgUserDataDto;

@Service
public class TgUserDataService {
    private final TgUserDataRepository tgUserDataRepository;
    private final TgUserDataPageSort tgUserDataPageSort;
    private final TgUserDataConjunct tgUserDataConjunct;
    private final TgUserDataQuery tgUserDataQuery;

    @Autowired
    public TgUserDataService(
        TgUserDataRepository tgUserDataRepository,
        TgUserDataPageSort tgUserDataPageSort,
        TgUserDataConjunct tgUserDataConjunct,
        TgUserDataQuery tgUserDataQuery
    ) {
        this.tgUserDataRepository = tgUserDataRepository;
        this.tgUserDataPageSort = tgUserDataPageSort;
        this.tgUserDataConjunct = tgUserDataConjunct;
        this.tgUserDataQuery = tgUserDataQuery;
    }
    
    public List<TgUserDataEntity> getAll() {
        return this.tgUserDataRepository.findAll();
    }
    
    public List<TgUserDataEntity> getAllLazy() {
        List<TgUserDataEntity> tgUserDataEntitys = this.tgUserDataRepository.findAll();
        tgUserDataEntitys.forEach(
            data -> System.out.println(
                data.getTgRoleData().getCdName()
            )
        );
        return tgUserDataEntitys;
    }

    public TgUserDataEntity getById(int idRegister) {
        return this.tgUserDataRepository.findById(idRegister).orElse(null);
    }

    public List<TgUserDataEntity> getOrderByColumn(String column) {
        List<TgUserDataEntity> response = null;
        if ("idRegister".equals(column)) {
            response = this.tgUserDataRepository.findAllByOrderByIdRegister();
        }
        if ("cdName".equals(column)) {
            response = this.tgUserDataRepository.findAllByOrderByCdName();
        }
        return response;
    }

    public <T> List<TgUserDataEntity> getSearchData(String column, T data) {
        List<TgUserDataEntity> response = null;
        if ("idRegister".equals(column) && data instanceof Integer) {
            response = this.tgUserDataRepository.findAllByIdRegister((Integer) data);
        }
        if ("cdName".equals(column) && data instanceof String) {
            response = this.tgUserDataRepository.findAllByCdNameIgnoreCase((String) data);
        }
        return response;
    }

    public List<TgUserDataEntity> getToday() {
        LocalDateTime today = LocalDate.now().atTime(0,0);
        return this.tgUserDataRepository.findAllByAtCreatedDateAfter(today);
    }

    public Page<TgUserDataEntity> pageAll(int page, int elements) {
        Pageable pageRequest = PageRequest.of(page, elements);
        return this.tgUserDataPageSort.findAll(pageRequest);
    }

    public Page<TgUserDataEntity> pageSortCol(
        int page, int elements, String sortBy, String sortDir
    ) {
        Sort sortData = Sort.by(Sort.Direction.fromString(sortDir), sortBy);
        Pageable pageRequest = PageRequest.of(page, elements, sortData);
        return this.tgUserDataPageSort.findBy(pageRequest);
    }

    public int natCountAll() {
        return this.tgUserDataConjunct.countAll();
    }

    public List<TgUserDataEntity> natIdRegister(String idRegister) {
        return this.tgUserDataConjunct.findIdRegister(idRegister);
    }

    public TgUserDataEntity queryCdName(String cdName) {
        return this.tgUserDataQuery.findByCdName(cdName);
    }

    public TgUserDataEntity save(TgUserDataEntity tgUserDataEntity) {
        return this.tgUserDataRepository.save(tgUserDataEntity);
    }

    @Transactional
    public void updateDto(TgUserDataDto tgUserDataDto) {
        this.tgUserDataConjunct.updateDto(tgUserDataDto);
    }

    public void deleteAll() {
        this.tgUserDataRepository.deleteAll();
    }

    public void deleteAllById(List<Integer> ids) {
        for (Integer id : ids) {
            this.tgUserDataRepository.deleteById(id);
        }
    }

    public void deleteById(int idRegister) {
        this.tgUserDataRepository.deleteById(idRegister);
    }

    public boolean existsById(int idRegister) {
        return this.tgUserDataRepository.existsById(idRegister);
    }
}
