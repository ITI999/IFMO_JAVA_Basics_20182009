package ru.ifmo.cet.javabasics;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.Files.readAllLines;


public class WarAndPeaceExercise {

    public static String warAndPeace() throws IOException {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");

        HashMap<String, Integer> dict = new HashMap<>();

        String data = readAllLines(tome12Path,Charset.forName("windows-1251")).stream().collect(Collectors.joining(" "));
        data += readAllLines(tome34Path,Charset.forName("windows-1251")).stream().collect(Collectors.joining(" "));
        data = data.replaceAll("[^a-zA-Zа-яА-Я]", " ").toLowerCase();
        Stream<String> stringStream = Stream.of(data.split(" "));

        stringStream.forEach(
                (String s) -> {
                    int x = dict.getOrDefault(s,0) + 1;
                    dict.put(s,x);
                }
                );

        List<Map.Entry<String,Integer>> list = new ArrayList<>(dict.entrySet());
        Collections.sort(list, (o1, o2) ->
                (o1.getValue().equals(o2.getValue())) ? o1.getKey().compareTo(o2.getKey()) : o2.getValue().compareTo(o1.getValue()));

        // TODO map lowercased words to its amount in text and concatenate its entries.
        // TODO Iff word "котик" occurred in text 23 times then its entry would be "котик - 23\n".
        // TODO Entries in final String should be also sorted by amount and then in alphabetical order iff needed.
        // TODO Also omit any word with lengths less than 4 and frequency less than 10

        return list.stream().filter(e -> e.getValue() >=10 && e.getKey().length() >=4)
                .map( entry -> entry.getKey() + " - " + entry.getValue())
                .collect(Collectors.joining("\n")
        );
    }

}