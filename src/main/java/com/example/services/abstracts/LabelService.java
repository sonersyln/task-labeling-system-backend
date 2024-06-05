package com.example.services.abstracts;

import com.example.core.utilities.results.DataResult;
import com.example.core.utilities.results.Result;
import com.example.services.dtos.requests.AddLabelRequest;
import com.example.services.dtos.requests.UpdateLabelRequest;
import com.example.services.dtos.responses.GetLabelResponse;

import java.util.List;

public interface LabelService {
    DataResult<List<GetLabelResponse>> getAllLabels();
    DataResult<GetLabelResponse> getById(int id);

    Result addLabel(AddLabelRequest addLabelRequest);

    Result updateLabel(UpdateLabelRequest updateLabelRequest);

    Result deleteLabel(int id);
}
