package com.analysis.modules;

import java.io.File;
import java.io.IOException;

import com.mpatric.mp3agic.*;

public class MusicAnalysis implements FileAnalysis {

    public String getPath() {
        return path;
    }

    String path;

    @Override
    public void analysis(String methodNumber, String path) throws InvalidDataException, IOException, UnsupportedTagException {
        this.path = path;
        File file = new File(path);
        MusicAnalysis an = new MusicAnalysis();
        Mp3File mp3 = new Mp3File(an.getPath());

        if (!checkType(file)) {
            System.out.println("WOW, it's not a .txt file!  \nTry again");
            return;
        }
        if(methodNumber.equals("1")){
           getMp3Name(mp3);
        }
        else if (methodNumber.equals( "2")) {
            getLengthInSecond(mp3);
        }
        else {
            getMp3Artist(mp3);
        }

    }



    @Override
    public boolean checkType(File file) {
        Mp3File mp3file;
        try {
            mp3file = new Mp3File(path);
            return true;

        } catch (Exception e) {
            return false;
        }

//        int index = file.getName().indexOf('.');
//        return index != -1 && (file.getName().substring(index).equals(".mp3") );
    }

    @Override
    public void getInformationAboutFunctions() {
        System.out.println("It's music file, you can use this function:\\n\" +" +
                "1: output track name from tags \n" +
                "2: duration output in seconds \n" +
                "3: output track artists from tags\n " +
                "type one of this number" );

    }

    private void getLengthInSecond(Mp3File mp3) {
        System.out.println("Size mp3 file in second: " + mp3.getLengthInSeconds());
    }

    private void getMp3Name(Mp3File mp3) {
        if (mp3.hasId3v1Tag()) {
            ID3v1 id3v1Tag = mp3.getId3v1Tag();
            System.out.println("Name of the track: " + id3v1Tag.getTitle());
        } else if (mp3.hasId3v2Tag()) {
            ID3v2 id3v2Tag = mp3.getId3v2Tag();
            System.out.println("Name of the track: " + id3v2Tag.getTitle());

        } else {
            System.out.println("something wrong in mp3 name ");
        }

    }

    private void getMp3Artist(Mp3File mp3) {
        if (mp3.hasId3v1Tag()) {
            ID3v1 id3v1Tag = mp3.getId3v1Tag();
            System.out.println("Name of the a rtist: " + id3v1Tag.getArtist());
        } else if (mp3.hasId3v2Tag()) {
            ID3v2 id3v2Tag = mp3.getId3v2Tag();
            System.out.println("Name of the artist: " + id3v2Tag.getArtist());

        } else {
            System.out.println("something wrong in mp3 artist ");
        }
    }
}
