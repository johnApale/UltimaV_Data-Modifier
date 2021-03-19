package javafx;

public class Avatar {
    String name;
    int strength, intelli, dex, hp, skull, gold, exp, keys, maxhp, gems, badge, carpets, axes;

    public Avatar(String name, int str, int intelli, int dex, int hp, int skull, int gold, int exp,
                  int keys, int maxhp, int gems, int badge, int carpets, int axes){
        this.name = name;
        this.strength = str;
        this.intelli = intelli;
        this.dex = dex;
        this.hp = hp;
        this.skull = skull;
        this.gold = gold;
        this.exp = exp;
        this.keys = keys;
        this.maxhp = maxhp;
        this.gems = gems;
        this.badge = badge;
        this.carpets = carpets;
        this.axes = axes;
    }


    public String getName() {
        return name;
    }

    public int getStrength() {
        return strength;
    }

    public int getIntelli() {
        return intelli;
    }

    public int getDex() {
        return dex;
    }

    public int getHP() {
        return hp;
    }

    public int getSkull() {
        return skull;
    }

    public int getGold() {
        return gold;
    }

    public int getExp() {
        return exp;
    }

    public int getKeys() {
        return keys;
    }

    public int getMaxHP() {
        return maxhp;
    }

    public int getGems() {
        return gems;
    }

    public int getBadge() {
        return badge;
    }

    public int getCarpets() {
        return carpets;
    }

    public int getAxes() {
        return axes;
    }
}
