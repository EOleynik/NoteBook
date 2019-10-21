import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Methods {

    /**
     *Read file
     * @throws FileNotFoundException
     */
    public static void readFile() throws FileNotFoundException {
        File file = new File("notebook.txt");
        Scanner scan = new Scanner(file);
        StringBuilder sb = new StringBuilder();
        while (scan.hasNextLine()) {
            sb.append(scan.nextLine() + "\n");
        }
        System.out.println(sb);
        scan.close();
    }


    /**
     *Add record to file
     */
    public static void addingPerson() {
        System.out.println("Для добавления записи введите: фамилию телефон дату рождения");
        System.out.println("Для выхода нажмите 0");
            File file = new File("notebook.txt");
            try {
                PrintWriter writer = new PrintWriter(new FileWriter(file, true));
                Scanner scan = new Scanner(System.in);
                while (true) {
                    String string = scan.nextLine();
                    if (string.equals("0")) {
                        break;
                    }
                    try {
                        boolean result = string.split(" ")[2].matches
                                ("([0-9]{2})-([0-9]{2})-([0-9]{4})");
                        if (result == true) {
                            writer.write(string + "\n");
                        } else {
                            System.out.println("Неверный формат даты рождения");
                        }
                    }catch (Exception e){
                        System.out.println("Введите данные в формате: Фамилия Телефон Дата Рождения(ДД-ММ-ГГГГ)");
                    }
                    writer.flush();
                    writer.close();
                }
            }
                catch (IOException e) {
                e.printStackTrace();
            }
        }


    /**
     * Search by last name
     */
        public static void search() {
        System.out.println("Для поиска введите фамилию");
        System.out.println("Для выхода нажмите 0");
        while (true) {
            Scanner scan = new Scanner(System.in);
            StringBuilder sb = new StringBuilder();
            String surname = scan.nextLine();
            if(surname.equals("0"))
                break;
            try {
                File file = new File("notebook.txt");
                FileReader fr = new FileReader(file);
                BufferedReader reader = new BufferedReader(fr);
                String line = reader.readLine();
                while (line != null) {
                    if (surname.equalsIgnoreCase(line.split(" ")[0])) {
                            System.out.println(line + "\n");
                        }
                        line = reader.readLine();
                }
                System.out.println("Введите фамилию");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Birthday reminder
     */
    public static void reminder(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat();
        String formattedDate = sdf.format(date);
        sdf = new SimpleDateFormat("dd-MM-yyyy");
        formattedDate = sdf.format(date);
        try{
            File file = new File("notebook.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                if(line.split(" ")[2].split("-")[1].equals(formattedDate.split("-")[1])){
                if(line.split(" ")[2].split("-")[0].equals(formattedDate.split("-")[0])){
                    System.out.println("Сегодня именины у" + " " +
                                line.toUpperCase().split(" ")[0] + "\n");
                    }
                if(Integer.parseInt(line.split(" ")[2].split("-")[0])==
                        (Integer.parseInt(formattedDate.split("-")[0])+1)){
                    System.out.println("Завтра именнины у" + " " +
                            line.toUpperCase().split(" ")[0] + "\n");
                    }
                }
                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void getDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat();
        String formattedDate = sdf.format(date);
        sdf = new SimpleDateFormat("dd-MM-yyyy");
        formattedDate = sdf.format(date);
        System.out.println( "Сегодня: " + formattedDate + "\n");
    }


    /**
     * Notebook functions
     * @throws FileNotFoundException
     */
    public static void actions() throws FileNotFoundException {
        while (true) {
        System.out.println("Для просмотра записей нажмите 1 ");
        System.out.println("Для добавления записи нажмите 2 ");
        System.out.println("Для поиска записи нажмите 3");
        System.out.println("Для завершения работы программы нажмите 0");
        Scanner scan = new Scanner(System.in);
        String command = scan.nextLine();
        if (command.equals("1")) {
            Methods.readFile();
        }
        else if (command.equals("2")) {
            Methods.addingPerson();
        }
        else if (command.equals("3")) {
            Methods.search();
        }
        else if (command.equals("0")) {
            break;
        }
    }
}

}
