package tec;

class TestPassagerStresse extends TestPassagerAbstrait {

  public static void main(String[] args) {
    boolean estMisAssertion = false;
    assert estMisAssertion = true;

    if (!estMisAssertion) {
      System.out.println("Execution impossible sans l'option -ea");
      return;
    }

    int nbTest = 0;

    // ************ Verifier l'instanciation *************
    System.out.print('.');
    nbTest++;
    new TestPassagerStresse().testInstanciation();

    // ********* Verifier changement d'etat **************
    System.out.print('.');
    nbTest++;
    new TestPassagerStresse().testGestionEtat();

    // ********* Verifier les interactions *************
    System.out.print('.');
    nbTest++;
    new TestPassagerStresse().testInteractionMontee();

    System.out.print('.');
    nbTest++;
    new TestPassagerStresse().testInteractionArret();

    System.out.println("(" + nbTest + "):OK: " + "tec.TestPassagerStresse");
  }

  
protected PassagerAbstrait creerPassager(String nom, int destination)
    {
	return new PassagerStresse(nom,destination);
    }
 
  /*
   * Interaction a la montee
   * Trois cas
   * - des places assises et debout
   * - pas de place assise
   * - aucune place.
   */
  public void testInteractionMontee() {
    PassagerStresse p = new PassagerStresse("yyy", 5);

    FauxVehicule faux = new FauxVehicule(FauxVehicule.VIDE);
    p.monterDans(faux);

    assert "monteeDemanderAssis" == getLastLog(faux) : "assis";

    faux = new FauxVehicule(FauxVehicule.DEBOUT);
    p.monterDans(faux);

    assert "monteeDemanderDebout" == getLastLog(faux) : "pas de place assise";

    faux = new FauxVehicule(FauxVehicule.PLEIN);
    p.monterDans(faux);

    assert 0 == faux.logs.size() : "pas de place";
  }

  /*
   * Interaction a un arret
   * Trois cas
   * - numero d'arret < 3 a la destination
   * - numero d'arret = à la destination
   */
  // TODO Comment tester le changement de position à l'arret
  public void testInteractionArret() {
    PassagerStresse p = new PassagerStresse("yyy", 7);

    FauxVehicule faux = new FauxVehicule(FauxVehicule.VIDE);

    p.changerEnDebout();
    p.nouvelArret(faux, 1);
    assert 0 == faux.logs.size() : "aucun appel.";

    p.changerEnAssis();
    p.nouvelArret(faux, 4);
    assert "arretDemanderDebout" == getLastLog(faux) : "-3 arrets (debout)";

    p.changerEnDebout();
    p.nouvelArret(faux, 7);
    assert "arretDemanderSortie" == getLastLog(faux) : "destination";
  }
}
