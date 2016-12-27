package king.echomood.periodictable.data;

/**
 * Created by yousf on 10/2/16.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.DoubleSummaryStatistics;
import java.util.Enumeration;
import java.util.Hashtable;

public class ElementCalculation {
    static Hashtable hm;
    Hashtable[][]reactpro;
    Hashtable[] templist;
    Hashtable brentry=new Hashtable();
    double output,finaloutput;
    int newid=0,roundopen=0,boxopen=0,fullLen=0,indexLen=0,hmrpos=0,hmcpos=0,digitvalue=0,tempindex=0,suffix=0;
    String temp="",args="";
    private double Final_Result;
    private String Element_Formela;

    public String getElement_Formela() {
        return Element_Formela;
    }

    public void setElement_Formela(String element_Formela) {
        Element_Formela = element_Formela;
    }

    public double getFinal_Result() {
        return Final_Result;
    }

    public void setFinal_Result(double final_Result) {
        Final_Result = final_Result;
    }

    boolean keyexists=false,yesno=false;
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    public ElementCalculation()
    {
        setInitialValues();
        initializeDefaultreactPro();
    }
    public void dispose()
    {
        temp = "";
        roundopen = boxopen = tempindex = hmrpos = hmcpos = digitvalue = fullLen = indexLen = newid = suffix = 0;
        brentry.clear();
        keyexists=yesno=false;
        finaloutput = output = 0;
    }
    private static void setInitialValues()
    {
        hm=new Hashtable();
        hm.put("H", new Double(1.0079));
        hm.put("He", new Double(4.0026));
        hm.put("Li", new Double(6.941));
        hm.put("Be", new Double(9.0122));
        hm.put("B", new Double(10.811));
        hm.put("C", new Double(12.0107));
        hm.put("N", new Double(14.0067));
        hm.put("O", new Double(15.9994));
        hm.put("F", new Double(18.9984));
        hm.put("Ne", new Double(20.1797));
        hm.put("Na", new Double(22.9897));
        hm.put("Mg", new Double(24.305));
        hm.put("Al", new Double(26.9815));
        hm.put("Si", new Double(28.0855));
        hm.put("P", new Double(30.9738));
        hm.put("S", new Double(32.065));
        hm.put("Cl", new Double(35.453));
        hm.put("K", new Double(39.0983));
        hm.put("Ar", new Double(39.948));
        hm.put("Ca", new Double(40.078));
        hm.put("Sc", new Double(44.9559));
        hm.put("Ti", new Double(47.867));
        hm.put("V", new Double(50.9415));
        hm.put("Cr", new Double(51.9961));
        hm.put("Mn", new Double(54.938));
        hm.put("Fe", new Double(55.845));
        hm.put("Ni", new Double(58.6934));
        hm.put("Co", new Double(58.9332));
        hm.put("Cu", new Double(63.546));
        hm.put("Zn", new Double(65.39));
        hm.put("Ga", new Double(69.723));
        hm.put("Ge", new Double(72.64));
        hm.put("As", new Double(74.9216));
        hm.put("Se", new Double(78.96));
        hm.put("Br", new Double(79.904));
        hm.put("Kr", new Double(83.8));
        hm.put("Rb", new Double(85.4678));
        hm.put("Sr", new Double(87.62));
        hm.put("Y", new Double(88.9059));
        hm.put("Zr", new Double(91.224));
        hm.put("Nb", new Double(92.9064));
        hm.put("Mo", new Double(95.94));
        hm.put("Tc", new Double(98));
        hm.put("Ru", new Double(101.07));
        hm.put("Rh", new Double(102.9055));
        hm.put("Pd", new Double(106.42));
        hm.put("Ag", new Double(107.8682));
        hm.put("Cd", new Double(112.411));
        hm.put("In", new Double(114.818));
        hm.put("Sn", new Double(118.71));
        hm.put("Sb", new Double(121.76));
        hm.put("I", new Double(126.9045));
        hm.put("Te", new Double(127.6));
        hm.put("Xe", new Double(131.293));
        hm.put("Cs", new Double(132.9055));
        hm.put("Ba", new Double(137.327));
        hm.put("La", new Double(138.9055));
        hm.put("Ce",new Double(140.116));
        hm.put("Pr", new Double(140.9077));
        hm.put("Nd", new Double(144.24));
        hm.put("Pm", new Double(145));
        hm.put("Sm", new Double(150.36));
        hm.put("Eu", new Double(151.964));
        hm.put("Gd", new Double(157.25));
        hm.put("Tb", new Double(158.9253));
        hm.put("Dy",new Double(162.5));
        hm.put("Ho", new Double(164.9303));
        hm.put("Er", new Double(167.259));
        hm.put("Tm", new Double(168.9342));
        hm.put("Yb", new Double(173.04));
        hm.put("Lu", new Double(174.967));
        hm.put("Hf", new Double(178.49));
        hm.put("Ta", new Double(180.9479));
        hm.put("W", new Double(183.84));
        hm.put("Re", new Double(186.207));
        hm.put("Os", new Double(190.23));
        hm.put("Ir", new Double(192.217));
        hm.put("Pt", new Double(195.078));
        hm.put("Au", new Double(196.9665));
        hm.put("Hg", new Double(200.59));
        hm.put("Tl", new Double(204.3833));
        hm.put("Pb", new Double(207.2));
        hm.put("Bi", new Double(208.9804));
        hm.put("Po", new Double(209));
        hm.put("At", new Double(210));
        hm.put("Rn", new Double(222));
        hm.put("Fr", new Double(223));
        hm.put("Ra", new Double(226));
        hm.put("Ac", new Double(227));
        hm.put("Pa", new Double(231.0359));
        hm.put("Th", new Double(232.0381));
        hm.put("Np", new Double(237));
        hm.put("U", new Double(238.0289));
        hm.put("Am", new Double(243));
        hm.put("Pu", new Double(244));
        hm.put("Cm", new Double(247));
        hm.put("Bk", new Double(247));
        hm.put("Cf", new Double(251));
        hm.put("Es", new Double(252));
        hm.put("Fm",new Double(257));
        hm.put("Md", new Double(258));
        hm.put("No", new Double(259));
        hm.put("Rf", new Double(261));
        hm.put("Lr", new Double(262));
        hm.put("Db", new Double(262));
        hm.put("Bh", new Double(264));
        hm.put("Sg", new Double(266));
        hm.put("Mt", new Double(268));
        hm.put("Rg", new Double(272));
        hm.put("Hs", new Double(277));
        hm.put("Ds", new Double(280));
    }

    //This function is created temporary to test the functinality in BBD
    private void initializeDefaultreactPro()
    {
        reactpro=new Hashtable[2][];
        reactpro[0]=new Hashtable[1];
        reactpro[1]=new Hashtable[1];
        initializeReactPro();
    }
    //For initializing the reactPro Hashtable
    private void initializeReactPro()
    {
        for(int i=0;i<reactpro.length;i++)

        {
            for(int j=0;j<reactpro[i].length;j++)
                reactpro[i][j]=new Hashtable();
        }
    }
    private boolean contains(String args){
        yesno=false;
        for(int i=0;i<args.length();i++)
        {
            if(args.charAt(i)=='(' || args.charAt(i)=='[')
            {
                yesno=true;
                break;
            }
        }
        return yesno;
    }
    public void accept()
    {
        try
        {

            args = getElement_Formela();
            if(contains(args)==true)
                getNoOfBrackets(args);
            reactpro[hmrpos][hmcpos].clear();
            calculateLength(args);
            matchElementToUpdate(reactpro[hmrpos][hmcpos],brentry);
            calculateMolWeight(hmrpos,hmcpos);
        }
        catch(Exception e1){}
    }

    //to calculate the no of Brackets appearing in the string
    private void getNoOfBrackets(String args)
    {
        for(int i=0;i<args.length();i++)
        {
            if(args.charAt(i)=='(' || args.charAt(i)=='[')
                tempindex++;
        }
        templist=new Hashtable[tempindex];

        tempindex=0;
        initializeTempHash();
    }
    /*intialized the templist hashtable*/
    private void initializeTempHash()
    {
        for (int i = 0; i < templist.length; i++)
            templist[i] = new Hashtable();
    }
    private void calculateLength(String args)
    {
        fullLen=args.length();
        indexLen=fullLen-1;
        readAtom(args);
    }
    //for calculating the next position within the String array.
    private int calculateNextPosition(int org)
    {
        int id = 0;
        if (org == fullLen - 1)
        {
            if (boxopen > 0 || roundopen > 0)
                id = org;
            else
                id = 0;
        }
        else if (org < fullLen - 1)
            id = org + 1;
        newid = id;
        return newid;
    }
    private void readAtom(String args) {
        for(int i=0,nxtpos=0;i<args.length();i++)
        {
            temp="";
            nxtpos=calculateNextPosition(i);
            if(Character.isUpperCase(args.charAt(i))&& Character.isUpperCase(args.charAt(indexLen-(indexLen-nxtpos))))
            {
                temp=String.valueOf(args.charAt(i));
                addentry(1);
            }
            else if(Character.isUpperCase(args.charAt(i)) && (args.charAt(indexLen-(indexLen-nxtpos))=='[' || args.charAt(indexLen-(indexLen-nxtpos))=='('))
            {
                temp = String.valueOf(args.charAt(i));
                addentry(1);

                if(args.charAt(indexLen - (indexLen - nxtpos))=='(')
                    roundopen++;
                else
                    boxopen++;
                // suffix = 1;
                i=readBrackets(args, indexLen - (indexLen - nxtpos));
            }
            else if(Character.isUpperCase(args.charAt(i)) && Character.isDigit(args.charAt(indexLen-(indexLen-nxtpos))))
            {
                temp =String.valueOf(args.charAt(i));
                //keyexists = reactpro[hmrpos][hmcpos].containsKey(temp);
                i=IsDigit(args, indexLen - (indexLen - nxtpos));
                addentry(digitvalue);
            }
            else if(Character.isUpperCase(args.charAt(i)))
            {
                temp=String.valueOf(args.charAt(i));
                i=readLower(args,i);
                i=i-1;
            }
            else if(args.charAt(i)=='[')
            {
                boxopen++;
                if(!brentry.isEmpty())
                {
                    addToTempHash();
                    tempindex++;
                }
                i=readBrackets(args, i);
            }
            else if(args.charAt(i)=='(')
            {
                roundopen++;
                if(!brentry.isEmpty())
                {
                    addToTempHash();
                    tempindex++;
                }
                i=readBrackets(args, i);
            }
        }
    }
    //This function is used for reading the elements having lower case letters and also for reading the digits
    private int readLower(String args, int idx) {
        for(int j=idx+1,nxtpos=0;j<args.length();j++)
        {
            nxtpos=calculateNextPosition(j);
            if(Character.isLowerCase(args.charAt(j))&& (!(Character.isDigit(args.charAt(indexLen-(indexLen-nxtpos))) || Character.isLowerCase(args.charAt(indexLen-(indexLen-
                    nxtpos))))))
            {
                temp+=String.valueOf(args.charAt(j));
                if (roundopen == 0 && boxopen == 0)
                    addentry(1);
                else
                {
                    if (!brentry.isEmpty())
                    {
                        addToTempHash();
                        tempindex++;
                    }
                    addentry(1);
                }
            }
            else if(Character.isLowerCase(args.charAt(j)) && Character.isDigit(args.charAt(indexLen-(indexLen-nxtpos))))
            {
                temp+=String.valueOf(args.charAt(j));
                j=IsDigit(args,indexLen-(indexLen-nxtpos));
                addentry(digitvalue);
            }
            else if(Character.isLowerCase(args.charAt(j))&& args.charAt(indexLen-(indexLen-nxtpos))=='[')
            {
                temp+=String.valueOf(args.charAt(j));
                addentry(1);
                boxopen++;
                //suffix=1;
                addToTempHash();
                tempindex++;
                j=readBrackets(args, indexLen-(indexLen-nxtpos));
            }
            else if(Character.isLowerCase(args.charAt(j))&& args.charAt(indexLen-(indexLen-nxtpos))=='(')
            {
                temp+=String.valueOf(args.charAt(j));
                addentry(1);
                roundopen++;
                //suffix=1;
                addToTempHash();
                tempindex++;
                j=readBrackets(args, indexLen-(indexLen-nxtpos));
            }
            else if(Character.isDigit(args.charAt(j))&& args.charAt(indexLen-(indexLen-nxtpos))==')')
            {
                j=IsDigit(args, j);
                if(roundopen>1)
                {
                    keyexists=brentry.containsKey(temp);
                    if(keyexists)
                    {
                        suffix=digitvalue;
                        j=IsDigit(args, indexLen-(indexLen-nxtpos)+1);
                        digitvalue*=suffix;
                        suffix=0;
                        addentry(digitvalue);
                        roundopen--;
                    }
                    else
                        addentry(digitvalue);
                }
                else
                    addentry(digitvalue);
            }
            else if(Character.isDigit(args.charAt(j)) && args.charAt(indexLen-(indexLen-nxtpos))==']')
            {
                j=IsDigit(args, j);
                if(boxopen>1)
                {
                    keyexists=brentry.containsKey(temp);
                    if(keyexists)
                    {
                        suffix=digitvalue;
                        j=IsDigit(args, indexLen-(indexLen-nxtpos)+1);
                        digitvalue*=suffix;
                        suffix=0;
                        addentry(digitvalue);
                        boxopen--;
                    }
                    else
                        addentry(digitvalue);
                }
                else
                    addentry(digitvalue);
            }
            else if(Character.isLowerCase(args.charAt(j)))
            {
                temp+=String.valueOf(args.charAt(j));
                for(int k=j+1,nxtpos1=0;j<args.length();k++)
                {
                    nxtpos1=calculateNextPosition(k);
                    if(Character.isLowerCase(args.charAt(k))&& !Character.isDigit(args.charAt(indexLen-(indexLen-nxtpos1))))
                    {
                        temp+=String.valueOf(args.charAt(k));
                        addentry(1);
                    }
                    else if(Character.isLowerCase(args.charAt(k)) && Character.isDigit(args.charAt(indexLen-(indexLen-nxtpos1))))
                    {
                        temp+=String.valueOf(args.charAt(k));
                        k=IsDigit(args, k);
                        addentry(digitvalue);
                    }
                    else if(Character.isLowerCase(args.charAt(k))&& args.charAt(indexLen-(indexLen-nxtpos1))=='[')
                    {
                        temp+=String.valueOf(args.charAt(k));
                        addentry(1);
                        boxopen++;
                        k=readBrackets(args, indexLen-(indexLen-nxtpos1));
                    }
                    else if(Character.isLowerCase(args.charAt(k))&& args.charAt(indexLen-(indexLen-nxtpos1))=='(')
                    {
                        temp+=String.valueOf(args.charAt(k));
                        addentry(1);
                        roundopen++;
                        k=readBrackets(args, indexLen-(indexLen-nxtpos1));
                    }
                    else if(Character.isDigit(args.charAt(k)))
                    {
                        k=IsDigit(args, k);
                        addentry(digitvalue);
                    }
                    else if(Character.isUpperCase(args.charAt(k)))
                    {
                        j=k-1;
                        break;
                    }
                    else
                    {
                        j=k-1;
                        break;
                    }
                }
            }
            else if(Character.isDigit(args.charAt(j))&& args.charAt(indexLen-(indexLen-nxtpos))!=')')
            {
                j=IsDigit(args, j);
                addentry(digitvalue);
            }
            else if(Character.isUpperCase(args.charAt(j)))
            {
                newid=j;
                break;
            }
            else if(args.charAt(j)=='[' && args.charAt(indexLen-(indexLen-nxtpos))=='(')
            {
                boxopen++;
                j=readBrackets(args, indexLen-(indexLen-nxtpos));
            }
            else if(args.charAt(j)=='[')
            {
                boxopen++;
                if(boxopen>0)
                {
                    if(!brentry.isEmpty())
                    {
                        addToTempHash();
                        tempindex++;
                    }
                }
                j=readBrackets(args, j);
            }
            else if(args.charAt(j)=='(')
            {
                roundopen++;
                if(roundopen>0)
                {
                    if(!brentry.isEmpty())
                    {
                        addToTempHash();
                        tempindex++;
                    }
                }
                j=readBrackets(args, j);
            }
            else if(args.charAt(j)==')' && Character.isDigit(args.charAt(indexLen-(indexLen-nxtpos))))
            {
                roundopen--;
                j=IsDigit(args, indexLen-(indexLen-nxtpos));
                if(boxopen==0 && roundopen==0)
                {
                    if(!brentry.isEmpty())
                    {
                        updateentry(brentry,digitvalue);
                        tempindex=tempindex>0?tempindex=tempindex-1:0;
                        if(!templist[tempindex].isEmpty())
                            matchElementToUpdate(this.brentry,this.templist[tempindex]);
                        else
                        {
                            //suffix=0;
                            addToReactPro();
                        }
                    }
                    else
                        updateentry(reactpro[hmrpos][hmcpos],digitvalue);
                }
                else
                {
                    updateentry(brentry,digitvalue);
                    tempindex=tempindex>0?tempindex=tempindex-1:0;
                    if(!templist[tempindex].isEmpty())
                        matchElementToUpdate(brentry, templist[tempindex]);
                    else                           {
                        //suffix=0;
                        addToReactPro();
                    }
                }
            }
            else if(args.charAt(j)==']' && Character.isDigit(args.charAt(indexLen-(indexLen-nxtpos))))
            {
                boxopen--;
                j=IsDigit(args, indexLen-(indexLen-nxtpos));
                if(boxopen==0 && roundopen==0)
                {
                    if(!brentry.isEmpty())
                    {
                        updateentry(brentry,digitvalue);
                        tempindex=tempindex>0?tempindex=tempindex-1:0;
                        if(!templist[tempindex].isEmpty())
                            matchElementToUpdate(brentry,templist[tempindex]);
                        else
                        {
                            //suffix=0;
                            addToReactPro();
                        }
                    }
                    else
                        updateentry(reactpro[hmrpos][hmcpos], digitvalue);
                }
                else
                {
                    updateentry(brentry,digitvalue);
                    tempindex=tempindex>0?tempindex=tempindex-1:0;
                    if(!templist[tempindex].isEmpty())
                        matchElementToUpdate(this.brentry,this.templist[tempindex]);
                    else
                    {
                        suffix=0;
                        addToReactPro();
                    }
                }
            }
            else if(args.charAt(j)==')')
            {
                roundopen--;
                if(roundopen==0 && boxopen==0)
                    addToReactPro();
                else
                {
                    tempindex=tempindex>0?tempindex=tempindex-1:0;
                    if(!templist[tempindex].isEmpty())
                        matchElementToUpdate(brentry,templist[tempindex]);
                    else
                    {
                        suffix=0;
                        addToReactPro();
                    }
                }
            }
            else if(args.charAt(j)==']')
            {
                boxopen--;
                if(roundopen==0 && boxopen==0)
                    addToReactPro();
                else
                {
                    tempindex=tempindex>0?tempindex=tempindex-1:0;
                    if(!templist[tempindex].isEmpty())
                        matchElementToUpdate(brentry,templist[tempindex]);
                    else
                    {
                        suffix=0;
                        addToReactPro();
                    }
                }
            }
            newid=j;
        }
        return newid;
    }
    //For reading the brackets expression
    private int readBrackets(String args, int idx) {
        for(int i=idx+1,nxtpos=0;i<args.length();i++)
        {
            nxtpos=calculateNextPosition(i);
            if(Character.isUpperCase(args.charAt(i)) && Character.isUpperCase(args.charAt(indexLen-(indexLen-nxtpos))))
            {
                temp=String.valueOf(args.charAt(i));
                addentry(1);
            }
            else if(Character.isUpperCase(args.charAt(i)) && args.charAt(indexLen-(indexLen-nxtpos))=='[')
            {
                temp=String.valueOf(args.charAt(i));
                addentry(1);
                addToTempHash();
                tempindex++;
                boxopen++;
                //suffix=1;
                i=readBrackets(args, indexLen-(indexLen-nxtpos));
            }
            else if(Character.isUpperCase(args.charAt(i))&& args.charAt(indexLen-(indexLen-nxtpos))=='(')
            {
                temp=String.valueOf(args.charAt(i));
                addentry(1);
                addToTempHash();
                tempindex++;
                roundopen++;
                //suffix=1;
                i=readBrackets(args, indexLen-(indexLen-nxtpos));
            }
            else if(Character.isUpperCase(args.charAt(i))&& args.charAt(indexLen-(indexLen-nxtpos))==')')
            {
                temp=String.valueOf(args.charAt(i));
                addentry(1);
                i=readLower(args, i);
                i=i-1;
            }
            else if(Character.isUpperCase(args.charAt(i)) && args.charAt(indexLen-(indexLen-nxtpos))==']')
            {
                temp=String.valueOf(args.charAt(i));
                addentry(1);
                i=readLower(args, i);
                i=i-1;
            }
            else if(args.charAt(i)=='(')
            {
                roundopen++;
                if(!brentry.isEmpty())
                {
                    addToTempHash();
                    tempindex++;
                }
                continue;
            }
            else if(args.charAt(i)=='[')
            {
                boxopen++;
                if(!brentry.isEmpty())
                {
                    addToTempHash();
                    tempindex++;
                }
                continue;
            }
            else if(Character.isUpperCase(args.charAt(i)))
            {
                temp=String.valueOf(args.charAt(i));
                i=readLower(args, i);
                i=i-1;
            }
            newid=i;
        }
        return newid;
    }
    //This function is used for adding elements in the respective hashmap considering whether any box brackets or round brackets
    //are open if not then add the elements to the reactpro hashmap
    private void addentry(int value) {
        if (boxopen > 0)
        {
            keyexists = brentry.containsKey(temp);
            if (roundopen > 0)
            {
                if (keyexists == false)
                    brentry.put(temp, new Integer(value));
                else
                    updateKeyValue(temp, value, brentry);
            }
            else
            {
                if (keyexists == false)
                    brentry.put(temp, new Integer(value));
                else
                    updateKeyValue(temp, value, brentry);
            }
        }
        else if (roundopen > 0)
        {
            keyexists = brentry.containsKey(temp);
            if (keyexists == false)
                brentry.put(temp, new Integer(value));
            else
                updateKeyValue(temp, value, brentry);
        }
        else
        {
            keyexists = reactpro[hmrpos][hmcpos].containsKey(temp);
            if (keyexists == false)
                reactpro[hmrpos][hmcpos].put(temp, new Integer(value));
            else
                updateKeyValue(temp, value, reactpro[hmrpos][hmcpos]);
        }
    }

    //This function are called when the Box or Round brackets opens we are adding it to the templist hashtable
    //when anyother round or box bracket gets opened
    private void addToTempHash() {
        Enumeration keys=brentry.keys();
        while(keys.hasMoreElements())
        {
            Object retrievedKey=keys.nextElement();
            templist[tempindex].put(retrievedKey,brentry.get(retrievedKey));
        }
        brentry.clear();
    }
    //For calculating the no of digits after the element.
    private int IsDigit(String args, int idx) {
        String variable = "";
        int newid2 = 0;
        for (int k = idx; k < fullLen; k++)
        {
            if (Character.isDigit(args.charAt(k)))
            {
                variable += String.valueOf(args.charAt(k));
                newid2 = k;
            }
            else
            {
                newid2 = k - 1;
                break;
            }
        }
        newid = newid2;
        if (variable.equals("") || variable.equals("0"))
            digitvalue = 1;
        else
            digitvalue=Integer.parseInt(variable);
        return newid;
    }
    //For updating the key value stored in the hashtable with that of the value specified after the round or box bracket
    protected void updateentry(Hashtable h, int value) {
        Enumeration keys=h.keys();
        while(keys.hasMoreElements())
        {
            Object retrievedKey=keys.nextElement();
            h.put(retrievedKey,(Object)String.valueOf(Integer.parseInt(String.valueOf(h.get(retrievedKey))) * value));
        }
    }
    //This function is called when any box or round brackets gets closed we are updating the brentry hashtable
    //with that of the templist hashtable also checking before adding whether any key exists or not.If exists we are updating the key value
    // with that of brentry hashtable and templist hashtable. And also finally adding it to the final hashtable.
    public void matchElementToUpdate(Hashtable a, Hashtable b) {
        //If a hashmap is empty and the b is not empty then directly copy all the elements from hashmap b to hashmap a
        yesno=a.isEmpty() && !b.isEmpty()?true:false;
        if(yesno==true)
        {
            Enumeration keys=b.keys();
            while(keys.hasMoreElements())
            {
                Object retrievedKey=keys.nextElement();
                a.put(retrievedKey, b.get(retrievedKey));
            }
        }
        else
        {
            Enumeration keys1=a.keys();
            while(keys1.hasMoreElements())
            {
                Object retrievedKey1=keys1.nextElement();
                Enumeration keys2=b.keys();
                while(keys2.hasMoreElements())
                {
                    Object retrievedKey2=keys2.nextElement();
                    if(a.containsKey(retrievedKey2))
                    {
                        if(retrievedKey1.equals(retrievedKey2))
                        {
                            int result=Integer.parseInt(String.valueOf(a.get(retrievedKey1)))+ Integer.parseInt(String.valueOf(b.get(retrievedKey2)));
                            a.put(retrievedKey1,(Object)String.valueOf(result));
                            b.remove(retrievedKey2);
                        }
                    }
                    else
                    {
                        a.put(retrievedKey2, b.get(retrievedKey2));
                        b.remove(retrievedKey2);
                    }
                }
            }
        }
        b.clear();
    }
    private void addToReactPro() {
        Enumeration keys=brentry.keys();
        while(keys.hasMoreElements())
        {
            Object retrievedKey=keys.nextElement();
            keyexists=reactpro[hmrpos][hmcpos].containsKey(retrievedKey);
            if(keyexists==false)
                reactpro[hmrpos][hmcpos].put(retrievedKey, brentry.get(retrievedKey));
            else
            {
                int value=Integer.parseInt(String.valueOf(brentry.get(retrievedKey)));
                updateKeyValue(String.valueOf(retrievedKey),value,reactpro[hmrpos][hmcpos]);
            }
        }
        brentry.clear();
    }
    private void updateKeyValue(String key, int newVal, Hashtable h)
    {
        Enumeration keys=h.keys();
        while(keys.hasMoreElements())
        {
            Object retrievedKey=keys.nextElement();
            if(retrievedKey.equals(key))
            {
                if(suffix!=0)
                {
                    h.put(retrievedKey,String.valueOf(Integer.parseInt(String.valueOf(h.get(retrievedKey)))* newVal));
                    suffix=0;
                }
                else
                    h.put(retrievedKey,String.valueOf(Integer.parseInt(String.valueOf(h.get(retrievedKey)))+newVal));
                break;
            }
        }
    }              //This function will return the atomic weight of that particular element which is stored in the hm hashmap
    //which is acting as a database in our case.
    private double getMolWeight(String key)
    {
        double value=0;
        Enumeration keys=hm.keys();
        while(keys.hasMoreElements())
        {
            Object retrievedKey=keys.nextElement();
            if(retrievedKey.equals(key))
            {
                value=Double.parseDouble(String.valueOf(hm.get(retrievedKey)));
                break;
            }
        }
        return value;
    }
    //For calculating the molecular weight of the expression and for storing the molecular weight as per the compounds
    //in the mol_weight variable at a particular row and at a particular's column
    protected void calculateMolWeight(int rindex,int cindex)
    {
        finaloutput=0;
        Enumeration keys=reactpro[rindex][cindex].keys();
        while(keys.hasMoreElements())
        {
            Object retrievedKey=keys.nextElement();
            output=getMolWeight(String.valueOf(retrievedKey));
            finaloutput+=output * Integer.parseInt(String.valueOf(reactpro[rindex][cindex].get(retrievedKey)));
        }
        setFinal_Result(finaloutput);
    }

}