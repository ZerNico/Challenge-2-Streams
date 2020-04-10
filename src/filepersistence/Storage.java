package filepersistence;

import java.io.*;

public class Storage {
    public static void saveData(String name, long time, float[] values) throws ConsistencyException {
        OutputStream os = null;

        // don't allow writes with missing values
        if (name == null) {
            throw new ConsistencyException("No sensorname given");
        }
        if (values == null || values.length == 0) {
            throw new ConsistencyException("No values given");
        }

        // open file
        try {
            // append to file
            os = new FileOutputStream("SensorData.txt", true);
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't open file - fatal");
            System.exit(0);
        }

        // build up data string
        String dataToBeSaved = name + "\n" + time + "\n";

        for (int i = 0; i < values.length; i++) {
            dataToBeSaved += values[i] + "\n";
        }
        dataToBeSaved += "\n";


        // write data to file
        try {
            os.write(dataToBeSaved.getBytes());
        } catch (IOException e) {
            System.out.println("couldn't write data - fatal");
            System.exit(0);
        }
    }

    public static String readData() {
        InputStream is = null;

        // open file
        try {
            is = new FileInputStream("SensorData.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't open file - fatal");
            System.exit(0);
        }

        byte[] readBuffer = new byte[256];

        // read from file
        try {
            is.read(readBuffer);
        } catch (IOException e) {
            System.out.println("Couldn't read data - fatal");
            System.exit(0);
        }

        return new String(readBuffer);
    }
}