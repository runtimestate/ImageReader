package image.reader.ocr;

import java.io.InputStream;
import java.net.URL;

import org.apache.commons.lang.StringUtils;

public class Main {

	/**
	 * For example, Shanghai. readText(new
	 * URL("http://www.shjtaq.com/Server3/validatecode
	 * .asp?Action=RELOAD&m=0.7939826503861696").openStream());
	 * 
	 * @param is
	 *            InputStream of the image file.
	 * @return Text of the image.
	 * @throws Exception
	 */
	public static String readText(InputStream is) throws Exception {
		IReader reader = new ImageReader();
		String text = format(reader.read(is));
		System.out.println(text);
		return text;
	}

	/**
	 * For example, Shanghai. readText(new
	 * URL("http://www.shjtaq.com/Server3/validatecode
	 * .asp?Action=RELOAD&m=0.7939826503861696").openStream(), "bpm");
	 * 
	 * @param is
	 *            InputStream of the image file.
	 * @param imageType
	 *            ImageType. For example "bpm"
	 * @return Text of the image.
	 * @throws Exception
	 */
	public static String readText(InputStream is, String imageType)
			throws Exception {
		IReader reader = new ImageReader();
		String text = format(reader.read(is, imageType));
		System.out.println(text);
		return text;
	}

	/**
	 * For example, Shanghai. readText(new
	 * URL("http://www.shjtaq.com/Server3/validatecode
	 * .asp?Action=RELOAD&m=0.7939826503861696").openStream(), "SHJTAQ");
	 * 
	 * @param is
	 *            InputStream of the image file.
	 * @param district
	 *            District.
	 * @return Text of the image.
	 * @throws Exception
	 */
	public static String readByDistrict(InputStream is, String district)
			throws Exception {
		IReader reader = (IReader) Class
				.forName("image.reader.ocr." + district).newInstance();
		String text = format(reader.read(is));
		System.out.println(text);
		return text;
	}

	private static String format(String str) {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		StringBuffer sb = new StringBuffer(str.length());
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (Character.isDigit(c) || Character.isLetter(c)) {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		try {
			String url = "";
			String imageType = "";
			String district = "";

			for (int i = 0; i < args.length; i++) {
				if ("-url".equalsIgnoreCase(args[i])) {
					url = args[i + 1];
				}
				if ("-imageType".equalsIgnoreCase(args[i])) {
					imageType = args[i + 1];
				}
				if ("-district".equalsIgnoreCase(args[i])) {
					district = args[i + 1];
				}
			}

			if (!"".endsWith(url) && "".endsWith(district)) {
				readText(new URL(url).openStream());
			}

			if (!"".endsWith(url) && !"".endsWith(imageType)) {
				readText(new URL(url).openStream(), imageType);
			}

			if (!"".endsWith(url) && !"".endsWith(district)) {
				readByDistrict(new URL(url).openStream(), district);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
