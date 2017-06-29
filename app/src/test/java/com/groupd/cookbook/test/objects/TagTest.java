package com.groupd.cookbook.test.objects; /**
 * Created by wutao on 2017/6/27.
 */

import com.groupd.cookbook.objects.tag;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.Assert.assertEquals;

public class TagTest {
    private tag test;

    private String tagS;

    @Before
    public void setUp() {
        test = new tag("vegi");

    }

    @After
    public void tearDown() {
        test = null;
    }

    @Test
    public void testgettagsNameValid() {
        tagS = test.getTagsName();
        assertEquals("tag valid fail", "vegi", tagS);

    }

    public void testgetTagsNameNull() {
        test = new tag(null);
        tagS = test.getTagsName();
        assertEquals("tag null fail", null, tagS);
    }
}

