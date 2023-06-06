package com.RestAPIs.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Category {
    @Id
    private Long id;
    private  String title;
    private Date created_at;
    private Date updated_at;
    private int clues_count;




}
