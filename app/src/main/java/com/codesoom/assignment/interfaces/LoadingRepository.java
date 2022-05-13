package com.codesoom.assignment.interfaces;

import com.codesoom.assignment.Task;

import java.util.List;

public interface LoadingRepository {
    List<Task> tasksAll();

    Task taskBy(Long id);

    boolean notPresent(Long id);

    ManipulatingRepository manipulator();
}