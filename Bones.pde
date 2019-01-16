PImage Bones;

void Bones(int xb, int yb) {                                //Place des os auc coords entrées qui tuent le joueur
  Bones=loadImage("Bones.png");                             //
  if (x+20>=xb-5 && y+24>=yb && x<xb+50 ) {                 //
    Hit.play();                                             //Son
    image(Bones, xb, yb);                                   //
    gameOver();                                             //(A desactiver si difficultés)
  }
}

