import java.util.*;

public class Domino {
  private int right;
  private int left;
  //create a domino

  public Domino(int left,int right){
    setLeft(left);
    setRight(right);
  }

  public Domino(){
    setLeft(0);
    setRight(0);
  }

  public void flip(){
    int aux = getRight();
    setRight(getLeft());
    setLeft(aux);
  }

  public boolean isDomino(){
    return ! (getLeft() >= 0 && getLeft() <= 6 && getRight() >= 0 && getRight() <= 6);
  }

  public String toString (){
    return ("[" + getLeft() + ";" + getRight() + "] ");
  }

  //--------------- GETTERS AND SETTERS -----------------------
  public int getLeft() {return this.left;}
  public int getRight() {return this.right;}

  private void setLeft(int pLeft) {this.left = pLeft;}
  private void setRight(int pRight) {this.right = pRight;}
}
