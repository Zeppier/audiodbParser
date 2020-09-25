import lombok.*;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Song {
    private Long idTrack;

    private Long idAlbum;

    private Long idArtist;

    private String strTrack;

    private String strAlbum;

    private String strArtist;

    private Integer intDuration;

    private String strGenre;

    private String strMusicVid;

    private Integer intMusicVidViews;

    private Integer intMusicVidDislikes;

    private Integer intMusicVidLikes;

    public Long getIdTrack() {
        return idTrack;
    }

    public Long getIdAlbum() {
        if(idAlbum == null) return -1L;
        return idAlbum;
    }

    public Long getIdArtist() {
        if(idArtist == null) return -1L;
        return idArtist;
    }

    public String getStrTrack() {
        if(strTrack == null) return "";
        return strTrack;
    }

    public String getStrAlbum() {
        if(strAlbum == null) return "";
        return strAlbum;
    }

    public String getStrArtist() {
        if(strArtist == null) return "";
        return strArtist;
    }

    public Integer getIntDuration() {
        if(intDuration == null) return -1;
        return intDuration;
    }

    public String getStrGenre() {
        if(strGenre == null) return "";
        return strGenre;
    }

    public String getStrMusicVid() {
        if(strMusicVid == null) return "";
        return strMusicVid;
    }

    public Integer getIntMusicVidViews() {
        if(intMusicVidViews == null) return -1;
        return intMusicVidViews;
    }

    public Integer getIntMusicVidDislikes() {
        if(intMusicVidDislikes == null) return -1;
        return intMusicVidDislikes;
    }

    public Integer getIntMusicVidLikes() {
        if(intMusicVidLikes == null) return -1;
        return intMusicVidLikes;
    }
}
