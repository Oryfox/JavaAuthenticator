package de.oryfox.totpauthenticator;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QRCodeProcessing {

    public static KeyItem getKeyItemFrom(BufferedImage bufferedImage) {
        try {
            String decodedQRImage = decodeQRImage(bufferedImage);
            if (decodedQRImage != null) {
                URL url = new URL(decodedQRImage.replaceAll("otpauth","http"));
                String title = null;
                String secret = null;
                String[] parameters = url.getQuery().split("&");
                String[] splitted;
                for (String s : parameters) {
                    splitted = s.split("=");
                    if (splitted[0].equals("issuer")) {
                        title = splitted[1];
                    }
                    if (splitted[0].equals("secret")) {
                        secret = splitted[1];
                    }
                }

                String accountRegex = "/(.*(%3A|:))?(.*)";
                Pattern pattern = Pattern.compile(accountRegex);
                Matcher matcher = pattern.matcher(url.getPath());
                //noinspection ResultOfMethodCallIgnored
                matcher.find();
                String account = matcher.group(3).replaceAll("%40","@");

                return new KeyItem(title,account,secret);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("No QR Code or contents found in selected file!");
    }

    public static String decodeQRImage(BufferedImage bufferedImage) {
        try {
            LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            Result result = new MultiFormatReader().decode(bitmap);
            return result.getText();
        } catch (NotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
