package image.reader.ocr;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

/**
 * 
 * Shanghai
 * 
 */
public class SHJTAQ extends ImageReader {

	private static final String DISTRICT = "SHJTAQ";

	@Override
	public String read(InputStream is) throws Exception {
		File tmpFile = File.createTempFile("img", "." + IMAGE_BMP);

		OutputStream out = new FileOutputStream(tmpFile);
		IOUtils.copy(is, out);
		IOUtils.closeQuietly(out);

		return new OCR().recognizeText(tmpFile, IMAGE_BMP, DISTRICT);
	}

}
