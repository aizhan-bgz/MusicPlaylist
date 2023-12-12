import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
        playlist.add(new Music("Believer", "Imagine Dragons", 204));
        playlist.add(new Music("Apologize", "OneRepublic", 184));
        playlist.add(new Music("Somebody that I used to know", "Gotye, Kimbra", 244));

        printMenu();
        try {
            code = scan.nextInt();
        } catch (InputMismatchException e){
            System.err.println("Недопустимый символ! Попробуйте еще раз запустить программу.");
            code = 0;
        }
        while (code != 0) {
            switch (code) {
                case 1: {
                    scan.nextLine();
                    addToPlaylist(playlist);
                } break;
                case 2: {
                    if (playlist.size() == 0){
                        System.out.println("Плейлист пуст");
                    } else {
                        printPlaylist(playlist);
                    }
                } break;
                case 3: {
                    if (playlist.size() == 0){
                        System.out.println("Плейлист пуст, вы не можете удалить");
                    } else {
                    removeFromPlaylist(playlist);
                    }
                } break;
                case 4: {
                    if (playlist.size() == 0){
                        System.out.println("Плейлист пуст, вы не можете перетасовать");
                    } else {
                        shufflePlaylist(playlist);
                    }
                } break;
                case 5: {
                    if (playlist.size() == 0){
                        System.out.println("Плейлист пуст, вы не можете отсортировать");
                    } else {
                        sortPlaylistByName(playlist);
                    }
                } break;
                case 6: {
                    if (playlist.size() == 0){
                        System.out.println("Плейлист пуст, вы не можете найти песню");
                    } else {
                        findMusicByTitle(playlist);
                    }
                }  break;
                case 7: {
                    if (playlist.size() == 0){
                        System.out.println("Плейлист пуст, вы не можете запустить плейлист");
                    } else {
                        startPlaylist(playlist);
                    }
                } break;
                case 8: {
                    askForSubscription();
                } break;
                default: {
                    System.out.println("Неверный ввод, попробуйте еще раз");
                }
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
                "\n5. Отсортировать плейлист по названию " +
                "\n6. Найти песню по названию " +
                "\n7. Запустить плейлист " +
                "\n8. Купить подписку " +
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
            checkNumber(duration);
            playlist.add(new Music(name, artist, duration));
            System.out.println("Новая песня " + name + " (" + artist + ") успешно добавлена!");
        }catch (InputMismatchException e){
            System.err.println("Недопустимый символ!\nВнимание! Из-за допущенной вами ошибки песня не была добавлена!");
        } catch (NegativeNumberException e2){
            System.err.println("Вы ввели отрицательное число!");
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
        } catch (IndexOutOfBoundsException e2){
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

    public static void startPlaylist(ArrayList<Music> playlist){
        /*
         * Прошу ко вниманию! Этот метод просто имитирует запуск песен из плейлиста
         */
        int index = 0;
        System.out.println("Перед тем, как плейлист запустится, выберите порядок проигрывания песен: \n<1 - по умолчанию> <2 - вразброс>");
        try {
            int num = new Scanner(System.in).nextInt();
            if (num == 1){
                while (index < playlist.size()){
                    playMusic(playlist, index);
                    index++;
                }
            } else if (num == 2) {
                shufflePlaylist(playlist); //Воспроизведение музыки вразброс
                while (index < playlist.size()){
                    playMusic(playlist, index);
                    index++;
                }
            } else {
                System.out.println("Неверная команда. Вы должны были ввести 1 или 2.");
            }
        } catch (InputMismatchException e){
            System.err.println("Недопустимый символ!\nВнимание! Из-за допущенной вами ошибки плейлист не был запущен!");
        }
    }

    //1) Метод вспомогательный к startPlaylist
    public static void printMusic(Music music){
        System.out.println("Играет " + music + " ...");
    }

    //2) Еще один вспомогательный метод во избежание дублирования кода
    public static void playMusic(ArrayList<Music> playlist, int index){
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.schedule(() -> printMusic(playlist.get(index)), 2, TimeUnit.SECONDS);
        try {
            scheduledExecutorService.awaitTermination(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        scheduledExecutorService.shutdown();
    }

    public static boolean askForSubscription() {
        boolean isSubscribed = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Для прослушивания песен без рекламы вам нужно подписаться на нашу услугу за 5$ в месяц.");
        System.out.println("Хотите подписаться? (Да / Нет");

        String reaction = scanner.nextLine();
        while (!"да".equals(reaction) && !"нет".equals(reaction)) {
            System.out.println("Некорректный ввод. Пожалуйста, введите Да или Нет.");
            reaction = scanner.nextLine();
        }
            if ("да".equals(reaction)) {
                System.out.println("Спасибо за подписку! Теперь вы можете слушать песни без рекламы.");
                isSubscribed = true;
                return true;
            } else {
                System.out.println("Вы отказались от подписки. Реклама будет отображаться при прослушивании песен.");
                return false;
            }
        }

    public static void findMusicByTitle(ArrayList<Music> playlist) {
        System.out.println("Введите название песни для поиска:");
        String titleToFind = new Scanner(System.in).nextLine();

        boolean found = false;
        for (Music music : playlist) {
            if (music.getTitle().equalsIgnoreCase(titleToFind)) {
                System.out.println("Найдена песня:");
                System.out.println(music);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Песня с названием '" + titleToFind +  "' не найдена в плейлисте.");
        }
    }

    public static void sortPlaylistByName(ArrayList<Music> playlist) {
        Collections.sort(playlist, (music1, music2) -> music1.getTitle().compareToIgnoreCase(music2.getTitle()));
        System.out.println("Плейлист отсортирован по названию песни(A-Z).");
    }

    public static void checkNumber(int number) throws NegativeNumberException {
        if (number <  0) throw new NegativeNumberException("[number < 0]");
    }

}





