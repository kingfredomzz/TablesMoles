package king.echomood.periodictable;



import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import king.echomood.periodictable.data.SampleObject;


public class TableMainLayout extends RelativeLayout {

    public final String TAG = "TableMainLayout.java";

    // set the header titles
    String headers[] = {
            "",
            "H+",
            "Li+",
            "NH4+",
            "K+",
            "Na+",
            "Ag+",
            "Ba2+",
            "Ca2+",
            "Mg2+",
            "Zn2+",
            "Mn2+",

            "Cu2+",
            "Cu+",
            "Hg",
            "Hg2+",
            "Pb2+",
            "Fe2+",
            "Fe3+",
            "Al3+",
            "Cr3+",
            "Bi3+",
            "Sn2+",
            "Sr2+"
    };
    String columns[] = {

            "OH -",
            "NO 3-",
            "F-",
            "Cl -",
            "Br -",
            "I -",
            "S -",
            "So3 -2",
            "SO4 -3",
            "CO3 -2",
            "SiO3 -2",
            "PO4 -3",
            "CrO4 -2",
            "CH4COO -"
    };

    TableLayout tableA;
    TableLayout tableB;
    TableLayout tableC;
    TableLayout tableD;

    HorizontalScrollView horizontalScrollViewB;
    HorizontalScrollView horizontalScrollViewD;

    ScrollView scrollViewC;
    ScrollView scrollViewD;

    Context context;

    List<SampleObject> sampleObjects = this.sampleObjects();

    int headerCellsWidth[] = new int[headers.length];

    public TableMainLayout(Context context , AttributeSet attributeSet) {

        super(context , attributeSet);

        this.context = context;

        // initialize the main components (TableLayouts, HorizontalScrollView, ScrollView)
        this.initComponents();
        this.setComponentsId();
        this.setScrollViewAndHorizontalScrollViewTag();


        // no need to assemble component A, since it is just a table
        this.horizontalScrollViewB.addView(this.tableB);

        this.scrollViewC.addView(this.tableC);

        this.scrollViewD.addView(this.horizontalScrollViewD);
        this.horizontalScrollViewD.addView(this.tableD);

        // add the components to be part of the main layout
        this.addComponentToMainLayout();
        this.setBackgroundColor(Color.BLACK);


        // add some table rows
        this.addTableRowToTableA();
        this. addTableRowToTableB();

        this.resizeHeaderHeight();

        this.getTableRowHeaderCellWidth();

        this.generateTableC_AndTable_B();

        this.resizeBodyTableRowHeight();
    }

    // this is just the sample data
    List<SampleObject> sampleObjects(){

        List<SampleObject> sampleObjects = new ArrayList<SampleObject>();

        for(int x=0; x < columns.length; x++){
            SampleObject sampleObject = new SampleObject(
                    columns[x] ,
                    "*",
                    "S",
                    "S",
                    "S",
                    "S",
                    "-",
                    "S",
                    "P",
                    "I",
                    "I",
                    "I",
                    "I",
                    "I",
                    "-",
                    "-",
                    "I",
                    "I",
                    "I",
                    "I",
                    "I",
                    "I",
                    "I",
                    "P"

            );

            if (x == 1) {
                 sampleObject = new SampleObject(
                         "bb" ,
                         "M",
                         "S",
                         "S",
                         "S",
                         "S",
                         "-",
                         "S",
                         "P",
                         "I",
                         "I",
                         "I" ,
                         "I" ,
                         "I" ,
                         "-" ,
                         "-" ,
                         "I" ,
                         "I" ,
                         "I" ,
                         "I"  ,
                         "I" ,
                         "I",
                         "I",
                         "P"
                 );
            }

            if ( x==1 ){
                sampleObject = new SampleObject(
                        columns[x] ,
                        "S",
                        "S",
                        "S",
                        "S",
                        "S",
                        "S",
                        "S",
                        "S",
                        "S",
                        "S",
                        "S" ,
                        "S" ,
                        "S" ,
                        "S" ,
                        "S" ,
                        "S" ,
                        "S" ,
                        "S" ,
                        "S" ,
                        "S"  ,
                        "S" ,
                        "-",
                        "S"
                );
            }


            if (x ==3) {
                sampleObject = new SampleObject(
                        columns[x] ,
                        "S",
                        "S",
                        "S",
                        "S",
                        "S",
                        "S",
                        "P",
                        "I",
                        "I",
                        "P",
                        "P" ,
                        "I" ,
                        "-" ,
                        "I" ,
                        "P",
                        "I" ,
                        "P" ,
                        "S" ,
                        "S" ,
                        "S"  ,
                        "I" ,
                        "S",
                        "P"
                );
            }

            if (x==4){
                sampleObject = new SampleObject(
                columns[x] ,
                        "S",
                        "S",
                        "S",
                        "S",
                        "S",
                        "I",
                        "S",
                        "I",
                        "I",
                        "P",
                        "P" ,
                        "I" ,
                        "-" ,
                        "I" ,
                        "S",
                        "P" ,
                        "S" ,
                        "S" ,
                        "S" ,
                        "S"  ,
                        "-" ,
                        "S",
                        "S" );
            }

            sampleObjects.add(sampleObject);
        }

        return sampleObjects;

    }

