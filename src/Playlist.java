import java.util.*;

public class Playlist {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int code;
        ArrayList<Music> playlist = new ArrayList<>();

        playlist.add(new Music("All of me", "John Legend", 269));
        playlist.add(new Music("Talking to the moon", "Bruno Mars", 217));
        playlist.add(new Music("Skyfall", "Adele", 286));
        playlist.add(new Music("Young and beautiful", "Lana Del Rey", 236));
        playlist.add(new Music("Yellow", "Coldplay", 266));
        playlist.add(new Music("Call out my name", "The Weekend", 228));
        playlist.add(new Music("Fire on fire", "Sam Smith", 246));
        playlist.add(new Music("Believer", "Imagine Dragons",204 ));
        playlist.add(new Music("Apologize", "OneRepublic", 184));
        playlist.add(new Music("Somebody that I used to know", "Gotye, Kimbra", 244));

        printMenu(); //Вместо дублирования кода менюшку я внесла в метод printMenu
        try {
            code = scan.nextInt();
        } catch (InputMismatchException e){
            System.err.println("Недопустимый символ! Попробуйте еще раз.");
            code = 0; //Чует душа моя ревьюер спросит за обработку ошибок разных и исключений. Мне кажется только эту ошибку можно обработать. И программа завершает свою работу после обработки ошибки
        }
        while (code != 0) {
            switch (code) {
                case 1: {
                    scan.nextLine();
                    addToPlaylist(playlist);
                } break;
                case 2: {
                    printPlaylist(playlist); //Метод для отображения плейлиста, также код внесли в отдельный метод, чтобы не было слишком много кода в мейн
                } break;
                case 3: {
                   removeFromPlaylist(playlist);
                } break;
                case 4: {
                //   Collections.shuffle(playlist, new Random()); // перетасовка????????????????
                    shufflePlaylist(playlist);
                } break;
                default: {
                    System.out.println("Неверный ввод, попробуйте еще раз");
                }
                break; //Если я не ошибаюсь, сюда тоже нужен брейк
            }
            printMenu();
            try {
                code = scan.nextInt();
            } catch (InputMismatchException e){
                System.err.println("Недопустимый символ!");
                code = 0; //тут тоже
            }
        }


    }

    public static void printMenu(){
        System.out.println("\n***  MENU  ***" +
                "\n1. Добавить новую песню" +
                "\n2. Отобразить список песен" +
                "\n3. Удалить песню" +
                "\n4. Перетасовать песни " +
                "\n0. Выйти");
    }

    public static void addToPlaylist(ArrayList<Music> playlist){
        Scanner scan = new Scanner(System.in);
        System.out.print("Введите название песни: ");
        String name = scan.nextLine();
        System.out.print("Введите исполнителя песни: ");
        String artist = scan.nextLine();
        System.out.print("Продолжительность песни в секундах: ");
        try {
            Integer duration = scan.nextInt();
            playlist.add(new Music(name, artist, duration));
            System.out.println("Новая песня " + name + " (" + artist + ") успешно добавлена!");
        }catch (InputMismatchException e){
            System.err.println("Недопустимый символ!\nВнимание! Из-за допущенной вами ошибки песня не была добавлена!");
        }


    }
    public static void printPlaylist (ArrayList<Music> playlist){
        int j = 1;
        int totalDuration = 0;
        System.out.println("Список песен: ");
        for (Music i : playlist) {
            System.out.println(j + ". " + i.toString());
            j++;
            totalDuration += i.getDuration();
        }
        System.out.println("Количество песен в плейлисте: " + playlist.size());
        System.out.println("Общая продолжительность песен в плейлисте: " + totalDuration/60 + " min " + totalDuration%60 + " sec");
    }

    public static void removeFromPlaylist (ArrayList<Music> playlist){
        int num;
        System.out.print("Выберите номер песни для удаления: ");
        try {
            num = new Scanner(System.in).nextInt();
            System.out.println("Песня <" + playlist.get(num - 1) + "> успешно удалена!");
            playlist.remove(num - 1);
        } catch (InputMismatchException e){
            System.err.println("Недопустимый символ!\nВнимание! Из-за допущенной вами ошибки песня не была удалена!");
        }
    }
    public static void shufflePlaylist(ArrayList<Music> playlist){
        //как показывал ментор, необязательно создавать объект класса рандом с ссылкой, можно работать с ним с помощью просто ключ слова new
        for (int i = playlist.size() - 1; i > 0; i--) {
            int j = new Random().nextInt(i + 1); //Вот тут видите :>
            Music temp = playlist.get(i);
            playlist.set(i, playlist.get(j));
            playlist.set(j, temp);
        }
        System.out.println("Плейлист перетасован");
    }


}