package image.reader.ocr;

import java.io.InputStream;

public interface IReader {

	public static final String IMAGE_FORMAT = "jpg";
	public static final String IMAGE_BMP = "bmp";
	public static final String IMAGE_JPG = "jpg";
	public static final String IMAGE_PNG = "png";
	public static final String IMAGE_TIF = "tif";

	public static final String ENG = "eng";
	public static final String CHI_SIM = "chi_sim";

	public String read(InputStream is) throws Exception;

	public String read(InputStream is, String imageType) throws Exception;
}
