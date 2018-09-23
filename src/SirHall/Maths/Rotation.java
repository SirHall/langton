package SirHall.Maths;

public class Rotation {

    public Rotation(){}
    public Rotation(float rotation){this.rot = rotation;}
    public Rotation(Rotation v){this.rot = v.rot;}

    /**
     * Is stored in radians
     */
    float rot = 0.0f;

    /**
     * Returns rotation in radians
     * @return
     */
    public float GetRotRad(){return rot;}

    /**
     * Returns rotation in degrees
     * @return
     */
    public float GetRotDeg(){return Oper.ToDeg(rot);}

    /**
     * Set rotation in radians
     * @param v
     * @return
     */
    public Rotation SetRotRad(float v){this.rot = Oper.ClampWrap(v, 0.0f, 2.0f * (float)Math.PI); return this;}

    /**
     * Set rotation in degrees
     * @param v
     * @return
     */
    public Rotation SetRotDeg(float v){SetRotRad(Oper.ToRad(v)); return this;}

    /**
     * Add to rotation in degreees
     * @param deltaDegrees
     * @return
     */
    public Rotation AddRotationDeg(float deltaDegrees){return SetRotDeg(GetRotDeg() + deltaDegrees);}

    /**
     * Add to rotation in radians
     * @param deltaRadians
     * @return
     */
    public Rotation AddRotationRad(float deltaRadians){return SetRotRad(GetRotRad() + deltaRadians);}
}
