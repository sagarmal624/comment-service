package com.example.commentproducer.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto {
    private String message;
    private Object data;
    private List<Error> errors;

    public static ResponseDto buildSuccess(String message) {
        return ResponseDto.builder().message(message).build();
    }

    public static ResponseDto buildSuccess(String message, Object data) {
        return ResponseDto.builder().message(message).data(data).build();
    }

    public static ResponseDto buildSuccess(Object data) {
        return ResponseDto.builder().data(data).build();
    }

    public static ResponseDto buildError(List<Error> errors) {
        return ResponseDto.builder().errors(errors).build();
    }

    public static ResponseDto buildError(String message, List<Error> errors) {
        return ResponseDto.builder().message(message).errors(errors).build();
    }
}
