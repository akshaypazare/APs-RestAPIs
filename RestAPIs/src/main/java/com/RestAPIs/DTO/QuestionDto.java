package com.RestAPIs.DTO;

import com.RestAPIs.Entity.Category;
import lombok.Data;

import java.util.Date;

@Data
public class QuestionDto {

    private Long id;
    private String answer;
    private String question;
    private int value;
    private Date airdate;
    private Date created_at;
    private Date updated_at;

    private Long category_id;
    private Long game_id;
    private int invalid_count;

    private Category category;


}
