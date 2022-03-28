package com.example.modules._common.domain.exceptions;

import java.awt.image.BufferedImage;

public class InvalidImageException extends RuntimeException {
    public InvalidImageException(String message) {
        super(message);
    }

    public static void assertRatio(BufferedImage bufferedImage, Integer width, Integer height) throws InvalidImageException {
        if (bufferedImage.getWidth() * height != bufferedImage.getHeight() * width) {
            throw new InvalidImageException(String.format("Image must conform to %d:%d ratio", width, height));
        }
    }
}
