package SirHall.Maths;

//This is take from https://github.com/SirHall/Excessives/blob/master/Excessives.cs
//(Also developed my me)
public class Oper {
    /**
     * Will clamp a valu within a range whilst allowing wrapping around the extremes
     * @param value
     * @param min
     * @param max
     * @return
     */
    public static float ClampWrap(float value, float min, float max){
        value = ((value - min) % (max - min)) + min;
        if (value < min)
            value += max;
        return value;
    }

    public static float ReMap(
            float value,
            float inMin, float inMax,
            float outMin, float outMax
    )
    {
        return (value - inMin) / (outMin - inMin) * (outMax - inMax) + inMax;
    }

    /**
     * Rounds to the nearest 'n'
     * @param val
     * @param n
     * @return
     */
    public static float RoundToN(float val, float n){return n * (float)Math.round(val / n);}

    /**
     * Rounds up to the nearest 'n'
     * @param val
     * @param n
     * @return
     */
    public static float CeilToN(float val, float n){return n * (float)Math.ceil(val / n);}

    /**
     * Rounds down to the nearest 'n'
     * @param val
     * @param n
     * @return
     */
    public static float FloorToN(float val, float n){return n * (float)Math.floor(val / n);}

    /**
     * Degrees -> Radians
     * @param v
     * @return
     */
    public static float ToRad(float v){return v * (float)Math.PI / 180.0f;}

    /**
     * Radians -> Degrees
     * @param v
     * @return
     */
    public static float ToDeg(float v){return v * 180.0f / (float)Math.PI;}
}
