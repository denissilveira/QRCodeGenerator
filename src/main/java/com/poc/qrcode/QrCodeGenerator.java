package com.poc.qrcode;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Collections;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;

/**
 *
 */
public final class QrCodeGenerator {

    private static final int DEFAULT_SIZE_QR_CODE = 200;
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String DEFAULT_IMAGE_FORMAT = "png";

    private QrCodeGenerator() {}

    public static void main(String[] args) throws WriterException, IOException {
        String qrCodeData = "https://unified-portal-int.172.20.185.20.xip.io/portal/billing/pay/123465";
        String filePath = "QRCode.png";

        System.out.println(createQrCodeAndGetByteArrayInputStream(qrCodeData, 200, filePath));
        byte[] tste = createQrCodeAndGetBytes(qrCodeData, 200, filePath);
        System.out.println(new String(Base64.getEncoder().encode(tste)));
        System.out.println("QR Code image created successfully!");

    }

    /**
     * This method creates a new QR code file
     * @param qrCodeData - Information that will be stored in QR Code.
     * @param size - size of image/file
     * @param filePath - file path with extension
     * @throws WriterException - error generate QR code
     * @throws IOException - error create file
     */
    public static void createQRCodeToPath(final String qrCodeData, final int size, final String filePath) throws WriterException, IOException {
        final BitMatrix matrix = new MultiFormatWriter().encode(new String(qrCodeData.getBytes(DEFAULT_CHARSET), DEFAULT_CHARSET),
                BarcodeFormat.QR_CODE, size, size, Collections.singletonMap(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L));
        MatrixToImageWriter.writeToPath(matrix,DEFAULT_IMAGE_FORMAT, Paths.get(filePath));
    }

    /**
     * This method creates a new QR code file
     * @param qrCodeData - Information that will be stored in QR Code.
     * @param filePath - file path with extension
     * @throws WriterException - error generate QR code
     * @throws IOException - error create file
     */
    public static void createQRCodeToPath(final String qrCodeData, final String filePath) throws WriterException, IOException {
        final BitMatrix matrix = new MultiFormatWriter().encode(new String(qrCodeData.getBytes(DEFAULT_CHARSET), DEFAULT_CHARSET),
                BarcodeFormat.QR_CODE, DEFAULT_SIZE_QR_CODE, DEFAULT_SIZE_QR_CODE, Collections.singletonMap(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L));
        MatrixToImageWriter.writeToPath(matrix,DEFAULT_IMAGE_FORMAT, Paths.get(filePath));
    }

    /**
     * This method creates a new QR code file
     * @param qrCodeData - Information that will be stored in QR Code.
     * @param size - size of image/file
     * @param filePath - file path with extension
     * @param outputStream - Stream add qrcode
     * @throws WriterException - error generate QR code
     * @throws IOException - error create file
     */
    public static void createQRCodeToStream(final String qrCodeData, final int size, final String filePath, final OutputStream outputStream) throws WriterException, IOException {
        final BitMatrix matrix = new MultiFormatWriter().encode(new String(qrCodeData.getBytes(DEFAULT_CHARSET), DEFAULT_CHARSET),
                BarcodeFormat.QR_CODE, size, size, Collections.singletonMap(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L));
        MatrixToImageWriter.writeToStream(matrix,DEFAULT_IMAGE_FORMAT, outputStream);
    }

    /**
     * This method creates a new QR code file
     * @param qrCodeData - Information that will be stored in QR Code.
     * @param filePath - file path with extension
     * @param outputStream - Stream add qrcode
     * @throws WriterException - error generate QR code
     * @throws IOException - error create file
     */
    public static void createQRCodeToStream(final String qrCodeData, final String filePath, final OutputStream outputStream) throws WriterException, IOException {
        final BitMatrix matrix = new MultiFormatWriter().encode(new String(qrCodeData.getBytes(DEFAULT_CHARSET), DEFAULT_CHARSET),
                BarcodeFormat.QR_CODE, DEFAULT_SIZE_QR_CODE, DEFAULT_SIZE_QR_CODE, Collections.singletonMap(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L));
        MatrixToImageWriter.writeToStream(matrix,DEFAULT_IMAGE_FORMAT, outputStream);
    }

