package weapons;

/**
 *
 * @author martin
 */
public enum RangedWeaponType {
    THROWING/*    */(-2, +1, +4, +10),
    MECHANICAL/*  */(-2, +0, +2, +6),
    BLASTER/*     */(-3, -1, +1, +5),
    FIREARM/*     */(-3, -2, -1, +1),
    KEW/*         */(-3, -3, -2, +0),
    RELATIVISTIC/**/(-3, -3, -3, -3);

    private int[] dodgeModifiers;

    private RangedWeaponType(int shortRange, int mediumRange, int longRange, int extremeRange) {
        this.dodgeModifiers = new int[]{shortRange, mediumRange, longRange, extremeRange};
    }

    public int[] getDodgeModifiers() {
        return dodgeModifiers;
    }

    public int getShortRangeModifier() {
        return dodgeModifiers[0];
    }

    public int getMediumRangeModifier() {
        return dodgeModifiers[1];
    }

    public int getLongRangeModifier() {
        return dodgeModifiers[2];
    }

    public int getExtremeRangeModifier() {
        return dodgeModifiers[3];
    }

}
