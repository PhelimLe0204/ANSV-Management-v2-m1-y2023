package vn.ansv.management.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import vn.ansv.management.dto.Layout.LayoutMenuCategoryDTO;
import vn.ansv.management.service.MenuCategoryService;

@Controller
public class BaseController {

    @Autowired // Inject "MenuCategoryService" - Dependency Injection
    private MenuCategoryService menuCategoryService;

    public ModelAndView _mvShare = new ModelAndView();

    public ModelAndView Init() {
        List<LayoutMenuCategoryDTO> menuCategoryLayout = menuCategoryService.findAllLayout();
        _mvShare.addObject("menuCategoryLayout", menuCategoryLayout);
        return _mvShare;
    }

    // Hàm lấy số tuần
    public int getWeekOfYear(Date date) {

        int current_year = Calendar.getInstance().get(Calendar.YEAR); // Get the curent year

        LocalDate dateTest = LocalDate.of(current_year, Month.FEBRUARY, 01);
        // Xác định ngày đầu tiên trong năm
        LocalDate firstDayOfYear = dateTest.with(TemporalAdjusters.firstDayOfYear());
        // System.out.println("--- firstDayOfYear: " + firstDayOfYear);
        DayOfWeek dayOfWeek = firstDayOfYear.getDayOfWeek(); // Xác định ngày thứ mấy trong tuần
        // In ra giá trị nằm trong khoảng từ 1 đến 7 tương ứng thứ 2 - Chủ nhật
        // System.out.println("--- dayOfWeek.getValue(): " + dayOfWeek.getValue());
        // System.out.println("--- First week of this year has: " + (8 -
        // dayOfWeek.getValue()) + " days.");

        /* === Determine week of year === */
        Calendar calendar = new GregorianCalendar();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        
        // Xác định tuần đầu tiên trong năm có mấy ngày
        int daysOfFirstWeek = 8 - dayOfWeek.getValue();
        if (daysOfFirstWeek < 2) {
            calendar.setMinimalDaysInFirstWeek(2);
        } else {
            calendar.setMinimalDaysInFirstWeek(8 - dayOfWeek.getValue());
        }
        
        calendar.setTime(date);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);

        return week;
    }
}
