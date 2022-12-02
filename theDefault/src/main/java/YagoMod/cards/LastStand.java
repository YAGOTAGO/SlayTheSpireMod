package YagoMod.cards;

import YagoMod.DefaultMod;
import YagoMod.characters.TheDefault;
import YagoMod.powers.LastStandPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static YagoMod.DefaultMod.makeCardPath;

public class LastStand extends AbstractDynamicCard {

    /*
     * (0) Heal 2(4) at the start of the next turn lose 2(4) HP.
     */

    public static final String ID = DefaultMod.makeID(LastStand.class.getSimpleName());
    public static final String IMG = makeCardPath("LastStand.png");
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;
    private static final int COST = 0;
    private static final int HEAL_HPLOSS = 2;
    private static final int HEAL_HPLOSS_INC = 2;

    public LastStand() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = HEAL_HPLOSS;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new LastStandPower(p, p, 1, magicNumber)));
    }

    public AbstractCard makeCopy() {return new LastStand();}

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(HEAL_HPLOSS_INC);
            initializeDescription();
        }
    }
}
