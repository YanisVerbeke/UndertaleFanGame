void gameOver() {                                        //Fonction de fin de jeu
  dX=0;                                                  //Bloque les mouvements
  dY=0;                                                  //
  jeu=false;                                             //
  index=4;                                               //Index transparent, supprime le personnage
  if (x<width/2) {                                       //Si perso dans la 1ère moitié de l'écran
    fill(0);                                             //Texte de mort dans la 2ème moitié
    stroke(255);
    rect(650, 200, 500, 300);
    fill(0, 255, 255);
    noStroke();
    textSize(60);
    text("GAME OVER", 700, 300);
    fill(255);
    textSize(40);
    text("Stay", 700, 400);
    fill(255, 0, 0);
    text("determined", 800, 400);
    fill(255);
    textSize(20);
    text("Press R to reset", 800, 450);
    image(Face, 1030, 320, 100, 100);
  }
  if (x>width/2) {                                        //Si perso dans la 2ème moitié de l'écran
    fill(0);                                              //Texte de mort dans la 1ère moitié
    stroke(255);
    rect(50, 200, 500, 300);
    fill(0, 255, 255);
    noStroke();
    textSize(60);
    text("GAME OVER", 100, 300);
    fill(255);
    textSize(40);
    text("Stay", 100, 400);
    fill(255, 0, 0);
    text("determined", 200, 400);
    fill(255);
    textSize(20);
    text("Press R to reset", 200, 450);
    image(Face, 430, 320, 100, 100);
  }
  image(Heart, x+10, y+20);                              //Image de coeur remplace le personnage (Rférence au jeu de base)






  if (downKeys['r']) {                                  //r pour revivre
    Hit.pause();                                        //Son réinitiallisé
    Hit.rewind();                                       //
    size(1200, 800);                                    //Setup
    background(0, 0, 30);
    fill(0);
    rect(12, 12, 1175, 775);

    preplateau=loadStrings("Stage1.txt");
    for (int j=0; j<16; j++) {
      String tab[]=split(preplateau[j], " ");
      for (int i=0; i<24; i++) {
        Stage[i][j]=int(tab[i]);
      }
    }
    index=0;
    x=15;
    y=690;
    dX=0;
    dY=0;
    jeu=true;
    stage1=true; 
    stage2=false; 
    stage3=false; 
    stage4=false; 
    stage5=false;
    End=false;
    Try++;
  }
}