    /**
     * This method creates a new QR code file and returns byte array from this file
     * @param qrCodeData - Information that will be stored in QR Code.
     * @param size - size of image/file
     * @param filePath - file path with extension
     * @return - file in byte[]
     * @throws WriterException - error generate QR code
     * @throws IOException - error create file
     */
    public static byte[] createQrCodeAndGetBytes(final String qrCodeData, final int size, final String filePath) throws WriterException, IOException {
        final BitMatrix matrix = new MultiFormatWriter().encode(new String(qrCodeData.getBytes(DEFAULT_CHARSET), DEFAULT_CHARSET),
                BarcodeFormat.QR_CODE, size, size, Collections.singletonMap(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L));
        final BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(matrix);
        return getImgBytes(bufferedImage,filePath);
    }

    /**
     * This method creates a new QR code file and returns byte array from this file
     * @param qrCodeData - Information that will be stored in QR Code.
     * @param filePath - file path with extension
     * @return - file in byte[]
     * @throws WriterException - error generate QR code
     * @throws IOException - error create file
     */
    public static byte[] createQrCodeAndGetBytes(final String qrCodeData, final String filePath) throws WriterException, IOException {
        final BitMatrix matrix = new MultiFormatWriter().encode(new String(qrCodeData.getBytes(DEFAULT_CHARSET), DEFAULT_CHARSET),
                BarcodeFormat.QR_CODE, DEFAULT_SIZE_QR_CODE, DEFAULT_SIZE_QR_CODE, Collections.singletonMap(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L));
        final BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(matrix);
        return getImgBytes(bufferedImage,filePath);
    }

    /**
     * This method creates a new QR code file and returns a ByteArrayInputStream
     * @param qrCodeData - Information that will be stored in QR Code.
     * @param size - size of image/file
     * @param filePath - file path with extension
     * @return - file in ByteArrayInputStream
     * @throws WriterException - error generate QR code
     * @throws IOException - error create file
     */
    public static ByteArrayInputStream createQrCodeAndGetByteArrayInputStream(final String qrCodeData, final int size, final String filePath) throws WriterException, IOException {
        final BitMatrix matrix = new MultiFormatWriter().encode(new String(qrCodeData.getBytes(DEFAULT_CHARSET), DEFAULT_CHARSET),
                BarcodeFormat.QR_CODE, size, size, Collections.singletonMap(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L));
        final BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(matrix);
        return getImgByteArrayInputStream(bufferedImage,DEFAULT_IMAGE_FORMAT);
    }

    /**
     * This method creates a new QR code file and returns a ByteArrayInputStream
     * @param qrCodeData - Information that will be stored in QR Code.
     * @param filePath - file path with extension
     * @return - file in ByteArrayInputStream
     * @throws WriterException - error generate QR code
     * @throws IOException - error create file
     */
    public static ByteArrayInputStream createQrCodeAndGetByteArrayInputStream(final String qrCodeData, final String filePath) throws WriterException, IOException {
        final BitMatrix matrix = new MultiFormatWriter().encode(new String(qrCodeData.getBytes(DEFAULT_CHARSET), DEFAULT_CHARSET),
                BarcodeFormat.QR_CODE, DEFAULT_SIZE_QR_CODE, DEFAULT_SIZE_QR_CODE, Collections.singletonMap(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L));
        final BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(matrix);
        return getImgByteArrayInputStream(bufferedImage,DEFAULT_IMAGE_FORMAT);
    }

    private static byte[] getImgBytes(final BufferedImage image, final String filePath) throws IOException {
        if (!ImageIO.write(image, DEFAULT_IMAGE_FORMAT, Paths.get(filePath).toFile())) {
            throw new IOException("Could not write an image of format " + DEFAULT_IMAGE_FORMAT + " to " + filePath);
        }
        return Files.readAllBytes(Paths.get(filePath));
    }

    private static ByteArrayInputStream getImgByteArrayInputStream(final BufferedImage image, final String format) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if (!ImageIO.write(image, format, baos)) {
            throw new IOException("Could not write an image of format " + format + " to " + baos);
        }
        return new ByteArrayInputStream(baos.toByteArray());
    }

}
