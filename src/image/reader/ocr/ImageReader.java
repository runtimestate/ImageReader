package image.reader.ocr;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;

public class ImageReader implements IReader {

	@Override
	public String read(InputStream is) throws Exception {
		return read(is, IMAGE_PNG);
	}

	@Override
	public String read(InputStream is, String imageType) throws Exception {
		File tmpFile = File.createTempFile("img", "." + imageType);

		OutputStream out = new FileOutputStream(tmpFile);
		IOUtils.copy(is, out);
		IOUtils.closeQuietly(out);

		ImageFilter imageFilter = new ImageFilter(ImageIO.read(tmpFile));

		return new OCR().recognizeText(
				ImageIOHelper.createImage(imageFilter.lineGrey()), IMAGE_TIF,
				ENG);
	}

}
