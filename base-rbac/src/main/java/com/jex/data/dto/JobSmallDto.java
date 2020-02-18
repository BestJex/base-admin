package com.jex.data.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
* @author Jex
*/
@Data
@NoArgsConstructor
public class JobSmallDto implements Serializable {

    private Long id;

    private String name;
}