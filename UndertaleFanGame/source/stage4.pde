void stage4() {                                                        //Niveau 4
  if (stage4) {
    preplateau=loadStrings("Stage4.txt");
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
    Bones(200, 460);
    Bones(400, 460);
    Bones(450, 510);
    Bones(550, 610);
    Bones(650, 610);
    Bones(800, 460);
    Bones(1000, 460);
    Bones(1100, 460);


    if (x>1150) {
      x=15;
      y=400;
      stage4=false;
      stage5=true;
    }
  }
}

