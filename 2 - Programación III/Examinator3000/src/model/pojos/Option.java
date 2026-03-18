package model.pojos;

import java.io.Serializable;

public class Option implements Serializable {

    private String text;
    private String rationale;
    private boolean isCorrect;

    public Option(String text, String rationale, boolean isCorrect) {
        this.text = text;
        this.rationale = rationale;
        this.isCorrect = isCorrect;
    }

    public String getRationale() { return rationale; }
    public String getText() { return text; }
    public boolean getIsCorrect() { return isCorrect;}

    @Override
    public String toString() { return ("\n| " + (isCorrect ? "[X]" : "[ ]") + " " + text + " (" + rationale + ")"); }


}
