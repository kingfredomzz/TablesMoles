package king.echomood.periodictable.data;

import io.realm.RealmObject;

/**
 * Created by king on 9/9/16.
 */
public  class Element_Class extends RealmObject {

    private int id;
    private String sympol;
    private String name;
    private Double atomic_mass;

    private String type;
    private String year_discoverd;
    private String Standerd_States;
    private String bonding_type;
    private String Density;
    private String atom_radues;
    private String ios_radus;



    private String Oxidation_states;
    private String Melting_point;
    private String Boiling_point;

    private String electronic_configuration;
    private String electronegativity;
    private String ionation_energy;
    private String Activation_Energy;


    public String getActivation_Energy() {
        return Activation_Energy;
    }

    public void setActivation_Energy(String activation_Energy) {
        Activation_Energy = activation_Energy;
    }

    public String getAtom_radues() {
        return atom_radues;
    }

    public void setAtom_radues(String atom_radues) {
        this.atom_radues = atom_radues;
    }

    public Double getAtomic_mass() {
        return atomic_mass;
    }

    public void setAtomic_mass(Double atomic_mass) {
        this.atomic_mass = atomic_mass;
    }

    public String getBoiling_point() {
        return Boiling_point;
    }

    public void setBoiling_point(String boiling_point) {
        Boiling_point = boiling_point;
    }

    public String getBonding_type() {
        return bonding_type;
    }

    public void setBonding_type(String bonding_type) {
        this.bonding_type = bonding_type;
    }

    public String getDensity() {
        return Density;
    }

    public void setDensity(String density) {
        Density = density;
    }

    public String getElectronegativity() {
        return electronegativity;
    }

    public void setElectronegativity(String electronegativity) {
        this.electronegativity = electronegativity;
    }

    public String getElectronic_configuration() {
        return electronic_configuration;
    }

    public void setElectronic_configuration(String electronic_configuration) {
        this.electronic_configuration = electronic_configuration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIonation_energy() {
        return ionation_energy;
    }

    public void setIonation_energy(String ionation_energy) {
        this.ionation_energy = ionation_energy;
    }

    public String getIos_radus() {
        return ios_radus;
    }

    public void setIos_radus(String ios_radus) {
        this.ios_radus = ios_radus;
    }

    public String getMelting_point() {
        return Melting_point;
    }

    public void setMelting_point(String melting_point) {
        Melting_point = melting_point;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOxidation_states() {
        return Oxidation_states;
    }

    public void setOxidation_states(String oxidation_states) {
        Oxidation_states = oxidation_states;
    }

    public String getStanderd_States() {
        return Standerd_States;
    }

    public void setStanderd_States(String standerd_States) {
        Standerd_States = standerd_States;
    }

    public String getSympol() {
        return sympol;
    }

    public void setSympol(String sympol) {
        this.sympol = sympol;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYear_discoverd() {
        return year_discoverd;
    }

    public void setYear_discoverd(String year_discoverd) {
        this.year_discoverd = year_discoverd;
    }
}
