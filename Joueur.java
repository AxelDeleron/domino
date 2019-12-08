import java.util.*;

public class Joueur{
  private String name;
  private int score;
  private ArrayList<Domino> dominoMain;

  //Constructeur de base
  public Joueur()
  {
    setName("John Doe");
    setScore(0);
    dominoMain = new ArrayList<Domino>();
    for (int i = 0; i <7 ; i++)
    {
      piocher();
    }
  }

  public Joueur(String pName)
  {
    setName(pName);
    setScore(0);
    dominoMain = new ArrayList<Domino>();
    for (int i = 0; i <7 ; i++)
    {
      piocher();
    }
  }

  public void piocher()
  {
    int newLeft = (int)(Math.random() * 6 + 1);
    int newRight = (int)(Math.random() * 6 + 1);
    Domino newDomino = new Domino(newLeft, newRight);
    dominoMain.add(newDomino);
  }

  public void flipMain()
  {
    for (int i = 0; i < dominoMain.size(); i++)
    {
      dominoMain.get(i).flip();
    }
  }

  public boolean mainPossible(Game jeu)
  {
    boolean possible = false;
    if (jeu.getDominoTerrain().size() != 0)
    {
      for(Domino domino : this.getDominoMain())
      {
        if(domino.getLeft() == jeu.getDominoTerrain().get(0).getLeft() || domino.getRight() == jeu.getDominoTerrain().get(0).getLeft() || domino.getLeft() == jeu.getDominoTerrain().get(jeu.getDominoTerrain().size() - 1).getRight() || domino.getRight() == jeu.getDominoTerrain().get(jeu.getDominoTerrain().size() - 1).getRight())
        {
          possible = true;
        }
      }
    }
    else {possible = true;}
    return possible;
  }

  public void placerDomino(Game jeu, int num)
  {
    Domino dominoToPlace = dominoMain.get(num);
    if (jeu.getDominoTerrain().size() == 0)
    {
      jeu.getDominoTerrain().add(dominoToPlace);
      System.out.println("\n" + getName() + " a place le domino : " + dominoToPlace);
      removeDomino(num);
    }
    else if (jeu.getDominoTerrain().get(jeu.getDominoTerrain().size() - 1).getRight() == dominoToPlace.getLeft())
    {
      jeu.getDominoTerrain().add(dominoToPlace);
      System.out.println("\n" + getName() + " a place le domino : " + dominoToPlace);
      removeDomino(num);
    }
    else if (jeu.getDominoTerrain().get(jeu.getDominoTerrain().size() - 1).getRight() == dominoToPlace.getRight())
    {
      flipMain();
      jeu.getDominoTerrain().add(dominoToPlace);
      System.out.println("\n" + getName() + " a place le domino : " + dominoToPlace);
      removeDomino(num);
    }
    else if (jeu.getDominoTerrain().get(0).getLeft() == dominoToPlace.getRight())
    {
      jeu.getDominoTerrain().add(0, dominoToPlace);
      System.out.println("\n" + getName() + " a place le domino : " + dominoToPlace);
      removeDomino(num);
    }
    else if (jeu.getDominoTerrain().get(0).getLeft() == dominoToPlace.getLeft())
    {
      flipMain();
      jeu.getDominoTerrain().add(0, dominoToPlace);
      System.out.println("\n" + getName() + " a place le domino : " + dominoToPlace);
      removeDomino(num);
    }

    else
    {
      System.out.println("");
      System.out.println("/!\\ Ce domino ne peut pas etre place, choisir un domino valide !");
      tourJoueur(jeu);
    }
  }

  public void tourJoueur(Game jeu)
  {
    System.out.println("-------------------------------------------");
    System.out.println("C'est le tour de " + getName() + " !");
    System.out.println("-------------------------------------------");
    montrerJeu(jeu);
    if((mainPossible(jeu) == false) && (jeu.getDominoPioche() == 0))
    {
      System.out.println(getName() + " ne peut pas jouer !");
      return;
    }
    Scanner sc = new Scanner(System.in);
    int choix = sc.nextInt();
    sc.nextLine();
    if(choix == 0)
    {
      if (jeu.getDominoPioche() !=0)
      {
        this.piocher();
        jeu.setDominoPioche(jeu.getDominoPioche() - 1);
      }
      else
      {
        System.out.println("");
        System.out.println("/!\\ La pioche est vide !");
        tourJoueur(jeu);
      }
    }
    else if(choix >= 1 && choix <= this.getDominoMain().size())
    {
      for(int j = 1; j <= this.getDominoMain().size(); j++)
      {
        if(choix == j) {this.placerDomino(jeu, j - 1);}
      }
    }
    else
    {
      System.out.println("/!\\ Vous n'avez pas rentre une commande valide.");
      tourJoueur(jeu);
    }
  }

  public void montrerJeu(Game jeu)
  {
    System.out.println("Votre main : " + this.getDominoMain());
    System.out.println("Le terrain : " + jeu.getDominoTerrain());
    System.out.println("La pioche : " + jeu.getDominoPioche());

    System.out.println("Tapez 0 pour piocher ou le numero de votre domino pour le placer sur le terrain...");
  }

  public boolean victoireJoueur()
  {
    if (this.getDominoMain().size() == 0) {return true;}
    else {return false;}
  }

  public void givePlayerScore()
  {
    for(Domino domino : getDominoMain())
    {
      this.setScore(this.getScore() + domino.getRight() + domino.getLeft());
    }
  }

  //--------------- GETTERS AND SETTERS -----------------
  public String getName() {return this.name;}
  public int getScore() {return this.score;}
  public ArrayList<Domino> getDominoMain() {return this.dominoMain;}

  public void setName(String pName) {this.name = pName;}
  private void setScore(int pScore) {this.score = pScore;}

  private void removeDomino(int num) {this.dominoMain.remove(num);}

//------------------- MAIN ----------------------
  public static void main (String[] args)
  {
    Joueur joueurTest = new Joueur();
    System.out.println(joueurTest.getDominoMain());
    joueurTest.flipMain();
    System.out.println(joueurTest.getDominoMain());

  }

}
