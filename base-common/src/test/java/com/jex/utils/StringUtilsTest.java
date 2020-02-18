package com.jex.utils;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static com.jex.utils.StringUtils.*;
import static org.junit.Assert.*;

public class StringUtilsTest {


    @Test
    public void testGetIP() {
        assertEquals("127.0.0.1", getIp(new MockHttpServletRequest()));
    }
}