import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class dbConnection {

    private final String url = "jdbc:postgresql://localhost:5432/postgres?";

    private Connection connection;

    public void openConnection() throws ClassNotFoundException{
        try{
            connection = DriverManager.getConnection(url, "postgres", "pass");
            return;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        System.out.println("No connection!");
    }

    public void addRow(Song song) throws ClassNotFoundException, SQLException {
        try {
            if(connection == null) {
                openConnection();
            }
            String query = "insert into bigdata.song(\"idTrack\", \"idAlbum\", \"idArtist\", \"strTrack\"," +
                    " \"strAlbum\", \"strArtist\", \"intDuration\", \"strGenre\", \"strMusicVid\"," +
                    " \"intMusicVidViews\", \"intMusicVidDislikes\", \"intMusicVidLikes\") " +
                    "values (?, ?, ?, ?, ?, ?," +
                    " ?, ?, ?, ?, ?, ? )";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setLong(1, song.getIdTrack());
            pstmt.setLong(2, song.getIdAlbum());
            pstmt.setLong(3, song.getIdArtist());
            pstmt.setString(4, song.getStrTrack());
            pstmt.setString(5, song.getStrAlbum());
            pstmt.setString(6, song.getStrArtist());
            pstmt.setInt(7, song.getIntDuration());
            pstmt.setString(8, song.getStrGenre());
            pstmt.setString(9, song.getStrMusicVid());
            pstmt.setInt(10, song.getIntMusicVidViews());
            pstmt.setInt(11, song.getIntMusicVidDislikes());
            pstmt.setInt(12, song.getIntMusicVidLikes());
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println("SqlError: "+ e.getMessage());
        }
    }

    public void exportTableToCsv() {
        try {
            if(connection == null) {
                openConnection();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        CSVWriter writer;
        try {
            writer = new CSVWriter(new FileWriter("imdb_data.csv"));
            boolean includeHeaders = true;
            try {
                Statement stat = connection.createStatement();
                writer.writeAll(stat.executeQuery("Select * from bigdata.song"), includeHeaders);
                writer.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void closeConnecton() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
