import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

import java.io.File;
import java.io.IOException;

/**
 * Created by Phoenix on 10/24/13.
 */
public class MediaHub {
    static int count=0;
    public static void main(String[] args) {
        final File folder = new File("C:\\Users\\Phoenix\\Downloads");
        listFilesForFolder(folder);
//        System.out.println("Hello World");
    }

    public static void listFilesForFolder(final File folder) {
        String fileName;
        AudioFile audioFile;
        AudioHeader audioHeader;
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                fileName = fileEntry.getName();
                if (fileName.endsWith("mp3")) {
                    try {
                        audioFile = AudioFileIO.read(fileEntry);
                        Tag tag=audioFile.getTag();
                        audioHeader=audioFile.getAudioHeader();
                        System.out.println((++count)+". "+tag.getFirst(FieldKey.TITLE)+"-> "+audioHeader.getTrackLength());
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

    }


}
