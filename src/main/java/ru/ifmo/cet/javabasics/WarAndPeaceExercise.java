package ru.ifmo.cet.javabasics;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.nio.file.Files.readAllLines;


public class WarAndPeaceExercise {

    public static String warAndPeace() throws IOException{
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");

        HashMap<String, Integer> dict = new HashMap<>();


        for(String cur : readAllLines(tome12Path, Charset.forName("windows-1251")) ){
            cur = cur.replaceAll("[^a-zA-Zа-яА-Я]", " ");
            for(String word : cur.split(" ")){
                word = word.toLowerCase();
                if(dict.containsKey(word))
                    dict.replace(word, dict.get(word) + 1);
                else
                    dict.put(word, 1);
            }
        }

        for(String cur : readAllLines(tome34Path, Charset.forName("windows-1251"))){
            cur = cur.replaceAll("[^a-zA-Zа-яА-Я]", " ");
            for(String word : cur.split(" ")){
                word = word.toLowerCase();
                if(dict.containsKey(word))
                    dict.replace(word, dict.get(word) + 1);
                else
                    dict.put(word, 1);
            }
        }

        String text = "";

        List<Map.Entry<String,Integer>> list = new ArrayList<>(dict.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if(o1.getValue().equals(o2.getValue()))
                    return o1.getKey().compareTo(o2.getKey());
                else
                    return o2.getValue().compareTo(o1.getValue());
            }
        });

        for(Map.Entry<String,Integer> entry : list){
            if(entry.getKey().length() >=4 && entry.getValue() >= 10)
                text += entry.getKey() + " - " + entry.getValue() + "\n";
        }

        text = text.substring(0,text.length()-1);

        // TODO map lowercased words to its amount in text and concatenate its entries.
        // TODO If word "котик" occurred in text 23 times then its entry would be "котик - 23\n".
        // TODO Entries in final String should be also sorted by amount and then in alphabetical order if needed.
        // TODO Also omit any word with lengths less than 4 and frequency less than 10

        return text;
    }

}
