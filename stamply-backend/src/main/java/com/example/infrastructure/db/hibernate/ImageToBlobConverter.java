package com.example.infrastructure.db.hibernate;

import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;

@Converter
@Component
public class ImageToBlobConverter
        implements AttributeConverter<BufferedImage, byte[]> {

    @Override
    public byte[] convertToDatabaseColumn(BufferedImage attribute) {
        return convertToBytes(attribute);
    }

    @Override
    public BufferedImage convertToEntityAttribute(byte[] dbData) {
        if (dbData == null) {
            return null;
        }
        return convertToImage(dbData);
    }


    static byte[] convertToBytes(BufferedImage image) {
        if (image == null) {
            return null;
        }

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static BufferedImage convertToImage(byte[] image) {
        try {
            return ImageIO.read(new ByteArrayInputStream(image));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
