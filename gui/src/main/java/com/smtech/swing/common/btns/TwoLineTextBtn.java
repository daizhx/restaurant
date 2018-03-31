package com.smtech.swing.common.btns;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * 包含2行文字的btn,居中显示
 */
@SuppressWarnings("serial")
public class TwoLineTextBtn extends JButton {
    private Color textColor = Color.BLACK;

    public TwoLineTextBtn(String s, Font f) {
        super();
        setText(s);
        setFont(f);
    }

    public TwoLineTextBtn(String s) {
        super();
        setText(s);
    }

    public TwoLineTextBtn(AbstractAction ac) {
        super(ac);
    }

    @Override
    protected void paintComponent(Graphics g) {
        // super.paintComponent(g);
        String text = getText();
        Font textFont = getFont();
        if (text == null) {
            return;
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(textFont);
        g2d.setColor(textColor);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
                RenderingHints.VALUE_STROKE_DEFAULT);

        String[] ss = text.split("\n");

        if (ss.length == 1) {
            super.paintComponent(g2d);
            return;
        }

        if (ss.length > 2) {
            return;
        }

        int w = getWidth();
        int h = getHeight();

        int text1Length = getFontSize(g2d, textFont, ss[0]);
        int text2Length = getFontSize(g2d, textFont, ss[1]);

        FontMetrics fontMetrics = g2d.getFontMetrics();
        int ascent = fontMetrics.getAscent();
        int descent = fontMetrics.getDescent();
        int fh = fontMetrics.getHeight();
        int lead = fontMetrics.getLeading();

        int gap = (h - fh * 2) / 2;

        int y1 = gap + (fh - ascent + descent) / 2 + ascent;
        int y2 = y1 + ascent;

        int x1 = (w - text1Length) / 2;
        int x2 = (w - text2Length) / 2;

        Icon ic = getIcon();
        if (ic != null) {

            int icw = ic.getIconWidth();
            int ich = ic.getIconHeight();
            int icgap = getIconTextGap();
            int x = Math.min(x1, x2);

            if (getVerticalTextPosition() == SwingConstants.CENTER) {

                int x3 = (2 * x - icw - icgap) / 2;
                int y3 = (h - ich) / 2;

                int start = x3 + icw + icgap;
                x1 = x1 - x + start;
                x2 = x2 - x + start;

                g2d.drawImage(((ImageIcon) ic).getImage(), x3, y3, icw, ich,
                        null);
                g2d.drawString(ss[0], x1, y1);
                g2d.drawString(ss[1], x2, y2);

            } else {

                int x3 = (w - icw) / 2;
                int y3 = ((h / 2) - ich) / 2;

                g2d.drawImage(((ImageIcon) ic).getImage(), x3, y3, icw, ich,
                        null);

                g2d.drawString(ss[0], x1, y3 + ich + fh / 2);
                g2d.drawString(ss[1], x2, y3 + ich + fh / 2 + ascent);
            }
        } else {
            g2d.drawString(ss[0], x1, y1);
            g2d.drawString(ss[1], x2, y2);
        }

    }

    /**
     * @param g2d
     * @param font
     * @return
     * @throws
     * @parm
     */
    public int getFontSize(Graphics2D g2d, Font font, String text) {
        FontRenderContext context = g2d.getFontRenderContext();
        Rectangle2D stringBounds = font.getStringBounds(text, context);
        double fontWidth = stringBounds.getWidth();
        // double fontHeight = stringBounds.getHeight();
        return (int) Math.round(fontWidth);
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
        setForeground(textColor);
    }

    @Override
    public void setForeground(Color fg) {
        this.textColor = fg;
        super.setForeground(fg);
    }

}
