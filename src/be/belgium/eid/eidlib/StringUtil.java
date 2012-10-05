// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StringUtil.java

package be.belgium.eid.eidlib;


public class StringUtil
{

    public StringUtil()
    {
    }

    public static Integer parseHex(String iStr)
    {
        int mask = 255;
        if(iStr.length() > 2)
            mask = 65535;
        try
        {
            return Integer.valueOf(Integer.parseInt(iStr, 16) & mask);
        }
        catch(Exception ex)
        {
            return null;
        }
    }

    public static String byteArrToString(byte iBytes[], String iSeparator)
    {
        StringBuffer sb = new StringBuffer();
        byte arr$[] = iBytes;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            byte b = arr$[i$];
            sb.append(byteToHex(b));
            sb.append(iSeparator);
        }

        if(iSeparator.length() > 0 && sb.length() >= iSeparator.length())
            sb.deleteCharAt(sb.length() - iSeparator.length());
        return sb.toString();
    }

    public static char byteToPrintableChar(byte iB)
    {
        if(iB >= 33 && iB <= 126)
            return (char)iB;
        else
            return '.';
    }

    public static String byteArrToPrintableString(byte iData[])
    {
        StringBuffer sb = new StringBuffer(iData.length);
        for(int i = 0; i < iData.length; i++)
            sb.append(byteToPrintableChar(iData[i]));

        return sb.toString();
    }

    public static String hexDump(byte iData[], int iRowSize)
    {
        StringBuffer sb = new StringBuffer();
        int rows = (int)Math.ceil((float)iData.length / (float)iRowSize);
        for(int row = 0; row < rows; row++)
        {
            int offset = iRowSize * row;
            StringBuffer hexpart = new StringBuffer();
            StringBuffer strpart = new StringBuffer();
            for(int i = 0; i < iRowSize && offset + i < iData.length; i++)
            {
                byte b = iData[offset + i];
                hexpart.append(byteToHex(b));
                hexpart.append(" ");
                strpart.append(byteToPrintableChar(b));
            }

            int expectedLen = iRowSize * 3;
            if(hexpart.length() < expectedLen)
                hexpart.append(spaces(expectedLen - hexpart.length()));
            sb.append(hexpart);
            sb.append("     ");
            sb.append(strpart);
            sb.append("\n");
        }

        return sb.toString();
    }

    public static String byteToHex(int iVal)
    {
        String hx = Integer.toHexString(iVal & 0xff).toUpperCase();
        if(hx.length() == 2)
            return hx;
        else
            return (new StringBuilder()).append("0").append(hx).toString();
    }

    public static byte[] stringToByteArr(String iStr)
    {
        iStr = iStr.replace(" ", "");
        byte outArr[] = new byte[iStr.length() / 2];
        for(int i = 0; i < iStr.length(); i += 2)
        {
            String hex = iStr.substring(i, i + 2);
            byte b = (byte)(Integer.parseInt(hex, 16) & 0xff);
            outArr[i / 2] = b;
        }

        return outArr;
    }

    public static String spaces(int iLen)
    {
        StringBuffer buf = new StringBuffer(iLen);
        while(iLen-- > 0) 
            buf.append(" ");
        return buf.toString();
    }
}
