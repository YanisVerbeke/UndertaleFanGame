void stage1() {                                                                                                  //Premier niveau
  if (stage1) {                                                                                                  //Test boolean
    preplateau=loadStrings("Stage1.txt");                                                                        //Plateau à partir d'un .txt
    for (int j=0; j<16; j++) {
      String tab[]=split(preplateau[j], " ");
      for (int i=0; i<24; i++) {
        Stage[i][j]=int(tab[i]);
      }
    }
    noStroke();
    background(0, 0, 30);
    fill(0);
    rect(12, 12, 1175, 775);
    for (int i=0; i<24; i++) {
      for (int j=0; j<16; j++) {
        if (Stage[i][j]==10) {                                                                                   //Si 10 sur le fichier,
          fill(0, 0, 70);                                                                                        //Bloc de 50x50
          stroke(0, 0, 70);
          rect(i*50, j*50, 50, 50);
        }
      }
    }
    image(Title, 250, 10, 700, 300);                                                                             //Le jeu de base
    image(Sans1, 700, 300);                                                                                      //Le boss du jeu
    fill(255);
    textFont(CSansMS);
    textSize(15);
    text("Hi, I'm Sans, Sans the skeleton.", 500, 250, 200, 200);
    text("I don't know what you do here but if you continu to this way...", 500, 300, 200, 200);
    text("Walk with Q and D, jump with Z. ", 30, 650);
    textSize(25);
    text("You're gonna have a bad time...", 400, 400, 300, 200);
    textSize(12);
    text("Watch out kid, look like there is something weird right here...", 400, 650, 200, 200);
    stroke(255);
    line(500, 680, 500, 720);
    line(500, 720, 490, 710);
    line(500, 720, 510, 710);

    strokeWeight(5);
    line(1000, 400, 1150, 400);
    line(1150, 400, 1100, 380);
    line(1150, 400, 1100, 420);

    Bones(545, 710);                                                                                             //Obstacle à éviter
    Bones(400, 710);
    Bones(790, 660);

    if (x>1150) {                                                                                                //Si il arrive à la fin du niveau
      x=15;                                                                                                      //Niveau suivant
      y=590;                                                                                                     //
      stage1=false;                                                                                              //
      stage2=true;                                                                                               //
    }
  }
}

