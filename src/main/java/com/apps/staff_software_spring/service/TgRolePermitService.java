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

import com.apps.staff_software_spring.persistence.entity.TgRolePermitEntity;
import com.apps.staff_software_spring.persistence.repository.TgRolePermitRepository;
import com.apps.staff_software_spring.persistence.repository.conjunct.TgRolePermitConjunct;
import com.apps.staff_software_spring.persistence.repository.pagesort.TgRolePermitPageSort;
import com.apps.staff_software_spring.persistence.repository.query.TgRolePermitQuery;
import com.apps.staff_software_spring.service.dto.TgRolePermitDto;

@Service
public class TgRolePermitService {
    private final TgRolePermitRepository tgRolePermitRepository;
    private final TgRolePermitPageSort tgRolePermitPageSort;
    private final TgRolePermitConjunct tgRolePermitConjunct;
    private final TgRolePermitQuery tgRolePermitQuery;

    @Autowired
    public TgRolePermitService(
            TgRolePermitRepository tgRolePermitRepository,
            TgRolePermitPageSort tgRolePermitPageSort,
            TgRolePermitConjunct tgRolePermitConjunct,
            TgRolePermitQuery tgRolePermitQuery
    ) {
        this.tgRolePermitRepository = tgRolePermitRepository;
        this.tgRolePermitPageSort = tgRolePermitPageSort;
        this.tgRolePermitConjunct = tgRolePermitConjunct;
        this.tgRolePermitQuery = tgRolePermitQuery;
    }

    public List<TgRolePermitEntity> getAll() {
        return this.tgRolePermitRepository.findAll();
    }
    
    public List<TgRolePermitEntity> getAllLazy() {
        List<TgRolePermitEntity> tgRolePermitEntitys = this.tgRolePermitRepository.findAll();
        tgRolePermitEntitys.forEach(
            data -> System.out.println(
                data.getTgRoleAction().getCdName()
            )
        );
        tgRolePermitEntitys.forEach(
            data -> System.out.println(
                data.getTgRoleAuth().getCdName()
            )
        );
        tgRolePermitEntitys.forEach(
            data -> System.out.println(
                data.getTgRoleData().getCdName()
            )
        );
        return tgRolePermitEntitys;
    }
    
    public TgRolePermitEntity getById(int idRegister) {
        return this.tgRolePermitRepository.findById(idRegister).orElse(null);
    }

    public List<TgRolePermitEntity> getOrderByColumn(String column) {
        List<TgRolePermitEntity> response = null;
        if ("idRegister".equals(column)) {
            response = this.tgRolePermitRepository.findAllByOrderByIdRegister();
        }
        if ("tgRoleAction".equals(column)) {
            response = this.tgRolePermitRepository.findAllByOrderByTgRoleAction();
        }
        if ("tgRoleAuth".equals(column)) {
            response = this.tgRolePermitRepository.findAllByOrderByTgRoleAuth();
        }
        if ("tgRoleData".equals(column)) {
            response = this.tgRolePermitRepository.findAllByOrderByTgRoleData();
        }
        return response;
    }

    public <T> List<TgRolePermitEntity> getSearchData(String column, T data) {
        List<TgRolePermitEntity> response = null;
        if ("idRegister".equals(column) && data instanceof Integer) {
            response = this.tgRolePermitRepository.findAllByIdRegister((Integer) data);
        }
        if ("tgRoleAction".equals(column) && data instanceof Integer) {
            response = this.tgRolePermitRepository.findAllByTgRoleAction_IdRegister((Integer) data);
        }
        if ("tgRoleAuth".equals(column) && data instanceof Integer) {
            response = this.tgRolePermitRepository.findAllByTgRoleAuth_IdRegister((Integer) data);
        }
        if ("tgRoleData".equals(column) && data instanceof Integer) {
            response = this.tgRolePermitRepository.findAllByTgRoleData_IdRegister((Integer) data);
        }
        return response;
    }

    public List<TgRolePermitEntity> getAtDateCreate() {
        LocalDateTime today = LocalDate.now().atTime(0,0);
        return this.tgRolePermitRepository.findAllByAtCreatedDateAfter(today);
    }

    public List<TgRolePermitEntity> getAtDateUpdate() {
        LocalDateTime today = LocalDate.now().atTime(0,0);
        return this.tgRolePermitRepository.findAllByAtModifiedDateAfter(today);
    }



    public Page<TgRolePermitEntity> pageAll(int page, int elements) {
        Pageable pageRequest = PageRequest.of(page, elements);
        return this.tgRolePermitPageSort.findAll(pageRequest);
    }

    public Page<TgRolePermitEntity> pageSortCol(
        int page, int elements, String sortBy, String sortDir
    ) {
        Sort sortData = Sort.by(Sort.Direction.fromString(sortDir), sortBy);
        Pageable pageRequest = PageRequest.of(page, elements, sortData);
        return this.tgRolePermitPageSort.findBy(pageRequest);
    }



    public int natCountAll() {
        return this.tgRolePermitConjunct.countAll();
    }

    public List<TgRolePermitEntity> natIdRegister(String idRegister) {
        return this.tgRolePermitConjunct.findIdRegister(idRegister);
    }

    

    public TgRolePermitEntity queryTgRoleAction(Integer tgRoleAction) {
        return this.tgRolePermitQuery.findByTgRoleAction(tgRoleAction);
    }

    public TgRolePermitEntity queryTgRoleAuth(Integer tgRoleAuth) {
        return this.tgRolePermitQuery.findByTgRoleAuth(tgRoleAuth);
    }

    public TgRolePermitEntity queryTgRoleData(Integer tgRoleData) {
        return this.tgRolePermitQuery.findByTgRoleData(tgRoleData);
    }



    public TgRolePermitEntity save(TgRolePermitEntity tgRolePermitEntity) {
        return this.tgRolePermitRepository.save(tgRolePermitEntity);
    }

    @Transactional
    public void updateDto(TgRolePermitDto tgRolePermitDto) {
        this.tgRolePermitConjunct.updateDto(tgRolePermitDto);
    }



    public void deleteAll() {
        this.tgRolePermitRepository.deleteAll();
    }

    public void deleteById(int idRegister) {
        this.tgRolePermitRepository.deleteById(idRegister);
    }

    public void deleteByIdAll(List<Integer> ids) {
        for (Integer id : ids) {
            this.tgRolePermitRepository.deleteById(id);
        }
    }



    public boolean existsById(int idRegister) {
        return this.tgRolePermitRepository.existsById(idRegister);
    }
}
