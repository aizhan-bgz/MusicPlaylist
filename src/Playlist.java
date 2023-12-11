import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Playlist {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        ArrayList<Music> playlist = new ArrayList<>();

        playlist.add(new Music("All of me", "John Legend", 269));
        playlist.add(new Music("Talking to the moon", "Bruno Mars", 217));
        playlist.add(new Music("Skyfall", "Adele", 286));
        playlist.add(new Music("Young and beautiful", "Lana Del Rey", 236));
        playlist.add(new Music("Yellow", "Coldplay", 266));
        playlist.add(new Music("Call out my name", "The Weeknd", 228));
        playlist.add(new Music("Fire on fire", "Sam Smith", 246));
        playlist.add(new Music("Believer", "Imagine Dragons",204 ));
        playlist.add(new Music("Apologize", "OneRepublic", 184));
        playlist.add(new Music("Somebody that I used to know", "Gotye, Kimbra", 244));


        System.out.println("***  MENU  ***" +
                "\n1. Добавить новую песню" +
                "\n2. Отобразить список песен" +
                "\n3. Удалить песню" +
                "\n4. Перетасовать песни " +
                "\n0. Выйти");

        int code = scan.nextInt();
        while (code != 0) {
            switch (code) {
                case 1: {
                    scan.nextLine();
                    System.out.print("Введите название песни: ");
                    String name = scan.nextLine();
                    System.out.print("Введите исполнителя песни: ");
                    String artist = scan.nextLine();
                    System.out.print("Продолжительность песни в секундах: ");
                    Integer duration = scan.nextInt();
                    playlist.add(new Music(name, artist, duration));
                    System.out.println("Новая песня " + name + " (" + artist + ") успешно добавлена!");
                } break;
                case 2: {
                    System.out.println("Список песен: ");
                    int j = 1;
                    int totalDuration = 0;
                    for (Music i : playlist) {
                        System.out.println(j + ". " + i.toString());
                        j++;
                        totalDuration += i.getDuration();
                    }
                    System.out.println("Количество песен в плейлисте: " + playlist.size());
                    System.out.println("Общая продолжительность песен в плейлисте: " + totalDuration/60 + " min " + totalDuration%60 + " sec");
                } break;
                case 3: {
                    System.out.print("Выберите номер песни для удаления: ");
                    int num = scan.nextInt();
                    System.out.println("Песня <" + playlist.get(num - 1) + "> успешно удалена!"); // писать sout до удаления???
                    playlist.remove(num - 1);
                } break;
                case 4: {
                //   Collections.shuffle(playlist, new Random()); // перетасовка????????????????

                    Random random = new Random();
                    for (int i = playlist.size() - 1; i > 0; i--) {
                        int j = random.nextInt(i + 1);

                        Music temp = playlist.get(i);
                        playlist.set(i, playlist.get(j));
                        playlist.set(j, temp);
                    }
                    System.out.println("Плейлист перетасован");
                } break;
                default: System.out.println("Неверный ввод, попробуйте еще раз");
            }
            System.out.println("\n***  MENU  ***" +
                    "\n1. Добавить новую песню" +
                    "\n2. Отобразить список песен" +
                    "\n3. Удалить песню" +
                    "\n4. Перетасовать песни " +
                    "\n0. Выйти");
            code = scan.nextInt();
        }


    }
}