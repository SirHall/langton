package SirHall;

import SirHall.Maths.*;

public class Ant {
    public Ant(){

    }


    Vector2D position = new Vector2D(0.0f, 0.0f); //Start at origin
    Rotation rotation = new Rotation(0.0f); //Start pointing rightwards

    Vector2D roomSize = new Vector2D(1000, 1000);

    /**
     * Returns our position
     * @return
     */
    public Vector2D GetPosition(){return new Vector2D(position);} //Do not return the original instance of our position

    /**
     * Set's the position to the one specified and make sure the position is within the canvas
     * @param position
     * @return
     */
    public void SetPosition(Vector2D position){
        //If position falls outside the room, wrap it around the other side
        this.position = position.ClampWrap(roomSize).SnapToGrid(1);
//        position.Print();
    }

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

    public void MoveForward(){SetPosition(Vector2D.Add(position, GetForward()));}
    public void MoveBackward(){SetPosition(Vector2D.Add(position, GetBackward()));}
    public void MoveRight(){SetPosition(Vector2D.Add(position, GetRight()));}
    public void MoveLeft(){SetPosition(Vector2D.Add(position, GetLeft()));}

    public void SetRotDeg(float degrees){rotation.SetRotDeg(degrees);}
    public void SetRotRad(float radians){rotation.SetRotRad(radians);}

    public void RotateByDeg(float deltaDegrees){rotation.AddRotationDeg(deltaDegrees);}
    public void RotateByRad(float deltaRadians){rotation.AddRotationRad(deltaRadians);}

    public void SetRoomSize(Vector2D roomSize){
        this.roomSize = roomSize;
    }
}