    // initalized components
    private void initComponents(){

        this.tableA = new TableLayout(this.context);
        this.tableB = new TableLayout(this.context);
        this.tableC = new TableLayout(this.context);
        this.tableD = new TableLayout(this.context);

        this.horizontalScrollViewB = new MyHorizontalScrollView(this.context);
        this.horizontalScrollViewD = new MyHorizontalScrollView(this.context);

        this.scrollViewC = new MyScrollView(this.context);
        this.scrollViewD = new MyScrollView(this.context);

        this.tableA.setBackgroundColor(getResources().getColor(R.color.sul_heaers));
        this.horizontalScrollViewB.setBackgroundColor(getResources().getColor(R.color.sul_background_D));


    }

    // set essential component IDs
    private void setComponentsId(){
        this.tableA.setId(1);
        this.horizontalScrollViewB.setId(2);
        this.scrollViewC.setId(3);
        this.scrollViewD.setId(4);
    }

    // set tags for some horizontal and vertical scroll view
    private void setScrollViewAndHorizontalScrollViewTag(){

        this.horizontalScrollViewB.setTag("horizontal scroll view b");
        this.horizontalScrollViewD.setTag("horizontal scroll view d");

        this.scrollViewC.setTag("scroll view c");
        this.scrollViewD.setTag("scroll view d");
    }

    // we add the components here in our TableMainLayout
    private void addComponentToMainLayout(){

        // RelativeLayout params were very useful here
        // the addRule method is the key to arrange the components properly
        LayoutParams componentB_Params = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        
        componentB_Params.addRule(RelativeLayout.RIGHT_OF, this.tableA.getId());

        LayoutParams componentC_Params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        componentC_Params.addRule(RelativeLayout.BELOW, this.tableA.getId());

        LayoutParams componentD_Params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        componentD_Params.addRule(RelativeLayout.RIGHT_OF, this.scrollViewC.getId());
        componentD_Params.addRule(RelativeLayout.BELOW, this.horizontalScrollViewB.getId());

        // 'this' is a relative layout,
        // we extend this table layout as relative layout as seen during the creation of this class
        this.addView(this.tableA , 250 , LayoutParams.WRAP_CONTENT);

        this.addView(this.horizontalScrollViewB, componentB_Params);
        this.addView(this.scrollViewC, componentC_Params);
        this.addView(this.scrollViewD, componentD_Params);

    }



    private void addTableRowToTableA(){
        this.tableA.addView(this.componentATableRow());
    }

    private void addTableRowToTableB(){
        this.tableB.addView(this.componentBTableRow());
    }

    // generate table row of table A
    TableRow componentATableRow(){

        TableRow componentATableRow = new TableRow(this.context);
        TextView textView = this.headerTextView(this.headers[0]);
        textView.setBackgroundColor(getResources().getColor(R.color.sul_heaers));

        componentATableRow.addView(textView);

        return componentATableRow;
    }

    // generate table row of table B
    TableRow componentBTableRow(){

        TableRow componentBTableRow = new TableRow(this.context);
        int headerFieldCount = this.headers.length;

        TableRow.LayoutParams params = new TableRow.LayoutParams(140,LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 0);

        for(int x=0; x<(headerFieldCount-1); x++){
            TextView textView = this.headerTextView(this.headers[x+1]);
            textView.setLayoutParams(params);
            textView.setBackgroundColor(getResources().getColor(R.color.sul_heaers));
            textView.setTextColor(Color.WHITE);
            componentBTableRow.addView(textView);
        }

        return componentBTableRow;
    }

