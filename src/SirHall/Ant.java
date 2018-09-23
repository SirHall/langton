package SirHall;

import SirHall.Maths.*;

public class Ant {
    public Ant(){}

    Vector2D position = new Vector2D(0.0f, 0.0f); //Start at origin
    Rotation rotation = new Rotation(90.0f); //Start pointing upwards

    /**
     * Returns our position
     * @return
     */
    public Vector2D GetPosition(){return new Vector2D(position);}

    /**
     * Set's the position to the one spcified
     * @param position
     * @return
     */
    public void SetPosition(Vector2D position){this.position = position;}

    /**
     * Returns our rotation
     * @return
     */
    public Rotation GetRotation(){return new Rotation(rotation);}

    /**
     * Set's rotation to the one specified
     * @param rotation
     */
    public void SetRotation(Rotation rotation){this.rotation = rotation;}

    public Vector2D GetForward(){return Vector2D.DirDist(rotation, 1);}
    public Vector2D GetBackward(){return GetForward().GetBackward();}
    public Vector2D GetRight(){return GetForward().GetRight();}
    public Vector2D GetLeft(){return GetForward().GetLeft();}

    public void MoveForward(){position = Vector2D.Add(position, GetForward());}
    public void MoveBackward(){position = Vector2D.Add(position, GetBackward());}
    public void MoveRight(){position = Vector2D.Add(position, GetRight());}
    public void MoveLeft(){position = Vector2D.Add(position, GetLeft());}

    public void SetRotDeg(float degrees){rotation.SetRotDeg(degrees);}
    public void SetRotRad(float radians){rotation.SetRotRad(radians);}

    public void RotateByDeg(float deltaDegrees){rotation.AddRotationDeg(deltaDegrees);}
    public void RotateByRad(float deltaRadians){rotation.AddRotationRad(deltaRadians);}
}
