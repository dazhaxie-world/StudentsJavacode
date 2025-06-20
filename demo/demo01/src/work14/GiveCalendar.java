package work14;

import java.time.LocalDate;

public class GiveCalendar {
    public LocalDate[] getCalendar(LocalDate currentDate) {
        LocalDate firstDayOfMonth = currentDate.withDayOfMonth(1);
        int daysInMonth = firstDayOfMonth.lengthOfMonth();
        LocalDate[] calendar = new LocalDate[daysInMonth];
        for (int i = 0; i < daysInMonth; i++) {
            calendar[i] = firstDayOfMonth.plusDays(i);
        }
        return calendar;
    }
}
