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

import com.apps.staff_software_spring.persistence.entity.TgRoleAuthEntity;
import com.apps.staff_software_spring.persistence.repository.TgRoleAuthRepository;
import com.apps.staff_software_spring.persistence.repository.conjunct.TgRoleAuthConjunct;
import com.apps.staff_software_spring.persistence.repository.pagesort.TgRoleAuthPageSort;
import com.apps.staff_software_spring.persistence.repository.query.TgRoleAuthQuery;
import com.apps.staff_software_spring.service.dto.TgRoleAuthDto;

@Service
public class TgRoleAuthService {
    private final TgRoleAuthRepository tgRoleAuthRepository;
    private final TgRoleAuthPageSort tgRoleAuthPageSort;
    private final TgRoleAuthConjunct tgRoleAuthConjunct;
    private final TgRoleAuthQuery tgRoleAuthQuery;

    @Autowired
    public TgRoleAuthService(
            TgRoleAuthRepository tgRoleAuthRepository,
            TgRoleAuthPageSort tgRoleAuthPageSort,
            TgRoleAuthConjunct tgRoleAuthConjunct,
            TgRoleAuthQuery tgRoleAuthQuery
    ) {
        this.tgRoleAuthRepository = tgRoleAuthRepository;
        this.tgRoleAuthPageSort = tgRoleAuthPageSort;
        this.tgRoleAuthConjunct = tgRoleAuthConjunct;
        this.tgRoleAuthQuery = tgRoleAuthQuery;
    }

    public List<TgRoleAuthEntity> getAll() {
        return this.tgRoleAuthRepository.findAll();
    }
    
    public TgRoleAuthEntity getById(int idRegister) {
        return this.tgRoleAuthRepository.findById(idRegister).orElse(null);
    }

    public List<TgRoleAuthEntity> getOrderByColumn(String column) {
        List<TgRoleAuthEntity> response = null;
        if ("idRegister".equals(column)) {
            response = this.tgRoleAuthRepository.findAllByOrderByIdRegister();
        }
        if ("cdName".equals(column)) {
            response = this.tgRoleAuthRepository.findAllByOrderByCdName();
        }
        return response;
    }

    public <T> List<TgRoleAuthEntity> getSearchData(String column, T data) {
        List<TgRoleAuthEntity> response = null;
        if ("idRegister".equals(column) && data instanceof Integer) {
            response = this.tgRoleAuthRepository.findAllByIdRegister((Integer) data);
        }
        if ("cdName".equals(column) && data instanceof String) {
            response = this.tgRoleAuthRepository.findAllByCdNameIgnoreCase((String) data);
        }
        return response;
    }

    public List<TgRoleAuthEntity> getAtDateCreate() {
        LocalDateTime today = LocalDate.now().atTime(0,0);
        return this.tgRoleAuthRepository.findAllByAtCreatedDateAfter(today);
    }

    public List<TgRoleAuthEntity> getAtDateUpdate() {
        LocalDateTime today = LocalDate.now().atTime(0,0);
        return this.tgRoleAuthRepository.findAllByAtModifiedDateAfter(today);
    }



    public Page<TgRoleAuthEntity> pageAll(int page, int elements) {
        Pageable pageRequest = PageRequest.of(page, elements);
        return this.tgRoleAuthPageSort.findAll(pageRequest);
    }

    public Page<TgRoleAuthEntity> pageSortCol(
        int page, int elements, String sortBy, String sortDir
    ) {
        Sort sortData = Sort.by(Sort.Direction.fromString(sortDir), sortBy);
        Pageable pageRequest = PageRequest.of(page, elements, sortData);
        return this.tgRoleAuthPageSort.findBy(pageRequest);
    }



    public int natCountAll() {
        return this.tgRoleAuthConjunct.countAll();
    }

    public List<TgRoleAuthEntity> natIdRegister(String idRegister) {
        return this.tgRoleAuthConjunct.findIdRegister(idRegister);
    }



    public TgRoleAuthEntity queryCdName(String cdName) {
        return this.tgRoleAuthQuery.findByCdName(cdName);
    }



    public TgRoleAuthEntity save(TgRoleAuthEntity tgRoleAuthEntity) {
        return this.tgRoleAuthRepository.save(tgRoleAuthEntity);
    }

    @Transactional
    public void updateDto(TgRoleAuthDto tgRoleAuthDto) {
        this.tgRoleAuthConjunct.updateDto(tgRoleAuthDto);
    }



    public void deleteAll() {
        this.tgRoleAuthRepository.deleteAll();
    }

    public void deleteById(int idRegister) {
        this.tgRoleAuthRepository.deleteById(idRegister);
    }

    public void deleteByIdAll(List<Integer> ids) {
        for (Integer id : ids) {
            this.tgRoleAuthRepository.deleteById(id);
        }
    }



    public boolean existsById(int idRegister) {
        return this.tgRoleAuthRepository.existsById(idRegister);
    }
}
