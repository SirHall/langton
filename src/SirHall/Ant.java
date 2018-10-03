package SirHall;

import SirHall.Maths.*;

/**
 * Does nothing by itself but rather acts as the agent and interface between all the smaller components
 */
public class Ant {
    public Ant(){

    }

    protected Vector2D position = new Vector2D(0.0f, 0.0f); //Start at origin
    protected Rotation rotation = new Rotation(0.0f); //Start pointing rightwards

    protected Vector2D roomSize = new Vector2D(1000, 1000);

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
        this.position = position.ClampWrap(roomSize);
//        position.Print();
    }

    /**
     * Adds a delta position onto the ant's current position
     * @param deltaPos
     */
    public void AddPosition(Vector2D deltaPos){
        SetPosition(Vector2D.Add(GetPosition(), deltaPos));
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

    /**
     * Returns a unit vector pointing in the ant's forward direction
     * @return
     */
    public Vector2D GetForward(){return Vector2D.DirDist(rotation, 1);}
    /**
     * Returns a unit vector pointing in the ant's backward direction
     * @return
     */
    public Vector2D GetBackward(){return GetForward().GetBackward();}
    /**
     * Returns a unit vector pointing in the ant's right direction
     * @return
     */
    public Vector2D GetRight(){return GetForward().GetRight();}
    /**
     * Returns a unit vector pointing in the ant's left direction
     * @return
     */
    public Vector2D GetLeft(){return GetForward().GetLeft();}

    /**
     * Moves the ant one unit in it's forward direction
     */
    public void MoveForward(){SetPosition(Vector2D.Add(position, GetForward()));}
    /**
     * Moves the ant one unit in it's backward direction
     */
    public void MoveBackward(){SetPosition(Vector2D.Add(position, GetBackward()));}
    /**
     * Moves the ant one unit in it's right direction
     */
    public void MoveRight(){SetPosition(Vector2D.Add(position, GetRight()));}
    /**
     * Moves the ant one unit in it's left direction
     */
    public void MoveLeft(){SetPosition(Vector2D.Add(position, GetLeft()));}

    /**
     * Set's the rotation of the ant in degrees
     * @param degrees
     */
    public void SetRotDeg(float degrees){rotation.SetRotDeg(degrees);}

    /**
     * Set's the rotation of the ant in radians
     * @param radians
     */
    public void SetRotRad(float radians){rotation.SetRotRad(radians);}

    /**
     * Adds a delta rotation in degrees
     * @param deltaDegrees
     */
    public void RotateByDeg(float deltaDegrees){rotation.AddRotationDeg(deltaDegrees);}
    /**
     * Adds a delta rotation in radians
     * @param deltaRadians
     */
    public void RotateByRad(float deltaRadians){rotation.AddRotationRad(deltaRadians);}

    /**
     * Tells the ant the size of the room it's currently in (allows for edge-wrapping)
     * @param roomSize
     */
    public void SetRoomSize(Vector2D roomSize){
        this.roomSize = roomSize;
    }
}
