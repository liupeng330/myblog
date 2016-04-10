package pengliu.me.utils;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by peng on 4/10/16.
 */
public class Common
{
    public static Timestamp getTimeStampNow()
    {
       return new Timestamp(new Date().getTime());
    }
}
