package com.ysp.ssm.demo.cache;

import de.flapdoodle.embed.memcached.MemcachedExecutable;
import de.flapdoodle.embed.memcached.MemcachedProcess;
import de.flapdoodle.embed.memcached.MemcachedStarter;
import de.flapdoodle.embed.memcached.config.MemcachedConfig;
import de.flapdoodle.embed.memcached.distribution.Version;
import net.spy.memcached.MemcachedClient;

import java.io.IOException;
import java.net.InetSocketAddress;

import static org.junit.Assert.assertEquals;

/**
 * @author: shipeng.yu
 * @time: 2016年09月04日 下午4:27
 * @version: 1.0
 * @since: 1.0
 * @description:
 */
public class MemcachedTest {

    public static void main(String[] args) throws IOException {
//        Cache
        int port = 11211;
        MemcachedConfig memcachedConfig = new MemcachedConfig(Version.Main.PRODUCTION, port);

        MemcachedStarter runtime = MemcachedStarter.getDefaultInstance();

        MemcachedExecutable memcachedExecutable = null;
        try {
            memcachedExecutable = runtime.prepare(memcachedConfig);
            MemcachedProcess memcached = memcachedExecutable.start();

            MemcachedClient jmemcache = new MemcachedClient(
                    new InetSocketAddress("localhost", 11211));
            // adding a new key
            jmemcache.add("key", 5, "value");
            // getting the key value
            assertEquals("value", jmemcache.get("key"));

        } finally {
            if (memcachedExecutable != null)
                memcachedExecutable.stop();
        }
    }
}
