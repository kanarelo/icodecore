/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icode.util;

import static java.awt.Toolkit.getDefaultToolkit;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JComponent;
import javax.swing.JPanel;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author Nes
 * @param <E>
 */
public class Commons<E> implements Cloneable {

	private static java.text.DecimalFormat DCFormat = new java.text.DecimalFormat(
			"KES #0,000.00");
	private static java.text.DecimalFormat smallDCFormat = new java.text.DecimalFormat(
			"KES #00.00");
	public static final String ULTIMATE_PASSWORD_ALGO = "SHA-1";

	/**
	 * 
	 * @param curr
	 * @return
	 */
	public static String getCurrency(Double curr) {
		return curr < 1000.00 ? smallDCFormat.format(curr) : DCFormat
				.format(curr);
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	/**
	 * 
	 * @param curr
	 * @return
	 */
	public static String getCurrency(Float curr) {
		return getCurrency(Math.ceil(curr));
	}

	private static final String Alphaumerals = "ABCDEFGHIJKLMNOPQRQSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
	private static final Random random = new Random();

	/**
	 * 
	 * @param Password
	 * @return
	 */
	public static String generatePassword(String Password) {
		String Algo = ULTIMATE_PASSWORD_ALGO;
		String Salt = getSalt().substring(0, 5);
		String Hash = generateFullHash(Algo, Salt, Password);
		return String.format("%s$%s$%s", Algo, Salt, Hash);
	}

	/**
	 * 
	 * @param Algorithm
	 * @param salt
	 * @param rawPassword
	 * @return
	 */
	public static String generateFullHash(String Algorithm, String salt,
			String rawPassword) {
		String saltedPassword = salt + rawPassword;
		return generateHash(saltedPassword, Algorithm);
	}

	public static boolean compare_passwords(String hashedPassword,
			String password2) {
		// remember to use the same SALT value use used while storing password
		// for the first time.
		String[] passArgs = hashedPassword.split("\\$");
		String hashedPassword2 = Commons.generateFullHash(passArgs[0],
				passArgs[1], password2);
		return hashedPassword2.equals(passArgs[2]);
	}

	public static boolean isValidPassword(String password) {
		if (password == null) {
			return false;
		}
		if (password.isEmpty()) {
			return false;
		}

		String[] passArr = password.split("\\$");
		if (passArr.length == 3) {
			return password.startsWith(ULTIMATE_PASSWORD_ALGO)
					& StringUtils.isAlphanumeric(passArr[1])
					& StringUtils.isAlphanumeric(passArr[2]);
		}
		return false;
	}

	/**
	 * 
	 * @return
	 */
	public static String getSalt() {
		return generateFullHash("SHA-1", getRandomIntString(),
				getRandomIntString());
	}

	/**
	 * 
	 * @param input
	 * @return
	 */
	public static String generateHash(String input) {
		return generateHash(input, "SHA-1");
	}

	public static int getRandomInt(int i) {
		return random.nextInt(i);
	}

	/**
	 * 
	 * @return
	 */
	public static int getRandomInt() {
		return random.nextInt();
	}

	/**
	 * 
	 * @return
	 */
	public static String getRandomIntString() {
		return Integer.toString(random.nextInt());
	}

	/**
	 * 
	 * @return
	 */
	public static String getRandomString() {
		return getRandomString(5);
	}

	/**
	 * 
	 * @param StrLength
	 * @return
	 */
	public static String getRandomString(int StrLength) {
		String str = "";
		for (int i = 0; i <= StrLength; i++) {
			str += Alphaumerals
					.charAt(random.nextInt(Alphaumerals.length() - 1));
		}
		return str;
	}

	/**
	 * 
	 * @param <T>
	 * @param packageName
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public synchronized static <T> List<Class<T>> getClasses(String packageName)
			throws IOException, ClassNotFoundException {
		return getClasses(packageName, false);
	}

	/**
	 * 
	 * @param <T>
	 * @param packageName
	 * @param checkSubpackages
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static synchronized <T> List<Class<T>> getClasses(
			String packageName, boolean checkSubpackages) throws IOException,
			ClassNotFoundException {
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		assert classLoader != null;
		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = classLoader.getResources(path);
		List<File> dirs = new ArrayList<File>(5);
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}
		ArrayList<Class<T>> classes = new ArrayList<Class<T>>(5);
		for (File directory : dirs) {
			List<Class<T>> findClasses = findClasses(directory, packageName,
					checkSubpackages);
			classes.addAll(findClasses);
		}
		return classes;
	}

	@SuppressWarnings("unchecked")
	private static synchronized <T> List<Class<T>> findClasses(File directory,
			String packageName, boolean checkSubpackages)
			throws ClassNotFoundException {
		List<Class<T>> classes = new ArrayList<Class<T>>(10);
		if (!directory.exists()) {
			return classes;
		}

		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory() && checkSubpackages) {
				assert !file.getName().contains(".");
				List<Class<T>> findClasses = findClasses(file, packageName
						+ "." + file.getName(), checkSubpackages);
				classes.addAll(findClasses);
			} else if (file.getName().endsWith(".class")) {
				classes.add((Class<T>) Class.forName(packageName
						+ "."
						+ file.getName().substring(0,
								file.getName().length() - 6)));
			}
		}
		return classes;
	}

	public static String packageNameConc(Class<?> clazz, String subPackage) {
		if (clazz == null) {
			return "";
		}
		return clazz.getPackage().getName()
				+ (subPackage.startsWith(".") ? subPackage : "." + subPackage);
	}

	/**
	 * 
	 * @param outer
	 * @param inner
	 * @return
	 */
	public static Rectangle setToCentralPosition(Rectangle outer,
			Rectangle inner) {
		if (outer == null | inner == null) {
			return new Rectangle();
		}
		if (outer.equals(inner)) {
			return inner;
		}

		inner.x = (int) (outer.x + ((outer.width * .5F) - (inner.width * .5F)));
		inner.y = (int) (outer.y + ((outer.height * .5F) - (inner.height * .5F)));
		return inner;
	}

	/**
	 * 
	 * @param outer
	 * @param inner
	 * @return
	 */
	public static Rectangle setToCentralPosition(Dimension outer,
			Dimension inner) {
		return setToCentralPosition(new Rectangle(outer), new Rectangle(inner));
	}

	private static Rectangle rectScreenSize = new Rectangle(0, 0,
			getDefaultToolkit().getScreenSize().width, getDefaultToolkit()
					.getScreenSize().height);

	public static Rectangle setToCentralPositionOnScreen(Rectangle inner) {
		return setToCentralPosition(rectScreenSize, inner);
	}

	public static Point setToCentralPositionOnScreen(Point p) {
		return setToCentralPositionOnRectangle(rectScreenSize, p);
	}

	public static Point setToCentralPositionOnRectangle(Rectangle r, Point p) {
		Rectangle inner = setToCentralPosition(r, new Rectangle(p));
		p.x = inner.x;
		p.y = inner.y;
		return p;
	}

	/**
	 * 
	 * @param input
	 * @param Algorithm
	 * @return
	 */
	public static String generateHash(String input, String Algorithm) {
		try {
			StringBuilder hash = new StringBuilder(10);
			MessageDigest sha = MessageDigest.getInstance(Algorithm);
			byte[] hashedBytes = sha.digest(input.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'a', 'b', 'c', 'd', 'e', 'f' };
			for (int idx = 0; idx < hashedBytes.length; ++idx) {
				byte b = hashedBytes[idx];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
			return hash.toString();
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(Commons.class.getName()).log(Level.SEVERE, null,
					ex);
		}
		return null;
	}

	/**
	 * 
	 * @param value
	 * @param to
	 * @return
	 */
	public synchronized static Object convertPOJO(Object to, Object value) {
		if (to instanceof String) {
			if (value instanceof String) {
				if (Integer.class.getName().equals(to)
						|| int.class.getName().equals(to)) {
					return Integer.parseInt((String) value);
				} else if (Float.class.getName().equals(to)
						|| float.class.getName().equals(to)) {
					return Float.parseFloat((String) value);
				} else if (Double.class.getName().equals(to)
						|| double.class.getName().equals(to)) {
					return Double.parseDouble((String) value);
				} else if (Boolean.class.getName().equals(to)
						|| boolean.class.getName().equals(to)) {
					return Boolean.parseBoolean((String) value);
				} else if (String.class.getName().equals(to)) {
					return value;
				}
			} else {
				return value;
			}
		}
		return null;
	}

	public synchronized static Object convertJPAObject(String value) {
		try {
			return Short.parseShort(value);
		} catch (NumberFormatException ex) {
			try {
				return Integer.parseInt(value);
			} catch (NumberFormatException ex1) {
				try {
					return Long.parseLong(value);
				} catch (NumberFormatException ex2) {
					try {
						return Double.parseDouble(value);
					} catch (NumberFormatException ex3) {
						try {
							return Float.parseFloat(value);
						} catch (NumberFormatException ex4) {
							try {
								if (value.equalsIgnoreCase("true")
										|| value.equalsIgnoreCase("false")) {
									return Boolean.parseBoolean(value);
								}
								throw new Exception();
							} catch (Exception ex5) {
								return String.valueOf(value);
							}
						}
					}
				}
			}
		}
	}

	private static final String osName = System.getProperty("os.name")
			.toLowerCase();
	private static final int WINDOWS_REPAINT_LEVEL = 2;
	private static final int UNIX_REPAINT_LEVEL = 1;

	public static void repaintAncestry(Container container, int x, int y,
			int w, int h) {
		repaintAncestry(container,
				osName.contains("windows") ? WINDOWS_REPAINT_LEVEL
						: UNIX_REPAINT_LEVEL, x, y, w, h);
	}

	private static void repaintAncestry(Container container, int depth, int x,
			int y, int w, int h) {
		if (container == null || depth == 0) {
			return;
		} else {
			try {
				container.repaint(x, y, w, h);
				repaintAncestry(container.getParent(), --depth, x, y, w, h);
			} catch (java.lang.NullPointerException ne) {
				return;
			}
		}
	}

	public static void repaintAncestry(Container container) {
		repaintAncestry(container,
				osName.contains("windows") ? WINDOWS_REPAINT_LEVEL
						: UNIX_REPAINT_LEVEL);
	}

	public static void repaintAncestry(Container container, int depth) {
		if (container == null || depth == 0) {
			return;
		} else {
			try {
				container.repaint();
				repaintAncestry(container.getParent(), --depth);
			} catch (java.lang.NullPointerException ne) {
				return;
			}
		}
	}

	public static void mountComponent(JComponent parent, JComponent child,
			boolean repaint) {
		while (parent.getComponentCount() > 1) {
			parent.remove(1);
		}
		parent.add(child);
		parent.revalidate();
		if (repaint) {
			Commons.repaintAncestry(parent);
		}
	}

	public static void mountComponent(JComponent parent, JComponent child) {
		mountComponent(parent, child, false);
	}

	public static float getRandomFloat() {
		return random.nextFloat();
	}

	public static void setTransparent(Component component) {
		if (component instanceof JPanel) {
			((JPanel) component).setOpaque(false);
		}
		if (component instanceof Container) {
			Container container = (Container) component;
			for (int i = 0, n = container.getComponentCount(); i < n; i++) {
				setTransparent(container.getComponent(i));
			}
		}
	}

	/**
     *
     */
	public static enum DateType {

		/**
         *
         */
		Short,
		/**
         *
         */
		Long
	};

	private static final Logger LOG = Logger.getLogger(Commons.class.getName());
}
