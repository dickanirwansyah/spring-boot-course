package com.pageable.springpageable.commandservice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {

    private Integer code;
    private String status;
    private T data;

    public static <T> Response<T> ok(T data){
        return Response.status(HttpStatus.OK, data);
    }

    public static <T> Response<T> status(HttpStatus status, T data){
        return Response.<T>builder()
                .code(status.value())
                .status(status.getReasonPhrase())
                .data(data)
                .build();
    }
}
