package utils;

import java.awt.Color;
import java.awt.Font;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Locale;

import javax.swing.JButton;

public class Format {
	public static void setButtonEvent(JButton... button) {
		for (JButton jButton : button) {
			jButton.setBackground(Color.decode("#FF008A"));
			jButton.setFont(new Font("", Font.BOLD,14));
			jButton.setForeground(Color.white);
			jButton.setBorder(null);
		}
	}
	public static String formatDate(Time time) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		String str = format.format(time);
		return str;
	}
	public static String formatAmout(float amount) {
        DecimalFormat formatter = new DecimalFormat("###,###.###");
        return formatter.format(amount);
	}
}
