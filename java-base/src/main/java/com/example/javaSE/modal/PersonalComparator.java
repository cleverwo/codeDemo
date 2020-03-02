package com.example.javaSE.modal;

import java.util.Comparator;

/**
 * @Auther: 10413
 * @Date: 2020/3/1 19:47
 * @Description:
 */
public class PersonalComparator implements Comparator<Personal> {
    @Override
    public int compare(Personal o1, Personal o2) {
        return o1.age - o2.age;
    }
}
