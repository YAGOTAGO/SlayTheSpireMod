package YagoMod.cards;

import YagoMod.DefaultMod;
import YagoMod.characters.TheDefault;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Iterator;


import static YagoMod.DefaultMod.makeCardPath;

public class RainOfTerror extends AbstractDynamicCard {

    /*
     * (1) Choose one: Give weak or vulnerable to 4(8) random enemies
     */

    public static final String ID = DefaultMod.makeID(RainOfTerror.class.getSimpleName());
    public static final String IMG = makeCardPath("RainOfTerror.png");
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int APPLY_AMOUNT = 4;
    private static final int APPLY_UPGRADE = 4;
    public RainOfTerror() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = APPLY_AMOUNT;
        this.magicNumber = this.baseMagicNumber;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        ArrayList<AbstractCard> choices = new ArrayList();
        choices.add(new BendTheKnee());
        choices.add(new BrokenWill());

        if (this.upgraded) {
            Iterator var4 = choices.iterator();

            while(var4.hasNext()) {
                AbstractCard c = (AbstractCard)var4.next();
                c.upgrade();
            }
        }

        this.addToBot(new ChooseOneAction(choices));
    }



    public AbstractCard makeCopy() {return new RainOfTerror();}

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(APPLY_UPGRADE);
            initializeDescription();
        }
    }
}
