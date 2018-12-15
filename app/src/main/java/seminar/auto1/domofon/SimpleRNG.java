package seminar.auto1.domofon;

import android.content.Context;

/*
Implementation of SimpleRNG algorithm, created by George Marsaglia.
General implementation taken from: https://www.codeproject.com/Articles/25172/Simple-Random-Number-Generation
 */
public class SimpleRNG {
    private Context context;
    private int m_w;
    private int m_z;

    SimpleRNG(Context context) {
        this.context = context;
        m_w = sharedPrefsData.getLastm_w(context);
        m_z = sharedPrefsData.getLastm_z(context);
    }

    /*
        Function that returns the next generated int.
        It uses zero fill shifting (so MSB is set to 0) to create an unsigned integer.
         */
    public int getInt()
    {
        m_z = 36969 * (m_z & 65535) + (m_z >>> 16);
        sharedPrefsData.savem_z(context, m_z);
        m_w = 18000 * (m_w & 65535) + (m_w >>> 16);
        sharedPrefsData.savem_w(context, m_w);
        return (m_z << 16) + m_w;
    }

    String getNextHex() {
        return Integer.toHexString(getInt()) + Integer.toHexString(getInt());
    }



}
