package com.dentaloffice.dto;

import com.dentaloffice.models.Record;
import lombok.Data;

import java.util.List;

@Data
public class RecordResponseDTO {

    private List<Record> content;
    private Integer totalPages;
}
