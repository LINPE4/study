package com.example.demo.pattern.template;

import java.sql.ResultSet;

/**
 * Created by Tom on 2018/3/11.
 */
public interface RowMapper<T> {

    public T mapRow(ResultSet rs, int rowNum) throws Exception;

}
