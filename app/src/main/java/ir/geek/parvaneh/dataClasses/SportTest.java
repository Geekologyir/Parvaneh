package ir.geek.parvaneh.dataClasses;

public class SportTest {
    String title;
    String imageFileName;
    int duration;
    byte difficulty;
    String videoFileName;
    String description;

    public SportTest(int id) {
        if (id == 1) {
            title = "تست شنا";
            imageFileName = "swim_bg.jpg";
        } else if (id == 2) {
            title = "تست دو";
            imageFileName = "running_bg.jpg";
        }if (id == 3) {
            title = "تست هوازی";
            imageFileName = "aerobic_bg.jpg";
        }
    }

    public SportTest(int id, String isMassageView) {
        this(id);
        if (id == 1) {
            duration = 90;
            difficulty = 0;
            videoFileName = "video1.mp4";
            description = "متن توضیحات تست شنا";
        } else if (id == 2) {
            duration = 120;
            difficulty = 1;
            videoFileName = "video1.mp4";
            description = "متن توضیحات تست دو";
        } else if (id == 3) {
            duration = 160;
            difficulty = 2;
            videoFileName = "video1.mp4";
            description = "متن توضیحات تست هوازی";
        }
        //duration
        //difficulty
        //videoFileName
        //description

    }

    public String getTitle() {
        return title;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public String getDuration() {
        return duration+"";
    }

    public String getVideoFileName() {
        return videoFileName;
    }

    public byte getDifficulty() {
        return difficulty;
    }

    public String getDescription() {
        return description;
    }
}
