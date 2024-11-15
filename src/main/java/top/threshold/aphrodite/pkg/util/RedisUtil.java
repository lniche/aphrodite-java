package top.threshold.aphrodite.pkg.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            log.error("redis error", e);
            return false;
        }
    }

    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    public boolean hasKey(String key) {
        try {
            return stringRedisTemplate.hasKey(key);
        } catch (Exception e) {
            log.error("redis error", e);
            return false;
        }
    }

    public void del(String... keys) {
        if (keys.length > 0) {
            if (keys.length == 1) {
                stringRedisTemplate.delete(keys[0]);
            } else {
                stringRedisTemplate.delete(Set.of(keys));
            }
        }
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public String getStr(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public int getInt(String key) {
        String str = stringRedisTemplate.opsForValue().get(key);
        return StrUtil.isBlank(str) ? 0 : Integer.parseInt(str);
    }

    public boolean getBool(String key) {
        String str = stringRedisTemplate.opsForValue().get(key);
        return StrUtil.isBlank(str) ? false : Boolean.parseBoolean(str);
    }

    public long getLong(String key) {
        String str = stringRedisTemplate.opsForValue().get(key);
        return StrUtil.isBlank(str) ? 0 : Long.parseLong(str);
    }

    public <T> T getObj(String key, Class<T> clazz) {
        try {
            String json = (String) redisTemplate.opsForValue().get(key);
            if (StrUtil.isBlank(json)) {
                return null;
            }
            return JSONUtil.toBean(json, clazz);
        } catch (Exception e) {
            log.error("redis error", e);
            return null;
        }
    }

    public boolean setLong(String key, long value) {
        try {
            stringRedisTemplate.opsForValue().set(key, String.valueOf(value));
            return true;
        } catch (Exception e) {
            log.error("redis error", e);
            return false;
        }
    }

    public boolean setStr(String key, String value) {
        try {
            stringRedisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            log.error("redis error", e);
            return false;
        }
    }

    public boolean setBool(String key, boolean value) {
        try {
            stringRedisTemplate.opsForValue().set(key, String.valueOf(value));
            return true;
        } catch (Exception e) {
            log.error("redis error", e);
            return false;
        }
    }

    public boolean setInt(String key, int value) {
        try {
            stringRedisTemplate.opsForValue().set(key, String.valueOf(value));
            return true;
        } catch (Exception e) {
            log.error("redis error", e);
            return false;
        }
    }

    public boolean setInt(String key, int value, long time) {
        try {
            if (time > 0) {
                stringRedisTemplate.opsForValue().set(key, String.valueOf(value), time, TimeUnit.SECONDS);
            } else {
                setStr(key, String.valueOf(value));
            }
            return true;
        } catch (Exception e) {
            log.error("redis error", e);
            return false;
        }
    }

    public boolean setStr(String key, String value, long time) {
        try {
            if (time > 0) {
                stringRedisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                setStr(key, value);
            }
            return true;
        } catch (Exception e) {
            log.error("redis error", e);
            return false;
        }
    }

    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            log.error("redis error", e);
            return false;
        }
    }

    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            log.error("redis error", e);
            return false;
        }
    }

    public long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("Increment factor must be greater than 0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("Decrement factor must be greater than 0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    public long decrDelIfZero(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("Decrement factor must be greater than 0");
        }
        long result = redisTemplate.opsForValue().increment(key, -delta);
        if (getLong(key) <= 0) {
            del(key);
        }
        return result;
    }

    public Object hget(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    public boolean hmset(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            log.error("redis error", e);
            return false;
        }
    }

    public boolean hmset(String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error("redis error", e);
            return false;
        }
    }

    public boolean hset(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            log.error("redis error", e);
            return false;
        }
    }

    public boolean hset(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error("redis error", e);
            return false;
        }
    }

    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    public double hincr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    public double hdecr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    public Set<Object> sGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            log.error("redis error", e);
            return null;
        }
    }

    public boolean sHasKey(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            log.error("redis error", e);
            return false;
        }
    }

    public long sSet(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            log.error("redis error", e);
            return 0;
        }
    }

    public long sSetAndTime(String key, long time, Object... values) {
        try {
            long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0) {
                expire(key, time);
            }
            return count;
        } catch (Exception e) {
            log.error("redis error", e);
            return 0;
        }
    }

    public long sGetSetSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            log.error("redis error", e);
            return 0;
        }
    }

    public long setRemove(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().remove(key, values);
        } catch (Exception e) {
            log.error("redis error", e);
            return 0;
        }
    }

    public Set<Object> sAll(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    public List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            log.error("redis error", e);
            return null;
        }
    }

    public long lGetListSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            log.error("redis error", e);
            return 0;
        }
    }

    public Object lGetIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            log.error("redis error", e);
            return null;
        }
    }

    public boolean llSet(String key, Object value) {
        try {
            redisTemplate.opsForList().leftPush(key, value);
            return true;
        } catch (Exception e) {
            log.error("redis error", e);
            return false;
        }
    }

    public boolean lrSet(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            log.error("redis error", e);
            return false;
        }
    }

    public boolean lSet(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error("redis error", e);
            return false;
        }
    }

    public boolean lSet(String key, List<Object> values) {
        try {
            redisTemplate.opsForList().rightPushAll(key, values.toArray());
            return true;
        } catch (Exception e) {
            log.error("redis error", e);
            return false;
        }
    }

    public boolean lSet(String key, List<Object> values, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, values.toArray());
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error("redis error", e);
            return false;
        }
    }

    public boolean lUpdateIndex(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            log.error("redis error", e);
            return false;
        }
    }

    public long lRemove(String key, long count, Object value) {
        try {
            Long removed = redisTemplate.opsForList().remove(key, count, value);
            return removed != null ? removed : 0;
        } catch (Exception e) {
            log.error("redis error", e);
            return 0;
        }
    }

    public boolean ltrim(String key, long start, long end) {
        try {
            redisTemplate.opsForList().trim(key, start, end);
            return true;
        } catch (Exception e) {
            log.error("redis error", e);
            return false;
        }
    }

    public Long nextId(String key) {
        return redisTemplate.opsForValue().increment(key);
    }

    public void setKey(String key) {
        stringRedisTemplate.opsForValue().set(key, "");
    }

    public void setKey(String key, long time) {
        stringRedisTemplate.opsForValue().set(key, "", time, TimeUnit.SECONDS);
    }
}
