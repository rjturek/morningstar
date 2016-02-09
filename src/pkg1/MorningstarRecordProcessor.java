package pkg1;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class MorningstarRecordProcessor {

    private Integer fileSize = -1;
    private boolean fileIsComplete = false;
    private Map<String, BigDecimal> stockMap = new HashMap<String, BigDecimal>();

    /**
     *
     * @param input
     * @return File record number for this record.  null if file size is exceeded.
     * @throws Exception
     */

    public Integer processRecord(String input) throws Exception {
        if (isFileComplete()) {
            return null;
        }

        String[] elements = input.split(",");
        Integer thisRecNum = Integer.parseInt(elements[0].trim());
        Integer totalNumRecs = Integer.parseInt(elements[1].trim());
        String stock = elements[2];
        BigDecimal price = new BigDecimal(elements[3].trim());

        if (fileSize == -1) {
            fileSize = totalNumRecs;
        }
        else if (fileSize != totalNumRecs) {
            throw new Exception("Record " + thisRecNum + "has erroneaous totalNumRecs");
        }
        else if (thisRecNum == fileSize) {
            fileIsComplete = true;
        }

        return thisRecNum;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public Boolean isFileComplete() {
        return fileIsComplete;
    }

    public String getFileReport() {
        return "Some report stuff goes here";
    }

    public static void main(String[] args) throws Exception {

        // Sample data
        String[] inRecs = new String[]{
                "1, 4, AAPL, 100.2",
                "2, 4, TROW, 60.5",
                "3, 4, IBM,  23.125",
                "4, 4, CSX,  38.5",
                "This record won't be processed blah, blech"
        };

        // Pretend we are receiving records from the stream - drive the MorningstarRecordProcessor class instance
        MorningstarRecordProcessor processor = new MorningstarRecordProcessor();

        for (String rec : inRecs) {
            if (processor.isFileComplete()) {
                break;
            }
            Integer recNum = processor.processRecord(rec);
            System.out.println(recNum + " out of " + processor.getFileSize() + " records processed.");
        }

        System.out.println();
        System.out.println(processor.getFileReport());
    }
}

