void stage2() {                                                  //Niveau 2
  if (stage2) {                                                  //Identique au 1
    preplateau=loadStrings("Stage2.txt");
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
      }
    }
    Bones(200, 560);
    Bones(350, 410);
    Bones(550, 210);
    Bones(720, 460);
    Bones(1000, 360);

    if (x>1150) {
      x=15;
      y=340;
      stage2=false;
      stage3=true;
    }
  }
}

