package weapons;

import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import lombok.Getter;

/**
 *
 * @author martin
 */
@Named(value = "weaponBuilder")
@ViewScoped
public class WeaponBuilder implements Serializable {

    @Getter
    private RangedWeaponModel weapon = new RangedWeaponModel();

    /**
     * Creates a new instance of WeaponBuilder
     */
    public WeaponBuilder() {
    }

    public void updateWeapon() {
        weapon.update();
    }
}
