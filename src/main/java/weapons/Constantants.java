package weapons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TableColumn.CellEditEvent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import weapons.RangedWeaponModel.RangedWeaponCostConstatnts;

/**
 *
 * @author martin
 */
@Named(value = "constants")
@ViewScoped
public class Constantants implements Serializable {

    private final List<ReflectUtil.ObjectAccess> objects = new ArrayList<>();

    {
        objects.add(new ReflectUtil.ObjectAccess(RangedWeaponCostConstatnts.INSTANCE));
    }

    /**
     * Creates a new instance of WeaponBuilder
     */
    public Constantants() {
    }

    public List<ReflectUtil.ObjectAccess> getObjects() {
        return objects;
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}
