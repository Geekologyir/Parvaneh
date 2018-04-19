package ir.geek.parvaneh.dataClasses;

public class SportPlanItem {
    String title;
    int order;
    int duration;
    boolean isRest;

    public SportPlanItem(int id){
        //ToDo: Get from db

        if (id == 1){
            title = "ورزش 1";
            order = 0;
            duration = 15;
            isRest= false;
        } else if (id == 2){
            title = "استراحت";
            order = 1;
            duration = 5;
            isRest= true;
        } else if (id == 3){
            title = "ورزش 2";
            order = 2;
            duration = 15;
            isRest= false;
        }else{
            title = "آی دی ناشناس";
            order = 3;
            duration = 15;
            isRest= false;
        }
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isRest() { return isRest; }

}