    // generate table row of table C and table D
    private void generateTableC_AndTable_B(){

        // just seeing some header cell width
        for(int x=0; x<this.headerCellsWidth.length; x++){
            Log.v("TableMainLayout.java", this.headerCellsWidth[x]+"");
        }

        for(SampleObject sampleObject : this.sampleObjects){

            TableRow tableRowForTableC = this.tableRowForTableC(sampleObject);
            TableRow taleRowForTableD = this.taleRowForTableD(sampleObject);

            tableRowForTableC.setBackgroundColor(Color.LTGRAY);
            taleRowForTableD.setBackgroundColor(getResources().getColor(R.color.sul_background_D));

            this.tableC.addView(tableRowForTableC);
            this.tableD.addView(taleRowForTableD);

        }
    }

    // a TableRow for table C
    TableRow tableRowForTableC(SampleObject sampleObject){

        TableRow.LayoutParams params = new TableRow.LayoutParams( 250,100);
        params.setMargins(0, 0, 0, 0);

        TableRow tableRowForTableC = new TableRow(this.context);
        TextView textView = this.bodyTextView(sampleObject.header1);
        textView.setTextColor(Color.WHITE);
        textView.setBackgroundColor(getResources().getColor(R.color.sul_heaers));
        textView.setBackground(getResources().getDrawable(R.drawable.borders));
        tableRowForTableC.addView(textView,params);

        return tableRowForTableC;
    }

    TableRow taleRowForTableD(SampleObject sampleObject){

        TableRow taleRowForTableD = new TableRow(this.context);

        int loopCount = ((TableRow)this.tableB.getChildAt(0)).getChildCount();
        String info[] = {
                sampleObject.header2,
                sampleObject.header3,
                sampleObject.header4,
                sampleObject.header5,
                sampleObject.header6,
                sampleObject.header7,
                sampleObject.header8,
                sampleObject.header9,
                sampleObject.header10,
                sampleObject.header11,
                sampleObject.header12,
                sampleObject.header13,
                sampleObject.header14,
                sampleObject.header15,
                sampleObject.header16,
                sampleObject.header17,
                sampleObject.header18,
                sampleObject.header19,
                sampleObject.header20,
                sampleObject.header21,
                sampleObject.header22,
                sampleObject.header23,
                sampleObject.header24,

        };

        for(int x=0 ; x<loopCount; x++) {
            TableRow.LayoutParams params = new TableRow.LayoutParams(140, 100);
            params.setMargins(0, 0, 0, 0);

            TextView textViewB = this.bodyTextView(info[x]);
            taleRowForTableD.addView(textViewB,params);
        }
        return taleRowForTableD;
    }

    // table cell standard TextView
    TextView bodyTextView(String label) {

        TextView bodyTextView = new TextView(this.context);

        if (label == "P") {
            bodyTextView.setTextColor(getResources().getColor(R.color.yell));
            bodyTextView.setBackgroundDrawable(getResources().getDrawable(R.drawable.yellow_borders));
        } else if (label == "I") {
            bodyTextView.setTextColor(getResources().getColor(R.color.redo));
            bodyTextView.setBackgroundDrawable(getResources().getDrawable(R.drawable.redo_borders));
        } else if (label == "S") {
            bodyTextView.setTextColor(getResources().getColor(R.color.gree));
            bodyTextView.setBackgroundDrawable(getResources().getDrawable(R.drawable.green_borders));
        } else if (label == "-"){
            bodyTextView.setTextColor(getResources().getColor(R.color.bluws));
            bodyTextView.setBackgroundDrawable(getResources().getDrawable(R.drawable.blues_borders));
        }else {
            bodyTextView.setTextColor(Color.WHITE);
            bodyTextView.setBackgroundDrawable(getResources().getDrawable(R.drawable.white_borders));
        }

        bodyTextView.setText(label);
        bodyTextView.setGravity(Gravity.CENTER);
        bodyTextView.setPadding(5, 5, 5, 5);

        return bodyTextView;
    }

    // header standard TextView
    TextView headerTextView(String label){

        TextView headerTextView = new TextView(this.context);
        headerTextView.setBackgroundColor(Color.WHITE);
        headerTextView.setText(label);
        headerTextView.setGravity(Gravity.CENTER);
        headerTextView.setPadding(5, 5, 5, 5);

        return headerTextView;
    }

