package com.polytech.algorithm.Question7;

import com.polytech.Bin;
import com.polytech.Item;

public class Transition {
    private Integer item1_index;
    private Integer item2_index;
    private Integer bin1_index;
    private Integer bin2_index;


    public Transition(Integer index_item1, Integer index_item2, Integer index_bin1, Integer index_bin2) {
        this.item1_index = index_item1;
        this.item2_index = index_item2;
        this.bin1_index = index_bin1;
        this.bin2_index = index_bin2;
    }

    public Transition() {};
    public Transition(Integer index_bin1, Integer index_bin2) {
        this.bin1_index = index_bin1;
        this.bin2_index = index_bin2;
    }


    public Integer getItem1_index() {
        return item1_index;
    }

    public Integer getItem2_index() {
        return item2_index;
    }


    public Integer getBin1_index() {
        return bin1_index;
    }

    public Integer getBin2_index() {
        return bin2_index;
    }


    public void setItem1_index(Integer item1_index) {
        this.item1_index = item1_index;
    }

    public void setItem2_index(Integer item2_index) {
        this.item2_index = item2_index;
    }


    public void setBin1_index(Integer bin1_index) {
        this.bin1_index = bin1_index;
    }

    public void setBin2_index(Integer bin2_index) {
        this.bin2_index = bin2_index;
    }
}
