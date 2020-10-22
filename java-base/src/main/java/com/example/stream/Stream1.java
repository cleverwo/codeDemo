package com.example.stream;

import lombok.var;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * @Auther: 10413
 * @Date: 2020/10/16 13:52
 * @Description:
 */
public class Stream1 {
    public static void main(String[] args) throws IOException {
        var contents = new String(Files.readAllBytes(Paths.get("E:/a.txt")), StandardCharsets.UTF_8);
        System.out.println(contents);
        // List<String> words = List.of(contents.split(" ")); jdk9
        List<String> words = Arrays.asList(contents.split("\\PL+"));

        long cont = 0;
        for (String w : words){
            if (w.length()>12) cont++;
        }
        System.out.println(cont);
        cont = words.stream().filter(w->w.length()>12).count();
        System.out.println(cont);
    }
}
