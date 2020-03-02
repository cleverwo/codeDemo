package com.example.javaSE;

import com.example.javaSE.modal.Person;
import com.example.javaSE.modal.Personal;
import com.example.javaSE.modal.PersonalComparator;

import java.util.Arrays;

/**
 * @Auther: 10413
 * @Date: 2020/3/1 19:29
 * @Description: Comparable 和 Comparator 接口的区别
 */
public class ComparaTest {

    //comparable 接口使用演示
    public void comparableTest() {
        Person[] people = new Person[]{
                new Person("xujian", 20),
                new Person("xiewei", 10)
        };
        System.out.println("排序前");
        for (Person person : people) {
            System.out.print(person.getName() + ":" + person.getAge());
        }
        Arrays.sort(people);
        System.out.println("\n排序后");
        for (Person person : people) {
            System.out.print(person.getName() + ":" + person.getAge());
        }
    }

    public void comparatorTest() {
        Personal[] people = new Personal[]{
                new Personal("xujian", 20),
                new Personal("xiewei", 10)
        };
        System.out.println("排序前");
        for (Personal person : people) {
            System.out.print(person.getName() + ":" + person.getAge());
        }
        Arrays.sort(people,new PersonalComparator());
        System.out.println("\n排序后");
        for (Personal person : people) {
            System.out.print(person.getName() + ":" + person.getAge());
        }
    }

    public static void main(String[] args) {
        ComparaTest t = new ComparaTest();
        t.comparatorTest();
    }
}
