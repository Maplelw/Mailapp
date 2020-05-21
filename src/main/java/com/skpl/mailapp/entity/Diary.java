package com.skpl.mailapp.entity;

import java.text.SimpleDateFormat;
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

    public DiaryToWeb toDiaryToWeb(Diary diary) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String resTime = format.format(diary.getD_time());
        return new DiaryToWeb(diary.getD_id(),diary.getD_executor(),diary.getD_execution(),resTime,diary.getD_state());
    }

    public class DiaryToWeb {
        private Integer d_id;

        private String d_executor;

        private String d_execution;

        private String d_time;

        private Integer d_state;

        public DiaryToWeb(Integer d_id, String d_executor, String d_execution, String d_time, Integer d_state) {
            this.d_id = d_id;
            this.d_executor = d_executor;
            this.d_execution = d_execution;
            this.d_time = d_time;
            this.d_state = d_state;
        }

        public Integer getD_id() {
            return d_id;
        }

        public void setD_id(Integer d_id) {
            this.d_id = d_id;
        }

        public String getD_executor() {
            return d_executor;
        }

        public void setD_executor(String d_executor) {
            this.d_executor = d_executor;
        }

        public String getD_execution() {
            return d_execution;
        }

        public void setD_execution(String d_execution) {
            this.d_execution = d_execution;
        }

        public String getD_time() {
            return d_time;
        }

        public void setD_time(String d_time) {
            this.d_time = d_time;
        }

        public Integer getD_state() {
            return d_state;
        }

        public void setD_state(Integer d_state) {
            this.d_state = d_state;
        }
    }
}