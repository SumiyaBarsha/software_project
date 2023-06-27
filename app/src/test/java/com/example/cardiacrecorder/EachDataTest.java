package com.example.cardiacrecorder;

import org.junit.Assert;
import org.junit.Test;

public class EachDataTest {


    @Test
    public void testIsIdSame() {
        String id1 = "1";
        String id2 = "2";

        EachData item1 = new EachData(id1, 123456789, "01-01-2023", "12:00AM", 90, 80, 70, "Comment 1");
        EachData item2 = new EachData(id2, 987654321, "01-01-2022", "12:00AM", 120, 80, 70, "Comment 2");

        boolean isSame1 = item1.isIdSame(item1);
        boolean isSame2 = item1.isIdSame(item2);

        Assert.assertTrue(isSame1);
        Assert.assertFalse(isSame2);
    }

    @Test
    public void testIsFullySame() {
        EachData originalData = new EachData("1", 123456789, "01-01-2023", "12:00AM", 120, 80, 70, "Comment");
        EachData sameData = new EachData("1", 123456789, "01-01-2023", "12:00AM", 120, 80, 70, "Comment");
        EachData differentData = new EachData("2", 987654321, "01-01-2022", "12:00AM", 130, 90, 75, "Different Comment");

        boolean isSame1 = originalData.isFullySame(sameData);
        boolean isSame2 = originalData.isFullySame(differentData);

        Assert.assertTrue(isSame1);
        Assert.assertFalse(isSame2);
    }

}
