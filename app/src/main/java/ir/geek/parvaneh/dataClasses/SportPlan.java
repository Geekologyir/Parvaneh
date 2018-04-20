package ir.geek.parvaneh.dataClasses;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;


public class SportPlan {
    private String title;
    private Date startDate;
    private Date finishDate;
    private byte periodType;
    private List<SportPlanItem> items;
    private int duration;

    // ToDo : Select title , datatime , duration from sport_plans where id = id
    public SportPlan(int id) {


        items = new ArrayList<SportPlanItem>();
        // ToDo : Select name from sport_plan_items where spid = id (items must be ordered)

        if (id == 1) {
            title = "ورزش تست 1";
            try {
                startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2018-04-12 21:50:20");
                finishDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2018-04-13 21:50:20");
            } catch (Exception e) {
                //ToDo : (Vahid) !!!!!!
            }
            periodType = 0;
            items.add(new SportPlanItem(1));
            items.add(new SportPlanItem(2));
            duration = 0;
            for (SportPlanItem item : items) {
                duration += item.duration;
            }
        } else if (id == 2) {
            title = "ورزش تست 2";
            try {
                startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2018-04-26 21:50:20");
                finishDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2018-05-07 21:50:20");
            } catch (Exception e) {
                //ToDo : (Vahid) !!!!!!
            }
            periodType = 1;
            items.add(new SportPlanItem(1));
            items.add(new SportPlanItem(3));
            duration = 0;
            for (SportPlanItem item : items) {
                duration += item.duration;
            }
        } else if (id == 3) {
            title = "ورزش تست 3";
            try {
                startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2018-04-10 21:50:20");
                finishDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2018-04-20 21:50:20");
            } catch (Exception e) {
                //ToDo : (Vahid) !!!!!!
            }
            periodType = 3;
            items.add(new SportPlanItem(1));
            items.add(new SportPlanItem(2));
            items.add(new SportPlanItem(3));
            duration = 0;
            for (SportPlanItem item : items) {
                duration += item.duration;
            }
        } else {
            title =
                    "آی دی ناشناس";
            try {
                startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2018-04-10 21:50:20");
                finishDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2018-04-20 21:50:20");
            } catch (Exception e) {
                //ToDo : (Vahid) !!!!!!
            }
            periodType = 3;
            items.add(new SportPlanItem(1));
            items.add(new SportPlanItem(2));
            items.add(new SportPlanItem(3));
            duration = 0;
            for (SportPlanItem item : items) {
                duration += item.duration;
            }
        }
    }

    public String getTitle() {
        return title;
    }

    public String getStartDate() {
        PersianDate now = new PersianDate();
        PersianDate shStartDate = new PersianDate(startDate);
        String dateStr;

        if (shStartDate.getShYear() == now.getShYear() && shStartDate.getShMonth() == now.getShMonth() && shStartDate.getShDay() == now.getShDay()) {
            dateStr = " امروز , ساعت" + new PersianDateFormat("H:i").format(shStartDate);
        } else if (shStartDate.getShYear() == now.getShYear() && shStartDate.getShMonth() == now.getShMonth() && shStartDate.getShDay() == now.getShDay() + 1) {
            dateStr = " فردا , ساعت" + new PersianDateFormat("H:i").format(shStartDate);
        } else {
            dateStr = new PersianDateFormat("Y/m/d").format(shStartDate) + " ساعت " + new PersianDateFormat("H:i").format(shStartDate);
        }

        return dateStr;
    }

    public String getFinishDate() {
        PersianDate now = new PersianDate();
        PersianDate shFinishDate = new PersianDate(finishDate);
        String dateStr;

        if (shFinishDate.getShYear() == now.getShYear() && shFinishDate.getShMonth() == now.getShMonth() && shFinishDate.getShDay() == now.getShDay()) {
            dateStr = " امروز , ساعت" + new PersianDateFormat("H:i").format(shFinishDate);
        } else if (shFinishDate.getShYear() == now.getShYear() && shFinishDate.getShMonth() == now.getShMonth() && shFinishDate.getShDay() == now.getShDay() + 1) {
            dateStr = " فردا , ساعت" + new PersianDateFormat("H:i").format(shFinishDate);
        } else {
            dateStr = new PersianDateFormat("Y/m/d").format(shFinishDate) + " ساعت " + new PersianDateFormat("H:i").format(shFinishDate);
        }

        return dateStr;
    }

    public byte getPeriodType() {
        return periodType;
    }

    public int getDuration() {
        return duration;
    }

    public List<SportPlanItem> getItems() {
        return items;
    }

    private List<PersianDate> getAllDates() {
        List<PersianDate> dates = new ArrayList<PersianDate>();
        PersianDate varDate = new PersianDate(startDate);
        PersianDate persianFinishDate = new PersianDate(finishDate);
        while (varDate.after(persianFinishDate)) {
            Log.d("index" + dates.size(),new PersianDateFormat("Y/m/d").format(varDate));
            dates.add(new PersianDate(varDate.toDate()));

            if (periodType == 0)
                break;
            else
                switch (periodType) {
                    case 1:
                        varDate.addDay(1);
                        break;
                    case 2:
                        varDate.addDay(2);
                        break;
                    case 3:
                        varDate.addDay(7);
                        break;
                }
        }
        return dates;
    }

    // ToDo: (Vahid) maybe have a bug ! when there is no nextDate , it returns , now
    public String nextDate() {
        List<PersianDate> dates = getAllDates();
        PersianDate nextDate;
        PersianDate now = new PersianDate();
        nextDate = null;
        for (int i = 0; i < dates.size(); i++) {
            if (dates.get(i).before(now)) {
                nextDate = dates.get(i);
                break;
            }
        }
        String dateStr;
        if (nextDate == null) {
            dateStr = "تمرین انجام شده";
        } else {
            if (nextDate.getShYear() == now.getShYear() && nextDate.getShMonth() == now.getShMonth() && nextDate.getShDay() == now.getShDay()) {
                dateStr = " امروز , ساعت" + new PersianDateFormat("H:i").format(nextDate);
            } else if (nextDate.getShYear() == now.getShYear() && nextDate.getShMonth() == now.getShMonth() && nextDate.getShDay() == now.getShDay() + 1) {
                dateStr = " فردا , ساعت" + new PersianDateFormat("H:i").format(nextDate);
            } else {
                dateStr = new PersianDateFormat("Y/m/d").format(nextDate) + " ساعت " + new PersianDateFormat("H:i").format(nextDate);
            }
        }
        return dateStr;
    }

    public String getExercisesStr() { // ToDo: (For Vahid Taheri) Make exercises sortable by order then sort them before converting to string
        String exercisesStr = "";
        for (SportPlanItem item : items) {
            if (!item.isRest) {
                if (item.title == items.get(items.size() - 1).title || (item.title == items.get(items.size() - 2).title && items.get(items.size() - 1).isRest))
                    exercisesStr += item.title;
                else {
                    exercisesStr += item.title + " , ";
                }
            }
        }
        return exercisesStr;
    }

    public String getDurationStr() {
        return duration + " " + "دقیقه";
    }

    private PersianDate gregorianToJalali(String date) { // Example : 2018-03-23 21:50:20
        Date gregorian = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            gregorian = simpleDateFormat.parse(date);
        } catch (Exception e) {
            //ToDo : (Vahid) !!!!!!
        }

        return new PersianDate(gregorian);
    }
}
