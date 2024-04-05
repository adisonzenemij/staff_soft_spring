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

import com.apps.staff_software_spring.persistence.entity.TgUserEmailEntity;
import com.apps.staff_software_spring.persistence.repository.TgUserEmailRepository;
import com.apps.staff_software_spring.persistence.repository.conjunct.TgUserEmailConjunct;
import com.apps.staff_software_spring.persistence.repository.pagesort.TgUserEmailPageSort;
import com.apps.staff_software_spring.persistence.repository.query.TgUserEmailQuery;
import com.apps.staff_software_spring.service.dto.TgUserEmailDto;

@Service
public class TgUserEmailService {
    private final TgUserEmailRepository tgUserEmailRepository;
    private final TgUserEmailPageSort tgUserEmailPageSort;
    private final TgUserEmailConjunct tgUserEmailConjunct;
    private final TgUserEmailQuery tgUserEmailQuery;

    @Autowired
    public TgUserEmailService(
        TgUserEmailRepository tgUserEmailRepository,
        TgUserEmailPageSort tgUserEmailPageSort,
        TgUserEmailConjunct tgUserEmailConjunct,
        TgUserEmailQuery tgUserEmailQuery
    ) {
        this.tgUserEmailRepository = tgUserEmailRepository;
        this.tgUserEmailPageSort = tgUserEmailPageSort;
        this.tgUserEmailConjunct = tgUserEmailConjunct;
        this.tgUserEmailQuery = tgUserEmailQuery;
    }
    
    public List<TgUserEmailEntity> getAll() {
        return this.tgUserEmailRepository.findAll();
    }
    
    public List<TgUserEmailEntity> getAllLazy() {
        List<TgUserEmailEntity> tgUserEmailEntitys = this.tgUserEmailRepository.findAll();
        tgUserEmailEntitys.forEach(
            data -> System.out.println(
                data.getTgUserData().getCdLogin()
            )
        );
        return tgUserEmailEntitys;
    }

    public TgUserEmailEntity getById(int idRegister) {
        return this.tgUserEmailRepository.findById(idRegister).orElse(null);
    }

    public List<TgUserEmailEntity> getOrderByColumn(String column) {
        List<TgUserEmailEntity> response = null;
        if ("idRegister".equals(column)) {
            response = this.tgUserEmailRepository.findAllByOrderByIdRegister();
        }
        if ("cdExtra".equals(column)) {
            response = this.tgUserEmailRepository.findAllByOrderByCdExtra();
        }
        if ("tgUserData".equals(column)) {
            response = this.tgUserEmailRepository.findAllByOrderByTgUserData();
        }
        return response;
    }

    public <T> List<TgUserEmailEntity> getSearchData(String column, T data) {
        List<TgUserEmailEntity> response = null;
        if ("idRegister".equals(column) && data instanceof Integer) {
            response = this.tgUserEmailRepository.findAllByIdRegister((Integer) data);
        }
        if ("cdExtra".equals(column) && data instanceof String) {
            response = this.tgUserEmailRepository.findAllByCdExtraIgnoreCase((String) data);
        }
        if ("tgUserData".equals(column) && data instanceof Integer) {
            response = this.tgUserEmailRepository.findAllByTgUserData_IdRegister((Integer) data);
        }
        return response;
    }

    public List<TgUserEmailEntity> getAtDateCreate() {
        LocalDateTime today = LocalDate.now().atTime(0,0);
        return this.tgUserEmailRepository.findAllByAtCreatedDateAfter(today);
    }

    public List<TgUserEmailEntity> getAtDateUpdate() {
        LocalDateTime today = LocalDate.now().atTime(0,0);
        return this.tgUserEmailRepository.findAllByAtModifiedDateAfter(today);
    }



    public Page<TgUserEmailEntity> pageAll(int page, int elements) {
        Pageable pageRequest = PageRequest.of(page, elements);
        return this.tgUserEmailPageSort.findAll(pageRequest);
    }

    public Page<TgUserEmailEntity> pageSortCol(
        int page, int elements, String sortBy, String sortDir
    ) {
        Sort sortData = Sort.by(Sort.Direction.fromString(sortDir), sortBy);
        Pageable pageRequest = PageRequest.of(page, elements, sortData);
        return this.tgUserEmailPageSort.findBy(pageRequest);
    }



    public int natCountAll() {
        return this.tgUserEmailConjunct.countAll();
    }

    public List<TgUserEmailEntity> natIdRegister(String idRegister) {
        return this.tgUserEmailConjunct.findIdRegister(idRegister);
    }



    public TgUserEmailEntity queryCdExtra(String cdExtra) {
        return this.tgUserEmailQuery.findByCdExtra(cdExtra);
    }

    public TgUserEmailEntity queryTgUserData(Integer tgUserData) {
        return this.tgUserEmailQuery.findByTgUserData(tgUserData);
    }



    public TgUserEmailEntity save(TgUserEmailEntity tgUserEmailEntity) {
        return this.tgUserEmailRepository.save(tgUserEmailEntity);
    }

    @Transactional
    public void updateDto(TgUserEmailDto tgUserEmailDto) {
        this.tgUserEmailConjunct.updateDto(tgUserEmailDto);
    }



    public void deleteAll() {
        this.tgUserEmailRepository.deleteAll();
    }

    public void deleteById(int idRegister) {
        this.tgUserEmailRepository.deleteById(idRegister);
    }

    public void deleteByIdAll(List<Integer> ids) {
        for (Integer id : ids) {
            this.tgUserEmailRepository.deleteById(id);
        }
    }



    public boolean existsById(int idRegister) {
        return this.tgUserEmailRepository.existsById(idRegister);
    }
}
