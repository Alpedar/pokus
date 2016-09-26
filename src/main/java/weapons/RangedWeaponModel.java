package weapons;

import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author martin
 */
@Data
public class RangedWeaponModel implements Serializable {

    private RangedWeaponType rangedWeaponType = RangedWeaponType.FIREARM;
    private int accuracy;
    private int damage;
    private int penetration;
    private int recoilCompensation;
    private int baseRange;
    private int shortRangeModifier;
    private int longRangeModifier;
    private int rateOfFire;
    private int techLevel;
    private int robustness;
    private int conceal;

    private WeaponRange range = new WeaponRange(0, 0, 0, 0);
    private RangedWeaponCost cost = new RangedWeaponCost();

    public void update() {
        range = new WeaponRange(baseRange, shortRangeModifier, longRangeModifier);
        cost = new RangedWeaponCost(this);
    }

    @Data
    public static class WeaponRange implements Serializable {

        private int[] rangeSteps;
        private int[] range;

        public WeaponRange(int baseRange, int shortRangeModifer, int longRangeModifier) {
            int sr = (1 + shortRangeModifer) * baseRange;
            int mr = (2) * baseRange;
            int lr = (3 - shortRangeModifer + longRangeModifier) * baseRange;
            int er = (4 - shortRangeModifer + 2 * longRangeModifier) * baseRange;
            setRanges(sr, mr, lr, er);
        }

        public WeaponRange(int shortRange, int mediumRange, int longRange, int extremeRange) {
            setRanges(shortRange, mediumRange, longRange, extremeRange);
        }

        private void setRanges(int srStep, int mrStep, int lrStep, int erStep) {
            this.rangeSteps = new int[]{srStep, mrStep, lrStep, erStep};
            this.range = new int[]{srStep, srStep + mrStep, srStep + mrStep + lrStep, srStep + mrStep + lrStep + erStep};
        }

        public int[] getRanges() {
            return rangeSteps;
        }

        public int getShortRange() {
            return range[0];
        }

        public int getMediumRange() {
            return range[1];
        }

        public int getLongRange() {
            return range[2];
        }

        public int getExtremeRange() {
            return range[3];
        }

        public int getShortRangeStep() {
            return rangeSteps[0];
        }

        public int getMediumRangeStep() {
            return rangeSteps[1];
        }

        public int getLongRangeStep() {
            return rangeSteps[2];
        }

        public int getExtremeRangeStep() {
            return rangeSteps[3];
        }
    }

    @Data
    public static class RangedWeaponCostConstatnts {

        public static final RangedWeaponCostConstatnts INSTANCE = new RangedWeaponCostConstatnts();
        private double damPenWeight = 1;
        private double rcAccWeight = 1;
        private double rangeWeight = 1;
        private int[] rangesWeigths = {5, 4, 3, 2};
    }

    @Data
    private static class RangedWeaponCost {

        private double damPen;
        private double rcAcc;
        private double rng;

        public RangedWeaponCost() {
        }

        public RangedWeaponCost(RangedWeaponModel weapon) {
            final RangedWeaponCostConstatnts consts = RangedWeaponCostConstatnts.INSTANCE;
            damPen = 3 * weapon.getDamage() + weapon.getPenetration();
            damPen *= consts.damPenWeight;
            rcAcc = weapon.getRecoilCompensation() + weapon.getAccuracy() * 2;
            rcAcc *= consts.rcAccWeight;
            rng = 0;
            int[] rangeSteps = weapon.getRange().getRangeSteps();
            int rangeSumWorth = 0;
            double tot = 0;
            for (int i = 0; i < rangeSteps.length; i++) {
                final int w = consts.getRangesWeigths()[i];
                tot += rangeSteps[i] * w;
                rangeSumWorth += w;
            }
            rng = tot / rangeSumWorth;
            rng *= consts.rangeWeight;
        }

    }
}
