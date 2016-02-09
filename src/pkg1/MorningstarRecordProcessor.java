package pkg1;

public class MorningstarRecordProcessor {

    private Integer fileSize = -1;

    public Boolean processRecord(String input) {
        String[] elements = input.split(",");
        for (String elem : elements) {
            System.out.println(elem);
        }
        return false;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public Boolean isFileComplete() {
        return false;
    }

    public String getFileReport() {
        return "Here is your report";
    }

    public static void main(String[] args) {

        System.out.println("Starting main");

        String[] inRecs = new String[]{
                "1, 4, AAPL, 100.2",
                "2, 4, TROW, 60.5",
                "3, 4, IBM,  23.125",
                "4, 4, CSX,  38.5",
                "This record won't be processed blah, blech"
        };

        MorningstarRecordProcessor processor = new MorningstarRecordProcessor();

        System.out.println("File size before first record " + processor.getFileSize());

        for (String rec : inRecs) {
            processor.processRecord(rec);
            if (processor.isFileComplete()) {
                break;
            }
        }
        System.out.println(processor.getFileReport());
    }
}

