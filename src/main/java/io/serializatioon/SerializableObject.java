package io.serializatioon;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class SerializableObject implements Serializable {
    public static final String RESOURCES_SERIALIZABLE_OBJECT_TXT = "serializableObject.txt";
    private final String s;

    public SerializableObject(String s) {
        this.s = s;
    }

    private static final long serialVersionUID = 1;

    private void writeObject(ObjectOutputStream oos) throws IOException {
        System.out.println(">>> Writing object...");
        oos.defaultWriteObject();
        System.out.println(">>> Done");
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        System.out.println(">>> Reading object...");
        ois.defaultReadObject();
        System.out.println(">>> Done");
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        save();
        System.out.println(get().s);
    }

    private static SerializableObject get() {
        try (var ois = new ObjectInputStream(new FileInputStream(RESOURCES_SERIALIZABLE_OBJECT_TXT))) {
            return (SerializableObject) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void save() {
        try (var oos = new ObjectOutputStream(new FileOutputStream(RESOURCES_SERIALIZABLE_OBJECT_TXT))) {
            oos.writeObject(new SerializableObject("Rachid"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
