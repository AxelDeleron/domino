import java.util.*;

class Main{

  public static void main (String[] args)
  {
    Game jeu = new Game();
    boolean gameOn = true;
    jeu.startGame();
    jeu.initializeGame();
    while(gameOn)
    {
      System.out.println("");
      for(int i = 0; i < jeu.getNbJoueurs(); i++)
      {
        if (jeu.stuckGame()) 
        {
          i = jeu.getNbJoueurs() - 1;
          System.out.println("\n" + "-------------------------------------------");
          System.out.println("/!\\ La partie est terminee car les joueurs ne peuvent plus jouer !");
          for(Joueur joueur : jeu.getTableauJoueur())
          {
            joueur.givePlayerScore();
            System.out.println("Le score de " + joueur.getName() + " est de : " + joueur.getScore());
          }
          Joueur gagnant = jeu.getTableauJoueur().get(0);
          for(Joueur joueur : jeu.getTableauJoueur())
          {
            if(joueur.getScore() <= gagnant.getScore()) {gagnant = joueur;}
          }
          System.out.println("Le gagnant est : " + gagnant.getName() + " !");
          System.out.println("Merci d'avoir joue !");
          Scanner sc = new Scanner(System.in);
          String choix = sc.nextLine();
          gameOn = false;
        }
        else
        {
          Joueur joueur = jeu.getTableauJoueur().get(i);
          joueur.tourJoueur(jeu);
          if(joueur.victoireJoueur())
          {
            System.out.println("-------------------------------------------");
            System.out.println(joueur.getName() + " a remporte la partie !");
            System.out.println("Bien joue a lui !");
            System.out.println("-------------------------------------------");
            System.out.println("Le jeu va maintenant se terminer.");
            i = jeu.getNbJoueurs() - 1;
            Scanner sc = new Scanner(System.in);
            String choix = sc.nextLine();
            gameOn = false;
          }
        }
      }
    }
  }




}
