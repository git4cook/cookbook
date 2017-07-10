package com.groupd.cookbook.objects;

import java.util.List;

/**
 * Created by Junwei on 2017/7/10.
 */

public class step implements stepInterface{
    private String st;

    public step (String st){
        this.st = st;
    }

    public String getStepsName(){
        return st;
    }

}
