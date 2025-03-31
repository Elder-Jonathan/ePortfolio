package edu.au.cpsc.module4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class AirlineDatabaseIO {

    public static final File DEFAULT_FILE = new File("flights.dat");
    private static AirlineDatabase airlineDatabase = null;

    public static AirlineDatabase getDatabase() {
        if (airlineDatabase == null)
            airlineDatabase = loadDatabase();
        return airlineDatabase;
    }

    private static AirlineDatabase loadDatabase() {
        try (InputStream is = new FileInputStream(DEFAULT_FILE)) {
            return load(is);
        } catch (IOException | ClassNotFoundException ex) {
            return new AirlineDatabase();
        }
    }

    public static void saveDatabase() {
        try (OutputStream os = new FileOutputStream(DEFAULT_FILE)) {
            save(getDatabase(), os);
        } catch (IOException ex) {
            System.err.println("Error saving database: " + DEFAULT_FILE);
            ex.printStackTrace();
            System.exit(-1);
        }
    }

    public static void save(AirlineDatabase database, OutputStream strm) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(strm);
        oos.writeObject(database);
    }

    public static AirlineDatabase load(InputStream strm) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(strm);
        return (AirlineDatabase) ois.readObject();
    }
}
