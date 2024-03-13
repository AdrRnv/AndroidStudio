package com.iut.app.android.retrofittp2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Clock {

    @SerializedName("abbreviation")
    @Expose
    private String abbreviation;

    @SerializedName("datetime")
    @Expose
    private String datetime;
    @SerializedName("day_of_week")
    @Expose
    private int dayOfWeek;
    @SerializedName("day_of_year")
    @Expose
    private int dayOfYear;
    @SerializedName("dst")
    @Expose
    private boolean dst;
    @SerializedName("dst_from")
    @Expose
    private Object dstFrom;
    @SerializedName("dst_offset")
    @Expose
    private int dstOffset;
    @SerializedName("dst_until")
    @Expose
    private Object dstUntil;
    @SerializedName("raw_offset")
    @Expose
    private int rawOffset;
    @SerializedName("timezone")
    @Expose
    private String timezone;
    @SerializedName("unixtime")
    @Expose
    private int unixtime;
    @SerializedName("utc_datetime")
    @Expose
    private String utcDatetime;
    @SerializedName("utc_offset")
    @Expose
    private String utcOffset;
    @SerializedName("week_number")
    @Expose
    private int weekNumber;

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getDatetime() {
        return datetime;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public int getDayOfYear() {
        return dayOfYear;
    }

    public boolean isDst() {
        return dst;
    }

    public Object getDstFrom() {
        return dstFrom;
    }

    public int getDstOffset() {
        return dstOffset;
    }

    public Object getDstUntil() {
        return dstUntil;
    }

    public int getRawOffset() {
        return rawOffset;
    }

    public String getTimezone() {
        return timezone;
    }

    public int getUnixtime() {
        return unixtime;
    }

    public String getUtcDatetime() {
        return utcDatetime;
    }

    public String getUtcOffset() {
        return utcOffset;
    }

    public int getWeekNumber() {
        return weekNumber;
    }
}
