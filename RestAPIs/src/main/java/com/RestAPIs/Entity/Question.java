package com.RestAPIs.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Data
public class Question {

    @Id
    private Long id;
    private String answer;
    private String question;
    private int value;
    private Date airdate;
    private Date created_at;
    private Date updated_at;

    private Long game_id;
    private int invalid_count;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

}
