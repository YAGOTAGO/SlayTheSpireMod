package YagoMod.cards;

import YagoMod.DefaultMod;
import YagoMod.actions.DevotionAction;
import YagoMod.characters.TheDefault;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


import static YagoMod.DefaultMod.makeCardPath;

public class Devotion extends AbstractDynamicCard {

    /*
     * SKILL:
     * (2) Discard up to 3(4) cards, heal 1 for each discarded.
     */

    public static final String ID = DefaultMod.makeID(Devotion.class.getSimpleName());
    public static final String IMG = makeCardPath("Devotion.png");

    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 2;
    private static final int AMOUNT = 3;
    private static final int AMOUNT_PLUS = 1;

    public Devotion() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = AMOUNT;
        magicNumber = baseMagicNumber;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DevotionAction(magicNumber));
    }


    public AbstractCard makeCopy() {return new Devotion();}

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(AMOUNT_PLUS);
            initializeDescription();
        }
    }
}
