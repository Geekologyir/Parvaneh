package ir.geek.parvaneh.dataClasses;

public class Massage {
    String title;
    String imageFileName;
    int duration;
    byte difficulty;
    String videoFileName;
    String description;

    public Massage(int id) {
        if (id == 1) {
            title = "ماساژ یک";
            imageFileName = "massage1.png";
        } else if (id == 2) {
            title = "ماساژ دو";
            imageFileName = "massage2.png";
        }if (id == 3) {
            title = "ماساژ سه";
            imageFileName = "massage3.png";
        }
    }

    public Massage(int id, String isMassageView) {
        this(id);
        if (id == 1) {
            duration = 90;
            difficulty = 0;
            videoFileName = "video1.mp4";
            description = "متن توضیحات ماساژ 1";
        } else if (id == 2) {
            duration = 120;
            difficulty = 1;
            videoFileName = "video1.mp4";
            description = "متن توضیحات ماساژ 2";
        } else if (id == 3) {
            duration = 160;
            difficulty = 2;
            videoFileName = "video1.mp4";
            description = "متن توضیحات ماساژ 3";
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
