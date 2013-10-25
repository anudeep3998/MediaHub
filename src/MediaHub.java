import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.datatype.DataTypes;
import org.jaudiotagger.tag.id3.ID3v23Frame;
import org.jaudiotagger.tag.id3.framebody.FrameBodyAPIC;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import static org.jaudiotagger.tag.FieldKey.*;

/**
 * Created by Phoenix on 10/24/13.
 */
public class MediaHub {
    static int count = 0;
    private final static Logger Log = Logger.getLogger(MediaHub.class.getName());

    public static void main(String[] args) throws IOException {
        final File folder = new File("C:\\Users\\user\\Music");
        //listFilesForFolder(folder);
//        System.out.println("Hello World");
        SQLite sqLite = new SQLite();
        sqLite.setupConnection();
    }

    public static void listFilesForFolder(final File folder) throws IOException {
        String fileName;
        AudioFile audioFile;
        AudioHeader audioHeader;
        Tag tag;
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                fileName = fileEntry.getName();
                if (fileName.endsWith("mp3")) {
                    try {
                        audioFile = AudioFileIO.read(fileEntry);
                        tag = audioFile.getTag();
                        audioHeader = audioFile.getAudioHeader();
                        //audioFile.getFile().getAbsolutePath();
                        TagField coverArtField =
                                tag.getFirstField(COVER_ART);
                        FrameBodyAPIC body = (FrameBodyAPIC) ((ID3v23Frame) coverArtField).getBody();
                        byte[] imageRawData = (byte[]) body.getObjectValue(DataTypes.OBJ_PICTURE_DATA);
                        BufferedImage bi = ImageIO.read(ImageIO.createImageInputStream(new
                                ByteArrayInputStream(imageRawData)));

                        System.out.println((++count) + ". " + tag.getFirst(TITLE) + "-> " + audioHeader.getTrackLength() + " is at -> "
                                + audioFile.getFile().getAbsolutePath());
                    } catch (CannotReadException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (TagException e) {
                        e.printStackTrace();
                    } catch (ReadOnlyFileException e) {
                        e.printStackTrace();
                    } catch (InvalidAudioFrameException e) {
                        e.printStackTrace();
                    }
                    //System.out.println(fileName);
                }
            }
        }
        Desktop.getDesktop().open(new File("C:\\Users\\user\\Music\\04 apologize.mp3"));

    }


}

