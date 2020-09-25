
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class Main {

    public static String nextID(Integer cur) {
        cur++;
        return cur.toString();
    }



    public static void main(String[] args) throws ClassNotFoundException {
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        URL url;
        DataInputStream dataInputStream = null;
        dbConnection dbc = new dbConnection();
        dbc.openConnection();
        try{
            for (int i = 32782085; i < 32900000; i++) {
                String nextId = nextID(i);
                url = new URL("https://theaudiodb.com/api/v1/json/1/track.php?h=" + nextId);
                System.out.println("Processing "+ nextId);
                try {
                    URLConnection con = url.openConnection();
                    con.setConnectTimeout(150);
                    con.setReadTimeout(150);
                    InputStream in = con.getInputStream();
                    dataInputStream = new DataInputStream(in);
                } catch (IOException exc) {
                    System.out.println(exc.getMessage());
                    continue;
                }
                String detail = dataInputStream.readLine();
                if (detail.equals("{\"track\":null}")) {
                    System.out.println(i+": "+detail);
                    continue;
                }
                System.out.println(i+": "+detail);
                Song s = mapper.treeToValue(mapper.readTree(detail).get("track").elements().next(), Song.class);
                dbc.addRow(s);
            }
            dataInputStream.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("Finished. Exporting data to csv");
        System.out.flush();
        dbc.exportTableToCsv();
        System.out.println("Export finished.");
        System.out.flush();
        dbc.closeConnecton();
    }
}
