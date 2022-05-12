package com.codesoom.assignment.interfaces;

import com.codesoom.assignment.controllers.dtos.*;

public interface ManipulatingController {

    TaskResponseDto create(TaskRequestDto requestDto);

    TaskResponseDto update(Long id, TaskRequestDto requestDto);

    void deleteBy(Long id);

}