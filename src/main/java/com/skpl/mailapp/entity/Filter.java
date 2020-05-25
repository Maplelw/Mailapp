package com.skpl.mailapp.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.coyote.OutputBuffer;

/**
 * (Filter)实体类
 *
 * @author makejava
 * @since 2020-05-22 19:13:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Filter implements Serializable {
    private static final long serialVersionUID = 265144973145319613L;
    
    private Integer fId;
    
    private String fMail;
    
    private String fRefuseMail;
    
    private String fIp;

    public FilterToApp toFilterToApp(Filter filter) {
        return new FilterToApp(filter.fId,filter.fRefuseMail,filter.fIp);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class FilterToApp {
        private Integer filterId;
        private String filterMail;
        private String filterIp;
    }
}