    // resizing TableRow height starts here
    void resizeHeaderHeight() {

        TableRow productNameHeaderTableRow = (TableRow) this.tableA.getChildAt(0);
        TableRow productInfoTableRow = (TableRow)  this.tableB.getChildAt(0);

        int rowAHeight = this.viewHeight(productNameHeaderTableRow);
        int rowBHeight = this.viewHeight(productInfoTableRow);

        TableRow tableRow = rowAHeight < rowBHeight ? productNameHeaderTableRow : productInfoTableRow;
        int finalHeight = rowAHeight > rowBHeight ? rowAHeight : rowBHeight;

        this.matchLayoutHeight(tableRow, finalHeight);
    }

    void getTableRowHeaderCellWidth(){

        int tableAChildCount = ((TableRow)this.tableA.getChildAt(0)).getChildCount();
        int tableBChildCount = ((TableRow)this.tableB.getChildAt(0)).getChildCount();;

        for(int x=0; x<(tableAChildCount+tableBChildCount); x++){

            if(x==0){
                this.headerCellsWidth[x] = this.viewWidth(((TableRow)this.tableA.getChildAt(0)).getChildAt(x));
            }else{
                this.headerCellsWidth[x] = this.viewWidth(((TableRow)this.tableB.getChildAt(0)).getChildAt(x-1));
            }

        }
    }

    // resize body table row height
    void resizeBodyTableRowHeight(){

        int tableC_ChildCount = this.tableC.getChildCount();

        for(int x=0; x<tableC_ChildCount; x++){

            TableRow productNameHeaderTableRow = (TableRow) this.tableC.getChildAt(x);
            TableRow productInfoTableRow = (TableRow)  this.tableD.getChildAt(x);

            int rowAHeight = this.viewHeight(productNameHeaderTableRow);
            int rowBHeight = this.viewHeight(productInfoTableRow);

            TableRow tableRow = rowAHeight < rowBHeight ? productNameHeaderTableRow : productInfoTableRow;
            int finalHeight = rowAHeight > rowBHeight ? rowAHeight : rowBHeight;

            this.matchLayoutHeight(tableRow, finalHeight);
        }

    }

    // match all height in a table row
    // to make a standard TableRow height
    private void matchLayoutHeight(TableRow tableRow, int height) {

        int tableRowChildCount = tableRow.getChildCount();

        // if a TableRow has only 1 child
        if(tableRow.getChildCount()==1){

            View view = tableRow.getChildAt(0);
            TableRow.LayoutParams params = (TableRow.LayoutParams) view.getLayoutParams();
            params.height = height - (params.bottomMargin + params.topMargin);

            return ;
        }

        // if a TableRow has more than 1 child
        for (int x = 0; x < tableRowChildCount; x++) {

            View view = tableRow.getChildAt(x);

            TableRow.LayoutParams params = (TableRow.LayoutParams) view.getLayoutParams();

            if (!isTheHeighestLayout(tableRow, x)) {
                params.height = height - (params.bottomMargin + params.topMargin);
                return;
            }
        }

    }

    // check if the view has the highest height in a TableRow
    private boolean isTheHeighestLayout(TableRow tableRow, int layoutPosition) {

        int tableRowChildCount = tableRow.getChildCount();
        int heighestViewPosition = -1;
        int viewHeight = 0;

        for (int x = 0; x < tableRowChildCount; x++) {
            View view = tableRow.getChildAt(x);
            int height = this.viewHeight(view);

            if (viewHeight < height) {
                heighestViewPosition = x;
                viewHeight = height;
            }
        }

        return heighestViewPosition == layoutPosition;
    }

    // read a view's height
    private int viewHeight(View view) {
        view.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
        return view.getMeasuredHeight();
    }

    // read a view's width
    private int viewWidth(View view) {
        view.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
        return view.getMeasuredWidth();
    }

    // horizontal scroll view custom class
    class MyHorizontalScrollView extends HorizontalScrollView{

        public MyHorizontalScrollView(Context context) {
            super(context);
        }

        @Override
        protected void onScrollChanged(int l, int t, int oldl, int oldt) {
            String tag = (String) this.getTag();

            if(tag.equalsIgnoreCase("horizontal scroll view b")){
                horizontalScrollViewD.scrollTo(l, 0);
            }else{
                horizontalScrollViewB.scrollTo(l, 0);
            }
        }

    }

    // scroll view custom class
    class MyScrollView extends ScrollView{

        public MyScrollView(Context context) {
            super(context);
        }

        @Override
        protected void onScrollChanged(int l, int t, int oldl, int oldt) {

            String tag = (String) this.getTag();

            if(tag.equalsIgnoreCase("scroll view c")){
                scrollViewD.scrollTo(0, t);
            }else{
                scrollViewC.scrollTo(0,t);
            }
        }
    }


}