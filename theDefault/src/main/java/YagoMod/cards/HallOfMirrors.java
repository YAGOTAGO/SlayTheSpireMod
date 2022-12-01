package YagoMod.cards;

import YagoMod.DefaultMod;
import YagoMod.actions.HallOfMirrorsAction;
import YagoMod.characters.TheDefault;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


import static YagoMod.DefaultMod.makeCardPath;

public class HallOfMirrors extends AbstractDynamicCard {

    /*
     * (2)->(1) Duplicate your hand. Exhaust.
     */

    public static final String ID = DefaultMod.makeID(HallOfMirrors.class.getSimpleName());
    public static final String IMG = makeCardPath("HallOfMirrors.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;
    private static final int COST = 2;
    private static final int COST_UPGRADE = 1;

    public HallOfMirrors() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.actionManager.addToBottom(new HallOfMirrorsAction(p));

    }

    public AbstractCard makeCopy() {return new HallOfMirrors();}

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(COST_UPGRADE);
            initializeDescription();
        }
    }
}
