package SirHall.Maths;

import java.util.Vector;

public class Vector2D {

    public Vector2D(){
        this.x = 0.0f;
        this.y = 0.0f;
    }

    public Vector2D(float x, float y){
        this.x = x;
        this.y = y;
    }

    public Vector2D(Vector2D v){
        this.x = v.x;
        this.y = v.y;
    }

    float x = 0.0f, y = 0.0f;

    public float GetX(){return x;}
    public void SetX(float val){this.x = val;}
    public float GetY(){return y;}
    public void SetY(float val){this.y = val;}

    //Java doesn't support operator overloading... :(

    public static Vector2D Add(Vector2D a, Vector2D b){
        return new Vector2D(a.x + b.x, a.y + b.y); //Return new instance
    }

    public static Vector2D Sub(Vector2D a, Vector2D b){
        return new Vector2D(a.x - b.x, a.y - b.y);
    }

    public static Vector2D Mul(Vector2D v, float m){
        return new Vector2D(v.x * m, v.y * m);
    }

    public static Vector2D Div(Vector2D v, float d){
        return new Vector2D(v.x / d, v.y / d);
    }

    public float MagnitudeSquared(){
        return x * x + y * y; //Useful for optimisations later down the line
    }

    public float Magnitude(){
        return (float) Math.sqrt(MagnitudeSquared());
    }

    public Vector2D Normalized(){return Vector2D.Div(this, this.Magnitude());}

    public Vector2D SetMagnitude(float v){
        return Vector2D.Mul(this.Normalized(), v);
    }

    public Vector2D SetDirection(Vector2D dir){return Vector2D.Mul(dir.Normalized(), this.Magnitude());}

    /**
     * Returns a vector given a rotation and distance
     * @param rotation - radians
     * @param distance
     * @return
     */
    public static Vector2D DirDist(float rotation, float distance){
        return
                new Vector2D(
                        distance * (float)Math.cos(rotation),
                        distance * (float)Math.sin(rotation)
                );
    }

    /**
     * Returns a vector given a rotation and distance
     * @param rotation - radians
     * @param distance
     * @return
     */
    public static Vector2D DirDist(Rotation rotation, float distance){
        return
                new Vector2D(
                        distance * (float)Math.cos(rotation.GetRotRad()),
                        distance * (float)Math.sin(rotation.GetRotRad())
                );
    }

    /**
     * Rotates the vector by a given rotation
     * @param rotation
     * @return
     */
    public Vector2D RotateBy(Rotation rotation){
        //Equation taken from: https://matthew-brett.github.io/teaching/rotation_2d.html
        // x2 = cos(a * x1) - sin(angle * y1)
        // y2 = sin(a * x1) + cos(angle * y1)
        float rot = rotation.GetRotRad(); //Only get the rotation once
        return
                new Vector2D(
                        (float)(Math.cos(rot * x) - Math.sin(rot * y)),
                        (float)(Math.sin(rot * x) + Math.sin(rot * y))
                );
    }

    /**
     * Finds the rotation of the direction of the vector
     * @return The rotation
     */
    public Rotation GetRotation(){
        float referenceAngle = (float)Math.abs(Math.atan2(y, x));

        if(referenceAngle >= 0.0f && referenceAngle <= (float)Math.PI / 2.0f) //Quartile I
            return new Rotation(referenceAngle);
        else if(referenceAngle <= (float)Math.PI) //Quartile II
            return new Rotation((float)Math.PI - referenceAngle);
        else if(referenceAngle <= (3.0f * (float)Math.PI) / 4.0f) //Quartile III
            return new Rotation(referenceAngle - (float)Math.PI);
        //We assume that the angle falls between 3pi/4 -> 2pi
        return new Rotation((2.0f * (float)Math.PI) - referenceAngle);
    }

    public Vector2D GetForward(){return this.Normalized();}

    /**
     * Returns the normalized vector rotated by 180 degrees
     * @return
     */
    public Vector2D GetBackward(){return new Vector2D(-x, -y).Normalized();}

    /**
     * Returns the normalized vector rotated by -90 degrees
     * @return
     */
    public Vector2D GetRight(){return new Vector2D(y, -x).Normalized();}

    /**
     * Returns the normalized vector rotated by -90 degrees
     * @return
     */
    public Vector2D GetLeft(){return new Vector2D(-y, x).Normalized();}

    /**
     * Snaps a vector to a grid given that grid's height and width
     * @param gridSize
     * @return
     */
    public Vector2D SnapToGrid(Vector2D gridSize){
        return new Vector2D(Oper.RoundToN(x, gridSize.x), Oper.RoundToN(y, gridSize.y));
    }

    /**
     * Snaps a vector to a grid given that grid's cell size
     * @param gridSize
     * @return
     */
    public Vector2D SnapToGrid(float gridSize){
        return new Vector2D(Oper.RoundToN(x, gridSize), Oper.RoundToN(y, gridSize));
    }

    /**
     * Clamp-wraps both axis' of the vector where min defaults to (0, 0)
     * @param wrapVectorMax
     * @return
     */
    public Vector2D ClampWrap(Vector2D wrapVectorMax){
        return ClampWrap(new Vector2D(0, 0), wrapVectorMax);
    }

    /**
     * Clamp-wraps both axis' of the vector
     * @return
     */
    public Vector2D ClampWrap(Vector2D wrapVectorMin, Vector2D wrapVectorMax){
        return new Vector2D(
                Oper.ClampWrap(x, wrapVectorMin.x, wrapVectorMax.x),
                Oper.ClampWrap(y, wrapVectorMin.y, wrapVectorMax.y)
                );
    }

    /**
     * Prints the Vector2D's info to the terminal
     */
    public void Print(){
        System.out.println("(" + this.x + ", " + this.y + ")");
    }
}
