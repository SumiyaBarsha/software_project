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
    public void testGetTimestamp(){
        EachData eachData = new EachData("1", 123456789, "01-01-2022", "12:00AM", 120, 80, 70, "Comment","Status");

        Assert.assertEquals(123456789,eachData.getTimestamp());

    }





    @Test
    public void testGetSystolic(){
        EachData eachData = new EachData("1", 123456789, "01-01-2022", "12:00AM", 120, 80, 70, "Comment","Status");

        Assert.assertEquals(120,eachData.getSysPressure());
    }

    @Test
    public void testGetDysPressure(){
        EachData eachData = new EachData("1", 123456789, "01-01-2022", "12:00AM", 120, 80, 70, "Comment","Status");

        Assert.assertEquals(80,eachData.getDysPressure());
    }

    @Test
    public void testGetTime() {
        EachData eachData = new EachData("1", 123456789, "01-01-2022", "12:00AM", 120, 80, 70, "Comment","Status");

        Assert.assertEquals("12:00AM",eachData.getTime());
    }
    @Test
    public void testGetHeartRate() {
        EachData eachData = new EachData("1", 123456789, "01-01-2022", "12:00AM", 120, 80, 70, "Comment","Status");

        Assert.assertEquals(70,eachData.getHeartRate());
    }
    @Test
    public void testGetComment() {
        EachData eachData = new EachData("1", 123456789, "01-01-2022", "12:00AM", 120, 80, 70, "Comment","Status");

        Assert.assertEquals("Comment",eachData.getComment());
    }
    @Test
    public void testGetStatus() {
        EachData eachData = new EachData("1", 123456789, "01-01-2022", "12:00AM", 120, 80, 70, "Comment","Status");

        Assert.assertEquals("Status",eachData.getStatus());
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
    @Test
    public void testGetDate() {
        EachData eachData = new EachData("1", 123456789, "01-01-2022", "12:00AM", 120, 80, 70, "Comment","Status");

        Assert.assertEquals("01-01-2022",eachData.getDate());
    }
    @Test
    public void testSetTimestamp() {
        EachData eachData = new EachData();
        long expectedTimestamp = 1234567890L; // Replace with the expected timestamp value

        eachData.setTimestamp(expectedTimestamp);

        long actualTimestamp = eachData.getTimestamp(); // Assuming there's a getter method to retrieve the timestamp
        Assert.assertEquals(expectedTimestamp, actualTimestamp);
    }

    @Test
    public void testSetDate() {
        EachData eachData = new EachData();
        String expectedDate = "2023-07-05"; // Replace with the expected date value

        eachData.setDate(expectedDate);

        String actualDate = eachData.getDate();
        Assert.assertEquals(expectedDate, actualDate);
    }

    @Test
    public void testSetTime() {
        EachData eachData = new EachData();
        String expectedTime = "09:30:00"; // Replace with the expected time value

        eachData.setTime(expectedTime);

        String actualTime = eachData.getTime();
        Assert.assertEquals(expectedTime, actualTime);
    }

    @Test
    public void testSetSysPressure() {
        EachData eachData = new EachData();
        int expectedSysPressure = 120; // Replace with the expected systolic pressure value

        eachData.setSysPressure(expectedSysPressure);

        int actualSysPressure = eachData.getSysPressure();
        Assert.assertEquals(expectedSysPressure, actualSysPressure);
    }

    @Test
    public void testSetDysPressure() {
        EachData eachData = new EachData();
        int expectedDysPressure = 80; // Replace with the expected diastolic pressure value

        eachData.setDysPressure(expectedDysPressure);

        int actualDysPressure = eachData.getDysPressure();
        Assert.assertEquals(expectedDysPressure, actualDysPressure);
    }

    @Test
    public void testSetHeartRate() {
        EachData eachData = new EachData();
        int expectedHeartRate = 80; // Replace with the expected heart rate value

        eachData.setHeartRate(expectedHeartRate);

        int actualHeartRate = eachData.getHeartRate();
        Assert.assertEquals(expectedHeartRate, actualHeartRate);
    }

    @Test
    public void testSetComment() {
        EachData eachData = new EachData();
        String expectedComment = "This is a comment."; // Replace with the expected comment value

        eachData.setComment(expectedComment);

        String actualComment = eachData.getComment();
        Assert.assertEquals(expectedComment, actualComment);
    }

    @Test
    public void testSetStatus() {
        EachData eachData = new EachData();
        String expectedStatus = "Normal"; // Replace with the expected status value

        eachData.setStatus(expectedStatus);

        String actualStatus = eachData.getStatus();
        Assert.assertEquals(expectedStatus, actualStatus);
    }
    @Test
    public void testSetPushId() {
        EachData eachData = new EachData();
        String expectedPushId = "ABC123"; // Replace with the expected pushId value

        eachData.setPushId(expectedPushId);

        String actualPushId = eachData.getPushId();
        Assert.assertEquals(expectedPushId, actualPushId);
    }
}