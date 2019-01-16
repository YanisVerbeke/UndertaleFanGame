void End() {                                                                            //Dernier plateau
  if (End) {
    preplateau=loadStrings("End.txt");
    for (int j=0; j<16; j++) {
      String tab[]=split(preplateau[j], " ");
      for (int i=0; i<24; i++) {
        Stage[i][j]=int(tab[i]);
      }
    }

    background(0, 0, 30);
    fill(0);
    rect(12, 12, 1175, 775);
    for (int i=0; i<24; i++) {
      for (int j=0; j<16; j++) {
        if (Stage[i][j]==10) {
          fill(0, 0, 70);
          stroke(0, 0, 70);
          rect(i*50, j*50, 50, 50);
        }
        if (Stage[(x+35+dX)/50][(y+59)/50]==12) {
          if (Stage[i][j]==12) {
            fill(0, 0, 70);
            stroke(0, 0, 70);
            rect(i*50, j*50, 50, 50);
          }
        }
      }
    }
    Sans.pause();                                                                         //Arrêt de la musique
    Megalovania.pause();                                                                  //

    if (x+dX>1150) dX=0;                                                                  //Empêche le personnage de sortir de l'écran

    if (x>50 && x<=300) {                                                                 //Premier dialogue
      image(Sans1, 700, 300);                                                             //
      fill(255);                                                                          //
      textFont(CSansMS);                                                                  //
      textSize(15);                                                                       //
      text("It's a beautiful day outside.", 520, 275, 200, 200);                          //(Ces répliques viennent du personnage dans le jeu de base)  
      text("Birds are singing, flowers are blooming...", 520, 300, 200, 200);             //
      text("On days like these, kids like you...", 520, 350, 200, 200);                   //
    }
    if (x>300 && x<=500) {                                                                //Deuxième dialogue
      image(Sans2, 700, 300);                                                             //
      fill(255);                                                                          //
      textFont(CSansMS);                                                                  //  
      textSize(20);                                                                       //
      text("Should be burn in hell.", 520, 350, 200, 200);                                //
    }
    if (x>500 && y>=535) {                                                                //Fin du jeu
      dX=0;                                                                               //
      dY=0;                                                                               //
      for (int i=0; i<24; i++) {                                                          //
        image(Bones, 50*i, 560);                                                          //
      }                                                                                   //
      image(Sans3, 700, 300);                                                             //
      index=4;                                                                            //
      image(Heart, x+10, y+20);                                                           //
      Hit.play();                                                                         // 
      fill(0);                                                                            //
      stroke(255);                                                                        //
      rect(50, 200, 500, 300);                                                            //
      fill(0, 255, 255);                                                                  //
      noStroke();                                                                         //
      textSize(60);                                                                       //
      text("GAME OVER", 100, 300);                                                        //
      fill(255);                                                                          //
      textSize(40);                                                                       //
      text("geeettttttt dunked on!!!", 100, 400);                                         //
      fill(255);                                                                          //
      textSize(20);                                                                       //
      text("Press R to reset", 200, 450);                                                 //
      Face=loadImage("Face.png");                                                         //
      Face.loadPixels();                                                                  //
      image(Face, 430, 400, 100, 100);                                                    //


      if (downKeys['r']) {                                                                //r pour revivre
        Hit.pause();
        Hit.rewind();
        size(1200, 800);
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
        Sans.rewind();                                                                     //Lignes à modifier si changement de musique
        Sans.play();                                                                       //
        //Megalovania.rewind();                                                              //
        //Megalovania.play();                                                                //
      }
    }
  }
}

