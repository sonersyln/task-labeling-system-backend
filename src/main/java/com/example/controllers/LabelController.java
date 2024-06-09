package com.example.controllers;

import com.example.core.utilities.results.DataResult;
import com.example.core.utilities.results.Result;
import com.example.services.abstracts.LabelService;
import com.example.services.dtos.requests.labelRequests.AddLabelRequest;
import com.example.services.dtos.requests.labelRequests.UpdateLabelRequest;
import com.example.services.dtos.responses.GetLabelResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/labels")
@AllArgsConstructor
@CrossOrigin
public class LabelController {
    private final LabelService labelService;

    @GetMapping()
    public DataResult<List<GetLabelResponse>> getAllLabels() {
        return this.labelService.getAllLabels();
    }

    @GetMapping("/{id}")
    public DataResult<GetLabelResponse> getById(@PathVariable int id) {
        return this.labelService.getById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Result addLabel(@RequestBody @Valid AddLabelRequest addLabelRequest) {
        return this.labelService.addLabel(addLabelRequest);
    }

    @PutMapping()
    public Result updateLabel(@RequestBody @Valid UpdateLabelRequest updateLabelRequest) {
        return this.labelService.updateLabel(updateLabelRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result deleteLabel(@PathVariable int id) {
        return this.labelService.deleteLabel(id);
    }

    //getAllLabelsByTaskId
    @GetMapping("/getAllLabelsByTaskId/{id}")
    public DataResult<List<GetLabelResponse>> getAllLabelsByTaskId(@PathVariable int id) {
        return this.labelService.getAllLabelsByTaskId(id);
    }

}
