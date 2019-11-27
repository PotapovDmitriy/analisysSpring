package com.analysis.modules;


import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Collection;

public class ImageAnalysis implements FileAnalysis {
    @Override
    public void analysis(String methodNumber, String path) {
        File file = new File(path);
        if (!checkType(file)) {
            System.out.println("WOW, it's not a .txt file!  \nTry again");
            return;
        }
        if (methodNumber.equals("1")) {
            getSize(file);
        } else if (methodNumber.equals("2")) {
            getExif(file);
        } else {
            getAllInformation(file);
        }

    }


    @Override
    public boolean checkType(File file) {

        int index = file.getName().indexOf('.');
        return index != -1 && (file.getName().substring(index).equals(".jpg") || file.getName().substring(index).equals(".png"));

    }

    @Override
    public void getInformationAboutFunctions() {
        System.out.println("It's image, you can use this function:\\n\" +" +
                "1: image size output \n" +
                "2: output exif information \n" +
                "3: output all information bout file\n" +
                "Type one of this number");
    }

    private void getSize(File file) {
        BufferedImage image;
        try {
            image = ImageIO.read(file);
            System.out.println("height: " + image.getHeight() + "; width: " + image.getWidth());
        } catch (Exception e) {
            System.out.println("Something wrong with Size of image " + file.getName());
        }
    }

    private void getExif(File file) {
        try {
            boolean flag = false;
            Metadata metadata = ImageMetadataReader.readMetadata(file);
            Iterable<Directory> metadataDirectories = metadata.getDirectories();
            for (Directory directory : metadataDirectories) {
                if (directory.toString().contains("Exif")) {
                    flag = true;
                    System.out.println(directory);
                    Collection<Tag> t = directory.getTags();
                    for (Tag tag : t) {
                        System.out.println("\t" + tag.getTagName() + " : " + tag.getDescription());
                    }
                }
            }
            if (!flag) {
                System.out.println("this file not contains EXIF information");
            }
        } catch (Exception e) {
            System.out.println("Something wrong with EXIF information for  " + file.getName());
        }

    }

    private void getAllInformation(File file) {
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(file);
            Iterable<Directory> metadataDirectories = metadata.getDirectories();
            for (Directory directory : metadataDirectories) {

                System.out.println(directory);
                Collection<Tag> t = directory.getTags();
                for (Tag tag : t) {
                    System.out.println("\t" + tag.getTagName() + " : " + tag.getDescription());
                }

            }

        } catch (Exception e) {
            System.out.println("Something wrong with meta information for  " + file.getName());
        }
    }


}


