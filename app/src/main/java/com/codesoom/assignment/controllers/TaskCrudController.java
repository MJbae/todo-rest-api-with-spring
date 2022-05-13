package com.codesoom.assignment.controllers;

import com.codesoom.assignment.Task;
import com.codesoom.assignment.TaskLoadingRepository;
import com.codesoom.assignment.controllers.dtos.TaskRequestDto;
import com.codesoom.assignment.controllers.dtos.TaskResponseDto;
import com.codesoom.assignment.controllers.validations.RequestBodyValidation;
import com.codesoom.assignment.controllers.validations.RequestParamValidation;
import com.codesoom.assignment.interfaces.TaskController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskCrudController implements TaskController {
    private final TaskLoadingRepository repository;

    public TaskCrudController(TaskLoadingRepository repository) {
        this.repository = repository;
    }

    @Override
    @GetMapping
    public List<TaskResponseDto> showAll() {
        return repository.tasksAll().stream()
                .map(TaskResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    @GetMapping("/{id}")
    public TaskResponseDto showBy(@PathVariable Long id) {
        new RequestParamValidation(id, repository).validate();

        return new TaskResponseDto(repository.taskBy(id));
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponseDto create(@RequestBody TaskRequestDto requestDto) {
        new RequestBodyValidation(requestDto).validate();

        Task task = requestDto.toEntity();
        repository.manipulator().save(task);

        return new TaskResponseDto(repository.manipulator().taskSaved());
    }

    @Override
    @RequestMapping(path = "/{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
    public TaskResponseDto update(@PathVariable Long id, @RequestBody TaskRequestDto requestDto) {
        new RequestBodyValidation(requestDto).validate();
        new RequestParamValidation(id, repository).validate();

        Task taskUpdating = new Task(id, requestDto.getTitle());
        repository.manipulator().update(taskUpdating);

        return new TaskResponseDto(repository.manipulator().taskUpdated());
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBy(@PathVariable Long id) {
        new RequestParamValidation(id, repository).validate();

        repository.manipulator().deleteBy(id);
    }
}
