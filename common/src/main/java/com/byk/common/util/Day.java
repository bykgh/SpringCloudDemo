/*
 * (C) 2004 - Geotechnical Software Services
 *
 * This code is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free
 * Software Foundation, Inc., 59 Temple Place - Suite 330, Boston,
 * MA  02111-1307, USA.
 */
//package no.geosoft.cc.util;
package com.byk.common.util;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


/**
 * A time-less Date class for basic date arithmetics.
 * <p>
 * Thanks to Paul Hill @ xmission.com for valuable contribution.
 */
public class Day
        implements Comparable, Cloneable, Serializable {
    protected Calendar calendar_;


    /**
     * Initialize the internal calendar instance.
     *
     * @param year       Year of new day.
     * @param month      Month of new day.
     * @param dayOfMonth Day of month of new day.
     */
    private void initialize(int year, int month, int dayOfMonth) {
        calendar_ = Calendar.getInstance();
        calendar_.setLenient(true);
        calendar_.setFirstDayOfWeek(Calendar.MONDAY);
//    calendar_.setTimeZone (TimeZone.getTimeZone ("GMT"));
        calendar_.setTimeZone(TimeZone.getTimeZone("GMT+8"));  // �й�ʱ��
        set(year, month, dayOfMonth);
    }


    /**
     * Create a new day.
     * The day is lenient meaning that illegal day parameters can be
     * specified and results in a recomputed day with legal month/day
     * values.
     *
     * @param year       Year of new day.
     * @param month      Month of new day (0-11)
     * @param dayOfMonth Day of month of new day (1-31)
     */
    public Day(int year, int month, int dayOfMonth) {
        initialize(year, month, dayOfMonth);
    }


    /**
     * Create a new day, specifying the year and the day of year.
     * The day is lenient meaning that illegal day parameters can be
     * specified and results in a recomputed day with legal month/day
     * values.
     *
     * @param year      Year of new day.
     * @param dayOfYear 1=January 1, etc.
     */
    public Day(int year, int dayOfYear) {
        initialize(year, Calendar.JANUARY, 1);
        calendar_.set(Calendar.DAY_OF_YEAR, dayOfYear);
    }


    /**
     * Create a new day representing the day of creation
     * (according to the setting of the current machine).
     */
    public Day() {
        // Now (in the currenct locale of the client machine)
        Calendar calendar = Calendar.getInstance();

        // Prune time part
        initialize(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
    }


    /**
     * Create a new day based on a java.util.Calendar instance.
     * NOTE: The time component from calendar will be pruned.
     *
     * @param calendar Calendar instance to copy.
     */
    public Day(Calendar calendar) {
        this(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
    }


    /**
     * Create a new day based on a java.util.Date instance.
     * NOTE: The time component from date will be pruned.
     *
     * @param date Date instance to copy.
     */
    public Day(Date date) {
        // Create a calendar based on given date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // Extract date values and use these only
        initialize(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
    }


    /**
     * Create a new day based on a time value.
     * Time is milliseconds since "the Epoch" (1.1.1970).
     * NOTE: The time component from time will be pruned.
     *
     * @param time Milliseconds since "the Epoch".
     */
    public Day(long time) {
        this(new Date(time));
    }


    /**
     * Create a new day as a copy of the specified day.
     *
     * @param day Day to clone.
     */
    public Day(Day day) {
        this(day.getYear(), day.getMonth(), day.getDayOfMonth());
    }


    /**
     * Create a clone of this day.
     *
     * @return This day cloned.
     */
    @Override
    public Object clone() {
        return new Day(this);
    }


    /**
     * A more explicit front-end to the Day() constructor which return a day
     * object representing the day of creation.
     *
     * @return A day instance representing today.
     */
    public static Day today() {
        return new Day();
    }


    /**
     * Return a Calendar instance representing the same day
     * as this instance. For use by secondary methods requiring
     * java.util.Calendar as input.
     *
     * @return Calendar equivalent representing this day.
     */
    public Calendar getCalendar() {
        return (Calendar) calendar_.clone();
    }


    /**
     * Return a Date instance representing the same date
     * as this instance. For use by secondary methods requiring
     * java.util.Date as input.
     *
     * @return Date equivalent representing this day.
     */
    public Date getDate() {
        return getCalendar().getTime();
    }


    /**
     * Compare this day to the specified day. If object is
     * not of type Day a ClassCastException is thrown.
     *
     * @param object Day object to compare to.
     * @return @see Comparable#compareTo(Object)
     * @throws ClassCastException If object is not of type Day.
     */
    @Override
    public int compareTo(Object object) {
        Day day = (Day) object;
        return calendar_.getTime().compareTo(day.calendar_.getTime());
    }


    /**
     * Return true if this day is after the specified day.
     *
     * @param date Day to compare to.
     * @return True if this is after day, false otherwise.
     */
    public boolean isAfter(Day day) {
        return calendar_.after(day.calendar_);
    }


    /**
     * Return true if this day is before the specified day.
     *
     * @param date Day to compare to.
     * @return True if this is before day, false otherwise.
     */
    public boolean isBefore(Day day) {
        return calendar_.before(day.calendar_);
    }


    /**
     * Return true if this day equals (represent the same date)
     * as the specified day.
     *
     * @param date Day to compare to.
     * @return True if this equals day, false otherwise.
     */
    public boolean equals(Day day) {
        return calendar_.equals(day.calendar_);
    }


    /**
     * Overload required as default definition of equals() has changed.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return calendar_.hashCode();
    }


    /**
     * Set date of this day.
     * The day is lenient meaning that illegal day parameters can be
     * specified and results in a recomputed day with legal month/day
     * values.
     *
     * @param year       Year of this day.
     * @param month      Month of this day (0-11).
     * @param dayOfMonth Day of month of this day (1-31).
     */
    public void set(int year, int month, int dayOfMonth) {
        setYear(year);
        setMonth(month);
        setDayOfMonth(dayOfMonth);
    }


    /**
     * Return year of this day.
     *
     * @return Year of this day.
     */
    public int getYear() {
        return calendar_.get(Calendar.YEAR);
    }


    /**
     * Set year of this day.
     *
     * @param year New year of this day.
     */
    public void setYear(int year) {
        calendar_.set(Calendar.YEAR, year);
    }


    /**
     * Return month of this day. The result must be compared to Calendar.JANUARY,
     * Calendar.FEBRUARY, etc.
     *
     * @return Month of this day.
     */
    public int getMonth() {
        return calendar_.get(Calendar.MONTH);
    }


    /**
     * Return the 1-based month number of the month of this day.
     * 1 = January, 2 = February and so on.
     *
     * @return Month number of this month
     */
    public int getMonthNo() {
        // It is tempting to return getMonth() + 1 but this is conceptually
        // wrong, as Calendar month is an enumeration and the values are tags
        // only and can be anything.
        switch (getMonth()) {
            case Calendar.JANUARY:
                return 1;
            case Calendar.FEBRUARY:
                return 2;
            case Calendar.MARCH:
                return 3;
            case Calendar.APRIL:
                return 4;
            case Calendar.MAY:
                return 5;
            case Calendar.JUNE:
                return 6;
            case Calendar.JULY:
                return 7;
            case Calendar.AUGUST:
                return 8;
            case Calendar.SEPTEMBER:
                return 9;
            case Calendar.OCTOBER:
                return 10;
            case Calendar.NOVEMBER:
                return 11;
            case Calendar.DECEMBER:
                return 12;
        }

        // This will never happen
        return 0;
    }


    /**
     * Set month of this day. January = 0, February = 1, etc.
     * Illegal month values will result in a recomputation of
     * year and a resetting of month to a valid value.
     * I.e. setMonth(20), will add 1 year to day and set month
     * to 8.
     *
     * @param month New month of this day.
     */
    public void setMonth(int month) {
        calendar_.set(Calendar.MONTH, month);
    }


    /**
     * Return day of month of this day.
     * NOTE: First day of month is 1 (not 0).
     *
     * @return Day of month of this day.
     */
    public int getDayOfMonth() {
        return calendar_.get(Calendar.DAY_OF_MONTH);
    }


    /**
     * Set day of month of this date. 1=1st  2=2nd, etc.
     * Illegal day values will result in a recomputation of
     * month/year and a resetting of day to a valid value.
     * I.e. setDayOfMonth(33), will add 1 month to date and
     * set day to 5, 4, 3 or 2 depending on month/year.
     *
     * @param dayOfMonth New day of month of this day.
     */
    public void setDayOfMonth(int dayOfMonth) {
        calendar_.set(Calendar.DAY_OF_MONTH, dayOfMonth);
    }


    /**
     * Return the day number of year this day represents.
     * January 1 = 1 and so on.
     *
     * @return day number of year.
     */
    public int getDayOfYear() {
        return calendar_.get(Calendar.DAY_OF_YEAR);
    }


    /**
     * Return the day of week of this day.
     * NOTE: Must be compared to Calendar.MONDAY, TUSEDAY etc.
     *
     * @return Day of week of this day.
     */
    public int getDayOfWeek() {
        return calendar_.get(Calendar.DAY_OF_WEEK);
    }


    /**
     * Return the day number of week of this day, where
     * Monday=1, Tuesday=2, ... Sunday=7.
     *
     * @return Day number of week of this day.
     */
    public int getDayNumberOfWeek() {
        return getDayOfWeek() == Calendar.SUNDAY ?
                7 : getDayOfWeek() - Calendar.SUNDAY;
    }


    /**
     * Return the week number of year, this day
     * belongs to. 1st=1 and so on.
     *
     * @return Week number of year of this day.
     */
    public int getWeekOfYear() {
        return calendar_.get(Calendar.WEEK_OF_YEAR);
    }


    /**
     * Add a number of days to this day. Subtracting a number of
     * days can be done by a negative argument to addDays() or calling
     * subtractDays() explicitly.
     *
     * @param nDays Number of days to add.
     */
    public void addDays(int nDays) {
        calendar_.add(Calendar.DAY_OF_MONTH, nDays);
    }


    /**
     * Subtract a number of days from this day
     *
     * @param nDays Number of days to subtract.
     */
    public void subtractDays(int nDays) {
        addDays(-nDays);
    }


    /**
     * Add a number of months to this day. The actual number of days added
     * depends on the staring day. Subtracting a number of months can be done
     * by a negative argument to addMonths() or calling subtactMonths()
     * explicitly.
     * NOTE: addMonth(n) m times will in general give a different result
     * than addMonth(m*n). Add 1 month to January 31, 2005 will give
     * February 28, 2005.
     *
     * @param nMonths Number of months to add.
     */
    public void addMonths(int nMonths) {
        calendar_.add(Calendar.MONTH, nMonths);
    }


    /**
     * Subtract a number of months from this day
     *
     * @param nDays Number of days to subtract.
     * @see #addMonths(int).
     */
    public void subtractMonths(int nMonths) {
        addMonths(-nMonths);
    }


    /**
     * Add a number of years to this day. The actual
     * number of days added depends on the starting day.
     * Subtracting a number of years can be done by a negative argument to
     * addYears() or calling subtractYears explicitly.
     *
     * @param nYears Number of years to add.
     */
    public void addYears(int nYears) {
        calendar_.add(Calendar.YEAR, nYears);
    }


    /**
     * Subtract a number of years from this day
     *
     * @param nYears Number of years to subtract.
     * @see #addYears(int).
     */
    public void subtractYears(int nYears) {
        addYears(-nYears);
    }


    /**
     * Return the number of days in the year of this day.
     *
     * @return Number of days in this year.
     */
    public int getDaysInYear() {
        return calendar_.getActualMaximum(Calendar.DAY_OF_YEAR);
    }


    /**
     * Return true if the year of this day is a leap year.
     *
     * @return True if this year is a leap year, false otherwise.
     */
    public boolean isLeapYear() {
        return getDaysInYear() == calendar_.getMaximum(Calendar.DAY_OF_YEAR);
    }


    /**
     * Return true if the specified year is a leap year.
     *
     * @param year Year to check.
     * @return True if specified year is leap year, false otherwise.
     */
    public static boolean isLeapYear(int year) {
        return (new Day(year, Calendar.JANUARY, 1)).isLeapYear();
    }


    /**
     * Return the number of days in the month of this day.
     *
     * @return Number of days in this month.
     */
    public int getDaysInMonth() {
        return calendar_.getActualMaximum(Calendar.DAY_OF_MONTH);
    }


    /**
     * Get default locale name of this day ("Monday", "Tuesday", etc.
     *
     * @return Name of day.
     */
    public String getDayName() {
        switch (getDayOfWeek()) {
            case Calendar.MONDAY:
                return "Monday";
            case Calendar.TUESDAY:
                return "Tuesday";
            case Calendar.WEDNESDAY:
                return "Wednesday";
            case Calendar.THURSDAY:
                return "Thursday";
            case Calendar.FRIDAY:
                return "Friday";
            case Calendar.SATURDAY:
                return "Saturday";
            case Calendar.SUNDAY:
                return "Sunday";
        }

        // This will never happen
        return null;
    }


    /**
     * Return number of days between two days.
     * if day>self return a positive number of days.
     * if day<self return a negative number of days
     *
     * @param date  The day to compare to.
     * @return Number of days between this and day.
     */
    private final static long DAY_MILLIS = (1000 * 60 * 60 * 24);

    public int daysBetween(Day day) {
//		 long millisBetween = Math.abs (calendar_.getTime().getTime() -
//		 day.calendar_.getTime().getTime());
//		 return (int) Math.round (millisBetween / (1000 * 60 * 60 * 24));

        return (int) (Math.round(calendar_.getTime().getTime() / DAY_MILLIS
                + 0.5) - Math.round(day.calendar_.getTime().getTime()
                / DAY_MILLIS + 0.5));
    }


    /**
     * Find the n'th xxxxday of s specified month (for instance find 1st sunday
     * of May 2006; findNthOfMonth (1, Calendar.SUNDAY, Calendar.MAY, 2006);
     * Return null if the specified day doesn't exists.
     *
     * @param n         Nth day to look for.
     * @param dayOfWeek Day to look for (Calendar.XXXDAY).
     * @param month     Month to check (Calendar.XXX).
     * @param year      Year to check.
     * @return Required Day (or null if non-existent)
     * @throws ArrayIndexOutOfBoundsException if dyaOfWeek parameter
     *                                        doesn't represent a valid day.
     */
    public static Day getNthOfMonth(int n, int dayOfWeek, int month, int year)
            throws ArrayIndexOutOfBoundsException {
        // Validate the dayOfWeek argument
        if (dayOfWeek < 0 || dayOfWeek > 6) {
            throw new ArrayIndexOutOfBoundsException(dayOfWeek);
        }

        Day first = new Day(year, month, 1);

        int offset = dayOfWeek - first.getDayOfWeek();
        if (offset < 0) {
            offset = 7 + offset;
        }

        int dayNo = (n - 1) * 7 + offset + 1;

        return dayNo > first.getDaysInMonth() ? null : new Day(year, month, dayNo);
    }


    /**
     * Find the first of a specific day in a given month, for instance
     * first Tuesday of May:
     * getFirstOfMonth (Calendar.TUESDAY, Calendar.MAY, 2005);
     *
     * @param dayOfWeek Weekday to get.
     * @param month     Month of day to get.
     * @param year      Year of day to get.
     * @return The requested day.
     */
    public static Day getFirstOfMonth(int dayOfWeek, int month, int year) {
        return Day.getNthOfMonth(1, dayOfWeek, month, year);
    }


    /**
     * Find the last of a specific day in a given month, for instance
     * last Tuesday of May:
     * getLastOfMonth (Calendar.TUESDAY, Calendar.MAY, 2005);
     *
     * @param dayOfWeek Weekday to get.
     * @param month     Month of day to get.
     * @param year      Year of day to get.
     * @return The requested day.
     */
    public static Day getLastOfMonth(int dayOfWeek, int month, int year) {
        Day day = Day.getNthOfMonth(5, dayOfWeek, month, year);
        return day != null ? day : Day.getNthOfMonth(4, dayOfWeek, month, year);
    }


    /**
     * Return a scratch string representation of this day.
     * Used for debugging only. The format of the
     * day is dd/mm-yyyy
     *
     * @return A string representation of this day.
     */
    @Override
    public String toString() {
        StringBuffer string = new StringBuffer();

        if (getDayOfMonth() < 10) {
            string.append('0');
        }
        string.append(getDayOfMonth());
        string.append('/');
        if (getMonth() < 9) {
            string.append('0');
        }
        string.append(getMonth() + 1);
        string.append('-');
        string.append(getYear());
        string.append(" ");
        string.append(getDayName());

        return string.toString();
    }


    /**
     * Testing this class.
     *
     * @param args Not used.
     */
    public static void main(String[] args) {
        // This proves that there are 912 days between the two major
        // terrorist attacks, not 911 as is common knowledge.
//    Day september11 = new Day (2001, Calendar.SEPTEMBER, 11);
//    Day march11     = new Day (2004, Calendar.MARCH,     11);
//    System.out.println (september11.daysBetween (march11));

        // This proves that Kennedy was president for 1037 days,
        // not 1000 as is the popular belief nor 1036 which is the
        // bluffers reply. Nerds knows when to add one...
        Day precidency = new Day(1961, Calendar.JANUARY, 20);
        Day assasination = new Day(1963, Calendar.NOVEMBER, 22);
        System.out.println(assasination.daysBetween(precidency) + 1);

        // Niel Armstrong walked the moon on a Sunday
//    Day nielOnMoon = new Day (1969, Calendar.JULY, 20);
//    System.out.println (nielOnMoon.getDayNumberOfWeek());

        // Find last tuesdays for 2005
//    for (int i = 0; i < 12; i++) {
//      Day tuesday = Day.getLastOfMonth (Calendar.TUESDAY, i, 2005);
//      System.out.println (tuesday);
//    }


    }
}


