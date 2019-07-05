package indie.aang.paybot.utilities;

import java.io.File;
import java.io.FileOutputStream;

public class FileUtils {


    public static void createNoMediaFile() {
        FileOutputStream out = null;

        try {

            File ROOT = new File(AppConstants.ROOT_DIR);

            if (!ROOT.exists())
                ROOT.mkdirs();


            File thumbnailsDir = new File(ROOT + File.separator + ".thumbnails");
            if (!thumbnailsDir.exists())
                thumbnailsDir.mkdirs();

            File file = new File(ROOT + File.separator, ".nomedia");
            if (!file.exists()) {
                out = new FileOutputStream(file);
                out.write(0);
                out.close();


                AppLogger.i("FileUtils", "No media file created!  " + file);
            } else {
                AppLogger.i("FileUtils", "No media already exists!!!!!!  " + file);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
