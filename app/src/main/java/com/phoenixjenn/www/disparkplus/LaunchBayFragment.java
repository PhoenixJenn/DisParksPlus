package com.phoenixjenn.www.disparkplus;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class LaunchBayFragment extends Fragment {

    Button btnClearText;
    Button btnBackSpace;
    Button btnSpaceBar;
    Button btnA;
    Button btnB;
    Button btnC;
    Button[] buttons = new Button[3];

    int i; //used for looping through the buttons


    TextView textViewEnglish;
    TextView textViewAurebesh;
    Switch toggleSwitch;

    //fonts
    Typeface typeFace;
    Typeface typeFaceAurebesh;
    Typeface typeFaceEnglish;

    //primitives
    Boolean firstCharacter = false;
    Boolean switchState;

    View thisFragmentView;

    //
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View thisFragmentView = inflater.inflate(R.layout.launch_bay, container, false);



        typeFaceAurebesh = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Aurebesh.ttf");
        typeFaceEnglish = Typeface.SANS_SERIF;


        //Initialize in English
        textViewEnglish = (TextView) thisFragmentView.findViewById(R.id.text_view_english);
        textViewEnglish.setTypeface(typeFaceEnglish);
        textViewEnglish.setText("Start typing");

        //Initialize Aurebesh
        textViewAurebesh = (TextView) thisFragmentView.findViewById(R.id.text_view_aurebesh);
        textViewAurebesh.setTypeface(typeFaceAurebesh);
        textViewAurebesh.setText("Start typing");




        //use the toggle and add an event listener which will call the setFonts method to swap languages

        toggleSwitch = (Switch) thisFragmentView.findViewById(R.id.aurebesh_english_switch);

        toggleSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // initiate a Switch
                // check current state of a Switch (true or false). http://abhiandroid.com/ui/switch
                // reset fonts
                setFonts();


            }
        });

        btnClearText = (Button) thisFragmentView.findViewById((R.id.buttonClear));
        btnClearText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                clearText();
            }

        });

        btnBackSpace = (Button) thisFragmentView.findViewById((R.id.buttonBackSpace));

        btnBackSpace.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                removeLetter();

            }

        });



        btnSpaceBar = (Button) thisFragmentView.findViewById((R.id.buttonSPACE));
        btnSpaceBar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addLetter(" ");

            }

        });


/*
* This is the hackiest way to do this. I can't get a handle on the button in the OnClick method so i have to hard code each
* Also, i can't just loop through the setting of OnClick Listeners
*
* http://www.i-programmer.info/programming/android/6882-introducing-android-fragments.html?start=2
*
* */
        btnA = (Button) thisFragmentView.findViewById(R.id.buttonA);

        btnA.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addLetter("A");
            }
        });

        btnB = (Button) thisFragmentView.findViewById(R.id.buttonB);
        btnB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addLetter("B");
            }
        });

        btnC = (Button) thisFragmentView.findViewById(R.id.buttonC);
        btnC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addLetter("C");
            }
        });


        // upButton = (Button) view.findViewById(R.id.smart_tv_controller_framgment_up_button);
       // upButton.setOnClickListener(this);
        //abstracted this logic for setting the button typefaces so it can be called on the switch
        setFonts();

        return thisFragmentView;
    }





    //TODO put code here ie, buttons etc


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

