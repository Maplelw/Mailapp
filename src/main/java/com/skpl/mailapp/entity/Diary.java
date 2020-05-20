package com.skpl.mailapp.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * (Diary)实体类
 *
 * @author makejava
 * @since 2020-05-20 21:52:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Diary implements Serializable {
    private static final long serialVersionUID = 173305189457580162L;
    
    private Integer d_id;
    
    private String d_executor;
    
    private String d_execution;
    
    private Date d_time;
    
    private Integer d_state;


}