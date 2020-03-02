package com.example.javaSE.modal;

/**
 * @Auther: 10413
 * @Date: 2020/3/1 19:35
 * @Description:
 */
public class Person implements Comparable<Person>{
    String name;
    int age;
    public Person(String name, int age)
    {
        super();
        this.name = name;
        this.age = age;
    }
    public String getName()
    {
        return name;
    }
    public int getAge()
    {
        return age;
    }

    @Override
    public int compareTo(Person o) {
        return this.age-o.getAge();
    }
}