/** Called when a user clicks a letter button **/
    public  void addLetter(String letter){

        try {
            if (firstCharacter == false) {
                clearText();
                firstCharacter = true;
            }

            Button b = (Button) thisFragmentView;

           // b.setText("AA");
            String buttonText = letter;
           // String buttonText = b.getText().toString();
            //String buttonText = letter;

            //Aurebesh
            String oldText = textViewAurebesh.getText().toString();
            textViewAurebesh.setText(oldText + buttonText);

            //English
            String oldTextEnglish = textViewEnglish.getText().toString();
            textViewEnglish.setText(oldTextEnglish + buttonText);
        } catch (Exception e){
            String myException = e.toString();

            Toast.makeText(getActivity(), myException,
                    Toast.LENGTH_LONG).show();
        }
    }


    /** Called when a user clicks the del button **/
    public void removeLetter(){
        try {
            //clear the whole field if the page just loaded
            if (firstCharacter == false) {
                clearText();
                firstCharacter = true;
            }

            String oldText = textViewAurebesh.getText().toString();
            if (oldText.length() > 0) {

                oldText = oldText.substring(0, oldText.length() - 1);
                textViewAurebesh.setText(oldText);

                //since same text in both text boxes, no need ot do the math twice
                textViewEnglish.setText(oldText);
            }
        } catch (Exception e){
            Log.i("Exception",e.toString());
        }
    }

    public void clearText(){

        //Log.i("tv","textViewAurebesh");
        try {
            textViewAurebesh.setText("");
            textViewEnglish.setText("");
        } catch (Exception e){
            Log.i("exception Clear", e.toString());
        }

    }
    private void setFonts() {

        //Log.i("toggle","got here");

        try {
           // toggleSwitch = (Switch) getView().findViewById(R.id.aurebesh_english_switch);
            switchState = toggleSwitch.isChecked();
            if (switchState == true){

                typeFace = typeFaceAurebesh;
                toggleSwitch.setText("Aurebesh to English");
                textViewAurebesh.setTypeface(typeFaceEnglish);
                textViewEnglish.setTypeface(typeFaceAurebesh);
            } else {

                typeFace = typeFaceEnglish;
                toggleSwitch.setText("English to Aurebesh");
                textViewAurebesh.setTypeface(typeFaceAurebesh);
                textViewEnglish.setTypeface(typeFaceEnglish);
            }
            //Create a loop that does this

            Button btnA = (Button) getView().findViewById(R.id.buttonA);

            btnA.setTypeface(typeFace);
            Button btnB = (Button)this.getView().findViewById(R.id.buttonB);
            btnB.setTypeface(typeFace);
            Button btnC = (Button)this.getView().findViewById(R.id.buttonC);
            btnC.setTypeface(typeFace);
            Button btnD = (Button)this.getView().findViewById(R.id.buttonD);
            btnD.setTypeface(typeFace);

            Button btnE = (Button)this.getView().findViewById(R.id.buttonE);
            btnE.setTypeface(typeFace);
            Button btnF = (Button)this.getView().findViewById(R.id.buttonF);
            btnF.setTypeface(typeFace);
            Button btnG = (Button)this.getView().findViewById(R.id.buttonG);
            btnG.setTypeface(typeFace);

            Button btnH = (Button)this.getView().findViewById(R.id.buttonH);
            btnH.setTypeface(typeFace);
            Button btnI = (Button)this.getView().findViewById(R.id.buttonI);
            btnI.setTypeface(typeFace);
            Button btnJ = (Button)this.getView().findViewById(R.id.buttonJ);
            btnJ.setTypeface(typeFace);
//groups of 10

            Button btnK = (Button)this.getView().findViewById(R.id.buttonK);
            btnK.setTypeface(typeFace);
            Button btnL = (Button)this.getView().findViewById(R.id.buttonL);
            btnB.setTypeface(typeFace);
            Button btnM = (Button)this.getView().findViewById(R.id.buttonM);
            btnM.setTypeface(typeFace);
            Button btnN = (Button)this.getView().findViewById(R.id.buttonN);
            btnN.setTypeface(typeFace);

            Button btnO = (Button)this.getView().findViewById(R.id.buttonO);
            btnO.setTypeface(typeFace);
            Button btnP = (Button)this.getView().findViewById(R.id.buttonP);
            btnP.setTypeface(typeFace);
            Button btnQ = (Button)this.getView().findViewById(R.id.buttonQ);
            btnQ.setTypeface(typeFace);

            Button btnR = (Button)this.getView().findViewById(R.id.buttonR);
            btnR.setTypeface(typeFace);
            Button btnS = (Button)this.getView().findViewById(R.id.buttonS);
            btnS.setTypeface(typeFace);
            Button btnT = (Button)this.getView().findViewById(R.id.buttonT);
            btnT.setTypeface(typeFace);
//group of 6

            Button btnU = (Button)this.getView().findViewById(R.id.buttonU);
            btnU.setTypeface(typeFace);
            Button btnV = (Button)this.getView().findViewById(R.id.buttonV);
            btnV.setTypeface(typeFace);
            Button btnW = (Button)this.getView().findViewById(R.id.buttonW);
            btnW.setTypeface(typeFace);
            Button btnX = (Button)this.getView().findViewById(R.id.buttonX);
            btnX.setTypeface(typeFace);
            Button btnY = (Button)this.getView().findViewById(R.id.buttonY);
            btnY.setTypeface(typeFace);
            Button btnZ = (Button)this.getView().findViewById(R.id.buttonZ);
            btnZ.setTypeface(typeFace);

            Button btnBackSpace = (Button)this.getView().findViewById(R.id.buttonBackSpace);
// btnBackSpace.setTypeface(typeFace);

            Button btnSpace = (Button)this.getView().findViewById(R.id.buttonSPACE);
//btnSpace.setTypeface(typeFace);


        } catch (Exception e){
            Log.i("exception", e.toString());

        }
      //

/*


    }
    */

        /*



         */
    }


}
