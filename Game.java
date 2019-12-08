import java.util.*;

public class Game{
  private int dominoPioche;
  private int nbJoueurs;
  private ArrayList<Domino> dominoTerrain;
  private ArrayList<Joueur> tableauJoueurs;

  public Game()
  {
    dominoPioche = -1;
    dominoTerrain = new ArrayList<Domino>();
    tableauJoueurs = new ArrayList<Joueur>();
    nbJoueurs = -1;
  }

  public void startGame()
  {
    System.out.println("-------------------------------------------");
    System.out.println("-------------- LE JEU COMMENCE ------------");
    System.out.println("-------------------------------------------");
    System.out.println("Bienvenue dans ce jeu de Domino !");
    System.out.println("Les regles sont simples, chaque joueur dispose de 7 dominos et joue jusqu'a ce qu'une main soit vide ou que le jeu soit bloque.");
  }

  public void initializeGame()
  {
    System.out.println("Combien y'a-t-il de joueurs ? (4 maximum)");
    Scanner sc = new Scanner(System.in);
    int choixnbJoueur =sc.nextInt();
    sc.nextLine();
    this.setNbJoueurs(choixnbJoueur);
    for(int i = 1; i <= this.getNbJoueurs(); i++)
    {
      System.out.println("Quel est le nom du joueur " + i + " ?");
      String choixNom = sc.nextLine();
      sc.nextLine();
      Joueur joueurAjout = new Joueur(choixNom);
      tableauJoueurs.add(joueurAjout);
    }
    if(choixnbJoueur == 1)
    {
      JoueurIA joueurIAAjout = new JoueurIA();
      tableauJoueurs.add(joueurIAAjout);
      this.setNbJoueurs(2);
    }
    setDominoPioche(28 - 7 * getNbJoueurs());
  }

  public boolean stuckGame()
  {
    int stuckHand = 0;
    if (getDominoPioche() == 0)
    {
      for (Joueur joueur : getTableauJoueur())
      {
        if (joueur.mainPossible(this) == false)
        {
          stuckHand++;
        }
      }
    }
    if(stuckHand == getTableauJoueur().size()) {return true;}
    else{return false;}

  }


  //---------------- GETTERS AND SETTERS ---------------
  public int getDominoPioche(){return this.dominoPioche;}
  public int getNbJoueurs(){return this.nbJoueurs;}
  public ArrayList<Domino> getDominoTerrain(){return this.dominoTerrain;}
  public ArrayList<Joueur> getTableauJoueur(){return this.tableauJoueurs;}

  public void setDominoPioche(int nb){this.dominoPioche = nb;}
  private void setNbJoueurs(int nb){this.nbJoueurs = nb;}
}
