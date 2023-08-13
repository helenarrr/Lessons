import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class BackUp {

//    Написать функцию, создающую резервную копию всех файлов в директории(без поддиректорий) во вновь созданную папку ./backup


    public static void backUp(String source) {
        File backupDir = new File("./backUp");
        if (!backupDir.exists()) {
            backupDir.mkdir();
        }
        File directoryFile = new File(source);
        if (directoryFile.exists() && directoryFile.isDirectory()) {
            for (File file : directoryFile.listFiles()) {
                if (file.isFile()) {
                    try {
                        Files.copy(file.toPath(), new File(backupDir + "/" + file.getName()).toPath());
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            System.out.println("Резервная копия создалась.");
        } else {
            System.out.println("Это не директория или в процессе копирования произошла ошибка.");
        }
    }
}
