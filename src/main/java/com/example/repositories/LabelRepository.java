package com.example.repositories;

import com.example.models.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LabelRepository extends JpaRepository<Label, Integer> {

    @Query("SELECT l FROM Label l JOIN l.tasks t WHERE t.id = :taskId")
    List<Label> findAllByTasks_Id(@Param("taskId") int taskId);
}
