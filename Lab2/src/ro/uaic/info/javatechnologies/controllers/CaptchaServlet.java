package ro.uaic.info.javatechnologies.controllers;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "CaptchaServlet", urlPatterns = "/captcha")
public class CaptchaServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String captchaString = generateCaptchaString();

        HttpSession session = request.getSession(true);
        session.setAttribute("CaptchaServlet.captchaString", captchaString);

        // buffered image setup
        int padding = 5;
        int size = 26;
        int captchaLength = captchaString.length();
        BufferedImage bufferedImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bufferedImage.getGraphics();
        Font arialBlackFont = new Font("Arial Black", Font.PLAIN, size);
        Rectangle2D r = arialBlackFont.getStringBounds(captchaString, ((Graphics2D)graphics).getFontRenderContext());

        bufferedImage = new BufferedImage(
                (int)r.getWidth() + padding * 2,
                (int)r.getHeight() + padding * 2,
                BufferedImage.TYPE_INT_RGB);
        graphics = bufferedImage.getGraphics();
        Graphics2D graphics2D = (Graphics2D)graphics;
        graphics.setFont(arialBlackFont);

        // background
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());

        int startingLetterX = 5;

        for (int i = 0; i < captchaLength; i++) {
            graphics.setColor(Color.BLACK);
            graphics.setFont(arialBlackFont);
            char currentChar = captchaString.charAt(i);
            graphics.drawString(String.valueOf(currentChar), startingLetterX, 32);
            startingLetterX += graphics.getFontMetrics(arialBlackFont).charWidth(currentChar);
        }

        BufferedImage buffer2 = new BufferedImage(
                bufferedImage.getWidth(),
                bufferedImage.getHeight(),
                bufferedImage.getType());
        Graphics2D g2d2 = (Graphics2D)buffer2.getGraphics();
        g2d2.drawImage(bufferedImage, 0, 0, null);
        Random rand = new Random();
        double seed = rand.nextDouble() * 3d + 5d;
        int complexity = 6;
        for (int x = 0; x < bufferedImage.getWidth(); x++) {
            for (int y = 0; y < bufferedImage.getHeight(); y++) {
                int xx = x + (int)(Math.cos((double)y/seed) * ((double)complexity/2d));
                int yy = y + (int)(Math.sin((double)x/(seed+1)) * ((double)complexity/2d));
                xx = Math.abs(xx % bufferedImage.getWidth());
                yy = Math.abs(yy % bufferedImage.getHeight());
                bufferedImage.setRGB(x, y, buffer2.getRGB(xx, yy));
            }
        }

        response.setContentType("image/jpg");
        ServletOutputStream responseOutputStream = response.getOutputStream();
        ImageIO.write(bufferedImage, "jpg", responseOutputStream);
        responseOutputStream.close();
    }

    private static String generateCaptchaString() {
        Random random = new Random();
        int length = 7 + (Math.abs(random.nextInt()) % 3);

        StringBuilder captchaStringBuffer = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int baseCharNumber = Math.abs(random.nextInt()) % 62;
            int charNumber = 0;
            if (baseCharNumber < 26) {
                charNumber = 65 + baseCharNumber;
            } else if (baseCharNumber < 52) {
                charNumber = 97 + (baseCharNumber - 26);
            } else {
                charNumber = 48 + (baseCharNumber - 52);
            }
            captchaStringBuffer.append((char) charNumber);
        }

        return captchaStringBuffer.toString();
    }
}
