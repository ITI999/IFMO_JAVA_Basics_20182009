package ru.ifmo.cet.javabasics;


/**
 * Нужно реализовать констурктор и метод, возвращающий слова песни про бутылки на стене.
 * <p>
 * Слова следующие:
 * <p>
 * 99 bottles of beer on the wall, 99 bottles of beer
 * Take one down, pass it around, 98 bottles of beer
 * 98 bottles of beer on the wall, 98 bottles of beer
 * Take one down, pass it around, 97 bottles of beer
 * 97 bottles of beer on the wall, 97 bottles of beer
 * Take one down, pass it around, 96 bottles of beer
 * 96 bottles of beer on the wall, 96 bottles of beer
 * Take one down, pass it around, 95 bottles of beer
 * 95 bottles of beer on the wall, 95 bottles of beer
 * ...
 * <p>
 * 3 bottles of beer on the wall, 3 bottles of beer
 * Take one down, pass it around, 2 bottles of beer
 * 2 bottles of beer on the wall, 2 bottles of beer
 * Take one down, pass it around, 1 bottles of beer
 * 1 bottle of beer on the wall, 1 bottle of beer
 * Take one down, pass it around, no more bottles of beer on the wall
 * No more bottles of beer on the wall, no more bottles of beer
 * Go to the store and buy some more, 99 bottles of beer on the wall
 * <p>
 * Дело усложняется тем, что текст песни переменный:
 * За раз может быть взято несколько бутылок.
 * Значение передается в качестве параметра конструктора
 * Нужно ограничить возможность взятия бутылок натуральным число не более 99 бутылок за раз.
 */
public class BottleSong {

    private final int bottleTakenAtOnce;

    public BottleSong(int bottleTakenAtOnce) {
        this.bottleTakenAtOnce = bottleTakenAtOnce;
    }

    public String getBottleSongLyrics() {
        if(this.bottleTakenAtOnce <1 || this.bottleTakenAtOnce > 99) throw new IllegalArgumentException();

        String text = "";
        for(int i = 99; i > 0; i -= bottleTakenAtOnce){
            text += i + ((i == 1) ? " bottle" : " bottles") + " of beer on the wall, " + i + ((i  == 1) ? " bottle" : " bottles") + " of beer.\n" +
                    "Take " + ((i > bottleTakenAtOnce)? NumberToWordsConverter.convert(bottleTakenAtOnce) : NumberToWordsConverter.convert(i) )
                    + " down and pass around, " + ((i - bottleTakenAtOnce > 0) ? (i - bottleTakenAtOnce) : "no more")
                    + ((i - bottleTakenAtOnce == 1) ? " bottle" : " bottles") + " of beer on the wall.\n";
        }

        text += "No more bottles of beer on the wall, no more bottles of beer.\n" +
                "Go to the store and buy some more, 99 bottles of beer on the wall.\n";

        return text;

    }
}

class NumberToWordsConverter {

    final private  static String[] units = {"zero","one","two","three","four",
            "five","six","seven","eight","nine","ten",
            "eleven","twelve","thirteen","fourteen","fifteen",
            "sixteen","seventeen","eighteen","nineteen"};
    final private static String[] tens = {"","","twenty","thirty","forty","fifty",
            "sixty","seventy","eighty","ninety"};

    public static String convert(Integer i) {
        //
        if( i < 20)  return units[i];
        return tens[i/10] + ((i % 10 > 0)? " " + convert(i % 10):"");
        }

}