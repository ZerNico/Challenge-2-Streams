package filepersistence;

public class WriteAndReadDataSet {
    public static void main(String[] args) {
        // three example data sets
        String sensorName = "MyGoodOldSensor"; // does not change

        long[] timeStamps = new long[3];
        timeStamps[0] = System.currentTimeMillis();
        timeStamps[1] = timeStamps[0] + 1; // milli sec later
        timeStamps[2] = timeStamps[1] + 1000; // second later

        float[][] values = new float[3][];
        // 1st measure .. just one value
        float[] valueSet = new float[1];
        values[0] = valueSet;
        valueSet[0] = (float) 1.5; // example value 1.5 degrees

        // 2nd measure .. just three values
        valueSet = new float[3];
        values[1] = valueSet;
        valueSet[0] = (float) 0.7;
        valueSet[1] = (float) 1.2;
        valueSet[2] = (float) 2.1;

        // 3rd measure .. two values
        valueSet = new float[2];
        values[2] = valueSet;
        valueSet[0] = (float) 0.7;
        valueSet[1] = (float) 1.2;

        // write three data set into a file
        for (int i = 0; i < timeStamps.length; i++) {
            try {
                Storage.saveData(sensorName, timeStamps[i], values[i]);
            } catch (Exception e){
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }

        // read data from file and print to System.out
        String[] data = Storage.readData().split("\n\n");
        for (int i = 0; i < data.length - 1; i++) {
            String[] lines = data[i].split("\n");
            for (int j = 0; j < lines.length; j++) {
                if (j == 0) {
                    System.out.println("Name: " + lines[j]);
                } else if (j == 1) {
                    System.out.println("Time: " + lines[j]);
                } else if (j == 2) {
                    System.out.println("Values: ");
                    System.out.println(lines[j]);
                } else {
                    System.out.println(lines[j]);
                }
            }
        }
    }
}