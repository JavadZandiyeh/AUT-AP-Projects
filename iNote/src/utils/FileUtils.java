package utils;

import model.Note;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * in this class we save the note contents to file by 3 ways
 */
public class FileUtils {

    //name of directory we save our file there
    private static final String NOTES_PATH = "./notes/";

    //It's a static initializer. It's executed when the class is loaded.
    //It's similar to constructor
    static {
        boolean isSuccessful = new File(NOTES_PATH).mkdirs();
        System.out.println("Creating " + NOTES_PATH + " directory is successful: " + isSuccessful);
    }

    /**
     * @return files witch arr in directory
     */
    public static File[] getFilesInDirectory() {
        return new File(NOTES_PATH).listFiles();
    }

    /**
     * @param file file witch we want to read details from it
     * @return content of file
     */
    public static String fileReader(File file) {
        //TODO: Phase1: read content from file    :)
        String content = "";
        try (FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
        ) {
            while (bufferedReader.ready())
                content += (char)bufferedReader.read();
        } catch (Exception e){
            e.printStackTrace();
        }

        return content;
    }

    /**
     * @param content content witch we want to save on file
     */
    public static void fileWriter(String content) {
        //TODO: write content on file   :)
        String fileName = getProperFileName(content);
        try (FileWriter fileWriter = new FileWriter(new File(NOTES_PATH + fileName));
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        ) {
            bufferedWriter.write(content);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @param file file witch we want to read details from it
     * @return content of file
     */
    public static String fileInputStream(File file) {
        //TODO: Phase1: define method here for reading file with InputStream  :)

        String content = "";
        byte[] bytes;
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            bytes = fileInputStream.readAllBytes();
            for (byte b : bytes)
                content += (char) b;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return content;
    }

    /**
     * @param content content witch we want to save on file
     */
    public static void fileOutPutStream(String content){
        //TODO: Phase1: define method here for writing file with OutputStream :)

        String fileName = getProperFileName(content);
        try (FileOutputStream fileOutputStream = new FileOutputStream(NOTES_PATH + fileName)){
            byte[] bytes = fileName.getBytes();
            fileOutputStream.write(bytes);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * by this method we read files by deserialize way
     * @param file file witch we want to read
     * @return content of file
     */
    public static String objectInputStream(File file){
        //TODO: Phase2: proper methods for handling serialization  :)
        String content = "";

        try (FileInputStream fileInputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        ) {
            Note note = (Note)objectInputStream.readObject();
            content = note.getContent_All();
        }catch (Exception e){
            e.printStackTrace();
        }

        return content;
    }

    /**
     * by this method we save objects(mean notes) to files
     * @param content the content of our object
     */
    public static void objectOutputStream(String content){

        Note note = (new Note("objectSaving", content, getCurrentDate()));
        File file = new File(NOTES_PATH + note.getContent_All());
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        ){
            objectOutputStream.writeObject(note);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @param content content of file witch we want to make a new name
     *                for its file name
     * @return name of file to save be saved by this name
     */
    private static String getProperFileName(String content) {
        int loc = content.indexOf("\n");
        if (loc != -1)
            return content.substring(0, loc);
        if (!content.isEmpty())
            return content;
        return System.currentTimeMillis() + "_new file.txt";
    }

    /**
     * @return date of note at the time that is saving
     */
    private static String getCurrentDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        return dateFormat.format(date);
    }

}