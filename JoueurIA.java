import java.util.*;

class JoueurIA extends Joueur {

	public JoueurIA()
	{
		super();
	}

	public void tourJoueur(Game jeu)
  {

    System.out.println("");
    System.out.println("-------------------------------------------");
    System.out.println("C'est le tour de " + getName() + " !");
    System.out.println("-------------------------------------------");
    if((mainPossible(jeu) == false) && (jeu.getDominoPioche() == 0))
    {
      System.out.println(getName() + " ne peut pas jouer !");
      return ;
    }
    else if (mainPossible(jeu)) 
    {
    	selectDominoIA(jeu);
		}
    else 
    {
    	piocher();
    	jeu.setDominoPioche(jeu.getDominoPioche() - 1);
    	System.out.println(getName() + " a pioche !");
  	}
  }

  public void selectDominoIA(Game jeu)
  {
  	int dominoToPlace = -1;
  	if (jeu.getDominoTerrain().size() == 0) {placerDomino(jeu, 0);}
    else
    {
    	for(int i = 0; i <= 1; i++)
    	{
    		flipMain();
		    for(Domino domino : getDominoMain())
		    {
		      if(domino.getRight() == jeu.getDominoTerrain().get(0).getLeft() || domino.getLeft() == jeu.getDominoTerrain().get(0).getLeft())
		      {
		        dominoToPlace = getDominoMain().indexOf(domino);
		        i = 1;
		        break;
		      }
		      else if(domino.getLeft() == jeu.getDominoTerrain().get(jeu.getDominoTerrain().size() - 1).getRight() || domino.getRight() == jeu.getDominoTerrain().get(jeu.getDominoTerrain().size() - 1).getRight())
		      {
		        dominoToPlace = getDominoMain().indexOf(domino);
		        i = 1;
		        break;
		      }
    		}
    	}
      placerDomino(jeu, dominoToPlace);
    }
  }
}