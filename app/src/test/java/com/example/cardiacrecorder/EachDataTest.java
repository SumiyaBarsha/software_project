package com.example.cardiacrecorder;

import org.junit.Assert;
import org.junit.Test;

public class EachDataTest {


    @Test
    public void testIsIdSame() {
        String id1 = "1";
        String id2 = "2";

        EachData item1 = new EachData(id1, 123456789, "01-01-2023", "12:00AM", 90, 80, 70, "Comment 1","Status 1");
        EachData item2 = new EachData(id2, 987654321, "01-01-2022", "12:00AM", 120, 80, 70, "Comment 2","Status2");

        boolean isSame1 = item1.isIdSame(item1);
        boolean isSame2 = item1.isIdSame(item2);

        Assert.assertTrue(isSame1);
        Assert.assertFalse(isSame2);
    }

    @Test
    public void testIsFullySame() {
        EachData originalData = new EachData("1", 123456789, "01-01-2023", "12:00AM", 120, 80, 70, "Comment","Status");
        EachData sameData = new EachData("1", 123456789, "01-01-2023", "12:00AM", 120, 80, 70, "Comment","Status");
        EachData differentData = new EachData("2", 987654321, "01-01-2022", "12:00AM", 130, 90, 75, "Different Comment","Different Status");

        boolean isSame1 = originalData.isFullySame(sameData);
        boolean isSame2 = originalData.isFullySame(differentData);

        Assert.assertTrue(isSame1);
        Assert.assertFalse(isSame2);
    }


    @Test
    public void testGetFormattedSysPressure() {
        String id = "1";
        long timestamp = System.currentTimeMillis();
        String date = "01-01-2023";
        String time = "12:00AM";
        int sysPressure = 120;
        int dysPressure = 80;
        int heartRate = 70;
        String comment = "Sample comment";
        String status = "Sample status";

        EachData data = new EachData(id, timestamp, date, time, sysPressure, dysPressure, heartRate, comment, status);

        Assert.assertEquals("120mm Hg", data.getFormattedSysPressure());
    }

    @Test
    public void testGetFormattedDysPressure() {
        int dysPressure = 80;

        EachData eachData = new EachData("1", 123456789, "01-01-2022", "12:00AM", 120, dysPressure, 70, "Comment","Status");

        String expectedFormattedDysPressure = dysPressure + "mm Hg";
        String formattedDysPressure = eachData.getFormattedDysPressure();

        Assert.assertEquals(expectedFormattedDysPressure, formattedDysPressure);
    }


    @Test
    public void testGetFormattedHeartRate() {
        int heartRate = 80;

        EachData eachData = new EachData("1", 123456789, "01-01-2022", "12:00AM", 120, 70, heartRate, "Comment","Status");

        String expectedText = heartRate + "BPM";
        String formattedHeartRate = eachData.getFormattedHeartRate();

        Assert.assertEquals(expectedText, formattedHeartRate);
    }

}
