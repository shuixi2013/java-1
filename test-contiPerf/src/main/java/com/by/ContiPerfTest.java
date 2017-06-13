package com.by;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * description:
 * create       2017/6/13 18:38
 *
 * @author email:baoyang@jd.com,ERP:baoyang3
 * @version 1.0.0
 */
public class ContiPerfTest {

    @Rule
    public ContiPerfRule i = new ContiPerfRule();

    @Test
    @PerfTest(invocations = 10, threads = 3,duration = 500)
    @Required(throughput = 1,max = 1200, average = 250, totalTime = 20000)
    public void test1() throws Exception {
        System.out.println(Thread.currentThread().getId()+"now:"+Instant.now());
        TimeUnit.MILLISECONDS.sleep(30);
    }
}
