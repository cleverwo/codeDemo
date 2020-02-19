package com.example.compareTest;

/**
 * @Auther: 10413
 * @Date: 2020/2/19 11:15
 * @Description:
 * Collections.sort()排序详解
 */
public class Test implements Comparable<Test>{
    private String name;
    private Integer order;

    public Test() {}

    public Test(String name, Integer order) {
        this.name = name;
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return this.name+" "+this.order;
    }

    @Override
    public int compareTo(Test o) {
        return this.order.compareTo(o.getOrder());
    }
}
