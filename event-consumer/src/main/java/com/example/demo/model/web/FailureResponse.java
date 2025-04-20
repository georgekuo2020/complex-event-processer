package com.example.demo.model.web;


import com.example.demo.enums.WebError;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class FailureResponse extends BaseResponse {

    public FailureResponse(Enum<WebError> errorEnum) {
        this.statusCode = WebError.fromOrdinal(errorEnum.ordinal()).getCode();
        this.message = WebError.fromOrdinal(errorEnum.ordinal()).getMessage();
    }
}
