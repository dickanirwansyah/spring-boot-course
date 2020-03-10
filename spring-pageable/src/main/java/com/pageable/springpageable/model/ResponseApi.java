package com.pageable.springpageable.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseApi implements Serializable {

    private Date timestamp;
    private int code;
    private String message;
    private Object data;
}
