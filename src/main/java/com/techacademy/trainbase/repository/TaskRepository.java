package com.techacademy.trainbase.repository;

import com.techacademy.trainbase.entity.Project;
import com.techacademy.trainbase.entity.Task;
import com.techacademy.trainbase.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByProject(Project project);
    List<Task> findByAssignee(User assignee);
    List<Task> findByStatus(String status);
    List<Task> findByPriority(String priority);
    List<Task> findByProjectAndStatus(Project project, String status);
}
