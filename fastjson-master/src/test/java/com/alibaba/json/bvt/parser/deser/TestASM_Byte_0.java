package com.alibaba.json.bvt.parser.deser;

import org.junit.Assert;
import junit.framework.TestCase;

import com.alibaba.fastjson.JSON;

public class TestASM_Byte_0 extends TestCase {

    public void test_asm() throws Exception {
        V0 v = new V0();
        String text = JSON.toJSONString(v);
        V0 v1 = JSON.parseObject(text, V0.class);

        Assert.assertEquals(v.getI(), v1.getI());
    }

    public static class V0 {

        private Byte i = 12;

        public Byte getI() {
            return i;
        }

        public void setI(Byte i) {
            this.i = i;
        }

    }
}
