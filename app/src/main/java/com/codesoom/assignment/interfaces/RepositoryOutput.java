package com.codesoom.assignment.interfaces;

import com.codesoom.assignment.Task;

public interface RepositoryOutput {

    void save(Task task);

    void update(Long oldTaskId, Task newTask);

    void deleteBy(Long id);


    Task taskSaved();

    Task taskUpdated();
}