import java.util.*;

public class Playlist {
    private static boolean isSubscribed = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

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

        do {
            System.out.println("\nВыберите действие:");
            System.out.println("1. Добавить песню");
            System.out.println("2. Удалить песню");
            System.out.println("3. Перетасовать плейлист");
            System.out.println("4. Отсортировать плейлист по названию");
            System.out.println("5. Показать плейлист и общую продолжительность");
            System.out.println("0. Выйти");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (choice) {
                case 1:
                    if (isSubscribed || askForSubscription()) {
                        addMusic(playlist, scanner);
                    }
                    break;
                case 2:
                    removeMusic(playlist, scanner);
                    break;
                case 3:
                    shufflePlaylist(playlist);
                    break;
                case 4:
                    sortPlaylistByName(playlist);
                    break;
                case 5:
                    displayMusicAndTotalDuration(playlist);
                    break;
                case 0:
                    System.out.println("Выход из музыкального плейлиста.");
                    System.exit(0);
                default:
                    System.out.println("Некорректный выбор. Попробуйте снова.");
                    break;
            }
        } while (true);
    }


    private static void addMusic(ArrayList<Music> playlist, Scanner scanner) {
        System.out.println("Введите название песни:");
        String title = scanner.nextLine();
        System.out.println("Введите исполнителя:");
        String artist = scanner.nextLine();
        System.out.println("Введите продолжительность песни (в секундах):");
        int duration = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        Music music = new Music(title, artist, duration);
        playlist.add(music);
        System.out.println("Песня добавлена в плейлист: " + music.getTitle());
    }

    private static void removeMusic(ArrayList<Music> playlist, Scanner scanner) {
        System.out.println("Введите название песни для удаления:");
        String titleToRemove = scanner.nextLine();

        playlist.removeIf(music -> music.getTitle().equalsIgnoreCase(titleToRemove));
        System.out.println("Песня удалена из плейлиста: " + titleToRemove);
    }

    private static void shufflePlaylist(ArrayList<Music> playlist) {
        Collections.shuffle(playlist);
        System.out.println("Плейлист перемешан.");
    }

    private static void sortPlaylistByName(ArrayList<Music> playlist) {
        Collections.sort(playlist, (music1, music2) -> music1.getTitle().compareToIgnoreCase(music2.getTitle()));
        System.out.println("Плейлист отсортирован по названию песни.");
    }

    private static boolean displayMusicAndTotalDuration(ArrayList<Music> playlist) {
        System.out.println("Текущий плейлист:");
        for (Music music : playlist) {
            System.out.println(music);
        }

        int totalDurationInSeconds = playlist.stream().mapToInt(Music::getDuration).sum();

        int minutes = totalDurationInSeconds / 60;
        int seconds = totalDurationInSeconds % 60;
        System.out.println("Общая продолжительность плейлиста: " + minutes + " минут " + seconds + " секунд");

        return false;
    }


    public static boolean askForSubscription() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Для добавления песни вам нужно подписаться на нашу услугу за 5$ в месяц.");
        System.out.println("Хотите подписаться? (Да / Нет");

        String reaction = scanner.nextLine();
        while (!"да".equals(reaction) && !"нет".equals(reaction)) {
            System.out.println("Некорректный ввод. Пожалуйста, введите Да или Нет.");
            reaction = scanner.nextLine();
        }
            if ("да".equals(reaction)) {
                System.out.println("Спасибо за подписку! Теперь вы можете добавлять песни без рекламы.");
                isSubscribed = true;
                return true;
            } else {
                System.out.println("Вы отказались от подписки. Реклама будет отображаться при добавлении песен.");
                return false;


            }
        }

    }





