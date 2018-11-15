package com.justl.utils;

import java.util.List;

/**
 * 封装list返回值
 */
public class ListUtils {
    private int total;
    private List rows;

    public  ListUtils(int total,List rows){
        this.total=total;
        this.rows=rows;
    }
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Object getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
