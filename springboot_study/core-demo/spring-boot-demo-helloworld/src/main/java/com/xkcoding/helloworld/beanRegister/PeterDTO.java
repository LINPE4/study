/**
 * Project: spring-boot-demo
 * File created at 2020/7/6 15:29
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.xkcoding.helloworld.beanRegister;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 功能描述
 *
 * @author linlipeng
 * @version 1.0
 * @type PeterDTO
 * @date 2020/7/6 15:29
 */
public class PeterDTO {
    private Long recordId;
    private String name;

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        PeterDTO r1 = new PeterDTO();
        r1.setRecordId(1L);
        r1.setName("aa");
        PeterDTO r2 = new PeterDTO();
        r2.setRecordId(1L);
        r2.setName("bb");
        PeterDTO r3 = new PeterDTO();
        r3.setRecordId(2L);
        r3.setName("cc");

        List<PeterDTO> result = Arrays.asList(r1, r2, r3);
        Map<Long, List<PeterDTO>> financeMap = result.stream().collect(Collectors.toMap(PeterDTO::getRecordId,
            p -> {
                List<PeterDTO> init = new ArrayList<>();
                init.add(p);
                return init;
            },
            (p1, p2) -> {
                p1.addAll(p2);
                return p1;
            }
        ));
        System.out.println(financeMap);
    }
}
