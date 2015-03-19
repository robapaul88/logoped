package pl.surecase.eu;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyDaoGenerator {

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(2, "com.android.logoped.model");
        Entity box = schema.addEntity("Word");
        box.addIdProperty();
        box.addStringProperty("name");
        box.addStringProperty("imageName");
        box.addStringProperty("audioName");
        box.addStringProperty("fonem");
        box.addIntProperty("answerType");
        new DaoGenerator().generateAll(schema, args[0]);
    }
}
