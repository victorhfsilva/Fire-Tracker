package com.victor.firetrackerapi.repository;

import com.victor.firetrackerapi.model.Data;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DataRepository extends JpaRepository<Data, Integer>  {

    @Query("SELECT d FROM Data d WHERE d.insertionDateTime > :startDateTime and d.insertionDateTime < :endDateTime")
    List<Data> findDataBetween(@Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime")  LocalDateTime endDateTime);

    @Query("SELECT d FROM Data d ORDER BY d.id DESC LIMIT 1")
    Data getLastInsertedData();

    @Modifying
    @Transactional
    @Query("DELETE FROM Data d WHERE d.insertionDateTime > :startDateTime AND d.insertionDateTime < :endDateTime")
    void deleteDataBetween(@Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime);
}
