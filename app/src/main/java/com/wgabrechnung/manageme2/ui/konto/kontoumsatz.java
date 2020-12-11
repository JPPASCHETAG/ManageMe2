package com.wgabrechnung.manageme2.ui.konto;

public class kontoumsatz {

    private int ID;
    private String BETRAG;
    private String NAME;
    private String DATUM;
    private String ART;
    private String CREDIT_DEBIT;
    private boolean isSelected;

    public String getBETRAG() {
        return BETRAG;
    }

    public void setBETRAG(String BETRAG) {
        this.BETRAG = BETRAG;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getDATUM() {
        return DATUM;
    }

    public void setDATUM(String DATUM) {
        this.DATUM = DATUM;
    }

    public String getART() {
        return ART;
    }

    public void setART(String ART) {
        this.ART = ART;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getCREDIT_DEBIT() {
        return CREDIT_DEBIT;
    }

    public void setCREDIT_DEBIT(String CREDIT_DEBIT) {
        this.CREDIT_DEBIT = CREDIT_DEBIT;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
