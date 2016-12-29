package king.echomood.periodictable.data;

import io.realm.RealmObject;

/**
 * Created by yousf on 12/29/16.
 */

public class FormulasElements extends RealmObject {

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int ID;
    private String formula ;
    private String name ;

}
