create table if not exists song
(
    "idTrack"             double precision not null
        constraint song_pk
            primary key,
    "idAlbum"             double precision,
    "idArtist"            double precision,
    "strTrack"            varchar,
    "strAlbum"            varchar,
    "strArtist"           varchar,
    "intDuration"         integer,
    "strGenre"            varchar,
    "strMusicVid"         varchar,
    "intMusicVidViews"    integer,
    "intMusicVidDislikes" integer,
    "intMusicVidLikes"    integer
);