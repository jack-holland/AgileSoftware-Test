import java.util.Scanner;

public class transMsToMin
{
     // Convert _ms time to _m_s form
    public String transMsToMin(long time)
    {
        String res = "";

        int second = (int) (time/1000), minute = 0;

        while(second >= 60)
        {
            second -= 60;
            minute++;
        }
        res += (minute>0 ? minute+"Minute" : "") + second + "Second";
        return res;
    }   
}
